import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.text.*;
import java.util.*;
import java.util.List; // resolves problem with java.awt.List and java.util.List

/**
 * A class that represents a picture.  This class inherits from 
 * SimplePicture and allows the student to add functionality to
 * the Picture class.  
 * 
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
public class Picture extends SimplePicture 
{
    ///////////////////// constructors //////////////////////////////////

    /**
     * Constructor that takes no arguments 
     */
    public Picture ()
    {
        /* not needed but use it to show students the implicit call to super()
         * child constructors always call a parent constructor 
         */
        super();  
    }

    /**
     * Constructor that takes a file name and creates the picture 
     * @param fileName the name of the file to create the picture from
     */
    public Picture(String fileName)
    {
        // let the parent class handle this fileName
        super(fileName);
    }

    /**
     * Constructor that takes the width and height
     * @param height the height of the desired picture
     * @param width the width of the desired picture
     */
    public Picture(int height, int width)
    {
        // let the parent class handle this width and height
        super(width,height);
    }

    /**
     * Constructor that takes a picture and creates a 
     * copy of that picture
     * @param copyPicture the picture to copy
     */
    public Picture(Picture copyPicture)
    {
        // let the parent class do the copy
        super(copyPicture);
    }

    /**
     * Constructor that takes a buffered image
     * @param image the buffered image to use
     */
    public Picture(BufferedImage image)
    {
        super(image);
    }

    ////////////////////// methods ///////////////////////////////////////

    /**
     * Method to return a string with information about this picture.
     * @return a string with information about the picture such as fileName,
     * height and width.
     */
    public String toString()
    {
        String output = "Picture, filename " + getFileName() + 
            " height " + getHeight() 
            + " width " + getWidth();
        return output;

    }

    /** Method to set the blue to 0 */
    public void zeroBlue()
    {
        Pixel[][] pixels = this.getPixels2D();
        for (Pixel[] rowArray : pixels)
        {
            for (Pixel pixelObj : rowArray)
            {
                pixelObj.setBlue(0);
            }
        }
    }
    public void keepOnlyBlue()
    {
    	Pixel[][] pixels = this.getPixels2D();
    	for(Pixel [] rowArray : pixels)
    	{
    		for(Pixel pixelObj : rowArray)
    		{
    			pixelObj.setRed(0);
    			pixelObj.setGreen(0);
    		}
    	}
    }
    public void primaries()
    {
        Pixel[][] pixels = this.getPixels2D();
        int n = 2;
        for(Pixel[] rowArray : pixels)
        {
            for(Pixel pixelObj : rowArray)
            {
                int blue = pixelObj.getBlue();
                int green = pixelObj.getGreen();
                int red = pixelObj.getRed();
                if(blue < 50 && red < 50 && green < 50)
                {
                    blue = 0;
                    red = 0;
                    green = 0;
                }
                else if(blue > green && blue > red)
                {
                    blue *= n;
                    red = 0;
                    green = 0;
                }
                else if (green > blue && green > red)
                {
                    blue = 0;
                    red = 0;
                    green *= n;
                }
                else
                {
                    blue = 0;
                    red *= n;
                    green = 0;
                }
                pixelObj.setBlue(blue);
                pixelObj.setRed(red);
                pixelObj.setGreen(green);
            }
        }
    }
    /** Method that mirrors the picture around a 
     * vertical mirror in the center of the picture
     * from left to right */
    public void mirrorVertical()
    {
        Pixel[][] pixels = this.getPixels2D();
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        int width = pixels[0].length;
        for (int row = 0; row < pixels.length; row++)
        {
            for (int col = 0; col < width / 2; col++)
            {
                leftPixel = pixels[row][col];
                rightPixel = pixels[row][width - 1 - col];
                rightPixel.setColor(leftPixel.getColor());
            }
        } 
    }

    /** Mirror just part of a picture of a temple */
    public void mirrorTemple()
    {
        int mirrorPoint = 276;
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        int count = 0;
        Pixel[][] pixels = this.getPixels2D();

        // loop through the rows
        for (int row = 27; row < 97; row++)
        {
            // loop from 13 to just before the mirror point
            for (int col = 13; col < mirrorPoint; col++)
            {

                leftPixel = pixels[row][col];      
                rightPixel = pixels[row]                       
                [mirrorPoint - col + mirrorPoint];
                rightPixel.setColor(leftPixel.getColor());
            }
        }
    }

    /** copy from the passed fromPic to the
     * specified startRow and startCol in the
     * current picture
     * @param fromPic the picture to copy from
     * @param startRow the start row to copy to
     * @param startCol the start col to copy to
     */
    public void copy(Picture fromPic, 
    int startRow, int startCol)
    {
        Pixel fromPixel = null;
        Pixel toPixel = null;
        Pixel[][] toPixels = this.getPixels2D();
        Pixel[][] fromPixels = fromPic.getPixels2D();
        for (int fromRow = 0, toRow = startRow; 
        fromRow < fromPixels.length &&
        toRow < toPixels.length; 
        fromRow++, toRow++)
        {
            for (int fromCol = 0, toCol = startCol; 
            fromCol < fromPixels[0].length &&
            toCol < toPixels[0].length;  
            fromCol++, toCol++)
            {
                fromPixel = fromPixels[fromRow][fromCol];
                toPixel = toPixels[toRow][toCol];
                toPixel.setColor(fromPixel.getColor());
            }
        }   
    }

    /** Method to create a collage of several pictures */
    public void createCollage()
    {
        Picture flower1 = new Picture("flower1.jpg");
        Picture flower2 = new Picture("flower2.jpg");
        this.copy(flower1,0,0);
        this.copy(flower2,100,0);
        this.copy(flower1,200,0);
        Picture flowerNoBlue = new Picture(flower2);
        flowerNoBlue.zeroBlue();
        this.copy(flowerNoBlue,300,0);
        this.copy(flower1,400,0);
        this.copy(flower2,500,0);
        this.mirrorVertical();
        this.write("collage.jpg");
    }

    /** Blur */
    public void blur(int val)
    {
        Picture from = this;
        Picture to = new Picture(from.getHeight(), from.getWidth());
        Pixel[][] fromPixels = from.getPixels2D();
        Pixel[][] toPixels = to.getPixels2D();
        for(int row = 0; row < fromPixels.length; row++)
        {
            for(int col = 0; col < fromPixels[0].length; col++)
            {
                int sumR = 0, sumB = 0, sumG = 0;
                int count = 0;
                for(int i = -val; i <= val; i++)
                {
                    for(int j = -val; j <= val; j++)
                    {
                        if(( (row + i) >= 0 && (row + i) < fromPixels.length ) && ((col + j) >= 0 && (col + j) < fromPixels[0].length ))
                        {
                            sumR += fromPixels[row + i][col + j].getRed();
                            sumG += fromPixels[row + i][col + j].getGreen();
                            sumB += fromPixels[row + i][col + j].getBlue();
                            count++;
                        }
                    }
                }
                toPixels[row][col].updatePicture(0, sumR / count, sumG / count, sumB / count); 
            }
        }
        to.write("blur.jpg");
    }

    /** Method to show large changes in color 
     * @param edgeDist the distance for finding edges
     */
    public void edgeDetection(int edgeDist)
    {
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        Pixel[][] pixels = this.getPixels2D();
        Color rightColor = null;
        for (int row = 0; row < pixels.length; row++)
        {
            for (int col = 0; 
            col < pixels[0].length-1; col++)
            {
                leftPixel = pixels[row][col];
                rightPixel = pixels[row][col+1];
                rightColor = rightPixel.getColor();
                if (leftPixel.colorDistance(rightColor) > 
                edgeDist)
                    leftPixel.setColor(Color.BLACK);
                else
                    leftPixel.setColor(Color.WHITE);
            }
        }
    }

    public void myEdgeDetection(int edgeDist)
    {
        Pixel left = null;
        Pixel right = null;
        Pixel[][] pixels = this.getPixels2D();
        Picture to = new Picture(this.getHeight(), this.getWidth());
        Pixel[][] toPixels = to.getPixels2D();
        for (int row = 0; row < pixels.length; row++)
        {
            for (int col = 0; col < pixels[0].length-1; col++)
            {
                left = pixels[row][col];
                right = pixels[row][col+1];
                if(Math.abs(left.getRed() - right.getBlue()) > edgeDist 
                || Math.abs(left.getGreen() - right.getBlue()) > edgeDist)
                    toPixels[row][col].setColor(Color.BLACK);
                else
                    toPixels[row][col].setColor(Color.WHITE);
            }
        }
        for(int row = 0; row < toPixels.length; row++)
        {
            for(int col = 0; col < toPixels[0].length; col++)
            {
                
            }
        }
        to.write("edge.jpg");
    }

    public void morph(int i, int total)
    {
        Picture from = new Picture("beach.jpg");
        Picture to = new Picture("seagull.jpg");
        Picture middle = new Picture(480, 640);
        Pixel[][] fromPixels = from.getPixels2D();
        Pixel[][] toPixels = to.getPixels2D();
        Pixel[][] interpolate = middle.getPixels2D();
        for(int row = 0; row < fromPixels.length; row++)
        {
            for(int col = 0; col < fromPixels[0].length; col++)
            {
                int fr = fromPixels[row][col].getRed();
                int fb = fromPixels[row][col].getBlue();
                int fg = fromPixels[row][col].getGreen();
                int tr = toPixels[row][col].getRed();
                int tb = toPixels[row][col].getBlue();
                int tg = toPixels[row][col].getGreen();
                interpolate[row][col].setRed(fr + i * (tr - fr) / total);
                interpolate[row][col].setBlue(fb + i * (tb - fb) / total);
                interpolate[row][col].setGreen(fg + i * (tg - fg) / total);
            }
        }
        middle.write("test" + i + ".jpg");
    }

    /* Main method for testing - each class in Java can have a main 
     * method 
     */
    public static void main(String[] args) 
    {
        Picture beach = new Picture("KatieFancy.jpg");
        beach.explore();
        beach.keepOnlyBlue();
        beach.explore();
    }

} // this } is the end of class Picture, put all new methods before this
