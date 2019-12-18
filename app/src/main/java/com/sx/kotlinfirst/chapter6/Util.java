package com.sx.kotlinfirst.chapter6;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sunxin
 * @date 2019-12-14 14:19
 * @desc
 */
public class Util {

    public static List<Integer> foo(List<Integer> list) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (Integer integer : list) {
            arrayList.add(integer * 2);
        }
        return arrayList;
    }
}
