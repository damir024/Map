public class MyHashMap implements MyMap {


    static class HashMapEntry implements MyMap.Entry {
        private String key;
        private String value;
        private int hashCode;
        private HashMapEntry next;


        public HashMapEntry(String key, String value, int hashCode) {
            this.key = key;
            this.value = value;
            this.hashCode = hashCode;
        }

        @Override
        public String getKey() {
            return key;
        }

        @Override
        public String getValue() {
            return value;
        }

        @Override
        public void setValue(String v) {
            this.value = v;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }


        public String toString() {
            return key + " " + value;
        }


    }

    int size = 0;
    private HashMapEntry[] table = new HashMapEntry[16];
    private double loadFactor = 0.75;
    private double threshold = loadFactor * table.length;


    private void resize() {
        Entry[] arr = toArray();
        table = new HashMapEntry[table.length * 2];
        threshold = loadFactor * table.length;
        for (Entry entry : arr) {
            putInternal(entry.getKey(), entry.getValue());
        }
    }

    private int capacity;

    private String putInternal(String key, String value) {
        HashMapEntry newEntry = new HashMapEntry(key, value, key.hashCode());
        int position = newEntry.hashCode % table.length;
        if (table[position] != null) {
            HashMapEntry tmp = table[position];
            while (tmp != null) {
                if (tmp.key.equals(key)) {
                    String oldValue = tmp.value;
                    tmp.value = value;
                    return oldValue;
                }
                tmp = tmp.next;
            }
            newEntry.next = table[position];
        }
        table[position] = newEntry;
        return null;
    }

    @Override
    public String put(String key, String value) {
        String result = putInternal(key, value);
        if (result == null) {
            size++;
        }
        if (size > threshold) {
            resize();
        }
        return result;
    }

    @Override
    public Object size() {
        return "length map: " + size;

    }


    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        size = 0;
        table = new HashMapEntry[16];
    }

    @Override
    public boolean containsKey(String key) {
        return get(key) != null;

    }


    public static int myHash(int v, int length) {
        System.out.println("hash in myHash:" + (v & (length - 1)));
        return v & (length - 1);
    }

    @Override
    public Object get(Object key) {
        int hash = myHash(key.hashCode(), table.length);
        Object value = null;
        if (table[hash] == null) {
            return null;

        } else {
            HashMapEntry temp = table[hash];
            while (temp != null) {
                if (temp.key.equals(key)) {

                    value = temp.value;
                    break;
                } else {
                    temp = temp.next;
                }
            }
        }
        return value;

    }


    @Override
    public String remove(String key) {
        for (int i = 0; i < capacity; i++) {
            if (table[i] != null) {
                if (table[i].next == null) {
                    if (table[i].value.equals((String) key)) {
                        table[i] = null;
                        continue;
                    }

                }
                HashMapEntry temp = table[i];
                while (temp != null && temp.next != null) {
                    while (temp.next != null && temp.next.value.equals((String) key)) {
                        if (temp.next.next == null) {
                            temp.next = null;
                            continue;

                        }
                        temp.next = temp.next.next;
                    }

                    if (temp.next != null) {

                        temp = temp.next;
                    }


                }


            }
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");

        for (int i = 0; i < table.length; i++) {
            HashMapEntry temp = table[i];
            while (temp != null) {
                sb.append(temp.key + ":" + temp.value + ",");
                temp = temp.next;
            }

        }
        sb.setCharAt(sb.length() - 1, '}');
        return sb.toString();

    }

    @Override
    public Entry[] toArray() {
        HashMapEntry[] result = new HashMapEntry[size];
        int index = 0;
        for (HashMapEntry tmp : table) {
            while (tmp != null) {
                result[index] = tmp;
                tmp = tmp.next;
                index++;
            }
        }
        return result;
    }

}
