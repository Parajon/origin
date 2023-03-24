package com.mygdx.game;

import com.badlogic.gdx.math.MathUtils;

public class Nasecomie {
    float x,y;
    float width;
    float height;
    float vy;
    EasyTimer easyTimer;
    //MyGdxGame2 myGdxGame2;
    public Nasecomie (float width, float height) {
        vy = -1;
        x = MathUtils.random(0, width);
        y = height;
        this.height = height;
        this.width = width;
    }
    void fly () {
        y += vy;
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
    public void restart() {
        this.x = MathUtils.random(0, width);
        this.y = height;
    }
}
