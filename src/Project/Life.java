package Project;    // �d�l�p�b�P�[�W�i�t�H���_�j�̎w��

import processing.core.*;    // �v���Z�b�V���O�̃C���|�[�g

public class Life extends PApplet {    // �v���Z�b�V���O�̃��C���N���X
    int life;

    @Override
    public void settings() {    // �T�C�Y�͂����ɏ����炵��
        size(800, 800);  // �f�B�X�v���C�T�C�Y

    }

    @Override
    public void setup() {    // �ŏ��Ɉ�񂾂��Ă΂��֐��i�����ݒ�Ƃ������Ƃ���j
        life = 3;
    }

    @Override
    public void draw() {    // ���C�����[�v�֐�
        background(255);
        fill(255, 0, 0);
        noStroke();
        if (life >= 1) {
            ellipse(50, 50, 50, 50);
            if (life >= 2) {
                ellipse(110, 50, 50, 50);
                if (life >= 3) {
                    ellipse(170, 50, 50, 50);
                }
            }
        }
    }

    public static void main(String[] args) {    // Java �̃��C���N���X
        PApplet.main("Project.Life");

    }
}