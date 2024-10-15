package io.bdrc.opensearch;

import org.apache.lucene.analysis.TokenStream;
import org.opensearch.common.settings.Settings;
import org.opensearch.env.Environment;
import org.opensearch.index.IndexSettings;
import org.opensearch.index.analysis.AbstractTokenFilterFactory;

import io.bdrc.lucene.bo.phonetics.EnglishPhoneticFilter;

public class EnglishPhoneticTokenFilterFactory extends AbstractTokenFilterFactory {
    
    /*
     * Token filter to apply on Tibetan Unicode tokens, transforming them into a simple
     * phonetic that fits well searches from English phonetic
     * Do *not* apply after EnglishPhoneticTokenizer
     */
    
    public EnglishPhoneticTokenFilterFactory(final IndexSettings indexSettings, final Environment env, final String name, final Settings settings) {
        super(indexSettings, name, settings);
    }

    @Override
    public TokenStream create(TokenStream tokenStream) {
        tokenStream = new EnglishPhoneticFilter(tokenStream);
        return tokenStream;
    }

}
