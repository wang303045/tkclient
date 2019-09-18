package com.yuyang.client.game.player;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.yuyang.client.game.element.base.BaseDirection;
import com.yuyang.client.game.element.base.BaseTank;
import com.yuyang.client.util.ClientConfig;

public class Player implements MouseListener, KeyListener{
	
	private BaseTank tank;
	
	private int up ;
	private int down ;
	private int left ;
	private int right ;
	private int fire ;
	private int c1 ;
	private int c2 ;
	private int c3 ;
	
	//Integer.parseInt(String s, int radix)
	public Player(BaseTank tank){
		this.tank = tank;
		up = Integer.parseInt(ClientConfig.getConfig("player.key.u"), 16);
		down = Integer.parseInt(ClientConfig.getConfig("player.key.d"), 16);
		left = Integer.parseInt(ClientConfig.getConfig("player.key.l"), 16);
		right = Integer.parseInt(ClientConfig.getConfig("player.key.r"), 16);
		
		fire = Integer.parseInt(ClientConfig.getConfig("player.key.fire"), 16);
		c1 = Integer.parseInt(ClientConfig.getConfig("player.key.c1"), 16);
		c2 = Integer.parseInt(ClientConfig.getConfig("player.key.c2"), 16);
		c3 = Integer.parseInt(ClientConfig.getConfig("player.key.c3"), 16);

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int knum = e.getKeyCode();
		if(knum == up){
			//坦克向上
			this.tank.go(BaseDirection.U);
		}else if(knum == down){
			this.tank.go(BaseDirection.D);
		}else if(knum == left){
			this.tank.go(BaseDirection.L);
		}else if(knum == right){
			this.tank.go(BaseDirection.R);
		}else if(knum == fire){
			tank.fire();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int knum = e.getKeyCode();
		if (knum == up || knum == down || knum == left
				|| knum == right) {
			tank.stop();
		}else if(knum == fire){
			tank.stopFire();
		}else if(knum == c1){
			tank.changeWeapon(1);
		}else if(knum == c2){
			tank.changeWeapon(2);
		}else if(knum == c3){
			tank.changeWeapon(3);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	

	public BaseTank getTank() {
		return tank;
	}

	public void setTank(BaseTank tank) {
		this.tank = tank;
	}
	
}
