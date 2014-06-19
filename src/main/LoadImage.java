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
	private static BufferedImage loadImage = null;
	int [][] histogram;
	
	public LoadImage( File file ) {
		try {
			loadImage = ImageIO.read(file);
			
			createHistogramData();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			loadImage = null;
			e.printStackTrace();
		}
	}
	
	private void createHistogramData() {
		int width = loadImage.getWidth();
		int height = loadImage.getHeight();
		System.out.println("width=" + width + " height=" + height );
		histogram = new int[width][height];
		
		for(int h=0; h<height; h++) {
			for(int w=0; w<width; w++) {
				histogram[w][h] = loadImage.getRGB(w, h);
			}
		}
		System.out.println(histogram[2][4]);
	}
	
	public static BufferedImage getImage() {
		return loadImage;
	}
	
	
}
