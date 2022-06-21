import java.io.*;

public class TestDataGenerator {

    // 每 ctrl+F11 就產生一筆新測資
    public static void main(String[] args) {
        TestDataGenerator ra = new TestDataGenerator();
        ra.createData(5, 10);	// 第一個參數是陣列長度，第二個測資是數值範圍(-n~n)
    }

    // 產生新測資
    public static void createData(int size, int maxlen)
    {

        try
        {
            FileWriter fw = new FileWriter("testData.txt");
            BufferedWriter bfw = new BufferedWriter(fw);

            bfw.write(String.valueOf(size));
            bfw.newLine();

            for(int i=0; i<size; i++)
            {
                int len = (int) ((Math.random()*5*2)-5);
                String str = "{";
                for (int j = 0; j < len; j++)
                {
                    str += (int)(Math.random()*maxlen*2)-maxlen;
                    str += ",";
                }
                str += (int)(Math.random()*maxlen*2)-maxlen;
                str += "},";

                bfw.write(str);
            }

            bfw.close();
        }
        catch(IOException e) {
            System.out.println(e);
        }
        System.out.println("createData complete!");
        System.out.println("size = "+size+", maxlen = "+maxlen);
    }
}