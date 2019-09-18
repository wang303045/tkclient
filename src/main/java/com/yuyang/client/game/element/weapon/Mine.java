package com.yuyang.client.game.element.weapon;

import java.awt.Color;
import java.awt.Graphics;

import com.yuyang.client.game.WarMain;
import com.yuyang.client.game.element.base.BaseBullet;
import com.yuyang.client.game.element.base.BaseDirection;

public class Mine extends BaseBullet{

	public Mine(int x, int y, int width, int height, int life,
			boolean visiable, boolean invincible, WarMain war, int speed,
			BaseDirection moveDir, boolean moving, BaseDirection team) {
		super(x, y, width, height, life, visiable, invincible, war, speed, moveDir,
				moving, team);
		// TODO Auto-generated constructor stub
	}
	
	
//	public Mine(int x, int y, int size, BaseDirection moveDir,
//			BaseDirection team, WarMain war) {
//		super(x, y, size, 0, moveDir, team, war);
//		// TODO Auto-generated constructor stub
//	}
//
//
//	@Override
//	public void show(Graphics g) {
//		if(!isLive){
//			war.getWeaponlist().remove(this);
//			return;
//		}
//		Color color = g.getColor();
//		g.setColor(color.RED);
//		g.fillOval(this.x, this.y, this.size, this.size);
//	}

}
