package io.bdrc.elasticsearch;

import org.apache.lucene.analysis.TokenStream;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.env.Environment;
import org.elasticsearch.index.IndexSettings;
import org.elasticsearch.index.analysis.AbstractTokenFilterFactory;

import io.bdrc.lucene.bo.phonetics.EnglishPhoneticFilter;

public class EnglishPhoneticTokenFilterFactory extends AbstractTokenFilterFactory {
    
    /*
     * Token filter to apply on Tibetan Unicode tokens, transforming them into a simple
     * phonetic that fits well searches from English phonetic
     * Do *not* apply after EnglishPhoneticTokenizer
     */
    
    public EnglishPhoneticTokenFilterFactory(final IndexSettings indexSettings, final Environment env, final String name, final Settings settings) {
        super(name);
    }

    @Override
    public TokenStream create(TokenStream tokenStream) {
        tokenStream = new EnglishPhoneticFilter(tokenStream);
        return tokenStream;
    }

}
