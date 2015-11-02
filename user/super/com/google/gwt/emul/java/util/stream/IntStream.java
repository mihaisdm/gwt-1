package java.util.stream;

import java.lang.Integer;
import java.lang.Override;
import java.lang.Runnable;
import java.util.IntSummaryStatistics;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.PrimitiveIterator;
import java.util.Spliterator;
import java.util.Spliterators;
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
import java.util.stream.Stream;

public interface IntStream extends BaseStream<Integer,IntStream> {

  static IntStream.Builder builder() {
    return null;//TODO
  }

  static IntStream concat(IntStream a, IntStream b) {
    return null;//TODO
  }

  static IntStream empty() {
    return new EmptyIntStreamSource(null);
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
  
  static class EmptyIntStreamSource extends TerminatableStream implements IntStream {
    public EmptyIntStreamSource(TerminatableStream previous) {
      super(previous);
    }

    @Override
    public IntStream filter(IntPredicate predicate) {
      throwIfTerminated();
      return this;
    }

    @Override
    public IntStream map(IntUnaryOperator mapper) {
      throwIfTerminated();
      return this;
    }

    @Override
    public <U> Stream<U> mapToObj(IntFunction<? extends U> mapper) {
      throwIfTerminated();
      return new Stream.EmptyStreamSource<U>(this);
    }

    @Override
    public LongStream mapToLong(IntToLongFunction mapper) {
      throwIfTerminated();
      return null;//TODO
    }

    @Override
    public DoubleStream mapToDouble(IntToDoubleFunction mapper) {
      throwIfTerminated();
      return null;//TODO
    }

    @Override
    public IntStream flatMap(IntFunction<? extends IntStream> mapper) {
      throwIfTerminated();
      return this;
    }

    @Override
    public IntStream distinct() {
      throwIfTerminated();
      return this;
    }

    @Override
    public IntStream sorted() {
      throwIfTerminated();
      return this;
    }

    @Override
    public IntStream peek(IntConsumer action) {
      throwIfTerminated();
      return this;
    }

    @Override
    public IntStream limit(long maxSize) {
      throwIfTerminated();
      return this;
    }

    @Override
    public IntStream skip(long n) {
      throwIfTerminated();
      return this;
    }

    @Override
    public void forEach(IntConsumer action) {
      terminate();
      //do nothing
    }

    @Override
    public void forEachOrdered(IntConsumer action) {
      terminate();
      //do nothing
    }

    @Override
    public int[] toArray() {
      terminate();
      return new int[0];
    }

    @Override
    public int reduce(int identity, IntBinaryOperator op) {
      terminate();
      return identity;
    }

    @Override
    public OptionalInt reduce(IntBinaryOperator op) {
      terminate();
      return OptionalInt.empty();
    }

    @Override
    public <R> R collect(Supplier<R> supplier, ObjIntConsumer<R> accumulator, BiConsumer<R, R> combiner) {
      terminate();
      return supplier.get();
    }

    @Override
    public int sum() {
      terminate();
      return 0;
    }

    @Override
    public OptionalInt min() {
      terminate();
      return OptionalInt.empty();
    }

    @Override
    public OptionalInt max() {
      terminate();
      return OptionalInt.empty();
    }

    @Override
    public long count() {
      terminate();
      return 0;
    }

    @Override
    public OptionalDouble average() {
      terminate();
      return OptionalDouble.empty();
    }

    @Override
    public IntSummaryStatistics summaryStatistics() {
      terminate();
      return new IntSummaryStatistics();
    }

    @Override
    public boolean anyMatch(IntPredicate predicate) {
      return false;
    }

    @Override
    public boolean allMatch(IntPredicate predicate) {
      terminate();
      return false;
    }

    @Override
    public boolean noneMatch(IntPredicate predicate) {
      terminate();
      return true;
    }

    @Override
    public OptionalInt findFirst() {
      terminate();
      return OptionalInt.empty();
    }

    @Override
    public OptionalInt findAny() {
      terminate();
      return OptionalInt.empty();
    }

    @Override
    public LongStream asLongStream() {
      throwIfTerminated();
      return null;//TODO
    }

    @Override
    public DoubleStream asDoubleStream() {
      throwIfTerminated();
      return null;//TODO
    }

    @Override
    public Stream<Integer> boxed() {
      throwIfTerminated();
      return new Stream.EmptyStreamSource<Integer>(this);
    }

    @Override
    public IntStream sequential() {
      throwIfTerminated();
      return this;
    }

    @Override
    public IntStream parallel() {
      throwIfTerminated();
      return this;
    }

    @Override
    public PrimitiveIterator.OfInt iterator() {
      return Spliterators.iterator(spliterator());
    }

    @Override
    public Spliterator.OfInt spliterator() {
      terminate();
      return Spliterators.emptyIntSpliterator();
    }

    @Override
    public boolean isParallel() {
      throwIfTerminated();
      return false;
    }

    @Override
    public IntStream unordered() {
      throwIfTerminated();
      return this;
    }

    @Override
    public IntStream onClose(Runnable closeHandler) {
      throwIfTerminated();
      return this;
    }

    @Override
    public void close() {
      throwIfTerminated();
    }
  }

}
