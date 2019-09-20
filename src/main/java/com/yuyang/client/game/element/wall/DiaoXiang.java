package com.yuyang.client.game.element.wall;

import java.awt.Image;

import com.yuyang.client.game.WarMain;
import com.yuyang.client.game.element.base.BaseWall;
import com.yuyang.client.game.tools.ImagesMap;

public class DiaoXiang extends BaseWall{

	public DiaoXiang(int x, int y, int width, int height, int life,
			boolean visiable, boolean invincible, WarMain war) {
		super(x, y, width, height, life, visiable, invincible, war);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Image getImage() {
		return ImagesMap.wallImages[1];
	}
	
	

}
