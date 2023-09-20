import java.util.HashMap;

public class PartyLegend {

    float x;
    float y;

    public PartyLegend(int x, int y) {
        this.x = x;
        this.y = y - 20;

    }

    public void draw() {
        Main.processing.textSize(17);
        Main.processing.fill(240);
        Main.processing.rect(x, y, Main.processing.textWidth("Green Party/ Comhaontas Glas") + 35, 250);

        Main.processing.textAlign(Main.processing.LEFT, Main.processing.CENTER);

        float x2 = x + 5;
        float y2 = y + 12;
        Main.processing.fill(0);
        Main.processing.text("Party Colours Legend", x2, y2);
        y2 += 20;
        for(int i = 0; i < winningPartiesName.length; i++) {
            Colour colour = adPartyColours.get(winningPartiesName[i]);
            Main.processing.fill(colour.r, colour.g, colour.b);
            Main.processing.rect(x2, y2, 14, 14);
            Main.processing.fill(0);
            Main.processing.text(winningPartiesName[i], x2 + 18, y2 + 5);
            y2 += 24;
        }
    }

    public String[] winningPartiesName = new String[]{
            "Aontú",
            "Fianna Fáil",
            "Fine Gael",
            "Green Party/ Comhaontas Glas",
            "Independents 4 Change",
            //"Irish Democratic Party",
            // "Irish Freedom Party",
            // "Non-Party", "Renua Ireland",
            "Sinn Féin",
            "Social Democrats",
            "Solidarity - People Before Profit",
            "The Labour Party",
            //"The National Party - An Páirtí Náisiúnta", "United People",
            //"Workers Party"
    };

    public String[] partiesName = new String[]{
            "Aontú", "Fianna Fáil", "Fine Gael", "Green Party/ Comhaontas Glas", "Independents 4 Change",
            "Irish Democratic Party", "Irish Freedom Party", "Non-Party", "Renua Ireland",
            "Sinn Féin", "Social Democrats", "Solidarity - People Before Profit",
            "The Labour Party", "The National Party - An Páirtí Náisiúnta", "United People",
            "Workers Party"
    };

    public String[] partyAbbreviations = new String[]{
            "A", "FF", "FG", "GP", "I",
            "DP", "FP", "NO", "RI",
            "SF", "SD", "PBP",
            "LA", "NP", "UP",
            "WP"
    };

    public HashMap<String, Colour> adPartyColours = new HashMap<String, Colour>() {{
        put("Aontú", new Colour(253, 144, 77)); //Aontu
        put("Fianna Fáil", new Colour(0, 193, 76)); // FF
        put("Fine Gael", new Colour(0, 177, 220)); // FG
        put("Green Party/ Comhaontas Glas", new Colour(127, 205, 0)); // Greens
        put("Independents 4 Change", new Colour(187, 187, 187)); // I4C
        put("Irish Democratic Party", new Colour(29, 42, 94)); // Irish Democratic Party
        put("Irish Freedom Party", new Colour(0, 60, 0)); // Irish Freedom Party
        put("Non-Party", new Colour(255, 128, 255)); // Non-Party
        put("Renua Ireland", new Colour(128, 64, 64)); // Renua Ireland
        put("Sinn Féin", new Colour(0, 156, 120)); // Sinn Fein
        put("Social Democrats", new Colour(113, 30, 109)); // Social Democrats
        put("Solidarity - People Before Profit", new Colour(87, 1, 2)); // PBP
        put("The Labour Party", new Colour(234, 26, 59)); // Labour
        put("The National Party - An Páirtí Náisiúnta", new Colour(64, 128, 128)); // Nationals
        put("United People", new Colour(200, 200, 0)); // United People
        put("Workers Party", new Colour(155, 0, 0)); // Workers
    }};

    public Colour[]  partyColours = new Colour[]{
            new Colour(253, 144, 77), //Aontu
            new Colour(0, 193, 76), // FF
            new Colour(0, 177, 220), // FG
            new Colour(127, 205, 0), // Greens
            new Colour(187, 187, 187), // I4C
            new Colour(29, 42, 94), // Irish Democratic Party
            new Colour(0, 60, 0), // Irish Freedom Party
            new Colour(255, 128, 255), // Non-Party
            new Colour(128, 64, 64), // Renua Ireland
            new Colour(0, 156, 120), // Sinn Fein
            new Colour(113, 30, 109), // Social Democrats
            new Colour(87, 1, 2), // PBP
            new Colour(234, 26, 59), // Labour
            new Colour(64, 128, 128), // Nationals
            new Colour(200, 200, 0), // United People
            new Colour(155, 0, 0) // Workers
    };
}
