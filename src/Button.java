import processing.core.PApplet;

public class Button extends PApplet {

    String name;
    float x;
    float y;
    float height;
    float width;
    Colour colour;
    Colour borderColour;
    boolean clicked;
    Colour borderHighlightColour;

    // x, y are the top left corner of the button
    public Button(String name, float x, float y, float width, float height, Colour colour, Colour borderColour) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.colour = colour;
        this.borderColour = borderColour;
        this.clicked = false;
        this.borderHighlightColour = new Colour(0, 0, 0);
    }

    // x, y are the top left corner of the button
    public Button(String name, float x, float y, float width, float height, Colour colour, Colour borderColour, Colour borderHighlightColour) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.colour = colour;
        this.borderColour = borderColour;
        this.clicked = false;
        this.borderHighlightColour = borderHighlightColour;
    }

    public void draw() {
        highlight();
        Main.processing.fill(colour.r, colour.g, colour.b);
        Main.processing.rect(x, y, width, height);
        Main.processing.textAlign(Main.processing.CENTER, Main.processing.CENTER);
        Main.processing.fill(0);
        Main.processing.text(name, x + (width/2), y + (height / 2));
        Main.processing.stroke(0);
        Main.processing.textAlign(Main.processing.LEFT, Main.processing.CENTER);
    }

    public void highlight() {
        if(!clicked) {
            Main.processing.fill(255);
            if(mouseWithin()) {
                Main.processing.stroke(borderHighlightColour.r, borderHighlightColour.g, borderHighlightColour.b);
            } else {
                Main.processing.stroke(borderColour.r, borderColour.g, borderColour.b);
            }
        } else {
            Main.processing.fill(200);
            if(mouseWithin()) {
                Main.processing.stroke(borderColour.r, borderColour.g, borderColour.b);
            } else {
                Main.processing.stroke(borderHighlightColour.r, borderHighlightColour.g, borderHighlightColour.b);
            }
        }

    }

    public boolean mouseWithin() {
        if(x < Main.processing.mouseX && Main.processing.mouseX < x + width) {
            if (y < Main.processing.mouseY && Main.processing.mouseY < y + height) {
                return true;
            }
        }
        return false;
    }
}
