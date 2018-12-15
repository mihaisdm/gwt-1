package com.google.gwt.emultest.java9.util;

import com.google.gwt.emultest.java.util.EmulTestBase;

import java.util.Iterator;
import java.util.Set;

public class SetTest extends EmulTestBase {
  public void testOf() {
    assertIsImmutableSetOf(Set.of());
    assertIsImmutableSetOf(Set.of("a"), "a");
    assertIsImmutableSetOf(
        Set.of("a", "b"),
        "a", "b"
    );
    assertIsImmutableSetOf(
        Set.of("a", "b", "c"),
        "a", "b", "c"
    );
    assertIsImmutableSetOf(
        Set.of("a", "b", "c", "d"),
        "a", "b", "c", "d"
    );
    assertIsImmutableSetOf(
        Set.of("a", "b", "c", "d", "e"),
        "a", "b", "c", "d", "e"
    );
    assertIsImmutableSetOf(
        Set.of("a", "b", "c", "d", "e", "f"),
        "a", "b", "c", "d", "e", "f"
    );
    assertIsImmutableSetOf(
        Set.of("a", "b", "c", "d", "e", "f", "g"),
        "a", "b", "c", "d", "e", "f", "g"
    );
    assertIsImmutableSetOf(
        Set.of("a", "b", "c", "d", "e", "f", "g", "h"),
        "a", "b", "c", "d", "e", "f", "g", "h"
    );
    assertIsImmutableSetOf(
        Set.of("a", "b", "c", "d", "e", "f", "g", "h", "i"),
        "a", "b", "c", "d", "e", "f", "g", "h", "i"
    );
    assertIsImmutableSetOf(
        Set.of("a", "b", "c", "d", "e", "f", "g", "h", "i", "j"),
        "a", "b", "c", "d", "e", "f", "g", "h", "i", "j"
    );
    assertIsImmutableSetOf(
        Set.of("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k"),
        "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k"
    );
  }

  protected static void assertIsImmutableSetOf(Set<String> set, String... contents) {

    assertEquals(contents.length, set.size());
    for (int i = 0; i < contents.length; i++) {
      assertTrue(set.contains(contents[i]));
      assertFalse(set.contains(contents[i] + "nope"));
    }


    // quick test that the set impl is sane, aside from the above
    if (contents.length == 0) {
      assertFalse(set.iterator().hasNext());
    } else {
      Iterator<String> itr = set.iterator();
      assertTrue(itr.hasNext());
      assertEquals(contents[0], itr.next());
      assertEquals(contents.length > 1, itr.hasNext());
    }

    // quick check that the set is immutable
    try {
      set.add("another item");
      fail("Set should be unmodifiable: add(T)");
    } catch (UnsupportedOperationException ignored) {
      // success
    }


    if (contents.length > 1) {
      // Without any items, remove(T) defaults to iterating items present, so we only test from
      // present items
      try {
        set.remove(contents[0]);
        fail("Set should be unmodifiable: remove(T)");
      } catch (UnsupportedOperationException ignored) {
        // success
      }

      // This will actually succeed if the collection is empty, since the base implementation
      // invokes the iterator and removes each item - an empty collection does no iteration,
      // so the operation trivially passes
      try {
        set.clear();
        fail("Set should be unmodifiable: clear()");
      } catch (UnsupportedOperationException ignored) {
        // success
      }
    }
  }
}
