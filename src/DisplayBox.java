public class DisplayBox {

    // Constituency
    // Total Electorate
    // Turnout
    // Winners [] - Name, Surname, Party
    public static void draw(float x, float y, String constituency, int electorate, float turnout,  String [] parties, String [] names) {
        Main.processing.stroke(0);
        Main.processing.fill(240);
        Main.processing.rect(x, y, 400, 75 + (names.length * 20));

        Main.processing.fill(0);
        Main.processing.textSize(17);
        Main.processing.textAlign(Main.processing.LEFT, Main.processing.TOP);
        String constituencyName = "Constituency: " + constituency;
        Main.processing.text(constituencyName, x + 5, y + 5);
        Main.processing.text("Total Electorate: " + electorate, x + 5, y + 25);
        Main.processing.text("Turnout: " + turnout + "%", x + 5, y + 45);

        y += 45;
        y += 20;
        for(int i = 0; i < names.length; i++) {
                Colour partyColour = Main.adPartyColours.get(parties[i]);
                Main.processing.fill(partyColour.r, partyColour.g, partyColour.b);
            Main.processing.square(x + 5, y + 5, 10);
            Main.processing.text(parties[i] + ": " + names[i], x + 20, y);
            y += 20;
        }

    }
}


