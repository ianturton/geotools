package org.geotools.filter;

import static org.junit.Assert.*;

import org.geotools.factory.CommonFactoryFinder;
import org.junit.Test;
import org.opengis.filter.FilterFactory;
import org.opengis.filter.MultiValuedFilter.MatchAction;
import org.opengis.filter.PropertyIsGreaterThan;
import org.opengis.filter.PropertyIsGreaterThanOrEqualTo;

public class Geot_7160Test {

	FilterFactory ff = CommonFactoryFinder.getFilterFactory();
	@Test
	public void testWithFactory() {
		PropertyIsGreaterThan filter1 = ff.greater(ff.literal("b"), ff.literal("A"));
		assertTrue(filter1.evaluate(null));
		PropertyIsGreaterThan filter2 = ff.greater(ff.literal("b"), ff.literal("A"), false); 
		assertTrue(filter2.evaluate(null));
		
		PropertyIsGreaterThanOrEqualTo filter3 = ff.greaterOrEqual(ff.literal("A"), ff.literal("b"));
		assertFalse(filter3.evaluate(null));
		PropertyIsGreaterThanOrEqualTo filter4 = ff.greaterOrEqual(ff.literal("A"), ff.literal("b"), false); 
		assertFalse(filter4.evaluate(null));
		PropertyIsGreaterThanOrEqualTo filter5 = ff.greaterOrEqual(ff.literal("A"), ff.literal("b"), true); 
		assertFalse(filter5.evaluate(null));
	}
	@Test
	public void testDirect() {
		
		PropertyIsGreaterThanOrEqualTo filter3 = new IsGreaterThanOrEqualToImpl(ff.literal("A"), ff.literal("b"));
		assertTrue(filter3.evaluate(null));
		PropertyIsGreaterThanOrEqualTo filter4 = new IsGreaterThanOrEqualToImpl(ff.literal("A"), ff.literal("b"), false); 
		assertTrue(filter4.evaluate(null));
	}
}
