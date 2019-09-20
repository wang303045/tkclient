package com.yuyang.client.game.element.tank;

import java.awt.Image;

import com.yuyang.client.game.WarMain;
import com.yuyang.client.game.element.base.BaseDirection;
import com.yuyang.client.game.element.base.BaseTank;
import com.yuyang.client.game.tools.ImagesMap;

public class EnemyTank extends BaseTank {
	
	public EnemyTank(int x, int y, int width, int height, int life,
			boolean visiable, boolean invincible, WarMain war, int speed,
			BaseDirection moveDir, boolean moving, int id) {
		super(x, y, width, height, life, visiable, invincible, war, speed, moveDir,
				moving, BaseDirection.BLUE, id, 1);
		// TODO Auto-generated constructor stub
	}
	
	public EnemyTank(int x, int y, int width, int height, int life,
			boolean visiable, boolean invincible, WarMain war, int speed,
			BaseDirection moveDir, boolean moving) {
		super(x, y, width, height, life, visiable, invincible, war, speed, moveDir,
				moving, BaseDirection.BLUE);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Image[] getMoveImages() {
		return ImagesMap.enemyTankImages;
	}

//	
//	@Override
//	public void show(Graphics g) {
//		if(!isAlive()) return ;
//		
//		if(this.visiable) {
//			switch (moveDir) {
//			case U:
//				g.drawImage(imgs.get("etankU"), x, y, null);
//				faceDir = moveDir;
//				break;
//			case D:
//				g.drawImage(imgs.get("etankD"), x, y, null);
//				faceDir = moveDir;
//				break;
//			case L:
//				g.drawImage(imgs.get("etankL"), x, y, null);
//				faceDir = moveDir;
//				break;
//			case R:
//				g.drawImage(imgs.get("etankR"), x, y, null);
//				faceDir = moveDir;
//				break;	
//			case STOP:
//				showFace(g);
//				break;	
//			default:
//				break;	
//			}
//		}
//		showBlood(g);
//	}

	//开火炮筒的方向 
//	@Override
//	protected void showFace(Graphics g){
//		switch (faceDir) {
//		case U:
//			g.drawImage(imgs.get("etankU"), x, y, null);
//			break;
//		case D:
//			g.drawImage(imgs.get("etankD"), x, y, null);
//			break;
//		case L:
//			g.drawImage(imgs.get("etankL"), x, y, null);
//			break;
//		case R:
//			g.drawImage(imgs.get("etankR"), x, y, null);
//			break;	
//		default:
//			g.drawImage(imgs.get("etankU"), x, y, null);
//			break;	
//		}
//	}

	
//	public void randomAction(int scope){
//			stop();
//			int randomint = (int)(1+Math.random()*(scope-1+1));
//			switch (randomint) {
//			case 1:
//				bU = true;
////				this.war.getWeaponlist().add(fire());
//				break;
//			case 2:
//				bD = true;
////				this.war.getWeaponlist().add(fire());
//				break;
//			case 3:
//				bL = true;
////				this.war.getWeaponlist().add(fire());
//				break;
//			case 4:
//				bR = true;
////				this.war.getWeaponlist().add(fire());
//				break;	
//			case 5:
//				this.war.getWeaponlist().add(fire());
//				break;	
//			default:
//				break;
//			}
//			changeDir();
//	}
//	
//	public void stop(){
//			bU = false;
//			bD = false;
//			bL = false;
//			bR = false;
//			changeDir();
//	}

}
