package com.tinhcao.lucene.custome;

import org.apache.lucene.analysis.util.CharTokenizer;

public class CourtesyTokenizer extends CharTokenizer {

	@Override
	protected boolean isTokenChar(int c) {
		return !Character.isSpaceChar(c);
	}

}
