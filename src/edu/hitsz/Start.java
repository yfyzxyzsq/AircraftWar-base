package edu.hitsz;

import edu.hitsz.application.*;
import edu.hitsz.dao.RecordDaoImpl;
import edu.hitsz.music.MusicThread;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;


public class Start {
    private boolean startFlag = false;

    public boolean isStartFlag() {
        return startFlag;
    }

    public void setStartFlag(boolean startFlag) {
        this.startFlag = startFlag;
    }

    public JPanel getJpanel() {
        return Jpanel;
    }

    public void setJpanel(JPanel jpanel) {
        Jpanel = jpanel;
    }

    private JFrame Jframe;
    private JPanel Jpanel;
    private JButton simpleButton;
    private JButton normalButton;
    private JButton difficultButton;
    private JLabel musicLable;
    private JComboBox selectComboBox;
    private int mode = 0;

    public static final int WINDOW_WIDTH = 512;
    public static final int WINDOW_HEIGHT = 768;


    public Start() {
            simpleButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    startFlag = true;
                    Main.setMode(0);
                }
            });

            normalButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    startFlag = true;
                    Main.setMode(1);
                }
            });

            difficultButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    startFlag = true;
                    Main.setMode(2);
                }
            });

            selectComboBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String selected = selectComboBox.getSelectedItem().toString();
                    if (selected == "是") {
                        Main.setMusicFlag(true);
                    } else {
                        Main.setMusicFlag(false);
                    }
                }
            });
//        }
    }

    public void main() {

        JFrame frame = new JFrame("AircraftWar");
        frame.setSize(100,100);
        frame.setContentPane(new Start().getJpanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }


    private void createUIComponents() {
        // TODO: place custom component creation code here
        Jpanel=new JPanel();
        simpleButton = new JButton();
        normalButton = new JButton();
        difficultButton = new JButton();
        musicLable = new JLabel();
        selectComboBox = new JComboBox();
    }





    public void initUi(){
        // 获得屏幕的分辨率，初始化 Frame
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Jframe = new JFrame("Aircraft War");
        Jframe.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        Jframe.setResizable(false);
        //设置窗口的大小和位置,居中放置
        Jframe.setBounds(((int) screenSize.getWidth() - WINDOW_WIDTH) / 2, 0,
                WINDOW_WIDTH, WINDOW_HEIGHT);
        Jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Jframe.add(Jpanel);
        Jframe.setVisible(true);
    }
}
