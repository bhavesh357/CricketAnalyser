package cricketanalyser;

import com.google.gson.Gson;
import cricketanalyser.exceptions.CricketAnalyserException;
import cricketanalyser.model.BowlerCSV;
import cricketanalyser.model.PlayerDAO;

import java.lang.reflect.Field;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CricketAnalyser {
    private PlayerAdapter.PLAYER_TYPE type;
    private Map<String, PlayerDAO> playersMap;

    public int loadBothData(String batsmanStatsCsvFilePath, String bowlerStatsCsvFilePath) {
        loadBatsmanData(batsmanStatsCsvFilePath);
        Map<String, PlayerDAO> playerMapBatsman = this.playersMap;
        loadBowlerData(bowlerStatsCsvFilePath);
        mergeMaps(playerMapBatsman);
        return this.playersMap.size();
    }

    private void mergeMaps(Map<String, PlayerDAO> playerMapBatsman) {
        Map<String,PlayerDAO> allRounders = new HashMap<>();
        for(String name : playersMap.keySet()){
            if(playerMapBatsman.get(name)!=null){
                PlayerDAO playerDAO = playersMap.get(name);
                playerDAO.battingAvg = playerMapBatsman.get(name).battingAvg;
                playerDAO.battingSR = playerMapBatsman.get(name).battingSR;
                playerDAO.sixes = playerMapBatsman.get(name).sixes;
                playerDAO.fours = playerMapBatsman.get(name).fours;
                playerDAO.boundries = playerMapBatsman.get(name).boundries;
                playerDAO.runsScored = playerMapBatsman.get(name).runsScored;
                allRounders.put(name,playerDAO);
            }
        }
        playersMap=allRounders;
    }

    public int loadBatsmanData(String csvFilePath) {
        type = PlayerAdapter.PLAYER_TYPE.BATSMAN;
        playersMap=PlayerAdapterFactory.getData(type,csvFilePath);
        return playersMap.size();
    }

    public int loadBowlerData(String csvFilePath) {
        type = PlayerAdapter.PLAYER_TYPE.BOWLER;
        playersMap=PlayerAdapterFactory.getData(type,csvFilePath);
        return playersMap.size();
    }

    public String getPlayerBestBattingAverage() {
        checkIfNull(playersMap);
        Comparator<PlayerDAO> comparing = Comparator.comparingDouble(census -> census.battingAvg);
        ArrayList censusList= getSortedArray(comparing.reversed());
        return getJson(censusList);
    }

    public String getPlayerBestBattingSR() {
        checkIfNull(playersMap);
        Comparator<PlayerDAO> comparing = Comparator.comparingDouble(census -> census.battingSR);
        ArrayList censusList= getSortedArray(comparing.reversed());
        return getJson(censusList);
    }

    public String getPlayerMostBoundries() {
        checkIfNull(playersMap);
        Comparator<PlayerDAO> comparing = Comparator.comparingInt(census -> census.sixes);
        comparing = comparing.thenComparingInt(census -> census.fours);
        ArrayList censusList= getSortedArray(comparing.reversed());
        return getJson(censusList);
    }


    public String getPlayerBestSR6s4s() {
        checkIfNull(playersMap);
        Comparator<PlayerDAO> comparing = Comparator.comparingInt(census -> census.sixes);
        comparing = comparing.thenComparingInt(census -> census.fours);
        comparing = comparing.thenComparingDouble(census -> census.battingSR);
        ArrayList censusList= getSortedArray(comparing.reversed());
        return getJson(censusList);
    }


    public String getPlayerBestAverageStrikeRate() {
        checkIfNull(playersMap);
        Comparator<PlayerDAO> comparing = Comparator.comparingDouble(census -> census.battingAvg);
        comparing = comparing.thenComparingDouble(census -> census.battingSR);
        ArrayList censusList= getSortedArray(comparing.reversed());
        return getJson(censusList);
    }

    public String getPlayerMostRunsBestAverage() {
        checkIfNull(playersMap);
        Comparator<PlayerDAO> comparing = Comparator.comparingInt(census -> census.runsScored);
        comparing = comparing.thenComparingDouble(census -> census.battingAvg);
        ArrayList censusList= getSortedArray(comparing.reversed());
        return getJson(censusList);
    }

    public String getPlayerBestBowlingAverage() {
        checkIfNull(playersMap);
        Comparator<PlayerDAO> comparing = Comparator.comparingDouble(census -> census.bowlingAvg);
        Predicate<PlayerDAO> filter = PlayerDAO.isAverageZero();
        ArrayList censusList= getSortedArray(comparing,filter);
        return getJson(censusList);
    }


    public String getPlayerBestBowlingStrikerate() {
        checkIfNull(playersMap);
        Comparator<PlayerDAO> comparing = Comparator.comparingDouble(census -> census.bowlingSR);
        Predicate<PlayerDAO> filter = PlayerDAO.isbowlingSRZero();
        ArrayList censusList= getSortedArray(comparing,filter);
        return getJson(censusList);
    }


    public String getPlayerBestBowlingEconomy() {
        checkIfNull(playersMap);
        Comparator<PlayerDAO> comparing = Comparator.comparingDouble(census -> census.economy);
        Predicate<PlayerDAO> filter = PlayerDAO.isEconomyZero();
        ArrayList censusList= getSortedArray(comparing,filter);
        return getJson(censusList);
    }


    public String getPlayerBestSR5W4W() {
        checkIfNull(playersMap);
        Comparator<PlayerDAO> comparing = Comparator.comparingInt(census -> census.fiveWickets);
        comparing = comparing.thenComparingInt(census -> census.fourWickets);
        comparing = comparing.reversed();
        comparing = comparing.thenComparingDouble(census -> census.bowlingSR);
        Predicate<PlayerDAO> filter = PlayerDAO.isbowlingSRZero();
        ArrayList censusList= getSortedArray(comparing,filter);
        return getJson(censusList);
    }

    public String getPlayerBestAverageSR() {
        checkIfNull(playersMap);
        Comparator<PlayerDAO> comparing = Comparator.comparingDouble(census -> census.bowlingAvg);
        comparing.thenComparingDouble(census -> census.bowlingSR);
        Predicate<PlayerDAO> filter = PlayerDAO.isAverageZero();
        ArrayList censusList= getSortedArray(comparing,filter);
        return getJson(censusList);
    }

    public String getPlayerMostWicketsBestAverage() {
        checkIfNull(playersMap);
        Comparator<PlayerDAO> comparing = Comparator.comparingInt(census -> census.wickets);
        comparing = comparing.reversed();
        comparing = comparing.thenComparingDouble(census -> census.bowlingAvg);
        Predicate<PlayerDAO> filter = PlayerDAO.isAverageZero();
        ArrayList censusList= getSortedArray(comparing,filter);
        return getJson(censusList);
    }

    public String getPlayerBestBattingAndBowlingAverages() {
        checkIfNull(playersMap);
        Comparator<PlayerDAO> comparing = Comparator.comparingDouble(census -> census.battingAvg);
        comparing = comparing.reversed();
        comparing = comparing.thenComparingDouble(census -> census.bowlingAvg);
        ArrayList censusList= getSortedArray(comparing);
        return getJson(censusList);
    }

    public String getPlayerMostRunsAndWickets() {
        checkIfNull(playersMap);
        Comparator<PlayerDAO> comparing = Comparator.comparingInt(census -> census.runsScored);
        comparing.thenComparingInt(census -> census.wickets);
        ArrayList censusList= getSortedArray(comparing.reversed());
        return getJson(censusList);
    }

    private void checkIfNull(Map<String, PlayerDAO> list){
        if(list == null || list.size()==0){
            throw new CricketAnalyserException(CricketAnalyserException.message,CricketAnalyserException.ExceptionType.NO_STATS_DATA);
        }
    }

    private ArrayList getSortedArray(Comparator<PlayerDAO> comparing) {
        return playersMap.values().stream().sorted(comparing).map(playerDAO -> playerDAO.getCensusDTO(type)).collect(Collectors.toCollection(ArrayList::new));
    }

    private ArrayList getSortedArray(Comparator<PlayerDAO> comparing, Predicate<PlayerDAO> filter) {
        return playersMap.values().stream().filter(filter).sorted(comparing).map(playerDAO -> playerDAO.getCensusDTO(type)).collect(Collectors.toCollection(ArrayList::new));
    }

    private String getJson(List list){
        String json = new Gson().toJson(list);
        return json;
    }

}
