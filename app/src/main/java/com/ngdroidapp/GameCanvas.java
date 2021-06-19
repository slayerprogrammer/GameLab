package com.ngdroidapp;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;

import java.util.Random;
import java.util.Vector;

import istanbul.gamelab.ngdroid.base.BaseCanvas;


/**
 * Created by noyan on 24.06.2016.
 * Nitra Games Ltd.
 */


public class GameCanvas extends BaseCanvas {
    //patlama parametreleri
    int PATLAMA_FRAME = 0, PATLAMA_X = 1, PATLAMA_Y = 2, PATLAMA_W = 3, PATLAMA_H = 4;
    //mermi listesi parametreleri
    int OMERMI_TUR = 0, OMERMI_FRAME = 1, OMERMI_X = 2, OMERMI_Y = 3, OMERMI_W = 4, OMERMI_H = 5, OMERMI_HIZ = 6;
    //düşman mermi
    int DMERMI_TUR = 0, DMERMI_FRAME = 1, DMERMI_X = 2, DMERMI_Y = 3, DMERMI_W = 4, DMERMI_H = 5, DMERMI_HIZ = 6;
    //Gemi çeşitleri
    int SARIGEMI = 0, SIYAHGEMI = 1, YILDIZGEMI = 2, TOPACGEMI = 3;
    //oyuncu platformu etiketleri
    int SOLTARAF = 0, SAGTARAF = 1;
    //oyuncu gemi parametreleri
    int OGEMI_TUR = 0, OGEMI_FRAME = 1, OGEMI_X = 2, OGEMI_Y = 3, OGEMI_W = 4, OGEMI_H = 5,
            OGEMI_HIZ = 6, OGEMI_SM = 7, OGEMI_YOL = 8, OGEMI_RELOAD = 9, OGEMI_MERMI = 10;
    //düşman gemi parametreleri
    int DGEMI_TUR = 0, DGEMI_FRAME = 1, DGEMI_X = 2, DGEMI_Y = 3, DGEMI_W = 4
            , DGEMI_H = 5, DGEMI_HIZ = 6, DGEMI_SM = 7, DGEMI_YOL = 8, DGEMI_RELOAD = 9, DGEMI_MERMI = 10;
    //Arkaplan
    Bitmap arkaPlan;
    int arkaPlanX, arkaPlanY;
    //oyuncu platformu
    Bitmap oyuncuPlatform;
    int oyuncuPlatformX[], oyuncuPlatformY[], oyuncuPlatformW[], oyuncuPlatformH[];
    int yolSayisi;
    //oyuncu gemisi
    Bitmap gemi[][];
    int gemiMaxFrame[], gemiCesitleri;
    int gemiFrame, gemiX, gemiY, gemiHiz;
    //gemi yüklede başlatılıyor
    Vector<Integer> yeniOyuncuGemi;
    //düşman platformu
    Bitmap dusmanPlatform;
    int dusmanPlatformX[], dusmanPlatformY[], dusmanPlatformW[], dusmanPlatformH[];
    int dusmanYolSayisi;
    //düşman gemisi
    Vector<Integer> yeniDusmanGemi;
    Vector<Vector<Integer>> dusmanGemiListesi;
    double dGemiUretBaslangic, dGemiUretAnlik;

    //2D vector
    Vector<Vector<Integer>> oyuncuGemiListesi;
    //random üreteç
    Random random;
    //platform animasyonları ve değişkenleri
    Bitmap platAnim[];
    int platAnimX, platAnimY, platAnimW, platAnimH;
    int platAnimMaxFrame, platAnimAnlikFrame;
    boolean platAnimGoster;

    //gemi seçimleri
    int gemiSecimiX[], gemiSecimiY[];
    int gemiSecimiW, gemiSecimiH;

    //
    int konumX, konumY;

    //geminin hangi yolda olduğunu gösteriyor
    int secilenOyuncuPlatformu, secilenDusmanOyuncuPlatformu;

    //mermi değişkenleri
    Bitmap mermiResimleri[];
    int mermiCesitleri;
    Vector<Integer> yeniOyuncuMermi;
    Vector<Vector<Integer>> oMermiListesi;

    //ayar butonu
    Bitmap ayarButon[];
    int ayarButonX, ayarButonY, ayarButonW, ayarButonH, ayarButonDurumu;
    Rect ayarButonKaynak, ayarButonHedef;
    boolean oyunDevamEdiyor, oyunBitti;

    //pause menü
    Bitmap pauseMenu;
    int pauseMenuX, pauseMenuY, pauseMenuW, pauseMenuH;
    Rect pauseMenuKaynak, pauseMenuHedef;

    //pause menü butonları
    Bitmap menuyeDonButonu[];
    int menuyeDonButonuX, menuyeDonButonuY, menuyeDonButonuW, menuyeDonButonuH, menuyeDonButonuDurumu;
    Rect menuyeDonButonKaynak, menuyeDonButonHedef;

    //müzik butonu
    Bitmap muzikButonu[];
    int muzikButonX, muzikButonY, muzikButonW, muzikButonH;
    Rect muzikButonKaynak, muzikButonHedef;

    //ses butonu
    Bitmap sesButonu[];
    int sesButonX, sesButonY, sesButonW, sesButonH;
    Rect sesButonKaynak, sesButonHedef;

    //restart
    Bitmap yenidenBaslatButon[];
    int yenidenBaslatButonX, yenidenBaslatButonY, yenidenBaslatButonW, yenidenBaslatButonH, yenidenBaslatButonDurum;
    Rect yenidenBaslatButonKaynak, yenidenBaslatButonHedef;

    //devam et
    Bitmap devamEtButon[];
    int devamEtButonX, devamEtButonY, devamEtButonW, devamEtButonH, devamEtButonDurum;
    Rect devamEtButonKaynak, devamEtButonHedef;

    //düşman mermi
    Vector<Integer> yeniDusmanMermisi;
    Vector<Vector<Integer>> dMermiListesi;

    //çarpışma kontrolleri
    Rect kontrol1, kontrol2;

    //patlama efekti
    Bitmap patlamaResmi;
    Rect patlamaKaynak[], patlamaHedef;
    int patlamaMaxFrame;
    Vector<Integer> yeniPatlama;
    Vector<Vector<Integer>> patlamaListesi;

