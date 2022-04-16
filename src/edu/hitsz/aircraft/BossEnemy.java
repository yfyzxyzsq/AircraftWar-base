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



    public BossEnemy(int locationX, int locationY, int speedX, int speedY, int hp){
        super(locationX,locationY,speedX,speedY,hp);
        this.power = 25;
        this.shootNum = 3;
        this.setMaxShootNum(6);
        this.setDirection(1);
    }
    @Override
    public List<AbstractBullet> shoot() {

        List<AbstractBullet> res = new LinkedList<>();
        int x = this.getLocationX();
        int y = this.getLocationY() + (1)*2;
        this.abstractShootStrategy = new Scattering(this);

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
