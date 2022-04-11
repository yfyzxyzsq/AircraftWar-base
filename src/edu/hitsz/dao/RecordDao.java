package edu.hitsz.dao;

import java.util.List;

public interface RecordDao {

    public void addRecord(Record record);

    public void deleteRecord(int index);

    public Record reviseRecord(Record record);

    public List<Record> getAllRecords();

    public Record findByName(String name);

    public Record findByRank(int rank);
}
