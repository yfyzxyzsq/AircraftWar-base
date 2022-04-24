package edu.hitsz.music;

import edu.hitsz.application.Game;
import edu.hitsz.application.Main;

public class MyMusic {

    private boolean playMusic = Main.isMusicFlag();
    public static final String BACKGROUND_MUSIC = "src/videos/bgm.wav";
    public static final String BOSS_BGM = "src/videos/bgm_boss.wav";
    public static final String BOMB_MUSIC = "src/videos/bomb_explosion.wav";
    public static final String BULLET_MUSIC = "src/videos/bullet.wav";
    public static final String BULLET_HIT_MUSIC = "src/videos/bullet_hit.wav";
    public static final String GAME_OVER_MUSIC = "src/videos/game_over.wav";
    public static final String GET_SUPPLY_MUSIC = "src/videos/get_supply.wav";

    public static MusicThread getBossBgmMusic() {
        return bossBgmMusic;
    }

    public static void setBossBgmMusic(MusicThread bossBgmMusic) {
        MyMusic.bossBgmMusic = bossBgmMusic;
    }

    public static MusicThread getBgmMusic() {
        return bgmMusic;
    }

    public static void setBgmMusic(MusicThread bgmMusic) {
        MyMusic.bgmMusic = bgmMusic;
    }

    private static MusicThread bgmMusic = new MusicThread(BACKGROUND_MUSIC,true);

    public static MusicThread getGameOverMusic() {
        return gameOverMusic;
    }

    private static MusicThread gameOverMusic = new MusicThread(GAME_OVER_MUSIC,false);
    private static MusicThread bossBgmMusic = null;
    private static MusicThread bombMusic = new MusicThread(BOMB_MUSIC,false);
    private static MusicThread supplyMusic = new MusicThread(GET_SUPPLY_MUSIC,false);
    private static MusicThread bulletMusic = new MusicThread(BULLET_MUSIC,false);
    private static MusicThread bulletHitMusic = new MusicThread(BULLET_HIT_MUSIC,false);

    public static void playBgmMusic(){
        if(!Game.isGameOver()){
            Main.getThreadPoolExecutor().execute(bgmMusic);
        }
    }

    public static void interruptAll(){
        bgmMusic.setInterrupted(true);
        if(bossBgmMusic != null && bossBgmMusic.isAlive()){
            bossBgmMusic.setInterrupted(true);
        }
//        bombMusic.setInterrupted(true);
//        supplyMusic.setInterrupted(true);
//        bulletHitMusic.setInterrupted(true);
//        bulletMusic.setInterrupted(true);
    }

    public static void playBulletMusic(){
        Main.getThreadPoolExecutor().execute(bulletMusic);
    }

    public static void playBulletHitMusic(){
        Main.getThreadPoolExecutor().execute(bulletHitMusic);
    }
    public static void playBombMusic(){
        Main.getThreadPoolExecutor().execute(bombMusic);
    }
    public static void playGetSupplyMusic(){
        Main.getThreadPoolExecutor().execute(supplyMusic);
    }

    public static void playGameOverMusic(){
        Main.getThreadPoolExecutor().execute(gameOverMusic);
    }
}
