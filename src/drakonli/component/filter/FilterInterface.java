package drakonli.component.filter;

public interface FilterInterface<T>
{
    boolean match(T obj);
}
