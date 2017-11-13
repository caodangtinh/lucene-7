package com.tinhcao.lucene.custome;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;

public class CourtesyTitleAnalyzer extends Analyzer {
	@Override
	protected TokenStreamComponents createComponents(String fieldName) {
		Tokenizer tokenizer = new CourtesyTokenizer();
		TokenStream tokenStream = new CourtesyTitleFilter(tokenizer);
		return new TokenStreamComponents(tokenizer, tokenStream);
	}

}
