package java.util.stream;

import java.lang.Object;
import java.lang.Override;
import java.lang.Runnable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
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
import java.util.function.UnaryOperator;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.StreamSupport;

public interface Stream<T> extends BaseStream<T, Stream<T>> {
  static final class ValueConsumer<T> implements Consumer<T> {
    T value;

    @Override
    public void accept(T value) {
      this.value = value;
    }
  }

  public static <T> Stream.Builder<T> builder() {
    return new Builder<T>() {
      private List<T> items = new ArrayList<>();
      @Override
      public void accept(T t) {
        items.add(t);
      }

      @Override
      public Stream<T> build() {
        return items.stream();
      }
    };
  }

  public static <T> Stream<T> concat(Stream<? extends T> a, Stream<? extends T> b) {
    return of(a, b).flatMap(stream -> stream);
  }

  public static <T> Stream<T> empty() {
    return new EmptyStreamSource<>();
  }

  public static <T> Stream<T> generate(Supplier<T> s) {
    return iterate(s.get(), prev -> s.get());
  }

  public static <T> Stream<T> iterate(T seed, UnaryOperator<T> f) {
    return StreamSupport.stream(new Spliterators.AbstractSpliterator<T>(Long.MAX_VALUE, Spliterator.ORDERED) {
      private T next = seed;
      @Override
      public boolean tryAdvance(Consumer<? super T> action) {
        action.accept(next);
        next = f.apply(next);
        return true;
      }
    }, false);
  }

  public static <T> Stream<T> of(T t) {
    return null;//TODO
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
      return null;//new IntStream.EmptyStreamSource();//TODO
    }

    @Override
    public LongStream mapToLong(ToLongFunction<? super T> mapper) {
      return null;//new LongStream.EmptyStreamSource();//TODO
    }

    @Override
    public DoubleStream mapToDouble(ToDoubleFunction<? super T> mapper) {
      return null;//new DoubleStream.EmptyStreamSource();//TODO
    }

    @Override
    public <R> Stream<R> flatMap(Function<? super T, ? extends  Stream<? extends R>> mapper) {
      return (Stream) this;
    }

    @Override
    public IntStream flatMapToInt(Function<? super T, ? extends IntStream> mapper) {
      return null;//new IntStream.EmptyStreamSource();//TODO
    }

    @Override
    public LongStream flatMapToLong(Function<? super T, ? extends LongStream> mapper) {
      return null;//new LongStream.EmptyStreamSource();//TODO
    }

    @Override
    public DoubleStream flatMapToDouble(Function<? super T, ? extends DoubleStream> mapper) {
      return null;//new DoubleStream.EmptyStreamSource();//TODO
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
      //nothing to do
    }

    @Override
    public void forEachOrdered(Consumer<? super T> action) {
      //nothing to do
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
      return Collections.emptyIterator();
    }

