package io.bdrc.elasticsearch;

import org.apache.lucene.analysis.Tokenizer;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.env.Environment;
import org.elasticsearch.index.IndexSettings;
import org.elasticsearch.index.analysis.AbstractTokenizerFactory;

import io.bdrc.lucene.bo.phonetics.EnglishPhoneticTokenizer;

public class EnglishPhoneticTokenizerFactory extends AbstractTokenizerFactory {

    public EnglishPhoneticTokenizerFactory(final IndexSettings indexSettings, final Environment env, final String name, final Settings settings) {
        super(name);
    }

    @Override
    public Tokenizer create() {
        return new EnglishPhoneticTokenizer();
    }
    
}
