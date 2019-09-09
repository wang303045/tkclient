package com.yuyang.client.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.Timer;

import com.yuyang.client.game.element.base.BaseTank;
import com.yuyang.client.game.element.base.BaseWall;
import com.yuyang.client.game.element.base.Element;
import com.yuyang.client.game.element.base.Weapon;
import com.yuyang.client.game.element.items.SpeedChanger;
import com.yuyang.client.game.element.manager.EnemyMoveManager;
import com.yuyang.client.game.element.tank.EnemyTank;
import com.yuyang.client.game.tools.ImagesMap;


public class WarMain extends JFrame{
	
	private static Image cacheImage = null;        //缓存图片主要内容在这个上面画，画完之后调用系统paint把这个图片画到屏幕上
	private static Graphics cacheGraphics = null;  //缓存图片的画笔，先用这个画笔把所有图片渲染好，只有缓存图片画好后，才画到屏幕上
	
	private boolean isLoaded = false;
	
	//screen size etc...
	public static final int windowW = 1000;
	public static final int windowH = 720;
	public static final int windowX = 100;
	public static final int windowY = 5;
	
	
	//游戏状态
	public static final int GAME_OVERED = -1;
	public static final int GAME_PUSHED = 0;
	public static final int GAME_BARRIER_SELECT = 1;
	public static final int GAME_MAP_SELECT = 2;
	public static final int GAME_RUNING = 3;
	public static final int GAME_FAILED = 4;
	public static final int GAME_SUCCEED = 5;
	public static final int GAME_LOAD = 6;
	
	private int state = GAME_LOAD;
	
	//backgroud
	public static Image[] background = null;
	
	//main images
	private static Map<String, Image> imgs = new HashMap<String, Image>();
	
	//element
	private List<Element> elementList = null;
	private BaseTank tank;
	private List<Weapon> weaponlist = null; 
	private List<BaseTank> etankList = null;
	private EnemyMoveManager enemyMoveManager = null;
	private List<BaseWall> wallList = null;
	private SpeedChanger sc = null;
	
	
	public List<Weapon> getWeaponlist() {
		return weaponlist;
	}


	public void setWeaponlist(List<Weapon> weaponlist) {
		this.weaponlist = weaponlist;
	}


	//构造方法
	public WarMain() throws HeadlessException {
		setLocation(windowX, windowY);
		setSize(windowW, windowH);
		setTitle("TankWar");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		getContentPane().setBackground(Color.BLACK);
		setVisible(true);
	}

	
	public void startGame() {
		loadResource();
		new Timer(48, new RepaintListener()).start();
	}

	//加载资源loadResource 图片 ， load完之后就 start ==》 start里面主要是画出所有的游戏资源，然后开启一个线程不断重画, 添加事件监听，主要是键盘监听
	public void loadResource(){
		try {
			//添加事件监听，主要是键盘监听
			addListener();
			//加载图片
			ImagesMap images = ImagesMap.getInstance();
			//创建游戏内容
			createElement();
			
			Thread.sleep(1000);
			
			state = GAME_RUNING;
		} catch (Exception e) {
			//将来记录打出log
			e.printStackTrace();
		}
		
	}

	
	/**
	 *  ****** 这里综合起来，可以做成地图配置类，不同的载入元素就是不同的地图 **********
	 * ***/
	//预加载游戏需要的元素
	private void createElement(){
		//所有元素list
		elementList = new LinkedList<Element>();
		
		//加入我的tank
		tank = new BaseTank(10, 10 , 2 , true , true , this);
		
		//加入电脑的tank
		etankList = new LinkedList<BaseTank>();
		for (int i = 1; i < 8; i++) {
//			e.add(new EnemyTank(10+i*150, 10 , 2 , true , false , this));
			etankList.add(new EnemyTank(10+i*120, 10 , 2 , true , false , this));
		}
		
		//加子弹list
		weaponlist = new LinkedList<Weapon>();
		
		//加2堵墙
		wallList = new ArrayList<BaseWall>();
		wallList.add(new BaseWall(200, 200, 200, 20));
		wallList.add(new BaseWall(600, 500, 200, 20));
		
		//单人游戏随意移动tank管理类
		enemyMoveManager = new EnemyMoveManager(etankList);
		
		//加入速度道具
		sc = new SpeedChanger(30, 300, 0, 20, true, true);
		
		//这里最终全部加入一个element的list中，然后在runingDraw中依次画出来
		elementList.add(tank);
		elementList.addAll(etankList);
		elementList.addAll(weaponlist);
		elementList.addAll(wallList);
		elementList.add(sc);
	}
	

