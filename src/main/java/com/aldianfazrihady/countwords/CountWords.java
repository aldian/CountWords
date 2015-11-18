package com.aldianfazrihady.countwords;

import org.apache.commons.cli.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

/**
 * Created by AldianFazrihady on 11/18/15.
 *
 * This command line app processes a List of Strings and applies the following business rules:
 * 1. Count and returns the NUMBER of words (i.e. Strings) that start with "M" or "m"
 * 2. Returns all the words longer than 5 characters
 *
 * Please read README.md before running this app.
 *
 * For developers:
 * If you need to understand this code, so you can extend it, please also check the unit testing code at CountWordsTest.java.
 */
public class CountWords {
    private static final String OPTION_FILE_PATH = "f";
    private static final Options options = new Options();

    public static void main( String[] args ) throws IOException {
        // Initialize command line options
        options.addOption(OPTION_FILE_PATH, true, "specify file containing the words");
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd;
        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            printUsage();
            return;
        }

        String text;
        // Read command line
        if (cmd.hasOption(OPTION_FILE_PATH)) {
            String fileName = cmd.getOptionValue(OPTION_FILE_PATH);
            Path path = Paths.get(fileName);
            byte[] bytes;
            try {
                bytes = Files.readAllBytes(path);
            } catch (NoSuchFileException e) {
                System.out.println("No such file: " + e.getLocalizedMessage());
                printUsage();
                return;
            }
            text = new String(bytes);
        } else {
            // No command line given. Let's check standard input
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            if (!input.ready()) {
                printUsage();
                return;
            }
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = input.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }
            text = sb.toString();
        }

        // Filtering words and delivering outputs
        WordCounter wc = new WordCounter("^[Mm]");
        String[] words = wc.filter(text);
        System.out.println("The number of words starting with \"M\" or \"m\" is " + words.length + ".");

        wc = new WordCounter(".{6,}");
        words = wc.filter(words);
        System.out.println("Words starting with \"M\" or \"m\" having length more than 5 are " + Arrays.toString(words));
    }

    private static void printUsage() {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("java -cp \"target/dependency/*:target/countwords-1.0-SNAPSHOT.jar\" " + CountWords.class.getCanonicalName(), options);
    }
}
