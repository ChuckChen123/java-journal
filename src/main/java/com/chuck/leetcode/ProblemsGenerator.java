package com.chuck.leetcode;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class ProblemsGenerator {

    private Random random = new Random();

    private static List<String> problemsUndone = new ArrayList<>();
    private static final Map<String, List<String>> problemsDone = new HashMap<>();
    private static final Date date = new Date();
    private static final Date weekDate = new Date(date.getTime() - 7 * 24 * 60 * 60 * 1000L);
    private static final Date monthDate = new Date(date.getTime() - 30 * 24 * 60 * 60 * 1000L);
    private static final Date yesDate = new Date(date.getTime() - 1 * 24 * 60 * 60 * 1000L);
    private static final String basePath =
            System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                    + File.separator + "resources";
    private static final String undonePath = basePath + File.separator + "problems.txt";
    private static final String donePath = basePath + File.separator + "problems_done.txt";
    private static final String addPath = basePath + File.separator + "add_problems.txt";
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    private static final String now = sdf.format(date);
    private static final String weekStr = sdf.format(weekDate);
    private static final String monthStr = sdf.format(monthDate);
    private static final String yesStr = sdf.format(yesDate);

    static {
        readProblemsUndone(undonePath);
        readProblemsDone(donePath);
        addProblems(addPath);
    }

    public List<String> getProblems(int n) {
        return this.getProblems(n, false);
    }

    public List<String> getProblems(int n, boolean isAppend) {
        List<String> result = new ArrayList<>();

        // 判断今天是否已经安排过任务
        if (problemsDone.containsKey(now)) {
            // 如果已经安排任务还需要继续安排，则在未完成任务中提取任务
            if (isAppend) {
                result.addAll(peekProblems(n));
            } else {
                // 如果不需要重新分配，则直接读取已完成的任务返回
                for (String d : problemsDone.keySet()) {
                    if (now.equals(d)) {
                        result.addAll(problemsDone.get(d));
                    }
                }
                result.addAll(peekReviewProblems());
            }
        } else {

            // 今天没有分配任务，需要在未完成的任务中读取n个任务，
            // 然后获取复习任务（为昨天已完成任务，前一周已完成任务，然后在剩下的任务中随机抽取一个任务）
            // 最后返回
            result.addAll(peekProblems(n));
            result.addAll(peekReviewProblems());
        }

        updateProblems();
        return result;
    }

    /**
     * 返回复习题目
     *
     * @return
     */
    private List<String> peekReviewProblems() {
        List<String> result = new ArrayList<>();

        if (problemsDone.containsKey(yesStr)) {
            result.addAll(problemsDone.get(yesStr));
        }

        if (problemsDone.containsKey(weekStr)) {
            result.addAll(problemsDone.get(weekStr));
        }

        if (problemsDone.containsKey(monthStr)) {
            result.addAll(problemsDone.get(monthStr));
        }

        List<String> restProblems = new ArrayList<>();
        for (String timeStr : problemsDone.keySet()) {
            if (!timeStr.equals(now) && !timeStr.equals(weekStr) && !timeStr.equals(monthStr) && !timeStr.equals(yesStr)) {
                restProblems.addAll(problemsDone.get(timeStr));
            }
        }

        shuffle(restProblems);
        result.add(restProblems.get(0));

        return result;
    }

    /**
     * 选择n个任务返回
     *
     * @param n
     * @return
     */
    private List<String> peekProblems(int n) {

        List<String> result = new ArrayList<>();
        if (problemsUndone.size() == 0) {
            return result;
        }
        shuffle(problemsUndone);

        n = (n > problemsUndone.size()) ? problemsUndone.size() : n;
        for (int i = 0; i < n; i++) {
            result.add(problemsUndone.get(i));
        }

        // 从未完成中删除分配的任务
        for (int i = 0; i < n; i++) {
            problemsUndone.remove(0);
        }

        // 将分配的任务添加到已完成中
        List<String> done = problemsDone.get(now);
        if (done == null) {
            done = new ArrayList<>();
        }
        done.addAll(result);
        problemsDone.put(now, done);

        return result;
    }

    /**
     * 为数组洗牌，随机排列数组
     *
     * @param arr
     */
    private void shuffle(List<String> arr) {
        for (int i = 0; i < arr.size(); i++) {
            int r = random.nextInt(i + 1);
            swap(arr, i, r);
        }
    }

    /**
     * 交换数组任意两个元素
     *
     * @param arr
     * @param f
     * @param t
     */
    private void swap(List<String> arr, int f, int t) {
        String temp = arr.get(f);
        arr.set(f, arr.get(t));
        arr.set(t, temp);
    }

    private static void addProblems(String path) {
        File file = new File(path);

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = "";
            Set<String> problemUndoneSet = new HashSet<>(problemsUndone);
            while ((line = br.readLine()) != null) {
                if (line != null && !"".equals(line)) {
                    line.trim();
                    problemUndoneSet.add(line);
                }
            }

            // 合并addProblems和undoneProblems
            problemsUndone = new ArrayList<>(problemUndoneSet);

            // 移除addProblems中已完成的部分
            Set<String> problemsDoneSet = new HashSet<>();
            for (List<String> done : problemsDone.values()) {
                problemsDoneSet.addAll(done);
            }
            problemsUndone.removeAll(problemsDoneSet);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取未完成的任务
     *
     * @param path
     */
    private static void readProblemsUndone(String path) {
        File file = new File(path);

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = "";
            while ((line = br.readLine()) != null) {
                if (line != null && !"".equals(line)) {
                    line.trim();
                    problemsUndone.add(line);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取已完成的任务
     *
     * @param path
     */
    private static void readProblemsDone(String path) {
        File file = new File(path);

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = "";
            while ((line = br.readLine()) != null) {
                if (line != null && !"".equals(line)) {
                    String[] arr = line.split(" ");

                    if (problemsDone.containsKey(arr[0])) {
                        problemsDone.get(arr[0]).add(arr[1]);
                    } else {
                        List<String> data = new ArrayList<>();
                        data.add(arr[1]);
                        problemsDone.put(arr[0], data);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 更新最新的任务到文件
     */
    private static void updateProblems() {

        // 更新unDone
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(undonePath))) {
            for (String str : problemsUndone) {
                bw.write(str);
                bw.newLine();
            }
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 更新done
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(donePath))) {
            for (String key : problemsDone.keySet()) {
                List<String> data = problemsDone.get(key);
                for (String str : data) {
                    bw.write(key + " " + str);
                    bw.newLine();
                }
            }
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
