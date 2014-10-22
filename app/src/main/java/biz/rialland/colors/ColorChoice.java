package biz.rialland.colors;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class ColorChoice {
    private final static ShapeColor[] colors = {
            new ShapeColor(Color.RED, "red"),
            new ShapeColor(Color.GREEN, "green"),
            new ShapeColor(Color.BLUE, "blue"),
            new ShapeColor(Color.YELLOW, "yellow"),
            new ShapeColor(Color.BLACK, "black"),
            new ShapeColor(Color.rgb(255, 0, 255), "violet")
    };

    public static ShapeColor[] getColors() {
        ShapeColor[] tmp = colors.clone();
        Collections.shuffle(Arrays.asList(colors));
        return tmp;
    }

    public static ShapeColor getRandomColor() {
        return colors[new Random().nextInt(colors.length)];
    }

    public static int nbItems() {
        return colors.length;
    }
}
