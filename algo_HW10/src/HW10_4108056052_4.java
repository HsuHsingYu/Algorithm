public class HW10_4108056052_4 extends StringSort
{
    int R = 256;

    @Override
    public String[] checkString(String[] array)
    {
        String[] aux = new String[array.length];
        sort(array, aux, 0, array.length - 1, 0);
        return array;
    }

    private void sort(String[] a, String[] aux, int lo, int hi, int d)
    {
        if (hi <= lo) return;
        int[] count = new int[R + 2];
        for (int i = lo; i <= hi; i++)
        {
            count[charAt(a[i], d) + 2]++;
        }

        for (int r = 0; r < R + 1; r++)
            count[r + 1] += count[r];
        for (int i = lo; i <= hi; i++)
            aux[count[charAt(a[i], d) + 1]++] = a[i];
        for (int i = lo; i <= hi; i++)
            a[i] = aux[i - lo];

        for (int r = 0; r < R; r++)
            sort(a, aux, lo + count[r], lo + count[r + 1] - 1, d + 1);
    }

    private static int charAt(String s, int d)
    {
        if (d < s.length()) return s.charAt(d);
        else return -1;
    }

}
