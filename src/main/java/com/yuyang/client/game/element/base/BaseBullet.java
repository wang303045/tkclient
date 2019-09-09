package com.yuyang.client.game.element.base;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import com.yuyang.client.game.WarMain;

public class BaseBullet  extends Weapon{
	
	
	protected WarMain war;
	
	private BaseDirection team = null;
	
	public BaseBullet(int x, int y, int size , int speed , BaseDirection moveDir , BaseDirection team ,  WarMain war) {
		this.x = x;
		this.y = y;
		this.size = size;
		this.speed = speed;
		this.moveDir = moveDir;
		this.team = team;
		this.war = war;
	}

	@Override
	public void move() {
		switch (moveDir) {
		case U:
			y = y - speed;
			break;
		case D:
			y = y + speed;
			break;
		case L:
			x = x - speed;
			break;
		case R:
			x = x + speed;
			break;	
		case STOP:
			break;	
		default:
			break;
		}
		
		//判断是否出界
		setIsOut(x , y , size);
		
	}


	@Override
	public void show(Graphics g) {
		if(!isLive){
			this.war.getWeaponlist().remove(this);
			return;
		}
		Color color = g.getColor();
		g.setColor(color.RED);
		g.fillOval(this.x, this.y, this.size, this.size);
		move();
	}

	
	private void setIsOut(int x , int y , int size){
		if(x + size > WarMain.windowW){
			isLive = false;
		}
		if(y + size > WarMain.windowH){
			isLive = false;
		}
		if(x <0){
			isLive = false;
		}
		if(y <0){
			isLive = false;
		}
	}
	
	
	@Override
	public boolean hit(Element element){
		if(!super.hit(element)){
			return false;
		}
		BaseTank tank = null;
		if(element instanceof BaseTank){
			tank = (BaseTank)element;
		}else{
			return true;
		}
		
		if(this.team == tank.team){
			return false;
		}else{
			return true;
		} 
	}
	
	@Override
	public Rectangle getRectangle(){
		return new Rectangle(this.x , this.y , this.size , this.size);
	}

	@Override
	protected void changeDir() {
		// TODO Auto-generated method stub
		
	}

	
}
