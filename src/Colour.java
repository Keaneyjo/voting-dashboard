import processing.core.PApplet;

public class Colour extends PApplet {

    public float r;
    public float g;
    public float b;
    public float [] rgb;

    public Colour(float r, float g, float b) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.rgb = new float [] {r, g, b};
    }
}
