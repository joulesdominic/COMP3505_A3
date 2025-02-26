package org.jfree.data;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Test2 {

    @BeforeEach
    void setUp() throws Exception {
    }
//  Get____Bound Methods Testing 
    @Test
    void testGetLowerBound() {
        Range range = new Range(-10, 25);  // Example range from -10 to 25
        assertEquals(-10, range.getLowerBound(), "Lower bound should be -10");
    }
    
    @Test
    void testGetLowerBoundNegativeRange() {
    	Range range = new Range(-50, -30);
        assertEquals(-50, range.getLowerBound(), "Lower bound should be -50");
    }
    @Test
    void testGetUpperBoundNegativeRange() {
    	Range range = new Range(-50, -30);
        assertEquals(-30, range.getUpperBound(), "Lower bound should be -30");
    }
    
    @Test
    void testGetUpperBound() {
    	Range range = new Range(-5, 40); 
        assertEquals(40, range.getUpperBound(), "Upper bound should be 40");
    }

//   Contain Method Testing 
    @Test
    void testContainsOutsideUpperRange() {
    	Range range = new Range(5, 20);  
        assertFalse(range.contains(30), "30 should be outside the range (5, 20)");
    }
    
    @Test
    void testContainsUpperBoundRange() {
    	Range range = new Range(-10, 5);  
        assertTrue(range.contains(5), "5 should be inside the range (-10, 5)");
    }
    
    @Test
    void testContainsLowerBoundRange() {
    	Range range = new Range(2, 5);  
        assertTrue(range.contains(2), "2 should be inside the range (2,5)");
    }
    
    @Test
    void testContainsOutsideLowerRange() {
    	Range range = new Range(-10, 25);  
        assertFalse(range.contains(-11), "-11 should be outside the range (-10,25)");
    }
    
//  GetLength Method Testing
    @Test
    void testGetLength() {
    	Range range = new Range(-10, 25);  
        assertEquals(35, range.getLength(), "Range length should be 35");
    }
    
    @Test
    void testGetLengthZeroLength() {
    	Range range = new Range(10,10);
        assertEquals(0, range.getLength(), "Range length should be 0");
    }
    
    @Test
    void testGetLength_ZeroInRange() {
    	Range range = new Range(0,30);
        assertEquals(30, range.getLength(), "Range length should be 30");
    }
    
    @Test
    void testGetLengthNegativeRange() {
    	Range range = new Range(-50,-30);
        assertEquals(20, range.getLength(), "Range length should be 20");
    }
    
    
// Intersect Method Testing
    
    @Test
    void testIntersectsFullyWithinRange() {
    	Range range = new Range(2, 5); 
    	assertTrue(range.intersects(3, 4), "Range (2, 5) should intersect with (3,4)");
    }
    @Test
    void testIntersectsUpperRange() {
    	Range range = new Range(-10, 25);  
        assertTrue(range.intersects(25, 30), "Range (-10,25) should intersect with (25,30)");
    }
    
    @Test
    void testIntersectsLowerRange() {
    	Range range = new Range(-10, 10);  
        assertTrue(range.intersects(-15, -10), "Range (-10,10) should intersect with (-15, -10)");
    }
    
    @Test
    void testIntersectsLowerRange_PosNums() {
    	Range range = new Range(2, 5); 
        assertTrue(range.intersects(1, 3), "Range (1,3) should intersect with (2,5)");
    }

    
    @Test
    void testIntersectsNoOverlapOverRange() {
    	Range range = new Range(-10, 25);  
        assertFalse(range.intersects(30, 40), "Range (-10,25) should not intersect with (30,40)");
    }
    
    @Test
    void testIntersectsNoOverlapUnderRange() {
    	Range range = new Range(-10, 25);  
        assertFalse(range.intersects(-30, -15), "Range (-10,25) should not intersect with (-30, -15)");
    }
    
// 	Shift Method Testing 
    @Test
    void testShift_PositiveRangeUp() {
        Range base = new Range(10, 20);
        Range result = Range.shift(base, 5);
        assertEquals(new Range(15, 25), result, "Shifting (10,20) by 5 should result in (15,25)");
    }

    @Test
    void testShift_PositiveRangeDown() {
        Range base = new Range(10, 20);
        Range result = Range.shift(base, -5);
        assertEquals(new Range(5, 15), result, "Shifting (10,20) by -5 should result in (5,15)");
    }

    @Test
    void testShift_NegativeRange() {
        Range base = new Range(-20, -10);
        Range result = Range.shift(base, 5);
        assertEquals(new Range(-15, -5), result, "Shifting (-20,-10) by 5 should result in (-15,-5)");
    }

    @Test
    void testShift_RangeStartsAtZero() {
        Range base = new Range(0, 10);
        Range result = Range.shift(base, -5);
        assertEquals(new Range(-5, 5), result, "Shifting (0,10) by -5 should result in (-5,5)");
    }

    @Test
    void testShift_SinglePointRange() {
        Range base = new Range(10, 10);
        Range result = Range.shift(base, 5);
        assertEquals(new Range(15, 15), result, "Shifting (10,10) by 5 should result in (15,15)");
    }

    @Test
    void testShift_LargeShift() {
        Range base = new Range(10, 20);
        Range result = Range.shift(base, 100);
        assertEquals(new Range(110, 120), result, "Shifting (10,20) by 100 should result in (110,120)");
    }

    @Test
    void testShift_ZeroWidthRange() {
        Range base = new Range(0, 0);
        Range result = Range.shift(base, 10);
        assertEquals(new Range(10, 10), result, "Shifting (0,0) by 10 should result in (10,10)");
    }

    

    

//  Combine Method Testing()
    @Test
    void testCombine_OneRangeInsideAnother() {
        Range range1 = new Range(-20, 50);
        Range range2 = new Range(0, 30);
        Range combined = Range.combine(range1, range2);

        assertEquals(-20, combined.getLowerBound(), "Lower bound should be -20");
        assertEquals(50, combined.getUpperBound(), "Upper bound should be 50");
    }
    
    @Test
    void testCombine_IdenticalRanges() {
        Range range1 = new Range(-10, 25);
        Range range2 = new Range(-10, 25);
        Range combined = Range.combine(range1, range2);

        assertEquals(-10, combined.getLowerBound(), "Lower bound should be -10");
        assertEquals(25, combined.getUpperBound(), "Upper bound should be 25");
    }

    @Test
    void testCombine_NonOverlappingRanges() {
        Range range1 = new Range(-50, -20);
        Range range2 = new Range(10, 30);
        Range combined = Range.combine(range1, range2);

        assertEquals(-50, combined.getLowerBound(), "Lower bound should be -50");
        assertEquals(30, combined.getUpperBound(), "Upper bound should be 30");
    }

    
    @Test
    void testCombine_OneRangeIsNull() {
        Range range1 = null;
        Range range2 = new Range(10, 30);
        Range combined = Range.combine(range1, range2);

        assertEquals(10, combined.getLowerBound(), "Lower bound should be 10 when first range is null");
        assertEquals(30, combined.getUpperBound(), "Upper bound should be 30 when first range is null");
    }


    @AfterEach
    void tearDown() {
    }
    
}