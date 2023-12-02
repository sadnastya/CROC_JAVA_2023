package ru.croc.winter.nastyasad;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatisticalModule {
    private final Connection conn;
    private ArrayList<Integer> votes = new ArrayList<>();
    private Map<Integer, Double> goodProbability = new HashMap<>();
    public StatisticalModule(Connection conn) {
        this.conn = conn;
    }

    public void readResultOfChecking() {
        String selectNotNull = "SELECT idLot FROM Lot WHERE (ResultOfChecking=true)";
        try (Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
            ResultSet goodLotsID = st.executeQuery(selectNotNull);
            if (!goodLotsID.next()) {
                String makeTrue = "UPDATE Lot SET ResultOfChecking=true";
                st.executeUpdate(makeTrue);

            } else {
                goodLotsID.beforeFirst();
                String selectIDCandidates = "SELECT idCandidate FROM Vote WHERE idLot=?";
                try (PreparedStatement state = conn.prepareStatement(selectIDCandidates)) {
                    while(goodLotsID.next()){
                        int lot = goodLotsID.getInt(1);
                        state.setInt(1, lot);
                        ResultSet allCand = state.executeQuery();
                        while (allCand.next()){
                            votes.add(allCand.getInt(1));
                        }
                    }

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println(votes);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    private ArrayList<Integer> createData(List<Vote> currentVotes){
        ArrayList<Integer> votes = new ArrayList<>();
        for(Vote vote: currentVotes){
            votes.add(vote.getCandidateId());
        }
        return votes;
    }
    private ArrayList<Integer> createListOfCandidates(){
        String selectCandidates = "SELECT idCandidate FROM Candidate";
        ArrayList<Integer> listOfCandidates= new ArrayList<Integer>();
        try(Statement st = conn.createStatement()){
            ResultSet cand = st.executeQuery(selectCandidates);
            while (cand.next()){
                listOfCandidates.add(cand.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listOfCandidates;
    }

    public void findProbabilityBD(){
        ArrayList<Integer> candidates = createListOfCandidates();
        for(Integer i: candidates){
            double number = 0;
            for(int j = 0; j < votes.size(); j++) {
                if(votes.get(j)==i){
                    number+=1;
                }
            }
            double probability = number/votes.size();
            goodProbability.put(i, probability);
        }

        System.out.println(goodProbability);
    }

    public boolean isBallotStuffing(List<Vote> votes){
        ArrayList<Integer> newVotes = createData(votes);
        ArrayList<Integer> candidates = createListOfCandidates();
        Map<Integer, Double> currentProbability = new HashMap<>();
        for(Integer i: candidates){
            double number = 0;
            for(int j = 0; j < newVotes.size(); j++) {
                if(newVotes.get(j)==i){
                    number+=1;
                }
            }
            double probability = number/newVotes.size();
            currentProbability.put(i, probability);
        }
        return compareAntFillResult(currentProbability);
    }
    public boolean compareAntFillResult(Map<Integer, Double> currentProbability){
        int goodCandidates=0;
        for(Integer i: currentProbability.keySet()){
            if((currentProbability.get(i)<goodProbability.get(i)+0.4) && (currentProbability.get(i)>goodProbability.get(i)-0.4)){
                goodCandidates+=1;
            }
            else {
                break;
            }
        }
        if (goodCandidates==currentProbability.keySet().size()){
            try (Statement st = conn.createStatement()) {
                String makeTrue = "UPDATE Lot SET ResultOfChecking=true WHERE ResultOfChecking IS NULL";
                st.executeUpdate(makeTrue);
                return true;

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            try (Statement st = conn.createStatement()) {
                String makeTrue = "UPDATE Lot SET ResultOfChecking=false WHERE ResultOfChecking IS NULL";
                st.executeUpdate(makeTrue);
                return false;

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
