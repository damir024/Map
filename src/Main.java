public class Main {

    public static void main(String[] args) {
        MyMap myMap = new MyHashMap();
        myMap.put("S", "35");
        myMap.put("M", "38");
        myMap.put("L", "40");
        myMap.put("XL", "42");
        System.out.println(myMap.containsKey("M"));

        MySet set = new MyHashSet();
        set.add("Dima");
        set.add("Damir");
        set.add("Denis");
        set.add("Jora");
        System.out.println(set.size());
    }

}
