/*
 * Copyright 2016 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.google.gwt.emultest.java8.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Tests for java.util.List Java 8 API emulation.
 */
public class ListTest extends AbstractJava8ListTest {
  @Override
  protected List<String> createEmptyList() {
    return new TestList<>();
  }

  private static class TestList<T> implements List<T> {
    private final List<T> container = new ArrayList<>();

    @Override
    public int size() {
      return container.size();
    }

    @Override
    public boolean isEmpty() {
      return container.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
      return container.contains(o);
    }

    @Override
    public Iterator<T> iterator() {
      return container.iterator();
    }

    @Override
    public Object[] toArray() {
      return container.toArray();
    }

    @Override
    public <E> E[] toArray(E[] a) {
      return container.toArray(a);
    }

    @Override
    public boolean add(T t) {
      return container.add(t);
    }

    @Override
    public boolean remove(Object o) {
      return container.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
      return container.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
      return container.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
      return container.addAll(index, c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
      return container.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
      return container.retainAll(c);
    }

    @Override
    public void clear() {
      container.clear();
    }

    @Override
    public T get(int index) {
      return container.get(index);
    }

    @Override
    public T set(int index, T element) {
      return container.set(index, element);
    }

    @Override
    public void add(int index, T element) {
      container.add(index, element);
    }

    @Override
    public T remove(int index) {
      return container.remove(index);
    }

    @Override
    public int indexOf(Object o) {
      return container.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
      return container.lastIndexOf(o);
    }

    @Override
    public ListIterator<T> listIterator() {
      return container.listIterator();
    }

    @Override
    public ListIterator<T> listIterator(int index) {
      return container.listIterator(index);
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
      return container.subList(fromIndex, toIndex);
    }
  }



  private static void assertIsImmutableListOf(List<String> list, String... contents) {
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
  }

}
