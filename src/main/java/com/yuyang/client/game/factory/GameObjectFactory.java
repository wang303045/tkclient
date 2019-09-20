package com.yuyang.client.game.factory;

import com.yuyang.client.game.WarMain;
import com.yuyang.client.game.common.Constants;
import com.yuyang.client.game.element.base.BaseDirection;
import com.yuyang.client.game.element.base.BaseWall;
import com.yuyang.client.game.element.base.GameObject;
import com.yuyang.client.game.element.tank.EnemyTank;
import com.yuyang.client.game.element.tank.NormalTank;
import com.yuyang.client.game.element.wall.DiaoXiang;
import com.yuyang.client.game.element.weapon.Mine;
import com.yuyang.client.game.element.weapon.NormalBullet;

public class GameObjectFactory {
	
	public static GameObject newGameObj(int x, int y, int type, BaseDirection dir, BaseDirection team, WarMain war) {
		GameObject gameObj;
		switch (type) {
		case Constants.TYPE_PLAY_TANK:
			gameObj = new NormalTank(x, y, 62, 64, 10,
					true, false, war, 10,
					BaseDirection.U, false, BaseDirection.RED);
			break;
		case Constants.TYPE_E_TANK:
			gameObj = new EnemyTank(x, y, 65, 54, 10,
					true, false, war, 10,
					BaseDirection.U, false);
			break;
		case Constants.TYPE_NO_BULLET:
			gameObj = new NormalBullet(x, y, 20, 20, 1, true, false, war, 15,
					dir, true, team, 1);
			break;
		case Constants.TYPE_MINE:
			gameObj = new Mine(x, y, 10, 10, 1, true, false, war, 0,
					dir, true, team, 1);
			break;
		case Constants.TYPE_WALL:
			gameObj = new BaseWall(x, y, 72, 84, 100, true, true, war);
			break;	
		case Constants.TYPE_WALL_DX:
			gameObj = new DiaoXiang(x, y, 190, 190, 100, true, true, war);
			break;	
		default:
			throw new IllegalArgumentException("error type! " + type);
		}
		return gameObj;
	}

}
