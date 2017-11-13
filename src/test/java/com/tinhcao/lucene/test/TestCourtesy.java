package com.tinhcao.lucene.test;

import static org.junit.Assert.assertEquals;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.junit.Test;

import com.tinhcao.lucene.custome.CourtesyTitleAnalyzer;

public class TestCourtesy {

	@Test
	public void testCourtesy() throws Exception {
		Directory directory = new RAMDirectory();
		// create index
		Analyzer analyzer = new CourtesyTitleAnalyzer();
		IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);
		IndexWriter indexWriter = new IndexWriter(directory, indexWriterConfig);
		// add document
		Document document = new Document();
		String text = "Mr Lucene is mainly used for information retrieval and you can read more about it at lucene.apache.org.";
		document.add(new TextField("title", text, Field.Store.YES));
		indexWriter.addDocument(document);
		indexWriter.close();
		// search
		IndexSearcher indexSearcher = new IndexSearcher(DirectoryReader.open(directory));
		QueryParser parser = new QueryParser("title", analyzer);
		Query query = parser.parse("mister");
		TopDocs topDocs = indexSearcher.search(query, 1);
		assertEquals(1, topDocs.totalHits);

	}

}
