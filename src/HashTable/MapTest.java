package HashTable;

/**
 * Created by Mykhailo on 11/6/2016.
 */
public class MapTest {

    int value;

    public MapTest(int value){
        this.value = value;
    }

    public static void main(String[] args) {
        TestClear(new MhsMap<>());
        TestContains(new MhsMap<>());
        TestGet(new MhsMap<>());
        TestRemove(new MhsMap<>());

    }

    public static void TestClear(IMhsMap<String, MapTest> map) {

        map.clear();
        assert map.size() == 0;

        map.add("", new MapTest(1));
        assert map.size() == 1;

        map.clear();
        assert map.size() == 0;
    }

    public static void TestContains(IMhsMap<String, MapTest> map){
        map.clear();

        map.add("HashTable", new MapTest(1));
        assert map.contains("HashTable");

        map.clear();
        assert !map.contains("HashTable");
    }

    public static void TestGet(IMhsMap<String, MapTest> map){
        map.clear();

        MapTest value = new MapTest(1);
        map.add("HashTable", value);
        assert value == map.get("HashTable");

        assert map.get("mhs") == null;

        map.clear();
        assert map.get("HashTable") == null;
    }

    public static void TestRemove(IMhsMap<String, MapTest> map){
        map.clear();

        MapTest value = new MapTest(1);
        map.add("HashTable", value);
        map.remove("HashTable");
        assert !map.contains("HashTable");

        assert map.size() == 0;
    }

}
