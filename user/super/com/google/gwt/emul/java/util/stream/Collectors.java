package java.util.stream;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.ToDoubleFunction;
import java.util.stream.Collector;


public final class Collectors {
  public static <T> Collector<T,?,Double> averagingDouble(ToDoubleFunction<? super T> mapper) {
    return null;//TODO
  }


  //...TODO
  
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
    return Collector.of(HashMap::new, (map, item) -> map.put(keyMapper.apply(item), valueMapper.apply(item)), (m1, m2) -> {
      Map<K, U> m = new HashMap<K, U>();
      m.putAll(m1);
      m.putAll(m2);
      return m;
    });
  }

  public static <T,K,U> Collector<T,?,Map<K,U>> toMap(Function<? super T,? extends K> keyMapper, Function<? super T,? extends U> valueMapper, BinaryOperator<U> mergeFunction) {
    return toMap(keyMapper, valueMapper);
  }

  public static <T,K,U,M extends Map<K,U>> Collector<T,?,M> toMap(final Function<? super T,? extends K> keyMapper, final Function<? super T,? extends U> valueMapper, BinaryOperator<U> mergeFunction, final Supplier<M> mapSupplier) {
    return Collector.of(mapSupplier, (map, item) -> map.put(keyMapper.apply(item), valueMapper.apply(item)), (m1, m2) -> {
      M m = mapSupplier.get();
      m.putAll(m1);
      m.putAll(m2);
      return m;
    });
  }

  public static <T> Collector<T,?,Set<T>> toSet() {
    return toCollection(HashSet::new);
  }
}