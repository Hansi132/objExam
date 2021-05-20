package no.MCH.utils;

import java.net.URL;

import javax.swing.ImageIcon;

import org.apache.log4j.Logger;

public class ImageLoader {
	private static Logger log = Logger.getLogger(ImageLoader.class);
	
	public ImageIcon loadImage(String path, String description) {
		URL imgUrl = getClass().getResource(path);
		if (imgUrl != null) {
			return new ImageIcon(imgUrl, description);
		} else {
			log.error("Could not find file: " + path);
			return null;
		}
	}
}
