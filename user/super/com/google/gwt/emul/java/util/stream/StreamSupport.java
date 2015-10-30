package java.util.stream;

import java.util.*;
import java.util.stream.Stream;

public final class StreamSupport {

  public static DoubleStream doubleStream(Spliterator.OfDouble spliterator, boolean parallel) {
    return null;//TODO
  }

  public static DoubleStream doubleStream(Supplier<? extends Spliterator.OfDouble> supplier, int characteristics, boolean parallel) {
    return null;//TODO
  }

  public static IntStream intStream(Spliterator.OfInt spliterator, boolean parallel) {
    return null;//TODO
  }

  public static IntStream intStream(Supplier<? extends Spliterator.OfInt> supplier, int characteristics, boolean parallel) {
    return null;//TODO
  }

  public static LongStream longStream(Spliterator.OfLong spliterator, boolean parallel) {
    return null;//TODO
  }

  public static LongStream longStream(Supplier<? extends Spliterator.OfLong> supplier, int characteristics, boolean parallel) {
    return null;//TODO
  }

  public static <T> Stream<T> stream(Spliterator<T> spliterator, boolean parallel) {
    return new Stream.StreamSource<T>(spliterator);
  }

  public static <T> Stream<T> stream(Supplier<? extends Spliterator<T>> supplier, int characteristics, final boolean parallel) {
    return Stream.of(supplier).flatMap(spliterator -> stream(spliterator, parallel));
  }

}
