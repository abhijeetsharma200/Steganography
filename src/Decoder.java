import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class Decoder {
    public static void main(String[] args) throws IOException{
        try {
            String steganographicImgPath = "steganographic_cat.png";

            int width = 902;
            int height = 672;

            BufferedImage stgImg = ImageIO.read(new File(steganographicImgPath));
            BufferedImage decodedImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);

            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int stgImgRGB = stgImg.getRGB(x, y);

                    int decodedRed = stgImgRGB & 0xF;
                    int decodedGreen = (stgImgRGB >> 4) & 0xF;
                    int decodedBlue = (stgImgRGB >> 8) & 0xF;

                    decodedRed <<= 4;
                    decodedGreen <<= 4;
                    decodedBlue <<= 4;

                    int decodedRGB = (255 << 24) | (decodedRed << 16) | (decodedGreen << 8) | decodedBlue;


                    decodedImage.setRGB(x, y, decodedRGB);
                }
            }

            ImageIO.write(decodedImage, "png", new File("decodedImage.png"));

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
