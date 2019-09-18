package com.yuyang.client.game;

public class MainPaintThread implements Runnable{
	
	private WarMain warMain = null;
	

	public MainPaintThread(WarMain warMain) {
		this.warMain = warMain;
	}


	@Override
	public void run() {
		while(true){
			warMain.repaint();
			try {
				Thread.sleep(48);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
