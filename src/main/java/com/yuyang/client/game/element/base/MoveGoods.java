package com.yuyang.client.game.element.base;


public abstract class MoveGoods implements Element{
	
	protected int x = 0;
	protected int y = 0;
	protected int speed = 0;
	protected int size = 0;
	protected boolean visiable = false;
	protected BaseDirection moveDir  = BaseDirection.STOP;
	
	//改变moveDir方向
	protected abstract void changeDir();

	
	//通过dir的方向，改变的运动也就是 x、y的值，真正的运动
	protected abstract void move();


	


}
