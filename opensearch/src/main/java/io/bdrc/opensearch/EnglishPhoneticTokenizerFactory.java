package io.bdrc.opensearch;

import org.apache.lucene.analysis.Tokenizer;
import org.opensearch.common.settings.Settings;
import org.opensearch.env.Environment;
import org.opensearch.index.IndexSettings;
import org.opensearch.index.analysis.AbstractTokenizerFactory;

import io.bdrc.lucene.bo.phonetics.EnglishPhoneticTokenizer;

public class EnglishPhoneticTokenizerFactory extends AbstractTokenizerFactory {

    public EnglishPhoneticTokenizerFactory(final IndexSettings indexSettings, final Environment env, final String name, final Settings settings) {
        super(indexSettings, settings, name);
    }

    @Override
    public Tokenizer create() {
        return new EnglishPhoneticTokenizer();
    }
    
}
