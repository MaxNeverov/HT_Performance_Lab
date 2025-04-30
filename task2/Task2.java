package task2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Task2 {

    public static void main(String[] args) throws IOException {
        Task2 task2 = new Task2();
        String circleFilePath = args[0];
        String pointFilePath = args[1];

        task2.run(circleFilePath, pointFilePath);
    }

    private void run(String circleFilePath, String pointFilePath) throws IOException {
        List<String> circleAndRadius = Files.readAllLines(Paths.get(circleFilePath));
        String[] x0y0 = circleAndRadius.get(0).split(" ");
        float x0 = Float.valueOf(x0y0[0]);
        float y0 = Float.valueOf(x0y0[1]);
        float R = Float.valueOf(circleAndRadius.get(1));

        List<String> coordinates = Files.readAllLines(Paths.get(pointFilePath));
        for (int i = 0; i < coordinates.size() && i < 100; i++) {
            String[] xy = coordinates.get(i).split(" ");
            float x = Float.valueOf(xy[0]);
            float y = Float.valueOf(xy[1]);
            String s = calculatePointPosition(x, x0, y, y0, R);
            System.out.println(s);
        }
    }

    public String calculatePointPosition(double x, double x0, double y, double y0, double R) {
        if (Math.pow(x - x0, 2) + Math.pow(y - y0, 2) == Math.pow(R, 2)) {
            return "0";
        }
        if (Math.pow(x - x0, 2) + Math.pow(y - y0, 2) < Math.pow(R, 2)) {
            return "1";
        }
        return "2";
    }
}
