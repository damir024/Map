public interface MyMap<E> {
    boolean isEmpty();

    interface Entry {
        String getKey();
        String getValue();
        void setValue(String value);
        boolean isEmpty();

    }
    void clear();
    boolean containsKey(String key);
    Object get(Object key);
    Object remove(String key);
    String put(String key, String value);
    Object size();
    Entry[] toArray();


}