package com.ngdroidapp;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import java.util.Random;
import java.util.Vector;

import istanbul.gamelab.ngdroid.base.BaseCanvas;
import istanbul.gamelab.ngdroid.util.Log;

/**
 * Created by noyan on 27.06.2016.
 * Nitra Games Ltd.
 */

public class MenuCanvas extends BaseCanvas {


    Bitmap arkaPlan;
    //oyna butonu
    Bitmap oynaButon;
    int oynaButonX, oynaButonY, oynaButonW, oynaButonH;
    Bitmap buttonOdev;
    int odevX, odevY, odevW, odevH;
    Rect oynaButonKaynak, oynaButonHedef;

    //gezen gemi
    Bitmap gemi[];
    int gemiX, gemiY, gemiW, gemiH;
    int gemiHiz;
    int gemiFrame;

    //etiketler
    int GEZENGEMI_FRAME = 0, GEZENGEMI_X = 1, GEZENGEMI_Y = 2, GEZENGEMI_W = 3, GEZENGEMI_H = 4;
    int GEZENGEMI_HIZ = 5;

    Vector<Integer> gezenGemi;
    //rastegele sayi üretimini sağlar
    Random rastgeleSayi;

    //ayar butonu
    Bitmap ayarButon[];
    int ayarButonX, ayarButonY, ayarButonW, ayarButonH, ayarButonDurumu;
    Rect ayarButonKaynak, ayarButonHedef;
    boolean ayarMenusuAcik;

    //müzik butonu
    Bitmap muzikButon[];
    int muzikButonX, muzikButonY, muzikButonW, muzikButonH;
    Rect muzikButonKaynak, muzikButonHedef;

    //ses butonu
    Bitmap sesButon[];
    int sesButonX, sesButonY, sesButonW, sesButonH;
    Rect sesButonKaynak, sesButonHedef;


    public MenuCanvas(NgApp ngApp) {
        super(ngApp);
    }

    public void setup() {
        arkaPlanYukle();
        oynaButonYukle();
        rastgeleSayi = new Random();
        gemiYukle();
        odevButtonYukle();
        ayarButonYukle();
        muzikButonYukle();
        sesButonYukle();
        root.menuMuzikCal(true);
    }

    private void sesButonYukle() {
        sesButon = new Bitmap[2];
        sesButon[0] = loadImage("ses_buton.png");
        sesButon[1] = loadImage("sessiz_buton.png");
        sesButonW = sesButon[root.sesButonDurumu].getWidth();
        sesButonH = sesButon[root.sesButonDurumu].getHeight();
        sesButonX = 0;
        sesButonY = getHeight() - sesButonH * 3;
        sesButonKaynak = new Rect(0, 0, sesButonW, sesButonH);
        sesButonHedef = new Rect(sesButonX, sesButonY, sesButonW + sesButonX, sesButonH + sesButonY);
    }

    private void muzikButonYukle() {
        muzikButon = new Bitmap[2];
        muzikButon[0] = loadImage("button/muzik_ac.png");
        muzikButon[1] = loadImage("button/muzik_kapa.png");
        muzikButonW = muzikButon[root.muzikButonDurumu].getWidth();
        muzikButonH = muzikButon[root.muzikButonDurumu].getHeight();
        muzikButonX = 0;
        muzikButonY = getHeight() - muzikButonH * 2;
        muzikButonKaynak = new Rect(0, 0, muzikButonW, muzikButonH);
        muzikButonHedef = new Rect(muzikButonX, muzikButonY, muzikButonX + muzikButonW, muzikButonY + muzikButonH);

    }

    private void ayarButonYukle() {
        ayarMenusuAcik = false;
        ayarButon = new Bitmap[2];
        ayarButonDurumu = 0;
        ayarButon[0] = loadImage("ayarlar_buton.png");
        ayarButon[1] = loadImage("ayarlar_butonu_tiklandi.png");
        ayarButonW = ayarButon[ayarButonDurumu].getWidth();
        ayarButonH = ayarButon[ayarButonDurumu].getHeight();
        ayarButonX = 0;
        ayarButonY = getHeight() - ayarButonH;
        ayarButonKaynak = new Rect(0, 0, ayarButonW, ayarButonH);
        ayarButonHedef = new Rect(ayarButonX, ayarButonY, ayarButonX + ayarButonW, ayarButonH + ayarButonY);

    }

