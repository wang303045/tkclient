package com.yuyang.client.game.element.base;

import com.yuyang.client.game.WarMain;


public abstract class Weapon extends MoveObject{
	

	public Weapon(int x, int y, int width, int height, int life,
			boolean visiable, boolean invincible, WarMain war, int speed,
			BaseDirection moveDir, boolean moving) {
		super(x, y, width, height, life, visiable, invincible, war, speed, moveDir,
				moving);
		// TODO Auto-generated constructor stub
	}

	public void explode(){}
	
}
