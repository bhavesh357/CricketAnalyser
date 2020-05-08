package exceptions;

public class CricketAnalyserException extends RuntimeException {
    public enum ExceptionType {
        CENSUS_FILE_PROBLEM,CENSUS_TYPE_PROBLEM, CENSUS_HEADER_PROBLEM, CENSUS_DELIMITER_PROBLEM
    }
    public CricketAnalyserException(String message, Object p1) {
    }
}
