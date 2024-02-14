package example.day05;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Example1 {
    public static void main(String[] args) {
        /*
            set 컬렉션
                - 저장 순서/인덱스 유지되지 않는다
                - 중복 저장할수 없다 , null 하나만 가능하다
                    * 기본 중복검사 방식 : hashCode() --t--> equals()
                    - 단일 데이터 구성
                    - 여러개 데이터로 구성된 객체들의 중복검사 직접 재정의
                        hashCode() , equals() 재정의
            - set 인터페이스
                1. 구현 클래스
                    HashSet
                2. 사용방법/메소드
                    .add(객체명)   : 주어진 객체를 set 컬렉션에 저장
                    .size()       : set 컬렉션 내 총 객체 수 반환
                    .iterator()   : 반복자 객체 리턴
                        Iterator<E> 객체명 = set.iterator();
                            객체명.hashNext(); : 다음에 가져올 객체가 존재하면 true / 없으면 false
                            객체명.next();     : 하나의 객체를 가져온다
                            객체명.remove();   : 가져온 객체를 삭제한다
                    .isEmpty()    : set 컬렉션이 비어 있는지 확인[T/F]
                    .clear()      : set 컬렉션 내 저장된 모든 객체를 삭제
                    .remove(객체)  : 주어진 객체를 set 컬렉션 내 삭제
                    .contains(객체): 주어진 객체가 set 컬렉션내 있는지 확인[T/F]
            - 선언
                E : 컬렉션에 저장할 객체의 타입
                set<E> 컬렉션명 = new 구현클래스<>():

            - 순회
                1. iterator() 이용한 방법
                    Iterator<String> rs = set.iterator();
                    while (rs.hasNext()){
                        System.out.println(rs.next());
                    }
                2. 향상된 for문
                    for (String s : set){
                        실행문;
                    }
                3. forEach() 함수
                    set.forEach(s -> 실행문);
        */
        
        // 1. set컬렉션 선언
        Set<String> set = new HashSet<>();
        // 2. set컬렉션에 객체 저장
        set.add("Java");
        set.add("JDBC");
        set.add("JSP");
        set.add("Java"); // 중복발생 : 중복 객체 이므로 저장되지 않음
        set.add("Spring");
        System.out.println("set = " + set);
        // 3. set컬렉션의 총 객체 수
        int size = set.size();
        System.out.println("size = " + size);
        // 4. 순회
            // * 인덱스 없으므로 일반 for문 사용x
            // 1. 향상된 for문
        for (String s : set){
            System.out.println("s = " + s);
        }
            // 2.
        Iterator<String> rs = set.iterator();
        while (rs.hasNext()){
            System.out.println(rs.next());
        }
            // 3. 컬렉션객체.forEach함수(반복변수명 -> 실행문);
                // - iterator 지원한 객체만 가능
        set.forEach(s -> System.out.println("s = " + s));
        
        // ------------------------------------- //
        Set<Member> set2 = new HashSet<>();
        set2.add(new Member("홍길동",30));
        set2.add(new Member("홍길동",30));
        System.out.println("set2 = " + set2);
        System.out.println("set2.toString() = " + set2.toString());
        System.out.println("set2.hashCode() = " + set2.hashCode());
    }
}
