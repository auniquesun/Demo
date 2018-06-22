package com.ruc.ir;

import com.ruc.ir.bean.Img;
import com.ruc.ir.bean.ImgRepository;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Indexer {

    private ImgRepository imgRepo;

    public Indexer(ImgRepository imgRepo){
        this.imgRepo = imgRepo;
    }

    public void createIndex(){
        try{

            //获取索引目录
            final Path path = Paths.get(Constants.indexDir);
            Directory directory = FSDirectory.open(path);

            //配置indexWriter的属性
            Analyzer analyzer = new SmartChineseAnalyzer();
//            Analyzer analyzer = new StandardAnalyzer();
            IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);
            indexWriterConfig.setOpenMode(IndexWriterConfig.OpenMode.CREATE);

            //生成indexWriter
            IndexWriter indexWriter = new IndexWriter(directory, indexWriterConfig);

            //从数据库中获取图片信息并建立索引
            List<Img> images = imgRepo.findAll();

            for (Img img: images) {
                Document document = new Document();
                document.add(new TextField("name", img.getName(), Field.Store.YES));
                document.add(new TextField("tag", img.getTag(), Field.Store.YES));
                document.add(new StoredField("imgid", img.getImgid()));
                indexWriter.addDocument(document);
            }

/*            //获取文本目录下的文本列表
            File files =  new File(Constants.textDir);
            File[] fileList = files.listFiles();

            //逐个读取文件并加入索引
            for (File f: fileList) {
                if(f.isFile()){
                    //读取文件内容
                    FileInputStream fis = new FileInputStream(f);
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fis, "GBK"));
                    StringBuilder sb = new StringBuilder();
                    String current = "";
                    while((current = bufferedReader.readLine()) != null){
                        sb.append(current);
                    }
                    //添加文档
                    Document document = new Document();
                    document.add(new TextField("text", sb.toString(), Field.Store.YES));
                    document.add(new StringField("file", f.getName(), Field.Store.YES));
                    indexWriter.addDocument(document);
                    //释放
                    bufferedReader.close();
                }
            }*/

            //写入索引
            indexWriter.close();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }




}
