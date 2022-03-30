package edu.hitsz.aircraft;

import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;
import edu.hitsz.basic.AbstractFlyingObject;
import edu.hitsz.bullet.AbstractBullet;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EliteEnemyTest extends AbstractFlyingObject {
    EliteEnemy eliteEnemy = new EliteEnemy(Main.WINDOW_WIDTH / 2,
            Main.WINDOW_HEIGHT - ImageManager.HERO_IMAGE.getHeight() ,
            0, 0, 100);
    @BeforeEach
    void setUp() {
        System.out.println("**---Executed before each test method in this class---**");

    }

    @AfterEach
    void tearDown() {
        System.out.println("***---Executed after each test method in this class---***");
    }


    @Test
    void testNotValid() {
        HeroAircraft heroAircraft = HeroAircraft.GetInstance();
        if(heroAircraft.crash(eliteEnemy)){
            eliteEnemy.vanish();
        }

        assertTrue(eliteEnemy.notValid());
    }

    @Test
    void shoot() {
        List<AbstractBullet> eliteBullets = new LinkedList<>();
        eliteBullets.addAll(eliteEnemy.shoot());
        assertNotNull(eliteBullets);
    }
}