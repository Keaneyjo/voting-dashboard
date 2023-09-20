import processing.core.PApplet;
import processing.core.PShape;

import java.util.ArrayList;
import java.util.HashMap;

public class GraphFive extends PApplet {

    Storage2 storage2;
    Storage3 storage;
    AdvancedStorage advancedStorage;
//    int graphX = 700;
//    int graphY = 730;
//    int graphWidth = 600;
//    int graphHeight = 330;

    PShape temp;

    HashMap<String, ArrayList<String>> PartiesPerConsitituency;

    public GraphFive(Storage2 storage2, Storage3 storage, AdvancedStorage advancedStorage) {
        this.storage = storage;
        this.storage2 = storage2;
        this.advancedStorage = advancedStorage;

    }

    public void readyGraph() {
        this.getPartiesPerConsitituency();
    }

    Constituency constituency;

    boolean drawBox = false;
    String constituencyBoxName = "";
    int constituencyElectorateCount = 0;
    float constituencyTurnout = 0;
    String [] constituencyPartyWinners;
    String [] constituencyWinnerNames;

    float buttonX = (Constants.SCREEN_WIDTH - (100 + Main.processing.textWidth("Current Round: 10") + 50));
    float buttonY = Constants.GRAPH_5_Y + 150;
    float buttonWidth = 150;
    float buttonHeight = 50;
    Button differenceButton = new Button("Difference Toggle", buttonX, buttonY, buttonWidth, buttonHeight, new Colour(255, 255, 255), new Colour(0, 0, 0), new Colour(255, 0, 0));
    boolean differenceToggle = true;

    public void checkClick() {
        if(differenceButton.mouseWithin()) {
            if(differenceToggle) {
                differenceToggle = false;
                GraphTwo.pluralityFill = false;
            }
            else {
                differenceToggle = true;
                GraphTwo.pluralityFill = true;
            }
        }
    }

    public void draw() {

        Main.processing.textSize(18);
        differenceButton.draw();
        Main.processing.textSize(12);
        if(!differenceToggle) {
            Main.processing.fill(255, 0, 0);
        } else {
            Main.processing.fill(255);
        }
        //Main.processing.stroke(255);
        Main.processing.rect(buttonX + buttonWidth, buttonY, 20, buttonHeight);
        Main.processing.strokeWeight(1);



        Main.processing.strokeWeight(1);
        Main.processing.textSize(24);
        Main.processing.fill(0);
        Main.processing.textAlign(LEFT, TOP);
        Main.processing.text("Plurality Voting", Constants.SCREEN_WIDTH - (100 + Main.processing.textWidth("Current Round: 10")), Constants.GRAPH_5_Y + 70);
        Main.processing.line(Constants.SCREEN_WIDTH - (100 + Main.processing.textWidth("Current Round: 10")), Constants.GRAPH_5_Y + 100,
                (Constants.SCREEN_WIDTH - (100 + Main.processing.textWidth("Current Round: 10"))) + Main.processing.textWidth("Plurality Voting"), Constants.GRAPH_5_Y + 100);
        Main.processing.textAlign(CENTER, CENTER);

        // Dublin section
        Main.processing.textSize(40);
        Main.processing.line(Constants.SCREEN_WIDTH - 300, Constants.GRAPH_5_Y + 220, Constants.SCREEN_WIDTH, Constants.GRAPH_5_Y + 220);
        Main.processing.line(Constants.SCREEN_WIDTH - 300, Constants.GRAPH_5_Y + 220, Constants.SCREEN_WIDTH - 400, Constants.GRAPH_5_Y + 490);
        Main.processing.line(Constants.SCREEN_WIDTH - 400, Constants.GRAPH_5_Y + 490, Constants.SCREEN_WIDTH, Constants.GRAPH_5_Y + 490);
        drawReverseVerticalText("DUBLIN", Constants.SCREEN_WIDTH - 50, Constants.GRAPH_5_Y + 300);


        drawBox = false;
        for(int i = 0; i < Main.constituenciesFive.length; i++) {

            constituency = Main.constituenciesFive[i];
            //temp = Main.ireland.getChild(Main.constituenciesFive[i].name);
            temp = constituency.shape;

            Main.processing.strokeWeight(2);
            if (constituency.containsTwo(Main.processing.mouseX, Main.processing.mouseY)) {
                //Main.processing.stroke(255, 0, 0);
                drawBox = true;
                constituencyBoxName = Main.constituenciesFive[i].name;
                constituencyElectorateCount = Main.constituenciesFive[i].electorate;
                constituencyTurnout = Main.constituenciesFive[i].turnout;
                constituencyPartyWinners = Main.constituenciesFive[i].winningParties;
                constituencyWinnerNames = Main.constituenciesFive[i].winnerNames;
            }

            if(differenceBetweenPluralityAndSTV(constituency, Main.constituencies[i])) {
                if(differenceToggle) {
                    Main.processing.fill(255, 0, 0);
                } else {
                    Main.processing.strokeWeight(7);
                    Main.processing.stroke(255, 0, 0);
                    Colour constituencyColour = constituencyColour(Main.constituenciesFive[i].name);
                    Main.processing.fill(constituencyColour.r, constituencyColour.g, constituencyColour.b);
                }

            } else {
                Main.processing.stroke(255);
                Colour constituencyColour = constituencyColour(Main.constituenciesFive[i].name);
                Main.processing.fill(constituencyColour.r, constituencyColour.g, constituencyColour.b);
                //Main.processing.strokeWeight(2);
                //Main.processing.stroke(255, 255, 255);
                //Main.processing.fill(255, 255, 255);
            }

            Main.processing.shape(Main.constituenciesFive[i].shape, Main.constituenciesFive[i].x, Main.constituenciesFive[i].y);
        }

        Main.processing.fill(255);
        Main.processing.stroke(0);
        Main.processing.shape(Main.Dublin2.shape, Main.Dublin2.x, Main.Dublin2.y);

        Main.processing.strokeWeight(2);

        for(int i = 0; i < Main.constituenciesFive.length; i++) {
            constituency = Main.constituenciesFive[i];
            drawWinnerBubbles(Main.constituenciesFive[i].centerX, Main.constituenciesFive[i].centerY, Main.constituenciesFive[i].name);
        }

        if(drawBox) {
            DisplayBox.draw(Main.processing.mouseX, Main.processing.mouseY, constituencyBoxName, constituencyElectorateCount, constituencyTurnout, constituencyPartyWinners, constituencyWinnerNames);
        }
    }

