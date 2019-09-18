package com.yuyang.client.game.element.base;

public enum BaseDirection {
	U(0) , L(2), D(1) , R(3) , STOP(-1) ,
	RED(254) , BLUE(253);
	
	private int value;
	
	private BaseDirection(int value) {
		this.value = value;
	}

	public int toValue() {
		return value;
	}
	
}
