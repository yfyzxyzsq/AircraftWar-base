package edu.hitsz.aircraft;

import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;
import edu.hitsz.bullet.AbstractBullet;
import edu.hitsz.prop.AbstractProp;
import edu.hitsz.weapon.Direct;
import edu.hitsz.weapon.Scattering;

import java.util.LinkedList;
import java.util.List;

/**
 * 英雄飞机，游戏玩家操控
 * @author hitsz
 */
public class HeroAircraft extends AbstractAircraft {


    //单例模式
    private static HeroAircraft heroAircraft = new HeroAircraft(Main.WINDOW_WIDTH / 2,
            Main.WINDOW_HEIGHT - ImageManager.HERO_IMAGE.getHeight() ,
            0, 0, 100);

    public static HeroAircraft getInstance(){
        return heroAircraft;
    }
    /**攻击方式 */

    /**
     * 子弹一次发射数量
     */

    /**
     * 子弹射击方向 (向上发射：1，向下发射：-1)
     */

    //英雄机最大生命值
    private int maxHp;

    public int getMaxHp() {
        return maxHp;
    }

    /**
     * @param locationX 英雄机位置x坐标
     * @param locationY 英雄机位置y坐标
     * @param speedX 英雄机射出的子弹的基准速度（英雄机无特定速度）
     * @param speedY 英雄机射出的子弹的基准速度（英雄机无特定速度）
     * @param hp    初始生命值
     */
    private HeroAircraft(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
        maxHp = hp;
        this.power = 30;
        this.shootNum = 1;
        this.setMaxShootNum(5);
        this.setDirection(-1);
    }

    @Override
    public void forward() {
        // 英雄机由鼠标控制，不通过forward函数移动
    }

    @Override
    public AbstractProp createProp() {
        return null;
    }

    @Override
    /**
     * 通过射击产生子弹
     * @return 射击出的子弹List
     */
    public List<AbstractBullet> shoot() {
        List<AbstractBullet> res = new LinkedList<>();
        int x = this.getLocationX();
        int y = this.getLocationY() + direction*2;
        int speedX = 0;
        int speedY = this.getSpeedY() + direction*5;
        if(this.shootNum == 1){
            this.setShootStrategy(new Direct(heroAircraft));
        }else{
            this.setShootStrategy(new Scattering(heroAircraft));
        }

        res = abstractShootStrategy.shoot();
        return res;
    }

}
