package base;

import base.game.Settings;
import base.renderer.SingleImageRenderer;
import base.scene.SceneManager;
import base.scene.SceneStage1;

public class CoverGameOverScene extends GameObject {
    public CoverGameOverScene() {
        this.renderer = new SingleImageRenderer("assets/images/background/gameOver.png");
        this.position.set(Settings.SCREEN_WIDTH/2, Settings.SCREEN_HEIGHT/2);
    }

    @Override
    public void run() {
        super.run();
        if (KeyEventPress.isAnyKeyPress) {
            SceneManager.signNewScene(new SceneStage1());
        }
    }
}
