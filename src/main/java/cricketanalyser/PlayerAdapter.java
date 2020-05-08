package cricketanalyser;

import cricketanalyser.csvBuilder.CSVBuilderFactory;
import cricketanalyser.csvBuilder.ICSVBuilder;
import cricketanalyser.exceptions.CSVBuilderException;
import cricketanalyser.exceptions.CricketAnalyserException;
import cricketanalyser.model.BatsmanCSV;
import cricketanalyser.model.BowlerCSV;
import cricketanalyser.model.PlayerDAO;

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
                streamPlayer.forEach(playerCSV -> censusCSVMap.put(playerCSV.name,new PlayerDAO(playerCSV)));
            }else if(type.equals(PLAYER_TYPE.BOWLER)){
                Stream<BowlerCSV> streamIndia = stream.map(BowlerCSV.class::cast);
                streamIndia.forEach(playerCSV -> censusCSVMap.put(playerCSV.name,new PlayerDAO(playerCSV)));
            }
            return censusCSVMap;
        } catch (IOException e) {
            throw new CricketAnalyserException(e.getMessage(),
                    CricketAnalyserException.ExceptionType.STATS_FILE_PROBLEM);
        } catch (CSVBuilderException e) {
            throw new CricketAnalyserException(e.getMessage(),e.type.name());
        }
    }

    public enum PLAYER_TYPE {
        BATSMAN,BOWLER
    }
}
