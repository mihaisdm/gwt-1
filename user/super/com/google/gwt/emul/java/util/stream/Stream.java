package java.util.stream;

import java.lang.Object;
import java.lang.Override;
import java.lang.Runnable;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.StreamSupport;

public interface Stream<T> extends BaseStream<T, Stream<T>> {

  public static static <T> Stream.Builder<T> builder() {
    return null;
  }

  public static <T> Stream<T> concat(Stream<? extends T> a, Stream<? extends T> b) {
    return null;
  }

  public static <T> Stream<T> empty() {
    return new EmptyStreamSource<>();
  }

  public static <T> Stream<T> generate(Supplier<T> s) {
    return iterate(s.get(), prev -> s.get());
  }

  public static <T> Stream<T> iterate(T seed, UnaryOperator<T> f) {
    Object[] current = new Object[] {seed};

    return new StreamSource<>(
        () -> Optional.of((T)current[0]),
        () -> {
          current[0] = f.apply((T)current[0]);
          return Optional.of((T)current[0]);
        }
    );
  }

  public static <T> Stream<T> of(T t) {
    return null;
  }

  public static <T> Stream<T> of(T... values) {
    return StreamSupport.stream(Spliterators.spliterator(values, Spliterator.ORDERED), false);
  }

  public interface Builder<T> {

    void accept(T t);

    default Stream.Builder<T> add(T t) {
      accept(t);
      return this;
    }

    Stream<T> build();
  }

  boolean allMatch(Predicate<? super T> predicate);

  boolean anyMatch(Predicate<? super T> predicate);

  <R,A> R collect(Collector<? super T,A,R> collector);

  <R> R collect(Supplier<R> supplier, BiConsumer<R,? super T> accumulator, BiConsumer<R,R> combiner);

  long count();

  Stream<T> distinct();

  Stream<T> filter(Predicate<? super T> predicate);

  Optional<T> findAny();

  Optional<T> findFirst();

  <R> Stream<R> flatMap(Function<? super T,? extends Stream<? extends R>> mapper);

  DoubleStream flatMapToDouble(Function<? super T,? extends DoubleStream> mapper);

  IntStream flatMapToInt(Function<? super T,? extends IntStream> mapper);

  LongStream flatMapToLong(Function<? super T,? extends LongStream> mapper);

  void forEach(Consumer<? super T> action);

  void forEachOrdered(Consumer<? super T> action);

  Stream<T> limit(long maxSize);

  <R> Stream<R> map(Function<? super T,? extends R> mapper);

  DoubleStream mapToDouble(ToDoubleFunction<? super T> mapper);

  IntStream mapToInt(ToIntFunction<? super T> mapper);

  LongStream mapToLong(ToLongFunction<? super T> mapper);

  Optional<T> max(Comparator<? super T> comparator);

  Optional<T> min(Comparator<? super T> comparator);

  boolean noneMatch(Predicate<? super T> predicate);

  Stream<T> peek(Consumer<? super T> action);

  Optional<T> reduce(BinaryOperator<T> accumulator);

  T reduce(T identity, BinaryOperator<T> accumulator);

  <U> U reduce(U identity, BiFunction<U,? super T,U> accumulator, BinaryOperator<U> combiner);

  Stream<T> skip(long n);

  Stream<T> sorted();

  Stream<T> sorted(Comparator<? super T> comparator);

  Object[] toArray();

  <A> A[] toArray(IntFunction<A[]> generator);


  static class EmptyStreamSource<T> implements Stream<T> {
    @Override
    public  Stream<T> filter(Predicate<? super T> predicate) {
      return this;
    }

    @Override
    public <R>  Stream<R> map(Function<? super T, ? extends R> mapper) {
      return (Stream) this;
    }

    @Override
    public IntStream mapToInt(ToIntFunction<? super T> mapper) {
      return new IntStream.EmptyStreamSource();
    }

    @Override
    public LongStream mapToLong(ToLongFunction<? super T> mapper) {
      return new LongStream.EmptyStreamSource();
    }

    @Override
    public DoubleStream mapToDouble(ToDoubleFunction<? super T> mapper) {
      return new DoubleStream.EmptyStreamSource();
    }

    @Override
    public <R> Stream<R> flatMap(Function<? super T, ? extends  Stream<? extends R>> mapper) {
      return (Stream) this;
    }

