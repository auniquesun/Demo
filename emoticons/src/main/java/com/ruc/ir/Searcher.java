package com.ruc.ir;

import com.ruc.ir.bean.Img;
import com.ruc.ir.bean.ImgRepository;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springframework.beans.factory.annotation.Autowired;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Searcher {

    private ImgRepository imgRepo;

    public Searcher(ImgRepository imgRepo){
        this.imgRepo = imgRepo;
    }


    public List<Img> search(String queryString){
        try{

            //相关配置
            final Path path = Paths.get(Constants.indexDir);
            Directory directory = FSDirectory.open(path);
            Analyzer analyzer = new SmartChineseAnalyzer();
//            Analyzer analyzer = new StandardAnalyzer();

            //查询解析
            QueryParser queryParser = new MultiFieldQueryParser(new String[]{"name", "tag"}, analyzer);
            Query query = queryParser.parse(queryString);

            //加载索引
            IndexReader indexReader = DirectoryReader.open(directory);
            IndexSearcher indexSearcher = new IndexSearcher(indexReader);

            //Top文档
            TopDocs topDocs = indexSearcher.search(query, 300);
            long count = topDocs.totalHits;
            ScoreDoc[] scoreDocs = topDocs.scoreDocs;

            List<Img> result = new ArrayList<>();
//            result.add(String.valueOf(count));

            for (ScoreDoc scoreDoc: scoreDocs) {
                Document document = indexSearcher.doc(scoreDoc.doc);
                //scoreDoc.score
                Img img = imgRepo.findOne(Integer.parseInt(document.get("imgid")));
                result.add(img);
            }
            return result;

        }
        catch(Exception ex){
            ex.printStackTrace();

            return new ArrayList<>();
        }
    }




}
