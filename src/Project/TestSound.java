package Project;    // 仕様パッケージ（フォルダ）の指定

import processing.core.*;

import ddf.minim.*;  // minim ライブラリのインポート

public class TestSound extends PApplet {    // プロセッシングのメインクラス

    Minim minim;
    AudioSample roller;
    AudioSample foot;

    @Override
    public void settings() {    // サイズはここに書くらしい
        size(800, 800);  // ディスプレイサイズ
    }

    @Override
    public void setup() {    // 最初に一回だけ呼ばれる関数（初期設定とか書くところ）
        minim = new Minim(this);
        roller = minim.loadSample("roller.wav", 2048);
        foot = minim.loadSample("foot.wav", 2048);

    }

    @Override
    public void draw() {    // メインループ関数

    }

    @Override
    public void stop(){
        roller.close();
        foot.close();
        minim.stop();

        super.stop();
    }

    public static void main(String[] args) {    // Java のメインクラス
        PApplet.main("Project.TestSound");

    }

    @Override
    public void keyPressed() {
        if (key == 'k') roller.trigger();
        if (key == 's') foot.trigger();
    }
}


//import ddf.minim.*;
//
//        Minim minim;
//        AudioSample roller;
//        AudioSample foot;
//
//        void setup() {
//        minim = new Minim(this);
//        roller = minim.loadSample("roller.wav", 2048);
//        foot = minim.loadSample("foot.wav", 2048);
//        }
//
//        void draw() {
//        }
//
//        void keyPressed() {
//        if (key == 'k') roller.trigger();
//        if (key == 's') foot.trigger();
//        }
//
//        void stop() {
//        roller.close();
//        foot.close();
//        minim.stop();
//
//        super.stop();
//        }