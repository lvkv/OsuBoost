import java.awt.Color;
import java.util.HashMap;

public final class Constants {

    private Constants() {}

    // APP
    public static final String APP_NAME = "osu!Boost";
    public static final Color NOTE = new Color(53,200, 255); // Light blue
    public static final Color NOTE_HOLD = new Color(0,255, 0); // Green
    public static final int FULL_HD = 1920;

    // GUI
    public static final String TITLE_FONT = "Tahoma";
    public static final String RESOLUTION_TEXT = "Resolution:\t";
    public static final String KEYS_TEXT = "\t  Keys:\t";
    public static final String APPLY_TEXT = "✔ Apply";
    public static final String START_TEXT = "⏩ Start";
    public static final String STOP_TEXT = "■ Stop";
    public static final String BOLD_STYLE = "-fx-font-weight: bold";

    public static final HashMap<String, Integer> RESOLUTIONS = new HashMap<String, Integer>(){{
        put("1920 x 1080", 1920);
        put("3840 x 2160", 3840);
        put("2560 x 1440", 2560);
        put("1280 x 720", 1280);
    }};

    public static final HashMap<String, Integer> MANIA_KEYS = new HashMap<String, Integer>(){{
        put("4 Key", 4);
        put("5 Key", 5);
        put("7 Key", 7);
    }};
}
