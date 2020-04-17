package ohm.softa.a04.tests;

import ohm.softa.a04.CollectionsUtility;
import ohm.softa.a04.SimpleFilter;
import ohm.softa.a04.SimpleList;
import ohm.softa.a04.SimpleListImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.Iterator;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Peter Kurfer
 * Created on 10/6/17.
 */
class SimpleListTests {

	private final Logger logger = LogManager.getLogger();
	private SimpleList<Integer> testList;

	@BeforeEach
	void setup(){
		testList = new SimpleListImpl<>();

		testList.add(1);
		testList.add(2);
		testList.add(3);
		testList.add(4);
		testList.add(5);
	}

	@Test
	void testAddElements(){
		logger.info("Testing if adding and iterating elements is implemented correctly");
		int counter = 0;
		for(Integer ignored : testList){
			counter++;
		}
		assertEquals(5, counter);
	}

	@Test
	void testSize(){
		logger.info("Testing if size() method is implemented correctly");
		assertEquals(5, testList.size());
	}

	@Test
	void testFilterAnonymousClass(){
		logger.info("Testing the filter possibilities by filtering for all elements greater 2");
		SimpleList<Integer> result = testList.filter(new SimpleFilter<Integer>() {
			@Override
			public boolean include(Integer item) {
				int current = item;
				return current > 2;
			}
		});

		for(Integer o : result){
			int i = o;
			assertTrue(i > 2);
		}
	}

	@Test
	void testFilterLambda(){
		logger.info("Testing the filter possibilities by filtering for all elements which are dividable by 2");
		SimpleList<Integer> result = testList.filter(o ->  o % 2 == 0);
		for(Integer o : result){
			int i = o;
            assertEquals(0, i % 2);
		}
	}

	@Test
    void testAddDefault() throws InstantiationException, IllegalAccessException {
	    logger.info("Testing the addDefault method");
	    SimpleList<ohm.softa.a04.tests.models.Test> result = new SimpleListImpl<>();
	    result.addDefault(ohm.softa.a04.tests.models.Test.class);
	    assertEquals(1, result.size());
    }

    @Test
    void testMapLambda() {
	    logger.info("Testing the map method");
	    Function<Integer, Integer> addOne = i -> i + 1;
	    SimpleList<Integer> result = testList.map(addOne);
        for (Integer i : result) {
            assertTrue(i > 1);
            assertTrue(i < 7);
        }
    }

    @Test
    void testMapAnonymousClass() {
	    logger.info("testing the map with anonymous function");
        Function<Integer, Integer> addTwo = new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer integer) {
                return integer + 2;
            }
        };
        SimpleList<Integer> result = testList.map(addTwo);
        for (Integer i : result) {
            assertTrue(i > 2);
            assertTrue(i < 8);
        }
    }

    @Test
    void testMapToString() {
        logger.info("Testing the map method");
        Function<Integer, String> addOne = i -> i.toString();
        SimpleList<String> result = testList.map(addOne);
        Iterator listiterator = testList.iterator();
        Iterator resultiterator = result.iterator();
        while (listiterator.hasNext() && resultiterator.hasNext()) {
            assertEquals(listiterator.next().toString(), resultiterator.next());
        }
    }

    @Test
    void testMapToStringFor() {
        logger.info("Testing the map method");
        Function<Integer, String> addOne = i -> i.toString();
        SimpleList<String> result = testList.map(addOne);
        for (String s : result) {
            assertTrue(s instanceof String);
        }
    }

    @Test
    void testSort() {
	    logger.info("Testing the sort Method");
	    SimpleList<Integer> list = new SimpleListImpl<>();
	    list.add(3);
	    list.add(2);
	    list.add(4);
	    list.add(1);
	    list.add(5);
        Comparator<Integer> cmp = (i1, i2) -> {
            if(i1 < i2) {
                return -1;
            }
            else if(i1.equals(i2)) {
                return 0;
            }
            else {
                return 1;
            }

        };
        SimpleList<Integer> result = CollectionsUtility.sort(list, cmp);
        Iterator<Integer> iterator = result.iterator();
        Integer prev = iterator.next();
        while(iterator.hasNext()) {
            Integer current = iterator.next();
            logger.info(prev.toString() + " " + current.toString());
            assertTrue(prev <= current);

            prev = current;
        }


    }
}