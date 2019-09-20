package com.yuyang.client.game.element.weapon;

import com.yuyang.client.game.WarMain;
import com.yuyang.client.game.element.base.BaseBullet;
import com.yuyang.client.game.element.base.BaseDirection;

public class NormalBullet extends BaseBullet{

	public NormalBullet(int x, int y, int width, int height, int life,
			boolean visiable, boolean invincible, WarMain war, int speed,
			BaseDirection moveDir, boolean moving, BaseDirection team, int power) {
		super(x, y, width, height, life, visiable, invincible, war, speed, moveDir,
				moving, team, power);
		// TODO Auto-generated constructor stub
	}

}