    //puan
    int puan;
    int gemiPuanları[];
    Bitmap puanCerceve;
    int puanCerceveX, puanCerceveY;
    Paint puanRengi;

    //para
    int para;
    Bitmap paraCercevesi;
    int paraCercevesiX, paraCercevesiY;
    Paint paraRengi;

    public GameCanvas(NgApp ngApp) {
        super(ngApp);

    }

    public void setup() {
        LOGI("setup");
        //geminin yukarı hareket etme hızı
        genelDegerler();
        arkaPlanYukle();
        oyuncuPlatformYukle();
        dusmanPlatformYukle();
        gemiYukle();
        platAnimYukle();
        gemiSecimiYukle();
        mermiYukle();
        ayarButonYukle();
        pauseMenuYukle();
        pauseMenuButonlari();
        patlamaYukle();
        puanYukle();
        paraYukle();
        root.oyunMuzikCal(true);
    }

    private void paraYukle() {
        para = 100;
        paraCercevesi = loadImage("coin_icon.png");
        paraCercevesiX = 0;
        paraCercevesiY = 150;
    }

    private void puanYukle() {
        puanCerceve = loadImage("puan_icon.png");
        puanCerceveX = 0;
        puanCerceveY = 0;
        gemiPuanları = new int[gemiCesitleri];
        gemiPuanları[SARIGEMI] = 20;
        gemiPuanları[SIYAHGEMI] = 10;
        gemiPuanları[YILDIZGEMI] = 15;
        gemiPuanları[TOPACGEMI] = 5;
    }

    private void patlamaUret(int konumX, int konumY, int gemiW, int gemiH) {
        yeniPatlama = new Vector<Integer>();
        yeniPatlama.add(0);             //frame
        yeniPatlama.add(konumX);        //x
        yeniPatlama.add(konumY);        //y
        yeniPatlama.add(gemiW);         //w
        yeniPatlama.add(gemiH);         //h

        patlamaListesi.add(yeniPatlama);
    }

    private void patlamaYukle() {
        patlamaResmi = loadImage("explosion.png");
        patlamaMaxFrame = 16;
        patlamaHedef = new Rect();
        patlamaKaynak = new Rect[patlamaMaxFrame];
        for (int frame = 0; frame < patlamaMaxFrame; frame++) {
            patlamaKaynak[frame] = new Rect((frame % 4) * 64, (frame / 4) * 64, ((frame % 4) + 1) * 64, ((frame / 4) + 1) * 64);
        }
        patlamaListesi = new Vector<Vector<Integer>>();

    }

    private void pauseMenuButonlari() {
        //menüye dön butonları
        menuyeDonButonuDurumu = 0;
        menuyeDonButonu = new Bitmap[2];
        menuyeDonButonu[0] = loadImage("pause/menu.png");
        menuyeDonButonu[1] = loadImage("pause/menu_tiklandi.png");
        menuyeDonButonuW = menuyeDonButonu[menuyeDonButonuDurumu].getWidth();
        menuyeDonButonuH = menuyeDonButonu[menuyeDonButonuDurumu].getHeight();
        menuyeDonButonuX = 150;
        menuyeDonButonuY = 770;
        menuyeDonButonKaynak = new Rect(0, 0, menuyeDonButonuW, menuyeDonButonuH);
        menuyeDonButonHedef = new Rect(menuyeDonButonuX, menuyeDonButonuY, menuyeDonButonuX + menuyeDonButonuW, menuyeDonButonuY + menuyeDonButonuH);

        //restart
        yenidenBaslatButonDurum = 0;
        yenidenBaslatButon = new Bitmap[2];
        yenidenBaslatButon[0] = loadImage("pause/oyna.png");
        yenidenBaslatButon[1] = loadImage("pause/oyna_tiklandi.png");
        yenidenBaslatButonW = yenidenBaslatButon[yenidenBaslatButonDurum].getWidth();
        yenidenBaslatButonH = yenidenBaslatButon[yenidenBaslatButonDurum].getHeight();
        yenidenBaslatButonX = 442;
        yenidenBaslatButonY = 770;
        yenidenBaslatButonKaynak = new Rect(0, 0, yenidenBaslatButonW, yenidenBaslatButonH);
        yenidenBaslatButonHedef = new Rect(yenidenBaslatButonX, yenidenBaslatButonY, yenidenBaslatButonX + yenidenBaslatButonW, yenidenBaslatButonY + yenidenBaslatButonH);

        //devam et
        devamEtButonDurum = 0;
        devamEtButon = new Bitmap[2];
        devamEtButon[0] = loadImage("pause/pause_buton.png");
        devamEtButon[1] = loadImage("pause/pausebuton_tiklandi.png");
        devamEtButonW = devamEtButon[devamEtButonDurum].getWidth();
        devamEtButonH = devamEtButon[devamEtButonDurum].getHeight();
        devamEtButonX = 734;
        devamEtButonY = 770;
        devamEtButonKaynak = new Rect(0, 0, devamEtButonW, devamEtButonH);
        devamEtButonHedef = new Rect(devamEtButonX, devamEtButonY, devamEtButonX + devamEtButonW, devamEtButonY + devamEtButonH);

        //müzik butonu
        muzikButonu = new Bitmap[2];
        muzikButonu[0] = loadImage("pause/muzik_ac.png");
        muzikButonu[1] = loadImage("pause/muzik_kapa.png");
        muzikButonW = muzikButonu[0].getWidth();
        muzikButonH = muzikButonu[0].getHeight();
        muzikButonX = 150;
        muzikButonY = 1100;
        muzikButonKaynak = new Rect(0, 0, muzikButonW, muzikButonH);
        muzikButonHedef = new Rect(muzikButonX, muzikButonY, muzikButonX + muzikButonW, muzikButonY + muzikButonH);

        //ses butonu
        sesButonu = new Bitmap[2];
        sesButonu[0] = loadImage("pause/ses_buton.png");
        sesButonu[1] = loadImage("pause/sessiz_buton.png");
        sesButonW = sesButonu[0].getWidth();
        sesButonH = sesButonu[0].getHeight();
        sesButonX = 442;
        sesButonY = 1100;
        sesButonKaynak = new Rect(0, 0, sesButonW, sesButonH);
        sesButonHedef = new Rect(sesButonX, sesButonY, sesButonX + sesButonW, sesButonY + sesButonH);
    }