    @Override
    public IntStream flatMapToInt(Function<? super T, ? extends IntStream> mapper) {
      return new IntStream.EmptyStreamSource();
    }

    @Override
    public LongStream flatMapToLong(Function<? super T, ? extends LongStream> mapper) {
      return new LongStream.EmptyStreamSource();
    }

    @Override
    public DoubleStream flatMapToDouble(Function<? super T, ? extends DoubleStream> mapper) {
      return new DoubleStream.EmptyStreamSource();
    }

    @Override
    public Stream<T> distinct() {
      return this;
    }

    @Override
    public Stream<T> sorted() {
      return this;
    }

    @Override
    public Stream<T> sorted(Comparator<? super T> comparator) {
      return this;
    }

    @Override
    public Stream<T> peek(Consumer<? super T> action) {
      return this;
    }

    @Override
    public Stream<T> limit(long maxSize) {
      return this;
    }

    @Override
    public Stream<T> skip(long n) {
      return this;
    }

    @Override
    public void forEach(Consumer<? super T> action) {

    }

    @Override
    public void forEachOrdered(Consumer<? super T> action) {

    }

    @Override
    public Object[] toArray() {
      return new Object[0];
    }

    @Override
    public <A> A[] toArray(IntFunction<A[]> generator) {
      return generator.apply(0);
    }

    @Override
    public T reduce(T identity, BinaryOperator<T> accumulator) {
      return identity;
    }

    @Override
    public Optional<T> reduce(BinaryOperator<T> accumulator) {
      return Optional.empty();
    }

    @Override
    public <U> U reduce(U identity, BiFunction<U, ? super T, U> accumulator, BinaryOperator<U> combiner) {
      return identity;
    }

    @Override
    public <R> R collect(Supplier<R> supplier, BiConsumer<R, ? super T> accumulator, BiConsumer<R, R> combiner) {
      return supplier.get();
    }

    @Override
    public <R, A> R collect(Collector<? super T, A, R> collector) {
      return collector.finisher().apply(collector.supplier().get());
    }

    @Override
    public Optional<T> min(Comparator<? super T> comparator) {
      return Optional.empty();
    }

    @Override
    public Optional<T> max(Comparator<? super T> comparator) {
      return Optional.empty();
    }

    @Override
    public long count() {
      return 0;
    }

    @Override
    public boolean anyMatch(Predicate<? super T> predicate) {
      return false;
    }

    @Override
    public boolean allMatch(Predicate<? super T> predicate) {
      return false;
    }

    @Override
    public boolean noneMatch(Predicate<? super T> predicate) {
      return true;
    }

    @Override
    public Optional<T> findFirst() {
      return Optional.empty();
    }

    @Override
    public Optional<T> findAny() {
      return Optional.empty();
    }

    @Override
    public Iterator<T> iterator() {
      return null;
    }

    @Override
    public Spliterator<T> spliterator() {
      return null;
    }

    @Override
    public boolean isParallel() {
      return false;
    }

    @Override
    public Stream<T> sequential() {
      return this;
    }

    @Override
    public Stream<T> parallel() {
      return this;
    }

    @Override
    public Stream<T> unordered() {
      return this;
    }

    @Override
    public Stream<T> onClose(Runnable closeHandler) {
      return this;
    }

    @Override
    public void close() {

    }
  }

  private static class StreamSource<T> implements Stream<T> {
    private final Supplier<Optional<T>> peek;
    private final Supplier<Optional<T>> pop;

    public StreamSource(Supplier<Optional<T>> peek, Supplier<Optional<T>> pop) {
      this.peek = peek;
      this.pop = pop;
    }

    //terminal
    @Override
    public Spliterator<T> spliterator() {         //TODO size
      return Spliterators.spliterator(iterator(), 12345, 0);
    }

    @Override
    public Iterator<T> iterator() {
      return new Iterator<T>() {
        @Override
        public boolean hasNext() {
          return peek.get().isPresent();
        }

        @Override
        public T next() {
          return pop.get().get();
        }
      };
    }

    @Override
    public long count() {
      long count = 0;
      while (peek.get().isPresent()) {
        count++;
      }
      return count;
    }

    @Override
    public void forEach(Consumer<? super T> action) {
      forEachOrdered(action);
    }

    @Override
    public void forEachOrdered(Consumer<? super T> action) {
      Optional<T> next = pop.get();
      while (next.isPresent()) {
        next.ifPresent(action);
        next = pop.get();
      }
    }

