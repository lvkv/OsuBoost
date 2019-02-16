import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class OsuBoost {
    public static void main(String[] args) throws AWTException {
        int keys = 4; // Amount of keys
        int x_start = 500; // starting x position
        int x_gap = 120; // gap between x positions
        int y_pos = 960; // y positions across all pixels

        int[] keyCodes = {KeyEvent.VK_D, KeyEvent.VK_F, KeyEvent.VK_J, KeyEvent.VK_K};

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(keys);

        for (int i = 0; i <= 3; i++) {
            executor.scheduleAtFixedRate(new Point(x_start + x_gap * i, y_pos, keyCodes[i]), 0, 1, TimeUnit.MILLISECONDS);
        }
    }
}
