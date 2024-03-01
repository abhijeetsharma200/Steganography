import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Encoder {
    public static void main(String[] args) throws IOException{
        try {
            String catImgPath = "cat.png";

            String dogImgPath = "dog.png";

            BufferedImage cat = ImageIO.read(new File(catImgPath));

            BufferedImage dog = ImageIO.read(new File(dogImgPath));

            int catWidth = cat.getWidth();
            int catHeight = cat.getHeight();
            int imageType = cat.getType();
            System.out.println(imageType);
            int dogWidth = dog.getWidth();
            int dogHeight = dog.getHeight();
            System.out.println(dog.getType());

            BufferedImage modified_cat = new BufferedImage(catWidth, catHeight, BufferedImage.TYPE_3BYTE_BGR);
            System.out.println(modified_cat.getType());

            for(int y = 0; y < dogHeight; y++){
                for(int x = 0; x < dogWidth; x++){
                    int catRGB = cat.getRGB(x,y);
                    int dogRGB = dog.getRGB(x, y);

                    int catRed = (catRGB >> 20) & 0xF;
                    int catGreen = (catRGB >> 12) & 0xF;
                    int catBlue = (catRGB >> 4) & 0xF;

                    int dogRed = (dogRGB >> 20) & 0xF;
                    int dogGreen = (dogRGB >> 12) & 0xF;
                    int dogBlue = (dogRGB >> 4) & 0xF;

                    int encodedRGB = (catRGB & 0xFFF0FFF0) | ((dogRGB & 0xF0F0F0F0) >>> 4);


                    cat.setRGB(x, y, encodedRGB);

                }
            }

            ImageIO.write(cat, "png", new File("steganographic_cat.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
