package Project;

import processing.core.PApplet;

public class Score extends PApplet {    // �v���Z�b�V���O�̃��C���N���X
    int score;

    @Override
    public void settings() {    // �T�C�Y�͂����ɏ����炵��
        size(800, 800);  // �f�B�X�v���C�T�C�Y

    }

    @Override
    public void setup() {    // �ŏ��Ɉ�񂾂��Ă΂��֐��i�����ݒ�Ƃ������Ƃ���j

    }

//    @Override

    public void draw() {    // ���C�����[�v�֐�
        background(255);    // �w�i�F�A��
        fill(255);
        strokeWeight(5);
        rect(50, 50, 80, 40);
        fill(0);
        textSize(30);
        textAlign(RIGHT);
        text(score, 120, 80);
        score++;
    }

    public static void main(String[] args) {    // Java �̃��C���N���X
        PApplet.main("Project.Score");

    }
}
