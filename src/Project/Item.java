package Project;    // 仕様パッケージ（フォルダ）の指定

import processing.core.*;    // プロセッシングのインポート

public class Item extends PApplet {    // プロセッシングのメインクラス
    int count;
    int  testlife;

    @Override
    public void settings() {    // サイズはここに書くらしい
        size(800, 800);  // ディスプレイサイズ

    }

    @Override
    public void setup() {    // 最初に一回だけ呼ばれる関数（初期設定とか書くところ）
        count = 0;
        testlife = 0;
    }

    @Override
    public void draw() {    // メインループ関数
        background(255);
        if (count == 300) {
            rect(100, 100, 25, 25);

        }
    }

    public static void main(String[] args) {    // Java のメインクラス
        PApplet.main("Project.Item");

    }
}