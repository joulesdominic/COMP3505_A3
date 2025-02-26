package org.jfree.data;
 
import static org.junit.jupiter.api.Assertions.*;

import java.security.InvalidParameterException;

import org.junit.jupiter.api.AfterAll; 
import org.junit.jupiter.api.AfterEach; 
import org.junit.jupiter.api.BeforeAll;
 import org.junit.jupiter.api.BeforeEach; 
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
 
class Test1 {
              	@BeforeAll
              	static void setUpBeforeClass() throws Exception {
              	}
              	@BeforeEach
              	void setUp() throws Exception {
              	}
              	@AfterEach
              	void tearDown() throws Exception {
              	}
              	@AfterAll
              	static void tearDownAfterClass() throws Exception {
              	}
              	
             // Test cases for the expand method
              	
              	@ParameterizedTest
                @CsvSource({
                    "2, 6, 0.25, 0.5, 1, 8",   // Normal test case
                    "0, 10, 0.1, 0.2, -1, 12", // Expanding both sides
                    "4, 4, 0.5, 0.5, 2, 6"     // Single-point range
                })
                void testExpand_ValidRangeAndMargins(double lower, double upper, double lowerMargin, double upperMargin, double expectedLower, double expectedUpper) {
                    Range range = new Range(lower, upper);
                    Range expandedRange = Range.expand(range, lowerMargin, upperMargin);
                    assertEquals(new Range(expectedLower, expectedUpper), expandedRange);
                }

                @Test
                void testExpand_NullRange() {
                    assertThrows(InvalidParameterException.class, () -> {
                        Range.expand(null, 0.25, 0.5);
                    }, "Null range should throw InvalidParameterException");
                }

                @Test
                void testExpand_NegativeLowerMargin() {
                    Range range = new Range(2, 6);
                    Range expandedRange = Range.expand(range, -0.25, 0.5);
                    assertEquals(new Range(3, 8), expandedRange, "Expanded range should be (3, 8)");
                }

                @Test
                void testExpand_NegativeUpperMargin() {
                    Range range = new Range(2, 6);

                    assertThrows(IllegalArgumentException.class, () -> {
                        Range.expand(range, 0.25, -0.5);
                    }, "Expected IllegalArgumentException due to invalid negative upper margin");
                }

                @Test
                void testExpand_ZeroMargins() {
                    Range range = new Range(2, 6);
                    Range expandedRange = Range.expand(range, 0.0, 0.0);
                    assertEquals(new Range(2, 6), expandedRange, "Expanded range should remain (2, 6)");
                }

                // Test cases for the shift method
                
                @ParameterizedTest
                @CsvSource({
                    "2, 6, 2.0, true, 4, 8",    // Normal test case
                    "0, 5, 1.5, true, 1.5, 6.5",// Shift starting from zero
                    "1, 1, 5.0, true, 6, 6"     // Single-point range shift
                })
                void testShift_PositiveDelta_AllowZeroCrossing(double lower, double upper, double delta, boolean allowZeroCrossing,
                                                               double expectedLower, double expectedUpper) {
                    Range base = new Range(lower, upper);
                    Range shiftedRange = Range.shift(base, delta, allowZeroCrossing);
                    assertEquals(new Range(expectedLower, expectedUpper), shiftedRange);
                }
                

                @Test
                void testShift_NegativeDelta_AllowZeroCrossing() {
                    Range base = new Range(2, 6);
                    Range shiftedRange = Range.shift(base, -2.0, true);
                    assertEquals(new Range(0, 4), shiftedRange, "Shifted range should be (0, 4)");
                }

                @Test
                void testShift_PositiveDelta_DisallowZeroCrossing() {
                    Range base = new Range(-2, 2);
                    Range shiftedRange = Range.shift(base, 3.0, false);
                    assertEquals(new Range(0, 5), shiftedRange, "Shifted range should be (0, 5)");
                }

                @Test
                void testShift_NegativeDelta_DisallowZeroCrossing() {
                    Range base = new Range(-2, 2);
                    Range shiftedRange = Range.shift(base, -3.0, false);
                    assertEquals(new Range(-5, 0), shiftedRange, "Shifted range should be (-5, 0)");
                }

                @Test
                void testShift_NullBase() {
                    assertThrows(InvalidParameterException.class, () -> {
                        Range.shift(null, 2.0, true);
                    }, "Null base should throw InvalidParameterException");
                }

                // Test cases for the equals method
                @ParameterizedTest
                @CsvSource({
                    "2, 6",   // Standard positive range
                    "-3, 5",  // Negative range
                    "0, 0"    // Single-point range
                })
                void testEquals_SameRange(double lower, double upper) {
                    Range range1 = new Range(lower, upper);
                    Range range2 = new Range(lower, upper);
                    
                    assertTrue(range1.equals(range2), "Ranges should be equal: " + range1 + " and " + range2);
                }

                @Test
                void testEquals_DifferentRange() {
                    Range range1 = new Range(2, 6);
                    Range range2 = new Range(1, 5);
                    assertFalse(range1.equals(range2), "Ranges (2, 6) and (1, 5) should not be equal");
                }

                @Test
                void testEquals_NonRangeObject() {
                    Range range = new Range(2, 6);
                    assertFalse(range.equals("alo"), "Range should not equal a non-Range object");
                }

                @Test
                void testEquals_Null() {
                    Range range = new Range(2, 6);
                    assertFalse(range.equals(null), "Range should not equal null");
                }

                // Test cases for the toString method
                @Test
                void testToString_PositiveBounds() {
                    Range range = new Range(2, 6);
                    assertEquals("Range[2.0,6.0]", range.toString(), "toString should return 'Range[2.0,6.0]'");
                }

                @Test
                void testToString_NegativeBounds() {
                    Range range = new Range(-6, -2);
                    assertEquals("Range[-6.0,-2.0]", range.toString(), "toString should return 'Range[-6.0,-2.0]'");
                }

                @Test
                void testToString_MixedBounds() {
                    Range range = new Range(-2, 2);
                    assertEquals("Range[-2.0,2.0]", range.toString(), "toString should return 'Range[-2.0,2.0]'");
                }
}
