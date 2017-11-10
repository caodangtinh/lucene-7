package com.tinhcao.lucene.phonetic;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.core.LetterTokenizer;
import org.apache.lucene.analysis.phonetic.DoubleMetaphoneFilter;

public class DoubleMetaphoneAnalyzer extends Analyzer {

	@Override
	protected TokenStreamComponents createComponents(String fieldName) {
		Tokenizer tokenizer = new LetterTokenizer();
		TokenStream tokenStream = new DoubleMetaphoneFilter(tokenizer, 6, false);
		return new TokenStreamComponents(tokenizer, tokenStream);
	}

}
