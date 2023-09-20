import processing.core.PApplet;

public class GraphOne extends PApplet {

    Storage storage;

    // x, y
    //
    //          graphWidth, graphHeight

    // old
    int graphX = 100;
    int graphY = 85;
    int graphWidth = 600;
    int graphHeight = 200;
    int bottomAxisPadding = 10;
    int maxWinners = 34;
    int maxTransfers = 135218;
    int maxExcluded = 60;
    int currentChart;

//    int graphX = 750;
////    int graphY = 430;
//    int graphWidth = 450;
////    int graphHeight = 200;
////    int bottomAxisPadding = 10;
////    int maxWinners = 34;
////    int maxTransfers = 135218;
////    int maxExcluded = 60;
////    int currentChart;

    //float buttonY = 50;
    float buttonWidth = graphWidth / 4;
    float buttonHeight =  20;
    float buttonY = graphY - (buttonHeight + 15);

    Button winnerButton = new Button("Winners", (float) graphX, buttonY, buttonWidth, buttonHeight, new Colour(255, 255, 255), new Colour(150, 150, 150));
    Button transferButton = new Button("Transfers", (float) graphX + buttonWidth, buttonY, buttonWidth, buttonHeight, new Colour(255, 255, 255), new Colour(150, 150, 150));
    Button excludedButton = new Button("Excluded", (float) graphX + buttonWidth + buttonWidth, buttonY, buttonWidth, buttonHeight, new Colour(255, 255, 255), new Colour(150, 150, 150));
    Button partiesButton = new Button("Parties", (float) graphX + buttonWidth + buttonWidth + buttonWidth, buttonY, buttonWidth, buttonHeight, new Colour(255, 255, 255), new Colour(150, 150, 150));

    public GraphOne(Storage storage) {
        this.storage = storage;
        this.currentChart = 0;

    }

    public void checkClick() {
        if(winnerButton.mouseWithin()) {
            winnerButton.clicked = true;
            transferButton.clicked = false;
            excludedButton.clicked = false;
            partiesButton.clicked = false;
            this.currentChart = 0;
        } else if(transferButton.mouseWithin()) {
            winnerButton.clicked = false;
            transferButton.clicked = true;
            excludedButton.clicked = false;
            partiesButton.clicked = false;
            this.currentChart = 1;
        } else if(excludedButton.mouseWithin()) {
            winnerButton.clicked = false;
            transferButton.clicked = false;
            excludedButton.clicked = true;
            partiesButton.clicked = false;
            this.currentChart = 2;
        } else if(partiesButton.mouseWithin()) {
            winnerButton.clicked = false;
            transferButton.clicked = false;
            excludedButton.clicked = false;
            partiesButton.clicked = true;
            this.currentChart = 3;
        }
    }

    public void draw() {
        Main.processing.strokeWeight(1);
        Main.processing.textAlign(CENTER, CENTER);
        Main.processing.fill(0);
        Main.processing.textSize(17);
        Main.processing.text("Round By Round Breakdown", graphX + (graphWidth/2), buttonY - 20);
        Main.processing.textSize(14);

        winnerButton.draw();
        transferButton.draw();
        excludedButton.draw();
        partiesButton.draw();

        if(currentChart == 0) {
            drawBarChart("Rounds", "Winners", storage.winners, maxWinners);
        } else if(currentChart == 1) {
            drawBarChart("Rounds", "Transfers", storage.transfers, maxTransfers);
        } else if(currentChart == 2) {
            drawBarChart("Rounds", "Excluded", storage.excluded, maxExcluded);
        } else if(currentChart == 3) {
            drawPercentageChart("Rounds", "% of Seats");
        }
    }

    public void drawBarChart(String xAxisTitle, String yAxisTitle, int [] array, int max) {
        int rounds = storage.maxRounds;
        float barHeight;
        float barWidth = graphWidth / rounds;
        float barPosition;
        Main.processing.textAlign(CENTER, CENTER);
        Main.processing.textSize(14);
        String stat;
        float statPlacement;
        for(int i = 0; i < rounds; i++) {
            barPosition = barWidth * i;
            barHeight = ((float) array[i] / max) * graphHeight;
            stat = transferButton.clicked ? "" + (int) Math.ceil(array[i] / 1000) + "K" : "" + array[i];
            statPlacement = barHeight > 20 ? 10 : - 15;
            Main.processing.fill(127, 205, 0);
            Main.processing.rect(graphX + barPosition, (graphY + graphHeight) - barHeight, barWidth, barHeight);
            Main.processing.fill(0);
            Main.processing.text(stat, graphX + barPosition + (barWidth / 2), ((graphY + graphHeight) - barHeight) + statPlacement);
            Main.processing.fill(0);
            Main.processing.text(i + 1, graphX + barPosition + (barWidth/2), (graphY + graphHeight + bottomAxisPadding));
        }
        Main.processing.text(xAxisTitle, graphX + (graphWidth/2), (graphY + graphHeight + (bottomAxisPadding * 3)));
        Main.processing.line(graphX-10, graphY, graphX-10, graphY+graphHeight);
        Main.processing.line(graphX-10, graphY+graphHeight, graphX, graphY+graphHeight);
        drawVerticalText(yAxisTitle, graphX - 50, graphY + (graphHeight / 2));

        // draw YAxis Values
        Main.processing.fill(0);
        Main.processing.stroke(0);
        if(yAxisTitle.equals("Transfers")) {
            int tempMax = max / 100;
            for(int i = 0; i < 5; i ++) {
                Main.processing.text(str(round((4-i) * ((float) tempMax/4))) + "K", graphX - 35, graphY + (i * graphHeight/4));
            }
        } else {
            for(int i = 0; i < 5; i ++) {
                Main.processing.text(str((int)((4-i) * ((float) max/4))), graphX - 30, graphY + (i * graphHeight/4));
            }
        }

    }

