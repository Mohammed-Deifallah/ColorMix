package com.example.lenovopc.colormix;

import android.graphics.Color;
import android.support.v4.graphics.ColorUtils;

import java.util.Random;

public class ColorGenerator {
    private Random random;

    public ColorGenerator() {
        random = new Random();
    }

    public int getRandomColor() {
        return Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));
    }

    public int getColorMix(int c1, int c2){
        return ColorUtils.blendARGB(c1, c2, 0.5F);
    }
}
