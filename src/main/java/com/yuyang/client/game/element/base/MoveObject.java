package com.yuyang.client.game.element.base;


import java.awt.Image;

import com.yuyang.client.game.WarMain;


public abstract class MoveObject extends GameObject{
	
	protected int speed;
	protected BaseDirection moveDir = BaseDirection.U;
	protected boolean moving;
	
	public MoveObject(int x, int y, int width, int height, int life,
			boolean visiable, boolean invincible, WarMain war, int speed, BaseDirection moveDir, boolean moving, int id) {
		
		super(x, y, width, height, life, visiable, invincible, war, id);
		this.speed = speed;
		this.moveDir = moveDir;
		this.moving = moving;
	}
	
	public MoveObject(int x, int y, int width, int height, int life,
			boolean visiable, boolean invincible, WarMain war, int speed, BaseDirection moveDir, boolean moving) {
		
		super(x, y, width, height, life, visiable, invincible, war);
		this.speed = speed;
		this.moveDir = moveDir;
		this.moving = moving;
	}

	
	
	@Override
	public Image getImage() {
		return getMoveImages()[moveDir.toValue()];
	}

	protected abstract Image[] getMoveImages();

	@Override
	public void doAction() {
		super.doAction();
		if (isMoving()) tryMove();
	}

	//尝试移动，调用 scene.getCollidedGameObj 的碰撞检测 ， 还包含了撞击之后的处理调用
	public void tryMove() {
		while (true) {
			GameObject collidedGameObj = war.getCollidedGameObj(this);		//判断前进速度上有没有相撞的物体

			if (collidedGameObj == null) {         //如果没有相撞的物体，跳出循环，直接移动
				break;
			}

			onCollide(collidedGameObj);     //撞击之后先调用子类实现的撞击处理方法，处理之后在调用下面的onDie方法,先需要判断死活

			//下面三个方法重新看一下
			if (!collidedGameObj.isAlive()) {   //如果被撞击的物体没有生命值了， 那么调用被撞物体的onDie方法，传入参数为自己（撞击物体）,最终是把被撞击物体移除（死掉）
				collidedGameObj.onDie(this);	//这个方法是让调用者死掉，而不是传入的参数死掉
			}

			if (!isAlive()) {				//撞击的物体(也就是自己)没有生命值了， 那么调用自己的onDie方法，传入参数为被撞击物体，最近是让自己移除（死掉）
				onDie(collidedGameObj);		//这个方法是让调用者死掉，而不是传入的参数死掉
				return;
			}

			if (!isMoving()) {			    //如果自己的moving值已经被设为false，那么让自己停止，传入被撞物体（用于计算停止时候的的x，y），等于是最后一步移动到这里
				onStop(collidedGameObj);	//onStop方法，收到stop指令，等于是最后一步，把自己贴上碰撞物体上去
				return;
			}
		}

		//碰撞检测通过那么开始移动
		beforeMove();   //移动之前把自己站的这一块全都设为0，相当于预处理移动把当前占的位置设为足迹
		move();
		afterMove();    //移动之后把当前占的位置都更新为自己的id
	}
	
	
	//移动之前把自己站的这一块全都设为0，相当于预处理移动把当前占的位置设为足迹
	private void afterMove() {
		war.getGameMapHandler().leftFootprint(this);
	}


	private void beforeMove() {
		war.getGameMapHandler().eraseFootprint(this);
	}

	//通过dir的方向，改变的运动也就是 x、y的值，真正的运动
		protected void move(){
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
		}

		//x轴碰撞
		public void adjustXOnCollide(int x) {
			beforeMove();
			this.x = x;
			afterMove();
		}

		//y轴碰撞
		public void adjustYOnCollide(int y) {
			beforeMove();
			this.y = y;
			afterMove();
		}
		
    //停止之后的操作, 把自己贴上被撞物体
	private void onStop(GameObject collidedGameObj) {
		switch (moveDir) {
		case U:
			adjustYOnCollide(collidedGameObj.getY() + collidedGameObj.getHeight());
			break;
		case D:
			adjustYOnCollide(collidedGameObj.getY() - getHeight());
			break;
		case L:
			adjustXOnCollide(collidedGameObj.getX() + collidedGameObj.getWidth()); //被撞物体的x+被撞物体的宽
			break;
		case R:
			adjustXOnCollide(collidedGameObj.getX() - getWidth()); 
			break;
		default:
			throw new IllegalStateException("illegal direction: " + moveDir);
		}
	}

	private boolean isMoving() {
		return this.moving;
	}
	
	public void go(BaseDirection moveDir) {
		this.moveDir = moveDir;
		go();
	}

	public void go() {
		moving = true;
	}

	public void stop() {
		moving = false;
	}
	
	public int getSpeed() {
		return speed;
	}



	public void setSpeed(int speed) {
		this.speed = speed;
	}



	public BaseDirection getMoveDir() {
		return moveDir;
	}



	public void setMoveDir(BaseDirection moveDir) {
		this.moveDir = moveDir;
	}
	

	//碰撞之后的方法，需要子类重写
	protected abstract void onCollide(GameObject gameObject); 
	
	
}
