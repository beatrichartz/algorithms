package beatrichartz.algorithms_test.elementary_symbol_tables;

import beatrichartz.algorithms.elementary_symbol_tables.ArraySymbolTable;
import org.junit.Test;

import java.lang.reflect.Field;

import static junit.framework.TestCase.assertEquals;

public class ArraySymbolTableTest {
    @Test
    public void resizes() throws Exception {
        ArraySymbolTable<String, Integer> symbolTable = new ArraySymbolTable<>(1);
        symbolTable.put("A", 1);
        symbolTable.put("B", 2);
        symbolTable.put("C", 2);

        assertEquals(3, symbolTable.size());
        assertEquals(true, symbolTable.contains("A"));
        assertEquals(true, symbolTable.contains("B"));
        assertEquals(true, symbolTable.contains("C"));

        symbolTable.delete("C");
        symbolTable.delete("B");
        symbolTable.delete("A");

        Field keysField = symbolTable.getClass().getDeclaredField("keys");
        keysField.setAccessible(true);
        Comparable[] keys = (Comparable[]) keysField.get(symbolTable);
        assertEquals(1, keys.length);
    }
}