    private void pauseMenuYukle() {
        pauseMenu = loadImage("pause/pause_ekran.png");
        pauseMenuW = pauseMenu.getWidth();
        pauseMenuH = pauseMenu.getHeight();
        pauseMenuX = (getWidth() - pauseMenuW) / 2;
        pauseMenuY = (getHeight() - pauseMenuH) / 2;
    }

    private void ayarButonYukle() {
        ayarButonDurumu = 0;
        ayarButon = new Bitmap[2];
        ayarButon[0] = loadImage("ayarlar_buton.png");
        ayarButon[1] = loadImage("ayarlar_butonu_tiklandi.png");
        ayarButonW = ayarButon[ayarButonDurumu].getWidth();
        ayarButonH = ayarButon[ayarButonDurumu].getHeight();
        ayarButonX = getWidth() - ayarButonW;
        ayarButonY = 0;
    }

    private void mermiYukle() {
        mermiCesitleri = 2;
        mermiResimleri = new Bitmap[mermiCesitleri];
        mermiResimleri[0] = loadImage("mavi_ates.png");
        mermiResimleri[1] = loadImage("kirmizi_ates.png");
        oMermiListesi = new Vector<Vector<Integer>>();
        dMermiListesi = new Vector<Vector<Integer>>();
    }

    private void oMermiUret(int konumX, int konumY) {
        yeniOyuncuMermi = new Vector<Integer>();
        yeniOyuncuMermi.add(0);     //tür
        yeniOyuncuMermi.add(0);     //frame
        yeniOyuncuMermi.add(konumX);     //x
        yeniOyuncuMermi.add(konumY);     //y
        yeniOyuncuMermi.add(mermiResimleri[0].getWidth());     //w
        yeniOyuncuMermi.add(mermiResimleri[0].getHeight());    //h
        yeniOyuncuMermi.add(-20);     //hız
        oMermiListesi.add(yeniOyuncuMermi);
    }
    private void dMermiUret(int konumX, int konumY) {
        yeniDusmanMermisi = new Vector<Integer>();
        yeniDusmanMermisi.add(1);       //tür
        yeniDusmanMermisi.add(0);       //frame
        yeniDusmanMermisi.add(konumX);       //x
        yeniDusmanMermisi.add(konumY);       //y
        yeniDusmanMermisi.add(mermiResimleri[1].getWidth());       //w
        yeniDusmanMermisi.add(mermiResimleri[1].getHeight());      //h
        yeniDusmanMermisi.add(20);       //hız
        dMermiListesi.add(yeniDusmanMermisi);
    }

    private void gemiSecimiYukle() {
        gemiSecimiX = new int[gemiCesitleri];
        gemiSecimiY = new int[gemiCesitleri];
        gemiSecimiW = 235;
        gemiSecimiH = 1840 - 1650;
        gemiSecimiX[0] = 10 + ((gemiSecimiW - gemi[0][0].getWidth()) / 2);
        gemiSecimiY[0] = 1650 + ((gemiSecimiH - gemi[0][0].getHeight()) / 2);
        gemiSecimiX[1] = 285 + ((gemiSecimiW - gemi[1][0].getWidth()) / 2);
        gemiSecimiY[1] = 1650 + ((gemiSecimiH - gemi[1][0].getHeight()) / 2);
        gemiSecimiX[2] = 550 + ((gemiSecimiW - gemi[2][0].getWidth()) / 2);
        gemiSecimiY[2] = 1650 + ((gemiSecimiH - gemi[2][0].getHeight()) / 2);
        gemiSecimiX[3] = 830 + ((gemiSecimiW - gemi[3][0].getWidth()) / 2);
        gemiSecimiY[3] = 1650 + ((gemiSecimiH - gemi[3][0].getHeight()) / 2);
    }

    private void platAnimYukle() {
        platAnimMaxFrame = 25;
        platAnim = new Bitmap[platAnimMaxFrame];
        platAnimGoster = true;
        for(int frame = 0; frame != platAnimMaxFrame; ++frame) {
            platAnim[frame] = loadImage("animasyon/platform_" + frame + ".png");
        }
        platAnimAnlikFrame = 0;
        platAnimW = platAnim[0].getWidth();
        platAnimH = platAnim[0].getHeight();
        platAnimX = oyuncuPlatformX[SOLTARAF];
        platAnimY = oyuncuPlatformY[SOLTARAF];
    }

    private void genelDegerler() {
        oyunDevamEdiyor = true;
        oyunBitti = false;
        random = new Random();
        kontrol1 = new Rect();
        kontrol2 = new Rect();
        puanRengi = new Paint();
        puanRengi.setTextSize(36);
        puanRengi.setARGB(255, 255, 255, 255);
        puanRengi.setTypeface(Typeface.DEFAULT_BOLD);
        puanRengi.setTextAlign(Paint.Align.CENTER);
    }

    public void update() {
        LOGI("update");
        if(oyunDevamEdiyor == false) {
            return;
        }
        zamanlayicilar();
        gemiHareketi();
        dusmanGemiHareketi();
        dusmanGemiUretimi();
        dusmanGemiSaldiriKontrol();
        oyuncuGemiSaldiriKontrol();
        oyuncuMermiUretimi();
        dusmanMermiUretimi();
        oyuncuMermiHareketi();
        dusmanMermiHareketi();
        carpismaKontrolleri();
    }

    private void carpismaKontrolleri() {
        oyuncuMermisiDusmanaCarptimi();
        dusmanMermisiOyuncuyaCarptimi();
    }

