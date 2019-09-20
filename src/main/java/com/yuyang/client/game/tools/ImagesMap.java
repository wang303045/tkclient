package com.yuyang.client.game.tools;

import java.awt.Image;
import java.util.HashMap;
import java.util.Map;

import com.yuyang.client.util.ImageLoader;



public class ImagesMap {
	
	private ImagesMap(){}
	
	
	//image resource
	public static Image[] background = null;
	public static Image[] tankImages = null;
	public static Image[] normalTankImages = null;
	public static Image[] enemyTankImages = null;
	public static Image[] bulletImages = null;
	public static Image[] wallImages = null;
	public static Image[] metalImages = null;
	public static Map<String, Image> imgs = new HashMap<String, Image>();
	
	public static void  initImanges()
	{
		//tank图片
		normalTankImages = new Image[] {
				ImageLoader.loadImageIcon("tank6_u.png"),
				
				ImageLoader.loadImageIcon("tank6_d.png"),
				
				ImageLoader.loadImageIcon("tank6_l.png"),
			
				ImageLoader.loadImageIcon("tank6_r.png")
		};
		
		enemyTankImages = new Image[] {
				ImageLoader.loadImageIcon("tank4_u.png"),
				
				ImageLoader.loadImageIcon("tank4_d.png"),
				
				ImageLoader.loadImageIcon("tank4_l.png"),
			
				ImageLoader.loadImageIcon("tank4_r.png")
		};
		
				//子弹图片
				bulletImages = new Image[] {
						ImageLoader.loadImageIcon("bullet1_u.png"),
						
						ImageLoader.loadImageIcon("bullet1_d.png"),
						
						ImageLoader.loadImageIcon("bullet1_l.png"),
					
						ImageLoader.loadImageIcon("bullet1_r.png")
				};
				//其他图片
				wallImages =  new Image[] {
						ImageLoader.loadImageIcon("w_jizx.png"),
						ImageLoader.loadImageIcon("w_diaos.png")
						
				};
				
				metalImages =  new Image[] {
						ImageLoader.loadImageIcon("back1.png")
				};
				
				
				
				
				//图片映射图片识别key
				imgs.put("bulletL", bulletImages[0]);
				imgs.put("bulletU", bulletImages[1]);
				imgs.put("bulletR", bulletImages[2]);
				imgs.put("bulletD", bulletImages[3]);
				
	}
	

	public static Map<String, Image> getImgs() {
		return imgs;
	}

	
}
