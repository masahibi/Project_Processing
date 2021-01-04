package Project;    // 仕様パッケージ（フォルダ）の指定

import processing.core.*;    // プロセッシングのインポート

import java.util.ArrayList;    // ArrayListのライブラリ

public class Main03 extends PApplet {    // プロセッシングのメインクラス
    PApplet pApplet;
    boolean right;    // true、false 値
    boolean left;
    boolean up;
    boolean down;
    boolean space;

    Myself myself;    // クラスの変数定義
    ArrayList<Enemy> enemies;
    ArrayList<Bullet> myBullets;
    ArrayList<Bullet> eneBullets;
    Item item;

    Project.Image image;  // Project パッケージの Image クラスを image として定義
//    Project.Sound sound;
    Score score;

    @Override
    public void settings() {    // サイズはここに書くらしい
        size(640, 640);    // 画面サイズ

    }

    @Override
    public void setup() {    // 最初に一回だけ呼ばれる関数（初期設定とか書くところ）

        image = new Image();  // Image クラスのインスタンス生成
//        sound = new Sound();
        score = new Score();

        rectMode(CENTER);    // x、y、幅、高さ
        myself = new Myself();    // インスタンスの生成
        enemies = new ArrayList<Enemy>();
        myBullets = new ArrayList<Bullet>();
        eneBullets = new ArrayList<Bullet>();
        item = new Item();

        noStroke();    //pAppletを加える
//        image.setup();  // image インスタンスの setup メソッド
//        sound.setup();
        score.setup();
    }

    @Override
    public void draw() {    // メインループ関数
        background(255);    // 背景色、白
//        score.draw();
        item.display();
        item.update();

/*------------------------------------------------------------------------*/
        // インスタンス生成
        myself.display();    // 自分描写メソッド
        for (Enemy enemy : enemies) {    // 敵リストの個数回す
            enemy.display();    // 敵描写メソッド
        }
        for (Bullet bullet : myBullets) {    // 自分の弾リストの個数回す
            bullet.display();    //　自分の弾描写メソッド
        }
        for (Bullet bullet : eneBullets) {    // 敵の弾リストの個数回す
            bullet.display();    // 敵の弾描写メソッド
        }

/*------------------------------------------------------------------------*/
        // 再描写
        myself.update();    // 自分の移動メソッド
        ArrayList<Enemy> nextEnemies = new ArrayList<Enemy>();    // 次の敵リスト
        for (Enemy enemy : enemies) {    // 敵リスト分回す
            enemy.update();    // 敵の移動メソッド
            if (!enemy.isDead) {    // もし敵が死んだら
                nextEnemies.add(enemy);    // 次の敵リストに追加
            }
        }
        enemies = nextEnemies;    // 敵リストを次の敵リストにする
        ArrayList<Bullet> nextMyBullets = new ArrayList<Bullet>();    // 次の自分の弾リスト
        for (Bullet bullet : myBullets) {    // 自分の弾リスト分回す
            bullet.update();    // 弾の移動メソッド
            if (!bullet.isDead) {    // もし弾が死んだら
                nextMyBullets.add(bullet);    // 次の弾リストに追加
            }
        }
        myBullets = nextMyBullets;    // 弾リストを次の弾リストにする
        ArrayList<Bullet> nextEneBullets = new ArrayList<Bullet>();    // 次の敵の弾リスト
        for (Bullet bullet : eneBullets) {    // 敵の弾リスト分回す
            bullet.update();    // 弾の移動メソッド
            if (!bullet.isDead) {    // もし弾が死んだら
                nextEneBullets.add(bullet);    // 次の弾リストに追加
            }
        }
        eneBullets = nextEneBullets;    // 弾リストを次の弾リストにする
        if (random(1) < 0.02) {    // もし0～１のランダムで0.02より小さければ
            enemies.add(new Enemy());    // 敵リストに新しいインスタンスを追加
        }

//        if (item.isDead){
//            item = new Item();
//        }
    }

    @Override
    public void keyPressed() {    // キーを押したときのメソッド
        if (keyCode == RIGHT) right = true;    // もし右押されたら、rightをtrueにする
        if (keyCode == LEFT) left = true;
        if (keyCode == UP) up = true;
        if (keyCode == DOWN) down = true;
        if (keyCode == ' ') space = true;
    }

