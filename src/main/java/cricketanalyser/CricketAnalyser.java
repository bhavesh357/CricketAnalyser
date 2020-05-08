package cricketanalyser;

import com.google.gson.Gson;
import cricketanalyser.exceptions.CricketAnalyserException;
import cricketanalyser.model.PlayerDAO;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CricketAnalyser {
    private PlayerAdapter.PLAYER_TYPE type;
    private Map<String, PlayerDAO> playerMap;

    public int loadBatsmanData(String csvFilePath) {
        type = PlayerAdapter.PLAYER_TYPE.BATSMAN;
        playerMap=PlayerAdapterFactory.getData(type,csvFilePath);
        return playerMap.size();
    }

    public String getPlayerBestBattingAverage() {
        checkIfNull(playerMap);
        Comparator<PlayerDAO> comparing = Comparator.comparingDouble(census -> census.battingAvg);
        ArrayList censusList= getSortedArray(comparing.reversed());
        return getJson(censusList);
    }

    public String getPlayerBestBattingSR() {
        checkIfNull(playerMap);
        Comparator<PlayerDAO> comparing = Comparator.comparingDouble(census -> census.battingSR);
        ArrayList censusList= getSortedArray(comparing.reversed());
        return getJson(censusList);
    }

    public String getPlayerMostBoundries() {
        checkIfNull(playerMap);
        Comparator<PlayerDAO> comparing = Comparator.comparingInt(census -> census.sixes);
        comparing.thenComparingInt(census -> census.fours);
        ArrayList censusList= getSortedArray(comparing.reversed());
        return getJson(censusList);
    }


    public String getPlayerBestSR6s4s() {
        checkIfNull(playerMap);
        Comparator<PlayerDAO> comparing = Comparator.comparingDouble(census -> census.battingSR);
        comparing.thenComparingInt(census -> census.sixes);
        comparing.thenComparingInt(census -> census.fours);
        ArrayList censusList= getSortedArray(comparing.reversed());
        return getJson(censusList);
    }


    public String getPlayerBestAverageStrikeRate() {
        checkIfNull(playerMap);
        Comparator<PlayerDAO> comparing = Comparator.comparingDouble(census -> census.battingAvg);
        comparing.thenComparingDouble(census -> census.battingSR);
        ArrayList censusList= getSortedArray(comparing.reversed());
        return getJson(censusList);
    }

    public String getPlayerMostRunsBestAverage() {
        checkIfNull(playerMap);
        Comparator<PlayerDAO> comparing = Comparator.comparingInt(census -> census.runsScored);
        comparing.thenComparingDouble(census -> census.battingAvg);
        ArrayList censusList= getSortedArray(comparing.reversed());
        return getJson(censusList);
    }

    private void checkIfNull(Map<String, PlayerDAO> list){
        if(list == null || list.size()==0){
            throw new CricketAnalyserException("No Stats Data",CricketAnalyserException.ExceptionType.NO_STATS_DATA);
        }
    }

    private ArrayList getSortedArray(Comparator<PlayerDAO> comparing) {
        return playerMap.values().stream().sorted(comparing).map(playerDAO -> playerDAO.getCensusDTO(type)).collect(Collectors.toCollection(ArrayList::new));
    }

    private String getJson(List list){
        String json = new Gson().toJson(list);
        return json;
    }

}
