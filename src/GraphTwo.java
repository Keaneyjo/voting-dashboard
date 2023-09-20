import processing.core.PApplet;
import processing.core.PShape;

import java.util.HashMap;
import java.util.HashSet;

public class GraphTwo extends PApplet {

    Storage2 storage;

    // x, y
    //
    //          graphWidth, graphHeight
    int graphX = 100;
    int graphY = 430;
    int graphWidth = 600;
    int graphHeight = 180;
    int currentChart;

    float buttonWidth = graphWidth / 4;
    float buttonHeight =  20;
    float buttonY = graphY - (buttonHeight + 15);

//    int graphX = 100;
//    int graphY = 85;
//    int graphWidth = 600;
//    int graphHeight = 180;
//    int currentChart;
//
//    float buttonWidth = graphWidth / 4;
//    float buttonHeight =  20;
//    float buttonY = graphY - (buttonHeight + 15);

    Button countButton = new Button("Count", (float) graphX, buttonY, buttonWidth, buttonHeight, new Colour(255, 255, 255), new Colour(150, 150, 150));
    Button genderButton = new Button("Gender", (float) graphX + buttonWidth, buttonY, buttonWidth, buttonHeight, new Colour(255, 255, 255), new Colour(150, 150, 150));
    Button partiesButton = new Button("Parties", (float) graphX + buttonWidth + buttonWidth, buttonY, buttonWidth, buttonHeight, new Colour(255, 255, 255), new Colour(150, 150, 150));
    Button roundWonButton = new Button("Round Won", (float) graphX + buttonWidth + buttonWidth + buttonWidth, buttonY, buttonWidth, buttonHeight, new Colour(255, 255, 255), new Colour(150, 150, 150));

    Button countButtonTwo = new Button("Count", (float) graphX, buttonY - buttonHeight, buttonWidth, buttonHeight, new Colour(255, 255, 255), new Colour(150, 150, 150));
    Button genderButtonTwo = new Button("Gender", (float) graphX + buttonWidth, buttonY - buttonHeight, buttonWidth, buttonHeight, new Colour(255, 255, 255), new Colour(150, 150, 150));
    Button partiesButtonTwo = new Button("Parties", (float) graphX + buttonWidth + buttonWidth, buttonY - buttonHeight, buttonWidth, buttonHeight, new Colour(255, 255, 255), new Colour(150, 150, 150));
    Button roundWonButtonTwo = new Button("Round Won", (float) graphX + buttonWidth + buttonWidth + buttonWidth, buttonY - buttonHeight, buttonWidth, buttonHeight, new Colour(255, 255, 255), new Colour(150, 150, 150));

    String [] currentXAxisValues;
    String [] currentYAxisValues;
    String currentXAxisLabel;
    String currentYAxisLabel;
    String [] totalVotesBins;
    String [] roundWonString;
    String [] totalVoteSet = new String[] {"5.0", "6.0", "7.0", "8.0", "9.0", "10.0", "11.0", "12.0", "13.0", "14.0"};
    String [] currentYAxisSet = new String[] {"14.0", "13.0", "12.0", "11.0", "10.0", "9.0", "8.0", "7.0", "6.0", "5.0" };
    String [] currentXAxisSet = totalVoteSet;
    String xTitle = "Total Votes (per 1000)";
    String yTitle = "Total Votes (per 1000)";

    public static boolean pluralityFill = true;

    private static double round (double value, int precision) {
        int scale = (int) Math.pow(10, precision);
        return (double) Math.round(value * scale) / scale;
    }

    public GraphTwo(Storage2 storage) {
        this.storage = storage;
        this.currentChart = 1;
        convertTotalVotes();
        convertRoundWon();
        currentXAxisValues = totalVotesBins;
        currentYAxisValues = totalVotesBins;
        countButton.clicked = true;
        countButtonTwo.clicked = true;
        checkClick();
    }

    public void convertTotalVotes() {
        //max total votes = 14046
        // divide by 6, 15000 / 6 = 2500
        totalVotesBins = new String[storage.total_votes.length];
        for(int i = 0; i < storage.total_votes.length; i++) {
            totalVotesBins[i] = "" + round(storage.total_votes[i] / 1000, 1);
        }
    }

