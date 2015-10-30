package java.util.stream;

import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.ConcurrentMap;
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
  
  public static <T,C extends Collection<T>> Collector<T,?,C> toCollection(Supplier<C> collectionFactory) {
    return Collector.of(collectionFactory, Collection::add, Collection::addAll);
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
    return Collector.of(ArrayList::new, List::add, List::addAll);
  }

  public static <T,K,U> Collector<T,?,Map<K,U>> toMap(final Function<? super T,? extends K> keyMapper, final Function<? super T,? extends U> valueMapper) {
    return Collector.of(HashMap::new, (map, item) -> map.put(keyMapper.apply(item), valueMapper.apply(item)), HashMap::putAll);
  }

  public static <T,K,U> Collector<T,?,Map<K,U>> toMap(Function<? super T,? extends K> keyMapper, Function<? super T,? extends U> valueMapper, BinaryOperator<U> mergeFunction) {
    return toMap(keyMapper, valueMapper);
  }

  public static <T,K,U,M extends Map<K,U>> Collector<T,?,M> toMap(final Function<? super T,? extends K> keyMapper, final Function<? super T,? extends U> valueMapper, BinaryOperator<U> mergeFunction, Supplier<M> mapSupplier) {
    return Collector.of(mapSupplier, (map, item) -> map.put(keyMapper.apply(item), valueMapper.apply(item)), HashMap::putAll);
  }

  public static <T> Collector<T,?,Set<T>> toSet() {
    return Collector.of(HashSet::new, Set::add, Set::addAll);
  }
}