    private void gemiYukle() {
        gemiFrame = 0;
        gemi = new Bitmap[25];
        for(int frame = 0; frame != 25; ++frame) {
            gemi[frame] = loadImage("animasyon/ship_3_" + (frame + 1) + ".png");
        }
        gemiX = rastgeleSayi.nextInt(getWidth() - gemi[0].getWidth());
        gemiY = 0;
        gemiW = gemi[0].getWidth();
        gemiH = gemi[0].getHeight();
        gemiHiz = 10;

        gezenGemi = new Vector<Integer>();
        gezenGemi.add(10);   //frame
        gezenGemi.add(rastgeleSayi.nextInt(getWidth() - gemi[0].getWidth()));   //x koord.
        gezenGemi.add(-gemi[0].getHeight());    //y koord
        gezenGemi.add(gemi[0].getWidth());      //width
        gezenGemi.add(gemi[0].getHeight());     //height
        gezenGemi.add(10);   //hiz
    }

    private void odevButtonYukle() {
        buttonOdev = loadImage("saat.png");
        //sağ üstte bulunsun
        odevW = buttonOdev.getWidth();
        odevH = buttonOdev.getHeight();
        odevX = getWidth() - odevW;
        odevY = 0;
    }

    private void odevButtonCiz() {
        drawBitmap(buttonOdev, odevX, odevY);
    }
    private void oynaButonYukle() {
        oynaButon = loadImage("play.png");
        oynaButonW = oynaButon.getWidth();
        oynaButonH = oynaButon.getHeight();
        oynaButonX = (getWidth() - oynaButonW) / 2;
        oynaButonY = (getHeight() - oynaButonH) / 2;
        oynaButonKaynak = new Rect();
        oynaButonHedef = new Rect();
        oynaButonKaynak.set(0, 0, oynaButonW, oynaButonH);
        oynaButonHedef.set(getWidth() / 2, getHeight() / 2, getWidth() / 2, getHeight() / 2);
    }

    private void arkaPlanYukle() {
        arkaPlan = loadImage("menu_background2.jpg");
    }

    public void update() {
        //alttakileri yoruma alınca canvas oluşturmadığından bir şey yapamıyor
        //siyah ekran çıkıyor
        //GameCanvas mc = new GameCanvas(root);
        //root.canvasManager.setCurrentCanvas(mc);
        arayuzAnimasyonlari();
        gezenGemiHareketi();

    }

    private void gezenGemiHareketi() {
        if(gemiY < getHeight()) {
            gemiY += gemiHiz;
        } else {
            gemiY = 0 - gemiH;
            gemiX = rastgeleSayi.nextInt(getWidth() - gemi[gemiFrame].getWidth());
        }


        //vektörle yapılan gemi hareketi
        if(gezenGemi.get(GEZENGEMI_Y) < getHeight()) {
            gezenGemi.set(GEZENGEMI_Y, gezenGemi.get(GEZENGEMI_Y) + gezenGemi.get(GEZENGEMI_HIZ));
        } else {
            gezenGemi.set(GEZENGEMI_Y, 0 - gezenGemi.get(GEZENGEMI_H));
            gezenGemi.set(GEZENGEMI_X, rastgeleSayi.nextInt(getWidth() - gezenGemi.get(GEZENGEMI_W)));
        }
    }

    private void arayuzAnimasyonlari() {
        oynaButonAnimasyonlari();
    }

    private void oynaButonAnimasyonlari() {
        if(oynaButonHedef.left > oynaButonX) {
            oynaButonHedef.left -= 10;
        }
        if(oynaButonHedef.top > oynaButonY) {
            oynaButonHedef.top -= 10;
        }
        if(oynaButonHedef.right < oynaButonX + oynaButonW) {
            oynaButonHedef.right += 10;
        }
        if(oynaButonHedef.bottom < oynaButonY + oynaButonH) {
            oynaButonHedef.bottom += 10;
        }
    }

    public void draw(Canvas canvas) {
        arkaPlanCiz();
        oynaButonCiz();
        gemiCiz();
        yeniGemiCiz();
        odevButtonCiz();
        ayarButonCiz();
        muzikButonCiz();
        sesButonCiz();
    }

    private void sesButonCiz() {
        if(!ayarMenusuAcik) return;
        drawBitmap(sesButon[root.sesButonDurumu], sesButonKaynak, sesButonHedef);
    }

