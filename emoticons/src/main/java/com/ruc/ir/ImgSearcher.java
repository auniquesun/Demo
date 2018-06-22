package com.ruc.ir;

import net.semanticmetadata.lire.DocumentBuilder;
import net.semanticmetadata.lire.ImageSearchHits;
import net.semanticmetadata.lire.ImageSearcher;
import net.semanticmetadata.lire.ImageSearcherFactory;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.store.FSDirectory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Path;
import java.io.IOException;
import java.nio.file.Paths;

public class ImgSearcher {
    public void imgSearcher(){
        String [] args = new String[]{"D:\\JetBrains\\IntelliJ IDEA\\workspace\\emoticons\\src\\main\\resources\\static\\emoticons\\search.jpg"};	//进行搜索的源图片
        BufferedImage img = null;
        boolean passed = false;
        if (args.length > 0) {
            File f = new File(args[0]);
            if (f.exists()) {
                try {
                    img = ImageIO.read(f);
                    passed = true;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        if (!passed) {
            System.out.println("No image given as first argument.");
            System.out.println("Run \"Searcher <query image>\" to search for <query image>.");
            System.exit(1);
        }

        //计算图片差异值
        try {
            //建立一个索引读取器
            String INDEX_DIRECTORY = "D:\\JetBrains\\IntelliJ IDEA\\workspace\\emoticons\\data\\img_index";
            IndexReader ir = DirectoryReader.open(FSDirectory.open(Paths.get(INDEX_DIRECTORY)));
            ImageSearcher searcher = ImageSearcherFactory.createCEDDImageSearcher(100);	//数字可根据图片量进行修改
            ImageSearchHits hits = searcher.search(img, ir);
            for (int i = 0; i < hits.length(); i++) {
                String fileName = hits.doc(i).getValues(DocumentBuilder.FIELD_NAME_IDENTIFIER)[0];
                System.out.println(hits.score(i) + ": \t" + fileName);
            }
        }catch (Exception ex){
            System.out.println(ex);
        }

    }
}
