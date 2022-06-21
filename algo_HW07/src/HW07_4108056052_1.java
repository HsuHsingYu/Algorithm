public class HW07_4108056052_1 extends Buy_Phone {

    static int[][] array;
    int[] cnt = new int[100000];
    final static public int[] screen = new int[10000];
    final static public int[] performance = new int[10000];
    static int[][] ans;
    static int max;
    static int len;

    @Override
    public int[][] bestPhone(int[][] inputArr) {
        array = inputArr;
        len = array.length;

        int right = 0, left = array[0][0];
        int i = -1;
        while(++i < len) {
            if(array[i][1] > cnt[array[i][0]]) {
                cnt[array[i][0]] = array[i][1];
            }
            if(array[i][0] > right) {
                right = array[i][0];
            }
            if(array[i][0] < left) {
                left = array[i][0];
            }
        }

        screen[0] = right;
        max = performance[0] = cnt[right];

        int top = 1;
        i = right;
        for(;i > left-1; i--) {
            if(cnt[i] > max) {
                screen[top] = i;
                max = performance[top++] = cnt[i];
            }
        }

        ans = new int[top][2];
        top--;
        i = -1;
        int lim = top+1;
        while (++i < lim)
        {
            ans[i][0] = screen[top];
            ans[i][1] = performance[top--];
        }
        return ans;
    }
}