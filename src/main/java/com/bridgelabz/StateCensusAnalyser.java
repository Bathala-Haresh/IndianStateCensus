package com.bridgelabz;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class StateCensusAnalyser {

    public static int LoadIndiaCensusData(String csvPath)   {

        Reader reader = null;
        try {
            reader = Files.newBufferedReader(Paths.get(csvPath));
        } catch (IOException e) {

            e.printStackTrace();
        }

        @SuppressWarnings({ "unchecked", "rawtypes" })
        CsvToBean <IndianCensusCSV> csvToBean = new CsvToBeanBuilder(reader)
                .withType(IndianCensusCSV.class)
                .withIgnoreLeadingWhiteSpace(true)
                .build();
        Iterator<IndianCensusCSV> censusCSVIterator = csvToBean.iterator();;
        int entries = 0;
        while(censusCSVIterator.hasNext()) {
            entries++;
            IndianCensusCSV censusData = censusCSVIterator.next();
        }
        System.out.println("Number of Entries in File "+entries);
        return entries;

    }

    public static void main(String[] args)  {

        String csvPath = "O:\\IntellijProjects\\IndianStateCenesusData\\Data\\IndiaStateCensusData.csv";
        LoadIndiaCensusData(csvPath);

    }
}