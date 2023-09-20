import processing.core.PApplet;
import processing.core.PShape;

import java.util.ArrayList;
import java.util.HashMap;

public class GraphFour extends PApplet {

    Storage2 storage2;
    Storage3 storage;
    AdvancedStorage advancedStorage;

    public int maxRounds = 15;
//    int graphX = 700;
//    int graphY = 730;
//    int graphWidth = 600;
//    int graphHeight = 330;

    PShape temp;

    HashMap<String, ArrayList<String>> PartiesPerConsitituency;

    int round = 1;
    int count = 0;

    public GraphFour(Storage2 storage2, Storage3 storage, AdvancedStorage advancedStorage) {
        this.storage = storage;
        this.storage2 = storage2;
        this.advancedStorage = advancedStorage;
        this.getPartiesPerConsitituency();
    }

    float buttonX = Constants.SCREEN_WIDTH - (100 + Main.processing.textWidth("Current Round: 10") + 50);
    float buttonY = 150;
    float buttonWidth = 150;
    float buttonHeight = 50;
    Button partiesButton = new Button("Animation Toggle", buttonX, buttonY, buttonWidth, buttonHeight, new Colour(255, 255, 255), new Colour(0, 0, 0), new Colour(127, 205, 0));
    boolean stvToggle = true;

    // winnerPerConsituencyPerRound
    public void checkClick() {
        if(partiesButton.mouseWithin()) {
            if(stvToggle)
                stvToggle = false;
            else
                stvToggle = true;
                round = 1;
        }
    }

    Constituency constituency;

    boolean drawBox = false;
    String constituencyBoxName = "";
    int constituencyElectorateCount = 0;
    float constituencyTurnout = 0;
    String [] constituencyPartyWinners;
    String [] constituencyWinnerNames;

    ArrayList<Candidate> constituencyWinnerNamesArrayList;




