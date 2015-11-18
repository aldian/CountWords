package com.aldianfazrihady.countwords;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by AldianFazrihady on 11/18/15.
 * Counting words based on given criteria
 * The user of this class must know Java Regular expression as described at
 * http://docs.oracle.com/javase/7/docs/api/java/util/regex/Pattern.html
 */
public class WordCounter {
    private final Pattern pattern;
    /**
     * Construct a WordCounter object
     * @param filterRegex When a word matches this filter, it gets counted
     */
    public WordCounter(String filterRegex) {
        this.pattern = Pattern.compile(filterRegex);
    }

    /**
     * Return array of words from the specified text, given the criteria
     * specified when creating this object
     * @param text The string containing words
     * @return words matching the filter
     */
    public String[] filter(String text) {
        String[] basicWords = text.split("\\s+");
        return filter(basicWords);
    }

    /**
     * Return array of words from the specified array of words, given the criteria
     * specified when creating this object
     * @param basicWords words to be filtered
     * @return words matching the filter
     */
    public String[] filter(String[] basicWords) {
        List<String> filteredWords = new ArrayList<String>();
        for (String word: basicWords) {
            Matcher matcher = pattern.matcher(word);
            if (matcher.find()) {
                filteredWords.add(word);
            }
        }
        return filteredWords.toArray(new String[1]);
    }
}
