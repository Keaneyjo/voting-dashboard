import processing.core.PApplet;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class GraphThree extends PApplet {

    Storage2 storage2;
    Storage3 storage;

    int graphX = 150;
    int graphY = 710;
    int graphWidth = 950;
    int graphHeight = 210;

//    int graphX = 100;
//    int graphY = 500;
//    int graphWidth = 500;
//    int graphHeight = 500;


    float buttonWidth = 150;
    float buttonHeight = 50;
    float buttonX = (graphX + graphWidth) - buttonWidth;
    float buttonY = graphY - (float) (buttonHeight * 2.5);

    float textX = (graphX + graphWidth) - (float) (buttonWidth * 1.5);
    float textY = graphY - (buttonHeight * 1);


    Button pluralityButton = new Button("Plurality Toggle", buttonX, buttonY, buttonWidth, buttonHeight, new Colour(255, 255, 255), new Colour(0, 0, 0), new Colour(0, 177, 220));

    boolean togglePlurality = false;

    public GraphThree(Storage2 storage2, Storage3 storage) {
        this.storage = storage;
        this.storage2 = storage2;

    }

    public void checkClick() {
        if(pluralityButton.mouseWithin()) {
            if(togglePlurality) {
                togglePlurality = false;
            } else {
                togglePlurality = true;
            }
        }
    }

    public void draw() {
        Main.processing.fill(0);
        Main.processing.stroke(0);

        // draw the seperator
//        Main.processing.line(0, graphY - 25, graphX + (float) (graphWidth * .65), graphY - 25);
//        Main.processing.line(graphX + (float) (graphWidth * .65), graphY - 25, buttonX + buttonWidth + 15, buttonY - 45);
//        float otherGraphY = 430;
//        Main.processing.line(buttonX + buttonWidth + 15, buttonY - 45, buttonX + buttonWidth + 15, otherGraphY - 95);
//        Main.processing.line(buttonX + buttonWidth + 15, otherGraphY - 95, 0, otherGraphY - 95);
        Main.processing.line(0, graphY - 25, graphX + (float) (graphWidth * .65), graphY - 25);
        Main.processing.line(graphX + (float) (graphWidth * .65), graphY - 25, buttonX - 15, buttonY - 45);
        float otherGraphY = 430;
        Main.processing.line(buttonX - 15, buttonY - 45, buttonX - 15, otherGraphY - 95);
        Main.processing.line(buttonX - 15, otherGraphY - 95, 0, otherGraphY - 95);


        //
        Main.processing.textSize(17);
        Main.processing.text("Constituency Breakdown", graphX + (graphWidth/2), graphY - 15);
        Main.processing.textSize(10);

        drawBubbleChart();
        //Main.processing.line(graphX, graphY, graphX + graphWidth, graphY + graphHeight);
    }

    public HashMap<String, Constituency> orderConstituencies() {

        Main.processing.strokeWeight(3);
        Main.processing.textSize(18);
        pluralityButton.draw();
        Main.processing.textSize(22);

        if(!togglePlurality) {
            Main.processing.strokeWeight(1);
            Main.processing.text("Single Transferable Vote", textX, textY);
            Main.processing.line(textX, textY + 25, textX + Main.processing.textWidth("Single Transferable Vote"), textY + 25);
            Main.processing.fill(255);
        } else {
            Main.processing.strokeWeight(1);
            Main.processing.text("Plurality Voting", textX, textY);
            Main.processing.line(textX, textY + 25, textX + Main.processing.textWidth("Plurality Voting"), textY + 25);
            Main.processing.fill(0, 177, 220);
        }

        Main.processing.strokeWeight(3);
        //Main.processing.stroke(255); buttonX + 20, buttonY - 30
        Main.processing.rect(buttonX + buttonWidth,  buttonY, 20, buttonHeight);
        Main.processing.strokeWeight(1);

        Main.processing.textSize(12);



        HashMap<String, Constituency> orderedConsituencies = new HashMap<>();
        if(togglePlurality) {
            for(int i = 0; i < Main.constituenciesFive.length; i++) {
                orderedConsituencies.put(Main.constituenciesFive[i].name, Main.constituenciesFive[i]);
            }
        } else {
            for(int i = 0; i < Main.constituencies.length; i++) {
                orderedConsituencies.put(Main.constituencies[i].name, Main.constituencies[i]);
            }
        }
        return orderedConsituencies;

    }

    public void drawBubbleChart() {
        Constituency constituency;
        String [] winners;
        String [] parties;
        String party;

        float widthPerConstituency = graphWidth / Main.constituencies.length;
        float heightPerParty = graphHeight / partyIndent.size();

        float currentX = graphX;
        float currentY;

        Main.processing.textSize(13);
//        Main.processing.textAlign(Main.processing.LEFT, Main.processing.BOTTOM);
        Main.processing.textAlign(Main.processing.LEFT, Main.processing.CENTER);

        HashMap<String, Constituency> orderedConstituencies = orderConstituencies();
        HashMap<String, Integer> totalSTVPartyCount = getTotalCountPerParty(Main.constituencies);
        HashMap<String, Integer> totalPluralityPartyCount = getTotalCountPerParty(Main.constituenciesFive);

        float circleSize = 10;
        // if % 2 then
//        int count = 0;

        HashMap<String, Integer> partyPosition = new HashMap<String, Integer>() {{
            put("Aontú", 0); //Aontu
            put("Fianna Fáil", 0); // FF
            put("Fine Gael", 0); // FG
            put("Green Party/ Comhaontas Glas", 0); // Greens
            put("Independents 4 Change", 0); // I4C
            put("Non-Party", 0); // Non-Party
            put("Sinn Féin", 0); // Sinn Fein
            put("Social Democrats", 0); // Social Democrats
            put("Solidarity - People Before Profit", 0); // PBP
            put("The Labour Party", 0); // Labour
        }};


        float previousX;
        for(int i = 0; i < provinces.size(); i++) {

            previousX = currentX;

            String [] province = provinces.get(i);
            for(int k = 0; k < province.length; k++) {
                //System.out.print(province[k]);
                constituency = orderedConstituencies.get(province[k]);
                parties = constituency.winningParties;

                partyPosition = new HashMap<String, Integer>() {{
                    put("Aontú", 0); //Aontu
                    put("Fianna Fáil", 0); // FF
                    put("Fine Gael", 0); // FG
                    put("Green Party/ Comhaontas Glas", 0); // Greens
                    put("Independents 4 Change", 0); // I4C
                    put("Non-Party", 0); // Non-Party
                    put("Sinn Féin", 0); // Sinn Fein
                    put("Social Democrats", 0); // Social Democrats
                    put("Solidarity - People Before Profit", 0); // PBP
                    put("The Labour Party", 0); // Labour
                }};

                int count;
                for(int j = 0; j < parties.length; j++) {
                    party = parties[j];

                    Colour partyColour = adPartyColours.get(party);
                    Main.processing.fill(partyColour.r, partyColour.g, partyColour.b);
                    currentY = graphY + partyIndent.get(party) * heightPerParty;
                    count = partyPosition.get(party);
                    partyPosition.put(party, count + 1);
                    if(count == 0) {
                        Main.processing.circle(currentX - circleSize, currentY - circleSize/2, circleSize);
                    } else if(count == 1) {
                        Main.processing.circle(currentX, currentY - circleSize/2, circleSize);
                    } else if(count == 2) {
                        Main.processing.circle(currentX - circleSize, currentY + circleSize/2, circleSize);
                    } else if(count == 3) {
                        Main.processing.circle(currentX, currentY + circleSize/2, circleSize);
                    }
                    // Main.processing.circle(currentX, currentY, circleSize);
                    Main.processing.fill(255);
                }


                drawVerticalText(constituency.name, (currentX + (widthPerConstituency / 2)) - 4, graphY + graphHeight + Main.processing.textWidth(constituency.name) + circleSize);
                Main.processing.stroke(200);
                Main.processing.line((currentX + (widthPerConstituency / 2)) - (circleSize/2), graphY, (currentX + (widthPerConstituency / 2)) - (circleSize/2), graphY + graphHeight);
                Main.processing.stroke(0);



                if(k == province.length - 1) {
                    Main.processing.strokeWeight(3);
                    //Main.processing.line((currentX + (widthPerConstituency / 2)) - (circleSize/2), graphY, (currentX + (widthPerConstituency / 2)) - (circleSize/2), graphY + graphHeight);
                    Main.processing.line((currentX + (widthPerConstituency / 2)) - (circleSize/2), graphY, (currentX + (widthPerConstituency / 2)) - (circleSize/2), Constants.SCREEN_HEIGHT - 10);
                    Main.processing.strokeWeight(1);

                    Main.processing.textAlign(Main.processing.CENTER, Main.processing.CENTER);
                    Main.processing.textSize(16);
                    Main.processing.fill(0);
                    Main.processing.text(provincesList[i],  (previousX + ((currentX - previousX) / 2)) - 5, Constants.SCREEN_HEIGHT- 20);
                    Main.processing.textSize(13);
                    Main.processing.textAlign(Main.processing.LEFT, Main.processing.CENTER);
                }

                currentX += widthPerConstituency;
            }


        }

        Main.processing.textSize(13);
        Main.processing.fill(0);

        for(String party_ : partyPosition.keySet()) {
            float ypos = graphY + partyIndent.get(party_) * heightPerParty;

            if(party_.equals("Green Party/ Comhaontas Glas")) {
                party_ = "Greens";
            } else if(party_.equals("Independents 4 Change")) {
                party_ = "Inds 4 Change";
            } else if(party_.equals("Solidarity - People Before Profit")) {
                party_ = "People Before Profit";
            } else if(party_.equals("The Labour Party")) {
                party_ = "Labour";
            }
            Main.processing.text(party_,  graphX - (Main.processing.textWidth(party_) + 25), ypos - (circleSize/2));

        }

        previousX = currentX;

        // draw totals
        int difference;
        for(String party_ : partyPosition.keySet()) {
            float ypos = graphY + partyIndent.get(party_) * heightPerParty;
            Main.processing.text(totalPluralityPartyCount.get(party_),  currentX - circleSize, ypos - (circleSize/2)); //Main.processing.textWidth(party_)
            Main.processing.text(totalSTVPartyCount.get(party_),  (currentX + widthPerConstituency) - circleSize, ypos - (circleSize/2));
            if(togglePlurality) {
                difference = totalPluralityPartyCount.get(party_) - totalSTVPartyCount.get(party_) ;
            }  else {
                difference = totalSTVPartyCount.get(party_) - totalPluralityPartyCount.get(party_);
            }

            if(difference < 0) {
                Main.processing.fill(255, 0, 0);
            } else if(difference == 0) {
                Main.processing.fill(255, 150, 0);
            }
            else {
                Main.processing.fill(0, 156, 120);
            }
            Main.processing.text(difference,  (currentX + widthPerConstituency + widthPerConstituency) - circleSize, ypos - (circleSize/2));
            Main.processing.fill(0);
        }
        Main.processing.textSize(16);
        drawVerticalText("Plurality Voting", (currentX + (widthPerConstituency / 2)) - 4, graphY + graphHeight + Main.processing.textWidth("Plurality Voting") + circleSize);
        Main.processing.stroke(200);
        Main.processing.line((currentX + (widthPerConstituency / 2)) - (circleSize/2), graphY, (currentX + (widthPerConstituency / 2)) - (circleSize/2), graphY + graphHeight);
        Main.processing.stroke(0);

        currentX += widthPerConstituency;
        drawVerticalText("STV Voting", (currentX + (widthPerConstituency / 2)) - 4, graphY + graphHeight + Main.processing.textWidth("STV Voting") + circleSize);
        Main.processing.stroke(200);
        Main.processing.line((currentX + (widthPerConstituency / 2)) - (circleSize/2), graphY, (currentX + (widthPerConstituency / 2)) - (circleSize/2), graphY + graphHeight);
        Main.processing.stroke(0);

        currentX += widthPerConstituency;
        drawVerticalText("Difference", (currentX + (widthPerConstituency / 2)) - 4, graphY + graphHeight + Main.processing.textWidth("Difference") + circleSize);

    }

    public HashMap<String, Integer> getTotalCountPerParty(Constituency [] constituencies) {
        HashMap<String, Integer> totalCountPerParty = new HashMap<String, Integer>() {{
            put("Aontú", 0); //Aontu
            put("Fianna Fáil", 0); // FF
            put("Fine Gael", 0); // FG
            put("Green Party/ Comhaontas Glas", 0); // Greens
            put("Independents 4 Change", 0); // I4C
            put("Non-Party", 0); // Non-Party
            put("Sinn Féin", 0); // Sinn Fein
            put("Social Democrats", 0); // Social Democrats
            put("Solidarity - People Before Profit", 0); // PBP
            put("The Labour Party", 0); // Labour
        }};

        String parties [];
        Constituency constituency;
        for(int i = 0; i < constituencies.length; i++) {
            constituency = constituencies[i];
            parties = constituency.winningParties;
            for(int j = 0; j < parties.length; j++) {
                totalCountPerParty.put(parties[j], totalCountPerParty.get(parties[j]) + 1);
            }
        }

        return totalCountPerParty;
    }

    String [] connacht = new String [] {"Galway East", "Galway West", "Mayo", "Sligo Leitrim", "Roscommon Galway"};
    String [] munster = new String [] {"Clare", "Kerry", "Limerick City", "Limerick County", "Tipperary", "Waterford"};
    String [] cork = new String [] {"Cork East", "Cork North Central", "Cork North West", "Cork South Central", "Cork South West"};
    String [] dublin = new String [] { "Dublin Central", "Dublin Bay North", "Dublin Fingal", "Dublin North West", "Dublin South Central",
                                "Dublin Bay South",  "Dublin Rathdown", "Dublin South West", "Dublin West", "Dun Laoghaire", "Dublin Mid West"};
    String [] leinster = new String [] {"Carlow Kilkenny", "Kildare North", "Laois Offaly", "Meath East", "Longford Westmeath", "Louth", "Meath West", "Wexford", "Wicklow", "Kildare South"};
    String [] ulster = new String [] {"Cavan Monaghan", "Donegal"};

    ArrayList<String []> provinces = new ArrayList<String []>() {{
       add(connacht);
       add(ulster);
       add(munster);
       add(cork);
       add(leinster);
       add(dublin);
    }};



    String [] provincesList = new String [] {"Connacht", "Ulster", "Munster", "Cork", "Leinster", "Dublin"};

//    Connacht {Galway East, Galway West, Mayo, Sligo Leitrim, Roscommon Galway
//        Leinster {Carlow Kilkenny, Kildare North, Laois Offaly, Meath East, Longford Westmeath, Louth, Meath West, Wexford, Wicklow
//            Kildare South
//            Ulster {Cavan Monaghan, Donegal
//                Munster {Clare, Cork East, Cork North Central, Cork North West, Cork South Central, Cork South West, Kerry
//                    Limerick City, Limerick County, Tipperary, Waterford
//
//                    Dublin {
//                        Dublin Central, Dublin Bay North, Dublin Fingal, Dublin North West,
//                        Dublin South Central,
//                                Dublin Bay South,
//                        Dublin Rathdown,
//                        Dublin South West,
//                                Dublin West,
//                                Dun Laoghaire,
//                                Dublin Mid West
//                    }

    public Colour constituencyColourTwo(ArrayList<Candidate> winners) {

        //ArrayList<String> temp = PartiesPerConsitituency.get(constituencyToDraw);
        Candidate candidate;

        float r = 0;
        float g = 0;
        float b = 0;
        String party;
        if(winners != null) {
            for(int i = 0; i < winners.size(); i++) {
                candidate = winners.get(i);
                party = this.storage2.candidatesParty.get(candidate.name + " " + candidate.id);
                Colour partyColour = this.storage.adPartyColours.get(party);
                r += partyColour.r;
                g += partyColour.g;
                b += partyColour.b;
            }

            r /= winners.size();
            g /= winners.size();
            b /= winners.size();

            return new Colour(r, g, b);
        } else {
            return new Colour(225, 225, 225);
        }
    }


    public boolean mouseWithin(int x, int y) {
        return x < Main.processing.mouseX && Main.processing.mouseX < x + width && y < Main.processing.mouseY && Main.processing.mouseY < y + height;
    }

    public void drawVerticalText(String text, float x, float y) {
        x -= 15;
        Main.processing.fill(0);
        Main.processing.pushMatrix();
        float angle = radians(270);
        Main.processing.translate(x, y);
        Main.processing.rotate(angle);
        Main.processing.text(text, 0, 0);
        Main.processing.popMatrix();
        Main.processing.fill(255);
    }



    public HashMap<String, Integer> partyIndent = new HashMap<String, Integer>() {{
        put("Aontú", 7); //Aontu
        put("Fianna Fáil", 2); // FF
        put("Fine Gael", 3); // FG
        put("Green Party/ Comhaontas Glas", 4); // Greens
        put("Independents 4 Change", 6); // I4C
        put("Non-Party", 5); // Non-Party
        put("Sinn Féin", 1); // Sinn Fein
        put("Social Democrats", 8); // Social Democrats
        put("Solidarity - People Before Profit", 9); // PBP
        put("The Labour Party", 10); // Labour
    }};

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


}









































