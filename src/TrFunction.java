@FunctionalInterface
public interface TrFunction<T, U, S, R> {

    R apply(T t, U u, S s);

}
