package edu.hitsz.factory;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.aircraft.BossEnemy;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;

public class BossEnemyFactory extends PlaneFactory{
    @Override
    public AbstractAircraft createAircraft() {

        BossEnemy bossEnemy = new BossEnemy(
                (int) ( Math.random() * (Main.WINDOW_WIDTH - ImageManager.MOB_ENEMY_IMAGE.getWidth()))*1,
                (int) (Math.random() * Main.WINDOW_HEIGHT*0.2)*1,//纵向位置需修改
                0,
                10,
                60);
        return bossEnemy;
    }
}
