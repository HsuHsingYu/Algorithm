public class HW06_4108056052_4 extends MedianOfArrays
{
    final int max = 100000000;
    int[] table = new int[max << 1]; // 0~max-1 for negative；max~2*max-1 for positive

    @Override
    public double find_median(int[][] arrays)
    {
        int arr_len, num = 0;   // num = total number

        /* put all number into the table */
        for (int[] array : arrays)
        {
            arr_len = array.length;
            for (int j = 0; j < arr_len; j++)
            {
                table[array[j] + max]++;
                num++;
            }
        }

        /* find median of arr */
        int cnt = 0, mid;
        if ((num % 2) != 0)
        {
            mid = (num >> 1) + 1;
            for (int i = 0; i < max << 1; i++)
            {
                if (table[i] == 0)
                {
                    continue;
                }
                cnt += table[i];
                if (cnt >= mid)
                {
                    return i - max;
                }
            }
        }
        else
        {
            mid = num >> 1;
            int median1 = Integer.MIN_VALUE;
            for (int i = 0; i < max << 1; i++)
            {
                if (table[i] == 0)
                {
                    continue;
                }
                cnt += table[i];
                if (cnt > mid)
                {
                    if (median1 == Integer.MIN_VALUE)   // mid = mid+1
                        return i - max;
                    else    // i = mid2
                        return (double) (median1 + (i - max)) / 2;

                }
                else if (cnt == mid) // find next mid (mid != mid+1)
                {
                    median1 = i - max;
                }
            }
        }
        return -1;
    }
}
