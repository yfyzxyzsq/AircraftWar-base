package edu.hitsz.weapon;

import edu.hitsz.bullet.AbstractBullet;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.bullet.HeroBullet;

import java.util.LinkedList;
import java.util.List;

public class Direct extends ShootStrategy{
    public Direct(int power, int shootNum, int maxShootNum, int locationX, int locationY, int speedX, int speedY, int direction) {
        super(power, shootNum, maxShootNum, locationX, locationY, speedX, speedY, direction);
    }

    @Override
    public List<AbstractBullet> shoot() {
        List<AbstractBullet> res = new LinkedList<>();
        for(int i=0; i<this.shootNum; i++){
            // 子弹发射位置相对飞机位置向前偏移
            // 多个子弹横向分散
            if(direction > 0){
                abstractBullet = new EnemyBullet(locationX+(i*2-shootNum+1)*10,locationY+direction*2,speedX,speedY+direction*5,power);
            }else{
                abstractBullet = new HeroBullet(locationX+(i*2-shootNum+1)*10,locationY+direction*2,speedX,speedY+direction*5,power);
            }
            res.add(abstractBullet);
        }
        return res;
    }
}
