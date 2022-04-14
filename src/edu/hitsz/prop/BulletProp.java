package edu.hitsz.prop;


import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.weapon.Direct;
import edu.hitsz.weapon.Scattering;

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
        HeroAircraft heroAircraft = HeroAircraft.getInstance();
        int maxShootNum = heroAircraft.getMaxShootNum();
        int shootNum = heroAircraft.getShootNum();
        if(shootNum < maxShootNum){
            shootNum += 2;
            heroAircraft.setShootNum(shootNum);
        }else if(shootNum > maxShootNum){
            heroAircraft.setShootNum(1);
        }
//        heroAircraft.setShootStrategy(new Scattering(heroAircraft.getPower(),heroAircraft.getShootNum(),heroAircraft.getMaxShootNum()
//                ,heroAircraft.getLocationX(),heroAircraft.getLocationY(),heroAircraft.getSpeedX(),heroAircraft.getSpeedY(),heroAircraft.getDirection()));

    }
}
