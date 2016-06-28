package stream;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Streamの中間処理メソッドを使用した例.
 */
public class IntermediateStreamMethods {

    /**
     * 型でフィルタリング(bまたはc)して、ポイントを昇順にソートし、その順での名前だけのリストを取得する.<br>
     *     filter(), sorted, map(), collect() の4つを使用した例.
     * @param filterTypes
     * @param points
     * @param names
     * @return
     */
    public List<String> getPointOrderdNameList(String[] filterTypes, Integer[] points, String[] names) {
        return this.getGradeStream(filterTypes, points, names)
                // 型として設定されているうち、"b"または"c" のみを対象としてフィルタリング
                .filter(item -> item.getFilterType()== "b" || item.getFilterType() == "c")
                // ポイントの昇順でソート
                .sorted((item1, item2) -> item1.getPoint() - item2.getPoint())
                // ポイント昇順で並べ替えられた名前だけのStreamに変換
                .map(item -> item.getName())
                // StreamからListに変換
                .collect(Collectors.toList());
    }


    /**
     * 受け取った配列を使ってGradeのStreamを取得する.<br>
     *     例なので、配列長が異なったり、nullだったりは考慮しない.
     * @param filterTypes
     * @param points
     * @param names
     * @return
     */
    private Stream<Grade> getGradeStream(String[] filterTypes, Integer[] points, String[] names) {
        List<Grade> resultList = new ArrayList<Grade>();
        try {
            IntStream
                    .range(0, filterTypes.length)
                    .forEachOrdered(index -> resultList.add(new Grade(filterTypes[index], points[index], names[index])));
        } catch(Exception e) {
                e.printStackTrace();
            }
        return resultList.stream();
    }

    /**
     * 例で使用するインナークラス.<br>
     *
     */
    private class Grade {

        public Grade(String filterType, int point, String name) {
            this.filterType = filterType;
            this.point = point;
            this.name = name;
        }

        public String getFilterType() {
            return filterType;
        }

        public void setFilterType(String filterType) {
            this.filterType = filterType;
        }

        private String filterType;

        public int getPoint() {
            return point;
        }

        public void setPoint(int point) {
            this.point = point;
        }

        private int point;


        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        private String name;
    }

    /**
     * SQLの GROUP BY のようなものを実現しようとしたクラス
     * @param types
     * @param grades
     * @param points
     * @return
     */
    public List<TypeGradePoint> getTypeGradePointList(String[] types, int[] grades, int[] points) {
        List<TypeGradePoint> results = new ArrayList<TypeGradePoint>();
// 力尽きた。
//        results = getTypeGradePointStream(types, grades, points)
//                // (意味は無いがサンプルとして) タイプで並べ替え
//                .sorted(Comparator.comparing(TypeGradePoint::getType))
//                // タイプ、グレードでグルーピング
//                .collect(Collectors.groupingBy(TypeGradePoint::getType, Collectors.groupingBy(TypeGradePoint::getGrade)))
//                //
//                .entrySet().stream()
//                // タイプでグルーピングしたものをStreamに変換
//                .map(item -> item.getValue()
//                        .entrySet().stream()
//                        //MapになっているグレードをStreamに変換
//                        .map(i -> i.getValue().stream()
//                                .reduce((f1, f2) -> new TypeGradePoint(f1.getType(), f1.getGrade(), f1.getPoint() + f2.getPoint()))))
//                .reduce((f1, f2) -> new T
//                .map(f -> f.collect(Collectors.toList()))
//                .
        return results;
    }

    private class GradePointList {
        public int grade;
        public int point;
    }

    /**
     *
     * @param types
     * @param grade
     * @param point
     * @return
     */
    private Stream<TypeGradePoint> getTypeGradePointStream(String[] types, int[] grade, int[] point) {
        List<TypeGradePoint> resultList = new ArrayList<TypeGradePoint>();
        try {
            IntStream
                    .range(0, types.length)
                    .forEachOrdered(index -> resultList.add(new TypeGradePoint(types[index], grade[index], point[index])));
        } catch(Exception e) {
            e.printStackTrace();
        }
        return resultList.stream();
    }


    /**
     *
     */
    private class TypeGradePoint {
        public TypeGradePoint(String type, int grade, int point) {
            this.type = type;
            this.grade = grade;
            this.point = point;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getGrade() {
            return grade;
        }

        public void setGrade(int grade) {
            this.grade = grade;
        }

        public int getPoint() {
            return point;
        }

        public void setPoint(int point) {
            this.point = point;
        }

        private String type;
        private int grade;
        private int point;

    }
}