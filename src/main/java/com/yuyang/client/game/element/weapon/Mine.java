package com.yuyang.client.game.element.weapon;

import java.awt.Color;
import java.awt.Graphics;

import com.yuyang.client.game.WarMain;
import com.yuyang.client.game.element.base.BaseBullet;
import com.yuyang.client.game.element.base.BaseDirection;
import com.yuyang.client.game.element.base.BaseTank;
import com.yuyang.client.game.element.base.GameObject;

public class Mine extends BaseBullet{

	public Mine(int x, int y, int width, int height, int life,
			boolean visiable, boolean invincible, WarMain war, int speed,
			BaseDirection moveDir, boolean moving, BaseDirection team, int power) {
		super(x, y, width, height, life, visiable, invincible, war, speed, moveDir,
				false, team, 5);
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	protected void onCollide(GameObject gameObject) {
		if(isAlive()){
			attack(gameObject);
			life = life - 1;
		}else{
			onDie(this);
		}
		
	}



	@Override
	public void attack(GameObject gameObject) {
		if(gameObject instanceof BaseTank){
//			if(isCanAttack(((BaseTank) gameObject))){ //判断是否可以攻击，现在是判断是否一个 team
			int lifeNow = ((BaseTank) gameObject).getLife();
			((BaseTank) gameObject).setLife(lifeNow-power);
			life = life - 1;
//			}
		}
	}

	
	@Override
	public void show(Graphics g) {
		Color color = g.getColor();
		g.setColor(color.RED);
		g.fillOval(this.x, this.y, 10, 10);
	}

}
