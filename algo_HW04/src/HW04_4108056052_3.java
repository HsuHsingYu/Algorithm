public class HW04_4108056052_3 extends One_0k_rock
{
    @Override
    public boolean[] one0k(String[] str)
    {
        boolean[] ans = new boolean[str.length];
        int i = -1;
        while (++i < str.length)
        {
            String s = str[i];
            if (s.length() % 2 == 0)
            {
                int left = s.length() / 2 - 1, right = s.length() / 2;
                ans[i] = true;
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