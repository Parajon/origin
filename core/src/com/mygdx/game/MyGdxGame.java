package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.Random;

// TO DO: 1. Остановить игру если финишировать.
public class MyGdxGame extends ApplicationAdapter {
    SpriteBatch batch; // ссылка на объект, отвечающий за вывод изображений
    Texture img; // ссылка на текстуру (картинку)
    Texture imgBackGroundTrava; // фон
    Texture imgBackGroundNoviyYear; // фон
    Texture imgBackGroundWinPeki; // фон
    Texture imgButtonObnovlenie; // фон
    Texture imgBackGroundLose; // фон
    Texture imgButtonKnopka; // фон
    Texture imgBackGroundTrassa; // фон
    public static int scrWidth;
    public static int scrHeight;
    float imgWidth = 150;
    float imgHeight = 150;
    boolean flag = false;
    boolean flagWinPeki = false;
    boolean peca1 = false;
    boolean peca1win = false;
    boolean obnovlenie = false;
    EasyTimer timer;

    String[] imgPeki = {
            "Chebyrek4.png", "Chebyrek6.png", "Cheburek7.png", "Chebyrek8.png", "Cheburek9.png", "Chebyrek10.png"
    };
    String[] imgOleni = {
            "oleni.png", "oleni1.png", "oleni2.png", "oleni3.png"
    };
    String[] imgDed = {
            "Ded1.png", "Ded2.png", "Ded3.png", "Ded4.png"
    };
    String[][] imgIgroki = {
            imgDed, imgOleni
    };
    float[] x = new float[15]; // создание массива
    float[] y = new float[15];
    float[] vx = new float[15];
    float[] vy = new float[15];
    Random random = new Random();
    Peka chebypeka1;
    Peka chebypeka2;
    boolean player = false;
    int kadr = 0;
    Music musicGameOver, musicWin;
    @Override
    public void create() {
        scrWidth = ScreenUtils.getFrameBufferTexture().getRegionWidth();
        scrHeight = ScreenUtils.getFrameBufferTexture().getRegionHeight();
        batch = new SpriteBatch(); // создаём объект, отвечающий за вывод изображений
        imgBackGroundTrava = new Texture("fon2.jpg");
        imgButtonKnopka = new Texture("Knopka.png");
        imgBackGroundNoviyYear = new Texture("novogodni_fon.jpg");
        imgBackGroundLose = new Texture("fon.jpg");
        imgBackGroundWinPeki = new Texture("Kartinka2.jpg");
        imgBackGroundTrassa = new Texture("Trassa_dlya_pek.jpg");
        imgButtonObnovlenie = new Texture("obnovlenie.png");
        musicGameOver = Gdx.audio.newMusic(Gdx.files.internal("GameOver.mp3"));
        musicWin = Gdx.audio.newMusic(Gdx.files.internal("Win.mp3"));
        float determinator = random.nextInt(10) + 1f;
        float determinator2 = random.nextInt(10) + 1f;
        float differ = determinator - determinator2;

        while (Math.abs(differ) < 5) {
            determinator = random.nextInt(10) + 1f;
            determinator2 = random.nextInt(10) + 1f;
            differ = determinator - determinator2;
        }

        chebypeka1 = new Peka(0f, 380f, determinator, 0.5f);
        chebypeka2 = new Peka(0f, 575f, determinator2, 0.5f);

//        chebypeka1.img = new Texture(imgOleni [0]);
//        chebypeka2.img = new Texture(imgDed [0]);
    timer = new EasyTimer();
     timer.startTimer();
        chebypeka1.img = new Texture(imgPeki[random.nextInt(imgPeki.length)]);
        chebypeka2.img = new Texture(imgPeki[random.nextInt(imgPeki.length)]);
    }

