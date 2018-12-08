package base.enemy;

import base.GameObject;
import base.action.*;
import base.physics.BoxCollider;
import base.physics.Physics;
import base.renderer.BoxRenderer;

import java.awt.*;

public class EnemySummoner extends GameObject implements Physics {
    BoxCollider boxCollider;
    Action action;

    public EnemySummoner() {
        this.boxCollider = new BoxCollider(this.position
                , 40, 100);
        this.renderer = new BoxRenderer(this.boxCollider
                , Color.WHITE, true);
        this.position.set(20, 100);
        this.action = this.createAction();
    }

    private Action createAction() {
        Action wait = new ActionWait(50);
        Action summon = new ActionSummonEnemy();
        Action sequence = new ActionSequence(summon,wait);
        Action repeat = new ActionRepeat(sequence);
        return repeat;
    }

    @Override
    public void run() {
        super.run();
        this.action.run(this);
    }

    @Override
    public BoxCollider getBoxCollider() {
        return this.boxCollider;
    }
}
