package edu.hitsz.weapon;

import edu.hitsz.bullet.AbstractBullet;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.bullet.HeroBullet;

import java.util.LinkedList;
import java.util.List;

import static java.lang.Math.pow;

public class Scattering implements ShootStrategy{
    @Override
    public List<AbstractBullet> shoot(int locationX, int locationY, int speedX, int speedY, int power, int shootNum) {
        List<AbstractBullet> res = new LinkedList<>();
        AbstractBullet abstractBullet;
        int[] speedXs = {0,4,-4,8,-8};
        for(int i=0; i<shootNum; i++){
            // 子弹发射位置相对飞机位置向前偏移
            // 多个子弹横向分散
            if(speedY < 0) {
                abstractBullet = new HeroBullet(locationX + (i * 2 - shootNum + 1) * 10, locationY, speedXs[i], speedY, power);
            }else{
                abstractBullet = new EnemyBullet(locationX + (i * 2 - shootNum + 1) * 10, locationY, speedXs[i], 5+i*3, power);
            }
            res.add(abstractBullet);
        }
        return res;
    }
}
