package com.yuyang.client.game.element.base;

import java.awt.Graphics;
import java.awt.Image;

import com.yuyang.client.game.WarMain;
import com.yuyang.client.game.tools.IdCreater;

public abstract class GameObject implements Element{

	protected int id;
	protected int x = 0;
	protected int y = 0;
	protected int width = 0;
	protected int height = 0;
	protected int life;
	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	protected boolean visiable = false;
	protected boolean invincible;
	
	protected WarMain war;
	
	public GameObject(int x, int y, int width, int height,
			int life, boolean visiable, boolean invincible, WarMain war, int id) {
		this.id = id;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.life = life;
		this.visiable = visiable;
		this.invincible = invincible;
		this.war = war;
	}
	
	public GameObject(int x, int y, int width, int height,
			int life, boolean visiable, boolean invincible, WarMain war) {
		this.id = IdCreater.getInstance().getId(null);
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.life = life;
		this.visiable = visiable;
		this.invincible = invincible;
		this.war = war;
	}
	
	public abstract Image getImage();
	
	@Override
	public void show(Graphics g) {
		g.drawImage(getImage(), x, y, null);
	}
	
	@Override
	public void doAction() {
		if (!isAlive()) {
			throw new IllegalStateException(
					"when doAction, gameObject shouldn't be died ===> " + this);
		}
	}
	
	@Override
	public boolean equals(Object obj) {
		return ((GameObject) obj).id == id;
	}

	public boolean isAlive() {
		if(life>0) return true;
		return false;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	//调用onDie方法之前最好需要判断是否还活着
	public void onDie(GameObject collidedGameObj) {
		war.removeObject(this);    //从游戏所有物体map中移除这个object
	}
	

}
