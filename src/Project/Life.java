package Project;    // 仕様パッケージ（フォルダ）の指定

import processing.core.*;    // プロセッシングのインポート

public class Life extends PApplet {    // プロセッシングのメインクラス
    int life;

    @Override
    public void settings() {    // サイズはここに書くらしい
        size(800, 800);  // ディスプレイサイズ

    }

    @Override
    public void setup() {    // 最初に一回だけ呼ばれる関数（初期設定とか書くところ）
        life = 3;
    }

    @Override
    public void draw() {    // メインループ関数
        background(255);
        fill(255, 0, 0);
        noStroke();
        if (life >= 1) {
            ellipse(50, 50, 50, 50);
            if (life >= 2) {
                ellipse(110, 50, 50, 50);
                if (life >= 3) {
                    ellipse(170, 50, 50, 50);
                }
            }
        }
    }

    public static void main(String[] args) {    // Java のメインクラス
        PApplet.main("Project.Life");

    }
}