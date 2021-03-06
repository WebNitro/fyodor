package uk.org.fyodor.generators.characters;

import uk.org.fyodor.range.Range;

import java.util.Arrays;

public class CharacterSetGenerator {

    private char[] charset = null;

    public CharacterSetGenerator() {
        this(CharacterSetFilter.AllExceptDoubleQuotes, CharacterSetRange.defaultLatinBasic);
    }

    @SafeVarargs
    public CharacterSetGenerator(Range<Integer>... ranges) {
        this(CharacterSetFilter.AllExceptDoubleQuotes, ranges);
    }

    public CharacterSetGenerator(CharacterSetRange... ranges) {
        this(CharacterSetFilter.AllExceptDoubleQuotes, ranges);
    }

    public CharacterSetGenerator(CharacterFilter filter) {
        this(filter, CharacterSetRange.defaultLatinBasic.getRange());
    }

    public CharacterSetGenerator(CharacterSetFilter filter, CharacterSetRange... characterSetRanges) {
        this(filter.getFilter(), characterSetRanges);
    }

    @SafeVarargs
    public CharacterSetGenerator(CharacterSetFilter filter, Range<Integer>... ranges) {
        this(filter.getFilter(), ranges);
    }

    public CharacterSetGenerator(CharacterFilter filter, CharacterSetRange... characterSetRanges) {
        Range<Integer>[] ranges = new Range[characterSetRanges.length];
        for (int i = 0; i < characterSetRanges.length; i++) {
            ranges[i] = characterSetRanges[i].getRange();
        }
        generateCharacterSet(filter, ranges);
    }

    @SafeVarargs
    public CharacterSetGenerator(CharacterFilter filter, Range<Integer>... ranges) {

        generateCharacterSet(filter, ranges);
    }

    @SafeVarargs
    private final void generateCharacterSet(CharacterFilter filter, Range<Integer>... ranges) {
        char[] charset = new char[getTotalSizeOfRanges(ranges)];

        int j = 0;
        for (Range<Integer> range : ranges) {
            for (int i = range.lowerBound(); i <= range.upperBound(); i++) {
                char c = (char) i;
                if (filter.includeCharacter(c)) {
                    charset[j] = c;
                    j++;
                }
            }
        }
        this.charset = Arrays.copyOf(charset, j);
    }

    private int getTotalSizeOfRanges(Range<Integer>[] ranges) {
        int rangesSize = 0;
        for (Range<Integer> range : ranges) {
            rangesSize += range.upperBound() - range.lowerBound() + 1;
        }
        return rangesSize;
    }

    public char[] getCharset() {
        return Arrays.copyOf(charset, charset.length);
    }
}
