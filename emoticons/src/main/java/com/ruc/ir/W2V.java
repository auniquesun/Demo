package com.ruc.ir;

//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.util.Collection;
import java.io.File;
import org.deeplearning4j.models.embeddings.loader.WordVectorSerializer;
//import org.deeplearning4j.models.embeddings.wordvectors.WordVectors;
import org.deeplearning4j.models.word2vec.Word2Vec;
import org.json.JSONObject;


public class W2V {
	  
 public static void main(String[] args) throws Exception{

//   File file = new File("D:\\JetBrains\\IntelliJ IDEA\\workspace\\emoticons\\wordembeddings\\pathToWriteto.txt");
//   Word2Vec vec= WordVectorSerializer.readWord2VecModel(file);
//   //collection<String> result = vec.wordsNearest("可爱",10);
//   System.out.println("和微信最接近的10个词汇:" + vec.wordsNearest("微信", 10));
//   System.out.println("和仙女最接近的10个词汇:" + vec.wordsNearest("仙女", 10));
//   System.out.println("和大佬最接近的10个词汇:" + vec.wordsNearest("大佬", 10));
//   System.out.println("和可爱最接近的10个词汇:" + vec.wordsNearest("可爱", 10));
//   System.out.println("和蘑菇最接近的10个词汇:" + vec.wordsNearest("蘑菇", 10));

     String jsonString = "{ \n \"err_no\": 0, \n \"err_msg\": \"success.\", \n \"corpus_no\": \"15984125203285346378\", \n \"sn\": \"481D633F-73BA-726F-49EF-8659ACCC2F3D\", \n \"result\": [\"哈哈哈\"] \n }";
     JSONObject jsonObject = new JSONObject(jsonString);
     System.out.println(jsonObject.toString());

    }   
}
