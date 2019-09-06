package com.yuyang.client.game.tools;

import java.awt.Image;
import java.util.HashMap;
import java.util.Map;

import com.yuyang.client.util.ImageLoader;



public class ImagesMap {
	
	private ImagesMap(){}
	
	
	private static ImagesMap instance = new ImagesMap();
	
	//image resource
	public static Image[] background = null;
	public static Image[] tankImages = null;
	private static Map<String, Image> imgs = new HashMap<String, Image>();
	
	static{
		//tank图片
				tankImages = new Image[] {
						ImageLoader.loadImageIcon("tank2_l.png"),
						
						ImageLoader.loadImageIcon("tank2_u.png"),
						
						ImageLoader.loadImageIcon("tank2_r.png"),
					
						ImageLoader.loadImageIcon("tank2_d.png"),
						
						ImageLoader.loadImageIcon("tank4_l.png"),
						
						ImageLoader.loadImageIcon("tank4_u.png"),
						
						ImageLoader.loadImageIcon("tank4_r.png"),
					
						ImageLoader.loadImageIcon("tank4_d.png")
				};
				
				//其他图片
				
				
				//图片映射图片识别key
				imgs.put("tankL", tankImages[0]);
				imgs.put("tankU", tankImages[1]);
				imgs.put("tankR", tankImages[2]);
				imgs.put("tankD", tankImages[3]);
				
				imgs.put("etankL", tankImages[4]);
				imgs.put("etankU", tankImages[5]);
				imgs.put("etankR", tankImages[6]);
				imgs.put("etankD", tankImages[7]);
	}
	
	public static ImagesMap getInstance(){
		return instance;
	}

	public static Map<String, Image> getImgs() {
		return imgs;
	}

	
}
