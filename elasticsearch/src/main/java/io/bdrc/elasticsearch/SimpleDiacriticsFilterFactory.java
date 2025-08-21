package io.bdrc.elasticsearch;

import java.io.Reader;

import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.env.Environment;
import org.elasticsearch.index.IndexSettings;
import org.elasticsearch.index.analysis.AbstractCharFilterFactory;
import org.elasticsearch.index.analysis.CharFilterFactory;

public class SimpleDiacriticsFilterFactory extends AbstractCharFilterFactory implements CharFilterFactory {

    public SimpleDiacriticsFilterFactory(final IndexSettings indexSettings, final Environment env, final String name, final Settings settings) {
        super(name);
    }

    @Override
    public Reader create(Reader reader) {
        return new SimpleDiacriticsFilter(reader);
    }

}
