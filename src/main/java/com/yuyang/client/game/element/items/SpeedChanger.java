package com.yuyang.client.game.element.items;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import com.yuyang.client.game.WarMain;
import com.yuyang.client.game.element.base.BaseDirection;
import com.yuyang.client.game.element.base.GameObject;
import com.yuyang.client.game.element.base.MoveObject;

public class SpeedChanger extends MoveObject{
	
	
	public SpeedChanger(int x, int y, int width, int height, int life,
			boolean visiable, boolean invincible, WarMain war, int speed,
			BaseDirection moveDir, boolean moving) {
		super(x, y, width, height, life, visiable, invincible, war, speed, moveDir,
				moving);
		// TODO Auto-generated constructor stub
	}
	

	@Override
	public void show(Graphics g) {
		if(!isAlive()) return;
		
		g.setColor(Color.RED);
		g.fillRect(x, y, width, height);
	}


	@Override
	protected void onCollide(GameObject gameObject) {
		// TODO Auto-generated method stub
		
	}



	@Override
	protected Image[] getMoveImages() {
		// TODO Auto-generated method stub
		return null;
	}



}