//////////////////////////
//import processing.core.PApplet;
//
//        import java.util.ArrayList;
//
//public class GraphThree extends PApplet {
//
//    Storage2 storage2;
//    Storage3 storage;
//
////    int graphX = 100;
////    int graphY = 730;
////    int graphWidth = 600;
////    int graphHeight = 330;
//
//    int graphX = 100;
//    int graphY = 500;
//    int graphWidth = 600;
//    int graphHeight = 500;
//
//    public GraphThree(Storage2 storage2, Storage3 storage) {
//        this.storage = storage;
//        this.storage2 = storage2;
//
//    }
//
//    public void checkClick() {
//
//    }
//
//    public void draw() {
//        Main.processing.fill(0);
//        Main.processing.stroke(0);
//
//        Main.processing.textSize(17);
//        Main.processing.text("Graph 3", graphX + (graphWidth/2), graphY - 25);
//        Main.processing.textSize(10);
//
//        drawConstGraph();
//
//    }
//
//    // max number of electorates votes 130000
//    int maxElectorates = 130000;
//    public void drawConstGraph() {
//        float barHeight = (float) graphHeight / storage.N2;
//        Main.processing.textSize(14);
//        Main.processing.textAlign(CENTER, CENTER);
//        for(int i = 0; i < Main.constituencies.length; i++) {
//            Constituency constituency = Main.constituencies[i];
//            Main.processing.fill(0);
//            Main.processing.text(constituency.name, graphX - 50, (graphY + (((float) i / storage.N2) * graphHeight)  + (barHeight / 2)));
//            Main.processing.fill(100);
//            Main.processing.rect(graphX, graphY + (((float) i / storage.N2) * graphHeight), (((float)constituency.electorate / maxElectorates) * graphWidth),barHeight);
//            Main.processing.fill(200);
//            Main.processing.rect(graphX, graphY + (((float) i / storage.N2) * graphHeight), (((((float)constituency.turnout / 100) * constituency.electorate) / maxElectorates) * graphWidth),barHeight);
//        }
//    }
//
////    // max number of electorates votes 130000
////    int maxElectorates = 130000;
////    public void drawConstGraph() {
////        float barHeight = (float) graphHeight / storage.N2;
////        Main.processing.textSize(14);
////        Main.processing.textAlign(CENTER, CENTER);
////        for(int i = 0; i < storage.N2; i++) {
////            Main.processing.fill(0);
////            Main.processing.text(storage.ConstituencyName[i], graphX - 50, (graphY + (((float) i / storage.N2) * graphHeight)  + (barHeight / 2)));
////            Main.processing.fill(100);
////            Main.processing.rect(graphX, graphY + (((float) i / storage.N2) * graphHeight), (((float)storage.totalElectorate[i] / maxElectorates) * graphWidth),barHeight);
////            Main.processing.fill(200);
////            Main.processing.rect(graphX, graphY + (((float) i / storage.N2) * graphHeight), (((float)storage.totalPoll[i] / maxElectorates) * graphWidth),barHeight);
////        }
////    }
//
//    public Colour constituencyColourTwo(ArrayList<Candidate> winners) {
//
//        //ArrayList<String> temp = PartiesPerConsitituency.get(constituencyToDraw);
//        Candidate candidate;
//
//        float r = 0;
//        float g = 0;
//        float b = 0;
//        String party;
//        if(winners != null) {
//            for(int i = 0; i < winners.size(); i++) {
//                candidate = winners.get(i);
//                party = this.storage2.candidatesParty.get(candidate.name + " " + candidate.id);
//                Colour partyColour = this.storage.adPartyColours.get(party);
//                r += partyColour.r;
//                g += partyColour.g;
//                b += partyColour.b;
//            }
//
//            r /= winners.size();
//            g /= winners.size();
//            b /= winners.size();
//
//            return new Colour(r, g, b);
//        } else {
//            return new Colour(225, 225, 225);
//        }
//    }
//
//
//    public boolean mouseWithin(int x, int y) {
//        return x < Main.processing.mouseX && Main.processing.mouseX < x + width && y < Main.processing.mouseY && Main.processing.mouseY < y + height;
//    }
//
//    public void drawVerticalText(String text, float x, float y) {
//        x -= 15;
//        Main.processing.fill(0);
//        Main.processing.pushMatrix();
//        float angle = radians(270);
//        Main.processing.translate(x, y);
//        Main.processing.rotate(angle);
//        Main.processing.text(text, 0, 0);
//        Main.processing.popMatrix();
//        Main.processing.fill(255);
//    }
//}

