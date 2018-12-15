package com.google.gwt.emultest.java9.util;

import com.google.gwt.emultest.java.util.EmulTestBase;

import java.util.Iterator;
import java.util.List;

public class ListTest extends EmulTestBase {
  public void testOf() {
    assertIsImmutableListOf(List.of());
    assertIsImmutableListOf(List.of("a"), "a");
    assertIsImmutableListOf(
        List.of("a", "b"),
        "a", "b"
    );
    assertIsImmutableListOf(
        List.of("a", "b", "c"),
        "a", "b", "c"
    );
    assertIsImmutableListOf(
        List.of("a", "b", "c", "d"),
        "a", "b", "c", "d"
    );
    assertIsImmutableListOf(
        List.of("a", "b", "c", "d", "e"),
        "a", "b", "c", "d", "e"
    );
    assertIsImmutableListOf(
        List.of("a", "b", "c", "d", "e", "f"),
        "a", "b", "c", "d", "e", "f"
    );
    assertIsImmutableListOf(
        List.of("a", "b", "c", "d", "e", "f", "g"),
        "a", "b", "c", "d", "e", "f", "g"
    );
    assertIsImmutableListOf(
        List.of("a", "b", "c", "d", "e", "f", "g", "h"),
        "a", "b", "c", "d", "e", "f", "g", "h"
    );
    assertIsImmutableListOf(
        List.of("a", "b", "c", "d", "e", "f", "g", "h", "i"),
        "a", "b", "c", "d", "e", "f", "g", "h", "i"
    );
    assertIsImmutableListOf(
        List.of("a", "b", "c", "d", "e", "f", "g", "h", "i", "j"),
        "a", "b", "c", "d", "e", "f", "g", "h", "i", "j"
    );
    assertIsImmutableListOf(
        List.of("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k"),
        "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k"
    );

    // TODO verify NPE if any element is null
  }
  protected static void assertIsImmutableListOf(List<String> list, String... contents) {
    assertEquals(contents, list);

    // quick test that the list impl is sane
    if (contents.length == 0) {
      assertFalse(list.iterator().hasNext());
    } else {
      Iterator<String> itr = list.iterator();
      assertTrue(itr.hasNext());
      assertEquals(contents[0], itr.next());
      assertEquals(contents.length > 1, itr.hasNext());
    }

    // quick check that the list is immutable
    try {
      list.add("another item");
      fail("List should be unmodifiable: add(T)");
    } catch (UnsupportedOperationException ignored) {
      // success
    }

    try {
      list.remove(0);
      fail("List should be unmodifiable: remove(int)");
    } catch (UnsupportedOperationException ignored) {
      // success
    }


    if (contents.length > 0) {
      // Without any items, remove(T) defaults to iterating items present, so we only test from
      // present items
      try {
        list.remove(contents[0]);
        fail("List should be unmodifiable: remove(T)");
      } catch (UnsupportedOperationException ignored) {
        // success
      }

      // This will actually succeed if the collection is empty, since the base implementation
      // invokes the iterator and removes each item - an empty collection does no iteration,
      // so the operation trivially passes
      try {
        list.clear();
        fail("List should be unmodifiable: clear()");
      } catch (UnsupportedOperationException ignored) {
        // success
      }
    }
  }
}
