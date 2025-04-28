package io.bdrc.opensearch;

import org.apache.lucene.analysis.TokenStream;
import org.opensearch.common.settings.Settings;
import org.opensearch.env.Environment;
import org.opensearch.index.IndexSettings;
import org.opensearch.index.analysis.AbstractTokenFilterFactory;

import io.bdrc.lucene.bo.PaBaFilter;
import io.bdrc.lucene.bo.TibAffixedFilter;
import io.bdrc.lucene.bo.TibSyllableLemmatizer;
import io.bdrc.lucene.bo.YiTokenFilter;

public class TibetanTokenFilterFactory extends AbstractTokenFilterFactory {
    
    private boolean remove_affixes = false;
    private boolean normalize_paba = false;
    private boolean normalize_verbs = false;
    private boolean remove_yi = false;
    
    public TibetanTokenFilterFactory(final IndexSettings indexSettings, final Environment env, final String name, final Settings settings) {
        super(indexSettings, name, settings);
        this.remove_affixes = settings.getAsBoolean("remove_affixes", false);
        this.normalize_paba = settings.getAsBoolean("normalize_paba", false);
        this.normalize_verbs = settings.getAsBoolean("normalize_verbs", false);
        this.remove_yi = settings.getAsBoolean("remove_yi", this.remove_affixes); // by default, same as remove_affixes
    }

    @Override
    public TokenStream create(TokenStream tokenStream) {
        if (this.remove_affixes)
            tokenStream = new TibAffixedFilter(tokenStream, true);
        if (this.remove_yi)
        	tokenStream = new YiTokenFilter(tokenStream);
        if (this.normalize_paba)
            tokenStream = new PaBaFilter(tokenStream);
        if (this.normalize_verbs)
            tokenStream = new TibSyllableLemmatizer(tokenStream);
        return tokenStream;
    }

}
