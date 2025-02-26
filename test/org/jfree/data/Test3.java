package org.jfree.data;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Test3 {
	private Range exampleRange;
	@BeforeAll
	static void setUpBeforeClass() throws Exception{
		
	}
	@BeforeEach
	void setUp() throws Exception {
		
	}
	
	@Test
	void test() {
		exampleRange = new Range(-1,1);
		assertEquals(0,exampleRange.getCentralValue(),0.1d,"The central value of (-1,1) is 0");
	}
	
	@AfterEach
	void tearDown() throws Exception {
		
	}
	
	@AfterAll
	static void tearDownAfterClass() throws Exception {
		
	}
	
	// central value tests
	@Test
	void testGetCentralValue_TypicalCase() {
		Range range = new Range(-2, 4);
		assertEquals(1, range.getCentralValue(), 0.1d, "The central value of (-2,4) is 1");
	}
	
	@Test
	void testGetCentralValue_ZeroBoundCase() {
		Range range = new Range(0, 6);
		assertEquals(3, range.getCentralValue(), 0.1d, "The central value of (0,6) is 3");
	}
	
	@Test
	void testGetCentralValue_NegRange() {
		Range range = new Range(-5, -1);
		assertEquals(-3, range.getCentralValue(), 0.1d, "The central value of (-5,-1) is -3");
	}
	
	@Test
	void testGetCentralValue_OneValueRange() {
		Range range = new Range(5, 5);
		assertEquals(5, range.getCentralValue(), 0.1d, "The central value of (5,5) is 5");
	}
	
	// constrain tests
	
	@Test
	void testConstrain_ValueInRange() {
	    Range range = new Range(1, 10);
	    assertEquals(8, range.constrain(8), "Value 8 is within range (1,10) and should return 8");
	}

	@Test
	void testConstrain_ValueBelowLowerBound() {
	    Range range = new Range(1, 10);
	    assertEquals(1, range.constrain(-2), "Value -2 is below range (1,10), should return 1");
	}

	@Test
	void testConstrain_ValueAboveUpperBound() {
	    Range range = new Range(1, 10);
	    assertEquals(10, range.constrain(17), "Value 17 is above range (1,10), should return 10");
	}

	@Test
	void testConstrain_ValueAtLowerBound() {
	    Range range = new Range(1, 10);
	    assertEquals(1, range.constrain(1), "Value at lower bound should return 1");
	}

	@Test
	void testConstrain_ValueAtUpperBound() {
	    Range range = new Range(1, 10);
	    assertEquals(10, range.constrain(10), "Value at upper bound should return 10");
	}
    
    // combine tests

    @Test
    void testCombine_BothRangesNull() {
        assertNull(Range.combine(null, null), "Two null ranges return null");
    }

    @Test
    void testCombine_FirstRangeNull_SecondValid() {
        Range range2 = new Range(4, 8);
        assertEquals(range2, Range.combine(null, range2), "Combining null with (4,8) returns (4,8)");
    }

    @Test
    void testCombine_SecondRangeNull_FirstValid() {
        Range range1 = new Range(1, 5);
        assertEquals(range1, Range.combine(range1, null), "Combining (1,5) with null returns (1,5)");
    }
    
    @Test
    void testCombine_IdenticalRanges() {
        Range range1 = new Range(3, 3);
        Range range2 = new Range(3, 3);

        Range result = Range.combine(range1, range2);

        assertNotNull(result, "Result should not be null");
        assertEquals(3, result.getLowerBound(), "Lower bound should be 3");
        assertEquals(3, result.getUpperBound(), "Upper bound should be 3");
    }

    // expandToInclude tests
    @Test
    void testExpandToInclude_ValueInRange() {
        Range range = new Range(1, 5);
        assertEquals(range, Range.expandToInclude(range, 2), "The expanding (1,5) with 2 should return the same range (1,5)");
    }

    @Test
    void testExpandToInclude_ValueBelowRange() {
        Range range = new Range(1, 5);
        assertEquals(new Range(-1, 5), Range.expandToInclude(range, -1), "The expanding (1,5) with -1 should return (-1,5)");
    }

    @Test
    void testExpandToInclude_ValueAboveRange() {
        Range range = new Range(1, 5);
        assertEquals(new Range(1, 7), Range.expandToInclude(range, 7), "The expanding (1,5) with 7 should return (1,7)");
    }

    @Test
    void testExpandToInclude_NullRange() {
        assertEquals(new Range(5,5), Range.expandToInclude(null, 5), "The expanding null range with 5 should create (5,5)");
    }
    
    /*
    @Test
    void testConstructorRange_ValidRange() {
        Range range = new Range(1.0, 5.0);
        assertEquals(1.0, range.getLowerBound(), 0.0001);
        assertEquals(5.0, range.getUpperBound(), 0.0001);
    }
    */
    
    
    
    
	
}
