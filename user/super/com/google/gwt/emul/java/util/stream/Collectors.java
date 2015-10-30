package java.util.stream;

import java.util.Collection;
import java.util.concurrent.ConcurrentMap;
import java.util.List;
import java.util.function.ToDoubleFunction;
import java.util.function.Supplier;
import java.util.function.Function;
import java.util.function.BinaryOperator;


public final class Collectors {
  public static <T> Collector<T,?,Double> averagingDouble(ToDoubleFunction<? super T> mapper) {
    return null;
  }


  //...
  
  public static <T,C extends Collection<T>> Collector<T,?,C> toCollection(Supplier<C> collectionFactory) {
    return null;
  }

  public static <T,K,U> Collector<T,?,ConcurrentMap<K,U>> toConcurrentMap(Function<? super T,? extends K> keyMapper, Function<? super T,? extends U> valueMapper) {
    return null;
  }

  public static <T,K,U> Collector<T,?,ConcurrentMap<K,U>> toConcurrentMap(Function<? super T,? extends K> keyMapper, Function<? super T,? extends U> valueMapper, BinaryOperator<U> mergeFunction) {
    return null;
  }

  public static <T,K,U,M extends ConcurrentMap<K,U>> Collector<T,?,M> toConcurrentMap(Function<? super T,? extends K> keyMapper, Function<? super T,? extends U> valueMapper, BinaryOperator<U> mergeFunction, Supplier<M> mapSupplier) {
    return null;
  }

  public static <T> Collector<T,?,List<T>> toList() {
    return null;
  }

  public static <T,K,U> Collector<T,?,Map<K,U>> toMap(Function<? super T,? extends K> keyMapper, Function<? super T,? extends U> valueMapper) {
    return null;
  }

  public static <T,K,U> Collector<T,?,Map<K,U>> toMap(Function<? super T,? extends K> keyMapper, Function<? super T,? extends U> valueMapper, BinaryOperator<U> mergeFunction) {
    return null;
  }

  public static <T,K,U,M extends Map<K,U>> Collector<T,?,M> toMap(Function<? super T,? extends K> keyMapper, Function<? super T,? extends U> valueMapper, BinaryOperator<U> mergeFunction, Supplier<M> mapSupplier) {
    return null;
  }

  public static <T> Collector<T,?,Set<T>> toSet() {
    return null;
  }
}