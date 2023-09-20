import processing.data.Table;
import processing.data.TableRow;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;

public class AdvancedStorage {

    public int maxRounds = 15;
    public int N; // = 16 - 1; // - 1 for header
    public String[] constituency;
    public String[] surname;
    public String[] firstname;
    public String[] gender;
    public String[] party;
    public String[] abbreviation;
    public int[] roundcount;
    public int[] quota;
    public int[] deposit;
    public int[] first_votes;
    public String[] result;
    public int[] id;
    public int[] constituency_id;
    public String[] constituency_gaeilge;
    public int[] round_won;
    public int[] total_votes;


    public ArrayList<int[]> parties;
    public HashMap<String, ArrayList<CandidateRoundOne>> pluralityWinnersPerConstituency;
    public HashMap<Integer, HashMap<String, ArrayList<Candidate>>> winnerPerConsituencyPerRound;

    // Constituency,Surname,Firstname,Gender Id,PartyId,PartyAbbreviation,Count Number,Required To Reach Quota,Required To Save Deposit,Votes,Result,CandidateId,Constituency Number,Constituency Ainm,RoundWon,TotalVotes
    public AdvancedStorage(Table table) {
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

        pluralityWinnersPerConstituency = new HashMap<String, ArrayList<CandidateRoundOne>>();

        winnerPerConsituencyPerRound = new HashMap<Integer, HashMap<String, ArrayList<Candidate>>>();
        ReadyTable(table);
    }

    public void ReadyTable(Table table) {
        for (int i = 0; i < N; i++) {
            TableRow row = table.getRow(i);

            // Constituency,Surname,FirstName,Result,CountNumber,Non-Transferable,OccurredOnCount,RequiredToReachQuota,RequiredToSaveDeposit,Transfers,Votes,TotalVotes,ConstituencyNumber,CandidateId
            constituency[i] = row.getString("Constituency");
            surname[i] = row.getString("Surname");
            firstname[i] = row.getString("FirstName");
            result[i] = row.getString("Result");
            roundcount[i] = row.getInt("CountNumber");
            first_votes[i] = row.getInt("Votes");
            id[i] = row.getInt("CandidateId");

            //gender[i] = row.getString("Gender Id");
            //party[i] = row.getString("PartyId");

//            quota[i] = row.getInt("Required To Reach Quota");
//            deposit[i] = row.getInt("Required To Save Deposit");
//            constituency_id[i] = row.getInt("Constituency Number");
//            constituency_gaeilge[i] = row.getString("Constituency Ainm");
//            round_won[i] = row.getInt("RoundWon");
            total_votes[i] = row.getInt("TotalVotes");
            //Constituency,Surname,Firstname,Gender Id,PartyId,PartyAbbreviation,Count Number,Required To Reach Quota,Required To Save Deposit,Votes,Result,CandidateId,Constituency Number,Constituency Ainm,RoundWon,TotalVotes

        }

        // issue with this is that some elections do not reach the 15 round
        // so i'll back track if thats the case, if asks for 15, but no 15, check 14, and repeat till finds one.
        createPluralityResults();
        HashMap<String, ArrayList<Candidate>> winnersPerConstituency = new HashMap<String, ArrayList<Candidate>>();
        HashMap<String, ArrayList<Candidate>> winnersPerConstituencytemp;
        for(int i = 1; i <= maxRounds; i++) {
            winnersPerConstituencytemp = createSTVResults(i, winnersPerConstituency);
            winnersPerConstituency = (HashMap<String, ArrayList<Candidate>>) winnersPerConstituencytemp.clone();

        }

//        for(int i = 1; i <= maxRounds; i++) {
//            createSTVResults(i);
//        }
        System.out.print("Finished generating STV results.");
    }




    // winners for each round

    // winners for plurality
    public static ArrayList<CandidateRoundOne> candidateRoundOneSort(ArrayList<CandidateRoundOne> list) {
        list.sort(Comparator.comparing(o -> o.votes));
        //        list.sort((o1, o2)
        //            -> o1.votes.compareTo(
        //                o2.votes));
        return list;
    }

    public static ArrayList<Candidate> candidateSort(ArrayList<Candidate> list) {
        list.sort(Comparator.comparing(o -> o.votes));
        //        list.sort((o1, o2)
        //            -> o1.votes.compareTo(
        //                o2.votes));
        return list;
    }


    public void createPluralityResults() {
        CandidateRoundOne candidateRoundOne;
        ArrayList<CandidateRoundOne> listOfCandidates;
        for (int i = 0; i < N; i++) {
            if(roundcount[i] == 1) {
                // candidateRoundOne = new CandidateRoundOne(firstname[i] + " " + surname[i], party[i], first_votes[i]);
                candidateRoundOne = new CandidateRoundOne(firstname[i] + " " + surname[i], first_votes[i], id[i]);
                if(pluralityWinnersPerConstituency.containsKey(constituency[i])) {
                    listOfCandidates = pluralityWinnersPerConstituency.get(constituency[i]);
                    listOfCandidates.add(candidateRoundOne);
                    pluralityWinnersPerConstituency.put(constituency[i], listOfCandidates);
                } else {
                    listOfCandidates = new ArrayList<CandidateRoundOne>();
                    listOfCandidates.add(candidateRoundOne);
                    pluralityWinnersPerConstituency.put(constituency[i], listOfCandidates);
                }
            }
        }

        for(String constit : pluralityWinnersPerConstituency.keySet()) {
            listOfCandidates = pluralityWinnersPerConstituency.get(constit);
            listOfCandidates = candidateRoundOneSort(listOfCandidates);
            pluralityWinnersPerConstituency.put(constit, listOfCandidates);
        }
    }

