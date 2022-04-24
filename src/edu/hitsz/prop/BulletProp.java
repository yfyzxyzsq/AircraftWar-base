package edu.hitsz.prop;


import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.application.Main;
import edu.hitsz.music.MusicThread;
import edu.hitsz.music.MyMusic;
import edu.hitsz.weapon.Direct;
import edu.hitsz.weapon.Scattering;

/**
* Description:bulletprop道具为英雄机加强火力
* date: 2022/3/16 15:56
* @author: fyd
*/
public class BulletProp extends AbstractProp{

    private int times = 0;
    private int tmp = 0;
    public BulletProp(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
    }

    @Override
    public void func() {
        times++;
        HeroAircraft heroAircraft = HeroAircraft.getInstance();
        Runnable fire = ()->{
            super.func();
            System.out.println("FireSupply avtive!");
            int maxShootNum = heroAircraft.getMaxShootNum();
            int shootNum = heroAircraft.getShootNum();
            if(shootNum < maxShootNum){
                shootNum += 2;
                heroAircraft.setShootNum(shootNum);
            }else if(shootNum > maxShootNum){
                heroAircraft.setShootNum(1);
            }
            try {
                Thread.sleep(3000);
                if(times >= 2){
                    Thread.sleep(5000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            times = 0;
            heroAircraft.setShootNum(1);
        };
        Main.getThreadPoolExecutor().execute(fire);
    }
}
