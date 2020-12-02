package at.moveii.utils;

import java.util.Arrays;

public class Utils {

    public static int multiplyAll(Integer[] integers) {
        return Arrays
                .stream(integers)
                .reduce((a, b) -> a * b)
                .orElseThrow(IllegalArgumentException::new);
    }
}
