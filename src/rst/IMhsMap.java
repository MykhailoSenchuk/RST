package rst;

/**
 * Created by Mykhailo on 11/6/2016.
 */
public interface IMhsMap<Key,Value> {
    Value add(Key key, Value value);
    Value get(Key key);
    Value remove(Key key);
    boolean contains(Key key);
    void clear();
    int size();
}
