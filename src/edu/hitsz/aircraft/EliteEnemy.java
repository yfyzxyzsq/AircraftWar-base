package edu.hitsz.aircraft;

import edu.hitsz.application.Main;
import edu.hitsz.bullet.AbstractBullet;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.bullet.HeroBullet;
import edu.hitsz.factory.AbstractPropFactory;
import edu.hitsz.factory.BloodPropFactory;
import edu.hitsz.factory.BombPropFactory;
import edu.hitsz.factory.BulletPropFactory;
import edu.hitsz.prop.AbstractProp;
import edu.hitsz.weapon.ShootStrategy;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
* Description:精英敌机，可射击
* date: 2022/3/16 10:07
* @author: fyd
*/
public class EliteEnemy extends AbstractAircraft{


    /**攻击方式 */

    /**
     * 子弹一次发射数量
     */
    private int shootNum = 1;

    /**
     * 子弹伤害
     */
    private int power = 30;

    /**
     * 子弹射击方向 (向上发射：1，向下发射：-1)
     */
    private int direction = 1;

    public EliteEnemy(int locationX, int locationY, int speedX, int speedY, int hp, ShootStrategy shootStrategy) {
        super(locationX, locationY, speedX, speedY, hp, shootStrategy);
    }

    @Override
    public void forward() {
        super.forward();
        if(locationY >= Main.WINDOW_HEIGHT){
            vanish();
        }
    }

    @Override
    public boolean notValid() {

        return super.notValid();
    }

    public AbstractProp createProp(){
        AbstractProp abstractProp = null;
        if(super.notValid()){
            Random ran = new Random();
            int prop = ran.nextInt(3);
            switch (prop){
                case 0:{
                    AbstractPropFactory propFactory = new BloodPropFactory();
                    abstractProp = propFactory.createProp(locationX,locationY);
                    //props.add(new BloodProp(locationX,locationY,0,10));
                    break;
                }
                case 1:{
                    AbstractPropFactory propFactory = new BombPropFactory();
                    abstractProp = propFactory.createProp(locationX,locationY);
                    //props.add(new BombProp(locationX,locationY,0,10));
                    break;
                }
                case 2:{
                    AbstractPropFactory propFactory = new BulletPropFactory();
                    abstractProp = propFactory.createProp(locationX,locationY);
                    //props.add(new BulletProp(locationX,locationY,0,10));
                    break;
                }
                default:;
            }
        }
        return abstractProp;
    }


    @Override
    public List<AbstractBullet> shoot() {
        List<AbstractBullet> res = new LinkedList<>();
        int x = this.getLocationX();
        int y = this.getLocationY() + direction*2;
        int speedX = 0;
        int speedY = this.getSpeedY() + direction*5;
        res = shootStrategy.shoot(x,y,speedX,speedY,power,shootNum);
        return res;
    }
}