    private void dusmanMermisiOyuncuyaCarptimi() {
        for (int dMermi = 0; dMermi < dMermiListesi.size(); dMermi++) {
            kontrol1.set(dMermiListesi.get(dMermi).get(DMERMI_X),
                    dMermiListesi.get(dMermi).get(DMERMI_Y),
                    dMermiListesi.get(dMermi).get(DMERMI_X) + dMermiListesi.get(dMermi).get(DMERMI_W),
                    dMermiListesi.get(dMermi).get(DMERMI_Y) + dMermiListesi.get(dMermi).get(DMERMI_H));
            for (int oyuncu = 0; oyuncu < oyuncuGemiListesi.size(); oyuncu++) {
                kontrol2.set(oyuncuGemiListesi.get(oyuncu).get(OGEMI_X),
                        oyuncuGemiListesi.get(oyuncu).get(OGEMI_Y),
                        oyuncuGemiListesi.get(oyuncu).get(OGEMI_X) + oyuncuGemiListesi.get(oyuncu).get(OGEMI_W),
                        oyuncuGemiListesi.get(oyuncu).get(OGEMI_Y) + oyuncuGemiListesi.get(oyuncu).get(OGEMI_H));
                if(kontrol1.intersect(kontrol2)) {
                    //patlama efekti yapılacak
                    patlamaUret(oyuncuGemiListesi.get(oyuncu).get(OGEMI_X), oyuncuGemiListesi.get(oyuncu).get(OGEMI_Y),
                                oyuncuGemiListesi.get(oyuncu).get(OGEMI_W), oyuncuGemiListesi.get(oyuncu).get(OGEMI_H));
                    //patlama sesi üretilecek
                    //düşman mermisi yok olacak
                    //oyuncu gemisi patlayacak
                    dMermiListesi.remove(dMermi);
                    oyuncuGemiListesi.remove(oyuncu);
                    break;
                }
            }
        }
    }

    private void oyuncuMermisiDusmanaCarptimi() {
        for (int oMermi = 0; oMermi < oMermiListesi.size(); oMermi++) {
            kontrol1.set(oMermiListesi.get(oMermi).get(OMERMI_X),
                    oMermiListesi.get(oMermi).get(OMERMI_Y),
                    oMermiListesi.get(oMermi).get(OMERMI_X) + oMermiListesi.get(oMermi).get(OMERMI_W),
                    oMermiListesi.get(oMermi).get(OMERMI_Y) + oMermiListesi.get(oMermi).get(OMERMI_H));
            for (int dusman = 0; dusman < dusmanGemiListesi.size(); dusman++) {
                kontrol2.set(dusmanGemiListesi.get(dusman).get(DGEMI_X),
                        dusmanGemiListesi.get(dusman).get(DGEMI_Y),
                        dusmanGemiListesi.get(dusman).get(DGEMI_X) + dusmanGemiListesi.get(dusman).get(DGEMI_W),
                        dusmanGemiListesi.get(dusman).get(DGEMI_Y) + dusmanGemiListesi.get(dusman).get(DGEMI_H));
                //kontrol aşaması
                if(kontrol1.intersect(kontrol2)) {
                    //patlama efekti yapılacak
                    patlamaUret(dusmanGemiListesi.get(dusman).get(DGEMI_X), dusmanGemiListesi.get(dusman).get(DGEMI_Y),
                            dusmanGemiListesi.get(dusman).get(DGEMI_W), dusmanGemiListesi.get(dusman).get(DGEMI_H));
                    //patlama sesi üretilecek
                    //oyuncu mermisi yok olacak
                    //düşman gemisi patlayacak
                    oMermiListesi.remove(oMermi);
                    puan += gemiPuanları[dusmanGemiListesi.get(dusman).get(DGEMI_TUR)];
                    dusmanGemiListesi.remove(dusman);
                    break;
                }
            }
        }
    }

    private void dusmanMermiHareketi() {
        for (int mermi = 0; mermi < dMermiListesi.size(); mermi++) {
            if(dMermiListesi.get(mermi).get(DMERMI_Y) + dMermiListesi.get(mermi).get(DGEMI_H) < getHeight()) {
                dMermiListesi.get(mermi).set(DMERMI_Y, dMermiListesi.get(mermi).get(DMERMI_Y)
                        + dMermiListesi.get(mermi).get(DMERMI_HIZ));
            } else {
                dMermiListesi.remove(mermi);
            }
        }
    }

    private void dusmanMermiUretimi() {
        for (int gemi = 0; gemi < dusmanGemiListesi.size(); gemi++) {
            if(dusmanGemiListesi.get(gemi).get(DGEMI_RELOAD) > 30 && dusmanGemiListesi.get(gemi).get(DGEMI_SM) == 1) {
                dMermiUret(dusmanGemiListesi.get(gemi).get(DGEMI_X)
                                + (dusmanGemiListesi.get(gemi).get(DGEMI_W)
                                - mermiResimleri[dusmanGemiListesi.get(gemi).get(DGEMI_MERMI)].getWidth()) / 2,
                                dusmanGemiListesi.get(gemi).get(DGEMI_Y)
                                + (dusmanGemiListesi.get(gemi).get(DGEMI_H)
                                - mermiResimleri[dusmanGemiListesi.get(gemi).get(DGEMI_MERMI)].getHeight()) / 2);
                dusmanGemiListesi.get(gemi).set(DGEMI_RELOAD, 0);
            } else {
                dusmanGemiListesi.get(gemi).set(DGEMI_RELOAD, dusmanGemiListesi.get(gemi).get(DGEMI_RELOAD) + 1);
            }
        }
    }

    private void oyuncuMermiHareketi() {
        for (int mermi = 0; mermi < oMermiListesi.size(); mermi++) {
            if(oMermiListesi.get(mermi).get(OMERMI_Y) > 0) {
                oMermiListesi.get(mermi).set(OMERMI_Y, oMermiListesi.get(mermi).get(OMERMI_Y) + oMermiListesi.get(mermi).get(OMERMI_HIZ));
            } else {
                oMermiListesi.remove(mermi);
            }
        }
    }

    private void oyuncuMermiUretimi() {
        for(int ogemi = 0; ogemi < oyuncuGemiListesi.size(); ogemi++) {
            if(oyuncuGemiListesi.get(ogemi).get(OGEMI_SM) == 1) {
                if(oyuncuGemiListesi.get(ogemi).get(OGEMI_RELOAD) > 30) {
                    oMermiUret(oyuncuGemiListesi.get(ogemi).get(OGEMI_X) + (Math.abs(gemi[oyuncuGemiListesi.get(ogemi).get(OGEMI_TUR)][oyuncuGemiListesi.get(ogemi).get(OGEMI_FRAME)].getWidth() - mermiResimleri[oyuncuGemiListesi.get(ogemi).get(OGEMI_MERMI)].getWidth()) / 2),
                                oyuncuGemiListesi.get(ogemi).get(OGEMI_Y) + (Math.abs(gemi[oyuncuGemiListesi.get(ogemi).get(OGEMI_TUR)][oyuncuGemiListesi.get(ogemi).get(OGEMI_FRAME)].getHeight() - mermiResimleri[oyuncuGemiListesi.get(ogemi).get(OGEMI_MERMI)].getHeight()) / 2));
                    oyuncuGemiListesi.get(ogemi).set(OGEMI_RELOAD, 0);
                } else {
                    oyuncuGemiListesi.get(ogemi).set(OGEMI_RELOAD,
                            oyuncuGemiListesi.get(ogemi).get(OGEMI_RELOAD) + 1);
                }
            }
        }
    }

