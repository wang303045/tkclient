package com.yuyang.client.game.element.base;


import java.awt.Image;

import com.yuyang.client.game.WarMain;


/**
 * 4个屏幕边缘对象
 * **/
public class BaseScene extends GameObject{

	
	private BaseScene(int x, int y, int width, int height) {
		super(x, y, width, height, 1, false, true, null);
	}

	public static final BaseScene UP = new BaseScene(0, 0, WarMain.windowW, 0);

	public static final BaseScene DOWN = new BaseScene(0,
			WarMain.windowH, WarMain.windowW, 0);

	public static final BaseScene LEFT = new BaseScene(0, 0, 0, WarMain.windowH);

	public static final BaseScene RIGHT = new BaseScene(WarMain.windowW, 0, 0, WarMain.windowH);



	@Override
	public boolean isAlive() {
		return true;
	}

	@Override
	public Image getImage() {
		return null;
	}

}
