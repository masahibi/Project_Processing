package Project;    // �d�l�p�b�P�[�W�i�t�H���_�j�̎w��

import processing.core.*;    // �v���Z�b�V���O�̃C���|�[�g

public class Image extends PApplet {    // �v���Z�b�V���O�̃��C���N���X

    PImage back1;  // �摜�̌^
    PImage back2;
    int x = 0;
    int y = 0;

    @Override
    public void settings() {    // �T�C�Y�͂����ɏ����炵��
        size(800, 800);  // �f�B�X�v���C�T�C�Y

    }

    @Override
    public void setup() {    // �ŏ��Ɉ�񂾂��Ă΂��֐��i�����ݒ�Ƃ������Ƃ���j
        System.out.println(11111);

        back1 = loadImage("Project/background.png");  // �摜�̓ǂݍ���
        back1.resize(800, 800);  // �T�C�Y�ύX
        back2 = loadImage("Project/background.png");
        back2.resize(800, 800);


    }

    @Override
    public void draw() {    // ���C�����[�v�֐�
        background(255);    // �w�i�F�A��
        image(back1, x, y);  // �摜�̕`��
        image(back2, x, y - 800);
        if (y >= 800){  // �����摜�������z������
            y = 0;  // �����W���O��
        }
        y++;  // +1 �����₷
    }

    public static void main(String[] args) {    // Java �̃��C���N���X
        PApplet.main("Project.Image");

    }

}