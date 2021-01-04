package Project;    // 仕様パッケージ（フォルダ）の指定

import processing.core.*;    // プロセッシングのインポート

public class Image extends PApplet {    // プロセッシングのメインクラス

    PImage back1;  // 画像の型
    PImage back2;
    int x = 0;
    int y = 0;

    @Override
    public void settings() {    // サイズはここに書くらしい
        size(800, 800);  // ディスプレイサイズ

    }

    @Override
    public void setup() {    // 最初に一回だけ呼ばれる関数（初期設定とか書くところ）
        System.out.println(11111);

        back1 = loadImage("Project/background.png");  // 画像の読み込み
        back1.resize(800, 800);  // サイズ変更
        back2 = loadImage("Project/background.png");
        back2.resize(800, 800);


    }

    @Override
    public void draw() {    // メインループ関数
        background(255);    // 背景色、白
        image(back1, x, y);  // 画像の描写
        image(back2, x, y - 800);
        if (y >= 800){  // もし画像が下を越えたら
            y = 0;  // ｙ座標を０に
        }
        y++;  // +1 ずつ増やす
    }

    public static void main(String[] args) {    // Java のメインクラス
        PApplet.main("Project.Image");

    }

}