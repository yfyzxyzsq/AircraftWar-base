package edu.hitsz.prop;

import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;
import edu.hitsz.basic.AbstractFlyingObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BloodPropTest extends AbstractFlyingObject {

    BloodProp bloodProp = new BloodProp(Main.WINDOW_WIDTH / 2,
            Main.WINDOW_HEIGHT - ImageManager.HERO_IMAGE.getHeight() ,
            0, 0);
    @BeforeEach
    void setUp() {
        System.out.println("**---Executed before each test method in this class---**");

    }

    @AfterEach
    void tearDown() {
        System.out.println("***---Executed after each test method in this class---***");
    }

    @Test
    void func() {
        HeroAircraft heroAircraft = HeroAircraft.getInstance();
        heroAircraft.decreaseHp(20);
        heroAircraft.crash(bloodProp);
        bloodProp.func();
        assertEquals(90,heroAircraft.getHp());
    }

    @Test
    void testVanish() {
        bloodProp.vanish();
        assertTrue(bloodProp.notValid());
    }
}