    public boolean drawHoverOverParty(float x, float y, float width, float height) {
        float mouseX = Main.processing.mouseX;
        float mouseY = Main.processing.mouseY;
        if(x < mouseX && mouseX < x + width) {
            if(y < mouseY && mouseY < y + height) {
                return true;

            }
        }
        return false;
    }

    public void drawPartyInfoBox(String partyName, int round, int seatsWon, int percentWon) {
        float x = Main.processing.mouseX;
        float y = Main.processing.mouseY;
        Main.processing.textAlign(Main.processing.LEFT, Main.processing.CENTER);
        Main.processing.fill(240);
        Main.processing.rect(x, y, Main.processing.textWidth("Party: " + partyName) + 35, 50);
        x += 5;
        y += 5;
        Main.processing.fill(0);
        Main.processing.text("Party: " + partyName, x, y);
        y += 12;
        Main.processing.text("Round: " + round, x, y);
        y += 12;
        Main.processing.text("Seats Won: " + seatsWon, x, y);
        y += 12;
        Main.processing.text("Percent Won: " + percentWon + "%", x, y);
        Main.processing.textAlign(Main.processing.CENTER, Main.processing.CENTER);

    }

    boolean drawPartyBox = false;
    String partyNameTemp;
    int roundTemp;
    int roundWins;
    float percentSeatsWon;

    public void drawPercentageChart(String xAxisTitle, String yAxisTitle) {
        drawPartyBox = false;
        int rounds = storage.maxRounds;
        float barHeight;
        float barWidth = graphWidth / rounds;
        float barPosition;
        Main.processing.textAlign(CENTER, CENTER);
        Main.processing.textSize(14);
        int totalWins;
        for(int i = 0; i < rounds; i++) {
            totalWins = storage.getTotalWinsOfRound(i);
            barPosition = barWidth * i;
            float barYPosition = 0;
            for(int j = 0; j < storage.parties.size(); j++) {
                Colour colour = storage.partyColours[j];
                Main.processing.fill(colour.r, colour.g, colour.b);
                barHeight = ((float) storage.parties.get(j)[i] / totalWins) * graphHeight;
                Main.processing.rect(graphX + barPosition, (graphY + barYPosition), barWidth, barHeight);
                Main.processing.fill(255);
                if(barHeight > 15) {
                    String abb = storage.partyAbbreviations[j];
                    Main.processing.text(abb,graphX + barPosition + (barWidth / 2), (graphY + barYPosition) + (barHeight / 2));
                }
                if(drawHoverOverParty(graphX + barPosition, (graphY + barYPosition), barWidth, barHeight)) {
                    drawPartyBox = true;

                    partyNameTemp = storage.partiesName[j];
                    roundTemp = i+1;
                    roundWins = storage.parties.get(j)[i];
                    percentSeatsWon = ((float) storage.parties.get(j)[i] / totalWins) * 100;
                }
                barYPosition += barHeight;
            }

//            barHeight = ((float) array[i] / max) * graphHeight;
//            Main.processing.rect(graphX + barPosition, (graphY + graphHeight) - barHeight, barWidth, barHeight);
            Main.processing.fill(0);
            Main.processing.text(i + 1, graphX + barPosition + (barWidth/2), (graphY + graphHeight + bottomAxisPadding));
        }
        Main.processing.text(xAxisTitle, graphX + (graphWidth/2), (graphY + graphHeight + (bottomAxisPadding * 3)));
        Main.processing.line(graphX-10, graphY, graphX-10, graphY+graphHeight);
        Main.processing.line(graphX-10, graphY+graphHeight, graphX, graphY+graphHeight);
        drawVerticalText(yAxisTitle, graphX - 50, graphY + (graphHeight / 2));

        // draw YAxis Values
        Main.processing.fill(0);
        Main.processing.stroke(0);
        int max = 100;
        for(int i = 0; i < 5; i ++) {
            Main.processing.text(str((int)((4-i) * ((float) max/4))), graphX - 30, graphY + (i * graphHeight/4));
        }

        if(drawPartyBox) {
            drawPartyInfoBox(partyNameTemp, roundTemp, roundWins, (int) percentSeatsWon);
        }
    }

    public boolean mouseWithin(int x, int y) {
        if(x < Main.processing.mouseX && Main.processing.mouseX < x + width) {
            if(y < Main.processing.mouseY && Main.processing.mouseY < y + height) {
                return true;
            }
        }
        return false;
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
}