    public void convertRoundWon() {
        //max total votes = 14046
        // divide by 6, 15000 / 6 = 2500
        roundWonString = new String[storage.round_won.length];
        for(int i = 0; i < storage.total_votes.length; i++) {
            roundWonString[i] = "" + storage.round_won[i];
        }
    }

    public void checkClick() {
        if(countButton.mouseWithin()) {
            countButton.clicked = true;
            genderButton.clicked = false;
            partiesButton.clicked = false;
            roundWonButton.clicked = false;
            currentXAxisValues = totalVotesBins;
            currentXAxisSet = totalVoteSet;
            xTitle = "Total Votes (per 1000)";
        } else if(genderButton.mouseWithin()) {
            countButton.clicked = false;
            genderButton.clicked = true;
            partiesButton.clicked = false;
            roundWonButton.clicked = false;
            currentXAxisValues = storage.gender;
            currentXAxisSet = readyAxisValues(storage.gender);
            xTitle = "Gender";
        } else if (partiesButton.mouseWithin()) {
            countButton.clicked = false;
            genderButton.clicked = false;
            partiesButton.clicked = true;
            roundWonButton.clicked = false;
            currentXAxisValues = storage.abbreviation;
            currentXAxisSet = readyAxisValues(storage.abbreviation);
            xTitle = "Party Abbreviations";
        } else if(roundWonButton.mouseWithin()) {
            countButton.clicked = false;
            genderButton.clicked = false;
            partiesButton.clicked = false;
            roundWonButton.clicked = true;
            currentXAxisValues = roundWonString;
            currentXAxisSet = new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15"};
            xTitle = "Round Won";
        }

        // YAxis
        if(countButtonTwo.mouseWithin()) {
            countButtonTwo.clicked = true;
            genderButtonTwo.clicked = false;
            partiesButtonTwo.clicked = false;
            roundWonButtonTwo.clicked = false;
            currentYAxisValues = totalVotesBins;
            currentYAxisSet = new String[] {"14.0", "13.0", "12.0", "11.0", "10.0", "9.0", "8.0", "7.0", "6.0", "5.0" };
            yTitle = "Total Votes (per 1000)";
        } else if(genderButtonTwo.mouseWithin()) {
            countButtonTwo.clicked = false;
            genderButtonTwo.clicked = true;
            partiesButtonTwo.clicked = false;
            roundWonButtonTwo.clicked = false;
            currentYAxisValues = storage.gender;
            currentYAxisSet = readyAxisValues(storage.gender);
            yTitle = "Gender";
        } else if (partiesButtonTwo.mouseWithin()) {
            countButtonTwo.clicked = false;
            genderButtonTwo.clicked = false;
            partiesButtonTwo.clicked = true;
            roundWonButtonTwo.clicked = false;
            currentYAxisValues = storage.abbreviation;
            currentYAxisSet = readyAxisValues(storage.abbreviation);
            yTitle = "Party Abbreviations";
        } else if(roundWonButtonTwo.mouseWithin()) {
            countButtonTwo.clicked = false;
            genderButtonTwo.clicked = false;
            partiesButtonTwo.clicked = false;
            roundWonButtonTwo.clicked = true;
            currentYAxisValues = roundWonString;
            currentYAxisSet = new String[] {"15", "14", "13", "12", "11", "10", "9", "8", "7",  "6", "5", "4", "3", "2", "1"};
            yTitle = "Round Won";
        }
    }


    public void drawLegend() {
//        Main.processing.fill(250);
//        Main.processing.rect(graphX + graphWidth + 60, graphY - 30, 120, 150);
        float x = graphX + graphWidth + 90;
        float y = (graphY - 30) + 60;
        Main.processing.fill(127, 205, 0, 85);
        Main.processing.circle(x, y, 100);
        Main.processing.circle(x, y + 25, 50);
        Main.processing.circle(x, y + 45, 10);

        Main.processing.fill(0);
        float bottom_or_circle = y + 50;

        //Main.processing.line(x, bottom_or_circle, x+100, bottom_or_circle);
        Main.processing.line(x, y + 40, x+60, y + 40);
        Main.processing.line(x, y, x+60, y);
        Main.processing.line(x, y - 50, x+60, y - 50);

        Main.processing.textAlign(Main.processing.LEFT, Main.processing.CENTER);
        Main.processing.text("10", x+70, y + 40);
        Main.processing.text("50", x+70, y);
        Main.processing.text("100", x+70, y - 50);

        Main.processing.textAlign(Main.processing.CENTER, Main.processing.CENTER);
        Main.processing.text("Winner Bubble Legend", x+20, y - 80);
    }


