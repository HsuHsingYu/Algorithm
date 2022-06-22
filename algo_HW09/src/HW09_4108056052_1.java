public class HW09_4108056052_1 extends LSD
{
    static class Node
    {
        int key;
        Node next;

        public Node(int key)
        {
            this.key = key;
            this.next = null;
        }
    }

    @Override
    public int Distance(int[][] array)
    {
        Node node, rear, front;
        int len = array.length, i = 0, V = 12000;
        boolean[] mark;

        while (++i < len)
        {
            if (array[i][1] > V) V = array[i][1];
            if (array[i][0] > V) V = array[i][0];
        }
        ++V;
        Node[] adj = new Node[V];

        i = 0;
        while (++i < len)
        {
            node = new Node(array[i][1]);
            node.next = adj[array[i][0]];
            adj[array[i][0]] = node;
            node = new Node(array[i][0]);
            node.next = adj[array[i][1]];
            adj[array[i][1]] = node;
        }

        int v = array[0][1], next;
        front = new Node(v);
        rear = front;
        mark = new boolean[V];
        while (front != null)
        {
            v = front.key;
            mark[v] = true;
            node = adj[v];
            while (node != null)
            {
                next = node.key;
                if (!mark[next])
                {
                    rear.next = new Node(next);
                    rear = rear.next;
                    mark[next] = true;
                }
                node = node.next;
            }
            front = front.next;
        }
        mark = new boolean[V];
        int[] dis = new int[V];
        front = new Node(v);
        rear = front;
        while (front != null)
        {
            v = front.key;
            mark[v] = true;
            node = adj[v];
            while (node != null)
            {
                next = node.key;
                if (!mark[next])
                {
                    rear.next = new Node(next);
                    rear = rear.next;
                    mark[next] = true;
                    dis[next] = dis[v] + 1;
                }
                node = node.next;
            }
            front = front.next;
        }
        return dis[v] + 4;
    }
}
