package com.sheepclass.utils;

import com.jieba.JiebaSegmenter;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class SearchUtilsTest {

    @Test
    public void getKnowledge() {
        String knowledge = "0不能做分母";
        JiebaSegmenter segmenter = new JiebaSegmenter();
        List<String> temp;
        temp=segmenter.sentenceProcess(knowledge);
        for (int i=0;i<temp.size();i++)
            System.out.println(temp.get(i));

        System.out.println(this.getClass().getResource("").getPath());
    }
}