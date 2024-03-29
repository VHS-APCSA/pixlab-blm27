/**
 * This class contains class (static) methods
 * that will help you test the Picture class 
 * methods.  Uncomment the methods and the code
 * in the main to test.
 * 
 * @author Barbara Ericson 
 */
public class PictureTester
{
	/** Method to test zeroBlue */
	public static void testZeroBlue()
	{
		Picture beach = new Picture("beach.jpg");
		beach.explore();
	}
	public static void testPrimaries()
	{
		Picture beach = new Picture("temple.jpg");
		beach.explore();
		beach.primaries();
		beach.explore();
	}
	public static void morphTest()
	{
		Picture beach = new Picture("beach.jpg");
		beach.explore();
		Picture mark = new Picture("seagull.jpg");
		mark.explore();
		int num = 12;
		for(int i = 1; i <= num; i++)
		{
			beach.morph(i, num);
			Picture test = new Picture("test" + i + ".jpg");
			test.explore();
		}
	}
	public static void blurTest()
	{
		Picture pic = new Picture("koala.jpg");
		pic.explore();
		pic.blur(5);
		Picture blur = new Picture("blur.jpg");
		blur.explore();
	}
	/** Method to test mirrorVertical */
	public static void testMirrorVertical()
	{
		Picture caterpillar = new Picture("caterpillar.jpg");
		caterpillar.explore();
		caterpillar.mirrorVertical();
		caterpillar.explore();
	}

	/** Method to test mirrorTemple */
	public static void testMirrorTemple()
	{
		Picture temple = new Picture("temple.jpg");
		temple.explore();
		temple.mirrorTemple();
		temple.explore();
	}

	/** Method to test the collage method */
	public static void testCollage()
	{
		Picture canvas = new Picture("640x480.jpg");
		canvas.createCollage();
		canvas.explore();
	}
	  public static void testMyCollage()
	  {
	  	Picture canvas = new Picture("640x480.jpg");
	    canvas.myCollage();
	    canvas.explore();
	  }
	/** Method to test edgeDetection */
	public static void testEdgeDetection()
	{
		Picture swan = new Picture("blue-mark.jpg");
		swan.edgeDetection(5);
		swan.explore();
	}
	public static void testMyEdgeDetection2()
	{
		Picture swan = new Picture("swan.jpg");
		swan.explore();
		swan.myEdgeDetection2(20);
		swan.explore();
	}
	public static void testMyEdgeDetection()
	{
		Picture pic = new Picture("blue-mark.jpg");
		pic.myEdgeDetection(17);
		pic.explore();
		Picture edge = new Picture("edge.jpg");
		edge.explore();
	}
	public static void testKeepOnlyBlue()
	{
		Picture blue = new Picture("beach.jpg");
		blue.explore();
		blue.keepOnlyBlue();
		blue.explore();
	}
	public static void testNegate()
	{
		Picture pic = new Picture("beach.jpg");
		pic.explore();
		pic.negate();
		pic.explore();
	}
	public static void testGrayscale()
	{
		Picture gray = new Picture("beach.jpg");
		gray.explore();
		gray.grayscale();
		gray.explore();
	}
	public static void testFixUnderwater()
	{
		Picture fish = new Picture("water.jpg");
		fish.explore();
		fish.fixUnderwater();
		fish.explore();
	}

	public static void testMirrorVerticalRightToLeft()
	{
		Picture redMotorycle = new Picture("redMotorcycle.jpg");
		redMotorycle.explore();
		redMotorycle.mirrorVerticalRightToLeft();
		redMotorycle.explore();
	}
	public static void testMirrorHorizontal()
	{
		Picture redMotorycle = new Picture("redMotorcycle.jpg");
		redMotorycle.explore();
		redMotorycle.mirrorHorizontal();
		redMotorycle.explore();
	}
	public static void testMirrorHorizontalBotToTop()
	{
		Picture redMotorycle = new Picture("redMotorcycle.jpg");
		redMotorycle.explore();
		redMotorycle.mirrorHorizontalBotToTop();
		redMotorycle.explore();
	}
	public static void testMirrorDiagonal()
	{
		Picture beach = new Picture("beach.jpg");
		beach.explore();
		beach.mirrorDiagonal();
		beach.explore();
	}
	public static void testMirrorArms()
	{
		Picture snowman = new Picture("snowman.jpg");
		snowman.explore();
		snowman.mirrorArms();
		snowman.explore();
	}
	public static void testMirrorGull()
	{
		Picture gull = new Picture("seagull.jpg");
		gull.explore();
		gull.mirrorGull();
		gull.explore();
	}

	/** Main method for testing.  Every class can have a main
	 * method in Java */
	public static void main(String[] args)
	{
		// uncomment a call here to run a test
		// and comment out the ones you don't want
		// to run
		//testMyEdgeDetection();
		//morphTest();
		//		blurTest();
		//testZeroBlue();
		//testPrimaries();
		//				testKeepOnlyBlue();
		//testKeepOnlyRed();
		//testKeepOnlyGreen();
		//		testNegate();
		//		testGrayscale();
		//		testFixUnderwater();
		//testMirrorVertical();
		//testMirrorTemple();
//		testMirrorArms();
//		testMirrorGull();
//		testMirrorDiagonal();s
		//		testMirrorVerticalRightToLeft();
		//		testMirrorHorizontal();
		//		testMirrorHorizontalBotToTop();
//		testCollage();
//		testMyCollage();
		//testCopy();
//		testEdgeDetection();
		testMyEdgeDetection2();
//		testEdgeDetection2();
		//testChromakey();
		//testEncodeAndDecode();
		//testGetCountRedOverValue(250);
		//testSetRedToHalfValueInTopHalf();
		//testClearBlueOverValue(200);
		//testGetAverageForColumn(0);
	}
}