package io.bdrc.opensearch;

import java.io.Reader;

import org.opensearch.common.settings.Settings;
import org.opensearch.env.Environment;
import org.opensearch.index.IndexSettings;
import org.opensearch.index.analysis.AbstractCharFilterFactory;
import org.opensearch.index.analysis.CharFilterFactory;

public class SimpleDiacriticsFilterFactory extends AbstractCharFilterFactory implements CharFilterFactory {

    public SimpleDiacriticsFilterFactory(final IndexSettings indexSettings, final Environment env, final String name, final Settings settings) {
        super(indexSettings, name);
    }

    @Override
    public Reader create(Reader reader) {
        return new SimpleDiacriticsFilter(reader);
    }

}
