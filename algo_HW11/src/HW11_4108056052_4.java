public class HW11_4108056052_4 extends SunMoonLake
{
    int[] dx = {1, -1, 0, 0};
    int[] dy = {0, 0, 1, -1};
    boolean[][] visit = new boolean[100][100];
    PQ q = new PQ(10000);

    @Override
    public int count_water_volume(int[][] heights)
    {
        int row = heights.length;
        if (row == 0)
        {
            return 0;
        }
        int col = heights[0].length;

        int i = -1;
        while (++i < row)
        {
            q.put(new Cell(i, 0, heights[i][0]));
            q.put(new Cell(i, col - 1, heights[i][col - 1]));
            visit[i][0] = true;
            visit[i][col - 1] = true;
        }

        i = -1;
        while (++i < col)
        {
            q.put(new Cell(0, i, heights[0][i]));
            q.put(new Cell(row - 1, i, heights[row - 1][i]));
            visit[0][i] = true;
            visit[row - 1][i] = true;
        }

        int ans = 0;
        while (!q.empty())
        {
            Cell now = q.get();
            i = -1;
            while (++i < 4)
            {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                if (0 <= nx && nx < row && 0 <= ny && ny < col && !visit[nx][ny])
                {
                    visit[nx][ny] = true;
                    q.put(new Cell(nx, ny, Math.max(now.h, heights[nx][ny])));
                    ans += Math.max(0, now.h - heights[nx][ny]);
                }
            }
        }

        return ans;
    }

    private static class Cell
    {
        int x;
        int y;
        int h;

        public Cell(int x, int y, int h)
        {
            this.x = x;
            this.y = y;
            this.h = h;
        }
    }

    private static class PQ
    {
        private final Cell[] heap;
        private int heapSize, capacity;

        public PQ(int capacity)
        {
            this.capacity = capacity + 1;
            heap = new Cell[this.capacity];
            heapSize = 0;
        }

        public boolean empty()
        {
            return heapSize == 0;
        }

        public void put(Cell newnode)
        {
            heap[++heapSize] = newnode;
            int pos = heapSize;
            while (pos != 1 && newnode.h < heap[pos / 2].h)
            {
                heap[pos] = heap[pos / 2];
                pos /= 2;
            }
            heap[pos] = newnode;
        }

        public Cell get()
        {
            int parent, child;
            Cell item, temp;
            if (empty())
            {
                System.out.println("Heap is empty");
                return null;
            }

            item = heap[1];
            temp = heap[heapSize--];

            parent = 1;
            child = 2;
            while (child <= heapSize)
            {
                if (child < heapSize && heap[child].h > heap[child + 1].h)
                    child++;
                if (temp.h <= heap[child].h)
                    break;

                heap[parent] = heap[child];
                parent = child;
                child *= 2;
            }
            heap[parent] = temp;
            return item;
        }
    }
}