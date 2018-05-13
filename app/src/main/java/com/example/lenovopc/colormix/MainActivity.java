package com.example.lenovopc.colormix;

import android.annotation.SuppressLint;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ColorGenerator cg;
    private LinearLayout back;
    private TextView score, center;
    private GradientDrawable circles[], g[];
    private Random random;
    private ImageView[] ops;
    private int correct_index, correct_color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView circle1 = findViewById(R.id.circle1);
        ImageView circle2 = findViewById(R.id.circle2);
        back = findViewById(R.id.back);
        ops = new ImageView[3];
        ops[0] = findViewById(R.id.op1);
        ops[0].setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                check(0);
            }
        });
        ops[1] = findViewById(R.id.op2);
        ops[1].setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                check(1);
            }
        });
        ops[2] = findViewById(R.id.op3);
        ops[2].setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                check(2);
            }
        });
        score = findViewById(R.id.score);
        center = findViewById(R.id.center);
        cg = new ColorGenerator();
        random = new Random();
        circles = new GradientDrawable[2];
        for (int i = 0; i < 2; i++) {
            circles[i] = new GradientDrawable();
            circles[i].setGradientType(GradientDrawable.LINEAR_GRADIENT);
            circles[i].setShape(GradientDrawable.OVAL);
            circles[i].setSize(50, 50);
        }
        circle1.setImageDrawable(circles[0]);
        circle2.setImageDrawable(circles[1]);
        g = new GradientDrawable[3];
        for (int i = 0; i < 3; i++) {
            g[i] = new GradientDrawable();
            g[i].setGradientType(GradientDrawable.LINEAR_GRADIENT);
            g[i].setShape(GradientDrawable.OVAL);
            g[i].setSize(50, 50);
            ops[i].setImageDrawable(g[i]);
        }
        newGame();
    }

    void newGame() {
        int c1 = cg.getRandomColor();
        int c2 = cg.getRandomColor();
        correct_color = cg.getColorMix(c1, c2);
        int c4 = cg.getRandomColor();
        int c5 = cg.getRandomColor();
        createGradients(c1, circles[0]);
        createGradients(c2, circles[1]);
        createOptions(correct_color, c4, c5);
    }

    void createGradients(int color,/* ImageView view,*/ GradientDrawable g) {
        g.setColor(color);
    }

    void createOptions(int c1, int c2, int c3) {
        int correct = getRandomIndex();
        correct_index = correct;
        int wrong1 = (correct ^ 3) % 2;
        int wrong2 = (correct ^ 3) & 2;
        for (int i = 0; i < 3; i++) {
            ops[i].setVisibility(View.VISIBLE);
        }
        createGradients(c1, g[correct]);
        createGradients(c2, g[wrong1]);
        createGradients(c3, g[wrong2]);
    }


    int getRandomIndex() {
        return Math.abs(random.nextInt() % 3);
    }

    void check(int index) {
        if (index == correct_index) {
            correct();
        } else {
            wrong();
        }
    }

    @SuppressLint("SetTextI18n")
    void correct() {
        Integer s = Integer.parseInt(score.getText().toString());
        s++;
        score.setText(s.toString());
        center.setText("Wow !!");
        newGame();
    }

    @SuppressLint("SetTextI18n")
    void wrong() {
        center.setText("Try Again :(");
        showCorrect();
        Integer s = Integer.parseInt(score.getText().toString());
        s--;
        score.setText(s.toString());
        newGame();
    }

    void showCorrect() {
        back.setBackgroundColor(correct_color);
    }
}
