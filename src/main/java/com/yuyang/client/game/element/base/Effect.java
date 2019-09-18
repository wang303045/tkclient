package com.yuyang.client.game.element.base;

import com.yuyang.client.game.WarMain;


//游戏显示效果类，不需要移动速度等，不需要检测碰撞，如爆炸效果
public abstract class Effect extends GameObject{

	public Effect(int x, int y, int width, int height, int life,
			boolean visiable, boolean invincible, WarMain war) {
		super(x, y, width, height, life, visiable, invincible, war);
		// TODO Auto-generated constructor stub
	}

}
