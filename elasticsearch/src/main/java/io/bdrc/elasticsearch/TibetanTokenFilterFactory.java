package io.bdrc.elasticsearch;

import org.apache.lucene.analysis.TokenStream;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.env.Environment;
import org.elasticsearch.index.IndexSettings;
import org.elasticsearch.index.analysis.AbstractTokenFilterFactory;

import io.bdrc.lucene.bo.PaBaFilter;
import io.bdrc.lucene.bo.TibAffixedFilter;
import io.bdrc.lucene.bo.TibSyllableLemmatizer;

public class TibetanTokenFilterFactory extends AbstractTokenFilterFactory {

    private boolean remove_affixes = false;
    private boolean normalize_paba = false;
    private boolean normalize_verbs = false;

    public TibetanTokenFilterFactory(final IndexSettings indexSettings, final Environment env, final String name,
            final Settings settings) {
        // indexSettings gives error when included in the super constructor
        super(name, settings);
        this.remove_affixes = settings.getAsBoolean("remove_affixes", false);
        this.normalize_paba = settings.getAsBoolean("normalize_paba", false);
        this.normalize_verbs = settings.getAsBoolean("normalize_verbs", false);
    }

    @Override
    public TokenStream create(TokenStream tokenStream) {
        if (this.remove_affixes)
            tokenStream = new TibAffixedFilter(tokenStream, true);
        if (this.normalize_verbs)
            tokenStream = new TibSyllableLemmatizer(tokenStream);
        if (this.normalize_paba)
            tokenStream = new PaBaFilter(tokenStream);
        return tokenStream;
    }

}
