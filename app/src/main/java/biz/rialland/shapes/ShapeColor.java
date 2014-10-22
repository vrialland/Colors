package biz.rialland.shapes;

public class ShapeColor {
    private int code;
    private String name;

    public ShapeColor(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() { return code; }

    public String getName() { return name; }
}