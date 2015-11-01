package java.util.stream;

import java.lang.Override;
import java.util.IntSummaryStatistics;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.PrimitiveIterator;
import java.util.Spliterator;
import java.util.function.BiConsumer;
import java.util.function.IntBinaryOperator;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;
import java.util.function.IntPredicate;
import java.util.function.IntSupplier;
import java.util.function.IntToDoubleFunction;
import java.util.function.IntToLongFunction;
import java.util.function.IntUnaryOperator;
import java.util.function.ObjIntConsumer;
import java.util.function.Supplier;
import java.util.stream.DoubleStream;
import java.util.stream.LongStream;

public interface IntStream extends BaseStream<Integer,IntStream> {

  static IntStream.Builder builder() {
    return null;//TODO
  }

  static IntStream concat(IntStream a, IntStream b) {
    return null;//TODO
  }

  static IntStream empty() {
    return null;//TODO
  }

  static IntStream generate(IntSupplier s) {
    return null;//TODO
  }

  static IntStream iterate(int seed, IntUnaryOperator f) {
    return null;//TODO
  }

  static IntStream of(int... values) {
    return null;//TODO
  }

  static IntStream of(int t) {
    return null;//TODO
  }

  static IntStream range(int startInclusive, int endExclusive) {
    return null;//TODO
  }

  static IntStream rangeClosed(int startInclusive, int endInclusive) {
    return null;//TODO
  }

  public interface Builder extends IntConsumer {
    @Override
    void accept(int t);

    default IntStream.Builder add(int t) {
      accept(t);
      return this;
    }

    IntStream build();
  }

  boolean allMatch(IntPredicate predicate);

  boolean anyMatch(IntPredicate predicate);

  DoubleStream asDoubleStream();

  LongStream asLongStream();

  OptionalDouble average();

  Stream<Integer> boxed();

  <R> R collect(Supplier<R> supplier, ObjIntConsumer<R> accumulator, BiConsumer<R,R> combiner);

  long count();

  IntStream distinct();

  IntStream filter(IntPredicate predicate);

  OptionalInt findAny();

  OptionalInt findFirst();

  IntStream flatMap(IntFunction<? extends IntStream> mapper);

  void forEach(IntConsumer action);

  void forEachOrdered(IntConsumer action);

  PrimitiveIterator.OfInt iterator();

  IntStream limit(long maxSize);

  IntStream map(IntUnaryOperator mapper);

  DoubleStream mapToDouble(IntToDoubleFunction mapper);

  LongStream mapToLong(IntToLongFunction mapper);

  <U> Stream<U> mapToObj(IntFunction<? extends U> mapper);

  OptionalInt max();

  OptionalInt min();

  boolean noneMatch(IntPredicate predicate);

  IntStream parallel();

  IntStream peek(IntConsumer action);

  OptionalInt reduce(IntBinaryOperator op);

  int reduce(int identity, IntBinaryOperator op);

  IntStream sequential();

  IntStream skip(long n);

  IntStream sorted();

  Spliterator.OfInt spliterator();

  int sum();

  IntSummaryStatistics summaryStatistics();

  int[] toArray();
}
