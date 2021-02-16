package com.chuck.leetcode;

import java.util.*;

/**
 * 面试题 17.13. 恢复空格
 * <p>
 * 哦，不！你不小心把一个长篇文章中的空格、标点都删掉了，
 * 并且大写也弄成了小写。
 * 像句子"I reset the computer. It still didn’t boot!"
 * 已经变成了"iresetthecomputeritstilldidntboot"。
 * 在处理标点符号和大小写之前，你得先把它断成词语。
 * 当然了，你有一本厚厚的词典dictionary，不过，有些词没在词典里。
 * 假设文章用sentence表示，设计一个算法，把文章断开，要求未识别的字符最少，返回未识别的字符数。
 */

/**
 * TODO 未解出！！！
 * 失败原因，不能按照从左向右的顺序进行匹配，可能会截断某个存在的单词
 */
public class Day20200709 {

    public static int respace(String[] dictionary, String sentence) {

        // 遍历dictionary，将字符串按照长度分类
        Map<Integer, List<String>> dictMap = new HashMap<>();
        for (String dictStr : dictionary) {
            int length = dictStr.length();
            List<String> dictLenList;
            if (dictMap.containsKey(length)) {
                dictLenList = dictMap.get(length);
            } else {
                dictLenList = new ArrayList<>();
            }

            dictLenList.add(dictStr);
            dictMap.put(length, dictLenList);
        }
        List<Integer> dictMapKeyList = new ArrayList<>();
        for (Integer key : dictMap.keySet()) {
            dictMapKeyList.add(key);
        }
        dictMapKeyList.sort((o1, o2) -> (o1 > o2) ? -1 : 1);
        for (int key : dictMapKeyList) {
            List<String> dictList = dictMap.get(key);
            dictList.sort((o1, o2) -> (o1.compareTo(o2)) > 0 ? -1 : 1);
        }

        // 遍历sentence
        int begin = 0;
        int noneChar = 0;
        while (begin < sentence.length()) {

            boolean hasWord = false;
            for (int len : dictMapKeyList) {
                List<String> dictLenList = dictMap.get(len);
                int end = begin + len;
                if (end > sentence.length()) {
                    continue;
                }

                String subStr = sentence.substring(begin, begin + len);

                if (dictLenList != null && dictLenList.size() > 0) {
                    for (String dictStr : dictLenList) {
                        if (dictStr.equals(subStr)) {
                            hasWord = true;
                            begin += len;
//                            System.out.println("Begin is " + begin + ", The word is " + dictStr);
                            System.out.print(dictStr);
                            break;
                        }
                    }
                }

                if (hasWord) {
                    break;
                }
            }

            if (!hasWord) {
//                System.out.print((begin+1) + "|" + sentence.substring(begin, begin + 1) + ",");
                begin++;
                noneChar++;
            }
        }

        return noneChar;
    }

    public static void main(String[] args) {

        /**
         * TODO 此为测试数据
         */
        String[] dictionary = {"frrrbbrrbfrfqqbbbrb", "qr", "b", "rf", "qqbbbbfrqbrrqrffbrqqqbqqfqfrr", "r", "ffqq", "bffbqfqqbrrrf", "fq", "qfr", "fr", "rqrrbfbfq", "r", "f", "qbqbrbrbqfqbbbfbbbfbq", "bqqbbbqrbbrf", "f"};
        String sentence = "bqqffbqbbfqrfrrrbbrrbfrfqqbbbrbfqfffffrfqfqfffffrrfqfrrqbqfrbfrqqrfrbrbbqbqbqqfqrfbfrfr";
        System.out.println(respace(dictionary, sentence));
//        System.out.println(new Solution().respace(dictionary, sentence));
    }

}
