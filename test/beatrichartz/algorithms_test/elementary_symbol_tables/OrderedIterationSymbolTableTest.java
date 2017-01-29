package beatrichartz.algorithms_test.elementary_symbol_tables;

import beatrichartz.algorithms.elementary_symbol_tables.ArraySymbolTable;
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
public class OrderedIterationSymbolTableTest {
    @Parameterized.Parameters(name = "{0}")
    public static Collection data() {
        List<Object> collection = new ArrayList();
        collection.add(ArraySymbolTable.class);
        return collection;
    }

    private Class symbolTableClass;
    private SymbolTable<String, Integer> symbolTable;

    public OrderedIterationSymbolTableTest(Class symbolTableClass) {
        this.symbolTableClass = symbolTableClass;
    }

    @Before
    public void setUp() throws Exception {
        this.symbolTable = (SymbolTable) symbolTableClass.newInstance();
    }

    @Test
    public void orderedIteration() throws Exception {
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
        assertEquals("A", keysIterated.get(0));
        assertEquals("B", keysIterated.get(1));
        assertEquals("C", keysIterated.get(2));
        assertEquals("D", keysIterated.get(3));
        assertEquals("E", keysIterated.get(4));
    }
}
