public class HW04_4108056052_2 extends One_0k_rock
{
    @Override
    public boolean[] one0k(String[] str)
    {
        int len = str.length;
        boolean[] ans = new boolean[len];
        String s;
        int i = -1, left, right;
        while (++i < len)
        {
            s = str[i];
            int s_max = s.length();
            ans[i] = true;
            if ((s_max % 2) == 0)
            {
                left = s_max / 2 - 1;
                right = left + 1;
                while (left > -1)
                {
                    if (s.charAt(left) == '1' || s.charAt(right) == '0')
                    {
                        ans[i] = false;
                        break;
                    }
                    left--;
                    right++;
                }
            }
            else
            {
                ans[i] = false;
            }
        }
        return ans;
    }
}
