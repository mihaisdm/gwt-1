/*
 * Copyright 2015 Google Inc.
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

import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.IntConsumer;
import java.util.function.LongConsumer;

/**
 * See <a href="https://docs.oracle.com/javase/8/docs/api/java/util/Spliterator.html">
 * the official Java API doc</a> for details.
 *
 * @param <T> the type of elements returned by Spliterator.
 */
public interface Spliterator<T> {

  int DISTINCT = 0x00000001;

  int ORDERED = 0x00000010;

  int NONNULL = 0x00000100;

  int CONCURRENT = 0x00001000;

  int SORTED = 0x00000004;

  int SIZED = 0x00000040;

  int IMMUTABLE = 0x00000400;

  int SUBSIZED = 0x00004000;

  int characteristics();

  long estimateSize();

  default void forEachRemaining(Consumer<? super T> consumer) {
    while (tryAdvance(consumer)) {}
  }

  default Comparator<? super T> getComparator() {
    throw new IllegalStateException();
  }

  default long getExactSizeIfKnown() {
    return hasCharacteristics(SIZED) ? estimateSize() : -1L;
  }


  default boolean hasCharacteristics(int characteristics) {
    return (characteristics() & characteristics) == characteristics;
  }

  boolean tryAdvance(Consumer<? super T> consumer);

  Spliterator<T> trySplit();

  /**
   * See <a href="https://docs.oracle.com/javase/8/docs/api/java/util/Spliterator.OfPrimitive.html">
   * the official Java API doc</a> for details.
   *
   * @param <T> the type of elements returned by this Spliterator.
   * @param <C> the type of primitive Consumer.
   * @param <S> the type of primitive Spliterator.
   */
  interface OfPrimitive<T, C, S extends Spliterator.OfPrimitive<T, C, S>> extends Spliterator<T> {

    boolean tryAdvance(C consumer);

    @Override
    S trySplit();

    default void forEachRemaining(C consumer) {
      while (tryAdvance(consumer)) {}
    }
  }

  /**
   * See <a href="https://docs.oracle.com/javase/8/docs/api/java/util/Spliterator.OfDouble.html">
   * the official Java API doc</a> for details.
   */
  interface OfDouble extends OfPrimitive<Double, DoubleConsumer, OfDouble> {

    @Override
    boolean tryAdvance(DoubleConsumer consumer);

    @Override
    OfDouble trySplit();

    @Override
    default void forEachRemaining(DoubleConsumer consumer) {
      while (tryAdvance(consumer)) {}
    }

    @Override
    default boolean tryAdvance(Consumer<? super Double> consumer) {
      if (consumer instanceof DoubleConsumer) {
        return tryAdvance((DoubleConsumer) consumer);
      } else {
        checkCriticalNotNull(consumer);
        return tryAdvance((DoubleConsumer) consumer::accept);
      }
    }

    @Override
    default void forEachRemaining(Consumer<? super Double> consumer) {
      if (consumer instanceof DoubleConsumer) {
        forEachRemaining((DoubleConsumer) consumer);
      } else {
        checkCriticalNotNull(consumer);
        forEachRemaining((DoubleConsumer) consumer::accept);
      }
    }
  }

  /**
   * See <a href="https://docs.oracle.com/javase/8/docs/api/java/util/Spliterator.OfInt.html">
   * the official Java API doc</a> for details.
   */
  interface OfInt extends OfPrimitive<Integer, IntConsumer, OfInt> {

    @Override
    boolean tryAdvance(IntConsumer consumer);

    @Override
    OfInt trySplit();

    @Override
    default void forEachRemaining(IntConsumer consumer) {
      while (tryAdvance(consumer)) {}
    }

    @Override
    default boolean tryAdvance(Consumer<? super Integer> consumer) {
      if (consumer instanceof IntConsumer) {
        return tryAdvance((IntConsumer) consumer);
      } else {
        checkCriticalNotNull(consumer);
        return tryAdvance((IntConsumer) consumer::accept);
      }
    }

    @Override
    default void forEachRemaining(Consumer<? super Integer> consumer) {
      if (consumer instanceof IntConsumer) {
        forEachRemaining((IntConsumer) consumer);
      } else {
        checkCriticalNotNull(consumer);
        forEachRemaining((IntConsumer) consumer::accept);
      }
    }
  }

  /**
   * See <a href="https://docs.oracle.com/javase/8/docs/api/java/util/Spliterator.OfLong.html">
   * the official Java API doc</a> for details.
   */
  interface OfLong extends OfPrimitive<Long, LongConsumer, OfLong> {

    @Override
    boolean tryAdvance(LongConsumer consumer);

    @Override
    OfLong trySplit();

    @Override
    default void forEachRemaining(LongConsumer consumer) {
      while (tryAdvance(consumer)) {}
    }

    @Override
    default boolean tryAdvance(Consumer<? super Long> consumer) {
      if (consumer instanceof LongConsumer) {
        return tryAdvance((LongConsumer) consumer);
      } else {
        checkCriticalNotNull(consumer);
        return tryAdvance((LongConsumer) consumer::accept);
      }
    }

    @Override
    default void forEachRemaining(Consumer<? super Long> consumer) {
      if (consumer instanceof LongConsumer) {
        forEachRemaining((LongConsumer) consumer);
      } else {
        checkCriticalNotNull(consumer);
        forEachRemaining((LongConsumer) consumer::accept);
      }
    }
  }
}
