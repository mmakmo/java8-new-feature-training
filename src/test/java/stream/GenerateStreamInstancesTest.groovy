package stream

import spock.lang.Specification

/**
 * Stream Instance生成例のUnit Test.<br>
 *     Spock使用例でもある。
 */
class GenerateStreamInstancesTest extends Specification {

    def GenerateStreamInstances sut

    void setup() {
        sut = new GenerateStreamInstances()
    }

    void cleanup() {

    }

    def "empty() method: Stream.empty() の値はnullでなく、かつ要素数がゼロ"() {
        setup:
        sut.useEmptyMethod()

        expect:
        sut._stringStreamValue != null
        sut._stringStreamValue.count() == 0
    }

    def "empty() method: IntStream.empty() の値はnullでなく、かつ要素数がゼロ"() {
        setup:
        sut.useEmptyMethod()

        expect:
        sut._intStreamValue != null
        sut._intStreamValue.count() == 0
    }

    def 'of() method: Stream.of("first", "second") には "first" と "second" の2つが含まれる'() {
        setup:
        sut.useOfMethod()

        expect:
        (String[])sut._stringStreamValue.toArray() == ["first", "second"]
    }

    def "of() method: IntStream.of(123, 456) には 123 と 456 の2つが含まれる"() {
        setup:
        sut.useOfMethod()

        expect:
        (int[])sut._intStreamValue.toArray() == [123, 456]
    }

    def 'builder() method: StreamBuilder.add("first").add("second") で生成したobjectには、"first", "second", "third" の3つが含まれる'() {
        setup:
        sut.useBuilderMethod()

        expect:
        (String[])sut._stringStreamValue.toArray() == ["first", "second", "third"]
    }

    def 'builder() method: IntStream.builder().add(100).add(200).add(300) で生成したobjectには、100, 200, 300 の3つが含まれる'() {
        setup:
        sut.useBuilderMethod()

        expect:
        (int[])sut._intStreamValue.toArray() == [100, 200, 300]
    }

    def 'iterate() method: Stream.iterate("a", c -> c + c).limit(4) で生成したobjectには、"a", "aa", "aaaa", "aaaaaaaa" の4つが含まれる'() {
        setup:
        sut.useIterateMethod()

        expect:
        (String[])sut._stringStreamValue.toArray() == ["a", "aa", "aaaa", "aaaaaaaa"]
    }

    def 'iterate() method: IntStream.iterate(1, n -> n * 2).limit(4) で生成したobjectには、1, 2, 4, 8 の4つが含まれる'() {
        setup:
        sut.useIterateMethod()

        expect:
        (int[])sut._intStreamValue.toArray() == [1, 2, 4, 8]
    }

    def 'generate() method: Stream.generate(() -> "item").limit(3) で生成したobjectには、 "item", "item", "item" の3つが含まれる'() {
        setup:
        sut.useGenerateMethod();

        expect:
        (String[])sut._stringStreamValue.toArray() == ["item", "item", "item"]
    }

    def 'generate() method: IntStream.generate()で 1^2, 2^2,3^3 を生成したobjectには、1, 4, 9 の3つが含まれる'() {
        setup:
        sut.useGenerateMethod();

        expect:
        (int[])sut._intStreamValue.toArray() == [1, 4, 9]
    }

    def 'concat() method: Stream.concat()で ("first", "second")と("third", "fourth")を連結したObjectには "first", "second", "third", "fourth" の4つが含まれる'() {
        setup:
        sut.useConcatMethod();

        expect:
        (String[])sut._stringStreamValue.toArray() == ["first", "second", "third", "fourth"]
    }

    def 'concat() method: IntStream.concat()で(100, 200)と(300, 400)を生成したobjectには、100, 200, 300, 400 の4つが含まれる'() {
        setup:
        sut.useConcatMethod();

        expect:
        (int[])sut._intStreamValue.toArray() == [100, 200, 300, 400]
    }

    def 'range() method: IntStream.range(0, 3)で生成したobjectには、0, 1, 2 の3つが含まれる'() {
        setup:
        sut.useRangeMethod();

        expect:
        (int[])sut._intStreamValue.toArray() == [0, 1, 2]
    }

    def 'rangeClosed() method: LongStream.rangeClosed(1, 3)で生成したobjectには、1, 2, 3 の3つが含まれる'() {
        setup:
        sut.useRangeClosedMethod();

        expect:
        (long[])sut._longStreamValue.toArray() == [1, 2, 3]
    }

    def 'stream() method: queue ["first", "second"]をstreamに変換できること'() {
        setup:
        sut.useStreamMethod();

        expect:
        (String[])sut._stringStreamValue.toArray() == ["first", "second"]
    }

}
