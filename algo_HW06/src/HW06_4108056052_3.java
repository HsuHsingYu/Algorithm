public class HW06_4108056052_3 extends MedianOfArrays
{
    @Override
    public double find_median(int[][] arrays)
    {
        int arr_num = 0;
        for (int[] array : arrays)
        {
            arr_num += array.length;
        }
        int[] arr = new int[arr_num];

        int index = 0;
        for (int[] array : arrays)
        {
            for (int i : array)
            {
                arr[index++] = i;
            }
        }

        mergeSort4108056052(arr);
        for (int i = 0; i < arr_num; i++)
        {
            System.out.print(arr[i]);
            System.out.print(" ");
        }
        System.out.println();

        if (arr_num % 2 == 0)
        {

            return (double) (arr[(arr_num-1) / 2] + arr[(arr_num) / 2]) / (double) 2;
        }
        else
        {
            return arr[arr_num/ 2];
        }
    }

    public static void mergeSort4108056052(int[] A) // divide and conquer
    {
        int len = A.length;
        int[] arr = new int[len];
        for (int i = 2; i < len * 2; i *= 2)
        {
            int left, mid, right, start, l, m;
            int j = -1;
            int limit = (len+i-1)/i;
            while(++j < limit)
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
