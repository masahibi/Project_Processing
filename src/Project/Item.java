package Project;    // �d�l�p�b�P�[�W�i�t�H���_�j�̎w��

import processing.core.*;    // �v���Z�b�V���O�̃C���|�[�g

public class Item extends PApplet {    // �v���Z�b�V���O�̃��C���N���X
    int count;
    int  testlife;

    @Override
    public void settings() {    // �T�C�Y�͂����ɏ����炵��
        size(800, 800);  // �f�B�X�v���C�T�C�Y

    }

    @Override
    public void setup() {    // �ŏ��Ɉ�񂾂��Ă΂��֐��i�����ݒ�Ƃ������Ƃ���j
        count = 0;
        testlife = 0;
    }

    @Override
    public void draw() {    // ���C�����[�v�֐�
        background(255);
        if (count == 300) {
            rect(100, 100, 25, 25);

        }
    }

    public static void main(String[] args) {    // Java �̃��C���N���X
        PApplet.main("Project.Item");

    }
}