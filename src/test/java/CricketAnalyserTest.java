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
}
