package java.util.stream;

import java.lang.Long;
import java.lang.Override;
import java.util.LongSummaryStatistics;
import java.util.OptionalDouble;
import java.util.OptionalLong;
import java.util.PrimitiveIterator;
import java.util.Spliterator;
import java.util.function.BiConsumer;
import java.util.function.LongBinaryOperator;
import java.util.function.LongConsumer;
import java.util.function.LongFunction;
import java.util.function.LongPredicate;
import java.util.function.LongSupplier;
import java.util.function.LongToDoubleFunction;
import java.util.function.LongToIntFunction;
import java.util.function.LongUnaryOperator;
import java.util.function.ObjLongConsumer;
import java.util.function.Supplier;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

public interface LongStream extends BaseStream<Long,LongStream> {

  static LongStream.Builder builder() {
    return null;//TODO
  }

  static LongStream concat(LongStream a, LongStream b) {
    return null;//TODO
  }

  static LongStream empty() {
    return null;//TODO
  }

  static LongStream generate(LongSupplier s) {
    return null;//TODO
  }

  static LongStream iterate(long seed, LongUnaryOperator f) {
    return null;//TODO
  }

  static LongStream of(long... values) {
    return null;//TODO
  }

  static LongStream of(long t) {
    return null;//TODO
  }

  static LongStream range(long startInclusive, long endExclusive) {
    return null;//TODO
  }

  static LongStream rangeClosed(long startInclusive, long endInclusive) {
    return null;//TODO
  }

  public interface Builder extends LongConsumer {
    @Override
    void accept(long t);

    default LongStream.Builder add(long t) {
      accept(t);
      return this;
    }

    LongStream build();
  }

  boolean allMatch(LongPredicate predicate);

  boolean anyMatch(LongPredicate predicate);

  DoubleStream asDoubleStream();

  OptionalDouble average();

  Stream<Long> boxed();

  <R> R collect(Supplier<R> supplier, ObjLongConsumer<R> accumulator, BiConsumer<R,R> combiner);

  long count();

  LongStream distinct();

  LongStream filter(LongPredicate predicate);

  OptionalLong findAny();

  OptionalLong findFirst();

  LongStream flatMap(LongFunction<? extends LongStream> mapper);

  void forEach(LongConsumer action);

  void forEachOrdered(LongConsumer action);

  PrimitiveIterator.OfLong iterator();

  LongStream limit(long maxSize);

  LongStream map(LongUnaryOperator mapper);

  DoubleStream mapToDouble(LongToDoubleFunction mapper);

  IntStream mapToInt(LongToIntFunction mapper);

  <U> Stream<U> mapToObj(LongFunction<? extends U> mapper);

  OptionalLong max();

  OptionalLong min();

  boolean noneMatch(LongPredicate predicate);

  LongStream parallel();

  LongStream peek(LongConsumer action);

  OptionalLong reduce(LongBinaryOperator op);

  long reduce(long identity, LongBinaryOperator op);

  LongStream sequential();

  LongStream skip(long n);

  LongStream sorted();

  Spliterator.OfLong spliterator();

  long sum();

  LongSummaryStatistics summaryStatistics();

  long[] toArray();
}
