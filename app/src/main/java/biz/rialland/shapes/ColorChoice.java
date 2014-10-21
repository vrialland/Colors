package biz.rialland.shapes;

import android.graphics.Color;

import java.util.Random;

public enum ColorChoice {
    BLUE(Color.BLUE),
    GREEN(Color.GREEN),
    RED(Color.RED),
    YELLOW(Color.YELLOW);

    private int code;

    private ColorChoice(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static int getRandomColor() {
        Random r = new Random();
        ColorChoice[] values = ColorChoice.values();
        return values[r.nextInt(values.length)].getCode();
    }
}