import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;
import processing.data.Table;
import processing.data.TableRow;

import java.awt.*;
import java.lang.Math;
//import processing.pdf.*;

public final class Misc extends PApplet {
    public Misc() {
//        table = loadTable("minard-data.csv", "header");
//        simpleTable = loadTable("minard-simple.csv", "header");
//        setTable(table);
//        fortIcon = loadImage("simpleWhiteFort23.png");
    }

//    public void AddImage() {
//        PImage map = loadImage("maps/newmaps/greylabellessMap1750.PNG");
//        image(map, 0, 0);
//    }

    public static float normalise(double value, double min, double max) {
        // zi = (xi – min(x)) / (max(x) – min(x))
        return (float) (value - min) / (float) (max - min);
    }

    public static float normalise(float value, float min, float max) {
        // zi = (xi – min(x)) / (max(x) – min(x))
        return (value - min) / (max - min);
    }

    public static float normalise(int value, int min, int max) {
        // zi = (xi – min(x)) / (max(x) – min(x))
        return (float) ((float) (value - min) / (float) (max - min));
    }

    public static float scale(float value, int scale, int padding) {
        return (value * scale) + padding;
    }

//    public static float log2 (float value) {
//        return (float) (Math.log(value) / (float) Math.log(2.0f));
//    }

    public static float log2(float f)
    {
        return (float)Math.floor(Math.log(f)/Math.log(2f));
    }
}

//
//    public void drawTextStroke(String text, float x, float y) {
//        fill(0);
//        for(int i = -1; i < 2; i++)
//        {
//            text(text, x+i,y);
//            text(text, x,y+i);
//        }
//        //noFill();
//    }
//
//    public void drawWhiteTextStroke(String text, float x, float y) {
//        fill(255);
//        for(int i = -1; i < 2; i++)
//        {
//            text(text, x+i,y);
//            text(text, x,y+i);
//        }
//        fill(0);
//    }
//
//    public float scaleToGraph(float value, int scale, int padding) {
//        return (value * scale) + padding;
//    }
//}
