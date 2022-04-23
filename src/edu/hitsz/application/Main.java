package edu.hitsz.application;

import edu.hitsz.End;
import edu.hitsz.Start;
import edu.hitsz.dao.RecordDaoImpl;

import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 * 程序入口
 *
 * @author hitsz
 */
public class Main {

    public static final int WINDOW_WIDTH = 512;
    public static final int WINDOW_HEIGHT = 768;
    private static boolean musicFlag = false;

    public static int getMode() {
        return mode;
    }

    public static void setMode(int mode) {
        Main.mode = mode;
    }

    private static int mode = 0;

    public static boolean isMusicFlag() {
        return musicFlag;
    }

    public static void setMusicFlag(boolean musicFlag) {
        Main.musicFlag = musicFlag;
    }

    public static void main(String[] args) {
        Object lock1 = new Object();
        JFrame Jframe;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Jframe = new JFrame("Aircraft War");
        Jframe.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        Jframe.setResizable(false);
        //设置窗口的大小和位置,居中放置
        Jframe.setBounds(((int) screenSize.getWidth() - WINDOW_WIDTH) / 2, 0,
                WINDOW_WIDTH, WINDOW_HEIGHT);
        Jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Start ss = new Start();
        Game[] game = {new SimpleGame(),new NormalGame(), new DifficultGame()};

        Thread thread1 = new Thread(() -> {
            synchronized (lock1) {
                System.out.println("Aircraft War");
                Jframe.add(ss.getJpanel());
                Jframe.setVisible(true);
                System.out.println("before first wait");
                lock1.notify();
                try {
                    lock1.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("next first wait,before second wait");
                Jframe.remove(ss.getJpanel());
                Jframe.add(game[Main.mode]);
                game[Main.mode].validate();
                Jframe.repaint();
                Jframe.setVisible(true);
                game[Main.mode].action();
                lock1.notify();
                System.out.println("before second wait");
                try {
                    lock1.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("after second wait");
                RecordDaoImpl recordDao = new RecordDaoImpl(new File(game[Main.mode].getRecordFile()));
                End end = new End(recordDao);
                Jframe.remove(game[Main.mode]);
                Jframe.add(end.getEndPanel());
                end.getEndPanel().revalidate();
                Jframe.repaint();
                Jframe.setVisible(true);

            }
        });

        thread1.start();

        synchronized (lock1) {
            lock1.notify();
            try {
                lock1.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            while (!ss.isStartFlag()) {
                System.out.println("not begin!!!");
                try {
                    Thread.sleep(1000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("before first notify");
            lock1.notify();
            System.out.println("after first notify");
            try {
                lock1.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            while (!game[Main.mode].isGameOverFlag() || game[Main.mode].isNotAddRecord()) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("before second notify");
            lock1.notify();
        }



    }
}
