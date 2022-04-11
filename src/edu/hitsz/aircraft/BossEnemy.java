package edu.hitsz.aircraft;

import edu.hitsz.bullet.AbstractBullet;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.weapon.Scattering;
import edu.hitsz.weapon.ShootStrategy;

import java.util.LinkedList;
import java.util.List;

public class BossEnemy extends AbstractAircraft{
    //一次发射子弹的数目
    private int shootNum = 3;
    //子弹的威力
    private int power = 30;

    private int direction = 1;

    public int getDirection() {
        return direction;
    }

    private int maxShootNum = 6;

    public int getMaxShootNum() {
        return maxShootNum;
    }

    public void setMaxShootNum(int maxShootNum) {
        this.maxShootNum = maxShootNum;
    }

    public BossEnemy(int locationX, int locationY, int speedX, int speedY, int hp){
        super(locationX,locationY,speedX,speedY,hp);
    }
    @Override
    public List<AbstractBullet> shoot() {

        List<AbstractBullet> res = new LinkedList<>();
        int x = this.getLocationX();
        int y = this.getLocationY() + (1)*2;
        this.shootStrategy = new Scattering(this.power,this.shootNum,this.getMaxShootNum(),
                    this.locationX,this.locationY, this.speedX,this.speedY,this.direction);

        res = shootStrategy.shoot();
        return res;
    }
    //给定初始x方向上速度的标志
    int flagSpeedX = 1;

    @Override
    public void forward() {
        super.forward();
        if(flagSpeedX == 1){
            flagSpeedX = 0;
            int x = (Math.random() > 0.5?1:0);
            if(x == 0){
                speedX = 5;
            }
            else{
                speedX = -5;
            }
        }
    }
}
