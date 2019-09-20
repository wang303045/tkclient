package com.yuyang.client.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.Image;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;

import javax.swing.JFrame;

import com.yuyang.client.game.ThreadPool.ThreadManager;
import com.yuyang.client.game.common.Constants;
import com.yuyang.client.game.element.base.BaseTank;
import com.yuyang.client.game.element.base.BaseWall;
import com.yuyang.client.game.element.base.Element;
import com.yuyang.client.game.element.base.GameObject;
import com.yuyang.client.game.element.base.MoveObject;
import com.yuyang.client.game.element.base.Weapon;
import com.yuyang.client.game.element.items.SpeedChanger;
import com.yuyang.client.game.element.manager.EnemyMoveManager;
import com.yuyang.client.game.factory.GameObjectFactory;
import com.yuyang.client.game.map.CollideHandler;
import com.yuyang.client.game.map.GameMapHandler;
import com.yuyang.client.game.player.Player;
import com.yuyang.client.game.tools.ImagesMap;


public class WarMain extends JFrame{
	
	private static Image cacheImage = null;        //缓存图片主要内容在这个上面画，画完之后调用系统paint把这个图片画到屏幕上
	private static Graphics cacheGraphics = null;  //缓存图片的画笔，先用这个画笔把所有图片渲染好，只有缓存图片画好后，才画到屏幕上
	
	//screen size etc...
	public static final int windowW = 1000;
	public static final int windowH = 720;
	public static final int windowX = 100;
	public static final int windowY = 5;
	
	//游戏地图以及游戏物体，包含地图数组，物体map
	private GameMapHandler gameMapHandler = null;
	
	//碰撞检测
	private CollideHandler collideHandler = null;
	
	
	//ThreadPool
	private ExecutorService tpool = null;
	
	public ExecutorService getTpool() {
		return tpool;
	}


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
	private List<GameObject> gameObjectList = null;
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
		tpool.submit(new MainPaintThread(this)); //初始化完成,开始画图
	}

	//加载资源loadResource 图片 ， load完之后就 start ==》 start里面主要是画出所有的游戏资源，然后开启一个线程不断重画, 添加事件监听，主要是键盘监听
	public void loadResource(){
		try {
			//加载图片
			ImagesMap.initImanges();
			
			//创建游戏内容（预加载所有物体） ,并且载入地图
			gameMapHandler = new GameMapHandler(this.windowW, this.windowH, this);
			
			//创建碰撞检测类
			collideHandler = new CollideHandler(gameMapHandler, windowW, windowH);
			
			//添加游戏玩家，主要是添加事件监听，主要是键盘监听
			addPlayer();
			
			//初始化线程池
			tpool = ThreadManager.getInstance().getPool();
			
			Thread.sleep(1000);
			
			state = GAME_RUNING;
		} catch (Exception e) {
			//将来记录打出log
			e.printStackTrace();
		}
		
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
	

	/**
	 * 	******************画出游戏**********************
	 *                大部分都在这里改 
	 * */
	public void runningDraw(Graphics g) {
		Font f = new Font("Arial", Font.BOLD, 10);
		g.setColor(Color.WHITE);
		g.setFont(f);
		g.drawString("object member count:" + gameMapHandler.getObjectMap().size() , 10, 200);
		
		g.drawImage(ImagesMap.metalImages[0], 0, 0, 1000, 720, null);
		
		//从地图物体大管家中取得所有游戏物体，调用他们的doAction和show方法
		Iterator<Map.Entry<Integer, GameObject>> entries = this.gameMapHandler.getObjectMap().entrySet().iterator();
		while (entries.hasNext()) { 
				Map.Entry<Integer, GameObject> entry = entries.next();
				GameObject o = (GameObject)entry.getValue();
				o.doAction();
				o.show(g);   //是否要在这个方法里面判断一下死了的物体就不要画了，或者在子类中实现需不需要画
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
	
	
	private void addPlayer() {
		//加入我的tank
		tank = (BaseTank) GameObjectFactory.newGameObj(30, 30, Constants.TYPE_PLAY_TANK, null, null, this);
				
//		tank = 	new NormalTank(30, 30, 33, 33, 20,
//						true, false, this, 10,
//						BaseDirection.D, false, BaseDirection.RED);
		addObject(tank);  //加入地图管理
		//键盘事件 和 加入玩家控制，一辆坦克和玩家绑定
		this.addKeyListener(new Player(tank));
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
	
	
    //调用碰撞检测方法
	public GameObject getCollidedGameObj(MoveObject moveObject) {
		return collideHandler.getCollidedGameObj(moveObject);
	}
	
	//移除地图上这个物体
	public void removeObject(GameObject gameObj){
		gameMapHandler.removeFromObjectMap(gameObj);
	}
	
	//在地图上新增物体
	public void addObject(GameObject gameObj){
		gameMapHandler.addToObjectMap(gameObj);
	}
	
	
	
    public GameMapHandler getGameMapHandler() {
		return gameMapHandler;
	}


	public CollideHandler getCollideHandler() {
		return collideHandler;
	}


	public static void main(String[] args) {
    	WarMain war = new WarMain();
    	war.startGame();
	}



	
}
