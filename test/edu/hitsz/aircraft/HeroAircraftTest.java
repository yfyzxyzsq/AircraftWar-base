package edu.hitsz.aircraft;

import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;
import edu.hitsz.basic.AbstractFlyingObject;
import edu.hitsz.bullet.AbstractBullet;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HeroAircraftTest extends AbstractFlyingObject {

    HeroAircraft heroAircraft = HeroAircraft.getInstance();

    @BeforeEach
    void setUp() {
        System.out.println("**---Executed before each test method in this class---**");

    }

    @AfterEach
    void tearDown() {
        System.out.println("***---Executed after each test method in this class---***");
    }

    @ParameterizedTest
    @CsvSource({"100,-10","50,50","0,60"})
    void decreaseHp(int hp, int val) {
        heroAircraft.decreaseHp(val);
        assertEquals(hp,heroAircraft.getHp());
    }

    @Test
    void testCrash() {
        MobEnemy mobEnemy = new MobEnemy(Main.WINDOW_WIDTH / 2, Main.WINDOW_HEIGHT, 0, 10,30);
        mobEnemy.forward();
        System.out.println("英雄机位置 x:"+heroAircraft.getLocationX()+" y:"+heroAircraft.getLocationY());
        System.out.println("敌机位置 x:"+mobEnemy.getLocationX()+" y"+mobEnemy.getLocationY());
        assertFalse(heroAircraft.crash(mobEnemy));

    }

    @Test
    void shoot() {
        List<AbstractBullet> heroBullets = new LinkedList<>();
        heroBullets.addAll(heroAircraft.shoot());
        assertNotNull(heroBullets);
    }
}