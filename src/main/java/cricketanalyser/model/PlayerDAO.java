package cricketanalyser.model;

import cricketanalyser.PlayerAdapter;

public class PlayerDAO {
    public String name;
    public Double bowlingAvg;
    public Double bowlingSR;
    public Double economy;
    public int fiveWickets;
    public int fourWickets;
    public int wickets;
    public Double battingAvg;
    public Double battingSR;
    public int fours;
    public int sixes;
    public int runsScored;
    public int boundries;

    public PlayerDAO(BowlerCSV playerCSV) {
        name = playerCSV.name;
        bowlingAvg=playerCSV.bowlingAvg;
        bowlingSR = playerCSV.bowlingSR;
        economy = playerCSV.economy;
        fiveWickets=playerCSV.fiveWickets;
        fourWickets=playerCSV.fourWickets;
        wickets=playerCSV.wickets;
    }

    public PlayerDAO(BatsmanCSV playerCSV) {
        name=playerCSV.name;
        battingAvg=playerCSV.battingAvg;
        battingSR=playerCSV.battingSR;
        fours=playerCSV.fours;
        runsScored=playerCSV.runsScored;
        sixes=playerCSV.sixes;
        boundries=fours+sixes;
    }

    public Object getCensusDTO(PlayerAdapter.PLAYER_TYPE type) {
        if(type.equals(PlayerAdapter.PLAYER_TYPE.BATSMAN)){
            return new BatsmanCSV(name,battingAvg,battingSR,fours,runsScored,sixes);
        }else{
            return new BowlerCSV(name,bowlingAvg,bowlingSR,economy,fiveWickets,fourWickets,wickets);
        }
    }
}
