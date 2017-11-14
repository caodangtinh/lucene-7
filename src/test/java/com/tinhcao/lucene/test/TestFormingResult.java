package com.tinhcao.lucene.test;

import static org.junit.Assert.assertEquals;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.junit.Test;

import com.tinhcao.lucene.custome.CourtesyTitleAnalyzer;

public class TestFormingResult {

	@Test
	public void test() throws Exception {
		// Indexing part
		Directory directory = new RAMDirectory();
		Analyzer analyzer = new CourtesyTitleAnalyzer();
		IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);
		IndexWriter indexWriter = new IndexWriter(directory, indexWriterConfig);
		String[] contents = { "Humpty Dumpty sat on a wall,", "Humpty Dumpty had a great fall.",
				"Four-score Men and Four-score more,", "Could not make Humpty Dumpty where he was before." };
		Document document;
		for (String content : contents) {
			document = new Document();
			document.add(new TextField("content", content, Field.Store.YES));
			indexWriter.addDocument(document);
		}
		indexWriter.close();
		// Searching part
		IndexReader indexReader = DirectoryReader.open(directory);
		IndexSearcher indexSearcher = new IndexSearcher(indexReader);
		QueryParser queryParser = new QueryParser("content", analyzer);
		Query query = queryParser.parse("humpty dumpty");
		TopDocs topDocs = indexSearcher.search(query, 10);
		assertEquals(3, topDocs.totalHits);
		System.out.println("Total Hit : " + topDocs.totalHits);
		for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
			System.out.println(indexReader.document(scoreDoc.doc).get("content") + " : " + scoreDoc.score);
		}
	}

}
