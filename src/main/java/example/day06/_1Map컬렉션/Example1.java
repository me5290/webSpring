package example.day06._1Map컬렉션;

import java.util.*;

public class Example1 {
    public static void main(String[] args) {
        /*
            Map 컬렉션
                - key와 value로 구성된 엔트리(객체)를 저장
                - key는 중복 불가능
                - Map인터페이스
                    - 구현클래스
                        HashMap : 동기화X
                        HashTable : 동기화O , 멀티스레드 권장
                    - 선언방법
                        Map<K,V> map = new HashMap<>();
                    - 사용방법/메소드
                        .put(key,value)     : 엔트리 추가
                        .get(key)           : 주어진 key 이용한 value 호출
                        .size()             : 엔트리의 총 길이 반환
                        .remove(key)        : 주어진 key의 해당하는 엔트리 삭제
                        .keySet()           : 전체 키를 set컬렉션 반환
                        .entrySet()         : 전체 엔트리 반환
                        .values()           : 전체 값을 컬렉션 반환
                        .clear()            : 전체 엔트리 삭제
        */
        // 1. Map 컬렉션 생성
        Map<String , Integer> map = new HashMap<>();
        // Map<String , int> map = new HashMap<>(); 제네릭 타입은 기본타입 불가능
        
        // 2. 컬렉션 객체에 객체(엔트리 = key , value) 넣기
        map.put("신용권" , 85);
        map.put("홍길동" , 90);
        map.put("동장군" , 80);
        map.put("홍길동" , 95);    // key의 중복일 경우 : 새로운 값을 대치
        System.out.println("map = " + map);

                // map은 인덱스가 없기 때문에 처음에 랜덤으로 나오고 그 후에 고정되서 나온다 순서가 상관없다, 정렬 가능
                // 동기화는 여러 스레드가 하나를 사용할때 순서를 정해주는 것 = 깃 취합과 같다
                // 매개변수 아무타입,여러개 가능 - 리턴 : 1개의 타입과 값 해당 메소드를 부른곳에 값 호출

        // 3. 키로 값 얻기
        System.out.println(map.get("홍길동"));

        // - 순회 : 인덱스 없음
        // .keySet() : 모든 키를 set컬렉션으로 반환
        // .entrySet() : 모든 엔트리를 set컬렉션으로 반환
        // .values() : 모든 값을 컬렉션으로 반환
        // 1.
        Set<String> keySet = map.keySet();
        for (String key : keySet){
            System.out.println("key = " + key);
            System.out.println("value = " + map.get(key));
        }
        // 2. 
        Set<Map.Entry<String,Integer>> entrySet = map.entrySet();
        Iterator<Map.Entry<String,Integer>> entryIterator = entrySet.iterator();
        System.out.println("entrySet = " + entrySet);
        System.out.println("entryIterator = " + entryIterator);
        while (entryIterator.hasNext()){
            Map.Entry<String , Integer> entry = entryIterator.next();
            System.out.println("while entry = " + entry);
            System.out.println("while entry.getKey() = " + entry.getKey());
            System.out.println("while entry.getValue() = " + entry.getValue());
        }
        // 3.
        for (Map.Entry<String,Integer> entry :map.entrySet()){
            System.out.println("for entry = " + entry);
            System.out.println("for entry.getKey() = " + entry.getKey());
            System.out.println("for entry.getValue() = " + entry.getValue());
        }
        map.keySet().forEach(key -> System.out.println("forEach key = " + key));
        // 4.
        for(Integer value:map.values()){
            System.out.println("value = " + value);
        }
        map.values().forEach(value -> System.out.println("forEach value = " + value));

        // 5. 총 엔트리 개수
        System.out.println(map.size());

        // 4. 키로 엔트리 삭제
        map.remove("홍길동");  // key 넣어서 엔트리(key,value) 삭제
        System.out.println("map = " + map);

        // ================================================================== //
        // 1. Properties 객체
            // - String 타입
        Properties properties = new Properties();
        // 2.
        properties.setProperty("driver","com.mysql.cj.jdbc.Driver");
        properties.setProperty("URL","jdbc:mysql://localhost:3306/springweb");
        properties.setProperty("user","root");
        properties.setProperty("password","1234");
        // 3.
        System.out.println(properties.getProperty("driver"));
        System.out.println(properties.getProperty("URL"));
        System.out.println(properties.getProperty("user"));
        System.out.println(properties.getProperty("password"));
    }
}
