import java.awt.*;

public class Point {
    private Robot robot;
    private int x; // x position of pixel
    private int y; // y position of pixel

    public Point(int x, int y) throws AWTException {
        this.x = x;
        this.y = y;
        robot = new Robot();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void track() {

    }

    public Color getColor() {
        return robot.getPixelColor(this.x, this.y);
    }
}