    private void oyuncuGemiSaldiriKontrol() {
        for(int oyuncu = 0; oyuncu != oyuncuGemiListesi.size(); ++oyuncu) {
            oyuncuGemiListesi.get(oyuncu).set(OGEMI_SM, 0);
            for(int dusman = 0; dusman != dusmanGemiListesi.size(); ++dusman) {
                if(Math.abs(oyuncuGemiListesi.get(oyuncu).get(OGEMI_Y)
                        - (dusmanGemiListesi.get(dusman).get(DGEMI_H)
                        +  dusmanGemiListesi.get(dusman).get(DGEMI_Y))) < 400
                        && dusmanGemiListesi.get(dusman).get(DGEMI_YOL)
                        == oyuncuGemiListesi.get(oyuncu).get(OGEMI_YOL)) {
                    oyuncuGemiListesi.get(oyuncu).set(OGEMI_SM, 1);
                    break;
                }
            }
        }
    }

    private void dusmanGemiSaldiriKontrol() {
        for(int dusman = 0; dusman != dusmanGemiListesi.size(); ++dusman) {
            dusmanGemiListesi.get(dusman).set(DGEMI_SM, 0);
            for(int oyuncu = 0; oyuncu != oyuncuGemiListesi.size(); ++oyuncu) {
                if(Math.abs(dusmanGemiListesi.get(dusman).get(DGEMI_H)
                        +  dusmanGemiListesi.get(dusman).get(DGEMI_Y)
                        -  oyuncuGemiListesi.get(oyuncu).get(OGEMI_Y)) < 400
                        && oyuncuGemiListesi.get(oyuncu).get(OGEMI_YOL)
                        == dusmanGemiListesi.get(dusman).get(DGEMI_YOL)) {
                    dusmanGemiListesi.get(dusman).set(DGEMI_SM, 1);
                    break;
                }
            }
        }
    }

    private void zamanlayicilar() {
        dGemiUretAnlik = System.currentTimeMillis();
    }

    private void dusmanGemiUretimi() {
        if(Math.abs(dGemiUretAnlik - dGemiUretBaslangic) > 1500) {
            dusmanGemiUret();
        }
    }

    public void draw(Canvas canvas) {
        LOGI("draw");
        //amacımız logoyu hareket ettiriyosak çizim öncesinde tüm ekranı temizlemek
        canvas.drawColor(Color.BLACK);  // tüm ekranı siyaha boyadı
        //drawBitmap(logo, logox, logoy);
        arkaPlanCiz();
        oyuncuPlatformCiz();
        dusmanPlatformCiz();
        platformAnimasyonCiz();
        gemiListesiCiz();
        oyuncuMermiCiz();
        dusmanMermiCiz();
        oyuncuGemiCiz();
        dusmanGemiCiz();
        patlamaCiz();
        arayuzNesneleriCiz();
        pauseMenuCiz();

        koordinatGostergeleri(konumX, konumY);
    }

    private void patlamaCiz() {
        for (int patlama = 0; patlama < patlamaListesi.size(); patlama++) {
            patlamaHedef.set(patlamaListesi.get(patlama).get(PATLAMA_X), patlamaListesi.get(patlama).get(PATLAMA_Y),
                       patlamaListesi.get(patlama).get(PATLAMA_X) + patlamaListesi.get(patlama).get(PATLAMA_W),
                    patlamaListesi.get(patlama).get(PATLAMA_Y) + patlamaListesi.get(patlama).get(PATLAMA_H));
            drawBitmap(patlamaResmi, patlamaKaynak[patlamaListesi.get(patlama).get(PATLAMA_FRAME)], patlamaHedef);
            //frame + 1
            if(patlamaListesi.get(patlama).get(PATLAMA_FRAME) < patlamaMaxFrame - 1) {
                patlamaListesi.get(patlama).set(PATLAMA_FRAME, patlamaListesi.get(patlama).get(PATLAMA_FRAME) + 1);
            } else {
                patlamaListesi.remove(patlama);
            }
        }
    }

    private void dusmanMermiCiz() {
        for (int mermi = 0; mermi < dMermiListesi.size(); mermi++) {
            drawBitmap(mermiResimleri[dMermiListesi.get(mermi).get(DMERMI_TUR)],
                    dMermiListesi.get(mermi).get(DMERMI_X), dMermiListesi.get(mermi).get(DMERMI_Y));
        }
    }

    private void devamEtButonCiz() {
        drawBitmap(devamEtButon[devamEtButonDurum], devamEtButonKaynak, devamEtButonHedef);
    }

    private void yenidenBaslatButonCiz() {
        drawBitmap(yenidenBaslatButon[yenidenBaslatButonDurum], yenidenBaslatButonKaynak, yenidenBaslatButonHedef);
    }

    private void menuyeDonButonuCiz() {
        drawBitmap(menuyeDonButonu[menuyeDonButonuDurumu], menuyeDonButonKaynak, menuyeDonButonHedef);
    }

    private void sesButonCiz() {
        drawBitmap(sesButonu[root.sesButonDurumu], sesButonKaynak, sesButonHedef);
    }

    private void muzikButonCiz() {
        drawBitmap(muzikButonu[root.muzikButonDurumu], muzikButonKaynak, muzikButonHedef);
    }

    private void pauseMenuCiz() {
        if(oyunDevamEdiyor == false && !oyunBitti) {
            drawBitmap(pauseMenu, pauseMenuX, pauseMenuY);
            menuyeDonButonuCiz();
            yenidenBaslatButonCiz();
            devamEtButonCiz();
            muzikButonCiz();
            sesButonCiz();
        }
    }

    private void arayuzNesneleriCiz() {
        ayarButonCiz();
        puanCiz();
        paraCiz();
    }