    public void draw() {
        Main.processing.stroke(0);
        Main.processing.fill(0);
        Main.processing.textSize(17);
        Main.processing.text("Attribute Vs. Attribute Scatter", graphX + (graphWidth/2), buttonY-45);
        Main.processing.textSize(14);

        Main.processing.text("Y-Axis", (float) graphX /2, (buttonY - (buttonHeight/2)) - 3);
        Main.processing.text("X-Axis", (float) graphX /2, (buttonY + (buttonHeight/2)) - 3);

        countButton.draw();
        genderButton.draw();
        partiesButton.draw();
        roundWonButton.draw();

        countButtonTwo.draw();
        genderButtonTwo.draw();
        partiesButtonTwo.draw();
        roundWonButtonTwo.draw();


        drawLegend();
        drawOtherLegend();
        //drawScatterChart("Gender", "Gender", readyAxisValues(currentXAxisValues), readyAxisValues(currentYAxisValues), readyAxis2(currentXAxisValues, currentYAxisValues));
        drawScatterChart(xTitle, yTitle, currentXAxisSet, currentYAxisSet, readyAxis2(currentXAxisValues, currentYAxisValues));

    }

    public void drawOtherLegend() {
        // 1 seat
        // left -
        float x = graphX + graphWidth + 260 + 50;
        float y = (graphY - 30);

        Main.processing.textSize(15);
        Main.processing.text("Constituency Map Legend", x + Main.processing.textWidth("Constituency Map Legend")/4, y - 40);
        Main.processing.line(x - Main.processing.textWidth("Constituency Map Legend")/4, y - 25,
                x + Main.processing.textWidth("Constituency Map Legend") - Main.processing.textWidth("Constituency Map Legend")/4, y - 25);
        Main.processing.textSize(14);

        Main.processing.fill(0, 205, 100);
        Main.processing.circle(x, y, 10);
        x += 50;
        Main.processing.fill(0);
        Main.processing.text("1 Seat", x + 30, y - 2);


//        Main.processing.line(0, graphY - 25, graphX + (float) (graphWidth * .65), graphY - 25);
//        Main.processing.line(graphX + (float) (graphWidth * .65), graphY - 25, buttonX + buttonWidth + 15, buttonY - 45);
//        float otherGraphY = 430;
//        Main.processing.line(buttonX + buttonWidth + 15, buttonY - 45, buttonX + buttonWidth + 15, otherGraphY - 95);
//        Main.processing.line(buttonX + buttonWidth + 15, otherGraphY - 95, 0, otherGraphY - 95);




        Main.processing.strokeWeight(1);
        Main.processing.fill(64, 128, 128);
        Main.processing.shape(Main.dublinBayNorth.shape, Main.dublinBayNorth.x - 5, Main.dublinBayNorth.y + 25);

        // colour of county
        Main.processing.fill(50);
        Main.processing.strokeWeight(2);
        Main.processing.textAlign(Main.processing.LEFT, Main.processing.CENTER);
        Main.processing.text("Average of", x + 10, Main.dublinBayNorth.y + (Main.dublinBayNorth.height/2) - 6 - 5);
        Main.processing.text("Party's Colours", x + 10, Main.dublinBayNorth.y + (Main.dublinBayNorth.height/2) + 6 - 5);
        Main.processing.line(x + 5, Main.dublinBayNorth.y + (Main.dublinBayNorth.height/2) - 5,
                x - 60, Main.dublinBayNorth.y + (Main.dublinBayNorth.height/2) - 5);


        Main.processing.strokeWeight(1);
        if(pluralityFill) {
            Main.processing.fill(255, 0, 0);
            Main.processing.strokeWeight(1);
            Main.processing.stroke(0, 0, 0);
            Main.processing.shape(Main.dublinBayNorth.shape, Main.dublinBayNorth.x - 5, Main.dublinBayNorth.y + 85);


            Main.processing.fill(50);
            Main.processing.strokeWeight(2);
            y += 60;
            Main.processing.textAlign(Main.processing.LEFT, Main.processing.CENTER);
            Main.processing.text("Difference between STV", x + 10, Main.dublinBayNorth.y + (Main.dublinBayNorth.height/2) - 6 - 5 + 60);
            Main.processing.text("Plurality and Results", x + 10, Main.dublinBayNorth.y + (Main.dublinBayNorth.height/2) + 6 - 5 + 60);
            Main.processing.line(x + 5, Main.dublinBayNorth.y + (Main.dublinBayNorth.height/2) - 5 + 60,
                    x - 60, Main.dublinBayNorth.y + (Main.dublinBayNorth.height/2) - 5 + 60);

            Main.processing.strokeWeight(1);
            Main.processing.stroke(0);
        } else {
            Main.processing.strokeWeight(7);
            Main.processing.stroke(255, 0, 0);
            Main.processing.shape(Main.dublinBayNorth.shape, Main.dublinBayNorth.x - 5, Main.dublinBayNorth.y + 85);
            Main.processing.strokeWeight(1);
            Main.processing.stroke(0);

            Main.processing.fill(50);
            Main.processing.strokeWeight(2);
            y += 60;
            Main.processing.textAlign(Main.processing.LEFT, Main.processing.CENTER);
            Main.processing.text("Difference between STV", x + 10, Main.dublinBayNorth.y + (Main.dublinBayNorth.height/2) - 6 - 5 + 60);
            Main.processing.text("Plurality and Results", x + 10, Main.dublinBayNorth.y + (Main.dublinBayNorth.height/2) + 6 - 5 + 60);
            Main.processing.line(x + 5, Main.dublinBayNorth.y + (Main.dublinBayNorth.height/2) - 5 + 60,
                    x - 30, Main.dublinBayNorth.y + (Main.dublinBayNorth.height/2) - 5 + 60);

            Main.processing.strokeWeight(1);
            Main.processing.stroke(0);
        }



        // county red stroke



        Main.processing.textAlign(Main.processing.CENTER, Main.processing.CENTER);
    }

