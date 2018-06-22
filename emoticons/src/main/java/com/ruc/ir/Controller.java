package com.ruc.ir;

import com.ruc.ir.bean.Img;
import com.ruc.ir.bean.ImgRepository;
import com.ruc.ir.bean.Likes;
import com.ruc.ir.bean.LikesRepository;
import org.deeplearning4j.models.embeddings.loader.WordVectorSerializer;
import org.deeplearning4j.models.word2vec.Word2Vec;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import sun.java2d.pipe.SpanShapeRenderer;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@EnableAutoConfiguration
public class Controller {

    Word2Vec vec = null;
    SpeechRecognition sr = null;
//    ImgIndexer img_indexer = null;

    public Controller() {
//        搜狗实验室新闻
//        File file = new File("D:\\JetBrains\\IntelliJ IDEA\\workspace\\emoticons\\data\\wordembeddings\\pathToWriteto.txt");
//        搜狗实验室新闻 + 图片标签 + 图片名
        File file = new File("D:\\JetBrains\\IntelliJ IDEA\\workspace\\emoticons\\data\\wordembeddings\\allvec.txt");
        vec = WordVectorSerializer.readWord2VecModel(file);
        sr = new SpeechRecognition();

    }

    @Autowired
    private ImgRepository imgRepo;

    @Autowired
    private LikesRepository likesRepo;

    @RequestMapping("/")
    public ModelAndView startPage(){
        return new ModelAndView("index");
    }

    //建立索引
    @RequestMapping("/add")
    public String addPage(){
        try{
            Indexer indexer = new Indexer(imgRepo);
            indexer.createIndex();

//            img_indexer = new ImgIndexer();
//            img_indexer.imgIndxer();
            return "Build index OK";
        }
        catch(Exception ex){
            ex.printStackTrace();
            return "Build index Failed";
        }
    }


    @RequestMapping("/search")
    public ModelAndView searchIndex(String query) {

        try {
            Searcher searcher =  new Searcher(imgRepo);
            List<Img> result = searcher.search(query);
            for (Img img: result
                 ) {
                img.setLikes(likesRepo.countByImgid(img.getImgid()));
            }
            ModelAndView mav =  new ModelAndView("result");

            Collection<String> relevant_words = vec.wordsNearest(query, 10);
            // 相关搜索
            List<String> relevant = new ArrayList<>();
//            relevant.add("相关1");
            for (String s:relevant_words)
            {
                relevant.add(s);
            }

            mav.addObject("result", result);
            mav.addObject("relevant", relevant);

            return mav;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return new ModelAndView("error");
        }

    }

    @RequestMapping("/add_like")
    public String addLike(String imgid){
        int realId = Integer.parseInt(imgid);
        Likes like = new Likes();
        like.setImgid(realId);
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
        like.setTime(sdf.format(d));
        likesRepo.save(like);
        return ""+like.getLikeid();
    }


    @RequestMapping("/remove_like")
    public String removeLike(String likeId){
        int realId = Integer.parseInt(likeId);
        Likes like = likesRepo.findOne(realId);
        likesRepo.delete(like);
        return "OK";
    }


    @RequestMapping("/voice")
    public void voicePage(HttpServletResponse resp){
        String jsonString = sr.getRecognitionResult();
        JSONObject jsonObject = new JSONObject(jsonString);
        Set<String> keys = jsonObject.keySet();

        if(keys.size() > 3){    //语音识别返回了结果
            String query = jsonObject.getString("result").toString();
            try {
                resp.sendRedirect("/search?query=" + query);
            }catch (IOException ex){
                System.out.println("=================== " + ex + " ===================");
            }

        }else {     //语音识别出错
            String result = jsonObject.getString("err_msg").toString();
            System.out.println("===================");
            System.out.println(result);
            System.out.println("===================");
            try {
                resp.sendRedirect("/");
            }catch (IOException ex){
                System.out.println("=================== " + ex + " ===================");
            }

        }

    }

    @RequestMapping("/speech")
    public ModelAndView speechPage(){
        return new ModelAndView("speech");
    }

    @RequestMapping("/image")
    public ModelAndView imagePage(){

        return new ModelAndView("image");
    }

    @RequestMapping("/hot")
    public ModelAndView hot(){
        try {
            Date endTime = new Date();
            Calendar cale = Calendar.getInstance();
            cale.setTime(endTime);
            cale.add(Calendar.DAY_OF_YEAR, -7);
            Date startTime = cale.getTime();
            SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
            List<Object[]> popularCount = likesRepo.findPopularCounts(sdf.format(startTime), sdf.format(endTime));
            List<Img> result = new ArrayList<>();
            for (Object[] data: popularCount
                 ) {
                Img img = imgRepo.findOne(Integer.parseInt(((BigInteger)(data[0])).toString()));
                img.setLikes(Integer.parseInt(((BigInteger)(data[1])).toString()));
                result.add(img);
            }
            return new ModelAndView("hot", "result", result);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return new ModelAndView("hot");
        }
    }

}
