package io.bdrc.elasticsearch;

import org.apache.lucene.analysis.TokenStream;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.env.Environment;
import org.elasticsearch.index.IndexSettings;
import org.elasticsearch.index.analysis.AbstractTokenFilterFactory;

import io.bdrc.lucene.bo.phonetics.StandardTibetanPhoneticFilter;

public class StandardTibetanPhoneticTokenFilterFactory extends AbstractTokenFilterFactory {
    
    /*
     * Token filter to apply on Tibetan Unicode tokens, transforming them into a simple
     * phonetic that fits well searches using the same token filter (so not searches from English phonetic)
     */
    
    public StandardTibetanPhoneticTokenFilterFactory(final IndexSettings indexSettings, final Environment env, final String name, final Settings settings) {
        super(name);
    }

    @Override
    public TokenStream create(TokenStream tokenStream) {
        tokenStream = new StandardTibetanPhoneticFilter(tokenStream);
        return tokenStream;
    }

}
