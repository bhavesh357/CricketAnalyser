package model;

import com.opencsv.bean.CsvBindByName;

public class BowlerCSV {
    @CsvBindByName(column = "Player", required = true)
    public String name;


    public BowlerCSV() {
    }
}
