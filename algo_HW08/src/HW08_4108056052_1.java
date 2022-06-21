public class HW08_4108056052_1 extends Buy_Phone_v2
{
    static int[][] array;
    final static public int[][] tmp = new int[10000][6];
    final static public int[] d0 = new int[10000];
    final static public int[] d1 = new int[10000];
    final static public int[] d2 = new int[10000];
    final static public int[] d3 = new int[10000];
    final static public int[] d4 = new int[10000];
    final static public int[] d5 = new int[10000];
    static int[][] ans;
    static int len;

    @Override
    public int[][] bestPhone(int[][] inputArr)
    {
        array = inputArr;
        len = array.length;
        int end = len - 1;

        sort(array, 0, end);

        d0[0] = array[end][0];
        d1[0] = array[end][1];
        d2[0] = array[end][2];
        d3[0] = array[end][3];
        d4[0] = array[end][4];
        d5[0] = array[end][5];

        int top = 1;
        int j;
        for (int i = len - 2; i > -1; i--)
        {
            boolean isAns = true;
            j = top;
            while (--j > -1)
            {
                if (array[i][1] <= d1[j] && array[i][2] <= d2[j] && array[i][3] <= d3[j] && array[i][4] <= d4[j] && array[i][5] <= d5[j])
                {
                    isAns = false;
                    break;
                }
            }

            if (isAns)
            {
                d0[top] = array[i][0];
                d1[top] = array[i][1];
                d2[top] = array[i][2];
                d3[top] = array[i][3];
                d4[top] = array[i][4];
                d5[top] = array[i][5];
                top++;
            }
        }

        ans = new int[top][6];
        int lim = top, i = -1;
        while (++i < lim)
        {
            top--;
            ans[i][0] = d0[top];
            ans[i][1] = d1[top];
            ans[i][2] = d2[top];
            ans[i][3] = d3[top];
            ans[i][4] = d4[top];
            ans[i][5] = d5[top];
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
            tmp[k] = array[k];

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
                else if (tmp[i][1] < tmp[j][1])
                {
                    array[k] = tmp[i++];
                }
                else
                {
                    if (tmp[i][2] > tmp[j][2])
                    {
                        array[k] = tmp[j++];
                    }
                    else if (tmp[i][2] < tmp[j][2])
                    {
                        array[k] = tmp[i++];
                    }
                    else
                    {
                        if (tmp[i][3] > tmp[j][3])
                        {
                            array[k] = tmp[j++];
                        }
                        else if (tmp[i][3] < tmp[j][3])
                        {
                            array[k] = tmp[i++];
                        }
                        else
                        {
                            if (tmp[i][4] > tmp[j][4])
                            {
                                array[k] = tmp[j++];
                            }
                            else if (tmp[i][4] < tmp[j][4])
                            {
                                array[k] = tmp[i++];
                            }
                            else
                            {
                                if (tmp[i][5] > tmp[j][5])
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
            }
        }
    }
}