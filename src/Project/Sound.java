package Project;    // 仕様パッケージ（フォルダ）の指定

import processing.core.*;

import ddf.minim.*;  // minim ライブラリのインポート

public class Sound extends PApplet {    // プロセッシングのメインクラス

    Minim minim;  // Minim 型変数である minim の宣言
    AudioPlayer player;  // サウンド格納用の変数

    @Override
    public void settings() {    // サイズはここに書くらしい
        size(800, 800);  // ディスプレイサイズ
    }

    @Override
    public void setup() {    // 最初に一回だけ呼ばれる関数（初期設定とか書くところ）
        minim = new Minim(this);  // 初期化
        player = minim.loadFile("game.wav");  // ファイルを読み込む
        player.play();  // 再生
    }

    @Override
    public void draw() {    // メインループ関数

    }

    @Override
    public void stop(){
        player.close();  // サウンドデータの終了
        minim.stop();
        super.stop();
    }

    public static void main(String[] args) {    // Java のメインクラス
        PApplet.main("Project.Sound");

    }
}