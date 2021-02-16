package com.chuck;

public class BinarySearch {

    public static void main(String[] args) {
        System.out.println(binarySearch(new int[]{1, 2, 3, 4, 5}, 0, 4, 100));
    }

    public static int binarySearch(int[] nums, int from, int to, int k) {

        if (from > to) {
            return -1;
        }

        int mid = from + (to - from) / 2;
        if (k == nums[mid]) {
            return mid;
        } else if (k > nums[mid]) {
            return binarySearch(nums, mid + 1, to, k);
        } else {
            return binarySearch(nums, from, mid - 1, k);
        }
    }
}