    public HashMap<String, Integer> readyAxis(String [] axis) {
        HashMap<String, Integer> xAxis = new HashMap<String, Integer>();
        for(int i = 1; i < storage.result.length; i++) {
            if(storage.result[i].equals("Elected")) {
                if (xAxis.containsKey(axis[i])) {
                    xAxis.put(axis[i], xAxis.get(axis[i]) + 1);
                } else {
                    xAxis.put(axis[i], 1);
                }
            }
        }
        return xAxis;
    }

    public HashMap<String, Integer> readyAxis2(String [] xaxis, String [] yaxis) {
        HashMap<String, Integer> winners = new HashMap<String, Integer>();
        for(int i = 1; i < storage.result.length; i++) {
            if(storage.result[i].equals("Elected")) {
                String key = xaxis[i] + yaxis[i];
                if (winners.containsKey(key)) {
                    winners.put(key, winners.get(key) + 1);
                } else {
                    winners.put(key, 1);
                }
            }
        }
        return winners;
    }

    public String[] readyAxisValues(String [] axis) {
        HashSet<String> axisValues = new HashSet<String>();
        for(int i = 0; i < axis.length; i++) {
                axisValues.add(axis[i]);
        }
        axisValues.remove(null);
        String[] set = new String[axisValues.size()];
        int i = 0;
        for(String s : axisValues) {
            set[i] = s;
            i++;
        }
        return set;
    }

