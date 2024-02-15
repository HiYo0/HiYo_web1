package example.day06.map컬랙션;

import java.util.*;

public class Example1 {
    public static void main(String[] args) {
        /*
            Map 컬렉션
                - key 와 value 구성된 엔트리(객체)를 저장
                - key 는 중복 불가능
                - Map 인터페이스
                    - 구현클래스 :
                        HashMap     : 동기화X
                        HashTable   : 동기화O , 멀티스레드 권장

                    - 선언방법
                        Map< K , V > map = new HashMap<>();
                    - 사용방법 / 메소드
                        .put( key , value )     : 엔트리 추가
                        .get( key )             : 주어진 key 이용한 value 호출
                        .remove( key )          : 주어진 key의 해당하는 엔트리 삭제
                        .keySet( )              : 전체 키를 set 컬렉션 반환
                        .entrySet( )            : 전체 엔트리 반환
                        .values( )              : 전체 값을 컬렉션 반환
                        .clear( )               : 전체 엔트리 삭제
                        .size( )                : 전체 엔트리 개수 반환

         */

        // 1. MAP 컬랙션 생성
        Map<String , Integer > map = new HashMap<>();
        // Map<String , Int > map = new HashMap<>(); // 제네릭 타입은 기본타입 불가능.

        // 2. 컬렉션 객체에 객체 ( 엔트리 = key , value ) 넣기
        map.put("신용권",85);
        map.put("홍길동",90);
        map.put("동장군",80);
        map.put("홍길동",95); // key 의 중복일 경우 : 새로운 값을 대치
        System.out.println("map = " + map);
        System.out.println("총 Entry 수 : "+map.size());
        System.out.println();

        // 3. 키로 값을 얻기
        System.out.println(map.get("홍길동"));     // 95
        
        // - 순회 : 인덱스없음
        // .keySet()    : 모든 키를 set컬력션을 반환
        // .entrySet()  :
        // .values      :

        // 1.
        Set<String > keySet = map.keySet();
        for( String key : map.keySet()){
            System.out.println("key = " + key);
            System.out.println("value =  "+map.get(key));
        }
        System.out.println();
        // 2.
        Set<Map.Entry<String ,Integer> > entrySet = map.entrySet();
            System.out.println("entrySet = " + entrySet);
        Iterator<Map.Entry<String ,Integer>> entryIterator = entrySet.iterator();
        while (entryIterator.hasNext()){
            Map.Entry<String ,Integer> entry = entryIterator.next();
            System.out.println(entry);
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }
        System.out.println();

        // 3.
        for(Map.Entry<String,Integer> entry : map.entrySet()){System.out.println(entry);}
        map.keySet().forEach( key -> System.out.println(map.get(key)));
        System.out.println();

        // 4.
        for (Integer value : map.values()){System.out.println("value = " + value);}
        map.values().forEach(value -> System.out.println("value = " + value));
        System.out.println();

        // 4. 키로 엔트리 삭제
        map.remove("홍길동");
        System.out.println("map = " + map);         // key를 넣어서 엔트리( key , value ) 삭제
        
        
        String key = "신용권";
        int value = map.get(key);
        System.out.println(key + " : " + value);
        System.out.println();


        // p. 663 ==============================================//

        // 1. Properties 객체
            // - String 타입
        Properties properties = new Properties();
        // 2.
        properties.setProperty("driver","com.mysql.cj.jdbc.Driver");
        // 3.
        System.out.println(properties.getProperty("driver") );
        properties.setProperty("URL" , "jdbc:mysql://localhost:3306/springweb");
        properties.setProperty("admin" , "root");
        properties.setProperty("password","1234");

    } // main end
} // class end
