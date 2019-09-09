package com.yuyang.client.game.element.base;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Timer;

import com.yuyang.client.game.WarMain;
import com.yuyang.client.game.element.items.SpeedChanger;
import com.yuyang.client.game.element.weapon.Mine;
import com.yuyang.client.game.tools.ImagesMap;

public class BaseTank extends MoveGoods{
	
	protected int oldX = 0;
	
	protected int oldY = 0;

	protected WarMain war = null;
	
	protected boolean islive = true;
	
	protected boolean isGood = false;
	
	protected BaseDirection team = BaseDirection.RED;
	
	protected boolean bU = false;

	protected boolean bL = false;

	protected boolean bD = false;

	protected boolean bR=false;
	
	protected int life = 0;
	
	protected int bloodAWidth = 60;
	protected int bloodAHeight = 5;
	
	protected int speedSeconds = 20;
	
	protected Timer speedTime = null;
	
	protected boolean speedFlag = false;
	
	protected BaseDirection faceDir  = BaseDirection.D;
	
	protected static Map<String, Image> imgs = new HashMap<String, Image>();
	
	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}
	
	public boolean isIslive() {
		return islive;
	}

	public void setIslive(boolean islive) {
		this.islive = islive;
	}
	
	
	public BaseTank(int x, int y, int speed , boolean isGood , WarMain war) {
		this.x = x;
		this.y = y;
		this.oldX = x;
		this.oldY = y;
		this.speed = speed;
		this.visiable = true;
		this.imgs = ImagesMap.getInstance().getImgs();
		this.size = 66;
		this.moveDir  = BaseDirection.STOP;
		this.isGood = isGood;
		this.war = war;
	}
	
	public BaseTank(int x, int y, int speed , boolean visiable , boolean isGood , int life, WarMain war) {
		this(x,  y,  speed ,  isGood ,   war);
		this.visiable = visiable;
		this.life = life;
	}
	
	public BaseTank(int x, int y, int speed , boolean visiable , boolean isGood ,  WarMain war) {
		this(x,  y,  speed ,  isGood ,   war);
		this.visiable = visiable;
		this.life = 10;
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
		case KeyEvent.VK_CONTROL:
			this.war.getWeaponlist().add(layMine());
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
		this.oldX = this.x;
		this.oldY = this.y;
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
		showBlood(g);
		if(speedFlag){
//			Font f = new Font("Arial", Font.BOLD, 10);
			g.setFont(new Font("宋体", Font.PLAIN, 12));
			g.setColor(Color.WHITE);
//			g.setFont(f);
			g.drawString("速度加快：" + speedSeconds + "秒", 10, 100);
		}
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
		if(islive){
			return new BaseBullet(this.x + 5, this.y+7, 5 , 60 , this.faceDir , this.team , this.war);
		}else{
			return null;
		}
			
	}
	
	public Weapon layMine() {
		if(islive){
			return new Mine(this.x + 5, this.y+7, 5 , this.faceDir , this.team , this.war);
		}else{
			return null;
		}
			
	}
	
	public void stay(){
		this.x = this.oldX;
		this.y = this.oldY;
	}
	
	//判断撞到tank  ----> 可以改用策略方式 
	//这里需要用控制翻转，把tank的引用传给element，让不同的element来处理对tank产生的不同影响
	//这里还没有写好需要改, 思路：可以写1个或2个大的接口，接口中统一处理碰撞之后的方法 beforeCllide
 	public boolean collide(Element element){
		if(!isCollide(element)){
			return false;
		}
		BaseTank tank = null;
		if(element instanceof BaseTank){
			tank = (BaseTank)element;
			if(this!=tank){
				stay();
				return true;
			}
		}else if(element instanceof BaseWall){
				stay();
				return true;
		}else if(element instanceof SpeedChanger){
			SpeedChanger sc= (SpeedChanger)element ;
			if(sc.isLive()){
				((SpeedChanger) element).setLive(false);
				this.speed = this.speed + 2;
				if(null == speedTime){
					speedSeconds = 10;
					speedTime = new Timer(1000, new SpeedListener()); //持续20秒
					speedTime.start();
				}
				return true;
			}
			return false;
		}else{
			return false;
		}
		return false;
	}
	


	public boolean isCollide(Element element){
		return this.getRectangle().intersects(element.getRectangle());
	}
	
	


	//画血条
	public void showBlood(Graphics g) {
		g.setColor(Color.BLUE);
		g.drawRect(x, y-8, bloodAWidth, bloodAHeight);
		g.setColor(Color.RED);
		int bloodWidth = bloodAWidth * life / 10;
		g.fillRect(x, y-8, bloodWidth, bloodAHeight);
	}
	
	@Override
	public Rectangle getRectangle(){
		return new Rectangle(this.x , this.y , this.size , this.size);
	}
	
	private class SpeedListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(speedSeconds == 0){
				speed = speed - 2;;
				speedFlag = false;
				speedTime.stop();
			}else{
				speedFlag = true;
			}
			speedSeconds--;
		}
    }
	

	
}
