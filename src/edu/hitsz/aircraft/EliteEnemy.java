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
import edu.hitsz.weapon.Direct;
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

    //横向移动的标记,当为1时给定一个x方向上的初速度
    int flagSpeedX = 1;
    /**攻击方式 */

    public int getShootNum() {
        return shootNum;
    }

    public void setShootNum(int shootNum) {
        this.shootNum = shootNum;
    }

    /**
     * 子弹一次发射数量
     */
    private int shootNum = 1;

    public int getMaxShootNum() {
        return maxShootNum;
    }

    public void setMaxShootNum(int maxShootNum) {
        this.maxShootNum = maxShootNum;
    }

    private int maxShootNum = 1;


    /**
     * 子弹伤害
     */
    private int power = 30;

    /**
     * 子弹射击方向 (向上发射：1，向下发射：-1)
     */
    private int direction = 1;

    public EliteEnemy(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
    }

    @Override
    public void forward() {
        super.forward();

        if(flagSpeedX == 1){
            flagSpeedX = 0;
            int x = (Math.random() > 0.5?1:0);
            if(x == 0){
                speedX = 3;
            }
            else{
                speedX = -3;
            }
        }
        if(locationY >= Main.WINDOW_HEIGHT){
            vanish();
        }
    }

    @Override
    public boolean notValid() {

        return super.notValid();
    }

    @Override
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
        this.setShootStrategy(new Direct(this.power,this.shootNum,this.maxShootNum,this.locationX,this.locationY,
                    this.speedX,this.speedY,this.direction));
        res = shootStrategy.shoot();
        return res;
    }
}
