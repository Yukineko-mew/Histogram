/**
 * 
 */
package main;

import java.awt.Component;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

/**
 * @author b1012213
 *
 */
public class LoadImage {
	private static BufferedImage grayScale = null;
	private static double [] histogram;
	
	public LoadImage( File file ) {
		try {
			BufferedImage loadImage = ImageIO.read(file);
			grayScale = new BufferedImage(loadImage.getWidth(), loadImage.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
			grayScale.getGraphics().drawImage(loadImage, 0, 0, null );
			histogram = new double[256];
			for( int i=0; i<histogram.length; i++ ) {
				histogram[i] = 0;
			}
			
			createHistogramData();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			grayScale = null;
			e.printStackTrace();
		}
	}
	
	private void createHistogramData() {
		int width = grayScale.getWidth();
		int height = grayScale.getHeight();
		System.out.println("width=" + width + " height=" + height );
		
		for(int h=0; h<height; h++) {
			for(int w=0; w<width; w++) {
				int hist = ImageRGBUtilitiy.r(grayScale.getRGB(w, h));
				histogram[hist]++;
//				System.out.println(histogram[w][h]);
//				System.out.println("R="+ ImageRGBUtilitiy.r(histogram[w][h]) +", G="+ ImageRGBUtilitiy.g(histogram[w][h]) +", B="+ ImageRGBUtilitiy.b(histogram[w][h]));
			}
		}
	}
	
	public static BufferedImage getImage() {
		return grayScale;
	}
	
	public static double[] getHistogramData() {
		return histogram;
	}
}
