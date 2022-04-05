package edu.hitsz.factory;

import edu.hitsz.prop.AbstractProp;

public abstract class AbstractPropFactory {
    public abstract AbstractProp createProp(int locationX, int locationY);

    public abstract AbstractProp createProp();
}
