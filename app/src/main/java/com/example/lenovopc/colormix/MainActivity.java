package com.example.lenovopc.colormix;

import android.graphics.drawable.GradientDrawable;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private ColorGenerator cg;
    private ImageView circle1, circle2, ops[];
    private TextView score, center;
    private Random random;
    private int correct_index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        circle1 = findViewById(R.id.circle1);
        circle2 = findViewById(R.id.circle2);
        //GradientDrawable g1, g2, g3, g4, g5;
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
        newGame();
    }

    void createGradients(int color, ImageView view) {
        GradientDrawable g = new GradientDrawable();
        g.setColor(color);
        g.setGradientType(GradientDrawable.LINEAR_GRADIENT);
        g.setShape(GradientDrawable.OVAL);
        g.setSize(50, 50);
        view.setImageDrawable(g);
    }

    int getRandomIndex() {
        return Math.abs(random.nextInt() % 3);
    }

    void createOptions(ImageView[] ops, int c1, int c2, int c3) {
        int correct = getRandomIndex();
        correct_index = correct;
        int wrong1 = (correct ^ 3) % 2;
        int wrong2 = (correct ^ 3) & 2;
        createGradients(c1, ops[correct]);
        createGradients(c2, ops[wrong1]);
        createGradients(c3, ops[wrong2]);
    }

    void check(int index) {
        if (index == correct_index) {
            correct();
        } else {

                wrong();

        }
    }

    void correct() {
        Integer s = Integer.parseInt(score.getText().toString());
        s++;
        score.setText(s.toString());
        center.setText("Wow !!");
        newGame();
    }

    void wrong(){
        center.setText("Try Again :(");
        Integer s = Integer.parseInt(score.getText().toString());
        s--;
        score.setText(s.toString());
        newGame();
    }

    void newGame() {
        int c1 = cg.getRandomColor();
        int c2 = cg.getRandomColor();
        int c3 = cg.getColorMix(c1, c2);
        int c4 = cg.getRandomColor();
        int c5 = cg.getRandomColor();
        createGradients(c1, circle1);
        createGradients(c2, circle2);
        createOptions(ops, c3, c4, c5);
    }
}
