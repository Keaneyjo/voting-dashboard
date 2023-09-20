public class CandidateRoundOne {

    public String name;
    public String party;
    public Integer votes;
    public Integer id;
    public String result;

    public CandidateRoundOne(String name, Integer votes, Integer id) {
        this.name = name;
        this.votes = votes;
        this.id = id;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setParty(String party) {
        this.party = party;
    }
}
