package io.bdrc.opensearch;

import org.apache.lucene.analysis.Tokenizer;
import org.opensearch.common.settings.Settings;
import org.opensearch.env.Environment;
import org.opensearch.index.IndexSettings;
import org.opensearch.index.analysis.AbstractTokenizerFactory;

import io.bdrc.lucene.bo.TibSyllableTokenizer;

public class TibetanTokenizerFactory  extends AbstractTokenizerFactory {
    
    private boolean splitNonStandard = false;

    public TibetanTokenizerFactory(final IndexSettings indexSettings, final Environment env, final String name, final Settings settings) {
        super(indexSettings, settings, name);
        this.splitNonStandard = settings.getAsBoolean("remove_affixes", false);
    }

    @Override
    public Tokenizer create() {
        return new TibSyllableTokenizer(this.splitNonStandard);
    }

}
