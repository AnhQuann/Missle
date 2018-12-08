package base.game;

import base.Background;
import base.GameObject;
import base.enemy.Enemy;
import base.enemy.EnemyBullet;
import base.enemy.EnemySummoner;
import base.player.Player;
import base.player.PlayerBullet;
import base.scene.MenuScene;
import base.scene.Scene;
import base.scene.SceneManager;
import base.scene.SceneStage1;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GameCanvas extends JPanel {

    public GameCanvas() {
        SceneManager.signNewScene(new MenuScene());
    }

    public void gameLoop() {
        int delay = 1000 / 60;
        long lastRun = 0;
        while(true) {
            long currentTime = System.currentTimeMillis();
            if(currentTime - lastRun > delay) {
                this.runAll();
                this.renderAll();
                lastRun = currentTime;
            }
        }
    }

    public void runAll() {
//        for(GameObject gameObject : GameObject.gameObjects) {
        for(int i = 0; i < GameObject.gameObjects.size(); i++) {
            GameObject gameObject = GameObject.gameObjects.get(i);
            if(gameObject.isActive) {
                gameObject.run();
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
//        for(GameObject gameObject : GameObject.gameObjects) {
//        Graphics2D g2d = (Graphics2D) g;
        g.fillRect(0, 0, Settings.SCREEN_WIDTH, Settings.SCREEN_HEIGHT);
        for(int i = 0; i < GameObject.gameObjects.size(); i++) {
            GameObject gameObject = GameObject.gameObjects.get(i);
            if(gameObject.isActive) {
                gameObject.render(g);
            }
        }
    }

    public void renderAll() {
        this.repaint();
    }
}
