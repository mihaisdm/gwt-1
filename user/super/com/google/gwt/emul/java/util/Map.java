/*
 * Copyright 2007 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package java.util;

import static javaemul.internal.InternalPreconditions.checkCriticalNotNull;

import java.io.Serializable;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Abstract interface for maps.
 *
 * @param <K> key type.
 * @param <V> value type.
 */
public interface Map<K, V> {

  /**
   * Represents an individual map entry.
   */
  interface Entry<K, V> {
    @Override
    boolean equals(Object o);

    K getKey();

    V getValue();

    @Override
    int hashCode();

    V setValue(V value);

    static <K extends Comparable<? super K>, V> Comparator<Map.Entry<K,V>> comparingByKey() {
      return (Comparator<Map.Entry<K, V>> & Serializable)
          (a, b) -> a.getKey().compareTo(b.getKey());
    }

    static <K, V extends Comparable<? super V>> Comparator<Map.Entry<K,V>> comparingByValue() {
      return (Comparator<Map.Entry<K, V>> & Serializable)
          (a, b) -> a.getValue().compareTo(b.getValue());
    }

    static <K, V> Comparator<Map.Entry<K, V>> comparingByKey(Comparator<? super K> cmp) {
      checkCriticalNotNull(cmp);
      return (Comparator<Map.Entry<K, V>> & Serializable)
          (a, b) -> cmp.compare(a.getKey(), b.getKey());
    }
  }

  void clear();

  boolean containsKey(Object key);

  boolean containsValue(Object value);

  Set<Entry<K, V>> entrySet();

  @Override
  boolean equals(Object o);

  V get(Object key);

  @Override
  int hashCode();

  boolean isEmpty();

  Set<K> keySet();

  V put(K key, V value);

  void putAll(Map<? extends K, ? extends V> t);

  V remove(Object key);

  int size();

  Collection<V> values();

  default V compute(K key, BiFunction<? super K,? super V,? extends V> remappingFunction) {
    V oldValue = get(key);
    V newValue = remappingFunction.apply(key, oldValue);
    if (oldValue != null) {
      if (newValue != null) {
        put(key, newValue);
      } else {
        remove(key);
      }
    } else {
      if (newValue != null) {
        put(key, newValue);
      }
    }
    return newValue;
  }

  default V computeIfAbsent(K key, Function<? super K,? extends V> mappingFunction) {
    V currentValue = get(key);
    if (currentValue == null) {
      V newValue = mappingFunction.apply(key);
      if (newValue != null) {
        put(key, newValue);
      }
      return newValue;
    }
    return currentValue;
  }

  default V computeIfPresent(K key, BiFunction<? super K,? super V,? extends V> remappingFunction) {
    V oldValue = get(key);
    if (oldValue != null) {
      V newValue = remappingFunction.apply(key, oldValue);
      if (newValue != null) {
        put(key, newValue);
      } else {
        remove(key);
      }
      return newValue;
    }
    return null;
  }

  default void forEach(BiConsumer<? super K,? super V> action) {
    for (Map.Entry<K, V> entry : entrySet()) {
      action.accept(entry.getKey(), entry.getValue());
    }
  }

  default V getOrDefault(Object key, V defaultValue) {
    if (containsKey(key)) {
      return get(key);
    } else {
      return defaultValue;
    }
  }

  default V merge(K key, V value, BiFunction<? super V,? super V,? extends V> remappingFunction) {
    V oldValue = get(key);
    V newValue = (oldValue == null) ? value :
        remappingFunction.apply(oldValue, value);
    if (newValue == null) {
      remove(key);
    } else {
      put(key, newValue);
    }
    return newValue;
  }

  default V putIfAbsent(K key, V value) {
    V v = get(key);
    if (v == null) {
      v = put(key, value);
    }

    return v;
  }

  default boolean remove(Object key, Object value) {
    if (containsKey(key) && Objects.equals(get(key), value)) {
      remove(key);
      return true;
    } else {
      return false;
    }
  }

  default V replace(K key, V value) {
    if (containsKey(key)) {
      return put(key, value);
    } else
      return null;
  }

  default boolean replace(K key, V oldValue, V newValue) {
    if (containsKey(key) && Objects.equals(get(key), oldValue)) {
      put(key, newValue);
      return true;
    } else {
      return false;
    }
  }

  default void replaceAll(BiFunction<? super K,? super V,? extends V> function) {
    for (Map.Entry<K, V> entry : entrySet()) {
      entry.setValue(function.apply(entry.getKey(), entry.getValue()));
    }
  }
}
