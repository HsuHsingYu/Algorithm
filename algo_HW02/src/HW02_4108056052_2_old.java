public class HW02_4108056052_2_old extends ThreeSum
{
    public static void main(String[] args)
    {
        HW02_4108056052_2_old test = new HW02_4108056052_2_old();
     //   int[] A = {-1, 1, 2, 4, 8, -3};
        int[] A = new RandomArray().readData();
        Stopwatch stopwatch = new Stopwatch();
        System.out.println("ThreeSum is zero: " + test.T_sum(A));
        double time = stopwatch.elapsedTime();
        System.out.println("time:" + time);
    }

    @Override
    public int T_sum(int[] A) // solution in LeetCode O(n^2)
    {
        int cnt = 0;
        mergeSort4108056052(A);
        // > 0 → do left shift to be small (right)
        // < 0 → do right shift to be big (left)
        // = 0 → range small
        // all positive → over
        int len = A.length;
        int first_positive = BT4108056052(A,len);

        /* start */
        for (int i = 0; i < first_positive; i++) // O(n)
        {
            int left = i+1, right = len-1;

            int sum;
            while (left < right) //O(n)
            {
                sum = A[i] + A[left] + A[right];
                if (sum > 0)
                    right--;
                else if (sum < 0)
                    left++;
                else
                {
                    cnt++;
                    right--;
                    left++;
                }
            }
        }
        return cnt;
    }

    private static void mergeSort4108056052(int[] A) // divide and conquer
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

    public int BT4108056052(int[] A,int len)
    {
        int key = 0, low = 0, high = len - 1;
        if ((key <= A[low]) || (key >= A[high])) // 0 <= left all positive 0 >= right all negative
        {
            return -1;
        }
        int mid, midValue;
        while (low + 1 < high)
        {
            mid = (low + high) / 2;
            midValue = A[mid];
            if (key < midValue)
            {
                high = mid;
            }
            else if (key > midValue)
            {
                low = mid;
            }
            else
            {
                return mid;
            }
        }
        return high;
    }
}
