package cricketanalyser;

import com.google.gson.Gson;
import cricketanalyser.CricketAnalyser;
import cricketanalyser.model.BatsmanCSV;
import cricketanalyser.model.BowlerCSV;
import org.junit.Assert;
import org.junit.Test;

public class CricketAnalyserTest {
    private static final String BATSMAN_STATS_CSV_FILE_PATH="./src/test/resources/IPL2019FactsheetMostRuns.csv";
    private static final String BOWLER_STATS_CSV_FILE_PATH="./src/test/resources/IPL2019FactsheetMostWkts.csv";

    @Test
    public void whenGivenBatsmanStats_ShouldReturn() {
        CricketAnalyser cricketAnalyser = new CricketAnalyser();
        int count=cricketAnalyser.loadBatsmanData(BATSMAN_STATS_CSV_FILE_PATH);
        Assert.assertEquals(100,count);
    }

    @Test
    public void whenGivenBatsmanStats_ShouldReturnPlayerWithBestBattingAverage() {
        CricketAnalyser cricketAnalyser = new CricketAnalyser();
        cricketAnalyser.loadBatsmanData(BATSMAN_STATS_CSV_FILE_PATH);
        String sortedStats = cricketAnalyser.getPlayerBestBattingAverage();
        BatsmanCSV[] batsmanCSVS = new Gson().fromJson(sortedStats, BatsmanCSV[].class);
        Assert.assertEquals("MS Dhoni",batsmanCSVS[0].name);
    }

    @Test
    public void whenGivenBatsmanStats_ShouldReturnPlayerWithBestBattingSR() {
        CricketAnalyser cricketAnalyser = new CricketAnalyser();
        cricketAnalyser.loadBatsmanData(BATSMAN_STATS_CSV_FILE_PATH);
        String sortedStats = cricketAnalyser.getPlayerBestBattingSR();
        BatsmanCSV[] batsmanCSVS = new Gson().fromJson(sortedStats, BatsmanCSV[].class);
        Assert.assertEquals("Ishant Sharma",batsmanCSVS[0].name);
    }

    @Test
    public void whenGivenBatsmanStats_ShouldReturnPlayerMostBoundries() {
        CricketAnalyser cricketAnalyser = new CricketAnalyser();
        cricketAnalyser.loadBatsmanData(BATSMAN_STATS_CSV_FILE_PATH);
        String sortedStats = cricketAnalyser.getPlayerMostBoundries();
        BatsmanCSV[] batsmanCSVS = new Gson().fromJson(sortedStats, BatsmanCSV[].class);
        Assert.assertEquals("Andre Russell",batsmanCSVS[0].name);
    }

    @Test
    public void whenGivenBatsmanStats_ShouldReturnPlayerBestSR6s4s() {
        CricketAnalyser cricketAnalyser = new CricketAnalyser();
        cricketAnalyser.loadBatsmanData(BATSMAN_STATS_CSV_FILE_PATH);
        String sortedStats = cricketAnalyser.getPlayerBestSR6s4s();
        BatsmanCSV[] batsmanCSVS = new Gson().fromJson(sortedStats, BatsmanCSV[].class);
        Assert.assertEquals("Ishant Sharma",batsmanCSVS[0].name);
    }

    @Test
    public void whenGivenBatsmanStats_ShouldReturnPlayerBestAverageStrikeRate() {
        CricketAnalyser cricketAnalyser = new CricketAnalyser();
        cricketAnalyser.loadBatsmanData(BATSMAN_STATS_CSV_FILE_PATH);
        String sortedStats = cricketAnalyser.getPlayerBestAverageStrikeRate();
        BatsmanCSV[] batsmanCSVS = new Gson().fromJson(sortedStats, BatsmanCSV[].class);
        Assert.assertEquals("MS Dhoni",batsmanCSVS[0].name);
    }

    @Test
    public void whenGivenBatsmanStats_ShouldReturnPlayerMostRunsBestAverage() {
        CricketAnalyser cricketAnalyser = new CricketAnalyser();
        cricketAnalyser.loadBatsmanData(BATSMAN_STATS_CSV_FILE_PATH);
        String sortedStats = cricketAnalyser.getPlayerMostRunsBestAverage();
        BatsmanCSV[] batsmanCSVS = new Gson().fromJson(sortedStats, BatsmanCSV[].class);
        Assert.assertEquals("David Warner",batsmanCSVS[0].name);
    }


    @Test
    public void whenGivenBowlerStats_ShouldReturn() {
        CricketAnalyser cricketAnalyser = new CricketAnalyser();
        int count=cricketAnalyser.loadBowlerData(BOWLER_STATS_CSV_FILE_PATH);
        Assert.assertEquals(100,count);
    }

    @Test
    public void whenGivenBowlerStats_ShouldReturnPlayerWithBestBowlingAverage() {
        CricketAnalyser cricketAnalyser = new CricketAnalyser();
        cricketAnalyser.loadBowlerData(BOWLER_STATS_CSV_FILE_PATH);
        String sortedStats = cricketAnalyser.getPlayerBestBowlingAverage();
        BowlerCSV[] bowlerCSVS = new Gson().fromJson(sortedStats, BowlerCSV[].class);
        Assert.assertEquals("Suresh Raina",bowlerCSVS[0].name);
    }

    @Test
    public void whenGivenBowlerStats_ShouldReturnPlayerWithBestBowlingStrikeRate() {
        CricketAnalyser cricketAnalyser = new CricketAnalyser();
        cricketAnalyser.loadBowlerData(BOWLER_STATS_CSV_FILE_PATH);
        String sortedStats = cricketAnalyser.getPlayerBestBowlingStrikerate();
        BowlerCSV[] bowlerCSVS = new Gson().fromJson(sortedStats, BowlerCSV[].class);
        Assert.assertEquals("Suresh Raina",bowlerCSVS[0].name);
    }

    @Test
    public void whenGivenBowlerStats_ShouldReturnPlayerWithBestBowlingEconomy() {
        CricketAnalyser cricketAnalyser = new CricketAnalyser();
        cricketAnalyser.loadBowlerData(BOWLER_STATS_CSV_FILE_PATH);
        String sortedStats = cricketAnalyser.getPlayerBestBowlingEconomy();
        BowlerCSV[] bowlerCSVS = new Gson().fromJson(sortedStats, BowlerCSV[].class);
        Assert.assertEquals("Suresh Raina",bowlerCSVS[0].name);
    }

    @Test
    public void whenGivenBowlerStats_ShouldReturnPlayerWithBestSR5W4W() {
        CricketAnalyser cricketAnalyser = new CricketAnalyser();
        cricketAnalyser.loadBowlerData(BOWLER_STATS_CSV_FILE_PATH);
        String sortedStats = cricketAnalyser.getPlayerBestSR5W4W();
        BowlerCSV[] bowlerCSVS = new Gson().fromJson(sortedStats, BowlerCSV[].class);
        Assert.assertEquals("Suresh Raina",bowlerCSVS[0].name);
    }
}
