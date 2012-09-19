package com.weaselworks.util;

import java.awt.*;
import java.awt.image.*;
import java.io.*;

import javax.imageio.*;

/**
 *
 */

public class ImageUtil
{
	private
	ImageUtil ()
	{
		return; 
	}
	
    /**
     * 
     * @param image
     * @param path
     * @throws IOException
     */
    
    public static
    void writeImage (final BufferedImage image, final File path, final String type)
    	throws IOException
    {
		ImageIO.write (image, type, path); 
		return;
    }
    
    /**
     * 
     * @param path
     * @return
     * @throws IOException
     */
    
    public static
    BufferedImage readImage (final File path) 
    	throws IOException
    {
		final InputStream is = new FileInputStream (path); 
		final BufferedInputStream bis = new BufferedInputStream (is); 
		final BufferedImage bi = ImageIO.read (bis); 
		return bi; 
    }
    
    /**
     * 
     * @param bi
     * @param width
     * @param height
     * @return
     */
    
	public static
	BufferedImage scaleImage (final BufferedImage bi, final int width, final int height)
	{
//		System.err.println ("scaleImage (Image[" + bi.getHeight () + ", " + bi.getWidth () + "], " + width + ", " + height + ")");
		
		final int magicSize = 64; 
		final int cw = bi.getWidth (), ch = bi.getHeight ();
		final int dw = cw - width, dh = ch - height; 
		
		if (dw < magicSize || dh < magicSize) { 
			return scaleImage2 (bi, width, height); 
		} else { 
			final BufferedImage intermediate = scaleImage2 (bi, cw - magicSize, ch - magicSize); 
			return scaleImage (intermediate, width, height); 
		}
		
		// NOT REACHED
	}
	
	/**
	 * 
	 * @param bi
	 * @param width
	 * @param height
	 * @return
	 */

	public static
	BufferedImage scaleImage2 (final BufferedImage bi, final int width, final int height)
	{
//		System.err.println ("scaleImage2 (Image[" + bi.getHeight () + ", " + bi.getWidth () + "], " + width + ", " + height + ")"); 

		final BufferedImage newImage = new BufferedImage (width, height, BufferedImage.TYPE_INT_RGB);
	    final Graphics2D g2d = newImage.createGraphics();
	    
	    g2d.setRenderingHint (RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g2d.setRenderingHint (RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
//	    g2d.setRenderingHint (RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
//	    g2d.setRenderingHint (RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//	    g2d.setRenderingHint (RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
	    
	    g2d.drawImage (bi, 0, 0, width, height, null);
	    g2d.dispose (); 
	    	    
	    return newImage; 
	}

	
	//	public static
//	void main (final String [] args)
//		throws Exception
//	{
//		final String a = "/tmp/a.jpg"; 
//	
//		
//	}
//	
//	public static 
//	InputStream scaleImage (InputStream p_image, int p_width, int p_height) 
//		throws Exception 
//	{
//		 
//	     InputStream imageStream = new BufferedInputStream(p_image);
//	     Image image = (Image) ImageIO.read(imageStream); 
//	 
//	     int thumbWidth = p_width;
//	        int thumbHeight = p_height;        
//	 
//	        // Make sure the aspect ratio is maintained, so the image is not skewed
//	        double thumbRatio = (double)thumbWidth / (double)thumbHeight;
//	        int imageWidth = image.getWidth(null);
//	        int imageHeight = image.getHeight(null);
//	        double imageRatio = (double)imageWidth / (double)imageHeight;
//	        if (thumbRatio < imageRatio) {
//	          thumbHeight = (int)(thumbWidth / imageRatio);
//	        } else {
//	          thumbWidth = (int)(thumbHeight * imageRatio);
//	        }
//	 
//	        // Draw the scaled image
//	        BufferedImage thumbImage = new BufferedImage(thumbWidth, 
//	          thumbHeight, BufferedImage.TYPE_INT_RGB);
//	        Graphics2D graphics2D = thumbImage.createGraphics();
//	        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
//	          RenderingHints.VALUE_INTERPOLATION_BILINEAR);
//	        graphics2D.drawImage(image, 0, 0, thumbWidth, thumbHeight, null);
//	 
//	        // Write the scaled image to the outputstream
//	        ByteArrayOutputStream out = new ByteArrayOutputStream();
//	        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
//	        JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(thumbImage);
//	        int quality = 100; // Use between 1 and 100, with 100 being highest quality
//	        quality = Math.max(0, Math.min(quality, 100));
//	        param.setQuality((float)quality / 100.0f, false);
//	        encoder.setJPEGEncodeParam(param);
//	        encoder.encode(thumbImage);        
//	        ImageIO.write(thumbImage, IMAGE_JPG , out); 
//	 
//	        // Read the outputstream into the inputstream for the return value
//	        ByteArrayInputStream bis = new ByteArrayInputStream(out.toByteArray());        
//	 
//	        return bis;        
//	    }
}


// EOF