    private void paraCiz() {
        drawBitmap(paraCercevesi, paraCercevesiX, paraCercevesiY);
        drawText("" + para, paraCercevesiX + 100, paraCercevesiY + 45);
    }

    private void puanCiz() {
        //puan çercevesi
        drawBitmap(puanCerceve, puanCerceveX, puanCerceveY);
        //puan
        setFont(puanRengi);
        drawText("" + puan, puanCerceveX + 100, puanCerceveY + 40);
    }

    private void ayarButonCiz() {
        drawBitmap(ayarButon[ayarButonDurumu], ayarButonX, ayarButonY);
    }

    private void oyuncuMermiCiz() {
        for (int mermi = 0; mermi < oMermiListesi.size(); mermi++) {
            drawBitmap(mermiResimleri[oMermiListesi.get(mermi).get(OMERMI_TUR)], oMermiListesi.get(mermi).get(OMERMI_X), oMermiListesi.get(mermi).get(OMERMI_Y));
        }
    }

    private void dusmanGemiCiz() {
        for(int dusman = 0; dusman != dusmanGemiListesi.size(); ++dusman) {
            drawBitmap(gemi[dusmanGemiListesi.get(dusman).get(DGEMI_TUR)][dusmanGemiListesi.get(dusman)
                    .get(DGEMI_FRAME)], dusmanGemiListesi.get(dusman).get(DGEMI_X), dusmanGemiListesi.get(dusman)
                    .get(DGEMI_Y));
            //frame + 1
            if(dusmanGemiListesi.get(dusman).get(DGEMI_FRAME) < gemiMaxFrame[dusmanGemiListesi
                    .get(dusman).get(DGEMI_TUR)] - 1) {
                dusmanGemiListesi.get(dusman).set(DGEMI_FRAME, dusmanGemiListesi.get(dusman).get(DGEMI_FRAME) + 1);

            } else {
                dusmanGemiListesi.get(dusman).set(DGEMI_FRAME, 0);
            }
        }
    }

    private void gemiListesiCiz() {
        for(int secim = 0; secim < gemiCesitleri; ++secim) {
            drawBitmap(gemi[secim][0], gemiSecimiX[secim], gemiSecimiY[secim]);
        }
    }

    private void platformAnimasyonCiz() {
        drawBitmap(platAnim[platAnimAnlikFrame], platAnimX, platAnimY);
        //frame + 1
        if(platAnimAnlikFrame < platAnimMaxFrame - 1) {
            ++platAnimAnlikFrame;
        }
    }

    private void dusmanGemiHareketi() {
        for(int sira = 0; sira != dusmanGemiListesi.size(); ++sira) {
            if(dusmanGemiListesi.get(sira).get(DGEMI_Y) < oyuncuPlatformY[SOLTARAF] &&
                    dusmanGemiListesi.get(sira).get(DGEMI_SM) == 0) {
                dusmanGemiListesi.get(sira).set(DGEMI_Y, dusmanGemiListesi.get(sira).get(DGEMI_Y)
                                                    + dusmanGemiListesi.get(sira).get(DGEMI_HIZ));
            } else {
                //dusmanGemiListesi.remove(sira);
            }
        }
    }

    private void dusmanGemiUret() {
        int kazananYol = 0;

        //hangi taraf daha kalabalık
        for(int i = 0; i != oyuncuGemiListesi.size(); ++i) {
            if(oyuncuGemiListesi.get(i).get(OGEMI_YOL) == SOLTARAF) {
                --kazananYol;
            } else {
                ++kazananYol;
            }
        }

        //karar mekanizması
        if(kazananYol > 0) secilenDusmanOyuncuPlatformu = SAGTARAF;
        else if(kazananYol == 0) secilenDusmanOyuncuPlatformu = random.nextInt(dusmanYolSayisi);
        else secilenDusmanOyuncuPlatformu = SOLTARAF;

        yeniDusmanGemi = new Vector<Integer>();
        int tur = random.nextInt(gemiCesitleri);
        yeniDusmanGemi.add(tur);    //tür
        yeniDusmanGemi.add(0);      //frame
        yeniDusmanGemi.add(dusmanPlatformX[secilenDusmanOyuncuPlatformu] + ((dusmanPlatform.getWidth() - gemi[tur][0].getWidth())/2));    //x
        yeniDusmanGemi.add(dusmanPlatformY[secilenDusmanOyuncuPlatformu] + ((dusmanPlatform.getHeight() - gemi[tur][0].getHeight())/2));  //y
        yeniDusmanGemi.add(gemi[tur][0].getWidth());    //w
        yeniDusmanGemi.add(gemi[tur][0].getHeight());  //h
        yeniDusmanGemi.add(10);     //hız
        yeniDusmanGemi.add(0);      //sm
        yeniDusmanGemi.add(secilenDusmanOyuncuPlatformu);  //yol
        yeniDusmanGemi.add(30);     //reload
        yeniDusmanGemi.add(1);      //mermi tipi

        dusmanGemiListesi.add(yeniDusmanGemi);
        dGemiUretBaslangic = System.currentTimeMillis();
        //bayrak
    }

    private void oyuncuPlatformYukle() {
        secilenOyuncuPlatformu = SOLTARAF;
        yolSayisi = 2;
        oyuncuPlatform = loadImage("silahli_gemi1.png");
        //bitmap resmin genişlik ve yüksekliği
        oyuncuPlatformX = new int[yolSayisi];
        oyuncuPlatformY = new int[yolSayisi];
        oyuncuPlatformW = new int[yolSayisi];
        oyuncuPlatformH = new int[yolSayisi];
        //sol platform
        oyuncuPlatformW[SOLTARAF] = oyuncuPlatform.getWidth();
        oyuncuPlatformH[SOLTARAF] = oyuncuPlatform.getHeight();
        //bitmap resmin başlangıç konumları
        oyuncuPlatformX[SOLTARAF] = 140;
        oyuncuPlatformY[SOLTARAF] = 1350;
        //sağ platform
        oyuncuPlatformW[SAGTARAF] = oyuncuPlatform.getWidth();
        oyuncuPlatformH[SAGTARAF] = oyuncuPlatform.getHeight();
        //bitmap resmin başlangıç konumları
        oyuncuPlatformX[SAGTARAF] = 140 + 514;
        oyuncuPlatformY[SAGTARAF] = 1350;
    }

