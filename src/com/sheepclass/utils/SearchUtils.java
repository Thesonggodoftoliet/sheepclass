package com.sheepclass.utils;


import com.jieba.JiebaSegmenter;
import com.sheepclass.dao.KnowledgeDao;
import com.sheepclass.dao.implement.KnowledgeDaoImpl;
import com.sheepclass.entity.Knowledge;
import com.sheepclass.service.Learn;

import java.util.ArrayList;
import java.util.Collections;
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

    public static Knowledge getKnowledge(String content){
        class temp implements Comparable{
            int knowledgeid;
            int count;
            Knowledge knowledge;

            public Knowledge getKnowledge() {
                return knowledge;
            }

            public void setKnowledge(Knowledge knowledge) {
                this.knowledge = knowledge;
            }

            public temp(int knowledgeid){
                this.knowledgeid=knowledgeid;
            }
            public int getKnowledgeid() {
                return knowledgeid;
            }

            public void setKnowledgeid(int knowledgeid) {
                this.knowledgeid = knowledgeid;
            }

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count=count;
            }

            @Override
            public int compareTo(Object o) {
                temp p = (temp)o;
                return p.count-this.count;//降序排列
            }
        }
        KnowledgeDao knowledgeDao = new KnowledgeDaoImpl();
        int i = 0;
        List<String> temp;
        List<Knowledge> knowledges = new ArrayList<>();
        JiebaSegmenter jiebaSegmenter =new JiebaSegmenter();
        temp=jiebaSegmenter.sentenceProcess(content);

        //忽略关键词字数少于1的
        while (i<temp.size()){
            if (temp.get(i).length()<1) {
                temp.remove(i);
            }
            else i++;
        }
        i=0;
        while (i<temp.size()){
            knowledges.addAll(knowledgeDao.getKnowledge(temp.get(i)));
            i++;
        }
        Collections.sort(knowledges);
        List<temp> temps = new ArrayList<>();
        for (int j = 0;j<knowledges.size();j++){
            temp t = new temp(knowledges.get(j).getKnowledgeid());
            int last = knowledges.lastIndexOf(knowledges.get(j));//最后一次出现位置
            t.setCount(last-j+1);//知识点符合程度
            t.setKnowledge(knowledges.get(j));
            temps.add(t);
            if (j != last)
                for (int m = j+1;m<=last-j;m++)
                    knowledges.remove(j+1);
        }
        knowledges.clear();
        Collections.sort(temps);

        if(temps.size() == 0)
            return null;
        return temps.get(0).getKnowledge();
    }
}

