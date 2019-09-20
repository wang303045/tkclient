package com.yuyang.client.game.element.base;

import java.awt.Image;

import com.yuyang.client.game.WarMain;
import com.yuyang.client.game.tools.ImagesMap;

public abstract class BaseBullet extends Weapon{
	
	
	protected WarMain war;
	
	private BaseDirection team = null;
	
	
	
	public BaseBullet(int x, int y, int width, int height, int life,
			boolean visiable, boolean invincible, WarMain war, int speed,
			BaseDirection moveDir, boolean moving, BaseDirection team, int power) {
		super(x, y, width, height, life, visiable, invincible, war, speed, moveDir,
				moving, power);
		this.team = team;
	}


	@Override
	protected void onCollide(GameObject gameObject) {
		life = life - 1;
		attack(gameObject);
	}



	@Override
	public void attack(GameObject gameObject) {
		if(gameObject instanceof BaseTank){
			if(isCanAttack(((BaseTank) gameObject))){ //判断是否可以攻击，现在是判断是否一个 team
				life = life - 1;
				int lifeNow = ((BaseTank) gameObject).getLife();
				((BaseTank) gameObject).setLife(lifeNow-power);
			}
		}else{
			if(isInvincible(gameObject)){
				life = life - 1;
			}else{
				int lifeNow =  gameObject.getLife();
				gameObject.setLife(lifeNow-power);
				life = life - 1;
			}
		}
	}


	@Override
	protected Image[] getMoveImages() {
		return ImagesMap.bulletImages;
	}

	
	protected boolean isCanAttack(BaseTank tank){
		if(this.team == tank.team){
			return false;
		}else{
			return true;
		} 
	}
	
	protected boolean isInvincible(GameObject gameObject){
		return gameObject.invincible;
	} 
	
}
