package edu.hitsz.aircraft;

import edu.hitsz.bullet.AbstractBullet;
import edu.hitsz.basic.AbstractFlyingObject;
import edu.hitsz.prop.AbstractProp;
import edu.hitsz.weapon.AbstractShootStrategy;

import java.util.List;

/**
 * 所有种类飞机的抽象父类：
 * 敌机（BOSS, ELITE, MOB），英雄飞机
 *
 * @author hitsz
 */
public abstract class AbstractAircraft extends AbstractFlyingObject {
    /**
     * 生命值
     */
    protected int maxHp;
    protected int hp;

    public void setShootStrategy(AbstractShootStrategy abstractShootStrategy) {
        this.abstractShootStrategy = abstractShootStrategy;
    }

    //添加射击策略
    protected AbstractShootStrategy abstractShootStrategy;


    public AbstractAircraft(int locationX, int locationY, int speedX, int speedY, int hp){
        super(locationX, locationY, speedX, speedY);
        this.hp = hp;
        this.maxHp = hp;
    }

    public void decreaseHp(int decrease){
        if(decrease>=0){
            hp -= decrease;
        }

        if(hp <= 0){
            hp=0;
            vanish();
        }
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    /**
     * 飞机射击方法，可射击对象必须实现
     * @return
     *  可射击对象需实现，返回子弹
     *  非可射击对象空实现，返回null
     */
    public abstract List<AbstractBullet> shoot();

    /**
    * Description:产生道具的抽象方法，若不产生，则返回null
    * date: 2022/4/14 14:55
    * @author: fyd
    */
    public abstract AbstractProp createProp();

}


