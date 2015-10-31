package com.google.gwt.emultest.java.util.stream;

import com.google.gwt.emultest.java.util.EmulTestBase;

import java.lang.IllegalStateException;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collector;
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

  public void testStreamOfOne() {
    assertEquals(Arrays.asList(""), Stream.of("").collect(Collectors.toList()));
  }

  public void testBuilder() {
    Stream<String> s = Stream.<String>builder()
        .add("1")
        .add("3")
        .add("2")
        .build();

    Optional<String> max = s.filter(str -> !str.equals("3")).max(Comparator.<String>naturalOrder());
    assertTrue(max.isPresent());
    assertEquals("2", max.get());

    Stream.Builder<Object> builder = Stream.builder();
    Stream<Object> built = builder.build();
    assertEquals(0, built.count());
    try {
      builder.build();
      fail("build() after build() should fail");
    } catch (IllegalStateException expected) {
      //expected
    }
    try {
      builder.add("asdf");
      fail("add() after build() should fail");
    } catch (IllegalStateException expected) {
      //expected
    }
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