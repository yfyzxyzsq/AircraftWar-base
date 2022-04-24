package edu.hitsz.music;

import edu.hitsz.application.Game;

public class MyMusicThread extends MusicThread{
    public MyMusicThread(String filename, boolean isLoop) {
        super(filename, isLoop);
    }

    @Override
    public void run() {
        synchronized (Game.class){
            super.run();
        }
    }
}
