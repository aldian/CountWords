package com.aldianfazrihady.countwords;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class CountWordsTest
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public CountWordsTest(String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( CountWordsTest.class );
    }

    /**
     * Filter text twice.
     * First find words start with "M" or "m".
     * From that first filtered list, filter it again so it contains only words with length more than 5
     */
    public void testWordCounterFilter() {
        String text = "Aku ingin menyadari makan Malam\nmalam Minggu ini";
        WordCounter wc = new WordCounter("^[Mm]");
        String[] words = wc.filter(text);
        assertEquals(words.length, 5);
        assertEquals(words[0], "menyadari");
        assertEquals(words[1], "makan");
        assertEquals(words[2], "Malam");
        assertEquals(words[3], "malam");
        assertEquals(words[4], "Minggu");

        wc = new WordCounter(".{6,}");
        words = wc.filter(words);
        assertEquals(words.length, 2);
        assertEquals(words[0], "menyadari");
        assertEquals(words[1], "Minggu");
    }
}
