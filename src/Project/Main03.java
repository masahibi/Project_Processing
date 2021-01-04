package Project;    // �d�l�p�b�P�[�W�i�t�H���_�j�̎w��

import processing.core.*;    // �v���Z�b�V���O�̃C���|�[�g

import java.util.ArrayList;    // ArrayList�̃��C�u����

public class Main03 extends PApplet {    // �v���Z�b�V���O�̃��C���N���X
    PApplet pApplet;
    boolean right;    // true�Afalse �l
    boolean left;
    boolean up;
    boolean down;
    boolean space;

    Myself myself;    // �N���X�̕ϐ���`
    ArrayList<Enemy> enemies;
    ArrayList<Bullet> myBullets;
    ArrayList<Bullet> eneBullets;
    Item item;

    Project.Image image;  // Project �p�b�P�[�W�� Image �N���X�� image �Ƃ��Ē�`
//    Project.Sound sound;
    Score score;

    @Override
    public void settings() {    // �T�C�Y�͂����ɏ����炵��
        size(640, 640);    // ��ʃT�C�Y

    }

    @Override
    public void setup() {    // �ŏ��Ɉ�񂾂��Ă΂��֐��i�����ݒ�Ƃ������Ƃ���j

        image = new Image();  // Image �N���X�̃C���X�^���X����
//        sound = new Sound();
        score = new Score();

        rectMode(CENTER);    // x�Ay�A���A����
        myself = new Myself();    // �C���X�^���X�̐���
        enemies = new ArrayList<Enemy>();
        myBullets = new ArrayList<Bullet>();
        eneBullets = new ArrayList<Bullet>();
        item = new Item();

        noStroke();    //pApplet��������
//        image.setup();  // image �C���X�^���X�� setup ���\�b�h
//        sound.setup();
        score.setup();
    }

    @Override
    public void draw() {    // ���C�����[�v�֐�
        background(255);    // �w�i�F�A��
//        score.draw();
        item.display();
        item.update();

/*------------------------------------------------------------------------*/
        // �C���X�^���X����
        myself.display();    // �����`�ʃ��\�b�h
        for (Enemy enemy : enemies) {    // �G���X�g�̌���
            enemy.display();    // �G�`�ʃ��\�b�h
        }
        for (Bullet bullet : myBullets) {    // �����̒e���X�g�̌���
            bullet.display();    //�@�����̒e�`�ʃ��\�b�h
        }
        for (Bullet bullet : eneBullets) {    // �G�̒e���X�g�̌���
            bullet.display();    // �G�̒e�`�ʃ��\�b�h
        }

/*------------------------------------------------------------------------*/
        // �ĕ`��
        myself.update();    // �����̈ړ����\�b�h
        ArrayList<Enemy> nextEnemies = new ArrayList<Enemy>();    // ���̓G���X�g
        for (Enemy enemy : enemies) {    // �G���X�g����
            enemy.update();    // �G�̈ړ����\�b�h
            if (!enemy.isDead) {    // �����G�����񂾂�
                nextEnemies.add(enemy);    // ���̓G���X�g�ɒǉ�
            }
        }
        enemies = nextEnemies;    // �G���X�g�����̓G���X�g�ɂ���
        ArrayList<Bullet> nextMyBullets = new ArrayList<Bullet>();    // ���̎����̒e���X�g
        for (Bullet bullet : myBullets) {    // �����̒e���X�g����
            bullet.update();    // �e�̈ړ����\�b�h
            if (!bullet.isDead) {    // �����e�����񂾂�
                nextMyBullets.add(bullet);    // ���̒e���X�g�ɒǉ�
            }
        }
        myBullets = nextMyBullets;    // �e���X�g�����̒e���X�g�ɂ���
        ArrayList<Bullet> nextEneBullets = new ArrayList<Bullet>();    // ���̓G�̒e���X�g
        for (Bullet bullet : eneBullets) {    // �G�̒e���X�g����
            bullet.update();    // �e�̈ړ����\�b�h
            if (!bullet.isDead) {    // �����e�����񂾂�
                nextEneBullets.add(bullet);    // ���̒e���X�g�ɒǉ�
            }
        }
        eneBullets = nextEneBullets;    // �e���X�g�����̒e���X�g�ɂ���
        if (random(1) < 0.02) {    // ����0�`�P�̃����_����0.02��菬�������
            enemies.add(new Enemy());    // �G���X�g�ɐV�����C���X�^���X��ǉ�
        }

//        if (item.isDead){
//            item = new Item();
//        }
    }

