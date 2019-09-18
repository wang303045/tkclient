package com.yuyang.client.game.factory;

import com.yuyang.client.game.WarMain;
import com.yuyang.client.game.common.Config;
import com.yuyang.client.game.element.base.BaseDirection;
import com.yuyang.client.game.element.base.BaseTank;
import com.yuyang.client.game.element.base.GameObject;
import com.yuyang.client.game.element.tank.EnemyTank;
import com.yuyang.client.game.element.tank.NormalTank;

public class GameObjectFactory {
	
	public static GameObject newGameObj(int x, int y, int type, WarMain war) {
		GameObject gameObj;
		switch (type) {
		case Config.TYPE_PLAY_TANK:
			gameObj = new NormalTank(x, y, 62, 64, 20,
					true, false, war, 10,
					BaseDirection.U, false, BaseDirection.RED);
			break;
		case Config.TYPE_E_TANK:
			gameObj = new EnemyTank(x, y, 65, 54, 20,
					true, false, war, 10,
					BaseDirection.U, false);
			break;
		default:
			throw new IllegalArgumentException("error type! " + type);
		}
		return gameObj;
	}

}
