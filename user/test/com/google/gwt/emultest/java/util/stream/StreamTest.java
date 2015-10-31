package com.google.gwt.emultest.java.util.stream;

import com.google.gwt.emultest.java.util.EmulTestBase;

import java.lang.IllegalStateException;
import java.lang.Object;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest extends EmulTestBase {

  public void testSimpleStream() {
    List<Object> letters = Stream.of("e", "a", "c", "b", "d", "f")
        .filter(letter -> !letter.equals("c"))
        .map(letter -> letter + " ")
        .sorted()
        .filter(letter -> !letter.equals("b "))
        .collect(Collectors.toList());

    assertEquals(new String[]{"a ", "d ", "e ", "f "}, letters);

  }

}