package Model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileOperations {
    public char[][] read2DArrayFromFile(String filePath) throws IOException {
        List<char[]> lines = new ArrayList<>();
        int maxWidth = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                maxWidth = Math.max(maxWidth, line.length());
                lines.add(line.toCharArray());
            }
        }
        int height = lines.size();
        char[][] canvas = new char[height][maxWidth];
        for (int i = 0; i < height; i++) {
            char[] lineChars = lines.get(i);
            System.arraycopy(lineChars, 0, canvas[i], 0, lineChars.length);
            for (int j = lineChars.length; j < maxWidth; j++) {
                canvas[i][j] = ' ';
            }
        }
        return canvas;
    }

    public void print2DArrayToFile(char[][] array, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (char[] row : array) {
                for (char ch : row) {
                    writer.write(ch);
                }
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
