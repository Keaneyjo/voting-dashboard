
public class Constants {
    public static final int SCREEN_WIDTH =  1920; // 1600 //842 // 3508 / 3;
    public static final int SCREEN_HEIGHT =  1080; // 800 //595 // 2480 / 3;

    public static final int GRAPH_PADDING = 100;
    public static final int GRAPH_WIDTH_PADDING = 200;
    public static final int GRAPH_HEIGHT_PADDING = 150;
    public static final int GRAPH_WIDTH = SCREEN_WIDTH - (GRAPH_WIDTH_PADDING * 2);
    public static final int GRAPH_HEIGHT = SCREEN_HEIGHT - (GRAPH_HEIGHT_PADDING * 2);

    public static final int CIRCLE_MAX_SIZE_ALL = 45;
    public static final int CIRCLE_MIN_SIZE_ALL = 30;

    public static final int CIRCLE_MAX_SIZE = 30;
    public static final int CIRCLE_MIN_SIZE = 15;

    public static final int CIRCLE_MAX_SIZE_2 = 120;
    public static final int CIRCLE_MIN_SIZE_2 = 15;

    //public static final int YEAR_BOX_WIDTH = (int) (Math.floor((double)Constants.GRAPH_HEIGHT / 12) * 2);
    public static final int YEAR_BOX_WIDTH = (int) (Math.floor((double)Constants.GRAPH_WIDTH / 12));
    public static final int HALF_YEAR_BOX_WIDTH = (int) (Math.floor((double)Constants.YEAR_BOX_WIDTH / 2));

    public static final int GRAPH_2_WIDTH_PADDING = 120;
    public static final int GRAPH_2_HEIGHT_PADDING = 100;
    public static final int GRAPH_2_WIDTH = SCREEN_WIDTH - (GRAPH_2_WIDTH_PADDING * 2);
    public static final int GRAPH_2_HEIGHT = SCREEN_HEIGHT - (GRAPH_2_HEIGHT_PADDING * 2);

    public static final int GRAPH_4_X = 1200;
    public static final int GRAPH_4_Y = 25;
    public static final float GRAPH_4_HEIGHT = (float) SCREEN_HEIGHT / 2;
    public static final float GRAPH_4_WIDTH = (float) SCREEN_WIDTH / 2;

    public static final int GRAPH_5_X = 1200;
    public static final int GRAPH_5_Y = 550;
}
