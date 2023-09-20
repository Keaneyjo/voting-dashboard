public class ScatterBox {

    public static void draw(String xaxis, String yaxis, int winners) {
        float x = Main.processing.mouseX;
        float y = Main.processing.mouseY;
        Main.processing.fill(220);
        float width = Math.max(Main.processing.textWidth("x-axis: " + xaxis), Main.processing.textWidth("y-axis: " + yaxis)) + 9;
        Main.processing.rect(x, y, width, 45);
        Main.processing.fill(0);
        x += 2;
        Main.processing.textAlign(Main.processing.LEFT, Main.processing.TOP);
        Main.processing.text("Winners: " + winners, x + 5, y);
        Main.processing.text("x-axis: " + xaxis, x + 5, y + 13);
        Main.processing.text("y-axis: " + yaxis, x + 5, y + 26);
        Main.processing.textAlign(Main.processing.CENTER, Main.processing.CENTER);
    }
}