    @Override
    public Object[] toArray() {
      return toArray(Object[]::new);
    }

    @Override
    public <A> A[] toArray(IntFunction<A[]> generator) {
      List<T> collected = collect(Collectors.toList());
      return collected.toArray(generator.apply(collected.size()));
    }

    @Override
    public <R> R collect(Supplier<R> supplier, BiConsumer<R, ? super T> accumulator, BiConsumer<R, R> combiner) {
      return collect(Collector.of(supplier, accumulator, combiner));
    }

    @Override
    public <R, A> R collect(final Collector<? super T, A, R> collector) {
      return collector.finisher().apply(reduce(
          collector.supplier().get(),
          (a, t) -> {
            collector.accumulator().accept(a, t);
            return a;
          },
          collector.combiner()
      ));
    }

    @Override
    public Optional<T> findFirst() {
      return pop.get();
    }

    @Override
    public Optional<T> findAny() {
      return pop.get();
    }

    @Override
    public boolean anyMatch(Predicate<? super T> predicate) {
      return filter(predicate).findFirst().ifPresent();
    }

    @Override
    public boolean allMatch(final Predicate<? super T> predicate) {
      return !filter(item -> !predicate.test(item)).findFirst().isPresent();
    }

    @Override
    public boolean noneMatch(final Predicate<? super T> predicate) {
      return anyMatch(item -> !predicate.test(item));
    }

    @Override
    public Optional<T> min(final Comparator<? super T> comparator) {
      return reduce((left, right) -> comparator.compare(left, right) < 0 ? left : right);
    }

    @Override
    public Optional<T> max(final Comparator<? super T> comparator) {
      return min((o1, o2) -> -comparator.compare(o1, o2));
    }


    @Override
    public T reduce(T identity, BinaryOperator<T> accumulator) {
      return this.<T>reduce(identity, accumulator, accumulator);
    }

    @Override
    public Optional<T> reduce(BinaryOperator<T> accumulator) {
      if (!peek.get().isPresent()) {
        return Optional.empty();
      }
      return reduce(pop.get(), accumulator);
    }

    @Override
    public <U> U reduce(U identity, BiFunction<U, ? super T, U> accumulator, BinaryOperator<U> combiner) {
      return null;
    }
    //end terminal

    //intermediate
    @Override
    public Stream<T> filter(Predicate<? super T> predicate) {
      return new StreamSource<T>();
    }

    @Override
    public <R> Stream<R> map(Function<? super T, ? extends R> mapper) {
      return new StreamSource<R>();
    }

    @Override
    public IntStream mapToInt(ToIntFunction<? super T> mapper) {
      return null;
    }

    @Override
    public LongStream mapToLong(ToLongFunction<? super T> mapper) {
      return null;
    }

    @Override
    public DoubleStream mapToDouble(ToDoubleFunction<? super T> mapper) {
      return null;
    }

    @Override
    public <R> Stream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper) {
      return null;
    }

    @Override
    public IntStream flatMapToInt(Function<? super T, ? extends IntStream> mapper) {
      return null;
    }

    @Override
    public LongStream flatMapToLong(Function<? super T, ? extends LongStream> mapper) {
      return null;
    }

    @Override
    public DoubleStream flatMapToDouble(Function<? super T, ? extends DoubleStream> mapper) {
      return null;
    }

    @Override
    public Stream<T> distinct() {
      return null;
    }

    @Override
    public Stream<T> sorted() {
      return null;
    }

    @Override
    public Stream<T> sorted(Comparator<? super T> comparator) {
      return null;
    }

    @Override
    public Stream<T> peek(Consumer<? super T> action) {
      return null;
    }

    @Override
    public Stream<T> limit(long maxSize) {
      return null;
    }

    @Override
    public Stream<T> skip(long n) {
      return null;
    }

    @Override
    public boolean isParallel() {
      return false;
    }

    @Override
    public Stream<T> sequential() {
      return null;
    }

    @Override
    public Stream<T> parallel() {
      return this;//do nothing, no such thing as gwt+parallel
    }

    @Override
    public Stream<T> unordered() {
      return null;
    }

    @Override
    public Stream<T> onClose(Runnable closeHandler) {
      return null;
    }

    @Override
    public void close() {

    }
  }

}
