package edu.hitsz.factory;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.aircraft.BossEnemy;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;
import edu.hitsz.weapon.Scattering;

public class BossEnemyFactory extends AbstractPlaneFactory {
    @Override
    public AbstractAircraft createAircraft() {

        BossEnemy bossEnemy = new BossEnemy(
                (int) ( Math.random() * (Main.WINDOW_WIDTH - ImageManager.MOB_ENEMY_IMAGE.getWidth()))*1,
                (int) (Math.random() * Main.WINDOW_HEIGHT*0.2)*1,//纵向位置需修改
                0,
                0,
                200,
                new Scattering());
        return bossEnemy;
    }
}
