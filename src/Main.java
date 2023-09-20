import processing.core.PApplet;
import processing.core.PShape;

import java.util.ArrayList;
import java.util.HashMap;
//import processing.pdf.*;

public class Main extends PApplet {
    public static PApplet processing;
//    public static Table table;
//
//    int window = 0;
//
//    LifeExpectency lifeExpectency;
//    Population population;
//    All all;
//
//    Storage storage;

    public static void main (String[] args) {
        System.out.print("Hello World!");
        PApplet.main("Main", args);
    }

//    public void setup() {
//
//        table = loadTable("gapminder.csv", "header");
//        storage = new Storage(table);
//
//        loadFlags();
//        lifeExpectency = new LifeExpectency(storage, flags);
//        population = new Population(storage, flags);
//        all = new All(storage, flags);
//    }
//
//    public void settings() {
//        processing = this;
//        processing.size(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
//        //processing.size(SCREEN_WIDTH, SCREEN_HEIGHT,  "processing.pdf.PGraphicsPDF", "[ANIMATED] assignment1.2.pdf");
//        fullScreen();
//    }
//
//    HashMap<String, PImage> flags;
//    PImage flag;
//
//    public void loadFlags() {
//        flags = new HashMap<String, PImage>();
//        int[] years = this.storage.getYear();
//        String[] countries = this.storage.getCountries();
//        String text = "";
//
//        for(int i = 0; i < years.length; i++) {
//            if(years[i] == 1952) {
//                text = "flags/" + countries[i] + ".png";
//                PImage flag = loadImage(text);
//                flags.put(countries[i], flag);
//            }
//        }
//
//        System.out.print("No problems! :)");
//    }

    public int NC = 39; // number of constituencies

    GraphOne graphOne;
    GraphTwo graphTwo;
    GraphThree graphThree;
    GraphFour graphFour;
    GraphFive graphFive;
    ///////////////////////
    public static PShape ireland;
    public static PShape michigan;
    public static PShape ohio;

    public static PShape dublin;
    public static Constituency Dublin;
    public static Constituency Dublin2;

    public static PShape don;
    public static Constituency [] constituencies;
    public static Constituency [] constituenciesFive;

    public static HashMap<String, ArrayList<String>> constituencyStats;
    public static HashMap<String, ArrayList<String>> constituencyFiveStats;


    HashMap<String, Float[]> countiesxy = new HashMap<String, Float[]>(){
        {
            put("Donegal", new Float[] {153.6f, 17.9f});
            put("Sligo Leitrim", new Float[] {128.5f, 111.9f});
            put("Mayo", new Float[] {50.2f, 142.42f});
            put("Roscommon Galway", new Float[] {144.8f, 188.4f});
            put("Galway West", new Float[] {44.3f, 227.9f});
            put("Galway East", new Float[] {121.2f, 226.6f});
            put("Longford Westmeath", new Float[] {207.5f, 192.6f});
            put("Meath West", new Float[] {256.6f, 210f});
            put("Meath East", new Float[] {284.9f, 201.9f});
            put("Cavan Monaghan", new Float[] {206.1f, 134.9f});
            put("Louth", new Float[] {303.5f, 171.5f});
            put("Laois Offaly", new Float[] {205f, 255.3f});
            put("Kildare South", new Float[] {265.2f, 252.7f});
            put("Wicklow", new Float[] {298.3f, 277.8f});
            put("Clare", new Float[] {67.1f, 287.0f});
            put("Tipperary", new Float[] {176f, 286.9f});
            put("Carlow Kilkenny", new Float[] {233.9f, 316f});
            put("Wexford", new Float[] {283.3f, 329.6f});
            put("Waterford", new Float[] {198.4f, 383.8f});
            put("Limerick City", new Float[] {156.8f, 331.9f});
            put("Limerick County", new Float[] {109.1f, 344.5f});
            put("Kerry", new Float[] {9.5f, 353.8f});
            put("Cork North West", new Float[] {108.3f, 380.2f});
            put("Cork South West", new Float[] {48.7f, 446.5f});
            put("Cork South Central", new Float[] {166.9f, 439.7f});
            put("Cork North Central", new Float[] {150f, 409.2f});
            put("Cork East", new Float[] {151.3f, 388.3f});
            put("Kildare North", new Float[] {282.7f, 251.1f});
            put("Dublin Fingal", new Float[] {424.3f, 237f});
            put("Dublin West", new Float[] {413.4f, 330f});
            put("Dublin North West", new Float[] {451.8f, 358.7f});
            put("Dublin Bay North", new Float[] {489.3f, 356.1f});
            put("Dublin Central", new Float[] {467.7f, 375.5f});
            put("Dublin Mid West", new Float[] {391.3f, 382.8f});
            put("Dublin South West", new Float[] {422.4f, 409.8f});
            put("Dublin Rathdown", new Float[] {473.3f, 410.2f});
            put("Dublin Bay South", new Float[] {471.5f, 392.2f});
            put("Dublin South Central", new Float[] {442.9f, 389.2f});
            put("Dun Laoghaire", new Float[] {499.7f, 411f});

        }};

