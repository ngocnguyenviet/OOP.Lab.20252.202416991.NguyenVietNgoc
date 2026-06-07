package hust.soict.hedspi.garbage;

import java.nio.file.Files;
import java.nio.file.Paths;

public class NoGarbage {
    public static void main(String[] args) throws Exception {
        String filename = "test.exe";
        byte[] inputBytes;
        long startTime, endTime;

        inputBytes = Files.readAllBytes(Paths.get(filename));

        startTime = System.currentTimeMillis();

        StringBuilder outputString = new StringBuilder();
        for (byte b : inputBytes) {
            outputString.append((char) b);
        }

        endTime = System.currentTimeMillis();

        System.out.println(endTime - startTime);
    }
}