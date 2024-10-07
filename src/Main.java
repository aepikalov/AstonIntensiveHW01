import Collection.HashMapCustom;

public class Main {
    public static void main(String[] args) {
        HashMapCustom<Integer, Integer> map = new HashMapCustom<Integer, Integer>();
        map.put(1, 99);
        map.put(2, 24);
        map.put(6, 32);
        map.put(4, 10);
        map.put(10, 12);
        map.put(11, 7);
        System.out.println(map.size());
        map.remove(2);
        System.out.println(map.size());
        System.out.println(map.get(6));
        System.out.println(map.get(4));
        System.out.println(map.get(1));
    }
}