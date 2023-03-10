package com.mygdx.game;

import com.badlogic.gdx.math.MathUtils;

public class Nasecomie {
    float x,y;
    float vy;
    EasyTimer easyTimer;
    //MyGdxGame2 myGdxGame2;
    public Nasecomie (float width, float height) {
        vy = -1;
        x = MathUtils.random(0, width);
        y = height;
    }
    void fly () {
        y += vy;
//        easyTimer = myGdxGame2.getEasyTimer();
//        if (easyTimer.timerDelay(15)) {
//            vy -= 4;
//        }
//        if (easyTimer.timerDelay(20)) {
//            vy -= 6;
//        }
    }

    public float getX() {
        return x;
    }
    public float getY() {
        return y;
    }

    public void setVy(float vy) {
        this.vy -= vy;
    }
}
