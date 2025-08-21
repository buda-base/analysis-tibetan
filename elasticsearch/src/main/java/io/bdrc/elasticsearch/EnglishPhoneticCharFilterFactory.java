package io.bdrc.elasticsearch;

import java.io.Reader;

import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.env.Environment;
import org.elasticsearch.index.IndexSettings;
import org.elasticsearch.index.analysis.AbstractCharFilterFactory;
import org.elasticsearch.index.analysis.CharFilterFactory;

import io.bdrc.lucene.bo.phonetics.EnglishPhoneticCharMapFilter;
import io.bdrc.lucene.bo.phonetics.EnglishPhoneticRegexFilter;
import io.bdrc.lucene.bo.phonetics.LowerCaseCharFilter;


public class EnglishPhoneticCharFilterFactory extends AbstractCharFilterFactory implements CharFilterFactory {

    public EnglishPhoneticCharFilterFactory(final IndexSettings indexSettings, final Environment env, final String name, final Settings settings) {
        super(name);
    }


    @Override
    public Reader create(Reader tokenStream) {
        tokenStream = new LowerCaseCharFilter(tokenStream);
        tokenStream = new EnglishPhoneticCharMapFilter(tokenStream);
        tokenStream = EnglishPhoneticRegexFilter.plugFilters(tokenStream);
        return tokenStream;
    }
    
}
