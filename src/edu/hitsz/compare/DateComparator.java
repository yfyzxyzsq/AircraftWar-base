package edu.hitsz.compare;

import edu.hitsz.dao.MyRecord;

import java.util.Comparator;

/**
* Description:实现将记录按照日期排序
* date: 2022/4/14 10:09
* @author: fyd
*/
public class DateComparator implements Comparator<MyRecord> {

    @Override
    public int compare(MyRecord a, MyRecord b) {
        return (int)(a.getCalendar().getTimeInMillis() - b.getCalendar().getTimeInMillis())%2;
    }
}
