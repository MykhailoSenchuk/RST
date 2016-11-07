package rst;

/**
 * Created by Mykhailo on 11/6/2016.
 */
public class MhsMap<Key,Value> implements IMhsMap<Key, Value> {

    private Pair<Key,Value>[] data;
    private int counter;

    public MhsMap(){
        data = new Pair[100];
    }


    @Override
    public Value add(Key key, Value value) {

        int index = Math.abs(key.hashCode() % data.length);

        Pair<Key, Value> oldPair = data[index];

        data[index] = new Pair(key, value);
        if(oldPair == null) counter++;

        return oldPair != null ? oldPair.value : null;
    }

    @Override
    public Value get(Key key) {
        int index = getIndex(key);
        return data[index] != null ? data[index].value : null;

    }

    @Override
    public Value remove(Key key) {
        int index = getIndex(key);
        Pair<Key, Value> oldPair = data[index];
        if(oldPair != null){
            data[index] = null;
            counter --;
        }
        return oldPair != null ? oldPair.value : null;
    }

    @Override
    public boolean contains(Key key) {

        return get(key) != null;
    }

    @Override
    public void clear() {
        data = new Pair[100];
        counter = 0;
    }

    @Override
    public int size() {

        return counter;
    }

    int getIndex(Key key){
        return Math.abs(key.hashCode() % data.length);
    }

    private static class Pair<Key,Value>{
        public final Key key;
        public final Value value;
        public Pair(Key key, Value value){
            this.key = key;
            this.value = value;
        }
    }
}
