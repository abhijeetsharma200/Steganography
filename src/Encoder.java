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
//            int imageType = cat.getType();
            int dogWidth = dog.getWidth();
            int dogHeight = dog.getHeight();

            BufferedImage modified_cat = new BufferedImage(catWidth, catHeight, BufferedImage.TYPE_3BYTE_BGR);

            int count = 0;

            for(int y = 0; y < dogHeight; y++){
                for(int x = 0; x < dogWidth; x++){
                    int catRGB = cat.getRGB(x,y);
                    int dogRGB = dog.getRGB(x, y);

//                    String catBinary = Integer.toBinaryString(catRGB);
//                    String dogBinary = Integer.toBinaryString(dogRGB);



                    int catRed = (catRGB >> 20) & 0xF;
                    int catGreen = (catRGB >> 12) & 0xF;
                    int catBlue = (catRGB >> 4) & 0xF;



                    int dogRed = (dogRGB >> 20) & 0xF;
                    int dogGreen = (dogRGB >> 12) & 0xF;
                    int dogBlue = (dogRGB >> 4) & 0xF;

                    int encodedRed = (dogRGB >> 20) & 0xF;
                    int encodedGreen = (dogRGB >> 12) & 0xF;
                    int encodedBlue = (dogRGB >> 4) & 0xF;

                    int encodedRGB = (catRGB & 0xFFF0FFF0) | ((dogRGB & 0xF0F0F0F0) >>> 4);

//                    if(count==1){
//                        System.out.println("Cat RGB " + Integer.toBinaryString(catRGB));
//                        System.out.println("Dog RGB " + Integer.toBinaryString(dogRGB));
//                        System.out.println("Encoded RGB " + Integer.toBinaryString(encodedRGB));
//                        System.out.println("Cat Red " + Integer.toBinaryString(catRed));
////                        System.out.println("Cat Green " + Integer.toBinaryString(catGreen));
////                        System.out.println("Cat Blue " + Integer.toBinaryString(catBlue));
//                        System.out.println("Dog Red " + Integer.toBinaryString(dogRed));
////                        System.out.println("Dog Green " + Integer.toBinaryString(dogGreen));
////                        System.out.println("Dog Blue " + Integer.toBinaryString(dogBlue));
////                        System.out.println("Original Red: " + Integer.toBinaryString(catRed) + " Original Green: " + Integer.toBinaryString(catGreen) + " Original Blue: " + Integer.toBinaryString(catBlue));
////                        System.out.println("Encoded Red: " + Integer.toBinaryString(encodedRed) + " Encoded Green: " + Integer.toBinaryString(encodedGreen) + " Encoded Blue: " + Integer.toBinaryString(encodedBlue));
//                    }
//                    count++;

//                    int encodedRGB = (catRGB & 0xFFF0FFF0) | ((dogRGB & 0xF0F0F0F0) >>> 4);


                    cat.setRGB(x, y, encodedRGB);

                }
            }

            ImageIO.write(cat, "png", new File("steganographic_cat.png"));

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