    public void drawScatterChart(String xAxisTitle, String yAxisTitle, String[] xaxis, String[] yaxis, HashMap<String, Integer> values) {
        float xEntryWidth = (float) graphWidth / (xaxis.length); // pad the empty space for start and end
        float yEntryWidth = (float) graphHeight / (yaxis.length + 1); // pad the empty space for start and end
        float barXPosition = graphX + (xEntryWidth / 2);
        float barYPosition = graphY + yEntryWidth;

        Main.processing.textAlign(CENTER, CENTER);
        Main.processing.textSize(14);

        // draw grey grid
        Main.processing.fill(200);
        Main.processing.stroke(200, 200, 200, 65);
        for(String x : xaxis) {
            Main.processing.line(barXPosition, graphY, barXPosition, graphY + graphHeight);
            barXPosition += xEntryWidth;
        }
        for(String y : yaxis) {
            Main.processing.line(graphX, barYPosition, graphX + graphWidth, barYPosition);
            barYPosition += yEntryWidth;
        }
        Main.processing.stroke(0);
        Main.processing.fill(255);

        barXPosition = graphX + (xEntryWidth / 2);

        float tempWinners = 0;
        boolean drawBox = false;
        String xAxisInfo = "";
        String yAxisInfo = "";
        for(String x : xaxis) {
            barYPosition = graphY + yEntryWidth;
            for(String y : yaxis) {
                String key = x + y;
                if(values.containsKey(key)) {
                    int totalWinners = values.get(x + y);
                    Main.processing.fill(127, 205, 0);
                    Main.processing.circle(barXPosition, barYPosition, (float) totalWinners);

                    // info box here
                    if(withinCircle(barXPosition, barYPosition, totalWinners))
                    {
                        drawBox = true;
                        tempWinners = totalWinners;
                        xAxisInfo = xAxisTitle + ": " + x;
                        yAxisInfo = yAxisTitle + ": " + y;
                    }
                    Main.processing.fill(255);
                }
                barYPosition += yEntryWidth;
            }
            Main.processing.line(graphX, barYPosition, graphX + graphWidth, barYPosition);
            //Main.processing.textSize(12);
            drawVerticalText(countButton.clicked ? x + "K" : x, barXPosition, graphY + graphHeight + 25);
            //Main.processing.textSize(14);
            Main.processing.text(x, barXPosition, barYPosition);
            barXPosition += xEntryWidth;
        }

        Main.processing.line(graphX, graphY + graphHeight, graphX + graphWidth, graphY + graphHeight);

        //Main.processing.textSize(12);
        // draw y axis
        Main.processing.fill(0);
        barYPosition = graphY + yEntryWidth;
        for(String y : yaxis) {
            Main.processing.text(countButtonTwo.clicked ? y + "K" : y, graphX - 40, barYPosition);
            barYPosition += yEntryWidth;
        }
        Main.processing.textSize(14);

        // draw vertical axis
        Main.processing.line(graphX, graphY, graphX, graphY + graphHeight);

        // draw xaxis title
        Main.processing.text(xAxisTitle, graphX + (graphWidth/2), graphY + graphHeight + 55);

        // draw yaxis title
        drawVerticalText(yAxisTitle, graphX - 75, graphY + (graphHeight/2) - 10);

        if(drawBox) {
            ScatterBox.draw(xAxisInfo, yAxisInfo, (int) tempWinners);
        }
    }

    public boolean withinCircle(float x, float y, float diameter) {
        float radius = (float) diameter / 2;
        if(x - radius < Main.processing.mouseX && Main.processing.mouseX < x + radius) {
            if(y - radius < Main.processing.mouseY && Main.processing.mouseY < y + radius) {
                return true;
            }
        }
        return false;
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

//        if(winnerButton.mouseWithin()) {
//                winnerButton.clicked = winnerButton.clicked ? false : true;
//                this.currentChart = 0;
//                } else if(transferButton.mouseWithin()) {
//                transferButton.clicked = transferButton.clicked ? false : true;
//                this.currentChart = 1;
//                } else if(excludedButton.mouseWithin()) {
//                excludedButton.clicked = excludedButton.clicked ? false : true;
//                this.currentChart = 2;
//                } else if(partiesButton.mouseWithin()) {
//                partiesButton.clicked = partiesButton.clicked ? false : true;
//                this.currentChart = 3;
//                }



//    public void drawScatterChart(String xAxisTitle, String yAxisTitle, HashSet<String> xaxis, HashMap<String, Integer> yaxis) {
//        float xEntryWidth = (float) graphWidth / xaxis.size() + 2; // pad the empty space for start and end
//        float yEntryWidth = (float) graphHeight / yaxis.size() + 2; // pad the empty space for start and end
//        float barXPosition = xEntryWidth;
//
//        Main.processing.textAlign(CENTER, CENTER);
//        Main.processing.textSize(14);
//
//        for(Map.Entry<String, Integer> xEntry : xaxis.entrySet()) {
//            String xValue = xEntry.getKey();//HashMap value = entry.getValue();
//
//            float barYPosition = yEntryWidth;
//
//            for(Map.Entry<String, Integer> yEntry : yaxis.entrySet()) {
//                String yValue = yEntry.getKey();//HashMap value = entry.getValue();
//
//                Main.processing.circle(xEntryWidth, yEntryWidth, );
//            }
//
//            barXPosition += xEntryWidth;
//        }