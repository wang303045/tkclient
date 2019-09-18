package com.yuyang.client.game.map;

public class ObjectAndLocation {
	
	 private int x , y;
	 private int type;
	 
	public ObjectAndLocation(int x, int y, int type) {
		super();
		this.x = x;
		this.y = y;
		this.type = type;
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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	 
	 

}
