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

            int counter = 0;

            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int stgImgRGB = stgImg.getRGB(x, y);

                    String stgBinary = Integer.toBinaryString(stgImgRGB);
                    String decodedString = stgBinary.substring(0, 8)+stgBinary.substring(12, 16)+"0010"+stgBinary.substring(20, 24)+"0100"+stgBinary.substring(28, 32)+"0100";

//                    int decodedRed = stgImgRGB & 0xF;
//                    int decodedGreen = (stgImgRGB >> 4) & 0xF;
//                    int decodedBlue = (stgImgRGB >> 8) & 0xF;
//
//                    decodedRed <<= 4;
//                    decodedGreen <<= 4;
//                    decodedBlue <<= 4;
//
//                    int decodedRGB = (255 << 24) | (decodedRed << 16) | (decodedGreen << 8) | decodedBlue;

                    if(counter==1){
                        System.out.println("stg RGB " + Integer.toBinaryString(stgImgRGB));
//                        System.out.println(Integer.toBinaryString(stgImgRGB).length());
                        System.out.println("decoded RGB " + decodedString);
//                        System.out.println(decodedString.length());
//                        System.out.println(decodedBlue);
//                        System.out.println(decodedRed);
//                        System.out.println(decodedGreen);
                    }

                    decodedImage.setRGB(x, y, Integer.parseUnsignedInt(decodedString, 2));
                    int decodedRGB = decodedImage.getRGB(x, y);
                    if(counter==1){
                        System.out.println(Integer.toBinaryString(decodedRGB));
                    }
                    counter++;
                }
            }
            System.out.println(counter);
            ImageIO.write(decodedImage, "png", new File("decodedImage.png"));

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
