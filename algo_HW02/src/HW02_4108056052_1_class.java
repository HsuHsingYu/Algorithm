public class HW02_4108056052_1_class extends ThreeSum
{
    public static void main(String[] args)
    {
        HW02_4108056052_1_class test = new HW02_4108056052_1_class();
     //   int[] A = {-1, 1, 2, 4, 8, -3};
        int[] A = new RandomArray().readData();
        Stopwatch stopwatch = new Stopwatch();
        System.out.println("ThreeSum is zero: " + test.T_sum(A));
        double time = stopwatch.elapsedTime();
        System.out.println("time:" + time);
    }

    @Override
    public int T_sum(int[] A) // solution in class Q(n^2log(n))
    {
        int cnt = 0;
        mergeSort4108056052(A);
        int len = A.length;
        for (int i = 0; i < len; i++)
        {
            for (int j = i + 1; j < len; j++)
            {
                if (this.rank4108056052(A, -A[i] - A[j]) > j)
                {
                    cnt++;
                }
            }
        }
        return cnt;
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

    public int rank4108056052(int[] A, int key)
    {
        int low = 0, high = A.length - 1;

        int mid, midValue;
        while (low <= high)
        {
            mid = low + (high - low) / 2;
            midValue = A[mid];
            if (key < midValue)
            {
                high = mid - 1;
            }
            else if (key > midValue)
            {
                low = mid + 1;
            }
            else
            {
                return mid;
            }
        }
        return -1; // no value
    }
}