package com.fyodor.generators;

import com.fyodor.generators.characters.AllLettersAndNumbersFilter;
import com.fyodor.generators.characters.CharacterFilter;
import com.fyodor.generators.characters.CharacterSetGenerator;
import com.google.common.collect.Range;

import java.util.Arrays;
import java.util.Collection;

public class StringGenerator implements Generator<String> {

    private static Range<Integer> defaultRange = Range.closed(33, 126);
    private static CharacterFilter defaultFilter = AllLettersAndNumbersFilter.getFilter();
    private Integer length;
    private Character[] charSet;

    public StringGenerator(Integer length) {
        this(length, new CharacterSetGenerator(defaultRange, defaultFilter));
    }

    public StringGenerator(Integer length, CharacterFilter filter) {
        this(length, new CharacterSetGenerator(defaultRange, filter));
    }

    public StringGenerator(Integer length, CharacterSetGenerator characterSetGenerator) {
        this(length, characterSetGenerator.getCharset());
    }

    public StringGenerator(Integer length, Collection<Character> charset) {
        this(length, charset.toArray(new Character[charset.size()]));
    }

    public StringGenerator(Integer length, Character[] charset) {
        this.length = length;
        this.charSet = charset;
    }

    @Override
    public String next() {
        char[] ret = new char[length];
        for (int i = 0; i < length; i++) {
            ret[i] = charSet[RDG.random.randomInteger(charSet.length)];
        }
        return String.valueOf(ret);
    }

    public Character[] getCharSet() {
        return Arrays.copyOf(charSet, charSet.length);
    }
}