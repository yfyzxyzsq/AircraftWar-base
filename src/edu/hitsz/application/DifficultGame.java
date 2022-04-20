package edu.hitsz.application;

public class DifficultGame extends Game{
    public DifficultGame() {
        this.setBackgroundImage(ImageManager.BACKGROUND_IMAGE_DIFFICULT);
        this.setRecordFile("src/records/DifficultRecords.dat");
    }
}
