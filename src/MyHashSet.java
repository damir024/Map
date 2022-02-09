import java.util.Arrays;

public class MyHashSet implements MySet {

    private MyHashMap map = new MyHashMap();


    private String placeHolder = "";

    @Override
    public boolean add(String e) {
        return map.put(e, placeHolder) == null;
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public boolean contains(String o) {
        return map.containsKey(o);
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean remove(String o) {
        return map.remove(o) == null;
    }

    @Override
    public Object size() {
        return map.size();
    }

    @Override
    public String[] toArray() {
        String[] result = new String[map.size];
        int index = 0;
        for (MyMap.Entry entry : map.toArray()) {
            result[index++] = entry.getKey();
        }
        return result;
    }

    @Override
    public String toString() {
        return Arrays.toString(toArray());
    }
}