    // get the winners of each constituency
    public void getPartiesPerConsitituency() {
        PartiesPerConsitituency = new HashMap<String, ArrayList<String>>();
        for(int i = 0; i < Main.constituenciesFive.length; i++) {
            ArrayList<String> temp = new ArrayList<String>();
            Constituency constituency = Main.constituenciesFive[i];
            for(int j = 0; j < constituency.winnerNames.length; j++) {
                temp.add(constituency.winningParties[j]);
            }
            PartiesPerConsitituency.put(constituency.name, temp);
        }
        System.out.print("Parties per constituency got.");

    }


    public boolean differenceBetweenPluralityAndSTV(Constituency c1, Constituency c2) {
        int sameCandidateCount = 0;
        for(int i = 0; i < c1.winnerNames.length; i++) {
            for(int j = 0; j < c2.winnerNames.length; j++) {
                if(c1.winnerNames[i].equals(c2.winnerNames[j])) {
                    sameCandidateCount++;
                }
            }
        }
        // if not equal then difference, otherwise no difference
        return sameCandidateCount != c1.winnerNames.length;
    }



    // x, y are centre of the consitituency;
    int sizeOfConstituencyCircle = 10;
    public void drawWinnerBubbles(float x, float y, String constituencyToDraw) {
        // adjust x by the weird translation.
        Main.processing.stroke(0);

        ArrayList<String> temp = PartiesPerConsitituency.get(constituencyToDraw);
        // 3 seats

        String party;
        for(int i = 0; i < temp.size(); i++) {
            party = temp.get(i);
            Colour partyColour = this.storage.adPartyColours.get(party);
            Main.processing.fill(partyColour.r, partyColour.g, partyColour.b);
            if(i == 0) {
                Main.processing.circle(x - 5, y - 5, sizeOfConstituencyCircle);
            } else if(i == 1) {
                Main.processing.circle(x + 5, y - 5, sizeOfConstituencyCircle);
            } else if(i == 2) {
                Main.processing.circle(x, y + 5, sizeOfConstituencyCircle);
            } else if(i == 3) {
                Main.processing.circle(x + 10, y + 5, sizeOfConstituencyCircle);
            } else if(i == 4) {
                Main.processing.circle(x + 15, y - 5, sizeOfConstituencyCircle);
            }
        }

    }

    public Colour constituencyColour(String constituencyToDraw) {
        ArrayList<String> temp = PartiesPerConsitituency.get(constituencyToDraw);

        float r = 0;
        float g = 0;
        float b = 0;
        String party;
        int constituencyTotalSeats = temp.size();
        for(int i = 0; i < temp.size(); i++) {
            party = temp.get(i);
            Colour partyColour = this.storage.adPartyColours.get(party);
            r += partyColour.r;
            g += partyColour.g;
            b += partyColour.b;
        }

        r /= constituencyTotalSeats;
        g /= constituencyTotalSeats;
        b /= constituencyTotalSeats;

        return new Colour(r, g, b);
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

    public void drawReverseVerticalText(String text, float x, float y) {
        x -= 15;
        Main.processing.fill(0);
        Main.processing.pushMatrix();
        float angle = radians(90);
        Main.processing.translate(x, y);
        Main.processing.rotate(angle);
        Main.processing.text(text, 0, 0);
        Main.processing.popMatrix();
        Main.processing.fill(255);
    }
}
