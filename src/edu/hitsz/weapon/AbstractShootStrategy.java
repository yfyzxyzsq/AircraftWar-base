package edu.hitsz.weapon;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.bullet.AbstractBullet;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.bullet.HeroBullet;

import java.util.List;
/**
* Description:此类作为武器的抽象
* date: 2022/4/11 10:30
* @author: fyd
*/
public abstract class AbstractShootStrategy {
    protected int power;
    protected int shootNum;
    protected int locationX;
    protected int locationY;
    protected int speedX;
    protected int speedY;
    protected int maxShootNum;
    protected int direction;

    protected AbstractBullet abstractBullet;


    public AbstractShootStrategy(AbstractAircraft abstractAircraft){
        this.power = abstractAircraft.getPower();
        this.shootNum = abstractAircraft.getShootNum();
        this.maxShootNum = abstractAircraft.getMaxShootNum();
        this.locationX = abstractAircraft.getLocationX();
        this.locationY = abstractAircraft.getLocationY();
        this.speedX = abstractAircraft.getSpeedX();
        this.speedY = abstractAircraft.getSpeedY();
        this.direction = abstractAircraft.getDirection();
    }


    public abstract List<AbstractBullet> shoot();
}
