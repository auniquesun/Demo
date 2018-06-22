package com.ruc.ir;

import net.semanticmetadata.lire.DocumentBuilder;
import net.semanticmetadata.lire.DocumentBuilderFactory;
import net.semanticmetadata.lire.utils.FileUtils;
import net.semanticmetadata.lire.utils.LuceneUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.FSDirectory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Paths;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class ImgIndexer {

    public ImgIndexer(){

    }

    public void imgIndxer(){
        boolean passed = false;
        String [] args = new String[]{"D:\\JetBrains\\IntelliJ IDEA\\workspace\\emoticons\\src\\main\\resources\\static\\emoticons"};	//图片存放目录，自行更改
        if (args.length > 0) {
            File f = new File(args[0]);
            System.out.println("Indexing images in " + args[0]);
            if (f.exists() && f.isDirectory()) passed = true;
        }
        if (!passed) {
            System.out.println("No directory given as first argument.");
            System.out.println("Run \"Indexer <directory>\" to index files of a directory.");
            System.exit(1);
        }
        // 获取所有目录下的图片2
        try{
            ArrayList<String> images = FileUtils.getAllImages(new File(args[0]), true);
            // 建立一个CEDD文档生成器并且索引所有图片
            DocumentBuilder builder = DocumentBuilderFactory.getCEDDDocumentBuilder();
            // 建立一个Lucene写索引工具
//            IndexWriterConfig conf = new IndexWriterConfig(LuceneUtils.LUCENE_VERSION,
//                    new WhitespaceAnalyzer(LuceneUtils.LUCENE_VERSION));
            Analyzer analyzer = new SmartChineseAnalyzer();
            IndexWriterConfig conf = new IndexWriterConfig(analyzer);
            String INDEX_DIRECTORY = "D:\\JetBrains\\IntelliJ IDEA\\workspace\\emoticons\\data\\img_index";
            IndexWriter iw = new IndexWriter(FSDirectory.open(Paths.get(INDEX_DIRECTORY)), conf);
            // 循环索引图片
            for (Iterator<String> it = images.iterator(); it.hasNext(); ) {
                String imageFilePath = it.next();
                System.out.println("Indexing " + imageFilePath);
                try {
                    BufferedImage img = ImageIO.read(new FileInputStream(imageFilePath));
                    Document document = builder.createDocument(img, imageFilePath);
                    iw.addDocument(document);
                } catch (Exception e) {
                    System.err.println("Error reading image or indexing it.");
                    e.printStackTrace();
                }
            }
            // 关闭写索引工具
            iw.close();
            System.out.println("Finished indexing.");
        }catch (IOException ex){
            System.out.println(ex);
        }
    }
}
