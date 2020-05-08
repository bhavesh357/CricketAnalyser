
import csvBuilder.CSVBuilderFactory;
import csvBuilder.ICSVBuilder;
import exceptions.CSVBuilderException;
import exceptions.CricketAnalyserException;
import model.BatsmanCSV;
import model.BowlerCSV;
import model.PlayerDAO;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class PlayerAdapter {
    protected <E> Map<String, PlayerDAO> loadData(Class playerClass,PLAYER_TYPE type, String csvFilePath) {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<E> csvFileIterator = csvBuilder.getCSVFileIterator(reader, playerClass);
            Iterable<E> csvStatesIterable =() ->csvFileIterator;
            Stream<E> stream = StreamSupport.stream(csvStatesIterable.spliterator(), false);
            Map<String, PlayerDAO> censusCSVMap = new HashMap<String, PlayerDAO>();
            if (type.equals(PLAYER_TYPE.BATSMAN)) {
                Stream<BatsmanCSV> streamPlayer = stream.map(BatsmanCSV.class::cast);
                streamPlayer.forEach(censusCSV -> censusCSVMap.put(censusCSV.name,new PlayerDAO(censusCSV)));
            }else if(type.equals(PLAYER_TYPE.BOWLER)){
                Stream<BowlerCSV> streamIndia = stream.map(BowlerCSV.class::cast);
                streamIndia.forEach(censusCSV -> censusCSVMap.put(censusCSV.name,new PlayerDAO(censusCSV)));
            }
            return censusCSVMap;
        } catch (IOException e) {
            throw new CricketAnalyserException(e.getMessage(),
                    CricketAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (CSVBuilderException e) {
            throw new CricketAnalyserException(e.getMessage(),e.type.name());
        }
    }

    public enum PLAYER_TYPE {
        BATSMAN,BOWLER
    }
}
