import java.awt.*;

public class OsuBoost {
    public static void main(String[] args) throws AWTException {
        int keys = 4; // Amount of keys
        int x_start = 500; // starting x position
        int x_gap = 120; // gap between x positions
        int y_pos = 960; // y positions across all pixels

        Point[] pixels = new Point[keys];

        for (int i = 0; i < 3; i++) {
            pixels[i] = new Point(x_start + x_gap * i, y_pos);
            pixels[i].track();
        }
    }
}
