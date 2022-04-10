package edu.hitsz.weapon;

import edu.hitsz.bullet.AbstractBullet;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.bullet.HeroBullet;

import java.util.LinkedList;
import java.util.List;

public class Direct implements ShootStrategy{
    @Override
    public List<AbstractBullet> shoot(int locationX, int locationY, int speedX, int speedY, int power,int shootNum) {
        List<AbstractBullet> res = new LinkedList<>();
        AbstractBullet abstractBullet;
        for(int i=0; i<shootNum; i++){
            // 子弹发射位置相对飞机位置向前偏移
            // 多个子弹横向分散
            if(speedY < 0) {
                abstractBullet = new HeroBullet(locationX + (i * 2 - shootNum + 1) * 10, locationY, speedX, speedY, power);
            }else{
                abstractBullet = new EnemyBullet(locationX + (i * 2 - shootNum + 1) * 10, locationY, speedX, speedY, power);
            }
            res.add(abstractBullet);
        }
        return res;
    }
}
