package com.ngdroidapp;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import java.util.Vector;

import istanbul.gamelab.ngdroid.base.BaseCanvas;

public class OdevCanvas extends BaseCanvas {
    int GEMI1 = 0, GEMI2 = 1, GEMI3 = 2, GEMI4 = 3;
    int GEMISAYISI = 4;
    Bitmap arkaPlan;
    int arkaPlanX, arkaPlanY;
    Bitmap buttonMenu;
    int platformX[], platformY[];
    Vector<Bitmap> gemiListe;
    Vector<Integer> gemiW, gemiH, gemiX, gemiY;
    Vector<Integer> gemiBilgi;

    //oyuncu gemi parametreleri
    int OGEMI_TUR = 0, OGEMI_FRAME = 1, OGEMI_X = 2, OGEMI_Y = 3, OGEMI_W = 4, OGEMI_H = 5,
            OGEMI_HIZ = 6, OGEMI_SM = 7, OGEMI_YOL = 8, OGEMI_RELOAD = 9, OGEMI_MERMI = 10;

    int menuX, menuY, menuW, menuH;

    Bitmap balon;
    Rect balonKaynak, balonHedef;
    int balonAnimAsamasi, balonAnimHizi, balonAnimMiktar;
    int balonX, balonY;

    //yılan hareketi yapacak resim
    Bitmap resim;
    Rect resimKaynak, resimHedef;
    int resimHiz, resimX, resimY, resimSwitch;

    //
    Bitmap solucan;
    Rect solucanKaynak, solucanHedef;
    int solucanSayac, solucanHizi;

    Vector<Integer> gemi;
    Bitmap gemiResimleri[];

    int frameBaslangic[], frameSon[];


    public OdevCanvas(NgApp ngApp) {
        super(ngApp);
    }
    public void setup() {
        arkaPlanYukle();
        gemiYukle();
        //buttonMenuYukle();
        balonYukle();
        resimYukle();
        solucanYukle();
        gemi2Yukle();
    }

    private void gemi2Yukle() {
        gemi = new Vector<Integer>();
        gemi.add(0);    //tur
        gemi.add(0);    //frame
        gemi.add(100);    //x
        gemi.add(100);    //y
        gemi.add(0);    //w
        gemi.add(0);    //h
        gemi.add(0);    //hız
        gemi.add(0);    //sm

        gemiResimleri = new Bitmap[36];
        for (int gemi = 0; gemi < 25; gemi++) {
            gemiResimleri[gemi] = loadImage("animasyon/ship_1_" + (gemi + 1) + ".png");
        }
        for(int gemi = 25; gemi < 36; gemi++) {
            gemiResimleri[gemi] = loadImage("animasyon/ship_1_shot_" + ((gemi % 25) + 1) + ".png");
        }

        frameBaslangic = new int[2];
        frameSon = new int[2];
        frameBaslangic[0] = 0;
        frameSon[0] = 24;
        frameBaslangic[1] = 25;
        frameSon[1] = 35;
    }

    private void solucanYukle() {
        solucan = loadImage("map.png");
        solucanKaynak = new Rect(0,0,solucan.getWidth(), solucan.getHeight());
        solucanHedef = new Rect(0,0, solucan.getWidth(), solucan.getHeight());
        solucanHizi = 10;
        solucanSayac = 0;
    }

    private void resimYukle() {
        resim = loadImage("platform_hp_02.png");
        resimHiz = 20;
        resimX = 50;
        resimY = 960;
        resimSwitch = 0;
        resimKaynak = new Rect(0, 0, resim.getWidth(), resim.getHeight());
        resimHedef = new Rect(resimX, resimY, resimX + resim.getWidth(), resimY + resim.getHeight());
    }

    private void balonYukle() {
        balon = loadImage("carpi.png");
        balonAnimAsamasi = 0;
        balonAnimHizi = 20;
        balonAnimMiktar = 150;
        balonX = 400;
        balonY = 300;
        balonKaynak = new Rect(0,0, balon.getWidth(), balon.getHeight());
        balonHedef = new Rect(balonX ,balonY, balonX + balon.getWidth(), balonY + balon.getHeight());
    }

    private void gemiYukle() {
        gemiListe = new Vector<Bitmap>();
        gemiW = new Vector<Integer>();
        gemiH = new Vector<Integer>();
        gemiX = new Vector<Integer>();
        gemiY = new Vector<Integer>();
        for(int gemi = 0; gemi != GEMISAYISI; ++gemi) {
            gemiListe.add(loadImage("silahli_gemi1.png"));
            gemiW.add(gemiListe.get(gemi).getWidth());
            gemiH.add(gemiListe.get(gemi).getHeight());
        }

        gemiX.add(0);
        gemiX.add(arkaPlan.getWidth() - gemiW.get(GEMI2));
        gemiX.add(0);
        gemiX.add(arkaPlan.getWidth() - gemiW.get(GEMI4));

        gemiY.add(0);
        gemiY.add(0);
        gemiY.add(arkaPlan.getHeight() - gemiH.get(GEMI3));
        gemiY.add(arkaPlan.getHeight() - gemiH.get(GEMI4));
    }

