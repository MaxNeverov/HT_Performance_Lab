package task3;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Task3 {

    public static void main(String[] args) throws IOException {
        Task3 task3 = new Task3();
        String valuesPath = args[0];
        String testsPath = args[1];
        String reportPath = args[2];

        task3.run(testsPath, valuesPath, reportPath);
    }

    private void run(String testsPath, String valuesPath, String reportPath) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        BufferedReader bufReaderTests = new BufferedReader(new FileReader(testsPath));
        BufferedReader bufReaderValues = new BufferedReader(new FileReader(valuesPath));
        Map<Integer, String> map = new HashMap<>();

        ValuesDto valuesDto = gson.fromJson(bufReaderValues, ValuesDto.class);
        for (int i = 0; i < valuesDto.values.size(); i++) {
            ValueDto value = valuesDto.values.get(i);
            map.put(value.getId(), value.getValue());
        }

        TestsDto testsDto = gson.fromJson(bufReaderTests, TestsDto.class);
        setNestingValue(testsDto.tests, map);
        Files.writeString(Paths.get(reportPath), gson.toJson(testsDto));
    }

    private void setNestingValue(List<Test> tests, Map<Integer, String> map) {
        for (int j = 0; j < tests.size(); j++) {
            Test test = tests.get(j);
            int id = test.getId();
            for (Integer integer : map.keySet()) {
                if (id == integer) {
                    test.setValue(map.get(integer));
                    break;
                }
            }
            if (tests.get(j).getValues() != null) {
                List<Test> tests2 = test.getValues();
                setNestingValue(tests2, map);
            }
        }
    }

    public static class TestsDto {
        public List<Test> tests;
    }

    public static class Test {
        public int id;
        public String title;
        public String value;
        public List<Test> values;

        public String getValue() {
            return value;
        }

        public int getId() {
            return id;
        }

        public List<Test> getValues() {
            return values;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    public static class ValuesDto {

        public List<ValueDto> values;
    }

    public static class ValueDto {

        public int id;

        public String value;

        public int getId() {
            return id;
        }

        public String getValue() {
            return value;
        }
    }
}