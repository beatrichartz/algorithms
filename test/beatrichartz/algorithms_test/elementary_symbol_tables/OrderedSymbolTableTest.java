package beatrichartz.algorithms_test.elementary_symbol_tables;

import beatrichartz.algorithms.elementary_symbol_tables.ArraySymbolTable;
import beatrichartz.algorithms.elementary_symbol_tables.BinarySearchTreeSymbolTable;
import beatrichartz.algorithms.elementary_symbol_tables.OrderedSymbolTable;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

@RunWith(value = Parameterized.class)
public class OrderedSymbolTableTest {

    @Parameterized.Parameters(name = "{0}")
    public static Collection data() {
        List<Object> collection = new ArrayList();
        collection.add(ArraySymbolTable.class);
        collection.add(BinarySearchTreeSymbolTable.class);
        return collection;
    }

    private Class symbolTableClass;
    private OrderedSymbolTable<String, Integer> empty;
    private OrderedSymbolTable<String, Integer> one;
    private OrderedSymbolTable<String, Integer> many;

    public OrderedSymbolTableTest(Class symbolTableClass) {
        this.symbolTableClass = symbolTableClass;
    }

    @Before
    public void setUp() throws Exception {
        this.empty = (OrderedSymbolTable) symbolTableClass.newInstance();
        this.one = (OrderedSymbolTable) symbolTableClass.newInstance();
        this.many = (OrderedSymbolTable) symbolTableClass.newInstance();

        one.put("Y", 1);

        many.put("D", 4);
        many.put("B", 2);
        many.put("E", 5);
        many.put("C", 3);
        many.put("A", 1);
    }

    @Test
    public void orderedIteration() throws Exception {
        List<String> keysIterated = new ArrayList<>();
        for (String key : many.keys()) {
            keysIterated.add(key);
        }

        assertEquals(5, keysIterated.size());
        assertEquals("A", keysIterated.get(0));
        assertEquals("B", keysIterated.get(1));
        assertEquals("C", keysIterated.get(2));
        assertEquals("D", keysIterated.get(3));
        assertEquals("E", keysIterated.get(4));

        List<String> emptyKeysIterated = new ArrayList<>();
        for (String key : empty.keys()) {
            emptyKeysIterated.add(key);
        }

        assertEquals(0, emptyKeysIterated.size());
    }

    @Test
    public void orderedRangeIteration() throws Exception {
        List<String> keysIterated = new ArrayList<>();
        for (String key : many.keys("B", "D")) {
            keysIterated.add(key);
        }

        assertEquals(3, keysIterated.size());
        assertEquals("B", keysIterated.get(0));
        assertEquals("C", keysIterated.get(1));
        assertEquals("D", keysIterated.get(2));

        List<String> emptyKeysIterated = new ArrayList<>();
        for (String key : empty.keys("A", "Z")) {
            emptyKeysIterated.add(key);
        }

        assertEquals(0, emptyKeysIterated.size());
    }

    @Test
    public void minReturnsSmallestKey() throws Exception {
        assertEquals(null, empty.min());
        assertEquals("Y", one.min());
        assertEquals("A", many.min());
    }

    @Test
    public void maxReturnsLargestKey() throws Exception {
        assertEquals(null, empty.max());
        assertEquals("Y", one.max());
        assertEquals("E", many.max());
    }

    @Test
    public void floorReturnsKeySmallerOrEqualToGivenKey() throws Exception {
        assertEquals(null, empty.floor("B"));
        assertEquals("Y", one.floor("Z"));
        assertEquals(null, one.floor("X"));
        assertEquals("E", many.floor("Z"));
        assertEquals("B", many.floor("B"));
        assertEquals("A", many.floor("A"));
    }

    @Test
    public void ceilingReturnsKeyGreatherThanOrEqualToGivenKey() throws Exception {
        assertEquals(null, empty.ceiling("B"));
        assertEquals(null, one.ceiling("Z"));
        assertEquals("Y", one.ceiling("X"));
        assertEquals(null, many.ceiling("F"));
        assertEquals("E", many.ceiling("E"));
        assertEquals("E", many.ceiling("B"));
        assertEquals("E", many.ceiling("A"));
    }

    @Test
    public void rankReturnsNumberOfKeysLessThanKey() throws Exception {
        assertEquals(0, empty.rank("B"));
        assertEquals(0, one.rank("X"));
        assertEquals(1, one.rank("Z"));
        assertEquals(5, many.rank("F"));
        assertEquals(4, many.rank("E"));
        assertEquals(1, many.rank("B"));
        assertEquals(0, many.rank("A"));
    }

    @Test
    public void selectReturnsKeyByRank() throws Exception {
        assertEquals(null, empty.select(100));
        assertEquals(null, empty.select(0));
        assertEquals(null, one.select(100));
        assertEquals(null, one.select(1));
        assertEquals("Y", one.select(0));
        assertEquals(null, many.select(500));
        assertEquals(null, many.select(5));
        assertEquals("E", many.select(4));
        assertEquals("C", many.select(2));
        assertEquals("A", many.select(0));
    }

    @Test
    public void deleteMinDeletesTheSmallestKey() throws Exception {
        OrderedSymbolTable symbolTable = (OrderedSymbolTable) symbolTableClass.newInstance();
        symbolTable.put("B", 1);
        symbolTable.put("A", 2);

        symbolTable.deleteMin();
        assertEquals(1, symbolTable.size());
        assertEquals(true, symbolTable.contains("B"));
        assertEquals(false, symbolTable.contains("A"));

        symbolTable.deleteMin();
        assertEquals(0, symbolTable.size());

        symbolTable.deleteMin();
    }

    @Test
    public void deleteMaxDeletesTheLargestKey() throws Exception {
        OrderedSymbolTable symbolTable = (OrderedSymbolTable) symbolTableClass.newInstance();
        symbolTable.put("B", 1);
        symbolTable.put("A", 2);

        symbolTable.deleteMax();
        assertEquals(1, symbolTable.size());
        assertEquals(false, symbolTable.contains("B"));
        assertEquals(true, symbolTable.contains("A"));

        symbolTable.deleteMax();
        assertEquals(0, symbolTable.size());

        symbolTable.deleteMax();
    }

    @Test
    public void sizeBetweenLowAndHighReturnsSizeRange() throws Exception {
        assertEquals(0, empty.size("A", "B"));
        assertEquals(0, one.size("A", "X"));
        assertEquals(1, one.size("X", "Z"));
        assertEquals(0, many.size("F", "Z"));
        assertEquals(2, many.size("D", "Y"));
        assertEquals(4, many.size("B", "E"));
    }
}
