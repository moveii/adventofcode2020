package at.moveii.days;

import java.util.stream.Stream;

public abstract class Day<L> {

    private final L handledInput;

    protected Day(Stream<String> input) {
        this.handledInput = handleInput(input);
    }

    protected abstract L handleInput(Stream<String> input);

    public L getHandledInput() {
        return handledInput;
    }
}
