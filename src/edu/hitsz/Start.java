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

                    mode = 1;
                    startFlag = true;
                    //startGame();
                }
            });

            normalButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mode = 2;
                    startGame();
                }
            });

            difficultButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mode  = 3;
                    startGame();
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

    private void startGame() {
        Game game;
        if(mode == 1){
            game = new SimpleGame();
        }else if(mode == 2){
            game = new NormalGame();
        }else{
            game = new DifficultGame();
        }
        Jframe.remove(Jpanel);
        Jframe.add(game);
        game.revalidate();
        Jframe.repaint();
        Jframe.setVisible(true);
        game.action();

        while(!game.isGameOverFlag()){

        }
        String path = game.getRecordFile();
        RecordDaoImpl recordDao = new RecordDaoImpl(new File(path));
        End end = new End(recordDao);
        JPanel endPanel = end.getEndPanel();
        Jframe.remove(game);
        Jframe.add(endPanel);
        endPanel.revalidate();
        Jframe.repaint();
        Jframe.setVisible(true);

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
