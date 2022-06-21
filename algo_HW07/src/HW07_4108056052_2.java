public class HW07_4108056052_2 extends Buy_Phone
{
    static int[][] array;
    final static public int[][] tmp = new int[10000][2];
    final static public int[][] result = new int[10000][2];
    static int[][] ans;
    static int len, maxy;

    @Override
    public int[][] bestPhone(int[][] inputArr)
    {
        array = inputArr;
        len = array.length;
        sort(array, 0, len - 1);
        for (int i = 0; i < len; i++)
        {
            for (int j = 0; j < 2; j++)
            {
                System.out.print(array[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }

        // find from end
        result[len - 1] = array[len - 1];
        int x = len - 2;
        maxy = array[len - 1][1];

        for (int i = x; i > -1; i--)
        {
            if (array[i][1] > maxy)
            {
                maxy = array[i][1];
                result[x--] = array[i];
            }
        }

        len = len - x - 1;
        ans = new int[len][2];
        System.arraycopy(result, x + 1, ans, 0, len);
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