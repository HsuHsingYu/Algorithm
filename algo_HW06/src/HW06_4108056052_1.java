import java.util.Arrays;

public class HW06_4108056052_1 extends MedianOfArrays
{
    public static void main(String[] args)
    {
        HW06_4108056052_1 test = new HW06_4108056052_1();
//       int[][] arrays = {{1, 3, 10}, {-1, 5, 7}, {-2, 5, 12}};
//        int[][] arrays = {{3}, {-1,5},{-2,5,12}};
       int[][] arrays = {{-7},{-1,4},{8,2},{0,5,-6},{-7}};
        for (int[] ints : arrays)
        {
            Arrays.sort(ints);
        }

        for (int[] array : arrays)
        {
            for (int j = 0; j < array.length; j++)
            {
                System.out.print(array[j]);
            }
            System.out.println();
        }
        Stopwatch stopwatch = new Stopwatch();
        double result = test.find_median(arrays);
        double time = stopwatch.elapsedTime();
        System.out.println("elapsed time = " + time);
        System.out.println(result);
    }
    @Override
    public double find_median(int[][] arrays)
    {
        int arr_num = arrays.length;

        // if arr = 1 ->return
        if (arr_num == 1)
        {
            int len = arrays[0].length;
            if (len % 2 == 0) // even
            {
                return (double) (arrays[0][len / 2 - 1] + arrays[0][len / 2]) / (double) 2;
            }
            else
            {
                return arrays[0][len / 2];
            }
        }

        int[] arr_len = new int[arrays.length]; // save new arr len

        int min_mid = Integer.MAX_VALUE, max_mid = Integer.MIN_VALUE, num_of_arr = 0;
        int min_arr = 0, max_arr = 0; // min_mid in which arr, max_mid in which arr
        boolean even = false;


        /* find min_mid and max_mid and num_of_arr */
        for (int i = 0; i < arr_num; i++)
        {
            int len = arrays[i].length;
            num_of_arr += len;
            arr_len[i] = len;
            int value = arrays[i][(len - 1) / 2]; // mid of the array
            if (min_mid > value)
            {
                min_mid = value;
                min_arr = i;
            }
            if (max_mid < value)
            {
                max_mid = value;
                max_arr = i;
            }
        }

        if (num_of_arr%2 == 0)
        {
            even = true;
        }

        System.out.println("--------------------------");
        while (num_of_arr > 3)
        {
            // index = index of new_arr, find_min_mid = new min mid , find_max_mid = new max mid
            int find_min_mid = Integer.MAX_VALUE, find_max_mid = Integer.MIN_VALUE;
            int new_len = 0; // cnt each array len
            num_of_arr = 0; // cnt number in arr
            System.out.println("min mid = " + min_mid);
            System.out.println("max mid = " + max_mid);
            for (int i = 0; i < arr_num; i++)
            {
                //save remain num
                int len = arr_len[i];
                for (int j = 0; j < len; j++)
                {
                    System.out.println();
                    System.out.println(arrays[i][j]);
                    System.out.println("min mid = " + min_mid);
                    System.out.println("max mid = " + max_mid);
                    System.out.println();

                    if (min_mid <= arrays[i][j] && arrays[i][j] <= max_mid)
                    {
                        arrays[i][new_len++] = arrays[i][j];
                        num_of_arr++;
                        System.out.println(arrays[i][j]);
                    }
                }

                //find new min_mid and max_mid
                if (new_len > 0)
                {
                    if (find_min_mid > arrays[i][(new_len - 1) / 2])
                    {
                        find_min_mid = arrays[i][(new_len - 1) / 2];
                        min_arr = i;
                    }
                    if (find_max_mid < arrays[i][(new_len - 1) / 2])
                    {
                        find_max_mid = arrays[i][(new_len - 1) / 2];
                        max_arr = i;
                    }
                }
                arr_len[i] = new_len;
                new_len = 0;
            }

            if (num_of_arr <= 3)
            {
                break;
            }

            // if the max, min still the same -> delete them
            if (min_mid == find_min_mid && max_mid == find_max_mid)
            {
                int len = arr_len[min_arr];
                System.arraycopy(arrays[min_arr], (len - 1) / 2 + 1, arrays[min_arr], (len - 1) / 2, len - 1);
                arr_len[min_arr]--;

                len = arr_len[max_arr];
                System.arraycopy(arrays[max_arr], (len - 1) / 2 + 1, arrays[max_arr], (len - 1) / 2, len - 1);
                arr_len[max_arr]--;
                num_of_arr -= 2; // min_mid and max_mid
                if (num_of_arr <= 3)
                {
                    break;
                }

                //re-find max and min
                min_mid = Integer.MAX_VALUE;
                max_mid = Integer.MIN_VALUE;
                for (int i = 0; i < arr_num; i++)
                {
                    len = arr_len[i];
                    if (len == 0)
                    {
                        continue;
                    }
                    if (min_mid > arrays[i][(len - 1) / 2])
                    {
                        min_mid = arrays[i][(len - 1) / 2];
                        min_arr = i;
                    }
                    if (max_mid < arrays[i][(len - 1) / 2])
                    {
                        max_mid = arrays[i][(len - 1) / 2];
                        max_arr = i;
                    }
                }
            }
            else
            {
                min_mid = find_min_mid;
                max_mid = find_max_mid;
            }
        }

        if (num_of_arr == 2)
        {
            double num = 0;
            for (int i = 0; i < arr_num; i++)
            {
                int len = arr_len[i];
                if (len == 0)
                {
                    continue;
                }
                for (int j = 0; j < len; j++)
                {
                    num += arrays[i][j];
                }
            }
            return num / (double) 2;
        }
        else if (num_of_arr == 1)
        {
            double num = 0;
            for (int i = arr_num - 1; i >= 0; i--)
            {
                int len = arr_len[i];
                if (len != 0)
                {
                    num = arrays[i][0];
                    break;
                }
            }
            return num;
        }
        else
        {
            if (even)
            {
                min_mid = Integer.MAX_VALUE;
                int min2_mid = Integer.MAX_VALUE;
                for (int i = 0; i < arr_num; i++)
                {
                    int len = arr_len[i];
                    if (len == 0)
                    {
                        continue;
                    }
                    if (min_mid > arrays[i][(len - 1) / 2])
                    {
                        min2_mid = min_mid;
                        min_mid = arrays[i][(len - 1) / 2];
                    }
                    else if (min2_mid > arrays[i][(len - 1) / 2])
                    {
                        min2_mid = arrays[i][(len - 1) / 2];
                    }
                }

                return (double) (min_mid+min2_mid) / (double) 2;
            }
            else
            {
                int[] num = {0, 0, 0};
                int index = 0;
                for (int i = 0; i < arr_num; i++)
                {
                    int len = arr_len[i];
                    if (len == 0)
                    {
                        continue;
                    }
                    for (int j = 0; j < len; j++) // if enter num is smaller -> exchange
                    {
                        num[index++] = arrays[i][j];
                    }
                }
                mergeSort4108056052(num);

                return num[1];
            }
        }

    }


    public static void mergeSort4108056052(int[] A) // divide and conquer
    {
        int len = A.length;
        int[] arr = new int[len];
        for (int i = 2; i < len << 1; i = i << 1)
        {
            int left, mid, right, start, l, m;
            for (int j = 0; j < (len + i - 1) / i; j++)
            {
                left = i * j;
                mid = (left + (i >> 1)) >= len ? (len - 1) : (left + (i >> 1));
                right = i * (j + 1) - 1 >= len ? (len - 1) : (i * (j + 1) - 1);
                start = left;
                l = left;
                m = mid;
                while (l < mid && m <= right)
                {
                    if (A[l] < A[m])
                    {
                        arr[start++] = A[l++];
                    }
                    else
                    {
                        arr[start++] = A[m++];
                    }
                }
                while (l < mid)
                {
                    arr[start++] = A[l++];
                }
                while (m <= right)
                {
                    arr[start++] = A[m++];
                }
                System.arraycopy(arr, left, A, left, right - left + 1);
            }
        }
    }
}
