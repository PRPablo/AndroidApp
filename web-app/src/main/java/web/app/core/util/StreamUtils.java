package web.app.core.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class StreamUtils {
	
	public static <T> ArrayList<T> toArrayList(final Iterator<T> iterator) {
	    return StreamSupport.stream(Spliterators.spliteratorUnknownSize(iterator, Spliterator.ORDERED), false)
	                        .collect(Collectors.toCollection(ArrayList::new));
	}
	
	public static <T> Stream<T> stream(Iterable<T> iterable) {
	    return StreamSupport.stream(
	        Spliterators.spliteratorUnknownSize(
	            iterable.iterator(),
	            Spliterator.ORDERED
	        ),
	        false
	    );
	}

}
