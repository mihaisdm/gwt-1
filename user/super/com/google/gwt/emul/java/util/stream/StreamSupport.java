package java.util.stream;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Stream;

public final class StreamSupport {

  public static DoubleStream doubleStream(Spliterator.OfDouble spliterator, boolean parallel) {
    return new DoubleStream.DoubleStreamSource(null, spliterator);
  }

  public static DoubleStream doubleStream(Supplier<? extends Spliterator.OfDouble> supplier, int characteristics, boolean parallel) {
    return Stream.generate(supplier).flatMapToDouble(doubleSpliterator -> doubleStream(doubleSpliterator, parallel));
  }

  public static IntStream intStream(Spliterator.OfInt spliterator, boolean parallel) {
    return new IntStream.IntStreamSource(null, spliterator);
  }

  public static IntStream intStream(Supplier<? extends Spliterator.OfInt> supplier, int characteristics, boolean parallel) {
    return Stream.generate(supplier).flatMapToInt(intSpliterator -> intStream(intSpliterator, parallel));
  }

  public static LongStream longStream(Spliterator.OfLong spliterator, boolean parallel) {
    return new LongStream.LongStreamSource(null, spliterator);
  }

  public static LongStream longStream(Supplier<? extends Spliterator.OfLong> supplier, int characteristics, final boolean parallel) {
    return Stream.generate(supplier).flatMapToLong(longSpliterator -> longStream(longSpliterator, parallel));
  }

  public static <T> Stream<T> stream(Spliterator<T> spliterator, boolean parallel) {
    return new Stream.StreamSource<T>(null, spliterator);
  }

  public static <T> Stream<T> stream(Supplier<? extends Spliterator<T>> supplier, int characteristics, final boolean parallel) {
    return Stream.generate(supplier).flatMap(spliterator -> stream(spliterator, parallel));
  }

}
