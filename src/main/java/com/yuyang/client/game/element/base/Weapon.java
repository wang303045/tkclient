package com.yuyang.client.game.element.base;


public abstract class Weapon extends MoveGoods{
	
	protected boolean isLive = true;

	public boolean isLive() {
		return isLive;
	}

	public void setLive(boolean isLive) {
		this.isLive = isLive;
	}

	public void explode(){}
	
	public boolean hit(Element element){
		return this.getRectangle().intersects(element.getRectangle());
	}
	
}
