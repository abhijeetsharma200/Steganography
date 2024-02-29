import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
//server
public class Server {
    public static void main(String[] args) throws IOException {
        JFrame jframe = new JFrame("Server");
        jframe.setSize(500, 500);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel jLabelText = new JLabel("waiting for images from client");
        jframe.add(jLabelText, BorderLayout.SOUTH);
        jframe.setVisible(true);

        ServerSocket serverSocket = new ServerSocket(1234);

        Socket socket = serverSocket.accept();

        InputStream inputStream = socket.getInputStream();
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        BufferedImage bufferedImage = ImageIO.read(bufferedInputStream);

        bufferedInputStream.close();
        socket.close();

        JLabel jLabelPic = new JLabel(new ImageIcon(bufferedImage));
        jLabelText.setText("Images are received");
        jframe.add(jLabelPic, BorderLayout.CENTER);
    }
}
