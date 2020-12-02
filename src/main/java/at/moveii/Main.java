package at.moveii;

import at.moveii.days.Day01;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class Main {

    public Main(String[] args) {
        final String day = args[0];
        final Stream<String> input = readContent(day);

        try {
            getAccordingMethod(day)
                    .invoke(this, input, args);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            throw new RuntimeException("The method \"callDay" + day + "\" hasn't been found");
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            throw new RuntimeException("The method \"callDay" + day + "\" can't be invoked");
        }
    }

    private Stream<String> readContent(String day) {
        try {
            Path path = Path
                    .of(Main.class.getResource("../../day" + day + ".txt").toURI())
                    .toAbsolutePath();

            return Files.lines(path);
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException("The following error occurred: " + e.getMessage());
        }
    }

    private Method getAccordingMethod(String day) throws NoSuchMethodException {
        return Main.class.getMethod("callDay" + day, Stream.class, String[].class);
    }

    @SuppressWarnings("unused")
    public void callDay01(Stream<String> input, String[] args) {
        int expectedSum = Integer.parseInt(args[1]);
        new Day01(input, expectedSum);
    }

    public static void main(String[] args) {
        new Main(args);
    }
}
