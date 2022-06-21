public class HW07_4108056052_3 extends Buy_Phone
{
    static int[][] array;
    final static public int[][] tmp = new int[10000][2];
    final static public int[] screen = new int[10000];  // x
    final static public int[] performance = new int[10000]; // y
    static int[][] ans;
    static int len, maxy;

    private static void show(int[][] a)
    {

        for (int[] ints : a)
        {
            System.out.printf("%3d", ints[0]);
        }
        System.out.println();

        for (int[] ints : a)
        {
            System.out.printf("%3d", ints[1]);
        }
        System.out.println();
    }

    public static void main(String[] args)
    {
        HW07_4108056052_3 test = new HW07_4108056052_3();
//		int[][] inputArr = {{1,1},{2,4},{2,10},{5,4},{4,8},{5,5},{8,4},{10,2},{10,1}};
        int[][] inputArr = {{1, 10}, {2, 3}, {2, 5}, {3, 1}, {4, 8}, {5, 6}, {5, 8}, {7, 2}, {10, 1}, {10, 2}};

        show(inputArr);

        System.out.println("case4:");
        Stopwatch stopwatch = new Stopwatch();
        test.bestPhone(inputArr);
        double time = stopwatch.elapsedTime();
        System.out.println("elapsed time " + time);

        show(ans);
    }

    @Override
    public int[][] bestPhone(int[][] inputArr)
    {
        array = inputArr;
        len = array.length;
        int end = len - 1;
        sort(array, 0, end);

        screen[end] = array[end][0];
        maxy = performance[end] = array[end][1];

        int x = end - 1;
        for (int i = x; i > -1; i--)
        {
            if (array[i][1] > maxy)
            {
                screen[x] = array[i][0];
                performance[x--] = maxy = array[i][1];
            }
        }

        ans = new int[len - x - 1][2];
        x++;
        int lim = len - x;
        for (int i = 0; i < lim; i++)
        {
            ans[i][0] = screen[x];
            ans[i][1] = performance[x++];
        }

        return ans;
    }

    private static void sort(int[][] array, int lo, int hi)
    {
        if (hi - lo < 1)
            return;
        int mid = (hi + lo) >> 1;
        sort(array, lo, mid);
        sort(array, mid + 1, hi);
        merge(array, lo, mid, hi);
    }

    private static void merge(int[][] array, int lo, int mid, int hi)
    {
        if (hi + 1 - lo >= 0) System.arraycopy(array, lo, tmp, lo, hi + 1 - lo);

        int i = lo, j = mid + 1;
        for (int k = lo; k < hi + 1; k++)
        {
            if (i > mid)
            {
                array[k] = tmp[j++];
            }
            else if (j > hi)
            {
                array[k] = tmp[i++];
            }
            else if (tmp[i][0] > tmp[j][0])
            {
                array[k] = tmp[j++];
            }
            else if (tmp[i][0] < tmp[j][0])
            {
                array[k] = tmp[i++];
            }
            else
            {
                if (tmp[i][1] > tmp[j][1])
                {
                    array[k] = tmp[j++];
                }
                else
                {
                    array[k] = tmp[i++];
                }
            }
        }
    }
}