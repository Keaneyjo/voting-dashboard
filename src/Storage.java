import processing.data.Table;
import processing.data.TableRow;

import java.util.*;

public class Storage {

    Table table;
    public int maxRounds = 15;
    public int N; // = 16 - 1; // - 1 for header
    public int [] winners;
    public int [] transfers;
    public int [] excluded;
    public int [] aontu;
    public int [] ff;
    public int [] fg;
    public int [] gp;
    public int [] ind;
    public int [] demo;
    public int [] freedom;
    public int [] non;
    public int [] renua;
    public int [] sf;
    public int [] sd;
    public int [] pbp;
    public int [] lab;
    public int [] national;
    public int [] united;
    public int [] workers;

    public ArrayList<int []> parties;
    public String [] partiesName;
    public String [] partyAbbreviations;
    public Colour [] partyColours;
    //    Rounds,Winners,Tranfers,Excluded,Aont�,Fianna F�il,Fine Gael,Green Party/ Comhaontas Glas,
//    Independents 4 Change,Irish Democratic Party,Irish Freedom Party,Non-Party,Renua Ireland,
//    Sinn F�in,Social Democrats,Solidarity - People Before Profit,The Labour Party,
//    The National Party - An P�irt� N�isi�nta,United People,Workers Party
//1,34,0,0,0,0,2,1,0,0,0,4,0,27,0,0,0,0,0,0

    public Storage(Table table) {
        N = table.getRowCount();

        winners = new int[N];
        transfers = new int[N];
        excluded = new int[N];
        aontu = new int[N];
        ff = new int[N];
        fg = new int[N];
        gp = new int[N];
        ind = new int[N];
        demo = new int[N];
        freedom = new int[N];
        non = new int[N];
        renua = new int[N];
        sf = new int[N];
        sd = new int[N];
        pbp = new int[N];
        lab = new int[N];
        national = new int[N];
        united = new int[N];
        workers = new int[N];

        ReadyTable(table);
    }

    public void ReadyTable(Table table) {
        for (int i = 0; i < N; i++) {
            TableRow row = table.getRow(i);

            //    Rounds,Winners,Tranfers,Excluded,Aont�,Fianna F�il,Fine Gael,Green Party/ Comhaontas Glas,
//    Independents 4 Change,Irish Democratic Party,Irish Freedom Party,Non-Party,Renua Ireland,
//    Sinn F�in,Social Democrats,Solidarity - People Before Profit,The Labour Party,
//    The National Party - An P�irt� N�isi�nta,United People,Workers Party

            winners[i] = row.getInt("Winners");
            transfers[i] = row.getInt("Tranfers");
            excluded[i] = row.getInt("Excluded");
            aontu[i] = row.getInt("Aont�");
            ff[i] = row.getInt("Fianna F�il");
            fg[i] = row.getInt("Fine Gael");
            gp[i] = row.getInt("Green Party/ Comhaontas Glas");
            ind[i] = row.getInt("Independents 4 Change");
            demo[i] = row.getInt("Irish Democratic Party");
            freedom[i] = row.getInt("Irish Freedom Party");
            non[i] = row.getInt("Non-Party");
            renua[i] = row.getInt("Renua Ireland");
            sf[i] = row.getInt("Sinn F�in");
            sd[i] = row.getInt("Social Democrats");
            pbp[i] = row.getInt("Solidarity - People Before Profit");
            lab[i] = row.getInt("The Labour Party");
            national[i] = row.getInt("The National Party - An P�irt� N�isi�nta");
            united[i] = row.getInt("United People");
            workers[i] = row.getInt("Workers Party");

            //System.out.print(winners[i]);
        }

        parties = new ArrayList<int[]>() {
            {add(aontu); add(ff); add(fg); add(gp); add(ind); add(demo); add(freedom);
                add(non); add(renua); add(sf); add(sd); add(pbp); add(lab);
                add(national); add(united); add(workers); }
        };

        partiesName = new String [] {
                "Aontú",	"Fianna Fáil",	"Fine Gael",	"Green Party/ Comhaontas Glas",	"Independents 4 Change",
                "Irish Democratic Party",	"Irish Freedom Party",	"Non-Party",	"Renua Ireland",
                "Sinn Féin", "Social Democrats",	"Solidarity - People Before Profit",
                "The Labour Party",	"The National Party - An Páirtí Náisiúnta",	"United People",
                "Workers Party"
        };

        partyAbbreviations = new String [] {
                "A",	"FF",	"FG",	"GP",	"I",
                "DP",	"FP",	"NO",	"RI",
                "SF", "SD",	"PBP",
                "LA",	"NP",	"UP",
                "WP"
        };

        partyColours = new Colour [] {
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

    public int getTotalWinsOfRound(int i) {
        int total = 0;
        for (int[] party : parties) {
            total += party[i];
        }
        return total;
    }

}

