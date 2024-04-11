# analysis-tibetan

Tibetan analyzer for Opensearch and Elasticsearch

### Getting started

Simple example:

```
POST _analyze
{
  "tokenizer" : "tibetan",
  "filter" : ["tibetan"],
  "char_filter" : ["tibetan"],
  "text" : "ཀ་ཁཱ་ག"
}
```

Example with parameters: first let's create an index for the test

```
PUT /tibetantest/
{
  "settings": {
    "analysis": {
      "analyzer": {
        "tibetan-lenient": {
          "tokenizer": "tibetan",
          "filter": [ "tibetan-lenient" ],
          "char_filter": [ "tibetan-lenient" ]
        },
        "tibetan-ewts-lenient": {
          "tokenizer": "tibetan",
          "filter": [ "tibetan-lenient" ],
          "char_filter": [ "tibetan-ewts-lenient" ]
        }
      },
      "filter": {
        "tibetan-lenient": {
          "type": "tibetan",
          "remove_affixes": true,
          "normalize_paba": true
        }
      },
      "char_filter": {
        "tibetan-lenient": {
          "type": "tibetan",
          "lenient": true
        },
        "tibetan-ewts-lenient": {
          "type": "tibetan",
          "lenient": true,
          "input_method": "ewts"
        }
      }
    }
  }
}
```

then test the lenient support (note that `ཁཱ` is transformed into `ཁ`):

```
POST /tibetantest/_analyze
{
  "tokenizer" : "tibetan",
  "filter" : ["tibetan-lenient"],
  "char_filter" : ["tibetan-lenient"],
  "text" : "ཀ་ཁཱ་ག"
}
```

and the transliteration support:

```
POST /tibetantest/_analyze
{
  "tokenizer" : "tibetan",
  "filter" : ["tibetan-lenient"],
  "char_filter" : ["tibetan-ewts-lenient"],
  "text" : "ka kha ga"
}
```

### Sources of inspiration and doc

https://github.com/lionsoul2014/jcseg
https://github.com/infinilabs/analysis-stconvert/

https://docs.aws.amazon.com/opensearch-service/latest/developerguide/custom-packages.html
