package example.day07._1트리컬렉션;

import java.util.NavigableSet;
import java.util.TreeSet;

public class Example1 {
    public static void main(String[] args) {
        // 1. TreeSet 컬렉션 생성
        TreeSet<Integer> scores = new TreeSet<>();
        
        // 2. TreeSet 컬렉션 객체에 객체 추가
        scores.add(87);
        scores.add(98);
        scores.add(75);
        scores.add(95);
        scores.add(80);

        System.out.println("scores = " + scores);

        /*
            컬렉션 프레임워크 : 널리 알려진 자료구조 기반으로 미리 만들어진 클래스/인터페이스들
                자료구조 : 자료(데이터)를 저장하는 방법론
            이진트리 : 여러 자료구조 중에 하나의 방법

                    순서대로 대입
                         87 - 처음값
                75                98
           노드     80         95      노드
        */
        
        // 3. 순회
        for (Integer i : scores){
            System.out.println("i = " + i);
        }
        scores.forEach(i -> System.out.println("i = " + i));

        // 4. HashSet 보다 추가적인 메소드 제공
        System.out.println("가장 낮은 점수 : " + scores.first());
        System.out.println("가장 높은 점수 : " + scores.last());
        System.out.println("95점 아래 점수 : " + scores.lower(95));
        System.out.println("95점 위의 점수 : " + scores.higher(95));
        System.out.println("95점 이거나 바로 아래 점수 : " + scores.floor(95));
        System.out.println("85점 이거나 바로 위의 점수 : " + scores.ceiling(85));
        
        // 5. 내림차순 정렬
        NavigableSet<Integer> descending = scores.descendingSet();

        System.out.println("descending = " + descending);
        
        // 6. 범위 검색 (80<=) 80이상
        System.out.println("scores.tailSet(80,true) = " + scores.tailSet(80,true));
        // (80 <= scores < 90) 80~89사이
        System.out.println("scores.subSet(80,true,90,false) = " + scores.subSet(80,true,90,false));
    }
}
