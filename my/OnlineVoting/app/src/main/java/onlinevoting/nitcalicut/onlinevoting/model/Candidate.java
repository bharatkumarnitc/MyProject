package onlinevoting.nitcalicut.onlinevoting.model;

public class Candidate {
    String name;
    int vote_count,candidate_id;

    public Candidate(){
        candidate_id=0;
        name="";
        vote_count=0;
    }

    public Candidate(int candidate_id,String name,int vote_count){
        this.candidate_id = candidate_id;
        this.name = name;
        this.vote_count = vote_count;
    }

    public int getCandidate_id() {
        return candidate_id;
    }

    public void setCandidate_id(int candidate_id) {
        this.candidate_id = candidate_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVote_count() {
        return vote_count;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }
}
