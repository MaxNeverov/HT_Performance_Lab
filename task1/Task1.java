package task1;

import java.util.ArrayList;
import java.util.List;

public class Task1 {

    public static void main(String[] args) {
        Task1 task1 = new Task1();
        int n = Integer.valueOf(args[0]);
        int m = Integer.valueOf(args[1]);

        String res = task1.run(n, m);
        System.out.println(res);
    }

    public String run(int n, int m) {
        StringBuilder sb = new StringBuilder();
        List<Integer> buf = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            buf.add(0);
        }

        fillArray(buf, 1, n, m);
        sb.append("1");
        while (true) {
            int lastVal = buf.get(buf.size() - 1);
            if (lastVal == 1) {
                break;
            }
            fillArray(buf, lastVal, n, m);
            sb.append(buf.get(0));
        }
        return sb.toString();
    }

    private void fillArray(List<Integer> buf, int fromVal, int n, int m) {
        for (int idx = 0, val = fromVal; idx < m; idx++, val++) {
            if (val > n) {
                val = 1;
            }
            buf.set(idx, val);
        }
    }
}
