package edu.hitsz.factory;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.aircraft.EliteEnemy;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;

public class EliteEnemyFactory extends AbstractPlaneFactory {
    @Override
    public AbstractAircraft createAircraft() {
        EliteEnemy eliteEnemy = new EliteEnemy(
                (int) ( Math.random() * (Main.WINDOW_WIDTH - ImageManager.MOB_ENEMY_IMAGE.getWidth()))*1,
                (int) (Math.random() * Main.WINDOW_HEIGHT*0.2)*1,
                0,
                10,
                60);
        return eliteEnemy;
    }
}
