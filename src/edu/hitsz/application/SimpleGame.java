package edu.hitsz.application;

public class SimpleGame extends Game{
    public SimpleGame() {
        this.setBackgroundImage(ImageManager.BACKGROUND_IMAGE_SIMPLE);
        this.setRecordFile("src/records/SimpleRecords.dat");
    }

}
