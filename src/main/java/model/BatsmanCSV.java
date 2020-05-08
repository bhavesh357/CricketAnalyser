package model;

import com.opencsv.bean.CsvBindByName;

public class BatsmanCSV {
    @CsvBindByName(column = "Player", required = true)
    public String name;

    @CsvBindByName(column = "Mat", required = true)
    public int matches;

    @CsvBindByName(column = "Inns", required = true)
    public int Innings;

    @CsvBindByName(column = "No", required = true)
    public int no;

    @CsvBindByName(column = "Runs", required = true)
    public int runs;

    @CsvBindByName(column = "HS", required = true)
    public String hs;

    @CsvBindByName(column = "Avg", required = true)
    public Double avg;

    @CsvBindByName(column = "BF", required = true)
    public int bf;

    @CsvBindByName(column = "SR", required = true)
    public Double sr;

    @CsvBindByName(column = "100", required = true)
    public int hundreds;

    @CsvBindByName(column = "50", required = true)
    public int fifties;

    @CsvBindByName(column = "4s", required = true)
    public int fours;

    @CsvBindByName(column = "6s", required = true)
    public int sixes;


    public BatsmanCSV() {
    }
}
