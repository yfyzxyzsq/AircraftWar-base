package edu.hitsz.factory;

import edu.hitsz.prop.AbstractProp;
import edu.hitsz.prop.BloodProp;

public class BloodPropFactory extends PropFactory{
    @Override
    public AbstractProp createProp(int locationX, int locationY) {
        BloodProp bloodProp = new BloodProp(locationX,locationY,0,10);
        return bloodProp;
    }

    @Override
    public AbstractProp createProp() {
        return null;
    }


}
