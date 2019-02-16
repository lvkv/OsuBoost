import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class OsuBoost {
    int keys = 4; // Amount of keys
    int x_start = 500; // starting x position
    int x_gap = 120; // gap between x positions
    int y_pos = 960; // y positions across all pixels
    int[] keyCodes;
    boolean running = false; // osu is running
    ScheduledExecutorService executor;

    public OsuBoost (){}

    public void apply (int resolution, int keys) {
        if (running)
            stop();

        this.keys = keys;

        if (keys == 4) {
            x_start = 500;
            x_gap = 120;
        } else if (keys == 7) {
            x_start = 450;
            x_gap = 90;
        }

        x_start *= resolution / Constants.FULL_HD;
        x_gap *= resolution / Constants.FULL_HD;
        y_pos *= resolution / Constants.FULL_HD;

        if (keys == 4)
            keyCodes = new int[]{KeyEvent.VK_D, KeyEvent.VK_F, KeyEvent.VK_J, KeyEvent.VK_K};
        else if (keys == 7)
            keyCodes = new int[]{KeyEvent.VK_S, KeyEvent.VK_D, KeyEvent.VK_F, KeyEvent.VK_SPACE, KeyEvent.VK_J, KeyEvent.VK_K, KeyEvent.VK_L};

    }

    public void start() throws AWTException {
        if (!running) {
            executor = Executors.newScheduledThreadPool(keys);

            for (int i = 0; i <= keys - 1; i++)
                executor.scheduleAtFixedRate(new Point(x_start + x_gap * i, y_pos, keyCodes[i]), 0, 1, TimeUnit.MILLISECONDS);
            running = true;
        }
    }

    public void stop() {
        if (running) {
            executor.shutdownNow();
            running = false;
        }
    }
}
