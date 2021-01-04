package Project;    // �d�l�p�b�P�[�W�i�t�H���_�j�̎w��

import processing.core.*;

import ddf.minim.*;  // minim ���C�u�����̃C���|�[�g

public class TestSound extends PApplet {    // �v���Z�b�V���O�̃��C���N���X

    Minim minim;
    AudioSample roller;
    AudioSample foot;

    @Override
    public void settings() {    // �T�C�Y�͂����ɏ����炵��
        size(800, 800);  // �f�B�X�v���C�T�C�Y
    }

    @Override
    public void setup() {    // �ŏ��Ɉ�񂾂��Ă΂��֐��i�����ݒ�Ƃ������Ƃ���j
        minim = new Minim(this);
        roller = minim.loadSample("roller.wav", 2048);
        foot = minim.loadSample("foot.wav", 2048);

    }

    @Override
    public void draw() {    // ���C�����[�v�֐�

    }

    @Override
    public void stop(){
        roller.close();
        foot.close();
        minim.stop();

        super.stop();
    }

    public static void main(String[] args) {    // Java �̃��C���N���X
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