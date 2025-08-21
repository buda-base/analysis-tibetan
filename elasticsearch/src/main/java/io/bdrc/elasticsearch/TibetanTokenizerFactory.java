package io.bdrc.elasticsearch;

import org.apache.lucene.analysis.Tokenizer;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.env.Environment;
import org.elasticsearch.index.IndexSettings;
import org.elasticsearch.index.analysis.AbstractTokenizerFactory;

import io.bdrc.lucene.bo.TibSyllableTokenizer;

public class TibetanTokenizerFactory  extends AbstractTokenizerFactory {
    
    private boolean splitNonStandard = true;
    private boolean keepShad = true;

    public TibetanTokenizerFactory(final IndexSettings indexSettings, final Environment env, final String name, final Settings settings) {
        super(name);
        this.splitNonStandard = settings.getAsBoolean("split_non_standard", true);
        this.keepShad = settings.getAsBoolean("keep_shad", false);
    }

    @Override
    public Tokenizer create() {
        return new TibSyllableTokenizer(this.splitNonStandard, this.keepShad);
    }

}
