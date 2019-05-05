package com.sheepclass.utils;


import com.jieba.JiebaSegmenter;
import com.sheepclass.entity.Knowledge;

import java.util.ArrayList;
import java.util.List;

public class SearchUtils {
/*
    public static List<Cuisine> getCuisine(String name, List<Cuisine> list){
        List<Cuisine> cuisines = new ArrayList<>();
        for (int i =0;i<list.size();i++){
            if (list.get(i).getC_name().indexOf(name) != -1)
                cuisines.add(list.get(i));
        }
        return cuisines;
    }*/

    public static List<Knowledge> getKnowledge(String content){
        int i = 0;
        List<String> temp;
        List<Knowledge> knowledges = new ArrayList<>();
        JiebaSegmenter jiebaSegmenter =new JiebaSegmenter();
        temp=jiebaSegmenter.sentenceProcess(content);
        while (i<temp.size()){
            if (temp.get(i).length()<1) {
                temp.remove(i);
            }
            else i++;
        }

        return null;
    }
}

