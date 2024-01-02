package com.advanced.ArrayAlgorithm;

import java.util.Arrays;

/*
* 冒泡排序
* 每次从数组中找到最大值放在数组的后面去
* 每一轮相邻比较，数组长度-1
*
* 选择排序
* 每次从数组中找到最小值放在数组的前面去
* 每一轮用一个索引与剩下进行比较
*
* 二分查找【查找对应索引位置】
* 取中间值
*
* */
public class SortArr {
    public static void main(String[] args) {
        // 冒泡排序
        // i j
        // 比较 arr[j]  > arr[j+1]
        // 0 {90,88,89,8}
        // 1 {90,88,89}
        // 2 {90,88}
        int[] arr = {90,88,89,8};
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if(arr[j] > arr[j+1]){
                    int temp = arr[j+1];
                    arr[j+1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(arr)); // [8, 88, 89, 90]

        // 选择排序
        // i  j
        // 比较 arr2[i] > arr2[j]
        // 0  {88,89,8}
        // 1  {89,8}
        // 2  {8}
        int[] arr2 = {90,88,89,8};
        for (int i = 0; i < arr2.length - 1; i++) {
            for (int j = i + 1; j < arr2.length; j++) {
                if(arr2[i] > arr2[j]){
                    int temp = arr2[i];
                    arr2[i] = arr2[j];
                    arr2[j] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(arr2)); // [8, 88, 89, 90]

        //  选择排序性能优化 查找最小值索引，在进行数组索引交换
        int[] arr3 = {90,88,89,8};
        for (int i = 0; i < arr3.length; i++) {
            // 定义最小值
            int minIndex = i;
            for (int j = i + 1; j < arr3.length; j++) {
                if(arr3[minIndex] > arr3[j]){
                    minIndex = j;
                }
            }

            if(i != minIndex){
                int temp = arr3[i];
                arr3[i] = arr3[minIndex];
                arr3[minIndex] = temp;
            }
        }
        System.out.println(Arrays.toString(arr3)); // [8, 88, 89, 90]

        // 二分查找
        System.out.println(binarySearch(arr3,88)); // 1
    }

    // 二分查找
    public static int binarySearch(int[] arr, int data){
        // 定义两个变量，一个站在左边位置，一个站在右边位置
        int left = 0;
        int right = arr.length - 1;
        while (left <= right){
            int mid = (left + right) / 2;
            if(data < arr[mid]){
                right = mid - 1; // 往左边找
            }else if(data > arr[mid]){
                left = mid - 1; // 往右边找
            }else{
                return  mid; // 中间值
            }
        }
        return -1; // 没有找到该元素
    }
}
