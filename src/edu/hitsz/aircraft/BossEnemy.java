package edu.hitsz.aircraft;

import edu.hitsz.bullet.AbstractBullet;
import edu.hitsz.bullet.EnemyBullet;

import java.util.LinkedList;
import java.util.List;

public class BossEnemy extends AbstractAircraft{
    //一次发射子弹的数目
    private int shootNum = 5;
    //子弹的威力
    private int power = 30;

    public BossEnemy(int locationX, int locationY, int speedX, int speedY, int hp){
        super(locationX,locationY,speedX,speedY,hp);
    }
    @Override
    public List<AbstractBullet> shoot() {

        List<AbstractBullet> res = new LinkedList<>();
        int x = this.getLocationX();
        int y = this.getLocationY() + (1)*2;
        int[] speedX = new int[5];
        speedX[0] = 5;
        speedX[1] = 10;
        speedX[2] = -5;
        speedX[3] = -10;
        speedX[4] = (int) Math.sin(this.getSpeedY());

        int[] speedY = new int[5];
        speedY[0] = this.getSpeedY() + (1)*5;
        for (int i = 1; i <5 ; i++) {
            speedY[i] = this.getSpeedY() + 5 +2*i;
        }
        AbstractBullet abstractBullet;
        for(int i=0; i<shootNum; i++){
            // 子弹发射位置相对飞机位置向前偏移
            // 多个子弹横向分散
            abstractBullet = new EnemyBullet(x + (i*2 - shootNum + 1)*10, y, speedX[i], speedY[i], power);
            res.add(abstractBullet);
        }
        return res;
    }
}
