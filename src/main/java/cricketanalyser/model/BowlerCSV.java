package cricketanalyser.model;

import com.opencsv.bean.CsvBindByName;

public class BowlerCSV {
    @CsvBindByName(column = "Player", required = true)
    public String name;

    @CsvBindByName(column = "avg", required = true)
    public Double bowlingAvg;

    @CsvBindByName(column = "SR", required = true)
    public Double bowlingSR;

    @CsvBindByName(column = "Econ", required = true)
    public Double economy;

    @CsvBindByName(column = "5w", required = true)
    public int fiveWickets;

    @CsvBindByName(column = "4w", required = true)
    public int fourWickets;

    @CsvBindByName(column = "Wkts", required = true)
    public int wickets;

    public BowlerCSV(String name, Double bowlingAvg, Double bowlingSR, Double economy, int fiveWickets, int fourWickets, int wickets) {
        this.name = name;
        this.bowlingAvg = bowlingAvg;
        this.bowlingSR = bowlingSR;
        this.economy = economy;
        this.fiveWickets = fiveWickets;
        this.fourWickets = fourWickets;
        this.wickets = wickets;
    }

    public BowlerCSV() {
    }
}
