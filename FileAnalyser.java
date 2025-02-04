import java.io.*;
import java.util.*;

public class FileAnalyser {
    private Set<String> keywords;

    public FileAnalyser(Set<String> keywords) {
        this.keywords = keywords;
    }

    public void analyzeLogFile(String inputFile, String outputFile) {
        Map<String, Integer> keywordCounts = new HashMap<>();
        for (String keyword : keywords) {
            keywordCounts.put(keyword, 0);
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                for (String keyword : keywords) {
                    if (line.contains(keyword)) {
                        keywordCounts.put(keyword, keywordCounts.get(keyword) + 1);
                    }
                }
            }
            for (Map.Entry<String, Integer> entry : keywordCounts.entrySet()) {
                writer.write(entry.getKey() + ": " + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Set<String> keywords = new HashSet<>(Arrays.asList("ERROR", "WARNING"));
        FileAnalyser analyzer = new FileAnalyser(keywords);
        analyzer.analyzeLogFile("input.log", "output.log");
    }
}