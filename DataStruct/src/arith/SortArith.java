package arith;

import java.util.Arrays;

/**
 * @Author: jerrylee
 * @Date: 2020/5/29 10:25 上午
 * @Desc: 排序算法
 */
public class SortArith {
    public static void main(String[] args) {
        int[] arr = {10, 2, 3, 6, 7, 11, 77, 6, 8, 9, 100, 32, 65, 84, 222, 1000};
        //insertSort(arr);
        shellSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * @Desc: 插入排序算法
     * @Author: Jerry
     * @Date: 2020/5/29
     * @Param: [arr]
     * @Return: void
     */
    public static void insertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > temp) {
                arr[j + 1] = arr[j];
                --j;
            }
            arr[j + 1] = temp;
        }
    }

    public static void shellSort(int[] arr) {
        final int len = arr.length;
        for (int gap = len / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < len; i++) {
                int j = i;
                while (j - gap > 0 && arr[j] < arr[j - gap]) {
                    int temp = arr[j];
                    arr[j] = arr[j - gap];
                    arr[j - gap] = temp;
                    j -= gap;
                }
            }
        }
    }
}