    @Override
    public void keyReleased() {    // キーを離したときのメソッド
        if (keyCode == RIGHT) right = false;    // もし右を離したら、rightをfalseにする
        if (keyCode == LEFT) left = false;
        if (keyCode == UP) up = false;
        if (keyCode == DOWN) down = false;
        if (keyCode == ' ') space = false;
    }


    class Myself {    // 自分のクラス
        PVector loc;    // ベクトル
        float size;
        int coolingTime;
        boolean isDead;

        int left_x;
        int left_y;
        int right_x;
        int right_y;
        int vx = 0;
        int vy = 0;

        Myself() {    // インスタンス用
            size = 25;
            loc = new PVector(width / 2, height - size / 2 - 20);    // ベクトル（ｘ、ｙ）を初期位置とする（トップ）
            coolingTime = 0;    // 弾とかの間隔調整用
            isDead = false;
        }

//        @Override
        public void display() {    // 描写メソッド
            left_x = (int) (loc.x - size);    //　左下の位置
            left_y = (int) (loc.y + size);
            right_x = (int) (loc.x + size);
            right_y = (int) (loc.y + size);
            if (isDead) {    // もし死んだら
                fill(255, 0, 0);    // この色にする
                stroke(0, 255, 0);
            } else {    // もし生きてたら
                fill(0);    // 色を黒にする
                stroke(0, 0, 0);
            }
            triangle(loc.x, loc.y, left_x, left_y, right_x, right_y);    // 自分の三角形描写
        }

        public void update() {    // 移動メソッド
            vx = 0;    // 初速は0
            vy = 0;
            isDead = false;
            if (left) vx = -10;    //左押されたら、vxをマイナスに
            if (right) vx = 10;
            if (up) vy = -10;
            if (down) vy = 10;

            loc.x += vx;    // x座標をvx分移動させる
            loc.y += vy;    // y座標をvy分移動させる
            coolingTime++;    // Timeをプラス１していく
            if (space && coolingTime >= 10) {    // もしスペースが押されて、Timeが10より大きくなったら
                myBullets.add(new Bullet());    // 弾リストに弾インスタンスを追加
                coolingTime = 0;    // Timeを０にする
            }
            for (Bullet b : eneBullets) {    // 敵の弾リスト分回す
                if ((loc.x - size / 2 <= b.loc.x && b.loc.x <= loc.x + size / 2)
                        && (loc.y - size / 2 <= b.loc.y && b.loc.y <= loc.y + size / 2)) {    // 敵の弾が自分に当たったら
                    isDead = true;    // isDeadをtrueに
                    b.isDead = true;    // 敵の弾のisDeadをtrueに
                    break;    // ループを抜ける
                }
            }
            for (Enemy e : enemies) {    // 敵のリスト分回す
                if (abs(loc.x - e.loc.x) < size / 2 + e.size / 2 && abs(loc.y - e.loc.y) < size / 2 + e.size / 2) {    // 敵自体に当たったら
                    isDead = true;    //isDeadをtrueに
                    e.isDead = true;    //敵のisDeadをtrueに
                    break;    //ループを抜ける
                }
            }
        }
    }


    class Bullet {     // 弾クラス
        PVector loc;
        float vel;
        boolean isMine;
        boolean isDead;


        Bullet() {    // 自分のインスタンス用
            loc = new PVector(myself.loc.x, myself.loc.y);    // ベクトル（ｘ、ｙ）を初期位置とする（トップ）
            vel = -10;    // 弾の長さ
            isMine = true;
        }

        Bullet(Enemy enemy) {    // 敵のインスタンス用
            loc = new PVector(enemy.loc.x, enemy.loc.y);    // ベクトル（ｘ、ｙ）を初期位置とする（トップ）
            vel = 5;    // 弾の長さ
            isMine = false;
        }

        public void display() {    // 描写メソッド
            if (isMine) {    // もし自分の弾なら
                stroke(0, 0, 0);    // 色を黒にする
            } else {    // 敵の弾なら
                stroke(255, 0, 0);    // 色を赤にする
            }
            line(loc.x, loc.y, loc.x, loc.y + vel);    // 弾のline描写
        }

