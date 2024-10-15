package io.bdrc.opensearch;

import java.io.Reader;

import org.opensearch.common.settings.Settings;
import org.opensearch.env.Environment;
import org.opensearch.index.IndexSettings;
import org.opensearch.index.analysis.AbstractCharFilterFactory;
import org.opensearch.index.analysis.CharFilterFactory;

import io.bdrc.lucene.bo.phonetics.EnglishPhoneticCharMapFilter;
import io.bdrc.lucene.bo.phonetics.EnglishPhoneticRegexFilter;
import io.bdrc.lucene.bo.phonetics.LowerCaseCharFilter;


public class EnglishPhoneticCharFilterFactory extends AbstractCharFilterFactory implements CharFilterFactory {

    public EnglishPhoneticCharFilterFactory(final IndexSettings indexSettings, final Environment env, final String name, final Settings settings) {
        super(indexSettings, name);
    }


    @Override
    public Reader create(Reader tokenStream) {
        tokenStream = new LowerCaseCharFilter(tokenStream);
        tokenStream = new EnglishPhoneticCharMapFilter(tokenStream);
        tokenStream = EnglishPhoneticRegexFilter.plugFilters(tokenStream);
        return tokenStream;
    }
    
}