    public void draw() {

        Main.processing.strokeWeight(3);
        Main.processing.textSize(18);
        partiesButton.draw();
        Main.processing.textSize(12);

        if(!stvToggle) {
            Main.processing.fill(127, 205, 0);
        } else {
            Main.processing.fill(255);
        }
        //Main.processing.stroke(255);
        Main.processing.rect(buttonX + buttonWidth, buttonY, 20, buttonHeight);
        Main.processing.strokeWeight(1);

        ///////////////////////////// Non STV
        if(stvToggle) {
            Main.processing.textSize(24);
            Main.processing.fill(0);
            Main.processing.textAlign(LEFT, TOP);
            Main.processing.text("Single Transferable Vote", Constants.SCREEN_WIDTH - (100 + Main.processing.textWidth("Current Round: 10")), 70);
            Main.processing.line(Constants.SCREEN_WIDTH - (100 + Main.processing.textWidth("Current Round: 10")), 100,
                    (Constants.SCREEN_WIDTH - (100 + Main.processing.textWidth("Current Round: 10"))) + Main.processing.textWidth("Single Transferable Vote"), 100);
            Main.processing.textAlign(CENTER, CENTER);


            // Dublin section
            Main.processing.textSize(40);
            Main.processing.line(Constants.SCREEN_WIDTH - 300, 250, Constants.SCREEN_WIDTH, 250);
            Main.processing.line(Constants.SCREEN_WIDTH - 300, 250, Constants.SCREEN_WIDTH - 400, 520);
            Main.processing.line(Constants.SCREEN_WIDTH - 400, 520, Constants.SCREEN_WIDTH, 520);
            drawReverseVerticalText("DUBLIN", Constants.SCREEN_WIDTH - 50, 330);

            drawBox = false;
            for(int i = 0; i < Main.constituencies.length; i++) {

                constituency = Main.constituencies[i];
                //temp = Main.ireland.getChild(Main.constituencies[i].name);
                temp = constituency.shape;

                Main.processing.strokeWeight(2);
                if (constituency.containsTwo(Main.processing.mouseX, Main.processing.mouseY)) {
                    Main.processing.stroke(255, 0, 0);
                    drawBox = true;
                    constituencyBoxName = Main.constituencies[i].name;
                    constituencyElectorateCount = Main.constituencies[i].electorate;
                    constituencyTurnout = Main.constituencies[i].turnout;
                    constituencyPartyWinners = Main.constituencies[i].winningParties;
                    constituencyWinnerNames = Main.constituencies[i].winnerNames;

                } else {
                    Main.processing.stroke(255, 255, 255);
                }
                Colour constituencyColour = constituencyColour(Main.constituencies[i].name);
                Main.processing.fill(constituencyColour.r, constituencyColour.g, constituencyColour.b);
                Main.processing.shape(Main.constituencies[i].shape, Main.constituencies[i].x, Main.constituencies[i].y);
            }

            Main.processing.fill(255);
            Main.processing.stroke(0);
            Main.processing.shape(Main.Dublin.shape, Main.Dublin.x, Main.Dublin.y);


            Main.processing.strokeWeight(2);

            for(int i = 0; i < Main.constituencies.length; i++) {
                constituency = Main.constituencies[i];
                drawWinnerBubbles(Main.constituencies[i].centerX, Main.constituencies[i].centerY, Main.constituencies[i].name);
            }

            if(drawBox) {
                DisplayBox.draw(Main.processing.mouseX, Main.processing.mouseY, constituencyBoxName, constituencyElectorateCount, constituencyTurnout, constituencyPartyWinners, constituencyWinnerNames);
            }
        }

        ///////////////////////////// STV
        else {
                count++;
                if(count == 10) {
                    round++;
                    count = 0;
                    if(round > 15) {
                        round = 1;
                    }
                }
                Main.processing.textSize(24);
                Main.processing.fill(0);
                Main.processing.textAlign(LEFT, TOP);
                Main.processing.text("Single Transferable Vote", Constants.SCREEN_WIDTH - (100 + Main.processing.textWidth("Current Round: 10")), 70);
                Main.processing.line(Constants.SCREEN_WIDTH - (100 + Main.processing.textWidth("Current Round: 10")), 100,
                        (Constants.SCREEN_WIDTH - (100 + Main.processing.textWidth("Current Round: 10"))) + Main.processing.textWidth("Single Transferable Vote"), 100);
                Main.processing.text("Current Round: " + round, Constants.SCREEN_WIDTH - (100 + Main.processing.textWidth("Current Round: 10")), 100);
                Main.processing.textAlign(CENTER, CENTER);


                // Dublin section
                Main.processing.textSize(40);
                Main.processing.line(Constants.SCREEN_WIDTH - 300, 250, Constants.SCREEN_WIDTH, 250);
                Main.processing.line(Constants.SCREEN_WIDTH - 300, 250, Constants.SCREEN_WIDTH - 400, 520);
                Main.processing.line(Constants.SCREEN_WIDTH - 400, 520, Constants.SCREEN_WIDTH, 520);
                drawReverseVerticalText("DUBLIN", Constants.SCREEN_WIDTH - 50, 330);

                drawBox = false;
                for(int i = 0; i < Main.constituencies.length; i++) {

                    constituency = Main.constituencies[i];
                    //temp = Main.ireland.getChild(Main.constituencies[i].name);
                    temp = constituency.shape;

                    Main.processing.strokeWeight(2);
                    if (constituency.containsTwo(Main.processing.mouseX, Main.processing.mouseY)) {
                        Main.processing.stroke(255, 0, 0);
                        drawBox = true;
                        constituencyBoxName = Main.constituencies[i].name;
                        constituencyElectorateCount = Main.constituencies[i].electorate;
                        constituencyTurnout = Main.constituencies[i].turnout;

                        //
                        //constituencyPartyWinners = advancedStorage.winnerPerConsituencyPerRound.get(round).get(Main.constituencies[i].name);
                        //constituencyWinnerNamesArrayList = advancedStorage.winnerPerConsituencyPerRound.get(round).get(Main.constituencies[i].name);

                        constituencyWinnerNames = roundWinnersForDisplayBox(advancedStorage.winnerPerConsituencyPerRound.get(round).get(Main.constituencies[i].name));
                        constituencyPartyWinners = roundPartiesForDisplayBox(advancedStorage.winnerPerConsituencyPerRound.get(round).get(Main.constituencies[i].name));

                    } else {
                        Main.processing.stroke(255, 255, 255);
                    }

                    Colour constituencyColour = constituencyColourTwo(advancedStorage.winnerPerConsituencyPerRound.get(round).get(Main.constituencies[i].name));
                    Main.processing.fill(constituencyColour.r, constituencyColour.g, constituencyColour.b);
                    Main.processing.shape(Main.constituencies[i].shape, Main.constituencies[i].x, Main.constituencies[i].y);
                }

                Main.processing.fill(255);
                Main.processing.stroke(0);
                Main.processing.shape(Main.Dublin.shape, Main.Dublin.x, Main.Dublin.y);


                Main.processing.strokeWeight(2);


                for(int i = 0; i < Main.constituencies.length; i++) {
                    constituency = Main.constituencies[i];
                    drawWinnerBubblesTwo(Main.constituencies[i].centerX, Main.constituencies[i].centerY, Main.constituencies[i].name,
                            advancedStorage.winnerPerConsituencyPerRound.get(round).get(Main.constituencies[i].name), Main.constituencies[i].seats);
                }

                if(drawBox) {
                    DisplayBox.draw(Main.processing.mouseX, Main.processing.mouseY, constituencyBoxName, constituencyElectorateCount, constituencyTurnout, constituencyPartyWinners, constituencyWinnerNames);
                }
//            }

        }

    }

