package csvBuilder;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import exceptions.CSVBuilderException;

import java.io.Reader;
import java.util.Iterator;

public class OpenCSVBuilder implements ICSVBuilder {
    @Override
    public Iterator getCSVFileIterator(Reader reader, Class playerClass) {
        try{
            CsvToBean csvToBean = getCsvToBean(reader, playerClass);
            return csvToBean.iterator();
        }catch (Exception e){
            throw new CSVBuilderException(e.getMessage(),CSVBuilderException.ExceptionType.CENSUS_HEADER_PROBLEM);
        }
    }

    private CsvToBean getCsvToBean(Reader reader, Class playerClass) {
        try{
            CsvToBean build = new CsvToBeanBuilder<>(reader)
                    .withType(playerClass)
                    .withIgnoreLeadingWhiteSpace(true)
                    .withSeparator(',')
                    .build();
            return build;
        }catch (RuntimeException e){
            throw new CSVBuilderException(e.getMessage(),CSVBuilderException.ExceptionType.CENSUS_HEADER_PROBLEM);
        }
    }
}
