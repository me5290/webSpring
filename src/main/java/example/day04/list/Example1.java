package example.day04.list;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Example1 {
    public static void main(String[] args) {
        // 컬렉션 프레임워크
            // 1. 컬렉션(수집) ,프레임워크(미리 만들어진 틀)
            // 2. 널리 사용되는 자료구조(데이터 저장하는 방법론) 기반으로 제공
            // 3. 대표 List , Set , Map 인터페이스

        // 1. List
            // - 객체를 저장하면 인덱스가 부여되고,중복가능
            // 동기화 : 여러 스레드가 하나의 함수를 동시에 호출했을때 호출순서대로 해당 함수를 끝날때까지 점유 상태
            // ArrayList : [배열구조] 동기화 지원X , 주로 단일스레드 사용
            // Vector : [배열구조] 동기화 지원O , 주로 멀티스레드 사용
            // LinkedList : [연결구조] 동기화 지원X
                // - 순차적인 삽입은 ArrayList 권장
                // - 빈번한 중간/특정위치 삽입과 삭제는 LinkedList 권장

        // List 선언하는 방법
            // 1. List<Board> list = new ArrayList<>();
            // 2. List<Board> list2 = new ArrayList<Board>();
            // 3. List list3 = new ArrayList();
            // 4. ArrayList<Board> list4 = new ArrayList<>();
        // 제공하는 함수
            // 1. 리스트객체명.add(객체)            : 주어진 객체를 리스트내 맨 끝에 추가
            // 2. 리스트객체명.add(인덱스,객체)      : 주어진 객체를 리스트내 주어진 인덱스에 추가
            // 3. 리스트객체명.set(인덱스,객체)      : 주어진 객체를 리스트내 주어진 인덱스에 바꿈
            // 4. 리스트객체명.size()               : 리스트내 저장된 전체 객체의 수를 반환
            // 5. 리스트객체명.get(인덱스)           : 주어진 인덱스에 저장된 객체를 반환
            // 6. 리스트객체명.contains(객체)       : 주어진 객체가 리스트내 저장되어 있는지 여부 반환
            // 7. 리스트객체명.remove(인덱스)       : 주어진 인덱스에 저장된 객체를 삭제
            // 8. 리스트객체명.remove(객체)         : 주어진 객체가 존재하면 해당 객체를 삭제
            // 9. 리스트객체명.clear()              : 리스트내 저장된 객체를 모두 삭제
            // 10. 리스트객체명.isEmpty()           : 리스트가 비어 있는지 확인 반환
        // 순회
            // 1. for(int i = 0; i < 리스트명.size(); i++){실행문;}
            // 2. for(타입 반복변수명 : 리스트명){실행문;}
            // 3. 리스트명.forEach(반복변수명 -> 실행문);

        // 1. 리스트 컬렉션 생성
        List<Board> list = new ArrayList<>();

        // 2. 리스트에 객체 추가
        list.add(new Board("제목1","내용1","글쓴이1"));
        list.add(new Board("제목2","내용2","글쓴이2"));
        Board board = new Board("제목3","내용3","글쓴이3");
        list.add(board);
        list.add(0,new Board("제목5","내용5","글쓴이5"));

        System.out.println(list);

        list.set(0,board);
        System.out.println(list);

        // 3. 리스트에 저장된 총 객체 수 호출하기
        int size = list.size();
        System.out.println(size);

        // 4. 리스트내 저장된 특정 인덱스의 객체 호출 하기
        Board board1 = list.get(2);
        System.out.println(board1);

        // 5. 리스트내 저장된 특정 인덱스의 객체 존재 여부
        boolean result1 = list.contains(board1);
        System.out.println(result1);

        // 6. 리스트내 객체 삭제
        list.remove(2);
        System.out.println(list);
        System.out.println(list.size());

        // ============리스트 순회============ //
        // 1. 일반 for문
        System.out.println("===== 일반 for문 =====");
        for (int i = 0; i < list.size(); i++){
            System.out.println(list.get(i));
        }
        System.out.println("=====================");

        // 2. 향상된 for문  for(타입 반복변수명 : 리스트/배열){} : 리스트내 순차적으로 하나씩 객체를 반복변수에 대입
        System.out.println("===== 향상된 for문 =====");
        for(Board b : list){
            System.out.println(b);
        }
        System.out.println("=====================");

        // 3. forEach(반복변수 -> 실행문)
            // forEach(반복변수 -> {실행문1,실행문2})
        System.out.println("===== forEach 함수 =====");
        list.forEach(b-> System.out.println(b));
        System.out.println("=====================");

        // ================================= //

        // 7. 리스트내 모든 객체 삭제
        list.clear();
        System.out.println(list);

        // 8. 리스트내 비어 있는지 확인
        boolean empty2 = list.isEmpty();
        System.out.println(empty2);

        // 9. ArrayList 와 LinkedList 차이점
            // 1. ArrayList
        List<String> list1 = new ArrayList<>();
            // 2. LinkedList
        List<String> list2 = new LinkedList<>();
            // 3. 시간 체크를 위한 변수 선언
        long startTime;
        long endTime;

            // ArrayList에 10000개 객체 대입
        startTime = System.nanoTime();
        for (int i = 0; i < 10000; i++){
            list1.add(0,i+"");
        }
        endTime = System.nanoTime();
        System.out.println("ArrayList 걸린 시간 : "+(endTime-startTime));

        // LinkedList 10000개 객체 대입
        startTime = System.nanoTime();
        for (int i = 0; i < 10000; i++){
            list2.add(0,i+"");
        }
        endTime = System.nanoTime();
        System.out.println("LinkedList 걸린 시간 : "+(endTime-startTime));
    }
}
