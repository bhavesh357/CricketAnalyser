package cricketanalyser.exceptions;

public class CricketAnalyserException extends RuntimeException {
    public static String message="No Stats Data";
    public enum ExceptionType {
        CRICKET_FILE_PROBLEM, NO_STATS_DATA, STATS_FILE_PROBLEM;
    }
    public CricketAnalyserException(String message, Object p1) {
    }
}