    HashSet<String> alreadyAdded = new HashSet<String>();
    public HashMap<String, ArrayList<Candidate>> createSTVResults(int round, HashMap<String, ArrayList<Candidate>> winnersPerConstituency) {

        Candidate candidate;
        ArrayList<Candidate> listOfCandidates;
        int votes;
        for (int i = 0; i < N; i++) {
            if(roundcount[i] == round) {
                if(result[i].equals("Elected")) {
                    // candidateRoundOne = new CandidateRoundOne(firstname[i] + " " + surname[i], party[i], first_votes[i]);
                    votes = Math.max(first_votes[i], total_votes[i]);
                    candidate = new Candidate(firstname[i] + " " + surname[i], votes, id[i]);
                    if(winnersPerConstituency.containsKey(constituency[i])) {
                        listOfCandidates = winnersPerConstituency.get(constituency[i]);
                        // candidates who have already won, are already in here, should not be put in again
                        if(!alreadyAdded.contains(firstname[i] + " " + surname[i] + " " + id[i])) {
                            listOfCandidates.add(candidate);
                            winnersPerConstituency.put(constituency[i], (ArrayList<Candidate>) listOfCandidates.clone());
                            alreadyAdded.add(firstname[i] + " " + surname[i] + " " + id[i]);
                        }
                    } else {
                        listOfCandidates = new ArrayList<Candidate>();
                        listOfCandidates.add(candidate);
                        winnersPerConstituency.put(constituency[i], (ArrayList<Candidate>) listOfCandidates.clone());
                        alreadyAdded.add(firstname[i] + " " + surname[i] + " " + id[i]);
                    }
                }
            }
        }

        for(String constit : winnersPerConstituency.keySet()) {
            listOfCandidates = (ArrayList<Candidate>) winnersPerConstituency.get(constit).clone();
            listOfCandidates = candidateSort(listOfCandidates);
            winnersPerConstituency.put(constit, listOfCandidates);
        }

        this.winnerPerConsituencyPerRound.put(round, (HashMap<String, ArrayList<Candidate>>) winnersPerConstituency.clone());
        return winnersPerConstituency;
    }


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


//    public void createSTVResults(int round) {
//        HashMap<String, ArrayList<Candidate>> winnersPerConstituency = new HashMap<String, ArrayList<Candidate>>();
//
//        Candidate candidate;
//        ArrayList<Candidate> listOfCandidates;
//        int votes;
//        for (int i = 0; i < N; i++) {
//            if(roundcount[i] == round) {
//                // candidateRoundOne = new CandidateRoundOne(firstname[i] + " " + surname[i], party[i], first_votes[i]);
//                votes = Math.max(first_votes[i], total_votes[i]);
//                candidate = new Candidate(firstname[i] + " " + surname[i], votes, id[i]);
//                if(winnersPerConstituency.containsKey(constituency[i])) {
//                    listOfCandidates = winnersPerConstituency.get(constituency[i]);
//                    listOfCandidates.add(candidate);
//                    winnersPerConstituency.put(constituency[i], listOfCandidates);
//                } else {
//                    listOfCandidates = new ArrayList<Candidate>();
//                    listOfCandidates.add(candidate);
//                    winnersPerConstituency.put(constituency[i], listOfCandidates);
//                }
//            }
//        }
//
//        for(String constit : winnersPerConstituency.keySet()) {
//            listOfCandidates = winnersPerConstituency.get(constit);
//            listOfCandidates = candidateSort(listOfCandidates);
//            winnersPerConstituency.put(constit, listOfCandidates);
//        }
//
//        winnerPerConsituencyPerRound.put(round, winnersPerConstituency);
//    }


//    public void createSTVResults(int round) {
//        HashMap<String, ArrayList<Candidate>> winnersPerConstituency = new HashMap<String, ArrayList<Candidate>>();
//
//        Candidate candidate;
//        ArrayList<Candidate> listOfCandidates;
//        int votes;
//        for (int i = 0; i < N; i++) {
//            if(roundcount[i] == round) {
//                if(result[i].equals("Elected")) {
//                    // candidateRoundOne = new CandidateRoundOne(firstname[i] + " " + surname[i], party[i], first_votes[i]);
//                    votes = Math.max(first_votes[i], total_votes[i]);
//                    candidate = new Candidate(firstname[i] + " " + surname[i], votes, id[i]);
//                    if(winnersPerConstituency.containsKey(constituency[i])) {
//                        listOfCandidates = winnersPerConstituency.get(constituency[i]);
//                        listOfCandidates.add(candidate);
//                        winnersPerConstituency.put(constituency[i], listOfCandidates);
//                    } else {
//                        listOfCandidates = new ArrayList<Candidate>();
//                        listOfCandidates.add(candidate);
//                        winnersPerConstituency.put(constituency[i], listOfCandidates);
//                    }
//                }
//            }
//        }
//
//        for(String constit : winnersPerConstituency.keySet()) {
//            listOfCandidates = winnersPerConstituency.get(constit);
//            listOfCandidates = candidateSort(listOfCandidates);
//            winnersPerConstituency.put(constit, listOfCandidates);
//        }
//
//        winnerPerConsituencyPerRound.put(round, winnersPerConstituency);
//    }

}