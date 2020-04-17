package ohm.softa.a04;

import java.util.function.Function;

public interface SimpleList<T> extends Iterable<T> {
	/**
	 * Add a given object to the back of the list.
	 */
	void add(T o);

	/**
	 * @return current size of the list
	 */
	int size();

    void addDefault(Class<T> o) throws IllegalAccessException, InstantiationException;

	/**
	 * Generate a new list using the given filter instance.
	 * @return a new, filtered list
	 */
	default SimpleList<T> filter(SimpleFilter<T> filter){
        SimpleList<T> result = new SimpleListImpl<>();
        for(T o : this){
            if(filter.include(o)){
                result.add(o);
            }
        }
        return result;
    }

	default <R> SimpleList<R> map(Function<T, R> transform) {
		SimpleList<R> result = new SimpleListImpl<>();
		for(T e : this) {
			result.add(transform.apply(e));
		}
		return result;
    }

    default SimpleList<T> swap(T first, T second) {
	    for (T t : this) {
	        if(t.equals(first)){
	            t = second;
            }
	        if(t.equals(second)) {
	            t = first;
            }
        }
	    return this;
    }
}
