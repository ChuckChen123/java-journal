package com.chuck.leetcode;

import java.util.List;

/**
 * 生成每日习题
 */
public class GeneratePast {

    public static void main(String[] args) {

        ProblemsGenerator problemsGenerator = new ProblemsGenerator();
        List<String> problems = problemsGenerator.getProblems(0);
        for (String problem : problems) {
            System.out.println(problem);
        }
    }
}
