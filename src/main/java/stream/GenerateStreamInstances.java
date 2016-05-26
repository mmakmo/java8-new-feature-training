package stream;

import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by mmakmo on 5/26/16.
 */
public class GenerateStreamInstances {

    // Stream Objects
    Stream<String> _stringStreamValue;
    IntStream _intStreamValue;

    public GenerateStreamInstances() {
    }

    /**
     * Use empty() method.<br>
     *     使いみち: Streamを初期化する
     */
    private void useEmptyMethod() {
        // Use empty() method
        _stringStreamValue = Stream.empty();
        _intStreamValue = IntStream.empty();
    }

    /**
     * use of() method.<br>
     *     使いみち: Streamの初期値を設定する
     */
    private void useOfMethod() {
        // Set Stream Default Values
        _stringStreamValue = Stream.of("first", "second");
        _intStreamValue = IntStream.of(123, 456);
    }

    /**
     * Use builder() method.<br>
     *     使いみち: 1 liner で初期値を用意する
     */
    private void useBuilderMethod() {
        Stream.Builder<String> stringStreamBuilder = Stream.builder();
        _stringStreamValue = stringStreamBuilder.add("first").add("second").add("third").build();

        _intStreamValue = IntStream.builder().add(100).add(200).add(300).build();
    }

}
