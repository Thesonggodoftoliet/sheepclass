package com.sheepclass.utils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PraseUtils {
    public static List<Integer> sToi(String str){
        System.out.println("调用sToi"+str);
        List list = new ArrayList();
        if (str.indexOf("[")!=-1 || str.indexOf("]")!=-1)
        {
            str = str.substring(1);
            str = str.substring(0,str.length()-1);
        }
        while (!str.isEmpty()) {
            if (str.indexOf(",") != -1) {
                int tag = str.indexOf(",");
                String str1 = str.substring(0, tag);
                str = str.substring(tag);
                System.out.println("new字符串" + str1 + "old 字符串" + str);
                Integer integer = Integer.parseInt(str1);
                System.out.println("id= " + integer);
                list.add(integer);
                str = str.substring(1);
            } else {
                Integer integer = Integer.parseInt(str);
                System.out.println("id= " + integer);
                list.add(integer);
                str=str.substring(1);
            }
        }
        return list;
    }

  /*  public static List<Restaurant> iTor(List<Integer> list){
        System.out.println("调用iTor");
        List<Restaurant> restaurants = new ArrayList<>();
        RestaurantDao rd = new RestaurantDaoImpl();
        for (int i=0;i<list.size();i++){
            System.out.println("restaurant = "+rd.getResById(list.get(i)));
            restaurants.add(rd.getResById(list.get(i)));
        }
        return restaurants;
    }

    public static List<Cuisine> iToc(List<Integer> list){
        System.out.println("调用iToc");
        List<Cuisine> cuisines = new ArrayList<>();
        CuisineDao cd = new CuisineDaoImpl();
        for (int i = 0;i<list.size();i++){
            System.out.println("cuisine = "+cd.getCuisineById(list.get(i)).getC_name());
            cuisines.add(cd.getCuisineById(list.get(i)));
        }
        return cuisines;
    }

    public static List<tempCui> sTot(String str) throws JSONException {
        JSONArray array = new JSONArray(str);
        List<tempCui> cuis= new ArrayList<>();
        for (int i =0;i<array.length();i++){
            JSONObject jo = array.getJSONObject(i);
            System.out.println("单个对象 "+jo);
            tempCui tempCui = new tempCui();
            tempCui.setId(jo.getInt("id"));
            tempCui.setNum(jo.getInt("num"));
            cuis.add(tempCui);
        }
        return cuis;
    }

 /*   public static List<Cuisine> tTOc(List<tempCui> list){
        CuisineDao cuisineDao =new CuisineDaoImpl();
        List<Cuisine> cuisines = new ArrayList<>();
        for (int i = 0;i<list.size();i++){
            for (int j = 0;j<list.get(i).getNum();j++){
                cuisines.add(cuisineDao.getCuisineById(list.get(i).getId()));
            }
        }
        return cuisines;
    }*/
}
