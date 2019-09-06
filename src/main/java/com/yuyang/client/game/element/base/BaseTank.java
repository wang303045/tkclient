package com.yuyang.client.game.element.base;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

import com.yuyang.client.game.WarMain;
import com.yuyang.client.game.element.MoveGoods;
import com.yuyang.client.game.element.Weapon;
import com.yuyang.client.game.tools.ImagesMap;

public class BaseTank extends MoveGoods{

	protected WarMain war = null;
	
	protected boolean islive = true;
	
	protected boolean isGood = false;
	
	protected BaseDirection team = BaseDirection.RED;
	
	protected boolean bU = false;

	protected boolean bL = false;

	protected boolean bD = false;

	protected boolean bR=false;
	
	
	protected BaseDirection faceDir  = BaseDirection.D;
	
	protected static Map<String, Image> imgs = new HashMap<String, Image>();
	
	public boolean isIslive() {
		return islive;
	}

	public void setIslive(boolean islive) {
		this.islive = islive;
	}
	
	
	public BaseTank(int x, int y, int speed , boolean isGood , WarMain war) {
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.visiable = true;
		this.imgs = ImagesMap.getInstance().getImgs();
		this.size = 66;
		this.moveDir  = BaseDirection.STOP;
		this.isGood = isGood;
		this.war = war;
	}
	
	public BaseTank(int x, int y, int speed , boolean visiable , boolean isGood ,  WarMain war) {
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.visiable = visiable;
		this.imgs = ImagesMap.getInstance().getImgs();
		this.size = 66;
		this.moveDir  = BaseDirection.STOP;
		this.isGood = isGood;
		this.war = war;
	}

	//按键改变是否按键的 boolean的值，按键之后改变表示按键的boolean值
	public void keyPressed(KeyEvent e) {
		int knum = e.getKeyCode();
		switch (knum) {
		case KeyEvent.VK_W:
			bU = true;
			break;
		case KeyEvent.VK_S:
			bD = true;
			break;
		case KeyEvent.VK_A:
			bL = true;
			break;
		case KeyEvent.VK_D:
			bR = true;
			break;	
		case KeyEvent.VK_SPACE:
			this.war.getWeaponlist().add(fire());
			break;	
		default:
			break;
		}
		changeDir();
	}

	
	public void keyReleased(KeyEvent e) {
		int knum = e.getKeyCode();
		switch (knum) {
		case KeyEvent.VK_W:
			bU = false;
			break;
		case KeyEvent.VK_S:
			bD = false;
			break;
		case KeyEvent.VK_A:
			bL = false;
			break;
		case KeyEvent.VK_D:
			bR = false;
			break;	
		default:
			break;
		}
		changeDir();
	}
	
	
	//通过dir的方向，改变坦克的运动也就是 x、y的值，真正的运动
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
		if(x + size > WarMain.windowW){
			x = WarMain.windowW - size;
		}
		if(y + size > WarMain.windowH){
			y = WarMain.windowH - size;
		}
		if(x <0){
			x = 0;
		}
		if(y <0){
			y = 0;
		}
	}

	//通过判断boolean值改变moveDir方向
	@Override
	public void changeDir() {
		if(bU){
			moveDir = BaseDirection.U;
		}else if(bL){
			moveDir = BaseDirection.L;
		}else if(bD){
			moveDir = BaseDirection.D;
		}else if(bR){
			moveDir = BaseDirection.R;
		}else{
			moveDir = BaseDirection.STOP;
		}
	}

	//显示tank，主要是通过判断moveDir运动的方向画出4个方向的tank图片,并且给开火方向赋值
	@Override
	public void show(Graphics g) {
		if(!islive) return ;
		
		if(this.visiable) {
			switch (moveDir) {
			case U:
				g.drawImage(imgs.get("tankU"), x, y, null);
				faceDir = moveDir;
				break;
			case D:
				g.drawImage(imgs.get("tankD"), x, y, null);
				faceDir = moveDir;
				break;
			case L:
				g.drawImage(imgs.get("tankL"), x, y, null);
				faceDir = moveDir;
				break;
			case R:
				g.drawImage(imgs.get("tankR"), x, y, null);
				faceDir = moveDir;
				break;	
			case STOP:
				showFace(g);
				break;	
			default:
				break;	
			}
		}
		move();
		
	}

	//开火炮筒的方向 
	protected void showFace(Graphics g){
		switch (faceDir) {
		case U:
			g.drawImage(imgs.get("tankU"), x, y, null);
			break;
		case D:
			g.drawImage(imgs.get("tankD"), x, y, null);
			break;
		case L:
			g.drawImage(imgs.get("tankL"), x, y, null);
			break;
		case R:
			g.drawImage(imgs.get("tankR"), x, y, null);
			break;	
		default:
			g.drawImage(imgs.get("tankU"), x, y, null);
			break;	
		}
	}

	public Weapon fire() {
		return new BaseBullet(this.x + 5, this.y+7, 5 , 60 , this.faceDir , this.team , this.war);
	}
	
	@Override
	public Rectangle getRectangle(){
		return new Rectangle(this.x , this.y , this.size , this.size);
	}
}
