import java.awt.*;

public class Point implements Runnable{
    private Robot robot; // Handles reading screen and user input
    private int x; // x position of pixel
    private int y; // y position of pixel
    private int keyCode; // keyCode representing key to be pressed
    private boolean isHeld; // "are we holding down a note right now"?


    public Point(int x, int y, int keyCode) throws AWTException {
        this.x = x;
        this.y = y;
        this.keyCode = keyCode;
        robot = new Robot();
    }

    @Override
    public void run() {
        Color current_color = getColor();
        if (isHeld) { // If we're sustained
            if (current_color.equals(Color.BLACK)) { // Black background means stop sustaining
                release();
                isHeld = false;
            }
        }
        else if (current_color.equals(Constants.NOTE)){  // If we see a single note, hit the key
            press();
            release();
        }
        else if (current_color.equals(Constants.NOTE_HOLD)){ // If we see a sustained note, press the key without releasing
            press();
            isHeld = true;
        }
    }

    public void press() {
        robot.keyPress(keyCode);
    }

    public void release() {
        robot.keyRelease(keyCode);
    }

    private Color getColor() {
        return robot.getPixelColor(this.x, this.y);
    }
}
