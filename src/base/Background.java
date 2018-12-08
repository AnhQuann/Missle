package base;

import base.game.Settings;
import base.player.Player;
import base.renderer.SingleImageRenderer;
import tklibs.SpriteUtils;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Background extends GameObject {
    public Background() {
        super();
        BufferedImage image = SpriteUtils.loadImage(
                "assets/images/background/bg.png"
        );
        this.renderer = new SingleImageRenderer(image);
        this.position.set(0
                , Settings.SCREEN_HEIGHT - image.getHeight());
        this.velocity.set(0, 0);
        this.anchor.set(0, 0);
        this.velocity.set(0, 5);
    }

    @Override
    public void run() {
        super.run();

        if (this.position.x < - Settings.SCREEN_WIDTH) {
            this.position.set(0, this.position.y);
        }

        if (this.position.x > 0) {
            this.position.set(- Settings.SCREEN_WIDTH, this.position.y);
        }

        if (this.position.y < Settings.SCREEN_HEIGHT - Settings.BACKGROUND_HEIGHT) {
            this.position.set(this.position.x, 0);
        }

        if (this.position.y > 0) {
            this.position.set(this.position.x, Settings.SCREEN_HEIGHT - Settings.BACKGROUND_HEIGHT);
        }
    }
}