    private void muzikButonCiz() {
        if(ayarMenusuAcik == false)
            return;
        drawBitmap(muzikButon[root.muzikButonDurumu], muzikButonKaynak, muzikButonHedef);
    }

    private void ayarButonCiz() {
        drawBitmap(ayarButon[ayarButonDurumu], ayarButonKaynak, ayarButonHedef);
    }

    private void yeniGemiCiz() {
        drawBitmap(gemi[gezenGemi.get(GEZENGEMI_FRAME)], gezenGemi.get(GEZENGEMI_X), gezenGemi.get(GEZENGEMI_Y));
        if(gezenGemi.get(GEZENGEMI_FRAME) < 24) {
            gezenGemi.set(GEZENGEMI_FRAME, gezenGemi.get(GEZENGEMI_FRAME) + 1);
        } else {
            gezenGemi.set(GEZENGEMI_FRAME, 0);
        }
    }

    private void gemiCiz() {
        drawBitmap(gemi[gemiFrame], gemiX, gemiY);
        if(gemiFrame < 24) {
            ++gemiFrame;
        } else {
            gemiFrame = 0;
        }
    }

    private void oynaButonCiz() {
        drawBitmap(oynaButon, oynaButonKaynak, oynaButonHedef);
    }

    private void arkaPlanCiz() {
        drawBitmap(arkaPlan);
    }

    public void keyPressed(int key) {
    }

    public void keyReleased(int key) {
    }

    public boolean backPressed() {
        return false;
    }

    public void touchDown(int x, int y, int id) {
        oynaButonTikla(x, y);
        odevButtonTikla(x, y);
        butonDurumlari(x, y);
    }

    private void butonDurumlari(int x, int y) {
        //ayar butonu
        if(ayarButonHedef.contains(x, y)) ayarButonDurumu = 1;
        else if(ayarMenusuAcik == false) ayarButonDurumu = 0;

    }

    private void odevButtonTikla(int x, int y) {
        if(x > odevX && x < odevX + odevW &&
                y > odevY && y < odevY + odevH) {
            root.canvasManager.setCurrentCanvas(new OdevCanvas(root));
        }
    }

    private void oynaButonTikla(int x, int y) {
        if(x > oynaButonX && x < oynaButonX + oynaButonW &&
           y > oynaButonY && y < oynaButonY + oynaButonH) {
            root.sesCal(root.SES_BUTON);
            root.menuMuzikCal(false);
            root.canvasManager.setCurrentCanvas(new GameCanvas(root));
        }
    }

    public void touchMove(int x, int y, int id) {
        //oynaButonHedef.right = x;
        //oynaButonHedef.bottom = y;
        butonDurumlari(x, y);
    }

    public void touchUp(int x, int y, int id) {
        ayarButonTikla(x, y);
        if(ayarMenusuAcik == true) {
            muzikButonTikla(x, y);
            sesButonTikla(x, y);
        }
    }

    private void sesButonTikla(int x, int y) {
        if(sesButonHedef.contains(x, y)) {
            root.sesCal(root.SES_BUTON);
            if(root.sesButonDurumu == 0) {
                root.sesButonDurumu = 1;
                root.sesAcik = false;
            } else {
                root.sesButonDurumu = 0;
                root.sesAcik = true;
            }
        }
    }

    private void muzikButonTikla(int x, int y) {
        if(muzikButonHedef.contains(x, y)) {
            root.sesCal(root.SES_BUTON);
            if(root.muzikButonDurumu == 0) {
                root.muzikButonDurumu = 1;
                root.menuMuzikAcik = false;
                root.oyunMuzikAcik = false;
                root.menuMuzikCal(false);
            } else {
                root.muzikButonDurumu = 0;
                root.menuMuzikAcik = true;
                root.oyunMuzikAcik = true;
                root.menuMuzikCal(true);
            }
            //buton seti eklenecek
            //sesler eklenecek
            //müzikleri kapatacağız

        }
    }

    private void ayarButonTikla(int x, int y) {
        if(ayarButonHedef.contains(x, y)) {
            root.sesCal(root.SES_BUTON);
            // ayarButonMenusu
            ayarMenusuAcik = !ayarMenusuAcik;
            if(ayarMenusuAcik == true) {
                ayarButonDurumu = 1;
            } else {
                ayarButonDurumu = 0;
            }
        }
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
