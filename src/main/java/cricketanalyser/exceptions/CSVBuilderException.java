package cricketanalyser.exceptions;

public class CSVBuilderException extends RuntimeException {
    public enum ExceptionType {
        CENSUS_FILE_PROBLEM,CENSUS_TYPE_PROBLEM, CENSUS_HEADER_PROBLEM, CENSUS_DELIMITER_PROBLEM
    }

    public CSVBuilderException.ExceptionType type;

    public CSVBuilderException(String message, CSVBuilderException.ExceptionType type) {
        super(message);
        this.type = type;
    }
}
