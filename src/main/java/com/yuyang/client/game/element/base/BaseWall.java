package com.yuyang.client.game.element.base;

import java.awt.Image;

import com.yuyang.client.game.WarMain;
import com.yuyang.client.game.tools.ImagesMap;

public class BaseWall extends GameObject{
	
	

	public BaseWall(int x, int y, int width, int height,
			int life, boolean visiable, boolean invincible, WarMain war) {
		super(x, y, width, height, life, visiable, invincible, war);
		// TODO Auto-generated constructor stub
	}


	@Override
	public Image getImage() {
		return ImagesMap.wallImages[0];
	}


}