    @Override
    public void keyPressed() {    // �L�[���������Ƃ��̃��\�b�h
        if (keyCode == RIGHT) right = true;    // �����E�����ꂽ��Aright��true�ɂ���
        if (keyCode == LEFT) left = true;
        if (keyCode == UP) up = true;
        if (keyCode == DOWN) down = true;
        if (keyCode == ' ') space = true;
    }

    @Override
    public void keyReleased() {    // �L�[�𗣂����Ƃ��̃��\�b�h
        if (keyCode == RIGHT) right = false;    // �����E�𗣂�����Aright��false�ɂ���
        if (keyCode == LEFT) left = false;
        if (keyCode == UP) up = false;
        if (keyCode == DOWN) down = false;
        if (keyCode == ' ') space = false;
    }


    class Myself {    // �����̃N���X
        PVector loc;    // �x�N�g��
        float size;
        int coolingTime;
        boolean isDead;

        int left_x;
        int left_y;
        int right_x;
        int right_y;
        int vx = 0;
        int vy = 0;

        Myself() {    // �C���X�^���X�p
            size = 25;
            loc = new PVector(width / 2, height - size / 2 - 20);    // �x�N�g���i���A���j�������ʒu�Ƃ���i�g�b�v�j
            coolingTime = 0;    // �e�Ƃ��̊Ԋu�����p
            isDead = false;
        }

//        @Override
        public void display() {    // �`�ʃ��\�b�h
            left_x = (int) (loc.x - size);    //�@�����̈ʒu
            left_y = (int) (loc.y + size);
            right_x = (int) (loc.x + size);
            right_y = (int) (loc.y + size);
            if (isDead) {    // �������񂾂�
                fill(255, 0, 0);    // ���̐F�ɂ���
                stroke(0, 255, 0);
            } else {    // ���������Ă���
                fill(0);    // �F�����ɂ���
                stroke(0, 0, 0);
            }
            triangle(loc.x, loc.y, left_x, left_y, right_x, right_y);    // �����̎O�p�`�`��
        }

        public void update() {    // �ړ����\�b�h
            vx = 0;    // ������0
            vy = 0;
            isDead = false;
            if (left) vx = -10;    //�������ꂽ��Avx���}�C�i�X��
            if (right) vx = 10;
            if (up) vy = -10;
            if (down) vy = 10;

            loc.x += vx;    // x���W��vx���ړ�������
            loc.y += vy;    // y���W��vy���ړ�������
            coolingTime++;    // Time���v���X�P���Ă���
            if (space && coolingTime >= 10) {    // �����X�y�[�X��������āATime��10���傫���Ȃ�����
                myBullets.add(new Bullet());    // �e���X�g�ɒe�C���X�^���X��ǉ�
                coolingTime = 0;    // Time���O�ɂ���
            }
            for (Bullet b : eneBullets) {    // �G�̒e���X�g����
                if ((loc.x - size / 2 <= b.loc.x && b.loc.x <= loc.x + size / 2)
                        && (loc.y - size / 2 <= b.loc.y && b.loc.y <= loc.y + size / 2)) {    // �G�̒e�������ɓ���������
                    isDead = true;    // isDead��true��
                    b.isDead = true;    // �G�̒e��isDead��true��
                    break;    // ���[�v�𔲂���
                }
            }
            for (Enemy e : enemies) {    // �G�̃��X�g����
                if (abs(loc.x - e.loc.x) < size / 2 + e.size / 2 && abs(loc.y - e.loc.y) < size / 2 + e.size / 2) {    // �G���̂ɓ���������
                    isDead = true;    //isDead��true��
                    e.isDead = true;    //�G��isDead��true��
                    break;    //���[�v�𔲂���
                }
            }
        }
    }


    class Bullet {     // �e�N���X
        PVector loc;
        float vel;
        boolean isMine;
        boolean isDead;


        Bullet() {    // �����̃C���X�^���X�p
            loc = new PVector(myself.loc.x, myself.loc.y);    // �x�N�g���i���A���j�������ʒu�Ƃ���i�g�b�v�j
            vel = -10;    // �e�̒���
            isMine = true;
        }

        Bullet(Enemy enemy) {    // �G�̃C���X�^���X�p
            loc = new PVector(enemy.loc.x, enemy.loc.y);    // �x�N�g���i���A���j�������ʒu�Ƃ���i�g�b�v�j
            vel = 5;    // �e�̒���
            isMine = false;
        }

