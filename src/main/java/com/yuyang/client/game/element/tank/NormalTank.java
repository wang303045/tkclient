package com.yuyang.client.game.element.tank;

import java.awt.Image;

import com.yuyang.client.game.WarMain;
import com.yuyang.client.game.element.base.BaseDirection;
import com.yuyang.client.game.element.base.BaseTank;
import com.yuyang.client.game.tools.ImagesMap;

public class NormalTank extends BaseTank{

	public NormalTank(int x, int y, int width, int height, int life,
			boolean visiable, boolean invincible, WarMain war, int speed,
			BaseDirection moveDir, boolean moving, BaseDirection team,
			int id) {
		super(x, y, width, height, life, visiable, invincible, war, speed, moveDir,
				moving, team, id);
		// TODO Auto-generated constructor stub
	}
	
	public NormalTank(int x, int y, int width, int height, int life,
			boolean visiable, boolean invincible, WarMain war, int speed,
			BaseDirection moveDir, boolean moving, BaseDirection team) {
		super(x, y, width, height, life, visiable, invincible, war, speed, moveDir,
				moving, team);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Image[] getMoveImages() {
		return ImagesMap.normalTankImages;
	}

	
}
