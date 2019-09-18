package com.yuyang.client.game.element.base;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import com.yuyang.client.game.WarMain;

public class BaseWall extends GameObject{
	
	

	public BaseWall(int x, int y, int width, int height,
			int life, boolean visiable, boolean invincible, WarMain war) {
		super(x, y, width, height, life, visiable, invincible, war);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void show(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(x, y, width, height);
	}

	@Override
	public Image getImage() {
		// TODO Auto-generated method stub
		return null;
	}


}
