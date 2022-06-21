public class HW07_4108056052_4 extends Buy_Phone
{

    static int[][] array;
    final static public int[][] temp = new int[10000][2];
    final static public int[] screen = new int[10000];
    final static public int[] performance = new int[10000];
    static int[][] ans;
    static int max;
    static int arrlen;

    public static void main(String[] args)
    {
        HW07_4108056052_4 test = new HW07_4108056052_4();
		int[][] inputArr = {{1,1},{2,4},{2,10},{5,4},{4,8},{5,5},{8,4},{10,2},{10,1}};
//        int[][] inputArr = {{1, 10}, {2, 3}, {2, 5}, {3, 1}, {4, 8}, {5, 6}, {5, 8}, {7, 2}, {10, 1}, {10, 2}};

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
        arrlen = array.length;

        sort(array, 0, arrlen - 1);

        screen[arrlen - 1] = array[arrlen - 1][0];
        max = performance[arrlen - 1] = array[arrlen - 1][1];

        int top = arrlen - 2;
        for (int i = top; i > -1; i--)
        {
            if (array[i][1] > max)
            {
                screen[top] = array[i][0];
                performance[top--] = max = array[i][1];
            }
        }

        ans = new int[arrlen - top - 1][2];
        top++;
        for (int i = 0, lim = arrlen - top; i < lim; i++)
        {
            ans[i][0] = screen[top];
            ans[i][1] = performance[top++];
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
        for (int k = lo; k < hi + 1; k++)
            temp[k] = array[k];

        int i = lo, j = mid + 1;
        for (int k = lo; k < hi + 1; k++)
        {
            if (i > mid)
            {
                array[k] = temp[j++];
            }
            else if (j > hi)
            {
                array[k] = temp[i++];
            }
            else if (temp[i][0] > temp[j][0])
            {
                array[k] = temp[j++];
            }
            else if (temp[i][0] < temp[j][0])
            {
                array[k] = temp[i++];
            }
            else
            {
                if (temp[i][1] > temp[j][1])
                {
                    array[k] = temp[j++];
                }
                else
                {
                    array[k] = temp[i++];
                }
            }
        }
    }

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

}