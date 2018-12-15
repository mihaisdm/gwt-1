package com.google.gwt.emultest.java9.util;

import com.google.gwt.emultest.java.util.EmulTestBase;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class MapTest extends EmulTestBase {
  public void testOf() {
    assertIsImmutableMapOf(Map.of());
    assertIsImmutableMapOf(Map.of("a", 1), "a");
    assertIsImmutableMapOf(
        Map.of("a", 1, "b", 2),
        "a", "b"
    );
    assertIsImmutableMapOf(
        Map.of("a", 1, "b", 2, "c", 3),
        "a", "b", "c"
    );
    assertIsImmutableMapOf(
        Map.of("a", 1, "b", 2, "c", 3, "d", 4),
        "a", "b", "c", "d"
    );
    assertIsImmutableMapOf(
        Map.of("a", 1, "b", 2, "c", 3, "d", 4, "e", 5),
        "a", "b", "c", "d", "e"
    );
    assertIsImmutableMapOf(
        Map.of("a", 1, "b", 2, "c", 3, "d", 4, "e", 5, "f", 6),
        "a", "b", "c", "d", "e", "f"
    );
    assertIsImmutableMapOf(
        Map.of("a", 1, "b", 2, "c", 3, "d", 4, "e", 5, "f", 6, "g", 7),
        "a", "b", "c", "d", "e", "f", "g"
    );
    assertIsImmutableMapOf(
        Map.of("a", 1, "b", 2, "c", 3, "d", 4, "e", 5, "f", 6, "g", 7, "h", 8),
        "a", "b", "c", "d", "e", "f", "g", "h"
    );
    assertIsImmutableMapOf(
        Map.of("a", 1, "b", 2, "c", 3, "d", 4, "e", 5, "f", 6, "g", 7, "h", 8, "i", 9),
        "a", "b", "c", "d", "e", "f", "g", "h", "i"
    );
    assertIsImmutableMapOf(
        Map.of("a", 1, "b", 2, "c", 3, "d", 4, "e", 5, "f", 6, "g", 7, "h", 8, "i", 9, "j", 10),
        "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k"
    );

    // TODO verify NPE if any key or value is null, IAE if any keys collide
  }

  protected static void assertIsImmutableMapOf(Map<String, Integer> map, String... contents) {

    assertEquals(contents.length, map.size());
    for (int i = 0; i < contents.length; i++) {
      assertTrue(map.containsKey(contents[i]));
      assertFalse(map.containsKey(contents[i] + "nope"));
      assertEquals(i, (int) map.get(contents[i]));
    }

    // quick check that the map is immutable
    try {
      map.put("another item", 1);
      fail("Set should be unmodifiable: add(T)");
    } catch (UnsupportedOperationException ignored) {
      // success
    }


    if (contents.length > 1) {
      // Without any items, remove(T) defaults to iterating items present, so we only test from
      // present items
      try {
        map.remove(contents[0]);
        fail("Map should be unmodifiable: remove(T)");
      } catch (UnsupportedOperationException ignored) {
        // success
      }

      try {
        map.clear();
        fail("Set should be unmodifiable: clear()");
      } catch (UnsupportedOperationException ignored) {
        // success
      }
    }
  }


  public void testEntry() {
    Map.Entry<String, String> entry = Map.entry("a", "b");

    assertEquals("a", entry.getKey());
    assertEquals("b", entry.getValue());

    try {
      entry.setValue("z");
      fail("Entry should be immutable: setValue");
    } catch (UnsupportedOperationException ignore) {
      // expected
    }

  }

  public void testOfEntries() {
    Map<String, Integer> map = Map.ofEntries(
        Map.entry("a", 1),
        Map.entry("b", 2)
    );

    assertIsImmutableMapOf(map, "a", "b");
  }
}
