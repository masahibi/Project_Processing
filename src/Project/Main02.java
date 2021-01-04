package Project;

import processing.core.PApplet;

import java.util.ArrayList;

public class Main02 extends PApplet {
    Player player;
    Bullet bullet;
    ArrayList<Bullet> bullets;
    Enemy enemy;


//    int p_top_x;
//    int p_top_y;
//    int p_left_x;
//    int p_left_y;
//    int p_right_x;
//    int p_right_y;
//
//    int p_dx;
//    int p_dy;

//    int bullet_x;
//    int bullet_y;
//    int bullet_r;
//
//    int bullet_dx;
//    int bullet_dy;

//    int e_top_x = 200;
//    int e_top_y;
//    int e_left_x;
//    int e_left_y;
//    int e_right_x;
//    int e_right_y;
//
//    int e_dx;
//    int e_dy;

    boolean right;
    boolean left;
    boolean up;
    boolean down;
    boolean space;

    int count = 0;


    @Override
    public void settings() {
        size(800, 800);

    }

    @Override
    public void setup() {
        background(255);
        noStroke();
        smooth();

        player = new Player(200, 200);
        bullet = new Bullet();
        bullets = new ArrayList<Bullet>();
        enemy = new Enemy();

        player.setup();
        bullet.setup();
        enemy.setup();


//        p_top_x = 200;
//        p_top_y = 400;
//        p_left_x = p_top_x - 50;
//        p_left_y = p_top_y + f50;
//        p_right_x = p_top_x + 50;
//        p_right_y = p_top_y + 50;
//
//        p_dx = 10;
//        p_dy = 10;

//        bullet_x = p_top_x;
//        bullet_y = p_top_y;
//        bullet_r = 5;

//        bullet_dx = p_dx;
//        bullet_dy = p_dy;

//        e_top_x = 400;
//        e_top_y = 100;
//        e_left_x = e_top_x - 50;
//        e_left_y = e_top_y - 50;
//        e_right_x = e_top_x + 50;
//        e_right_y = e_top_y - 50;
//
//        e_dy = 1;

        right = false;
        left = false;
        up = false;
        down = false;
        space = false;

    }

    @Override
    public void draw() {
        background(255);
        fill(0);

        if (right) {
            player.top_x += player.dx;
            bullet.x += bullet.dx;
        }
        if (left) {
            player.top_x -= player.dx;
            bullet.x -= bullet.dx;
        }
        if (up) {
            player.top_y -= player.dy;
            if (count != 0) bullet.dy = 0;
            bullet.y -= bullet.dy;
        }
        if (down) {
            player.top_y += player.dy;
            if (count != 0) bullet.dy = 0;
            bullet.y += bullet.dy;
        }
        count += 1;
        if (space && count >= 10) {
            bullets.add(new Bullet());
            System.out.println(bullets.size());
            count = 0;
            for (int i = 0; i < bullets.size(); i++){
                bullets.get(i).dx = 0;
                bullets.get(i).dy = 10;
            }
        }

        bullet.draw();
        for (int i = 0; i < bullets.size(); i++){
            System.out.println(bullets.get(i).y);
            ellipse(bullets.get(i).x, bullets.get(i).y, bullets.get(i).r, bullets.get(i).r);
        }

        ellipse(bullet.x, bullet.y, bullet.r, bullet.r);
        player.draw();
        triangle(player.top_x, player.top_y, player.left_x, player.left_y, player.right_x, player.right_y);
        enemy.draw();
        if (bullet.y <= enemy.top_y && bullet.x >= enemy.left_x && bullet.x <= enemy.right_x) {
            int a = 0;
        }else {
            triangle(enemy.top_x, enemy.top_y, enemy.left_x, enemy.left_y, enemy.right_x, enemy.right_y);
        }


    }
//    public void reload_value(){
//        p_left_x = p_top_x - 50;
//        p_left_y = p_top_y + 50;
//        p_right_x = p_top_x + 50;
//        p_right_y = p_top_y + 50;
//
//        e_left_x = e_top_x - 50;
//        e_left_y = e_top_y - 50;
//        e_right_x = e_top_x + 50;
//        e_right_y = e_top_y - 50;
//
//        if (count == 1)  bullet_y -= 10;
//
//        e_top_y += e_dy;
//    }

    @Override
    public void keyPressed(){
//        switch (keyCode) {
//            case RIGHT:
//                right = true;
//            case LEFT:
//                left = true;
//            case UP:
//                up = true;
//            case DOWN:
//                down = true;
//        }
        if (keyCode == RIGHT) right = true;
        if (keyCode == LEFT) left = true;
        if (keyCode == UP) up = true;
        if (keyCode == DOWN) down = true;
        if (keyCode == ' ') space = true;
    }
    @Override
    public void keyReleased() {
        if (keyCode == RIGHT) right = false;
        if (keyCode == LEFT) left = false;
        if (keyCode == UP) up = false;
        if (keyCode == DOWN) down = false;
        if (keyCode == ' ') space = false;
    }


    class Player extends PApplet{
        int top_x;
        int top_y;
        int left_x;
        int left_y;
        int right_x;
        int right_y;

        int dx;
        int dy;

        Player(int x, int y){
            top_x = x;
            top_y = y;
        }

        @Override
        public void setup() {
            top_x = 200;
            top_y = 400;
            left_x = top_x - 50;
            left_y = top_y + 50;
            right_x = top_x + 50;
            right_y = top_y + 50;

            dx = 10;
            dy = 10;
        }

        @Override
        public void draw() {
            left_x = top_x - 50;
            left_y = top_y + 50;
            right_x = top_x + 50;
            right_y = top_y + 50;

//            triangle(top_x, top_y, left_x, left_y, right_x, right_y);
        }

    }


    class Bullet extends PApplet{
        int x;
        int y;
        int r;

        int dx;
        int dy;

        Bullet(){

        }


        @Override
        public void setup(){
            x = player.top_x;
            y = player.top_y +  20;
            r = 10;
            dx = player.dx;
            dy = player.dy;
        }

        @Override
        public void draw(){
            if (count != 0)  y -= 10;
//            ellipse(x, y, r, r);
        }
    }


    class Enemy extends PApplet{
        int top_x;
        int top_y;
        int left_x;
        int left_y;
        int right_x;
        int right_y;

        int dx;
        int dy;

        Enemy(){

        }

        @Override
        public void setup() {
            top_x = (int) random(100, 400);
            top_y = 100;
            left_x = top_x - 50;
            left_y = top_y - 50;
            right_x = top_x + 50;
            right_y = top_y - 50;

            dy = 1;
        }

        @Override
        public void draw(){
        left_x = top_x - 50;
        left_y = top_y - 50;
        right_x = top_x + 50;
        right_y = top_y - 50;

        top_y += dy;

//        triangle(top_x, top_y, left_x, left_y, right_x, right_y);
        }
    }


    public static void main(String[] args) {
        PApplet.main("Project.Main02");
        println("Hello World!");
    }
}