package task4;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Task4 {

    public static void main(String[] args) throws IOException {
        Task4 task4 = new Task4();
        String numsFilePath = args[0];
        int count = task4.run(numsFilePath);
        System.out.println(count);
    }

    private int run(String numsFilePath) throws IOException {
        List<Integer> numsArray = getNumsArray(numsFilePath);
        boolean even = isEven(numsArray);
        Integer target = getMedian(even, numsArray);
        int count = getMinStep(numsArray, target);
        return count;
    }

    private List<Integer> getNumsArray(String numsFilePath) throws IOException {
        List<String> numsFile = Files.readAllLines(Paths.get(numsFilePath));
        ArrayList<Integer> integers = new ArrayList<>();
        for (String s : numsFile) {
            int num = Integer.parseInt(s);
            integers.add(num);
        }
        integers.sort(Integer::compareTo);
        return integers;
    }

    private boolean isEven(List<Integer> integers) {
        return integers.size() % 2 == 0;
    }

    private Integer getMedian(boolean isEven, List<Integer> integers) {
        if (isEven) {
            Integer target1 = integers.get(integers.size() / 2);
            Integer target2 = integers.get(integers.size() / 2 - 1);
            return (target1 + target2) / 2;
        }
        return integers.get(integers.size() / 2);
    }

    private int getMinStep(List<Integer> integers, Integer target) {
        int count = 0;
        for (Integer integer : integers) {
            while (integer != target) {
                if (integer > target) {
                    integer--;
                    count++;
                } else {
                    integer++;
                    count++;
                }
            }
        }
        return count;
    }
}
