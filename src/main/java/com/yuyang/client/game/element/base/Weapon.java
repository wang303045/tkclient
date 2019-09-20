package com.yuyang.client.game.element.base;

import com.yuyang.client.game.WarMain;


public abstract class Weapon extends MoveObject{
	
	protected int power;
	
	public Weapon(int x, int y, int width, int height, int life,
			boolean visiable, boolean invincible, WarMain war, int speed,
			BaseDirection moveDir, boolean moving, int power) {
		super(x, y, width, height, life, visiable, invincible, war, speed, moveDir,
				moving);
		this.power = power;
	}

	public void attack(GameObject gameObject){}
	
	public void explode(){}
	
}
