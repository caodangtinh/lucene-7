package com.tinhcao.lucene;

import java.util.Scanner;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import com.tinhcao.lucene.constant.Constant;
import com.tinhcao.lucene.phonetic.DoubleMetaphoneAnalyzer;

public class BookSearcher {

	public static void main(String[] args) throws Exception {
		Directory directory = FSDirectory.open(Constant.INDEX_PATH);
		IndexSearcher indexSearcher = new IndexSearcher(DirectoryReader.open(directory));
		QueryParser queryParser = new QueryParser("name", new DoubleMetaphoneAnalyzer());
		Scanner scanner = new Scanner(System.in);
		Query query;
		TopDocs topDocs;
		while (true) {
			System.out.println("Enter Search Keyword : ");
			String keyword = scanner.nextLine();
			query = queryParser.parse(keyword);
			topDocs = indexSearcher.search(query, 10);
			long totalHit = topDocs.totalHits;
			System.out.println("Total Hit : " + totalHit);
			float maxScore = topDocs.getMaxScore();
			System.out.println("Max Score : " + maxScore);
			for (ScoreDoc doc : topDocs.scoreDocs) {
				Document document = indexSearcher.doc(doc.doc);
				System.out.println(document.get("name"));
				System.out.println(doc.score);
			}
		}
	}

}
