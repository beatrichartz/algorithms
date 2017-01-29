package beatrichartz.algorithms_test.elementary_symbol_tables;

import beatrichartz.algorithms.elementary_symbol_tables.ArraySymbolTable;
import beatrichartz.algorithms.elementary_symbol_tables.LinkedListSymbolTable;
import beatrichartz.algorithms.elementary_symbol_tables.SymbolTable;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

@RunWith(value = Parameterized.class)
public class SymbolTableTest {
    @Parameterized.Parameters(name = "{0}")
    public static Collection data() {
        List<Object> collection = new ArrayList();
        collection.add(LinkedListSymbolTable.class);
        collection.add(ArraySymbolTable.class);
        return collection;
    }

    private Class symbolTableClass;
    private SymbolTable<String, Integer> empty;
    private SymbolTable<String, Integer> one;
    private SymbolTable<String, Integer> many;

    public SymbolTableTest(Class symbolTableClass) {
        this.symbolTableClass = symbolTableClass;
    }

    @Before
    public void setUp() throws Exception {
        this.empty = (SymbolTable) symbolTableClass.newInstance();
        this.one = (SymbolTable) symbolTableClass.newInstance();
        this.many = (SymbolTable) symbolTableClass.newInstance();

        one.put("A", 1);
        many.put("A", 1);
        many.put("B", 2);
    }

    @Test
    public void emptiness() throws Exception {
        assertEquals(true, empty.isEmpty());
        assertEquals(false, one.isEmpty());
        assertEquals(false, many.isEmpty());
    }

    @Test
    public void size() throws Exception {
        assertEquals(0, empty.size());
        assertEquals(1, one.size());
        assertEquals(true, many.size() > 1);
    }

    @Test
    public void contains() throws Exception {
        assertEquals(false, empty.contains("A"));
        assertEquals(false, empty.contains("B"));
        assertEquals(true, one.contains("A"));
        assertEquals(false, one.contains("B"));
        assertEquals(true, many.contains("A"));
        assertEquals(true, many.contains("B"));
    }

    @Test
    public void get() throws Exception {
        assertEquals(null, empty.get("A"));
        assertEquals(null, empty.get("B"));
        assertEquals(new Integer(1), one.get("A"));
        assertEquals(null, one.get("B"));
        assertEquals(new Integer(1), many.get("A"));
        assertEquals(new Integer(2), many.get("B"));
    }

    @Test
    public void overwritingValues() throws Exception {
        SymbolTable<String, Integer> symbolTable = (SymbolTable) symbolTableClass.newInstance();
        symbolTable.put("A", 1);
        symbolTable.put("B", 2);
        symbolTable.put("A", 3);

        assertEquals(new Integer(3), symbolTable.get("A"));

        symbolTable.delete("A");
        assertEquals(false, symbolTable.contains("A"));
    }

    @Test
    public void delete() throws Exception {
        SymbolTable<String, Integer> symbolTable = (SymbolTable) symbolTableClass.newInstance();
        symbolTable.put("A", 1);
        symbolTable.put("B", 2);

        symbolTable.delete("A");

        assertEquals(1, symbolTable.size());
        assertEquals(false, symbolTable.contains("A"));
        assertEquals(true, symbolTable.contains("B"));

        symbolTable.delete("does not exist");
        symbolTable.delete("B");

        assertEquals(true, symbolTable.isEmpty());
        assertEquals(false, symbolTable.contains("A"));
        assertEquals(false, symbolTable.contains("B"));
    }

    @Test
    public void iterableKeys() throws Exception {
        SymbolTable<String, Integer> symbolTable = (SymbolTable) symbolTableClass.newInstance();
        symbolTable.put("A", 1);
        symbolTable.put("B", 2);
        symbolTable.put("C", 3);
        symbolTable.put("D", 4);
        symbolTable.put("E", 5);

        List<String> keysIterated = new ArrayList<>();
        for (String key : symbolTable.keys()) {
            keysIterated.add(key);
        }

        assertEquals(5, keysIterated.size());
        assertEquals(true, keysIterated.contains("A"));
        assertEquals(true, keysIterated.contains("B"));
        assertEquals(true, keysIterated.contains("C"));
        assertEquals(true, keysIterated.contains("D"));
        assertEquals(true, keysIterated.contains("E"));

        List<String> keysIteratedForEmpty = new ArrayList<>();
        for (String key : empty.keys()) {
            keysIterated.add(key);
        }

        assertEquals(true, keysIteratedForEmpty.isEmpty());
    }
}
