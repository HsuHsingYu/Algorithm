public class HW07_4108056052_5 extends Buy_Phone
{
    static int[][] array;
    final static public int[][] tmp = new int[10000][2];
    final static public int[] screen = new int[10000];  // x
    final static public int[] performance = new int[10000]; // y
    static int[][] ans;
    static int len, maxy, lim;

    @Override
    public int[][] bestPhone(int[][] inputArr)
    {
        array = inputArr;
        len = array.length;
        int end = len - 1;
        sort(array, 0, end);

        screen[end] = array[end][0];
        maxy = performance[end] = array[end][1];

        int x = end - 1, i = x + 1;
        while (--i > -1)
        {
            if (array[i][1] > maxy)
            {
                screen[x] = array[i][0];
                performance[x--] = maxy = array[i][1];
            }
        }

        ans = new int[len - x - 1][2];
        x++;
        lim = len - x;
        i = -1;
        while (++i < lim)
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

        int i = lo, j = mid + 1, k = lo - 1, end = hi + 1;
        while (++k < end)
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