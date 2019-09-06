package com.yuyang.client.util;

import java.awt.Image;
import java.awt.Toolkit;



public class ImageLoader {
	
	static Toolkit TK = Toolkit.getDefaultToolkit();
	
	private ImageLoader(){}

	public static Image loadImageIcon(String _file) {
		return TK.getImage(ImageLoader.class.getClassLoader().getResource("res/images/"+_file));
	}
}
