package edu.hitsz.weapon;

import edu.hitsz.bullet.AbstractBullet;

import java.util.List;

public interface ShootStrategy {
    public abstract List<AbstractBullet> shoot(int locationX, int intlocationY, int speedX, int speedY, int power, int shootNum);
}
