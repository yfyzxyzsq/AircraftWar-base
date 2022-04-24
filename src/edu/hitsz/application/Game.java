package edu.hitsz.application;

import edu.hitsz.End;
import edu.hitsz.aircraft.*;
import edu.hitsz.bullet.AbstractBullet;
import edu.hitsz.basic.AbstractFlyingObject;
import edu.hitsz.dao.MyRecord;
import edu.hitsz.dao.RecordDaoImpl;
import edu.hitsz.factory.*;
import edu.hitsz.music.MusicThread;
import edu.hitsz.music.MyMusic;
import edu.hitsz.prop.AbstractProp;
import edu.hitsz.prop.BombProp;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.*;
import java.util.List;
import java.util.concurrent.*;

/**
 * 游戏主面板，游戏启动
 *
 * @author hitsz
 */
public class Game extends JPanel {

    private static boolean gameOver = false;

    private boolean hasBossEnemy = false;

    public boolean isHasBossEnemy() {
        return hasBossEnemy;
    }

    public void setHasBossEnemy(boolean hasBossEnemy) {
        this.hasBossEnemy = hasBossEnemy;
    }

    public static boolean isGameOver() {
        return gameOver;
    }

    public static void setGameOver(boolean gameOver) {
        Game.gameOver = gameOver;
    }

    public boolean isNotAddRecord() {
        return notAddRecord;
    }

    public void setNotAddRecord(boolean notAddRecord) {
        this.notAddRecord = notAddRecord;
    }

    private boolean notAddRecord = true;

    private BossEnemy bossEnemy;
    private int backGroundTop = 0;

    /**
     * Scheduled 线程池，用于任务调度
     */
    private final ScheduledExecutorService executorService;

    /**
     * 时间间隔(ms)，控制刷新频率
     */
    private int timeInterval = 40;

    public BufferedImage getBackgroundImage() {
        return BACKGROUND_IMAGE;
    }

    public void setBackgroundImage(BufferedImage backgroundImage) {
        BACKGROUND_IMAGE = backgroundImage;
    }

    public BufferedImage BACKGROUND_IMAGE;

    private final HeroAircraft heroAircraft;
    private final List<AbstractAircraft> enemyAircrafts;
    private final List<AbstractBullet> heroBullets;
    private final List<AbstractBullet> enemyBullets;
    //添加道具
    private final List<AbstractProp> props;

    private int enemyMaxNumber = 5;

    public boolean isGameOverFlag() {
        return gameOverFlag;
    }

    public void setGameOverFlag(boolean gameOverFlag) {
        this.gameOverFlag = gameOverFlag;
    }

    private boolean gameOverFlag = false;
    private int score = 0;
    //flag与score关联，根据flag添加Boss敌机
    private int flag = 0;
    private int time = 0;
    /**
     * 周期（ms)
     * 指示子弹的发射、敌机的产生频率
     */
    private int cycleDuration = 600;
    private int cycleTime = 0;

    //添加RecordDaoImpl类对后期数据进行处理
    private RecordDaoImpl recordDaoImpl;

    private String recordFile;

    public String getRecordFile() {
        return recordFile;
    }

    public void setRecordFile(String recordFile) {
        this.recordFile = recordFile;
    }

    public Game() {
        heroAircraft = HeroAircraft.getInstance();

        enemyAircrafts = new LinkedList<>();
        heroBullets = new LinkedList<>();
        enemyBullets = new LinkedList<>();
        //prop list初始化
        props = new LinkedList<>();

        /**
         * Scheduled 线程池，用于定时任务调度
         * 关于alibaba code guide：可命名的 ThreadFactory 一般需要第三方包
         * apache 第三方库： org.apache.commons.lang3.concurrent.BasicThreadFactory
         */
        this.executorService = new ScheduledThreadPoolExecutor(1,
                new BasicThreadFactory.Builder().namingPattern("game-action-%d").daemon(true).build());

        //启动英雄机鼠标监听
        new HeroController(this, heroAircraft);


    }

