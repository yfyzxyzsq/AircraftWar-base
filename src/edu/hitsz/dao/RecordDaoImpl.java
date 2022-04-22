package edu.hitsz.dao;

import edu.hitsz.compare.ScoreComparator;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


/**
* Description:
* date: 2022/4/12 0:43
* @author: fyd
*/
public class RecordDaoImpl implements RecordDao{

    private List<MyRecord> recordList;
    private File file;
    //记录记录的条数
    private static int records;

    public void setFile(File file) {
        this.file = file;
    }

    public RecordDaoImpl(File file) {
        this.file = file;
    }

    //向文件中写入一条记录
    @Override
    public void addRecord(MyRecord record) {
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            if(file.length() != 0){
                boolean isExist = true;
                fileOutputStream = new FileOutputStream(file,true);
                objectOutputStream = new ObjectOutputStream(fileOutputStream);
                long pos = 0;
                if(isExist){
                    pos = fileOutputStream.getChannel().position()-4;
                    fileOutputStream.getChannel().truncate(pos);
                }
                objectOutputStream.writeObject(record);
            }else{
                fileOutputStream = new FileOutputStream(file);
                objectOutputStream = new ObjectOutputStream(fileOutputStream);
                objectOutputStream.writeObject(record);
                objectOutputStream.close();
            }

            records++;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try{
            if(objectOutputStream != null){
                objectOutputStream.close();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteRecord(int index) {
        this.recordList = this.getAllRecords();
        recordList.remove(index);
        this.reWrite();
    }

    @Override
    public MyRecord reviseRecord(MyRecord record) {
        return null;
    }

    @Override
    public void showRecords() {
        System.out.println("**************************************************");
        System.out.println("                      得分榜                       ");
        System.out.println("**************************************************");
        if(this.recordList == null){
            this.recordList = getAllRecords();
        }
        for(int i = 0;i < recordList.size();i++){
            System.out.println("Rank: "+(i+1)+"\t"+recordList.get(i).toString());
        }
    }

    @Override
    public  List<MyRecord> getAllRecords() {
        List<MyRecord> myRecords = new ArrayList<>();

        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            objectInputStream = new ObjectInputStream(fileInputStream);
            MyRecord myRecord;
            while(fileInputStream.available() > 0){
                myRecord = (MyRecord) objectInputStream.readObject();
                myRecords.add(myRecord);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            if(objectInputStream != null){
                objectInputStream.close();
            }
        } catch (IOException e) {

            e.printStackTrace();
        }

        records = myRecords.size();
        Collections.sort(myRecords,new ScoreComparator());

        return myRecords;
    }

    @Override
    public MyRecord findByName(String name) {
        return null;
    }

    @Override
    public MyRecord findByRank(int rank) {
        return null;
    }

    @Override
    public String[][] listToTable() {
        recordList = getAllRecords();
        String[][] recordTable;
        recordTable = new String[records][4];
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (int i = 0;i < records;i++) {
            MyRecord myRecord = recordList.get(i);
            String name = myRecord.getName();
            String rank = String.valueOf(i+1);
            String score = String.valueOf(myRecord.getScore());
            String date = dateFormat.format(myRecord.getCalendar().getTime());

            recordTable[i][0] = rank;
            recordTable[i][1] = name;
            recordTable[i][2] = score;
            recordTable[i][3] = date;
        }
        return recordTable;
    }

    @Override
    public void reWrite() {
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;

        try {
            fileOutputStream = new FileOutputStream(file);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            for(int i = 0;i < recordList.size();i++){
                objectOutputStream.writeObject(recordList.get(i));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            if(objectOutputStream != null){
                objectOutputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
