package base.enemy;

import base.GameObject;
import base.game.Settings;
import base.physics.BoxCollider;
import base.physics.Physics;
import base.player.Player;
import base.renderer.BoxRenderer;

import java.awt.*;

public class EnemyBullet extends GameObject implements Physics {
    BoxCollider boxCollider;
    int damage;

    public EnemyBullet() {
        super();
        this.boxCollider = new BoxCollider(this.position
                , 16, 16);
//        BufferedImage image = SpriteUtils.loadImage("assets/images/enemies/bullets/blue.png");
//        this.renderer = new SingleImageRenderer(image);
        this.renderer = new BoxRenderer(this.boxCollider
                , Color.green, true);
        this.velocity.set(0, 15);
        this.damage = 3;
    }

    private void hitPlayer() {
        Player player = GameObject.intersects(Player.class, this.boxCollider);
        if(player != null) {
            player.takeDamage(this.damage);
            this.destroy();
        }
    }

    @Override
    public void run() {
        super.run();
        this.hitPlayer();
        this.destroyIfNeeded();
    }

    private void destroyIfNeeded() {
        if(this.position.y < -50 || this.position.y >= Settings.SCREEN_HEIGHT + 50
                || this.position.x < -50 || this.position.x >= Settings.SCREEN_WIDTH + 50) {
            this.destroy();
        }
    }

    @Override
    public BoxCollider getBoxCollider() {
        return this.boxCollider;
    }
}
