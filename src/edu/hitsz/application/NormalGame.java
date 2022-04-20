package edu.hitsz.application;

public class NormalGame extends Game{
    public NormalGame() {
        this.setBackgroundImage(ImageManager.BACKGROUND_IMAGE_NORMAL);
        this.setRecordFile("src/records/NormalRecords.dat");
    }
}