    public static AdvancedStorage advancedStorage;

    public static HashMap<String, Colour> adPartyColours = new HashMap<String, Colour>(){{
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

    public static PartyLegend partyLegend = new PartyLegend(Constants.SCREEN_WIDTH / 2 - 100, 75);


    public static Constituency dublinBayNorth;

    public void setup() {
//        ireland = loadShape("imgs/ireland3.svg");
        ireland = loadShape("imgs/cons_final.svg");
        //ireland = loadShape("imgs/constituencies.svg");
        michigan = ireland.getChild("Sligo Leitrim");
        ohio = ireland.getChild("Louth");

        float dBNx = 100 + 600 + 260;
        float dBNy = (430 - 30);
        dublinBayNorth = new Constituency("Dublin Bay North Legend", loadShape("imgs/counties/Waterford.svg"), dBNx, dBNy, 0, 0, constituencyStats, 0);


        don = loadShape("imgs/counties/Donegal.svg");

        ohio.disableStyle();
        michigan.disableStyle();

        // load data
        Storage storage = new Storage(loadTable("csv/graph_one.csv", "header"));
        graphOne = new GraphOne(storage);
        Storage2 storage2 = new Storage2(loadTable("csv/graph_one_final_two.csv", "header"));
        graphTwo = new GraphTwo(storage2);

        Storage3 storage3 = new Storage3(loadTable("csv/graph_one_final_two.csv", "header"), loadTable("csv/2020_constituency_details.csv", "header"));
        graphThree = new GraphThree(storage2, storage3);

        advancedStorage = new AdvancedStorage(loadTable("csv/plurality.csv", "header"));

        graphFour = new GraphFour(storage2, storage3, advancedStorage);

        graphFive = new GraphFive(storage2, storage3, advancedStorage);

        createGraphFourConstituencies(storage2, storage3);

        createGraphFiveConstituencies(storage2, storage3, advancedStorage);

        graphFive.readyGraph();

        for(int i = 0; i < constituencies.length; i++) {
            constituencies[i].setWinners();
        }
        System.out.print("Winners set.");
    }

    public void createGraphFourConstituencies(Storage2 storage2, Storage3 storage3) {
        constituencyStats = new HashMap<String, ArrayList<String>>();

        // HashMap<ConstituencyName, ArrayList<String>> constituencyStats =
        // stats = [party colour] Party - FirstName, Surname
        // string [6];
        for(int i = 0; i < storage2.result.length; i++) {
            String constituencyNameTemp = storage2.constituency[i];
            ArrayList<String> stats;

            if(storage2.result[i].equals("Elected")) {
                if (constituencyStats.containsKey(constituencyNameTemp)) {
                    stats = constituencyStats.get(constituencyNameTemp);
                    stats.add(storage2.party[i]);
                    stats.add(storage2.firstname[i] + " " + storage2.surname[i]);
                    constituencyStats.put(constituencyNameTemp, stats);
                } else {
                    stats = new ArrayList<String>();
                    stats.add(storage2.party[i]);
                    stats.add(storage2.firstname[i] + " " + storage2.surname[i]);
                    constituencyStats.put(constituencyNameTemp, stats);
                }
            }

        }

        constituencies = new Constituency[NC];
        PShape temp;
        for(int i = 0; i < constituencies.length; i++) {
            String location = "imgs/counties/" + storage3.ConstituencyName[i] + ".svg";
            Float[] tempCoords = countiesxy.get(storage3.ConstituencyName[i]);
            temp = loadShape(location);
            //temp.enableStyle();
            constituencies[i] = new Constituency(storage3.ConstituencyName[i], temp, tempCoords[0] + Constants.GRAPH_4_X, tempCoords[1] + Constants.GRAPH_4_Y, storage3.totalElectorate[i], storage3.totalPoll[i], constituencyStats, storage3.numSeats[i]);
        }

        Dublin = new Constituency("Dublin", loadShape("imgs/counties/Dublin.svg"), Constants.GRAPH_4_X + 315.7f, Constants.GRAPH_4_Y + 228.5f, 0, 0, constituencyStats, 0);

    }

    // need to add the parites to the candidates first;
    // need to get the number of winners per const
    public void createGraphFiveConstituencies(Storage2 storage2, Storage3 storage3, AdvancedStorage advancedStorage) {
//        constituencyStats = new HashMap<String, ArrayList<String>>();
//
// HashMap<ConstituencyName, ArrayList<String>> constituencyStats =
// stats = [party colour] Party - FirstName, Surname
// string [6];
//        for(int i = 0; i < storage2.result.length; i++) {
//            String constituencyNameTemp = storage2.constituency[i];
//            ArrayList<String> stats;
//
//            if(storage2.result[i].equals("Elected")) {
//                if (constituencyStats.containsKey(constituencyNameTemp)) {
//                    stats = constituencyStats.get(constituencyNameTemp);
//                    stats.add(storage2.party[i]);
//                    stats.add(storage2.firstname[i] + " " + storage2.surname[i]);
//                    constituencyStats.put(constituencyNameTemp, stats);
//                } else {
//                    stats = new ArrayList<String>();
//                    stats.add(storage2.party[i]);
//                    stats.add(storage2.firstname[i] + " " + storage2.surname[i]);
//                    constituencyStats.put(constituencyNameTemp, stats);
//                }
//            }
//
//        }

        constituencyFiveStats = new HashMap<String, ArrayList<String>>();

        // first get the winners in each constituency
        int seats;
        for(String constituency : advancedStorage.pluralityWinnersPerConstituency.keySet()) {
            ArrayList<CandidateRoundOne> winners = advancedStorage.pluralityWinnersPerConstituency.get(constituency);
            seats = storage3.seatsPerConstituency.get(constituency);

            String party;
            for(int i = 0; i < seats; i++) {
                CandidateRoundOne candidateRoundOne = winners.get((winners.size() - 1) - i); // gets each of the winners for this round
                party = storage2.candidatesParty.get(candidateRoundOne.name + " " + candidateRoundOne.id);
                candidateRoundOne.setParty(party);
                candidateRoundOne.setResult("Elected");

                ArrayList<String> stats;
                if (constituencyFiveStats.containsKey(constituency)) {
                    stats = constituencyFiveStats.get(constituency);
                    stats.add(party);
                    stats.add(candidateRoundOne.name);
                    constituencyFiveStats.put(constituency, stats);
                } else {
                    stats = new ArrayList<String>();
                    stats.add(party);
                    stats.add(candidateRoundOne.name);
                    constituencyFiveStats.put(constituency, stats);
                }
            }
        }

        constituenciesFive = new Constituency[NC];
        PShape temp;
        for(int i = 0; i < constituenciesFive.length; i++) {
            String location = "imgs/counties/" + storage3.ConstituencyName[i] + ".svg";
            Float[] tempCoords = countiesxy.get(storage3.ConstituencyName[i]);
            temp = loadShape(location);
            //temp.enableStyle();
            constituenciesFive[i] = new Constituency(storage3.ConstituencyName[i], temp, tempCoords[0] + Constants.GRAPH_5_X, tempCoords[1] + Constants.GRAPH_5_Y, storage3.totalElectorate[i], storage3.totalPoll[i], constituencyFiveStats, storage3.numSeats[i]);
        }

        Dublin2 = new Constituency("Dublin", loadShape("imgs/counties/Dublin.svg"), Constants.GRAPH_5_X + 315.7f, Constants.GRAPH_5_Y + 228.5f, 0, 0, constituencyStats, 0);

    }





    public void settings() {
        processing = this;
        processing.size(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        //processing.size(SCREEN_WIDTH, SCREEN_HEIGHT,  "processing.pdf.PGraphicsPDF", "[ANIMATED] assignment1.2.pdf");
        fullScreen();
    }


    public void draw(){
        background(255);


        //Main.processing.text(Main.processing.mouseX + " " + Main.processing.mouseY, Main.processing.mouseX, Main.processing.mouseY);
        //shape(ireland, 0, 0);
        graphOne.draw();
        //line(50, 335, 750, 335);
        //line(50, 335, 1000, 335);
        graphTwo.draw();
        graphThree.draw();
        graphFour.draw();
        graphFive.draw();
        partyLegend.draw();
        //shape(ireland, 0, 0);
//        Main.processing.stroke(0);
//        //ohio.enableStyle();
//        Main.processing.fill(0);
        //ohio.scale(4);
        //shape(ireland, 0, 0);
        //drawIreland();

    }



    int i = 0;
    public void drawIreland() {
        background(255);

        //michigan.enableStyle();
        //ohio.enableStyle();

        // Draw the full map
        shape(ireland, 0, 0);

        // Disable the colors found in the SVG file

        // Set our own coloring
        //fill(0, 150, 255);
        //stroke(0, 150, 255);
        // Draw a single state
        shape(michigan, 0, 0); // Wolverines!

        // Disable the colors found in the SVG file

        // Set our own coloring
        //fill(255, 0, 0);
        //stroke(255, 0, 0);
        // Draw a single state
        if (ohio.contains(mouseX, mouseY)) {
            processing.fill(255, 0, 0);
        }
        else {
            processing.fill(255, 255, 255);
        }
        shape(ohio, 0, 0);  // Buckeyes!

        //noStroke();

        processing.circle(mouseX, mouseY, 10);

    }

    public void mouseClicked() {
        graphOne.checkClick();
        graphTwo.checkClick();
        graphThree.checkClick();
        graphFour.checkClick();
        graphFive.checkClick();
    }

//    public void keyPressed() {
//
//    }
}