    private void oyuncuGemiUret(int gemiTur) {
        int tur = gemiTur;
        yeniOyuncuGemi = new Vector<Integer>();
        yeniOyuncuGemi.add(tur);                        //bunu tür olarak kullanacağız
        yeniOyuncuGemi.add(0);                          //frame
        yeniOyuncuGemi.add(oyuncuPlatformX[secilenOyuncuPlatformu] + ((oyuncuPlatform.getWidth() - gemi[tur][0].getWidth())/2));  //x
        yeniOyuncuGemi.add(oyuncuPlatformY[secilenOyuncuPlatformu] + ((oyuncuPlatform.getHeight() - gemi[tur][0].getHeight())/2));  //y
        yeniOyuncuGemi.add(gemi[tur][0].getWidth());      //w
        yeniOyuncuGemi.add(gemi[tur][0].getHeight());     //h
        yeniOyuncuGemi.add(-10);                        //hız
        yeniOyuncuGemi.add(0);                          //saldırı modu
        yeniOyuncuGemi.add(secilenOyuncuPlatformu);     //geminin hangi yolda olduğunu gösteriyor
        yeniOyuncuGemi.add(30);                         //mermi reload süresi
        yeniOyuncuGemi.add(0);                          //mermi türü
        //üretilmiş gemi listeye eklendi
        oyuncuGemiListesi.add(yeniOyuncuGemi);


    }

    private void dusmanPlatformYukle() {
        secilenOyuncuPlatformu = SOLTARAF;
        dusmanYolSayisi = 2;
        dusmanPlatform = loadImage("silahli_dusmangemi1.png");
        dusmanPlatformX = new int[dusmanYolSayisi];
        dusmanPlatformY = new int[dusmanYolSayisi];
        dusmanPlatformW = new int[dusmanYolSayisi];
        dusmanPlatformH = new int[dusmanYolSayisi];
        //sol platform
        dusmanPlatformX[SOLTARAF] = 140;
        dusmanPlatformY[SOLTARAF] = 0;
        dusmanPlatformW[SOLTARAF] = dusmanPlatform.getWidth();
        dusmanPlatformH[SOLTARAF] = dusmanPlatform.getHeight();
        //sağ platform
        dusmanPlatformX[SAGTARAF] = 140 + 530;
        dusmanPlatformY[SAGTARAF] = 0;
        dusmanPlatformW[SAGTARAF] = dusmanPlatform.getWidth();
        dusmanPlatformH[SAGTARAF] = dusmanPlatform.getHeight();
    }

    private void gemiYukle() {
        gemiCesitleri = 4;
        gemiMaxFrame = new int[gemiCesitleri];
        gemiMaxFrame[SARIGEMI] = 25;
        gemiMaxFrame[SIYAHGEMI] = 16;
        gemiMaxFrame[YILDIZGEMI] = 25;
        gemiMaxFrame[TOPACGEMI] = 25;
        //gemilerin listesi
        oyuncuGemiListesi = new Vector<Vector<Integer>>();
        gemi = new Bitmap[gemiCesitleri][25];
        for(int gemiCesit = 0; gemiCesit != gemiCesitleri; ++gemiCesit) {
            for(int gemiFrame = 0; gemiFrame != gemiMaxFrame[gemiCesit]; ++gemiFrame) {
                gemi[gemiCesit][gemiFrame] = loadImage("animasyon/ship_" + (gemiCesit + 1)
                                                                    + "_" + (gemiFrame + 1) + ".png");
            }
        }

        gemiFrame = 0;
        gemiHiz = -10;
        //oyuncu gemisi sol platformda
        gemiX = oyuncuPlatformX[SOLTARAF];
        gemiY = oyuncuPlatformY[SOLTARAF];
        //düşman gemisi sağ platformda
        dusmanGemiListesi = new Vector<Vector<Integer>>();
        dGemiUretBaslangic = dGemiUretAnlik = System.currentTimeMillis();
    }

    private void oyuncuPlatformCiz() {
        for(int yol = 0; yol < yolSayisi; ++yol) {
            drawBitmap(oyuncuPlatform, oyuncuPlatformX[yol], oyuncuPlatformY[yol]);
        }
    }

    private void gemiHareketi() {
        for(int sira = 0; sira < oyuncuGemiListesi.size(); ++sira) {
            if(oyuncuGemiListesi.get(sira).get(OGEMI_Y) > dusmanPlatformY[SOLTARAF] && oyuncuGemiListesi.get(sira).get(OGEMI_SM) == 0) {
                oyuncuGemiListesi.get(sira).set(OGEMI_Y, oyuncuGemiListesi.get(sira).get(OGEMI_Y) +
                                                        oyuncuGemiListesi.get(sira).get(OGEMI_HIZ));
            } else {
                //oyuncuGemiListesi.remove(sira);
            }
        }
    }

    private void dusmanPlatformCiz() {
        for(int yol = 0; yol < dusmanYolSayisi; ++yol) {
            drawBitmap(dusmanPlatform, dusmanPlatformX[yol], dusmanPlatformY[yol]);
        }

    }

    private void oyuncuGemiCiz() {
       for(int sira = 0; sira < oyuncuGemiListesi.size(); ++ sira) {
           canvas.save();
           int gx = oyuncuGemiListesi.get(sira).get(OGEMI_X) + oyuncuGemiListesi.get(sira).get(OGEMI_W) / 2;
           int gy = oyuncuGemiListesi.get(sira).get(OGEMI_Y) + oyuncuGemiListesi.get(sira).get(OGEMI_H) / 2;
           canvas.rotate(180, gx, gy);
           drawBitmap(gemi[oyuncuGemiListesi.get(sira).get(OGEMI_TUR)][oyuncuGemiListesi.get(sira).get(OGEMI_FRAME)]
                     , oyuncuGemiListesi.get(sira).get(OGEMI_X), oyuncuGemiListesi.get(sira).get(OGEMI_Y));
           canvas.restore();
           //frame + 1
           if(oyuncuGemiListesi.get(sira).get(OGEMI_FRAME) < gemiMaxFrame[oyuncuGemiListesi.get(sira).get(OGEMI_TUR)] - 1) {
               oyuncuGemiListesi.get(sira).set(OGEMI_FRAME, oyuncuGemiListesi.get(sira).get(OGEMI_FRAME) + 1);
           } else {
               oyuncuGemiListesi.get(sira).set(OGEMI_FRAME, 0);
           }
       }
    }

