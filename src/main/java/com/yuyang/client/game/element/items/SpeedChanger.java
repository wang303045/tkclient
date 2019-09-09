package com.yuyang.client.game.element.items;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.yuyang.client.game.element.base.MoveGoods;

public class SpeedChanger extends MoveGoods{
	
	private boolean isLive = true;
	
	public SpeedChanger(int x, int y, int speed, int size, boolean isLive, boolean visiable){
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.size = size;
		this.isLive = isLive;
		this.visiable = visiable;
	}
	

	
	
	public boolean isLive() {
		return isLive;
	}




	public void setLive(boolean isLive) {
		this.isLive = isLive;
	}




	@Override
	public void show(Graphics g) {
		if(!isLive) return;
		
		g.setColor(Color.RED);
		g.fillRect(x, y, size, size);
	}

	@Override
	public Rectangle getRectangle() {
		return new Rectangle(this.x , this.y , this.size , this.size);
	}

	@Override
	protected void changeDir() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void move() {
		// TODO Auto-generated method stub
		
	}

}
