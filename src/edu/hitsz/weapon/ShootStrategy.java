package edu.hitsz.weapon;

import edu.hitsz.bullet.AbstractBullet;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.bullet.HeroBullet;

import java.util.List;
/**
* Description:此类作为武器的抽象
* date: 2022/4/11 10:30
* @author: fyd
*/
public abstract class ShootStrategy {
    protected int power;
    protected int shootNum;
    protected int locationX;
    protected int locationY;
    protected int speedX;
    protected int speedY;
    protected int maxShootNum;
    protected int direction;

    protected AbstractBullet abstractBullet;


    public ShootStrategy(int power,int shootNum,int maxShootNum,int locationX,int locationY,int speedX,int speedY,int direction){
        this.power = power;
        this.shootNum = shootNum;
        this.maxShootNum = maxShootNum;
        this.locationX = locationX;
        this.locationY = locationY;
        this.speedX = speedX;
        this.speedY = speedY;
        this.direction = direction;
    }


    public abstract List<AbstractBullet> shoot();
}
