package java.util.stream;

import java.lang.Override;
import java.util.DoubleSummaryStatistics;
import java.util.OptionalDouble;
import java.util.PrimitiveIterator;
import java.util.Spliterator;
import java.util.function.BiConsumer;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleConsumer;
import java.util.function.DoubleFunction;
import java.util.function.DoublePredicate;
import java.util.function.DoubleSupplier;
import java.util.function.DoubleToIntFunction;
import java.util.function.DoubleToLongFunction;
import java.util.function.DoubleUnaryOperator;
import java.util.function.ObjDoubleConsumer;
import java.util.function.Supplier;

public interface DoubleStream extends BaseStream<Double,DoubleStream> {

  static DoubleStream.Builder builder() {
    return null;//TODO
  }

  static DoubleStream concat(DoubleStream a, DoubleStream b) {
    return null;//TODO
  }

  static DoubleStream empty() {
    return null;//TODO
  }

  static DoubleStream generate(DoubleSupplier s) {
    return null;//TODO
  }

  static DoubleStream iterate(double seed, DoubleUnaryOperator f) {
    return null;//TODO
  }

  static DoubleStream of(double... values) {
    return null;//TODO
  }

  static DoubleStream of(double t) {
    return null;//TODO
  }

  public interface Builder extends DoubleConsumer {
    @Override
    void	accept(double t);

    default DoubleStream.Builder	add(double t) {
      accept(t);
      return this;
    }

    DoubleStream	build();
  }

  boolean allMatch(DoublePredicate predicate);

  boolean anyMatch(DoublePredicate predicate);

  OptionalDouble average();

  Stream<Double> boxed();

  <R> R collect(Supplier<R> supplier, ObjDoubleConsumer<R> accumulator, BiConsumer<R,R> combiner);

  long count();

  DoubleStream distinct();

  DoubleStream filter(DoublePredicate predicate);

  OptionalDouble findAny();

  OptionalDouble findFirst();

  DoubleStream flatMap(DoubleFunction<? extends DoubleStream> mapper);

  void forEach(DoubleConsumer action);

  void forEachOrdered(DoubleConsumer action);

  PrimitiveIterator.OfDouble iterator();

  DoubleStream limit(long maxSize);

  DoubleStream map(DoubleUnaryOperator mapper);

  IntStream mapToInt(DoubleToIntFunction mapper);

  LongStream mapToLong(DoubleToLongFunction mapper);

  <U> Stream<U> mapToObj(DoubleFunction<? extends U> mapper);

  OptionalDouble max();

  OptionalDouble min();

  boolean noneMatch(DoublePredicate predicate);

  DoubleStream parallel();

  DoubleStream peek(DoubleConsumer action);

  OptionalDouble reduce(DoubleBinaryOperator op);

  double reduce(double identity, DoubleBinaryOperator op);

  DoubleStream sequential();

  DoubleStream skip(long n);

  DoubleStream sorted();

  Spliterator.OfDouble spliterator();

  double sum();

  DoubleSummaryStatistics summaryStatistics();

  double[] toArray();

}
