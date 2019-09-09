package com.yuyang.client.game.element.base;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class BaseWall implements Element{
	
	protected int x = 0;
	protected int y = 0;
	protected int width = 0;
	protected int heigth = 0;
	

	public BaseWall(int x, int y, int width, int heigth) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.heigth = heigth;
	}

	@Override
	public void show(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(x, y, width, heigth);
	}

	@Override
	public Rectangle getRectangle() {
		return new Rectangle(this.x , this.y , this.width , this.heigth);
	}

}
