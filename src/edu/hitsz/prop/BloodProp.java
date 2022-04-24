package edu.hitsz.prop;

import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.application.Main;
import edu.hitsz.music.MusicThread;
import edu.hitsz.music.MyMusic;

/**
* Description:此道具可为英雄机恢复hp,可叠加但不可超过上限
* date: 2022/3/16 16:02
* @author: fyd
*/

public class BloodProp extends AbstractProp{

    public BloodProp(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
    }

    @Override
    public void func() {
        super.func();
        HeroAircraft heroAircraft = HeroAircraft.getInstance();
        int hp = heroAircraft.getHp();
        hp = hp + 10;
        int mhp = heroAircraft.getMaxHp();
        if(hp > mhp){
            hp = mhp;
        }
        heroAircraft.setHp(hp);
    }
}
