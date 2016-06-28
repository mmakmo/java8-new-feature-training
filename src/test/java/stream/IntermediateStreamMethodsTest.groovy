package stream

import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll


/**
 * Streamの中間処理メソッドを使用した例のUnit Test.<br>
 *     Spock使用例でもある。
 */
class IntermediateStreamMethodsTest extends Specification {

    IntermediateStreamMethods sut

    enum Rank {
        a(2), b(3), c(6);

        private int rank;

        public Rank(int rank) { this.rank = rank }

        public getRank() { return rank; }
    }

    @Shared
    String[] types1
    @Shared
    Integer[] points1
    @Shared
    String[] names1

    @Shared
    String[] types2
    @Shared
    Integer[] points2
    @Shared
    String[] names2

    @Shared
    String[] typesB1
    @Shared
    int[] gradesB1
    @Shared
    int[] pointsB1

    def setupSpec() {
        types1 = ["a", "b", "c", "c", "b", "a"]
        points1 = [60, 20, 30, 10, 40, 50]
        names1 = ["sixth", "second", "third", "first", "fourth", "fifth"]

        types2 = ["a", "b", "c", "c", "b", "a"]
        points2 = [60, 20, 30, 10, 40, 50]
        names2 = ["sixth", "second", "third", "first", "fourth", "fifth"]

        typesB1 = ["a", "c", "b", "a", "b", "c", "a", "b", "c"]
        gradesB1 = [3, 6, 4, 2, 1, 9, 4, 1, 9, 2, 3, 6]
        pointsB1 = [12, 16, 19, 12, 13, 11, 23, 22, 21, 24, 25, 22]

    }

    void setup() {
        sut = new IntermediateStreamMethods();
    }

    def 'filter(), sorted, map(), collect(): 型b,cでフィルタリングされ、ポイントの昇順ソートした結果得られる名前リストには、"first", "second", "third", "fourth"の4つが含まれる'() {
        setup:
        String[] filterTypes = ["a", "b", "c", "c", "b", "a"]
        Integer[] points = [60, 20, 30, 10, 40, 50]
        String[] names = ["sixth", "second", "third", "first", "fourth", "fifth"]

        expect:
        sut.getPointOrderdNameList(filterTypes, points, names) == ["first", "second", "third", "fourth"]
    }

    def 'filter(), sorted, map(), collect(): 上の例で失敗するテストケース'() {
        setup:
        String[] filterTypes = ["a", "b", "c", "c", "b", "a"]
        Integer[] points = [60, 20, 30, 10, 40, 50]
        String[] names = ["sixth", "second", "third", "first", "fourth", "fifth"]

        expect:
        sut.getPointOrderdNameList(filterTypes, points, names) == ["fourth", "third", "second", "first"]  // 失敗するケース
    }

    // 上の例をparameterized testにしたもの
    @Unroll
    def 'filter(), sorted, map(), collect() parameterized test: 型が#filterTypesでポイントが#points、名前が#namesの時、得られる名前リストは#resultsである'() {
        expect:
        sut.getPointOrderdNameList(filterTypes, points, names) == results

        where:
        filterTypes | points  | names  || results
        types1      | points1 | names1 || ["first", "second", "third", "fourth"]  // 成功するケース
        types2      | points2 | names2 || ["fourth", "third", "second", "first"]  // 失敗するケース
    }

    @Unroll
    def 'SQL の group by のようなものテストまだできていないので失敗する'() {
        expect:
        sut.getTypeGradePointList(types, grades, points) == results

        where:
        types   | grades   | points   || results
        typesB1 | gradesB1 | pointsB1 || [1,2,3]
    }

}