package Project;

import processing.core.PApplet;

public class Score extends PApplet {    // プロセッシングのメインクラス
    int score;

    @Override
    public void settings() {    // サイズはここに書くらしい
        size(800, 800);  // ディスプレイサイズ

    }

    @Override
    public void setup() {    // 最初に一回だけ呼ばれる関数（初期設定とか書くところ）

    }

//    @Override

    public void draw() {    // メインループ関数
        background(255);    // 背景色、白
        fill(255);
        strokeWeight(5);
        rect(50, 50, 80, 40);
        fill(0);
        textSize(30);
        textAlign(RIGHT);
        text(score, 120, 80);
        score++;
    }

    public static void main(String[] args) {    // Java のメインクラス
        PApplet.main("Project.Score");

    }
}
