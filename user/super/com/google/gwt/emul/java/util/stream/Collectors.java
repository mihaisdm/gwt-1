package java.util.stream;

import java.lang.CharSequence;
import java.lang.IllegalStateException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IntSummaryStatistics;
import java.util.LongSummaryStatistics;
import java.util.Map;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;
import java.util.stream.Collector;


public final class Collectors {
  public static <T> Collector<T,?,Double> averagingDouble(ToDoubleFunction<? super T> mapper) {
    return null;//TODO
  }

  public static <T> Collector<T,?,Double> averagingInt(ToIntFunction<? super T> mapper) {
    return null;//TODO
  }

  public static <T> Collector<T,?,Double> averagingLong(ToLongFunction<? super T> mapper) {
    return null;//TODO
  }

  public static <T,A,R,RR> Collector<T,A,RR> collectingAndThen(Collector<T,A,R> downstream, Function<R,RR> finisher) {
    return Collector.of(
        downstream.supplier(),
        downstream.accumulator(),
        downstream.combiner(),
        downstream.finisher().andThen(finisher),
        downstream.characteristics().toArray(new Collector.Characteristics[downstream.characteristics().size()])
    );
  }

  public static <T> Collector<T,?,Long> counting() {
    return null;//TODO
  }

  public static <T,K> Collector<T,?,Map<K,List<T>>> groupingBy(Function<? super T,? extends K> classifier) {
    return null;//TODO
  }

  public static <T,K,A,D> Collector<T,?,Map<K,D>> groupingBy(Function<? super T,? extends K> classifier, Collector<? super T,A,D> downstream) {
    return null;//TODO
  }

  public static <T,K,D,A,M extends Map<K,D>> Collector<T,?,M> groupingBy(Function<? super T,? extends K> classifier, Supplier<M> mapFactory, Collector<? super T,A,D> downstream) {
    return null;//TODO
  }

//  public static <T,K> Collector<T,?,ConcurrentMap<K,List<T>>> groupingByConcurrent(Function<? super T,? extends K> classifier) {
//    return null;//TODO
//  }

//  public static <T,K,A,D> Collector<T,?,ConcurrentMap<K,D>> groupingByConcurrent(Function<? super T,? extends K> classifier, Collector<? super T,A,D> downstream) {
//    return null;//TODO
//  }

//  public static <T,K,A,D,M extends ConcurrentMap<K,D>> Collector<T,?,M> groupingByConcurrent(Function<? super T,? extends K> classifier, Supplier<M> mapFactory, Collector<? super T,A,D> downstream) {
//    return null;//TODO
//  }

  public static Collector<CharSequence,?,String> joining() {
    return null;//TODO
  }

  public static Collector<CharSequence,?,String> joining(CharSequence delimiter) {
    return null;//TODO
  }

  public static Collector<CharSequence,?,String> joining(CharSequence delimiter, CharSequence prefix, CharSequence suffix) {
    return null;//TODO
  }

  public static <T,U,A,R> Collector<T,?,R> mapping(Function<? super T,? extends U> mapper, Collector<? super U,A,R> downstream) {
    return null;//TODO
  }

  public static <T> Collector<T,?,Optional<T>> maxBy(Comparator<? super T> comparator) {
    return null;//TODO
  }

  public static <T> Collector<T,?,Optional<T>> minBy(Comparator<? super T> comparator) {
    return null;//TODO
  }

  public static <T> Collector<T,?,Map<Boolean,List<T>>> partitioningBy(Predicate<? super T> predicate) {
    return null;//TODO
  }

  public static <T,D,A> Collector<T,?,Map<Boolean,D>> partitioningBy(Predicate<? super T> predicate, Collector<? super T,A,D> downstream) {
    return null;//TODO
  }

  public static <T> Collector<T,?,Optional<T>> reducing(BinaryOperator<T> op) {
    return null;//TODO
  }

  public static <T> Collector<T,?,T> reducing(T identity, BinaryOperator<T> op) {
    return null;//TODO
  }

  public static <T,U> Collector<T,?,U> reducing(U identity, Function<? super T,? extends U> mapper, BinaryOperator<U> op) {
    return null;//TODO
  }

  public static <T> Collector<T,?,DoubleSummaryStatistics> summarizingDouble(ToDoubleFunction<? super T> mapper) {
    return Collector.<T, DoubleSummaryStatistics>of(
        DoubleSummaryStatistics::new,
        (stats, item) -> stats.accept(mapper.applyAsDouble(item)),
        (t, u) -> {
          t.combine(u);
          return t;
        }
    );
  }

  public static <T> Collector<T,?,IntSummaryStatistics> summarizingInt(ToIntFunction<? super T> mapper) {
    return Collector.<T, IntSummaryStatistics>of(
        IntSummaryStatistics::new,
        (stats, item) -> stats.accept(mapper.applyAsInt(item)),
        (t, u) -> {
          t.combine(u);
          return t;
        }
    );
  }

