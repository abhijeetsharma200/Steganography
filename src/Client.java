import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
//a
public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 1234);
        System.out.println("connected to server");

        JFrame jframe = new JFrame("Client");
        jframe.setSize(500, 500);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon imageIcon1 = new ImageIcon("cat.png");
        ImageIcon imageIcon2 = new ImageIcon("dog.png");
        JLabel jLabelPicture1 = new JLabel(imageIcon1);
        JLabel jLabelPicture2 = new JLabel(imageIcon2);
        JButton jButton = new JButton("Send images");

        jframe.add(jLabelPicture1, BorderLayout.WEST);
        jframe.add(jLabelPicture2, BorderLayout.EAST);
        jframe.add(jButton, BorderLayout.SOUTH);

        jframe.setVisible(true);

        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    OutputStream outputStream = socket.getOutputStream();
                    BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);

                    Image image1 = imageIcon1.getImage();
                    Image image2 = imageIcon2.getImage();

                    BufferedImage bufferedImage1 = new BufferedImage(image1.getWidth(null), image1.getHeight(null), BufferedImage.TYPE_3BYTE_BGR);
                    Graphics graphics1 = bufferedImage1.createGraphics();
                    graphics1.drawImage(image1, 0, 0, null);
                    graphics1.dispose();

                    BufferedImage bufferedImage2 = new BufferedImage(image2.getWidth(null), image2.getHeight(null), BufferedImage.TYPE_3BYTE_BGR);
                    Graphics graphics2 = bufferedImage2.createGraphics();
                    graphics2.drawImage(image1, 0, 0, null);
                    graphics2.dispose();

                    ImageIO.write(bufferedImage1, "png", bufferedOutputStream);
                    ImageIO.write(bufferedImage2, "png", bufferedOutputStream);

                    bufferedOutputStream.close();
                    socket.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
