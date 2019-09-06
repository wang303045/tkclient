package com.yuyang.client.game.element.manager;

import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.List;

import com.yuyang.client.game.element.base.BaseTank;
import com.yuyang.client.game.element.tank.EnemyTank;

public class EnemyMoveManager {
	
	private int sleepSecond = 3000;
	
	private int scope = 5;
	
	private boolean isMoveRun = false; 
	
	private volatile boolean isStop = false;
	
	private List<BaseTank> etankList = new LinkedList<BaseTank>();
	
	public EnemyMoveManager(List<BaseTank> etankList){
		this.etankList = etankList;
	}
	
	
	
	public void keyPressed(KeyEvent e) {
		int knum = e.getKeyCode();
		switch (knum) {
		case KeyEvent.VK_1:
			if(!isMoveRun){
				new MoveThread().start();
				isMoveRun = true;
				isStop = false;
			}
			break;
		case KeyEvent.VK_2:
			isStop = true;
			stopAll();
			isMoveRun = false;
			break;
		default:
			break;
		}

	}

	public void moveAll(){
		for (int i = 0; i < this.etankList.size(); i++) {
			((EnemyTank)this.etankList.get(i)).randomAction(scope);
		}
	}
	
	public void stopAll(){
		for (int i = 0; i < this.etankList.size(); i++) {
			((EnemyTank)this.etankList.get(i)).stop();
		}
	}
	
	private class MoveThread extends Thread{

		@Override
		public void run() {
			while(true){
				if(isStop) break;
				moveAll();
				try {
					Thread.sleep(sleepSecond);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
	
}
