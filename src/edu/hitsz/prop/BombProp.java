package edu.hitsz.prop;

import edu.hitsz.application.Main;
import edu.hitsz.music.MusicThread;
import edu.hitsz.music.MyMusic;

/**
* Description:此道具可使在场出boss和英雄机以外的战机爆炸，使敌机子弹消失
* date: 2022/3/16 15:57
* @author: fyd
*/
public class BombProp extends AbstractProp{

    public BombProp(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
    }

    @Override
    public void func() {
        super.func();
        System.out.println("BombSupply active!");
    }
}
