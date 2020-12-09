package at.moveii.days;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day03 extends Day<int[][]> {

    public Day03(Stream<String> input) {
        super(input);

        int trees = countTrees(3, 1);
        long treesMultiplied = countTrees(1, 2);

        for (int i = 1; i <= 7; i += 2) {
            treesMultiplied *= countTrees(i, 1);
        }

        System.out.printf("You would encounter %s trees\n", trees);
        System.out.printf("You would encounter %s trees with all variants multiplied", treesMultiplied);
    }

    @Override
    protected int[][] handleInput(Stream<String> input) {
        return input
                .map(String::chars)
                .map(IntStream::toArray)
                .toArray(int[][]::new);
    }

    private int countTrees(int right, int down) {
        final int[][] handledInput = getHandledInput();
        int trees = 0;

        for (int i = 1; i * down < handledInput.length; i++) {
            if ("#".charAt(0) == handledInput[i * down][right * i % handledInput[i * down].length]) trees++;
        }

        return trees;
    }
}