    private void koordinatGostergeleri(int x, int y) {
        drawText("x değeri: " + x, 300, 400);
        drawText("y değeri: " + y, 300, 500);
    }

    private void arkaPlanCiz() {
        //arka plan resmi, x - y koordinatları
        this.drawBitmap(arkaPlan, arkaPlanX, arkaPlanY);
    }

    private void arkaPlanYukle() {
        arkaPlan = this.loadImage("map.png");
        arkaPlanX = 0;
        arkaPlanY = 0;
    }

    public void keyPressed(int key) {

    }

    public void keyReleased(int key) {

    }

    public boolean backPressed() {
        return true;
    }

    public void surfaceChanged(int width, int height) {

    }

    public void surfaceCreated() {

    }

    public void surfaceDestroyed() {

    }

    public void touchDown(int x, int y, int id) {
        platformSecici(x, y);
        konumX = x;
        konumY = y;
        gemiSecici(konumX, konumY);
        butonDurumlariGuncelle(x, y);
    }

    private void butonDurumlariGuncelle(int x, int y) {

        ayarButonDurumu(x, y);
        //menüye dön butonu
        if(menuyeDonButonHedef.contains(x, y)) menuyeDonButonuDurumu = 1; else menuyeDonButonuDurumu = 0;
        if(yenidenBaslatButonHedef.contains(x, y)) yenidenBaslatButonDurum = 1; else yenidenBaslatButonDurum = 0;
        if(devamEtButonHedef.contains(x, y)) devamEtButonDurum = 1; else devamEtButonDurum = 0;

    }

    private void ayarButonDurumu(int x, int y) {
        if(x > ayarButonX && x < ayarButonX + ayarButonW
        && y > ayarButonY && y < ayarButonY + ayarButonH) {
            if(ayarButonDurumu == 0) ayarButonDurumu = 1;
            else {
                ayarButonDurumu = 0;
                //oyunDevamEdiyor = !oyunDevamEdiyor;
            }

        }
    }

    private void gemiSecici(int x, int y) {
        for(int gemi = 0; gemi != gemiCesitleri; ++gemi) {
            if(x > gemiSecimiX[gemi] && x < gemiSecimiX[gemi] + gemiSecimiW &&
               y > gemiSecimiY[gemi] && y < gemiSecimiY[gemi] + gemiSecimiH) {
                oyuncuGemiUret(gemi);
            }
        }
    }

    private void platformSecici(int x, int y) {
        if(x > oyuncuPlatformX[SOLTARAF] && x < oyuncuPlatformX[SOLTARAF] + oyuncuPlatformW[SOLTARAF] &&
                y > oyuncuPlatformY[SOLTARAF] && y < oyuncuPlatformY[SOLTARAF] + oyuncuPlatformH[SOLTARAF]) {
            platAnimX = oyuncuPlatformX[SOLTARAF];
            platAnimY = oyuncuPlatformY[SOLTARAF];
            platAnimAnlikFrame = 0;
            secilenOyuncuPlatformu = SOLTARAF;
        }

        if(x > oyuncuPlatformX[SAGTARAF] && x < oyuncuPlatformX[SAGTARAF] + oyuncuPlatformW[SAGTARAF] &&
                y > oyuncuPlatformY[SAGTARAF] && y < oyuncuPlatformY[SAGTARAF] + oyuncuPlatformH[SAGTARAF]) {
            platAnimX = oyuncuPlatformX[SAGTARAF];
            platAnimY = oyuncuPlatformY[SAGTARAF];
            platAnimAnlikFrame = 0;
            secilenOyuncuPlatformu = SAGTARAF;
        }
    }


    public void touchMove(int x, int y, int id) {
        ayarButonDurumu(x, y);
        butonDurumlariGuncelle(x, y);
    }

    public void touchUp(int x, int y, int id) {
        if(oyunDevamEdiyor == true && oyunBitti == false) {

        }
        if(oyunDevamEdiyor == false && oyunBitti == false) {
            menuyeDonButonuTikla(x, y);
            sesButonTikla(x, y);
            muzikButonTikla(x, y);
            yenidenBaslatButonTikla(x, y);
            devamEtButonTikla(x, y);
        }
        if(oyunBitti == false) {
            ayarButonTiklandi(x, y);
        }
    }

    private void sesButonTikla(int x, int y) {
        if(sesButonHedef.contains(x, y)) {
            if(root.sesButonDurumu == 0) {
                root.sesButonDurumu = 1;
            } else {
                root.sesButonDurumu = 0;
            }
        }
    }

    private void muzikButonTikla(int x, int y) {
        if(muzikButonHedef.contains(x, y)) {
            if(root.muzikButonDurumu == 0) {
                root.muzikButonDurumu = 1;
                root.oyunMuzikAcik = false;
                root.menuMuzikAcik = false;
                root.oyunMuzikCal(false);
            } else {
                root.muzikButonDurumu = 0;
                root.oyunMuzikAcik = true;
                root.menuMuzikAcik = true;
                root.oyunMuzikCal(true);
            }
        }
    }

    private void devamEtButonTikla(int x, int y) {
        if(devamEtButonHedef.contains(x, y)) {
            oyunDevamEdiyor = true;
        }
    }

    private void yenidenBaslatButonTikla(int x, int y) {
        if(yenidenBaslatButonHedef.contains(x, y)) {
            root.canvasManager.setCurrentCanvas(new GameCanvas(root));
        }
    }

    private void menuyeDonButonuTikla(int x, int y) {
        if(menuyeDonButonHedef.contains(x, y)) {
            menuyeDonButonuDurumu = 0;
            root.oyunMuzikCal(false);
            root.canvasManager.setCurrentCanvas(new MenuCanvas(root));
        }
    }

    private void ayarButonTiklandi(int x, int y) {
        if(x > ayarButonX && x < ayarButonX + ayarButonW
                && y > ayarButonY && y < ayarButonY + ayarButonH) {
            ayarButonDurumu = 0;
            oyunDevamEdiyor = !oyunDevamEdiyor;
        }
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