    @Override
    public Spliterator<T> spliterator() {
      return new Spliterators.AbstractSpliterator<T>(0, Spliterator.SIZED) {
        @Override
        public boolean tryAdvance(Consumer<? super T> action) {
          return false;
        }
      };
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

  static final class MappedSpliterator<U, T> extends Spliterators.AbstractSpliterator<T> {
    private final Function<? super U, ? extends T> map;
    private final Spliterator<U> original;

    public MappedSpliterator(Function<? super U, ? extends T> map, Spliterator<U> original) {
      super(original.estimateSize(), original.characteristics());
      this.map = map;
      this.original = original;
    }

    @Override
    public boolean tryAdvance(final Consumer<? super T> action) {
      return original.tryAdvance(u -> action.accept(map.apply(u)));
    }
  }
  static final class FilterSpliterator<T> extends Spliterators.AbstractSpliterator<T> {
    private final Predicate<? super T> filter;
    private final Spliterator<T> original;

    public FilterSpliterator(Predicate<? super T> filter, Spliterator<T> original) {
      super(original.estimateSize(), original.characteristics() & ~Spliterator.SIZED);
      this.filter = filter;
      this.original = original;
    }

    @Override
    public boolean tryAdvance(final Consumer<? super T> action) {
      boolean[] found = {false};
      while (original.tryAdvance(item -> {
        if (filter.test(item)) {
          found[0] = true;
          action.accept(item);
        }
      })) {
        if (found[0]) {
          return true;
        }
      }

      return false;
    }
  }
  static final class SkipSpliterator<T> extends Spliterators.AbstractSpliterator<T> {
    private long skip;
    private final Spliterator<T> original;

    public SkipSpliterator(long skip, Spliterator<T> original) {
      super(original.estimateSize() - skip, original.characteristics());
      this.skip = skip;
      this.original = original;
    }

    @Override
    public boolean tryAdvance(Consumer<? super T> action) {
      while (skip-- > 0) {
        original.tryAdvance(ignore -> {});
      }
      return original.tryAdvance(action);
    }
  }
  static final class LimitSpliterator<T> extends Spliterators.AbstractSpliterator<T> {
    private final long limit;
    private final Spliterator<T> original;
    private int position = 0;

    public LimitSpliterator(long limit, Spliterator<T> original) {
      super(original.estimateSize(), original.characteristics());
      this.limit = limit;
      this.original = original;
    }

    @Override
    public boolean tryAdvance(Consumer<? super T> action) {
      if (limit <= position++) {
        return false;
      }
      return original.tryAdvance(action);
    }
  }

  static class StreamSource<T> implements Stream<T> {
    private final Spliterator<T> spliterator;

    public StreamSource(Spliterator<T> spliterator) {
      this.spliterator = spliterator;
    }

    //terminal
    @Override
    public Spliterator<T> spliterator() {
      return spliterator;
    }

    @Override
    public Iterator<T> iterator() {
      return collect(Collectors.toList()).iterator();
    }

    @Override
    public long count() {
      long count = 0;
      while (spliterator.tryAdvance(a -> {})) {
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
      spliterator.forEachRemaining(action);
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
    public <R> R collect(Supplier<R> supplier, BiConsumer<R, ? super T> accumulator, final BiConsumer<R, R> combiner) {
      return collect(Collector.of(supplier, accumulator, (a, b) -> {
        combiner.accept(a, b);
        return a;
      }));
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
      ValueConsumer<T> holder = new ValueConsumer<T>();
      if (spliterator.tryAdvance(holder)) {
        return Optional.of(holder.value);
      }
      return Optional.empty();
    }

    @Override
    public Optional<T> findAny() {
      return findFirst();
    }

    @Override
    public boolean anyMatch(Predicate<? super T> predicate) {
      return filter(predicate).findFirst().isPresent();
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
      ValueConsumer<T> consumer = new ValueConsumer<T>();
      if (!spliterator.tryAdvance(consumer)) {
        return Optional.empty();
      }
      return Optional.of(reduce(consumer.value, accumulator));
    }

    @Override
    public <U> U reduce(U identity, final BiFunction<U, ? super T, U> accumulator, BinaryOperator<U> combiner) {
      final ValueConsumer<U> consumer = new ValueConsumer<U>();
      spliterator.forEachRemaining(item -> consumer.accept(accumulator.apply(consumer.value, item)));
      return consumer.value;
    }
    //end terminal

    //intermediate
    @Override
    public Stream<T> filter(Predicate<? super T> predicate) {
      return new StreamSource<T>(new FilterSpliterator<T>(predicate, spliterator));
    }

    @Override
    public <R> Stream<R> map(Function<? super T, ? extends R> mapper) {
      return new StreamSource<R>(new MappedSpliterator<T, R>(mapper, spliterator));
    }

    @Override
    public IntStream mapToInt(ToIntFunction<? super T> mapper) {
      return null;//TODO
    }

    @Override
    public LongStream mapToLong(ToLongFunction<? super T> mapper) {
      return null;//TODO
    }

    @Override
    public DoubleStream mapToDouble(ToDoubleFunction<? super T> mapper) {
      return null;//TODO
    }

    @Override
    public <R> Stream<R> flatMap(final Function<? super T, ? extends Stream<? extends R>> mapper) {
      final Spliterator<? extends Stream<? extends R>> spliteratorOfStreams = map(mapper).spliterator();
      return new StreamSource<R>(new Spliterators.AbstractSpliterator<R>(Long.MAX_VALUE, 0) {
        Stream<? extends R> nextStream;
        Spliterator<? extends R> next;
        @Override
        public boolean tryAdvance(Consumer<? super R> action) {
          while (advanceToNextSpliterator()) {//look for a new spliterator
            if (next.tryAdvance(action)) {//if we have one, try to read and use it
              return true;
            } else {
              nextStream.close();
              nextStream = null;
              next = null;//failed, null it out so we can find another
            }
          }
          return false;
        }
        private boolean advanceToNextSpliterator() {
          while (next == null) {
            if (!spliteratorOfStreams.tryAdvance(n -> {
              if (n != null) {
                nextStream = n;
                next = n.spliterator();
              }
            })) {
              return false;
            }
          }
          return true;
        }
      });
    }

    @Override
    public IntStream flatMapToInt(Function<? super T, ? extends IntStream> mapper) {
      return null;//TODO
    }

    @Override
    public LongStream flatMapToLong(Function<? super T, ? extends LongStream> mapper) {
      return null;//TODO
    }

    @Override
    public DoubleStream flatMapToDouble(Function<? super T, ? extends DoubleStream> mapper) {
      return null;//TODO
    }

    @Override
    public Stream<T> distinct() {
      HashSet<T> seen = new HashSet<>();
      return filter(seen::add);
    }

    @Override
    public Stream<T> sorted() {
      Comparator<T> c = (Comparator) Comparator.naturalOrder();
      return sorted(c);
    }

    @Override
    public Stream<T> sorted(final Comparator<? super T> comparator) {
      return new StreamSource<T>(new Spliterators.AbstractSpliterator<T>(spliterator.estimateSize(), spliterator.characteristics() | Spliterator.SORTED) {
        Spliterator<T> ordered = null;
        @Override
        public boolean tryAdvance(Consumer<? super T> action) {
          if (ordered == null) {
            List<T> list = new ArrayList<>();
            spliterator.forEachRemaining(item -> list.add(item));
            Collections.sort(list, comparator);
            ordered = list.spliterator();
          }
          return ordered.tryAdvance(action);
        }
      });
    }

    @Override
    public Stream<T> peek(final Consumer<? super T> peek) {
      return new StreamSource<T>(new Spliterators.AbstractSpliterator<T>(spliterator.estimateSize(), spliterator.characteristics()) {
        @Override
        public boolean tryAdvance(final Consumer<? super T> innerAction) {
          return spliterator.tryAdvance(item -> {
            peek.accept(item);
            innerAction.accept(item);
          });
        }
      });
    }

    @Override
    public Stream<T> limit(long maxSize) {
      return new StreamSource<T>(new LimitSpliterator<T>(maxSize, spliterator));
    }

    @Override
    public Stream<T> skip(long n) {
      return new StreamSource<T>(new SkipSpliterator<T>(n, spliterator));
    }

    @Override
    public boolean isParallel() {
      return false;
    }

    @Override
    public Stream<T> sequential() {
      return null;//TODO
    }

    @Override
    public Stream<T> parallel() {
      return this;//do nothing, no such thing as gwt+parallel
    }

    @Override
    public Stream<T> unordered() {
      return null;//TODO
    }

    @Override
    public Stream<T> onClose(Runnable closeHandler) {
      return null;//TODO
    }

    @Override
    public void close() {
      //TODO
    }
  }

}
