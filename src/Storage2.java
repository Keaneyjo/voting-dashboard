import processing.data.Table;
import processing.data.TableRow;

import java.util.ArrayList;
import java.util.HashMap;

public class Storage2 {

    Table table;
    public int maxRounds = 15;
    public int N; // = 16 - 1; // - 1 for header
    public String [] constituency;
    public String [] surname;
    public String [] firstname;
    public String [] gender;
    public String [] party;
    public String [] abbreviation;
    public int [] roundcount;
    public int [] quota;
    public int [] deposit;
    public int [] first_votes;
    public String [] result;
    public int [] id;
    public int [] constituency_id;
    public String [] constituency_gaeilge;
    public int [] round_won;
    public int [] total_votes;


    public ArrayList<int []> parties;
    public String [] partiesName;
    public String [] partyAbbreviations;
    public Colour [] partyColours;
    public HashMap<String, Colour> adPartyColours;
    //    Rounds,Winners,Tranfers,Excluded,Aont�,Fianna F�il,Fine Gael,Green Party/ Comhaontas Glas,
//    Independents 4 Change,Irish Democratic Party,Irish Freedom Party,Non-Party,Renua Ireland,
//    Sinn F�in,Social Democrats,Solidarity - People Before Profit,The Labour Party,
//    The National Party - An P�irt� N�isi�nta,United People,Workers Party
//1,34,0,0,0,0,2,1,0,0,0,4,0,27,0,0,0,0,0,0

    // Constituency,Surname,Firstname,Gender Id,PartyId,PartyAbbreviation,Count Number,Required To Reach Quota,Required To Save Deposit,Votes,Result,CandidateId,Constituency Number,Constituency Ainm,RoundWon,TotalVotes
    public Storage2(Table table) {
        N = table.getRowCount();

        constituency = new String[N];
        surname = new String[N];
        firstname = new String[N];
        gender = new String[N];
        party = new String[N];
        abbreviation = new String[N];
        roundcount = new int[N];
        quota = new int[N];
        deposit = new int[N];
        first_votes = new int[N];
        result = new String[N];
        id = new int[N];
        constituency_id = new int[N];
        constituency_gaeilge = new String[N];
        round_won = new int[N];
        total_votes = new int[N];

        ReadyTable(table);
    }

    // candidatesParty - name, party
    public HashMap<String, String> candidatesParty = new HashMap<String, String>();
    public void ReadyTable(Table table) {
        for (int i = 0; i < N; i++) {
            TableRow row = table.getRow(i);

            //    Rounds,Winners,Tranfers,Excluded,Aont�,Fianna F�il,Fine Gael,Green Party/ Comhaontas Glas,
            //    Independents 4 Change,Irish Democratic Party,Irish Freedom Party,Non-Party,Renua Ireland,
            //    Sinn F�in,Social Democrats,Solidarity - People Before Profit,The Labour Party,
            //    The National Party - An P�irt� N�isi�nta,United People,Workers Party

            constituency[i] = row.getString("Constituency");
            surname[i] = row.getString("Surname");
            firstname[i] = row.getString("Firstname");
            gender[i] = row.getString("Gender Id");
            party[i] = row.getString("PartyId");
            abbreviation[i] = row.getString("PartyAbbreviation");
            roundcount[i] = row.getInt("Count Number");
            quota[i] = row.getInt("Required To Reach Quota");
            deposit[i] = row.getInt("Required To Save Deposit");
            first_votes[i] = row.getInt("Votes");
            result[i] = row.getString("Result");
            id[i] = row.getInt("CandidateId");
            constituency_id[i] = row.getInt("Constituency Number");
            constituency_gaeilge[i] = row.getString("Constituency Ainm");
            round_won[i] = row.getInt("RoundWon");
            total_votes[i] = row.getInt("TotalVotes");
            //Constituency,Surname,Firstname,Gender Id,PartyId,PartyAbbreviation,Count Number,Required To Reach Quota,Required To Save Deposit,Votes,Result,CandidateId,Constituency Number,Constituency Ainm,RoundWon,TotalVotes

            candidatesParty.put(firstname[i] + " " + surname[i] + " " + id[i], party[i]);
        }

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

        adPartyColours = new HashMap<String, Colour>(){{
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

