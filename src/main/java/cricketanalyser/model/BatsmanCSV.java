package cricketanalyser.model;

import com.opencsv.bean.CsvBindByName;

public class BatsmanCSV {
    public int boundries;
    @CsvBindByName(column = "Player", required = true)
    public String name;

    @CsvBindByName(column = "Runs", required = true)
    public int runsScored;

    @CsvBindByName(column = "Avg", required = true)
    public Double battingAvg;

    @CsvBindByName(column = "SR", required = true)
    public Double battingSR;

    @CsvBindByName(column = "4s", required = true)
    public int fours;

    @CsvBindByName(column = "6s", required = true)
    public int sixes;


    public BatsmanCSV() {
    }

    public BatsmanCSV(String name, Double battingAvg, Double battingSR, int fours, int runsScored, int sixes) {
        this.name = name;
        this.runsScored = runsScored;
        this.battingAvg = battingAvg;
        this.battingSR = battingSR;
        this.fours = fours;
        this.sixes = sixes;
    }
}
