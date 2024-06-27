package io.bdrc.elasticsearch;

import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.env.Environment;
import org.elasticsearch.index.IndexSettings;
import org.elasticsearch.index.analysis.AbstractCharFilterFactory;
import org.elasticsearch.index.analysis.CharFilterFactory;
//import org.elasticsearch.index.analysis.NormalizingCharFilterFactory;

import io.bdrc.lucene.bo.TibCharFilter;
import io.bdrc.lucene.bo.TibEwtsFilter;
import io.bdrc.lucene.bo.TibPattFilter.MergedSylFilter1;
import io.bdrc.lucene.bo.TibPattFilter.MergedSylFilter2;
import io.bdrc.lucene.bo.TibPattFilter.MergedSylFilter3;
import io.bdrc.lucene.bo.TibPattFilter.MergedSylFilter4;
import io.bdrc.lucene.bo.TibPattFilter.ReorderFilter;
import io.bdrc.lucene.bo.TibPattFilter.SktFilter1;
import io.bdrc.lucene.bo.TibPattFilter.SktFilter2;
import io.bdrc.lucene.bo.TibPattFilter.SktFilter3;
import io.bdrc.lucene.bo.TibPattFilter.SktFilter4;

import java.io.Reader;

// implementing CharFilterFactory gives redundancy warning
public class TibetanCharFilterFactory extends AbstractCharFilterFactory implements CharFilterFactory {

    private Boolean lenient = false;
    private String inputMethod = null;

    public TibetanCharFilterFactory(final IndexSettings indexSettings, final Environment env, final String name,
            final Settings settings) {
        // indexSettings gives error when included in the super constructor
        super(name);
        this.lenient = settings.getAsBoolean("lenient", false);
        this.inputMethod = settings.get("input_method", null);
    }

    @Override
    public Reader create(Reader tokenStream) {
        if (this.inputMethod == null)
            tokenStream = new ReorderFilter(tokenStream);
        else
            tokenStream = new TibEwtsFilter(tokenStream, this.inputMethod, this.lenient);
        tokenStream = new TibCharFilter(tokenStream, lenient, lenient);
        if (this.lenient) {
            tokenStream = new SktFilter1(tokenStream);
            tokenStream = new SktFilter2(tokenStream);
            tokenStream = new SktFilter3(tokenStream);
            tokenStream = new SktFilter4(tokenStream);
            tokenStream = new MergedSylFilter1(tokenStream);
            tokenStream = new MergedSylFilter2(tokenStream);
            tokenStream = new MergedSylFilter3(tokenStream);
            tokenStream = new MergedSylFilter4(tokenStream);
        }
        return tokenStream;
    }
}