    /**
     * 游戏启动入口，执行游戏逻辑
     */
    public void action() {
        MyMusic.playBgmMusic();
        // 定时任务：绘制、对象产生、碰撞判定、击毁及结束判定
        Runnable task = () -> {

            time += timeInterval;

            // 周期性执行（控制频率）
            if (timeCountAndNewCycleJudge()) {
                System.out.println(time);
                // 新敌机产生
                if (enemyAircrafts.size() < enemyMaxNumber) {
                    Random rand = new Random();
                    int a = rand.nextInt(10);
                    if(a%2 == 0){
                        AbstractPlaneFactory planeFactory = new MobEnemyFactory();
                        AbstractAircraft aircraft = planeFactory.createAircraft();
                        enemyAircrafts.add(aircraft);

                    }else{
                        AbstractPlaneFactory planeFactory = new EliteEnemyFactory();
                        AbstractAircraft aircraft = planeFactory.createAircraft();
                        enemyAircrafts.add(aircraft);

                    }


                }
                // 飞机射出子弹
                shootAction();
            }

            //判断boss机是否仍在场,被消灭后关掉其背景音乐
            turnDownBossmusic();

            // 子弹移动
            bulletsMoveAction();

            // 飞机移动
            aircraftsMoveAction();

            // 撞击检测
            crashCheckAction();

            //添加boss敌机
            addBossAction();

            //添加道具移动
            propsMoveAction();

            // 后处理
            postProcessAction();

            //每个时刻重绘界面
            repaint();

            // 游戏结束检查
            if (heroAircraft.getHp() <= 0) {
                // 游戏结束
                MyMusic.interruptAll();
                MyMusic.getGameOverMusic().run();

                executorService.shutdown();
                Main.getThreadPoolExecutor().shutdown();
                System.out.println("Game Over!");
                recordProcessing();
                notAddRecord = false;
                gameOverFlag = true;
                gameOver = true;
            }

        };

        /**
         * 以固定延迟时间进行执行
         * 本次任务执行完成后，需要延迟设定的延迟时间，才会执行新的任务
         */
        executorService.scheduleWithFixedDelay(task, timeInterval, timeInterval, TimeUnit.MILLISECONDS);

    }

    //***********************
    //      Action 各部分
    //***********************

    private boolean timeCountAndNewCycleJudge() {
        cycleTime += timeInterval;
        if (cycleTime >= cycleDuration && cycleTime - timeInterval < cycleTime) {
            // 跨越到新的周期
            cycleTime %= cycleDuration;
            return true;
        } else {
            return false;
        }
    }

    private void shootAction() {
        // TODO 敌机射击
        for (int i = 0; i < enemyAircrafts.size(); i++) {
            if(enemyAircrafts.get(i) instanceof EliteEnemy){
                EliteEnemy e = (EliteEnemy) enemyAircrafts.get(i);
                enemyBullets.addAll(e.shoot());
            }else if(enemyAircrafts.get(i) instanceof BossEnemy){
                BossEnemy b = (BossEnemy) enemyAircrafts.get(i);
                enemyBullets.addAll(b.shoot());
            }
        }
        // 英雄射击
        heroBullets.addAll(heroAircraft.shoot());
        MyMusic.playBulletMusic();
    }

    private void bulletsMoveAction() {
        for (AbstractBullet bullet : heroBullets) {
            bullet.forward();
        }
        for (AbstractBullet bullet : enemyBullets) {
            bullet.forward();
        }
    }

    private void aircraftsMoveAction() {
        for (AbstractAircraft enemyAircraft : enemyAircrafts) {
            enemyAircraft.forward();
        }
    }

    //添加道具移动
    private  void propsMoveAction(){
        for(AbstractProp abstractProp : props){
            abstractProp.forward();
        }
    }

    /**
     * 碰撞检测：
     * 1. 敌机攻击英雄
     * 2. 英雄攻击/撞击敌机
     * 3. 英雄获得补给
     */
    private void crashCheckAction() {
        // TODO 敌机子弹攻击英雄
        for(AbstractBullet ebullet : enemyBullets){
            if(ebullet.notValid()){
                continue;
            }

            if(heroAircraft.crash(ebullet) || ebullet.crash(heroAircraft)){
                MyMusic.playBulletHitMusic();
                heroAircraft.decreaseHp(ebullet.getPower());
                ebullet.vanish();
            }
        }
        // 英雄子弹攻击敌机
        for (AbstractBullet bullet : heroBullets) {
            if (bullet.notValid()) {
                continue;
            }
            for (AbstractAircraft enemyAircraft : enemyAircrafts) {
                if (enemyAircraft.notValid()) {
                    // 已被其他子弹击毁的敌机，不再检测
                    // 避免多个子弹重复击毁同一敌机的判定
                    continue;
                }
                if (enemyAircraft.crash(bullet)) {
                    // 敌机撞击到英雄机子弹
                    // 敌机损失一定生命值
                    MyMusic.playBulletHitMusic();
                    enemyAircraft.decreaseHp(bullet.getPower());
                    bullet.vanish();
                    if (enemyAircraft.notValid()) {
                        // TODO 获得分数，产生道具补给
//                        if(enemyAircraft instanceof EliteEnemy){
//                            EliteEnemy eliteEnemy = (EliteEnemy) enemyAircraft;
                        AbstractProp abstractProp = enemyAircraft.createProp();
                        if(abstractProp != null){
                            props.add(enemyAircraft.createProp());
                        }
//                        }
                        score += 10;
                        flag += 10;
                    }
                }
                // 英雄机 与 敌机 相撞，均损毁
                if (enemyAircraft.crash(heroAircraft) || heroAircraft.crash(enemyAircraft)) {
                    enemyAircraft.vanish();
                    heroAircraft.decreaseHp(Integer.MAX_VALUE);
                }
            }
        }

        // Todo: 我方获得道具，道具生效
        for(AbstractProp abstractProp : props){
            if(abstractProp.notValid()){
                continue;
            }
            if(abstractProp.crash(heroAircraft) || heroAircraft.crash(abstractProp)){
                if(abstractProp instanceof BombProp){
                    MyMusic.playBombMusic();
                }else{
                    MyMusic.playGetSupplyMusic();
                }
                abstractProp.func();
                abstractProp.vanish();
            }
        }
    }

