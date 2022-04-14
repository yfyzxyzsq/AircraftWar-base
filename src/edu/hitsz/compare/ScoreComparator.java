package edu.hitsz.compare;

import edu.hitsz.dao.MyRecord;

import java.util.Comparator;

/**
* Description:按照分数对记录排序,分数相同时按照日期排序
* date: 2022/4/14 10:08
* @author: fyd
*/
public class ScoreComparator implements Comparator<MyRecord> {

    @Override
    public int compare(MyRecord a, MyRecord b) {
        if(a.getScore() != b.getScore()){
            return b.getScore() - a.getScore();
        }else{
            return (int)(a.getCalendar().getTimeInMillis() - b.getCalendar().getTimeInMillis())%2;
        }
    }
}
