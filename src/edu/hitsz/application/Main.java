package edu.hitsz.application;

import edu.hitsz.Start;

import javax.swing.*;
import java.awt.*;

/**
 * 程序入口
 * @author hitsz
 */
public class Main {

    public static final int WINDOW_WIDTH = 512;
    public static final int WINDOW_HEIGHT = 768;
    private static boolean musicFlag = false;

    public static boolean isMusicFlag() {
        return musicFlag;
    }

    public static void setMusicFlag(boolean musicFlag) {
        Main.musicFlag = musicFlag;
    }

    public static void main(String[] args) {

        System.out.println("Hello Aircraft War");
        //frame.remove(start.getJpanel());
        Start start = new Start();
        start.initUi();

//

    }
}
