package stream;

import java.util.Queue;
import java.util.ArrayDeque;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * Stream Instance生成例.
 */
public class GenerateStreamInstances {

    // Stream Objects
    Stream<String> _stringStreamValue;
    IntStream _intStreamValue;
    LongStream _longStreamValue;

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
     * Use of() method.<br>
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

    /**
     * Use iterate() method.<br>
     *     前の値を使って新しい値を生成し、streamに追加していく.
     *     つかいみち: なんだろう?
     */
    private void useIterateMethod() {
        _stringStreamValue = Stream.iterate("a", charactor -> charactor + charactor).limit(4);

        _intStreamValue = IntStream.iterate(1, number -> number * 2).limit(4);
    }

    /**
     * Use generate() method.<br>
     *     使いみち: 何らかのMethodで生成した値をStream要素に入れる
     */
    private void useGenerateMethod() {
        _stringStreamValue = Stream.generate(() -> "item").limit(3);

        NextPowerInt nextPowerInt = new NextPowerInt();
        _intStreamValue = IntStream.generate(() -> nextPowerInt.getNextValue()).limit(3);
    }

    /**
     * IntStream.generate() method 動作確認用のべき乗生成クラス.
     */
    class NextPowerInt {
        private int _value;

        public NextPowerInt() {
            _value = 1;
        }

        public int getNextValue() {
            int returnValue = (int)Math.pow(_value, 2);
            _value++;
            return returnValue;
        }
    }

    /**
     * Use concat() method.<br>
     *     使いみち: 2つのStreamをつなげて1つにする
     */
    private void useConcatMethod() {
        Stream<String> firstStream = Stream.of("first", "second");
        Stream<String> secondStream = Stream.of("third", "fourth");
        _stringStreamValue = Stream.concat(firstStream, secondStream);

        IntStream firstIntStream = IntStream.of(100, 200);
        IntStream secondIntStream = IntStream.of(300, 400);
        _intStreamValue = IntStream.concat(firstIntStream, secondIntStream);
    }

    /**
     * Use range() method.<br>
     *     使いみち: index範囲を指定したIntStreamを用意する(至数は含まず)
     */
    private void useRangeMethod() {
        _intStreamValue = IntStream.range(0, 3);
    }

    /**
     * Use rangeClosed() method.<br>
     *     使いみち: index範囲を指定したIntStreamを用意する(至数を含む)
     */
    private void useRangeClosedMethod() {
        _longStreamValue = LongStream.rangeClosed(1, 3);
    }

    /**
     * Use stream() method.<br>
     *     使いみち: コレクションをStreamで処理したい場合に使う
     */
    private void useStreamMethod() {
        Queue<String> queue = new ArrayDeque<String>();
        queue.offer("first");
        queue.offer("second");
        _stringStreamValue = queue.stream();
    }
}