    public String [] roundPartiesForDisplayBox(ArrayList<Candidate> winners) {
        Candidate candidate;
        String party;

        if(winners != null) {
            String [] formattedWinners = new String [winners.size()];
            for(int i = 0; i < winners.size(); i++) {
                candidate = winners.get(i);
                party = this.storage2.candidatesParty.get(candidate.name + " " + candidate.id);
                formattedWinners[i] = party;
            }
            return formattedWinners;
        } else {
            return new String [0];
        }
    }

    public String [] roundWinnersForDisplayBox(ArrayList<Candidate> winners) {
        Candidate candidate;

        if(winners != null) {
            String [] formattedWinners = new String [winners.size()];
            for(int i = 0; i < winners.size(); i++) {
                candidate = winners.get(i);
                //party = this.storage2.candidatesParty.get(candidate.name + " " + candidate.id);
                //Colour partyColour = this.storage.adPartyColours.get(party);
                formattedWinners[i] = candidate.name;
            }
            return formattedWinners;
        } else {
            return new String [0];
        }
    }

    public void getPartiesPerConsitituency() {
        PartiesPerConsitituency = new HashMap<String, ArrayList<String>>();
        ArrayList<String> temp;
        for(int i = 0; i < this.storage.N; i++) {
            if(this.storage.result[i].equals("Elected")) {
                if(PartiesPerConsitituency.containsKey(this.storage.constituency[i])) {
                    temp = PartiesPerConsitituency.get(this.storage.constituency[i]);
                    temp.add(this.storage.party[i]);
                } else {
                    temp = new ArrayList<String>();
                    temp.add(this.storage.party[i]);
                    PartiesPerConsitituency.put(this.storage.constituency[i], temp);
                }
            }
        }
        System.out.print("Parties per constituency got.");
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

    // x, y are centre of the consitituency;
    public void drawWinnerBubblesTwo(float x, float y, String constituencyToDraw, ArrayList<Candidate> winners, int seats) {
        // adjust x by the weird translation.
        Main.processing.stroke(0);

        //ArrayList<String> temp = PartiesPerConsitituency.get(constituencyToDraw);
        Candidate candidate;
        // 3 seats

        String party;

        for(int i = 0; i < seats; i++) {
            if(winners != null) {
                if(i < winners.size()) {
                    candidate = winners.get(i);
                    party = this.storage2.candidatesParty.get(candidate.name + " " + candidate.id);
                    Colour partyColour = this.storage.adPartyColours.get(party);
                    Main.processing.fill(partyColour.r, partyColour.g, partyColour.b);
                } else {
                    Main.processing.fill(255);
                }
            } else {
                Main.processing.fill(255);
            }

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

//        String party;
//        for(int i = 0; i < winners.size(); i++) {
//            candidate = winners.get(i);
//            party = this.storage2.candidatesParty.get(candidate.name + " " + candidate.id);
//            Colour partyColour = this.storage.adPartyColours.get(party);
//            Main.processing.fill(partyColour.r, partyColour.g, partyColour.b);
//            if(i == 0) {
//                Main.processing.circle(x - 5, y - 5, sizeOfConstituencyCircle);
//            } else if(i == 1) {
//                Main.processing.circle(x + 5, y - 5, sizeOfConstituencyCircle);
//            } else if(i == 2) {
//                Main.processing.circle(x, y + 5, sizeOfConstituencyCircle);
//            } else if(i == 3) {
//                Main.processing.circle(x + 10, y + 5, sizeOfConstituencyCircle);
//            } else if(i == 4) {
//                Main.processing.circle(x + 15, y - 5, sizeOfConstituencyCircle);
//            }
//        }

    }

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
