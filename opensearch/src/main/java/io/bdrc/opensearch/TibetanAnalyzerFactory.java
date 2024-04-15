package io.bdrc.opensearch;

import java.io.IOException;

import org.opensearch.common.settings.Settings;
import org.opensearch.env.Environment;
import org.opensearch.index.IndexSettings;
import org.opensearch.index.analysis.AbstractIndexAnalyzerProvider;

import io.bdrc.lucene.bo.TibetanAnalyzer;

public class TibetanAnalyzerFactory extends AbstractIndexAnalyzerProvider<TibetanAnalyzer> {
    
    private boolean lenient = false;
    private String inputMethod = TibetanAnalyzer.INPUT_METHOD_DEFAULT;

    public TibetanAnalyzerFactory(IndexSettings indexSettings, Environment env, String name, Settings settings) {
        super(indexSettings, name, settings);
        this.lenient = settings.getAsBoolean("lenient", false);
        this.inputMethod = settings.get("input_method", TibetanAnalyzer.INPUT_METHOD_DEFAULT);
    }

    @Override
    public TibetanAnalyzer get() {
        try {
            return new TibetanAnalyzer(false, this.lenient ? "" : "paba-affix", this.lenient ? "l-ot" : "min", this.inputMethod, null, null);
        } catch (IOException e) {
            // this shouldn't happen
            e.printStackTrace();
            return null;
        }
    }

}
