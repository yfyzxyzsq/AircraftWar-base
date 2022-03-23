package edu.hitsz.aircraft;

import edu.hitsz.bullet.AbstractBullet;

import java.util.List;

public class BossEnemy extends AbstractAircraft{

    public BossEnemy(int locationX, int locationY, int speedX, int speedY, int hp){
        super(locationX,locationY,speedX,speedY,hp);
    }
    @Override
    public List<AbstractBullet> shoot() {
        return null;
    }
}