  public static <T> Collector<T,?,LongSummaryStatistics> summarizingLong(ToLongFunction<? super T> mapper) {
    return Collector.<T, LongSummaryStatistics>of(
        LongSummaryStatistics::new,
        (stats, item) -> stats.accept(mapper.applyAsLong(item)),
        (t, u) -> {
          t.combine(u);
          return t;
        }
    );
  }

  public static <T> Collector<T,?,Double> summingDouble(final ToDoubleFunction<? super T> mapper) {
    return Collector.<T, DoubleSummaryStatistics, Double>of(
        DoubleSummaryStatistics::new,
        (stats, item) -> stats.accept(mapper.applyAsDouble(item)),
        (t, u) -> {
          t.combine(u);
          return t;
        },
        DoubleSummaryStatistics::getSum//this is a dumb way to implement this, but its quick and easy to get right the first time
    );
  }

  public static <T> Collector<T,?,Integer> summingInt(ToIntFunction<? super T> mapper) {
    return Collector.<T, IntSummaryStatistics, Integer>of(
        IntSummaryStatistics::new,
        (stats, item) -> stats.accept(mapper.applyAsInt(item)),
        (t, u) -> {
          t.combine(u);
          return t;
        },
        stats -> (int) stats.getSum()//this is a dumb way to implement this, but its quick and easy to get right the first time
    );
  }

  public static <T> Collector<T,?,Long> summingLong(ToLongFunction<? super T> mapper) {
    return Collector.<T, LongSummaryStatistics, Long>of(
        LongSummaryStatistics::new,
        (stats, item) -> stats.accept(mapper.applyAsLong(item)),
        (t, u) -> {
          t.combine(u);
          return t;
        },
         LongSummaryStatistics::getSum//this is a dumb way to implement this, but its quick and easy to get right the first time
    );
  }

  public static <T,C extends Collection<T>> Collector<T,?,C> toCollection(final Supplier<C> collectionFactory) {
    return Collector.of(
        collectionFactory,
        (collection, item) -> collection.add(item),
        (c1, c2) -> {
          C c = collectionFactory.get();
          c.addAll(c1);
          c.addAll(c2);
          return c;
        }
    );
  }

  //not supported
//  public static <T,K,U> Collector<T,?,ConcurrentMap<K,U>> toConcurrentMap(Function<? super T,? extends K> keyMapper, Function<? super T,? extends U> valueMapper) {
//    return null;
//  }
//
//  public static <T,K,U> Collector<T,?,ConcurrentMap<K,U>> toConcurrentMap(Function<? super T,? extends K> keyMapper, Function<? super T,? extends U> valueMapper, BinaryOperator<U> mergeFunction) {
//    return null;
//  }
//
//  public static <T,K,U,M extends ConcurrentMap<K,U>> Collector<T,?,M> toConcurrentMap(Function<? super T,? extends K> keyMapper, Function<? super T,? extends U> valueMapper, BinaryOperator<U> mergeFunction, Supplier<M> mapSupplier) {
//    return null;
//  }

  public static <T> Collector<T,?,List<T>> toList() {
    return toCollection(ArrayList::new);
  }

  public static <T,K,U> Collector<T,?,Map<K,U>> toMap(final Function<? super T,? extends K> keyMapper, final Function<? super T,? extends U> valueMapper) {
    return toMap(keyMapper, valueMapper, (m1, m2) -> { throw new IllegalStateException("Can't assign multiple values to the same key"); });
  }

  public static <T,K,U> Collector<T,?,Map<K,U>> toMap(Function<? super T,? extends K> keyMapper, Function<? super T,? extends U> valueMapper, BinaryOperator<U> mergeFunction) {
    return toMap(keyMapper, valueMapper, mergeFunction, HashMap::new);
  }

  public static <T,K,U,M extends Map<K,U>> Collector<T,?,M> toMap(final Function<? super T,? extends K> keyMapper, final Function<? super T,? extends U> valueMapper, final BinaryOperator<U> mergeFunction, final Supplier<M> mapSupplier) {
    return Collector.of(
        mapSupplier,
        (map, item) -> {
          K key = keyMapper.apply(item);
          U newValue = valueMapper.apply(item);
          if (map.containsKey(key)) {
            map.put(key, mergeFunction.apply(map.get(key), newValue));
          }
          map.put(key, newValue);
        },
        (m1, m2) -> {
          M m = mapSupplier.get();
          m.putAll(m1);
          m.putAll(m2);
          return m;
        }
    );
  }

  public static <T> Collector<T,?,Set<T>> toSet() {
    return toCollection(HashSet::new);
  }
}