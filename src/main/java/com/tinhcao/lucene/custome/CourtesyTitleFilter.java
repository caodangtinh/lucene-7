package com.tinhcao.lucene.custome;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

public class CourtesyTitleFilter extends TokenFilter {

	Map<String, String> courtesyTitleMap = new HashMap<>();
	private CharTermAttribute charTermAttribute;

	protected CourtesyTitleFilter(TokenStream input) {
		super(input);
		charTermAttribute = addAttribute(CharTermAttribute.class);
		courtesyTitleMap.put("Dr", "doctor");
		courtesyTitleMap.put("Mr", "mister");
		courtesyTitleMap.put("Mrs", "miss");
	}

	@Override
	public final boolean incrementToken() throws IOException {
		if (!input.incrementToken())
			return false;
		String small = charTermAttribute.toString();
		if (courtesyTitleMap.containsKey(small)) {
			charTermAttribute.setEmpty().append(courtesyTitleMap.get(small));
		}
		return true;
	}

}
