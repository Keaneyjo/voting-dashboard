import processing.core.PApplet;
import processing.core.PShape;

import java.util.ArrayList;
import java.util.HashMap;


public class Constituency extends PApplet{

    public String name;
    public PShape shape;
    public float x;
    public float y;
    public float centerX;
    public float centerY;
    public int seats;

    public int electorate;
    public float turnout;

    public String [] winningParties;
    public String [] winnerNames;

    public HashMap<Integer, ArrayList<Candidate>> winnersPerRound = new HashMap<>();

    public Constituency(String name, PShape shape, float x, float y, int totalElectorate, int totalPoll, HashMap<String, ArrayList<String>> constituencyStats, int seats) {
        this.name = name;
        this.shape = shape;
        if(shape == null) {
            System.out.println(this.name);
        }
        this.shape.disableStyle();
        this.x = x;
        this.y = y;
        this.seats = seats;

        this.centerX = this.x + (this.shape.width / 2);
        this.centerY = this.y + (this.shape.height / 2);

        this.electorate = totalElectorate;

        this.turnout = ((float) totalPoll / totalElectorate * 100);
        this.turnout = (float) (Math.round(this.turnout * 100.0) / 100.0);

        if(!this.name.equals("Dublin") && !this.name.equals("Dublin Bay North Legend")) {
            ArrayList<String> stats = constituencyStats.get(this.name);
            this.winningParties = new String[stats.size() / 2];
            this.winnerNames = new String[stats.size() / 2];

            for(int i = 0; i < stats.size(); i+= 2) {
                winningParties[i/2] = stats.get(i);
                winnerNames[i/2] = stats.get(i+1);
            }
        }
    }

    public void setWinners() {
        for(int i = 1; i <= 15; i++) {
            winnersPerRound.put(i, Main.advancedStorage.winnerPerConsituencyPerRound.get(i).get(this.name));
        }
    }

    public boolean containsTwo(float x, float y) {
        if(this.x < x && x < this.x + this.shape.width) {
            if(this.y < y && y < this.y + this.shape.height) {
                return true;
            }
        }
        return false;
    }
}
