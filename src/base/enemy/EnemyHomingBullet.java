package base.enemy;

import base.GameObject;
import base.Vector2D;
import base.physics.Physics;
import base.player.Player;

public class EnemyHomingBullet extends EnemyBullet implements Physics {
    public EnemyHomingBullet(){
        super();
    }
    private void hitEachOther() {
        EnemyHomingBullet enemyHomingBullet = null;
        for (GameObject gameObject : GameObject.gameObjects) {

            if (gameObject instanceof EnemyHomingBullet && gameObject != this &&gameObject.isActive
                    && ((EnemyHomingBullet) gameObject).getBoxCollider().intersects(this.boxCollider)){
                enemyHomingBullet = (EnemyHomingBullet) gameObject;
                break;
            }
        }
        if (enemyHomingBullet != null) {
            EnemyExplosion explosion = GameObject.recycle(EnemyExplosion.class);
            explosion.position.set(this.position);
            enemyHomingBullet.destroy();
            this.destroy();
        }
    }

    private void moveToPlayer() {
        Player player = null;
        for (GameObject gameObject : GameObject.gameObjects) {
            if(gameObject.isActive && gameObject instanceof Player) {
                player = (Player) gameObject;
                break;
            }
        }
        if(player != null) {
            Vector2D playerPosition = player.position;
            Vector2D result = playerPosition.substract(this.position);
            result.setLength(0.5f);
            result.addThis(this.velocity);
            result.setLength(5);
            this.velocity.set(result);
        }
    }

    @Override
    public void run() {
        super.run();
        this.hitEachOther();
        this.moveToPlayer();
    }
}
