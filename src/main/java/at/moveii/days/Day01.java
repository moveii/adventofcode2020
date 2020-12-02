package at.moveii.days;

import at.moveii.utils.Utils;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day01 extends Day<LinkedList<Integer>> {

    public Day01(Stream<String> input, int expectedResult) {
        super(input);

        Integer[] integersTwo = findSum(getHandledInput(), expectedResult, false);
        Integer[] integersThree = findSum(getHandledInput(), expectedResult, true);

        System.out.printf("The calculated result with two numbers is %s. \n", Utils.multiplyAll(integersTwo));
        System.out.printf("The calculated result with three numbers is %s. \n", Utils.multiplyAll(integersThree));
    }

    private Integer[] findSum(LinkedList<Integer> integers, int expectedResult, boolean extraCycle) {
        HashSet<Integer> hashSet = new HashSet<>(integers);

        for (int i = 0; i < integers.size(); i++) {
            int firstNumber = integers.get(i);
            for (int j = i + 1; j < integers.size(); j++) {
                int extraNumber = extraCycle ? integers.get(j) : 0;
                int toFind = expectedResult - firstNumber - extraNumber;

                if (hashSet.contains(toFind)) {
                    return extraCycle ? new Integer[]{firstNumber, extraNumber, toFind} : new Integer[]{firstNumber, toFind};
                } else if (!extraCycle) {
                    break;
                }
            }
        }

        throw new RuntimeException("No numbers add up to " + expectedResult);
    }

    @Override
    protected LinkedList<Integer> handleInput(Stream<String> input) {
        return input
                .map(Integer::valueOf)
                .collect(Collectors.toCollection(LinkedList::new));
    }
}
