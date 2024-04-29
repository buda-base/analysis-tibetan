package io.bdrc.opensearch;

import java.io.Reader;

import org.apache.lucene.analysis.charfilter.MappingCharFilter;
import org.apache.lucene.analysis.charfilter.NormalizeCharMap;

public class SimpleDiacriticsFilter extends MappingCharFilter {
    
    /*
     * Simple diacritics filter for use in mixed fields (ex: English with some Tibetan)
     */

    private static final NormalizeCharMap map = getCharMap();
    
    public SimpleDiacriticsFilter(Reader in) {
        super(map, in);
    }

    private final static NormalizeCharMap getCharMap() {
        final NormalizeCharMap.Builder builder = new NormalizeCharMap.Builder();
        builder.add("ś", "sh");
        builder.add("s\u0301", "sh");
        builder.add("Ś", "sh");
        builder.add("S\u0301", "sh");
        builder.add("Ṣ", "sh");
        builder.add("S\u0323", "sh");
        builder.add("ṣ", "sh");
        builder.add("s\u0323", "sh");
        builder.add("ź", "zh");
        builder.add("z\u0301", "zh");
        builder.add("Ź", "zh");
        builder.add("Z\u0301", "zh");
        builder.add("š", "sh");
        builder.add("s\u030C", "sh");
        builder.add("Š", "sh");
        builder.add("S\u030C", "sh");
        builder.add("ž", "zh");
        builder.add("z\u030C", "zh");
        builder.add("Ž", "zh");
        builder.add("Z\u030C", "zh");
        builder.add("ḥ", "");
        builder.add("ṅ", "ng");
        builder.add("n\u0307", "ng");
        builder.add("Ṅ", "ng");
        builder.add("N\u0307", "ng");
        builder.add("ñ", "ny");
        builder.add("n\u0303", "ny");
        builder.add("Ñ", "ny");
        builder.add("N\u0303", "ny");
        builder.add("\u0301", "");
        builder.add("\u0303", "");
        builder.add("\u0307", "");
        builder.add("\u030C", "");
        builder.add("\u0323", "");
        builder.add("\u0304", "");
        builder.add("\u0325", "");
        builder.add("\u0901", "");
        return builder.build();
    }

}

