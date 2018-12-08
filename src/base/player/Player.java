package base.player;

import base.Background;
import base.FrameCounter;
import base.GameObject;
import base.KeyEventPress;
import base.action.Action;
import base.enemy.EnemyExplosion;
import base.physics.BoxCollider;
import base.physics.Physics;
import base.renderer.AnimationRenderer;
import base.scene.GameOverScene;
import base.scene.SceneManager;
import tklibs.SpriteUtils;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Player extends GameObject implements Physics {
    Action action;
    int hp;
    boolean immune;
    FrameCounter immuneCouter;
    BoxCollider boxCollider;

    public Player() {
        super();
        this.createRenderer();
        this.position.set(200, 300);
        this.action = new ActionFire(3);
        this.hp = 993;
        this.immuneCouter = new FrameCounter(30);
        this.immune = false;
        this.boxCollider = new BoxCollider(this.position, 32, 48);
    }

    private void createRenderer() {
        //ArrayList<BufferedImage> images
        ArrayList<BufferedImage> images = new ArrayList<>();
        images.add(SpriteUtils.loadImage("assets/images/players/straight/0.png"));
        images.add(SpriteUtils.loadImage("assets/images/players/straight/1.png"));
        images.add(SpriteUtils.loadImage("assets/images/players/straight/2.png"));
        images.add(SpriteUtils.loadImage("assets/images/players/straight/3.png"));
        images.add(SpriteUtils.loadImage("assets/images/players/straight/4.png"));
        images.add(SpriteUtils.loadImage("assets/images/players/straight/5.png"));
        images.add(SpriteUtils.loadImage("assets/images/players/straight/6.png"));
        //AnimationRenderer(images)
        this.renderer = new AnimationRenderer(images);
    }

    public void takeDamage(int damage) {
        if(this.immune)
            return;
        this.hp -= damage;
        if(this.hp <= 0) {
            this.hp = 0;
            this.destroy();
        } else {
            this.immune = true;
            this.immuneCouter.reset();
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        EnemyExplosion explosion = GameObject.recycle(EnemyExplosion.class);
        explosion.position.set(this.position);
        SceneManager.signNewScene(new GameOverScene());
    }

    @Override
    public void reset() {
        super.reset();
        this.velocity.set(3, 0);
        this.immune = false;
        this.immuneCouter.reset();
        this.hp = 3;
    }

    @Override
    public void run() {
        this.move(); //change velocity
        this.action.run(this);
        super.run(); //this.position.addThis(this.velocity)
    }

    private void move() {
        int vx = 0;
        int vy = 0;
        if(KeyEventPress.isUpPress) {
            vy -= 5;
        }
        if(KeyEventPress.isDownPress) {
            vy += 5;
        }
        if(KeyEventPress.isLeftPress) {
                vx -= 5;
        }
        if(KeyEventPress.isRightPress) {
          vx += 5;
        }
        Background background = null;
        for (GameObject GO: gameObjects) {
            if (GO instanceof Background)
                background = (Background) GO;
        }
        background.velocity.set(-vx, -vy);
          this.velocity.set(vx, vy);
    }

    @Override
    public BoxCollider getBoxCollider() {
        return this.boxCollider;
    }
}
