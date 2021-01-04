package Project;    // �d�l�p�b�P�[�W�i�t�H���_�j�̎w��

import processing.core.*;

import ddf.minim.*;  // minim ���C�u�����̃C���|�[�g

public class Sound extends PApplet {    // �v���Z�b�V���O�̃��C���N���X

    Minim minim;  // Minim �^�ϐ��ł��� minim �̐錾
    AudioPlayer player;  // �T�E���h�i�[�p�̕ϐ�

    @Override
    public void settings() {    // �T�C�Y�͂����ɏ����炵��
        size(800, 800);  // �f�B�X�v���C�T�C�Y
    }

    @Override
    public void setup() {    // �ŏ��Ɉ�񂾂��Ă΂��֐��i�����ݒ�Ƃ������Ƃ���j
        minim = new Minim(this);  // ������
        player = minim.loadFile("game.wav");  // �t�@�C����ǂݍ���
        player.play();  // �Đ�
    }

    @Override
    public void draw() {    // ���C�����[�v�֐�

    }

    @Override
    public void stop(){
        player.close();  // �T�E���h�f�[�^�̏I��
        minim.stop();
        super.stop();
    }

    public static void main(String[] args) {    // Java �̃��C���N���X
        PApplet.main("Project.Sound");

    }
}