    @Override
    public void render() { // повторяется с частотой 60 fps
        ScreenUtils.clear(0.65f, 0.99f, 0f, 0); // очистка экрана (red, green, blue, alpha)
        batch.begin();
        batch.draw(imgBackGroundTrava, 0, 0);
        batch.draw(imgButtonKnopka, 0, 0);
        batch.draw(imgBackGroundTrassa, 0, 350, scrWidth, 400);
        if (!player) {
            batch.draw(imgBackGroundNoviyYear, 500, 280);
            batch.draw (
                    chebypeka1.img, 1000, 300);
            batch.draw (
                    chebypeka2.img, 500, 300);
        }
        if (Gdx.input.justTouched() && obnovlenie){
            float x = Gdx.input.getX();
            float y = Gdx.input.getY();
            if (x >= 0 && x <= imgButtonObnovlenie.getWidth() && y <= 150 && y >= 150 - imgButtonObnovlenie.getHeight()) {
                ScreenUtils.clear(0.65f, 0.99f, 0f, 1);
                obnovlenie = false;
                musicWin.stop();
                musicGameOver.stop();
                chebypeka1.x = 0;
                chebypeka2.x = 0;
                player = false;
                flagWinPeki = false;
                peca1 = false;
                peca1win = false;
            }
        }
      if (obnovlenie){
            batch.draw(
                    imgButtonObnovlenie, 0, scrHeight - imgButtonObnovlenie.getHeight()
            );
         }
        if (Gdx.input.justTouched() && !player) {
            float x = Gdx.input.getX();
            float y = Gdx.input.getY();
            if (x >= 500 && x <= 500 + chebypeka1.img.getWidth() && y <= 450 + chebypeka1.img.getHeight() && y >= 450) {
                peca1 = false;
                player = true;
            }
            else if (x >= scrHeight && x <= scrHeight + chebypeka1.img.getWidth() && y <= 450+chebypeka1.img.getHeight() && y >= 450) {
                peca1 = true;
                player = true;
            }

            System.out.println("выбрали пеку 1 = " + peca1);
        }
        if (Gdx.input.justTouched() && player) {
            float x = Gdx.input.getX();
            float y = Gdx.input.getY();
            if (x >= 0 && x <= imgButtonKnopka.getWidth() && y <= scrHeight && y >= scrHeight - imgButtonKnopka.getHeight()) {
                flag = !flag;
            }
        }
        if (chebypeka1.x > 1920 - chebypeka1.img.getWidth()) {
            flagWinPeki = true;
            obnovlenie = true;
            flag = false;
            peca1win = true;
        }
        if (chebypeka2.x > 1920 - chebypeka2.img.getWidth()) {
            flagWinPeki = true;
            obnovlenie = true;
            flag = false;
            peca1win = false;
        }

        if (flag) {
            for (int i = 0; i < 5; i++) {
                batch.draw(chebypeka1.img, chebypeka1.x, chebypeka1.y, imgWidth, imgHeight);
                batch.draw(chebypeka2.img, chebypeka2.x, chebypeka2.y, imgWidth, imgHeight);
                chebypeka1.move();
                chebypeka2.move();
            }
        } else {
            batch.draw(chebypeka1.img, chebypeka1.x, chebypeka1.y, imgWidth, imgHeight);
            batch.draw(chebypeka2.img, chebypeka2.x, chebypeka2.y, imgWidth, imgHeight);
        }


        if (flagWinPeki && ((peca1 && peca1win) || (!peca1 && !peca1win)) ) {
            musicWin.play();
            batch.draw(imgBackGroundWinPeki,
                    ScreenUtils.getFrameBufferTexture().getRegionWidth() / 2.0f - imgBackGroundWinPeki.getWidth() / 2.0f,
                    ScreenUtils.getFrameBufferTexture().getRegionHeight() / 2.0f - imgBackGroundWinPeki.getHeight() / 2.0f);
        } else if (flagWinPeki) {
            musicGameOver.play();
            batch.draw(imgBackGroundLose,
                    ScreenUtils.getFrameBufferTexture().getRegionWidth() / 2.0f - imgBackGroundLose.getWidth() / 2.0f,
                    ScreenUtils.getFrameBufferTexture().getRegionHeight() / 2.0f - imgBackGroundLose.getHeight() / 2.0f);
        }

        chebypeka1.img = new Texture(imgOleni [kadr]);
        chebypeka2.img = new Texture(imgDed [kadr]);
        if(timer.timerDelay(0.15) && !flagWinPeki && player && flag) {
            if (kadr < 3) {
                kadr++;
                timer.startTimer();
            } else {
                kadr = 0;
                timer.startTimer();
            }
        }

        batch.end();
    }

    //batch.draw(img, x[i], y[i], imgWidth, imgHeight);
    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
        chebypeka2.img.dispose();
        chebypeka1.img.dispose();
        imgBackGroundTrassa.dispose();
        imgButtonObnovlenie.dispose();
        imgBackGroundNoviyYear.dispose();
        imgButtonKnopka.dispose();
        imgBackGroundWinPeki.dispose();
        imgBackGroundLose.dispose();

    }

}

class Peka {
    float x, y;
    float vx, vy;
    Texture img;

    Peka(float x, float y, float vx, float vy) {
        this.x = x;
        this.y = y;
//        this.vx = randomspeed.nextFloat();
        System.out.println("determinator = " + vx);
        this.vx = 2 / vx;
        System.out.println(this.vx);
        this.vy = vy;

    }

    void move() {
        x += vx;
    }
}

