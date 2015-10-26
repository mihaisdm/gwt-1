package java.util.stream;

public interface Collector<T,A,R> {
  public enum Characteristics { CONCURRENT, IDENTITY_FINISH, UNORDERED }

  public static <T,A,R> Collector<T,A,R> of(Supplier<A> supplier, BiConsumer<A,T> accumulator, BinaryOperator<A> combiner, Function<A,R> finisher, Collector.Characteristics... characteristics) {
    return null;
  }

  public static <T,R> Collector<T,R,R> of(Supplier<R> supplier, BiConsumer<R,T> accumulator, BinaryOperator<R> combiner, Collector.Characteristics... characteristics) {
    return null;
  }

  Supplier<A> supplier();

  BiConsumer<A,T> accumulator();

  Set<Collector.Characteristics> characteristics();

  BinaryOperator<A> combiner();

  Function<A,R> finisher();
}