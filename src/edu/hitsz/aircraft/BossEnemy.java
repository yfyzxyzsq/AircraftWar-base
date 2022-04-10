package edu.hitsz.aircraft;

import edu.hitsz.bullet.AbstractBullet;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.weapon.ShootStrategy;

import java.util.LinkedList;
import java.util.List;

public class BossEnemy extends AbstractAircraft{
    //一次发射子弹的数目
    private int shootNum = 3;
    //子弹的威力
    private int power = 30;

    public BossEnemy(int locationX, int locationY, int speedX, int speedY, int hp, ShootStrategy shootStrategy){
        super(locationX,locationY,speedX,speedY,hp);
        this.shootStrategy = shootStrategy;
    }
    @Override
    public List<AbstractBullet> shoot() {

        List<AbstractBullet> res = new LinkedList<>();
        int x = this.getLocationX();
        int y = this.getLocationY() + (1)*2;


        res = shootStrategy.shoot(locationX,locationY,speedX,speedY,power,shootNum);
        return res;
    }
}
