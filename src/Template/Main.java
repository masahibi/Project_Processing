package Template;    // 仕様パッケージ（フォルダ）の指定

import processing.core.*;    // プロセッシングのインポート

public class Main extends PApplet {    // プロセッシングのメインクラス

    @Override
    public void settings() {    // サイズはここに書くらしい
        size(800, 800);

    }

    @Override
    public void setup() {    // 最初に一回だけ呼ばれる関数（初期設定とか書くところ）

    }

    @Override
    public void draw() {    // メインループ関数

    }

    public static void main(String[] args) {    // Java のメインクラス
        PApplet.main("Template.Main");

    }
}