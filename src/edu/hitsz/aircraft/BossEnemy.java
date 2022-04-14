package edu.hitsz.aircraft;

import edu.hitsz.bullet.AbstractBullet;
import edu.hitsz.factory.AbstractPropFactory;
import edu.hitsz.factory.BloodPropFactory;
import edu.hitsz.factory.BombPropFactory;
import edu.hitsz.factory.BulletPropFactory;
import edu.hitsz.prop.AbstractProp;
import edu.hitsz.weapon.Scattering;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

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
        this.abstractShootStrategy = new Scattering(this.power,this.shootNum,this.getMaxShootNum(),
                    this.locationX,this.locationY, this.speedX,this.speedY,this.direction);

        res = abstractShootStrategy.shoot();
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

}
