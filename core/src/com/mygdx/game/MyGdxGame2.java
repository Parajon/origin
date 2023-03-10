package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.Random;

/* TODO:
3. Придумать падающие предметы
4. Сделать физику предметам
5. Сделать уровни
6. Сделать магазин
7. Сделать заставку
9. Сделать таблицу рекордов
10. Очки на полу
11. бонусы с предметами
12. сделать жизни для
13. сделать хитбокс мин. размер: 280x90; макс. размер: 250x200;
 */
public class MyGdxGame2 extends ApplicationAdapter {
    SpriteBatch batch;
    Texture img;
    Texture imgBackGroundTrava; // фон
    Texture[] imgBackGroundNasecomoe;
    EasyTimer timer;
    float scrWidth;
    float scrHeight;
    float x = 0;
    float y = 0;
    Nasecomie nasecomie;
    EasyTimer easyTimer;

    Texture[] imgChelovecPravo;
    Texture[] imgChelovecLevo;
    Texture imgChelovecStoitPravo;
    Texture imgChelovecStoitLevo;
    Texture imgJump;

    boolean levo = false;
    boolean pravo = false;
    boolean stoitpravo = true;
    boolean stoitlevo = false;
    boolean jump = false;
    boolean jumpUp = false;

    int kadr = 0;

    @Override
    public void create() {
        batch = new SpriteBatch(); // создаём объект, отвечающий за вывод изображений
        img = new Texture("Ded1.png");
        imgChelovecStoitPravo = new Texture("Chelovek_Stoit_pravo.png");
        imgChelovecStoitLevo = new Texture("Chelovek_Stoit_levo.png");
        // TODO по аналогии создать imgJump = new Textere ("")

        imgChelovecPravo = new Texture[]{
                new Texture("Chelovek_Begit_pravo1.png"),
                new Texture("Chelovek_Begit_pravo2.png"),
                new Texture("Chelovek_Begit_pravo3.png"),
                new Texture("Chelovek_Begit_pravo4.png"),
                new Texture("Chelovek_Begit_pravo5.png"),
                new Texture("Chelovek_Begit_pravo6.png"),
                new Texture("Chelovek_Begit_pravo7.png"),
                new Texture("Chelovek_Begit_pravo8.png")
        };
        imgChelovecLevo = new Texture[]{
                new Texture("Chelovek_Begit_levo1.png"),
                new Texture("Chelovek_Begit_levo2.png"),
                new Texture("Chelovek_Begit_levo3.png"),
                new Texture("Chelovek_Begit_levo4.png"),
                new Texture("Chelovek_Begit_levo5.png"),
                new Texture("Chelovek_Begit_levo6.png"),
                new Texture("Chelovek_Begit_levo7.png"),
                new Texture("Chelovek_Begit_levo8.png")
        };
        imgBackGroundNasecomoe = new Texture[] {
                new Texture("Nasekomoe.jpg")

    };


        imgBackGroundTrava = new Texture("Fon_c_travoi.jpg");
        scrWidth = ScreenUtils.getFrameBufferTexture().getRegionWidth();
        scrHeight = ScreenUtils.getFrameBufferTexture().getRegionHeight();
        easyTimer = new EasyTimer();
        timer = new EasyTimer();
        easyTimer.startTimer();
        timer.startTimer();
        nasecomie = new Nasecomie(scrWidth, scrHeight);
    }

    @Override
    public void render() {
        batch.begin();
        batch.draw(imgBackGroundTrava, 0, 0);

        batch.draw(imgBackGroundNasecomoe[0],nasecomie.getX(), nasecomie.getY());
        nasecomie.fly();

        if (timer.timerDelay(1)) {
            nasecomie.setVy(0.2f);
            timer.startTimer();
        }

//        if(Gdx.input.isKeyPressed(Input.Keys.W) && y < scrHeight - img.getHeight()) {
//            y+=10;
//        }
//        if(Gdx.input.isKeyPressed(Input.Keys.S) && y >= 0) {
//            y-=10;
//        }
        if (Gdx.input.isKeyPressed(Input.Keys.A) && x >= 0) {
            x -= 3;
            levo = true;
            pravo = false;
            stoitpravo = false;
            stoitlevo = false;
        } else if (Gdx.input.isKeyPressed(Input.Keys.D) && x < scrWidth - imgChelovecPravo[kadr].getWidth()) {
            x += 3;
            levo = false;
            pravo = true;
            stoitpravo = false;
            stoitlevo = false;
        } else {
            if (pravo) {
                stoitpravo = true;
                stoitlevo = false;
            } else if (levo) {
                stoitlevo = true;
                stoitpravo = false;
            }
            levo = false;
            pravo = false;
        }

        if (jump) {
            if (y < 80 && jumpUp) {
                y += 5;
            } else if (y > 0) {
                y -= 5;
                jumpUp = false;
            }
            if (y == 0) {
                jump = false;
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            if (pravo) {
                x += 5;
            }
            if (levo) {
                x -= 5;
            }
        }

        ScreenUtils.clear(0.65f, 0.99f, 0f, 0);

        if (levo) { // && !jump && !jumpUp
            batch.draw(imgChelovecLevo[kadr], x, y);
        } else if (pravo) { // && !jump && !jumpUp
            batch.draw(imgChelovecPravo[kadr], x, y);
        } else if (stoitpravo) {
            batch.draw(imgChelovecStoitPravo, x, y);
        } else if (stoitlevo) {
            batch.draw(imgChelovecStoitLevo, x, y);
        }

        /*
         else if (jump || jumpUp) {

            if (pravo) {
             рисуем прыжок вправо
            } else if (levo) {
            рисуем прыжок влево
            }

         }
         */
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            if (easyTimer.timerDelay(0.127)) {
                if (kadr < 7) {
                    kadr++;
                    easyTimer.startTimer();
                } else {
                    kadr = 0;
                    easyTimer.startTimer();
                }
            }
        } else {
            if (easyTimer.timerDelay(0.247)) {
                if (kadr < 7) {
                    kadr++;
                    easyTimer.startTimer();
                } else {
                    kadr = 0;
                    easyTimer.startTimer();
                }
            }
        }
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
    }

    public float getScrHeight() {
        return scrHeight;
    }

    public float getScrWidth() {
        return scrWidth;
    }

    public EasyTimer getEasyTimer() {
        return easyTimer;
    }
}

