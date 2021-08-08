package com.bridgelabz;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class StateCensusAnalyser {

    public String csvPath;

    public StateCensusAnalyser() {
    }
    /**
     * This boolean method to load india census  data.
     *@return boolean value.
     */
    public static int LoadIndiaCensusData(String csvPath) throws CensusAnalyserException {
        Reader reader;
        try {
            reader = Files.newBufferedReader(Paths.get(csvPath));

            @SuppressWarnings({"unchecked", "rawtypes"})

            CsvToBean<IndianCensusCSV> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(IndianCensusCSV.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            Iterator<IndianCensusCSV> censusCSVIterator = csvToBean.iterator();
            int entries = 0;
            while (censusCSVIterator.hasNext()) {
                entries++;
                IndianCensusCSV censusData = censusCSVIterator.next();
            }
            return entries;
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.Csv_File_Problem);
        } catch (IllegalStateException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.Unable_To_Parse);
        }
    }

    /**
     * This boolean method to load india census csv data.
     *@return boolean value.
     */
    @SuppressWarnings("deprecation")
    public boolean LoadIndiaCensusCSVData(String csvPath) throws CensusAnalyserException, IOException {
        // read the CSV file as a list of String array.
        CSVReader reader;
        try {
            reader = new CSVReader(new FileReader(csvPath), '|');
            @SuppressWarnings("unused")
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                return true;
            }
            if (reader != null) {
                reader.close();
            }
        } catch (IllegalStateException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.Unable_To_Parse);
        }
        return false;
    }
}