	/**
	 * 	******************画出游戏**********************
	 *                大部分都在这里改 
	 * */
	public void runningDraw(Graphics g) {
		Font f = new Font("Arial", Font.BOLD, 10);
		g.setColor(Color.WHITE);
		g.setFont(f);
		g.drawString("weaponlist:" + weaponlist.size() , 10, 200);
		
		//可以使用 一个 Element List 一次性把所有元素都画出来
		for (Element e : elementList) {
			e.show(g);
		}
		//画墙
//		wall.show(g);
//		
//		//画坦克
//		tank.show(g);
//		
//		//画敌人tank
//		for (int i = 0; i < etankList.size(); i++) {
//			etankList.get(i).show(g);
//		}
		
		
		//*****  下面是碰撞检测 需要单独做一个 handler类来处理 ******
		//撞墙
		for (BaseWall wall : wallList) {
			tank.collide(wall);
		}
		//所有tank碰撞检测
		for (int i = 0; i < etankList.size(); i++) {
			for (BaseWall wall : wallList) {
				etankList.get(i).collide(wall);//碰撞墙
			}
			tank.collide(etankList.get(i));
			etankList.get(i).collide(tank);
			for (int j = 0; j < etankList.size(); j++) {
				etankList.get(i).collide(etankList.get(j));
			}
		}
		
		//子弹碰撞检测
		for (int i = 0; i < weaponlist.size(); i++) {
			Weapon weapon = weaponlist.get(i);
			weapon.show(g);
			if(weapon.hit(tank)){
				if(tank.isIslive()){
					tank.setLife(tank.getLife()-2);
					if(tank.getLife() <= 0){
						tank.setIslive(false);
					}
					weapon.setLive(false);
					weaponlist.remove(weapon);
				}
			}
			for (int j = 0; j < etankList.size(); j++) {
				if(weapon.hit(etankList.get(j))){
					BaseTank baseTank = etankList.get(j);
					if(baseTank.isIslive()){
						baseTank.setLife(baseTank.getLife()-2);
						if(baseTank.getLife() <= 0){
							baseTank.setIslive(false);
							etankList.remove(baseTank);
						}
						weapon.setLive(false);
						weaponlist.remove(weapon);
					}
//					weapon.setLive(false);
//					weaponlist.remove(weapon);
				}
			}
		}
		
		//碰撞速度道具
		tank.collide(sc);
	}
	
	//画游戏的主方法，根据不同状态画不同画面
	public void showGame(Graphics g) {
		
		switch ( state ) 
		{
			case GAME_BARRIER_SELECT:   break;
			case GAME_MAP_SELECT:		break;
			case GAME_RUNING: 			runningDraw(g); break;
			case GAME_FAILED:			break;
			case GAME_SUCCEED:          break;
			case GAME_LOAD:             loading(g); break;
		}
		
	}
	
	
	public void loading(Graphics g){
		String str = "Loading...";
		Font f = new Font("Arial", Font.BOLD, 50);
		g.setColor(Color.WHITE);
		g.setFont(f);
		g.drawString(str,
				(getWidth() - this.getFontMetrics(f).stringWidth(str)) / 2,
				getHeight() / 2);
	}
	
	
	//碰撞检测
	public void hitCheck() {
		// TODO Auto-generated method stub

	}
	
	
	private void addListener() {
		//键盘事件
		this.addKeyListener(new TankKeyListener());
	}
	
	//双缓存除闪烁重写paint方法
	@Override
	public void paint(Graphics g) {
		
			   // 截取窗体所在位置的图片  
		       if (cacheImage == null){
		    	   cacheImage = this.createImage(this.getWidth(), this.getHeight());
		       }
		       
				// 获得截取图片的画布
				cacheGraphics = cacheImage.getGraphics();
		        // 获取画布的底色并且使用这种颜色填充画布（默认的颜色为黑色） 
				cacheGraphics.setColor(Color.BLACK);
		        // 有清除上一步图像的功能，相当于gImage.clearRect(0, 0, WIDTH, HEIGHT) 
				cacheGraphics.fillRect(0, 0, this.getWidth(), this.getHeight());
				//调用父类的重绘方法，传入的是截取图片上的画布，防止再从最底层来重绘      
				super.paint(cacheGraphics);
				showGame(cacheGraphics); //画游戏画面到cache上
		        //将接下来的图片加载到窗体画布上去，才能考到每次画的效果  
				g.drawImage(cacheImage, 0, 0, null);
	
	}
	
	private class RepaintListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			repaint();
		}
    }
    
	/**
	 * hero tank key listener class . <br /> 
	 */
	private class TankKeyListener extends KeyAdapter 
	{
		@Override
		public void keyPressed(KeyEvent e) {
			tank.keyPressed(e);
			enemyMoveManager.keyPressed(e);
			
		}
		
		@Override
		public void keyReleased(KeyEvent e) {
			tank.keyReleased(e);
			
		}

	}
	
	
	
    public static void main(String[] args) {
    	WarMain war = new WarMain();
    	war.startGame();
	}
}
