package com.sheepclass.utils;

import com.jieba.JiebaSegmenter;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class SearchUtilsTest {

    @Test
    public void getKnowledge() {
        float a = (float) 3.07123;
        System.out.println("a="+a);
        String result = String.format("%.1f",a);
        a=Float.parseFloat(result);
        System.out.println("a="+a);
    }
}