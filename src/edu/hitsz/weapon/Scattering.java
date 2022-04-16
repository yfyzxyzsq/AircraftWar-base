package edu.hitsz.weapon;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.bullet.AbstractBullet;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.bullet.HeroBullet;

import java.util.LinkedList;
import java.util.List;

public class Scattering extends AbstractShootStrategy {

    public Scattering(AbstractAircraft abstractAircraft) {
        super(abstractAircraft);
    }

    @Override
    public List<AbstractBullet> shoot() {
        List<AbstractBullet> res = new LinkedList<>();
        int[] eSpeedXs = {0,4,-4,8,-8};
        int[][] hSpeedXs = {{0,-2,2,-4,4},{0,-4,4,-8,8},{0,0,5,-5,0}};
        int sy = speedY+direction*5;
        int[] hSpeedYs = {-5,-10,-15,-20,-25};
        power = power/shootNum+15;
        int j = (int)(Math.random()*10)%3;
        for(int i=0; i<shootNum; i++){
            // 子弹发射位置相对飞机位置向前偏移
            // 多个子弹横向分散
            if(direction > 0){
                abstractBullet = new EnemyBullet(locationX,locationY+direction*2,eSpeedXs[i],speedY+direction*8,power);
            }else{

                abstractBullet = new HeroBullet(locationX,locationY+direction*2,hSpeedXs[j][i],hSpeedYs[i],power);
            }
            res.add(abstractBullet);
        }
        return res;
    }
}
