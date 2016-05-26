package stream

import spock.lang.Specification

/**
 * Created by mmakmo on 5/26/16.
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

    def 'builder() method: Stream.builder() で生成したobjectには、"first", "second", "third" の3つが含まれる'() {
        setup:
        sut.useBuilderMethod()

        expect:
        (String[])sut._stringStreamValue.toArray() == ["first", "second", "third"]
    }

    def 'builder() method: IntStream.builder() で生成したobjectには、100, 200, 300 の3つが含まれる'() {
        setup:
        sut.useBuilderMethod()

        expect:
        (int[])sut._intStreamValue.toArray() == [100, 200, 300]
    }

}