        public void update() {    // 移動メソッド
            loc.y += vel;    //y座標を長さ分移動させる
            if ((vel > 0 && loc.y > height) || (vel < 0 && loc.y < 0)) {    // もし弾が上か下を越えたら
                isDead = true;    // isDeadをtrueに
            }
        }
    }


    class Enemy {    // 敵のクラス
        PVector loc;
        float vel;
        float size;
        int coolingTime;
        boolean isDead;

        int left_x;
        int left_y;
        int right_x;
        int right_y;
        int vx = 0;
        int vy = 3;

        Enemy() {    // インスタンス用
            size = 25;
            loc = new PVector(random(size / 2, width - size / 2), -size / 2);    // ベクトル（ｘ、ｙ）を初期位置とする（トップ）、xはランダム
            coolingTime = (int) random(60);    // 生成間隔は、random
            isDead = false;
        }

        public void display() {    // 描写メソッド
            left_x = (int) (loc.x - size);    //　左上の位置
            left_y = (int) (loc.y - size);
            right_x = (int) (loc.x + size);
            right_y = (int) (loc.y - size);

            fill(255, 0, 0);    // 色は赤
            stroke(255, 0, 0);
            triangle(loc.x, loc.y, left_x, left_y, right_x, right_y);    // 敵の三角形描写
        }

        public void update() {    // 移動メソッド
            loc.y += vy;    // y座標をvy分移動させる
            if (loc.y > height) {    // もし下を越えたら
                isDead = true;    // isDeadをtrueにする
            }
            coolingTime++;    // Timeをプラス１していく
            if (coolingTime >= 60) {    // もしTimeが60を越えたら
                eneBullets.add(new Bullet(this));    // 敵の弾リストに新しい弾インスタンスを追加
                coolingTime = 0;    // Timeを0にする
            }
            for (Bullet b : myBullets) {    // 自分の弾リスト分回す
                if ((loc.x - size / 2 - 10 <= b.loc.x && b.loc.x <= loc.x + size / 2 + 10)
                        && (loc.y - size <= b.loc.y && b.loc.y <= loc.y)) {    // もし自分の弾が敵に当たったら
                    isDead = true;    // isDeadをtrueに
                    b.isDead = true;    // 自分の弾のisDeadをtrueに
                    break;    // ループを抜ける
                }
            }
        }
    }

    class Item {    // 敵のクラス
        PVector loc;
        float vel;
        float size;
        int coolingTime;
        boolean isDead;

        int left_x;
        int left_y;
        int right_x;
        int right_y;
        int vx = 0;
        int vy = 3;

        Item() {    // インスタンス用
            size = 25;
            loc = new PVector(random(size / 2, width - size / 2), -size / 2);    // ベクトル（ｘ、ｙ）を初期位置とする（トップ）、xはランダム
            coolingTime = (int) random(60);    // 生成間隔は、random
            isDead = false;
        }

        public void display() {    // 描写メソッド
            left_x = (int) (loc.x - size);    //　左上の位置
            left_y = (int) (loc.y - size);
            right_x = (int) (loc.x + size);
            right_y = (int) (loc.y - size);

            fill(0, 255, 0);    // 色は緑
            stroke(0, 255, 0);
            if (!isDead) {
                triangle(loc.x, loc.y, left_x, left_y, right_x, right_y);    // アイテムの三角形描写
            }
        }

        public void update() {    // 移動メソッド
            loc.y += vy;    // y座標をvy分移動させる
            if (loc.y > height) {    // もし下を越えたら
                isDead = true;    // isDeadをtrueにする
            }
            if (isDead) {
                coolingTime++;    // Timeをプラス１していく
            }
            if (coolingTime >= 600 && isDead) {    // もしTimeが60を越えたら
                item = new Item();

                coolingTime = 0;    // Timeを0にする
            }
            if (abs(loc.x - myself.loc.x) < size / 2 + myself.size / 2 && abs(loc.y - myself.loc.y) < size / 2 + myself.size / 2) {    // 自分自体に当たったら
//                life++;
                isDead = true;    //isDeadをtrueに
            }
        }
    }


    public static void main(String[] args) {    // Java のメインクラス
        PApplet.main("Project.Main03");    // processingのメインクラス
        System.out.println(1);
    }

}