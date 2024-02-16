package example.day07._1트리컬렉션;

import java.util.Map;
import java.util.TreeMap;

public class Example2 {
    public static void main(String[] args) {
        // 1. TreeMap 컬렉션 생성
        TreeMap<String,Integer> treeMap = new TreeMap<>();
        
        // 2. 엔트리 저장
        treeMap.put("apple",10);
        treeMap.put("forever",60);
        treeMap.put("description",40);
        treeMap.put("ever",50);
        treeMap.put("zoo",80);
        treeMap.put("base",20);
        treeMap.put("guess",70);
        treeMap.put("cherry",30);

        System.out.println("treeMap = " + treeMap);
        
        // 3. 순회
        for(Map.Entry<String,Integer> entry : treeMap.entrySet()){
            System.out.println("entry = " + entry);
        }
        treeMap.entrySet().forEach(entry -> System.out.println("forEach entry = " + entry));

        // 4. 일반 Map 컬렉션 보다 추가적인 메소드 제공
        System.out.println("제일 앞 단어 : "+treeMap.firstEntry());
        System.out.println("제일 뒤 단어 : "+treeMap.lastEntry());
        System.out.println("ever 앞 단어 : "+treeMap.lowerEntry("ever"));

        // 5. 내림차순 정렬
        System.out.println(treeMap.descendingMap());

        // 6. 범위 검색
        System.out.println(treeMap.subMap("c" , true , "f" , false).descendingMap());
    }
}
