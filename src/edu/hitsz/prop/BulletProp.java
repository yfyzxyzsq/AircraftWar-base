package edu.hitsz.prop;


/**
* Description:bulletprop道具为英雄机加强火力
* date: 2022/3/16 15:56
* @author: fyd
*/
public class BulletProp extends AbstractProp{

    public BulletProp(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
    }

    @Override
    public void func() {
        super.func();
        System.out.println("FireSupply avtive!");
    }
}