        public void display() {    // �`�ʃ��\�b�h
            if (isMine) {    // ���������̒e�Ȃ�
                stroke(0, 0, 0);    // �F�����ɂ���
            } else {    // �G�̒e�Ȃ�
                stroke(255, 0, 0);    // �F��Ԃɂ���
            }
            line(loc.x, loc.y, loc.x, loc.y + vel);    // �e��line�`��
        }

        public void update() {    // �ړ����\�b�h
            loc.y += vel;    //y���W�𒷂����ړ�������
            if ((vel > 0 && loc.y > height) || (vel < 0 && loc.y < 0)) {    // �����e���ォ�����z������
                isDead = true;    // isDead��true��
            }
        }
    }


    class Enemy {    // �G�̃N���X
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

        Enemy() {    // �C���X�^���X�p
            size = 25;
            loc = new PVector(random(size / 2, width - size / 2), -size / 2);    // �x�N�g���i���A���j�������ʒu�Ƃ���i�g�b�v�j�Ax�̓����_��
            coolingTime = (int) random(60);    // �����Ԋu�́Arandom
            isDead = false;
        }

        public void display() {    // �`�ʃ��\�b�h
            left_x = (int) (loc.x - size);    //�@����̈ʒu
            left_y = (int) (loc.y - size);
            right_x = (int) (loc.x + size);
            right_y = (int) (loc.y - size);

            fill(255, 0, 0);    // �F�͐�
            stroke(255, 0, 0);
            triangle(loc.x, loc.y, left_x, left_y, right_x, right_y);    // �G�̎O�p�`�`��
        }

        public void update() {    // �ړ����\�b�h
            loc.y += vy;    // y���W��vy���ړ�������
            if (loc.y > height) {    // ���������z������
                isDead = true;    // isDead��true�ɂ���
            }
            coolingTime++;    // Time���v���X�P���Ă���
            if (coolingTime >= 60) {    // ����Time��60���z������
                eneBullets.add(new Bullet(this));    // �G�̒e���X�g�ɐV�����e�C���X�^���X��ǉ�
                coolingTime = 0;    // Time��0�ɂ���
            }
            for (Bullet b : myBullets) {    // �����̒e���X�g����
                if ((loc.x - size / 2 - 10 <= b.loc.x && b.loc.x <= loc.x + size / 2 + 10)
                        && (loc.y - size <= b.loc.y && b.loc.y <= loc.y)) {    // ���������̒e���G�ɓ���������
                    isDead = true;    // isDead��true��
                    b.isDead = true;    // �����̒e��isDead��true��
                    break;    // ���[�v�𔲂���
                }
            }
        }
    }

    class Item {    // �G�̃N���X
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

        Item() {    // �C���X�^���X�p
            size = 25;
            loc = new PVector(random(size / 2, width - size / 2), -size / 2);    // �x�N�g���i���A���j�������ʒu�Ƃ���i�g�b�v�j�Ax�̓����_��
            coolingTime = (int) random(60);    // �����Ԋu�́Arandom
            isDead = false;
        }

        public void display() {    // �`�ʃ��\�b�h
            left_x = (int) (loc.x - size);    //�@����̈ʒu
            left_y = (int) (loc.y - size);
            right_x = (int) (loc.x + size);
            right_y = (int) (loc.y - size);

            fill(0, 255, 0);    // �F�͗�
            stroke(0, 255, 0);
            if (!isDead) {
                triangle(loc.x, loc.y, left_x, left_y, right_x, right_y);    // �A�C�e���̎O�p�`�`��
            }
        }

        public void update() {    // �ړ����\�b�h
            loc.y += vy;    // y���W��vy���ړ�������
            if (loc.y > height) {    // ���������z������
                isDead = true;    // isDead��true�ɂ���
            }
            if (isDead) {
                coolingTime++;    // Time���v���X�P���Ă���
            }
            if (coolingTime >= 600 && isDead) {    // ����Time��60���z������
                item = new Item();

                coolingTime = 0;    // Time��0�ɂ���
            }
            if (abs(loc.x - myself.loc.x) < size / 2 + myself.size / 2 && abs(loc.y - myself.loc.y) < size / 2 + myself.size / 2) {    // �������̂ɓ���������
//                life++;
                isDead = true;    //isDead��true��
            }
        }
    }


    public static void main(String[] args) {    // Java �̃��C���N���X
        PApplet.main("Project.Main03");    // processing�̃��C���N���X
        System.out.println(1);
    }

}