    /**
     * 后处理：
     * 1. 删除无效的子弹
     * 2. 删除无效的敌机
     * 3. 检查英雄机生存
     * <p>
     * 无效的原因可能是撞击或者飞出边界
     */
    private void postProcessAction() {
        enemyBullets.removeIf(AbstractFlyingObject::notValid);
        heroBullets.removeIf(AbstractFlyingObject::notValid);
        enemyAircrafts.removeIf(AbstractFlyingObject::notValid);
        props.removeIf(AbstractFlyingObject::notValid);
    }


    //***********************
    //      Paint 各部分
    //***********************

    /**
     * 重写paint方法
     * 通过重复调用paint方法，实现游戏动画
     *
     * @param  g
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // 绘制背景,图片滚动
        g.drawImage(BACKGROUND_IMAGE, 0, this.backGroundTop - Main.WINDOW_HEIGHT, null);
        g.drawImage(BACKGROUND_IMAGE, 0, this.backGroundTop, null);
        this.backGroundTop += 1;
        if (this.backGroundTop == Main.WINDOW_HEIGHT) {
            this.backGroundTop = 0;
        }

        // 先绘制子弹，后绘制飞机
        // 这样子弹显示在飞机的下层
        paintImageWithPositionRevised(g, enemyBullets);
        paintImageWithPositionRevised(g, heroBullets);

        paintImageWithPositionRevised(g, enemyAircrafts);
        //绘制道具
        paintImageWithPositionRevised(g,props);

        g.drawImage(ImageManager.HERO_IMAGE, heroAircraft.getLocationX() - ImageManager.HERO_IMAGE.getWidth() / 2,
                heroAircraft.getLocationY() - ImageManager.HERO_IMAGE.getHeight() / 2, null);

        //绘制得分和生命值
        paintScoreAndLife(g);

    }

    private void paintImageWithPositionRevised(Graphics g, List<? extends AbstractFlyingObject> objects) {
        if (objects.size() == 0) {
            return;
        }

        for (AbstractFlyingObject object : objects) {
            BufferedImage image = object.getImage();
            assert image != null : objects.getClass().getName() + " has no image! ";
            g.drawImage(image, object.getLocationX() - image.getWidth() / 2,
                    object.getLocationY() - image.getHeight() / 2, null);
        }
    }

    private void paintScoreAndLife(Graphics g) {
        int x = 10;
        int y = 25;
        g.setColor(new Color(16711680));
        g.setFont(new Font("SansSerif", Font.BOLD, 22));
        g.drawString("SCORE:" + this.score, x, y);
        y = y + 20;
        g.drawString("LIFE:" + this.heroAircraft.getHp(), x, y);
    }

    /**
    * Description:判断分数添加Boss敌机
    * date: 2022/3/30 18:01
    * @author: fyd
    */
    private void addBossAction(){
        if(flag >= 100){
            for (AbstractAircraft enemyAircraft : enemyAircrafts) {
                if(enemyAircraft instanceof BossEnemy){
                    return ;
                }
            }
            if(MyMusic.getBossBgmMusic() != null){
                Main.getThreadPoolExecutor().remove(MyMusic.getBgmMusic());
            }
            MyMusic.setBossBgmMusic(new MusicThread(MyMusic.BOSS_BGM,true));
            Main.getThreadPoolExecutor().execute(MyMusic.getBossBgmMusic());
            hasBossEnemy = true;
            MyMusic.getBgmMusic().setInterrupted(true);
            AbstractPlaneFactory planeFactory = new BossEnemyFactory();
            AbstractAircraft aircraft = planeFactory.createAircraft();
            bossEnemy = (BossEnemy) aircraft;
            enemyAircrafts.add(aircraft);
            flag = 0;
        }
    }
    /**
    * Description:游戏结束后添加数据的方法
    * date: 2022/4/14 11:01
    * @author: fyd
    */
    private void recordProcessing(){
        String userName = JOptionPane.showInputDialog("Please input your name.");
        if(userName != ""){
            MyRecord myRecord = new MyRecord(userName, score, Calendar.getInstance());
            recordDaoImpl = new RecordDaoImpl(new File(recordFile));
            recordDaoImpl.addRecord(myRecord);
            recordDaoImpl.showRecords();
        }
    }

    public void turnDownBossmusic(){
        if(hasBossEnemy){
            if(bossEnemy.notValid()){
                hasBossEnemy = false;
                MyMusic.getBossBgmMusic().setInterrupted(true);
                Main.getThreadPoolExecutor().remove(MyMusic.getBgmMusic());
                MyMusic.setBgmMusic(new MusicThread(MyMusic.BACKGROUND_MUSIC,true));
                Main.getThreadPoolExecutor().execute(MyMusic.getBgmMusic());
            }
        }
    }

}
