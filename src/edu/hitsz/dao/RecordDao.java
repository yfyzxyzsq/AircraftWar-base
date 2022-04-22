package edu.hitsz.dao;

import java.util.List;

public interface RecordDao {

    public void addRecord(MyRecord record);

    public void deleteRecord(int index);

    public MyRecord reviseRecord(MyRecord record);

    public List<MyRecord> getAllRecords();

    public MyRecord findByName(String name);

    public MyRecord findByRank(int rank);

    public void showRecords();

    public String[][] listToTable();

    public void reWrite();
}