    private void arkaPlanYukle() {
        arkaPlan = loadImage("menu_background2.jpg");
    }

    private void buttonMenuYukle() {
        buttonMenu = loadImage("play_ticked.png");
        //sol altta buton
        menuW = buttonMenu.getWidth();
        menuH = buttonMenu.getHeight();
        menuX = 0;
        menuY = getHeight() - menuH;
    }

    public void update() {
        //balonAnimasyon();
        resimAnimasyon();
    }

    private void resimAnimasyon() {
        if(resimSwitch == 0)
            resimAnimasyon0();
        else
            resimAnimasyon1();
    }

    private void resimAnimasyon1() {
        if(resimHedef.left < getWidth() - resim.getWidth()) {
            resimHedef.left += resimHiz;
            try {
                Thread.sleep(150);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            resimSwitch = 0;
        }
    }

    private void resimAnimasyon0() {
        if(resimHedef.right < getWidth()) {
            resimHedef.right += resimHiz;
            try {
                Thread.sleep(150);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            resimSwitch = 1;
        }
    }

    private void balonAnimasyon() {
        if(balonAnimAsamasi == 0) balonAnimAsamasi0();
        if(balonAnimAsamasi == 1) balonAnimAsamasi1();
    }

    private void balonAnimAsamasi1() {
        if(balonHedef.left < balonX) {
            balonHedef.left += balonAnimHizi;
            balonHedef.right -= balonAnimHizi;
            balonHedef.top += balonAnimHizi;
            balonHedef.bottom -= balonAnimHizi;
        } else
            balonAnimAsamasi = 0;
    }

    private void balonAnimAsamasi0() {
        if(balonHedef.left > balonX - balonAnimMiktar) {
            balonHedef.left -= balonAnimHizi;
            balonHedef.right += balonAnimHizi;
            balonHedef.top -= balonAnimHizi;
            balonHedef.bottom += balonAnimHizi;
        } else
            balonAnimAsamasi = 1;
    }

    public void draw(Canvas canvas) {
        arkaPlanCiz();
        gemiCiz();
        //balonCiz();
        //buttonMenuCiz();
        resimCiz();
        //solucanCiz();
    }

    private void solucanCiz() {
        drawBitmap(solucan, solucanKaynak, solucanHedef);
    }

    private void resimCiz() {
        drawBitmap(resim, resimKaynak, resimHedef);
    }

    private void balonCiz() {
        drawBitmap(balon, balonKaynak, balonHedef);
    }

    private void gemiCiz() {
        drawBitmap(gemiResimleri[gemi.get(0)], gemi.get(1), gemi.get(2));
        //frame + 1
        if(gemi.get(0) < frameSon[gemi.get(7)]) {
            gemi.set(0, gemi.get(0) + 1);

        } else {
            //mermi üret
            gemi.set(0, frameBaslangic[gemi.get(7)]);
        }
    }

    private void arkaPlanCiz() {
        drawBitmap(arkaPlan);
        arkaPlanX = arkaPlan.getWidth();
        arkaPlanY = arkaPlan.getHeight();

    }

    private void buttonMenuCiz() {
        drawBitmap(buttonMenu, menuX, menuY);
    }

    public void keyPressed(int key) {

    }

    public void keyReleased(int key) {

    }

    public boolean backPressed() {
        return false;
    }

    public void touchDown(int x, int y, int id) {
        //odevButtonTikla(x, y);
        zoomIslemi(x, y);

        gemi.set(0, frameBaslangic[1]);
        gemi.set(7, 1);
    }

    private void odevButtonTikla(int x, int y) {
        if(x > menuX && x < menuX + menuW &&
                y > menuY && y < menuY + menuH) {
            root.canvasManager.setCurrentCanvas(new MenuCanvas(root));
        }
    }

    public void touchMove(int x, int y, int id) {
        balonHedef.top = y - 100;
        balonHedef.left = x - 100;
        balonHedef.bottom = y + 100;
        balonHedef.right = x + 100;

        zoomIslemi(x, y);
    }

    private void zoomIslemi(int x, int y) {
        solucanKaynak.set(x - 90, y - 160, x + 90, y + 160);
        solucanHedef.set(0, 0, getWidth(), getHeight());
    }

    public void touchUp(int x, int y, int id) {
        //zoomlama bitiş
        solucanKaynak.set(0, 0, solucan.getWidth(), solucan.getHeight());


    }

    public void surfaceChanged(int width, int height) {

    }

    public void surfaceCreated() {

    }

    public void surfaceDestroyed() {

    }

    public void pause() {

    }

    public void resume() {

    }

    public void reloadTextures() {

    }

    public void showNotify() {

    }

    public void hideNotify() {

    }
}
