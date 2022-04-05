package edu.hitsz.factory;

import edu.hitsz.prop.AbstractProp;
import edu.hitsz.prop.BulletProp;

public class BulletPropFactory extends AbstractPropFactory {
    @Override
    public AbstractProp createProp(int locationX, int locationY) {
        AbstractProp abstractProp = new BulletProp(locationX,locationY,0,10);

        return abstractProp;
    }

    @Override
    public AbstractProp createProp() {
        return null;
    }
}
