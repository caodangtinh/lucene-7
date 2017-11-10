package com.tinhcao.lucene;

import java.util.Arrays;
import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import com.tinhcao.lucene.constant.Constant;
import com.tinhcao.lucene.phonetic.DoubleMetaphoneAnalyzer;

public class BookIndexer {

	public static void main(String[] args) throws Exception {
		// create IndexWriter
		Directory directory = FSDirectory.open(Constant.INDEX_PATH);
		IndexWriterConfig indexWriterConfig = new IndexWriterConfig(new DoubleMetaphoneAnalyzer());
		IndexWriter indexWriter = new IndexWriter(directory, indexWriterConfig);
		// prepare data "live", "love", "lavie"
		List<String> inputData = Arrays.asList("live");
		for (String s : inputData) {
			// create document
			Document document = new Document();
			document.add(new TextField("name", s, Field.Store.YES));
			// write document to index
			indexWriter.addDocument(document);
		}
		indexWriter.close();
	}
}
