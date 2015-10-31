package com.google.gwt.emultest.java.util.stream;

import com.google.gwt.emultest.java.util.EmulTestBase;

import java.lang.IllegalStateException;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest extends EmulTestBase {

  public void testEmptyStream() {
    Stream<Object> empty = Stream.empty();
    assertEquals(0, empty.count());
    try {
      empty.count();
      fail("second terminal operation should have thrown IllegalStateEx");
    } catch (IllegalStateException expected) {
      //expected
    }

    assertEquals(0, Stream.empty().limit(2).collect(Collectors.toList()).size());
  }



  public void testSimpleStream() {
    List<Object> letters = Stream.of("e", "a", "c", "b", "d", "f")
        .filter(letter -> !letter.equals("c"))
        .map(letter -> letter + " ")
        .sorted()
        .filter(letter -> !letter.equals("b "))
        .collect(Collectors.toList());

    assertEquals(new String[]{"a ", "d ", "e ", "f "}, letters);
  }

  public void testGenerate() {
    //infinite, but if you limit it is already too short to skip much
    assertEquals(new Integer[]{}, Stream.generate(makeGenerator()).limit(4).skip(5).toArray(Integer[]::new));

    assertEquals(new Integer[]{10, 11, 12, 13, 14}, Stream.generate(makeGenerator()).skip(10).limit(5).toArray(Integer[]::new));
  }

  private Supplier<Integer> makeGenerator() {
    return new Supplier<Integer>() {
      int next = 0;

      @Override
      public Integer get() {
        return next++;
      }
    };
  }

}