package org.jfree.data;

import static org.junit.Assert.*;

import java.security.InvalidParameterException;

import org.jfree.chart.util.ParamChecks;
import org.jfree.data.Range; import org.junit.*;

public class RangeTest {
    private Range exampleRange;
    @BeforeClass public static void setUpBeforeClass() throws Exception {
    }


    @Before
    public void setUp() throws Exception { exampleRange = new Range(-1, 1);
    }
    
//  Testing constructor with invalid range with lowerbound = 2 and upperbound = -2
    @Test
    public void testConstructor1() {
    	try {
    		Range rangeTemp = new Range (2, -2);
    	} catch (Exception err) {
    		assertEquals(err.getMessage(), "Range(double, double): require lower (2.0) <= upper (-2.0).");
    	}
    }
    
//  Testing constructor with lowerbound = upperbound
    @Test
    public void testConstructor2() {
    	try {
    		Range rangeTemp = new Range (-2, -2);
    	} catch (Exception err) {
    		fail(err.getMessage());
    	}
    }

    //CONTAINS() TESTING

    // testing a positive number thats inside the range and checking that it returns true
    @Test
    public void testContainsPositiveInsideRange() {
    	assertTrue(exampleRange.contains(0.34));
    }
    
    // testing a negative number thats inside the range and checking that it returns true
    @Test
    public void testContainsNegativeInsideRange() {
    	assertTrue(exampleRange.contains(-0.34));
    }
    
    // testing a positive number thats outside the range and checking that it returns false
    @Test
    public void testContainsPositiveOutsideRange() {
    	assertFalse(exampleRange.contains(12.32));
    }
    
    // testing a negative number thats outside the range and checking that it returns false
    @Test
    public void testContainsNegativeOutsideRange() {
    	assertFalse(exampleRange.contains(-12.32));
    }
    
    //INTERSECTS() TESTING
    
    //testing a range that's within the range being tested completely and verifying that it returns true
    @Test
    public void testIntersectsOverlappingSmaller() {
    	assertTrue(exampleRange.intersects(-0.5, 0.5));
    }
    
    //testing a range that overlaps completely over the range being tested and verifying that it returns true
    @Test
    public void testIntersectsOverlappingBigger() {
    	assertTrue(exampleRange.intersects(-1.5, 1.5));
    }
    
    //testing a range that has only its upper bound within the range being tested and verifying that it returns true
    @Test
    public void testIntersectsOverlappingLower() {
    	assertTrue(exampleRange.intersects(-0.5, 1.5));
    }
    
    //testing a range that has only its lower bound within the range being tested and verifying that it returns true
    @Test
    public void testIntersectsOverlappingUpper() {
    	assertTrue(exampleRange.intersects(-1.5, 0.5));
    }
    
    //testing a range that doesn't intersect and verifying that it returns false
    @Test
    public void testIntersectsNotOverlapping() {
    	assertFalse(exampleRange.intersects(1, 1.5));
    }
    
    
    //COMBINE() TESTING
    
    //combining two ranges and verifying that the upper and lower bound of the new range are accurate
    @Test
    public void testCombineTwoRanges() {
    	Range range_one = new Range(-3, 3);
    	Range range_two = new Range(-4, 5);
    	
    	Range new_range = Range.combine(range_one, range_two);
    	
    	double lower_bound = new_range.getLowerBound();
    	double upper_bound = new_range.getUpperBound();

    	
    	assertEquals("Range 1 is (-3, 3), Range_2 is (-4, 5), the lower bound should be -4.0", -4.0, lower_bound, 0.00000001d);
    	assertEquals("Range 1 is (-3, 3), Range_2 is (-4, 5), the upper bound should be 5.0", 5.0, upper_bound, 0.00000001d);
    	
    }
    
    //combining one range with a null range and verifying that it returns the range
    @Test
    public void testCombineOneNull() {
    	Range range_one = new Range(-3, 3);
    	Range range_two = null;
    	
    	Range new_range = Range.combine(range_one, range_two);
    	
    	double lower_bound = new_range.getLowerBound();
    	double upper_bound = new_range.getUpperBound();

    	
    	assertEquals("Range 1 is (-3, 3), Range_2 is null, the lower bound should be -3.", -3.0, lower_bound, 0.00000001d);
    	assertEquals("Range 1 is (-3, 3), Range_2 is null, the upper bound should be 3.", 3.0, upper_bound, 0.00000001d);
    	
    }
    
    //combining two null ranges and verifying that it returns null
    @Test
    public void testCombineTwoNulls() {
    	Range range_one = null;
    	Range range_two = null;
    	
    	Range new_range = Range.combine(range_one, range_two);

    	
    	assertNull("Range 1 is null, Range_2 is null, new_range should be null.", new_range);
    	
    }
    
    //GETLOWERBOUND() TESTING
    //creating a range and verifying that the lower bound is the same value of the method
    @Test
    public void testLowerBound() {
    	assertEquals(-1, exampleRange.getLowerBound(), 0.00000001d);
    }
    
    //GETUPPERBOUND() TESTING
    //creating a range and verifying that the upper bound is the same value of the method
    @Test
    public void testUpperBound() {
    	assertEquals(1, exampleRange.getUpperBound(), 0.00000001d);
    }
    
    //CONSTRAIN() TESTING
    //entering a value close to the upper bound of the range and verifying it
    @Test
    public void constrainShouldBeOne() {
    	
        assertEquals("In the range -1 to 1, the closest value to 2 is 1.",
        1, exampleRange.constrain(2), .000000001d);
    }
    
    //entering a value close to the lower bound of the range and verifying it
    @Test
    public void constrainShouldBeNegOne() {
        assertEquals("In the range -1 to 1, the closest value to -2 is -1.",
        -1, exampleRange.constrain(-2), .000000001d);
    }
    
    //entering a value equal to the middle of the range and verifying that it returns the same value
    @Test
    public void constrainShouldBeZero() {
        assertEquals("In the range -1 to 1, the closest value to 0 is 0.",
        0, exampleRange.constrain(0), .000000001d);
    }
    
    //SHIFT() TESTING
    //shifting the range by 1 then verifying the lower bound of the range is correct
    @Test
    public void beginningShiftTest2() {
        assertEquals("The beginning of the range [-1, 1] shifted right by 1 should be 0.",
        0, Range.shift(exampleRange, 1).getLowerBound(), .000000001d);
    }
    
    //shifting the range by 1 then verifying the upper bound of the range is correct
    @Test
    public void endShiftTest2() {
        assertEquals("The end of the range [-1, 1] shifted right by 1 should be 2.",
        2, Range.shift(exampleRange, 1).getUpperBound(), .000000001d);
    }
    
    @Test
    public void expandLowerTest()
    {
    	Range newRange = Range.expand(exampleRange, 1, 0);
    	assertEquals(-3, newRange.getLowerBound(), 0.00000001d);
    }
    
    @Test
    public void expandUpperTest()
    {
    	Range newRange = Range.expand(exampleRange, 0, -5);
    	assertEquals(-5, newRange.getUpperBound(), 0.00000001d);
    }
    
    @Test
    public void toStringTest()
    {
    	Range stringRange = new Range(0, 1);
    	String expected = "Range[0.0,1.0]";
    	assertEquals(expected, stringRange.toString());
    }
    
    @Test
    public void toStringTest2()
    {
    	double n1 = 0.3;
    	double n2 = 1.3;
    	Range stringRange = new Range(n1, n2);
    	
    	String expected = "Range[0.3,1.3]";
    	assertEquals(expected, stringRange.toString());
    }
    
    @Test
    public void equalsTrueSameObjectTest()
    {
    	assertTrue(exampleRange.equals(exampleRange));
    }
    
    @Test
    public void equalsTrueSameNumberTest()
    {
    	Range newRange = new Range(-1, 1);
    	assertTrue(exampleRange.equals(newRange));
    }
    
    @Test
    public void equalsFalseLowerTest()
    {
    	Range newRange = new Range(0, 1);
    	assertFalse(exampleRange.equals(newRange));
    }
    
    @Test
    public void equalsFalseDifferentObjectTest()
    {
    	int number = 5;
    	assertFalse(exampleRange.equals(number));
    }
    
    @Test
    public void equalsFalseUpperTest()
    {
    	Range newRange = new Range(-1, 2);
    	assertFalse(exampleRange.equals(newRange));
    }
    
    
    @Test
    public void hashCodeTest()
    {
    	long val = -31457280;
    	long correct_val = exampleRange.hashCode();
    	
    	assertEquals(val, correct_val, 0.00000001d);
    }
    
//  TESTING getCentralValue() method
    @Test
    //double checking that the value returns is equal to calculated value
    public void getCentralValueTest() {
        assertEquals("The central value of [-1, 1] is 0.",
        0, exampleRange.getCentralValue(), .000000001d);
    }
    
    @Test
    public void testGetCentralValueBothNegativeBounds() {
    	Range temp = new Range (-30, -20);
    	assertEquals(-25, temp.getCentralValue(), .000000001d);
    }
    
    @Test
    public void testGetCentralValueBothPositiveBounds() {
    	Range temp = new Range (20, 30);
    	assertEquals(25, temp.getCentralValue(), .000000001d);
    }
    
    @Test
    public void testGetCentralValueZeroLowerPositiveUpper() {
    	Range temp = new Range (0, 30);
    	assertEquals(15, temp.getCentralValue(), .000000001d);
    }
   
    @Test
    public void testGetCentralValueZeroUpperNegativeLower() {
    	Range temp = new Range (-30, 0);
    	assertEquals(-15, temp.getCentralValue(), .000000001d);
    }
    
    //GETLENGTH() TESTING
    @Test
    //double checking that the value returns is equal to calculated value
    public void getLengthTest() {
        assertEquals("The length of [-1, 1] is 2.",
        2, exampleRange.getLength(), .000000001d);
    }
    
    //testing a range that doesn't intersect and verifying that it returns false
    @Test
    public void testIntersects() {
    	Range exRange = new Range(1, 1.5);
    	assertFalse(exampleRange.intersects(exRange));
    }
    
    @Test
    public void scaleUpperTest()
    {
    	Range exRange = new Range(0, 1);
    	
    	Range scaledRange = Range.scale(exRange, 2);
    	
    	assertEquals(2.0, scaledRange.getUpperBound(), .000000001d);
    }
    
    @Test
    public void scaleMutationTest()
    {
    	Range exRange = new Range(150, 300);
    	
    	Range scaledRange = Range.scale(exRange, 5);
    	
    	assertEquals(750.0, scaledRange.getLowerBound(), .000000001d);
    	assertEquals(1500.0, scaledRange.getUpperBound(), .000000001d);
    }
    
    @Test
    public void scaleMutationTest2()
    {
    	Range exRange = new Range(150, 300);
    	
    	Range scaledRange = Range.scale(exRange, 0.5);
    	
    	assertEquals(75.0, scaledRange.getLowerBound(), .000000001d);
    	assertEquals(150.0, scaledRange.getUpperBound(), .000000001d);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void scaleMutationTest3()
    {
    	Range exRange = new Range(150, 300);
    	
    	Range scaledRange = Range.scale(exRange, -0.8);
    }
    
    public void scaleMutationTest4()
    {
    	Range exRange = new Range(150, 300);
    	
    	Range scaledRange = Range.scale(exRange, 0);
    	
    	assertEquals(0, scaledRange.getLowerBound(), .000000001d);
    	assertEquals(0, scaledRange.getUpperBound(), .000000001d);
    }
   
    
    
    @Test (expected = IllegalArgumentException.class)
    public void scaleExceptionTest()
    {
    	Range exRange = new Range(0, 1);
    	Range.scale(exRange, -7);
    }

//  TESTS for expandToInclude() method
    
    @Test
    public void ExpandToIncludeNullTest()
    {
    	Range nullRange = null;
    	Range newRange = Range.expandToInclude(nullRange, 1);
    	
    	assertEquals(1, newRange.getLowerBound(), .000000001d);
    	assertEquals(1, newRange.getUpperBound(), .000000001d);
    }
    
    @Test
    public void ExpandToIncludeLessThanLowerTest()
    {
    	Range newRange = Range.expandToInclude(exampleRange, -2);
    	
    	assertEquals(-2, newRange.getLowerBound(), .000000001d);
    	assertEquals(1, newRange.getUpperBound(), .000000001d);
    }
    
    @Test
    public void ExpandToIncludeBiggerThanUpperTest()
    {
    	Range newRange = Range.expandToInclude(exampleRange, 7);
    	
    	assertEquals(-1, newRange.getLowerBound(), .000000001d);
    	assertEquals(7, newRange.getUpperBound(), .000000001d);
    }
    
    @Test
    public void ExpandToIncludeEqualTest()
    {
    	Range equalRange = new Range(1, 1);
    	Range newRange = Range.expandToInclude(equalRange, 1);
    	
    	assertEquals(1, newRange.getLowerBound(), .000000001d);
    	assertEquals(1, newRange.getUpperBound(), .000000001d);
    }
    
//    Value to expand is = to lower bound
    @Test
    public void testExpandToIncludeEqualToLower() {
    	Range range = new Range (0, 20);
    	Range newRange = Range.expandToInclude(range, 0);
    	Range expected = new Range(0, 20);
    	assertEquals(expected, newRange);
    }
    
//    Value to expand is = to upper bound
    @Test
    public void testExpandToIncludeEqualToUpper() {
    	Range range = new Range (0, 20);
    	Range newRange = Range.expandToInclude(range, 20);
    	Range expected = new Range(0, 20);
    	assertEquals(expected, newRange);
    }
    
//    Value to expand is already within the range
    @Test
    public void testExpandToIncludeValueInRange() {
    	Range range = new Range (0, 20);
    	Range newRange = Range.expandToInclude(range, 15);
    	assertEquals(range.getLength(), newRange.getLength(), 0);
    }
   
//  TESTS FOR shift() method
    @Test
    public void shiftAllowZeroCrossing() {
    	Range newRange = Range.shift(exampleRange, 0, true);
    	assertEquals(-1, newRange.getLowerBound(), .000000001d);
    	assertEquals(1, newRange.getUpperBound(), .000000001d);
    }
    
    @Test
    public void shfitWithNoZeroCrossingEqualZeroTest()
    {
    	Range zeroRange = new Range(0, 0);
    	Range newRange = Range.shift(zeroRange, 0.0, false);
    	assertEquals(0, newRange.getLowerBound(), .000000001d);
    	assertEquals(0, newRange.getUpperBound(), .000000001d);
    }
   
    @Test (expected = IllegalArgumentException.class)
    public void testLowerBound2() {
    	Range exampleRange2 = new Range(1, -1);
    	exampleRange2.getUpperBound();
    }
    
    //COMBINEIGNORINGNAN() TESTING
    
    
    @Test
    public void combaineWithoutNaNMutation()
    {
    	Range ex1 = new Range(0.0, 0.0);
    	Range ex2 = new Range(0.0, 0.0);
    	
    	Range newRange = Range.combineIgnoringNaN(ex1, ex2);
    	
    	assertEquals(0.0, newRange.getLowerBound(), 0.000000001d);
    	assertEquals(0.0, newRange.getUpperBound(), 0.000000001d);
    }
    
    
    
    @Test
    public void combaineWithoutNaNMutation2()
    {
    	Range ex1 = new Range(-0.05, 0.0);
    	Range ex2 = new Range(0.0, 0.05);
    	
    	Range newRange = Range.combineIgnoringNaN(ex1, ex2);
    	
    	assertEquals(-0.05, newRange.getLowerBound(), 0.000000001d);
    	assertEquals(0.05, newRange.getUpperBound(), 0.000000001d);
    }
    
    @Test
    public void combaineWithoutNaNNonNullTest()
    {
    	Range ex1 = new Range(0.0, 1.0);
    	Range ex2 = new Range(-2.0, 3.0);
    	
    	Range newRange = Range.combineIgnoringNaN(ex1, ex2);
    	
    	assertEquals(-2.0, newRange.getLowerBound(), 0.000000001d);
    	assertEquals(3.0, newRange.getUpperBound(), 0.000000001d);
    }
    
    @Test
    public void combaineWithoutNaNFirstNullTest()
    {
    	Range ex1 = null;
    	Range ex2 = new Range(-2.0, 3.0);
    	
    	Range newRange = Range.combineIgnoringNaN(ex1, ex2);
    	
    	assertEquals(-2.0, newRange.getLowerBound(), 0.000000001d);
    	assertEquals(3.0, newRange.getUpperBound(), 0.000000001d);
    }
    
    @Test
    public void combaineWithoutNaNSecondtNullTest()
    {
    	Range ex1 = new Range(0.0, 1.0);
    	Range ex2 = null;
    	
    	Range newRange = Range.combineIgnoringNaN(ex1, ex2);
    	
    	assertEquals(0.0, newRange.getLowerBound(), 0.000000001d);
    	assertEquals(1.0, newRange.getUpperBound(), 0.000000001d);
    }
    
    @Test
    public void combaineWithoutNaNBothNullTest()
    {
    	Range ex1 = null;
    	Range ex2 = null;
    	
    	Range newRange = Range.combineIgnoringNaN(ex1, ex2);
    	
    	assertNull(newRange);
    }
    
    @Test
    public void combaineWithoutNaNNaNNullTest()
    {
    	Range ex1 = new Range(Float.NaN, Float.NaN);
    	Range ex2 = null;
    	
    	Range newRange = Range.combineIgnoringNaN(ex1, ex2);
    	
    	assertNull(newRange);
    }
    
    @Test
    public void combaineWithoutNaNNaNNull2Test()
    {
    	Range ex1 = new Range(2, Float.NaN);
    	Range ex2 = null;
    	
    	Range newRange = Range.combineIgnoringNaN(ex1, ex2);
    	
    	assertEquals(2, newRange.getLowerBound(), 0.000000001d);
    	assertEquals(Float.NaN, newRange.getUpperBound(), 0.000000001d);
    }
    
    @Test
    public void combaineWithoutNaNNaNNull3Test()
    {
    	Range ex1 = new Range(Float.NaN, 2);
    	Range ex2 = null;
    	
    	Range newRange = Range.combineIgnoringNaN(ex1, ex2);
    	
    	assertEquals(Float.NaN, newRange.getLowerBound(), 0.000000001d);
    	assertEquals(2, newRange.getUpperBound(), 0.000000001d);
    }
    
    @Test
    public void combaineWithoutNulLNaNTest()
    {
    	Range ex1 = null;
    	Range ex2 = new Range(Float.NaN, Float.NaN);
    	
    	Range newRange = Range.combineIgnoringNaN(ex1, ex2);
    	
    	assertNull(newRange);
    }
    
    @Test
    public void combaineBothNaNTest()
    {
    	Range ex1 = new Range(Float.NaN, Float.NaN);
    	Range ex2 = new Range(Float.NaN, Float.NaN);
    	
    	Range newRange = Range.combineIgnoringNaN(ex1, ex2);
    	
    	assertNull(newRange);
    }
    
    @Test
    public void combaineBothNaN2Test()
    {
    	Range ex1 = new Range(2, Float.NaN);
    	Range ex2 = new Range(Float.NaN, Float.NaN);
    	
    	Range newRange = Range.combineIgnoringNaN(ex1, ex2);
    	
    	assertEquals(2, newRange.getLowerBound(), 0.000000001d);
    	assertEquals(Float.NaN, newRange.getUpperBound(), 0.000000001d);
    }
    
    @Test
    public void combaineBothNaN3Test()
    {
    	Range ex1 = new Range(Float.NaN, 2);
    	Range ex2 = new Range(Float.NaN, Float.NaN);
    	
    	Range newRange = Range.combineIgnoringNaN(ex1, ex2);
    	
    	assertEquals(Float.NaN, newRange.getLowerBound(), 0.000000001d);
    	assertEquals(2, newRange.getUpperBound(), 0.000000001d);
    }
    
    
    
//    @Test
//    //combining a null with a valid range
//    public void combineWithoutNaNTest() {
//    	Range ex1 = null;
//    	Range ex2 = new Range(0.0, 1.0);
//    	assertEquals(0, Range.combineIgnoringNaN(ex1, ex2).getLowerBound(), .000000001d);
//    }
//    
//    @Test
//    //combining a valid range with a null
//    public void combineWithoutNaNTest2() {
//        Range ex1 = new Range(0.0, 1.0);
//        Range ex2 = null; 
//        assertEquals("The new range is [0.0, 1.0]",
//                0.0, Range.combineIgnoringNaN(ex1, ex2).getLowerBound(), .000000001d);
//    }
//
// 
//    @Test
//    //combining a null with a NaN range
//    public void combineWithoutNaNTest3() {
//        Range ex1 = null;
//        double x = 0.0d / 0.0;
//        Range ex2 = new Range(x, x);
//        assertNull(Range.combineIgnoringNaN(ex1, ex2).getLowerBound());
//    }
//
//    @Test
//    //combining a NaN range with a null range
//    public void combineWithoutNaNTest4() {
//        double x = 0.0d / 0.0;
//        Range ex1 = new Range(x, x);
//        Range ex2 = new Range(x, x);
//        assertNull(Range.combineIgnoringNaN(ex1, ex2).getLowerBound());
//    }
//
//
//    @Test
//    public void combineWithoutNaNTest5() {
//    Range ex1 = null;
//    Range ex2 = null;
//    assertNull(Range.combineIgnoringNaN(ex1, ex2).getLowerBound());
//}
//    @Test
//    //combining a NaN range with a null range
//    public void combineWithoutNaNTest6() {
//        double x = 0.0d / 0.0;
//        Range ex1 = new Range(x, x);
//        Range ex2 = new Range(x, x);
//        assertNull(Range.combineIgnoringNaN(ex1, ex2).getLowerBound());
//    }
//    
//    @Test
//    //combining a NaN range with a null range
//    public void combineWithoutNaNTest7() {
//        double x = 0.0d / 0.0;
//        Range ex1 = new Range(x, 4);
//        Range ex2 = new Range(x, 4);
//        assertEquals(Float.NaN, Range.combineIgnoringNaN(ex1, ex2).getLowerBound(), 0.000000001d);
//    }
//    
//    @Test
//    //combining a NaN range with a null range
//    public void combineWithoutNaNTest8() {
//        double x = 0.0d / 0.0;
//        Range ex1 = new Range(4, x);
//        Range ex2 = new Range(x, 4);
//        assertEquals(4, Range.combineIgnoringNaN(ex1, ex2).getLowerBound(), 0.000000001d);
//    }
//    
//    @Test
//    //combining a NaN range with a null range
//    public void combineWithoutNaNTest9() {
//        double x = 0.0d / 0.0;
//        Range ex1 = new Range(4, x);
//        Range ex2 = new Range(x, 4);
//        assertEquals(4, Range.combineIgnoringNaN(ex1, ex2).getLowerBound(), 0.000000001d);
//    }
//    
//    @Test
//    //combining a NaN range with a null range
//    public void combineWithoutNaNTest10() {
//        Range ex1 = new Range(4, 7);
//        Range ex2 = new Range(3, 10);
//        
//        assertEquals(4, ex1.getLowerBound(), 0.000000001d);
//        assertEquals(7, ex1.getUpperBound(), 0.000000001d);
//    }
    
    @Test
    public void intersectsBiggerThanLowerTest()
    {
    	boolean intersect = exampleRange.intersects(-5, -3);
    	assertFalse(intersect);
    }
    
    @Test
    public void intersectsMutation1()
    {
    	Range r = new Range(0, 1);
    	boolean intersect = r.intersects(0.3, 1);
    	assertTrue(intersect);
    }
    
    @Test
    public void intersectsMutation2()
    {
    	Range r = new Range(0, 1);
    	boolean intersect = r.intersects(-0.7, 0.5);
    	assertTrue(intersect);
    }
    
    @Test
    public void intersectsLessThanUpperTest()
    {
    	boolean intersect = exampleRange.intersects(2, 5);
    	assertFalse(intersect);
    }
    
    @Test
    public void intersectsEqualTest()
    {
    	boolean intersect = exampleRange.intersects(0, 2);
    	assertTrue(intersect);
    }
    
    @Test
    public void constrainMutation()
    {
    	Range new_range = new Range(5, 10);
    	double val = new_range.constrain(2.9);
    	assertEquals(5, val, 0.00001d);
    }
    
    @Test
    public void beginningShiftTest() {
        assertEquals("The beginning of the range [-1, 1] shifted right by 1 should be 0.",
        0, Range.shift(exampleRange, 1).getLowerBound(), .000000001d);
    }

    //shifting the range by 1 then verifying the upper bound of the range is correct
    @Test
    public void endShiftTest() {
        assertEquals("The end of the range [-1, 1] shifted right by 1 should be 2.",
        2, Range.shift(exampleRange, 1).getUpperBound(), .000000001d);
    }

    //shifting the range by 1 then verifying the upper bound of the range is correct (allowing zero crossing)
    @Test
    public void shiftZeroCrossingTestAllow() {
        assertEquals("The end of the range [-1, 1] shifted right by 1 should be 2.",
        2, Range.shift(exampleRange, 1, true).getUpperBound(), .000000001d);
    }

    //shifting the range by 1 then verifying the upper bound of the range is correct (allowing zero crossing)
    @Test
    public void shiftZeroCrossingTestAllow2() {
        assertEquals("The beginning of the range [-1, 1] shifted right by 1 should be 0.",
        0, Range.shift(exampleRange, 1, true).getLowerBound(), .000000001d);
    }

    //shifting the range by 1 then verifying the upper bound of the range is correct (allowing zero crossing)
    @Test
    public void shiftZeroCrossingTestDontAllow() {
        assertEquals("The beginning of the range [-1, 1] shifted right by 1 should be 0.",
        0, Range.shift(exampleRange, 1, false).getLowerBound(), .000000001d);
    }
//shifting the range by 1 then verifying the upper bound of the range is correct (allowing zero crossing)
    @Test
    public void shiftZeroCrossingTestDontAllow2() {
        assertEquals("The end of the range [-1, 1] shifted right by 1 should be 2.",
        2, Range.shift(exampleRange, 1, false).getUpperBound(), .000000001d);
    }

    //shifting the range by 1 then verifying the upper bound of the range is correct (allowing zero crossing)
    @Test (expected = IllegalArgumentException.class)
    public void shiftZeroCrossingTestDontAllowNull() {
        Range example = null;
        Range.shift(example, 1, false);
        //assertEquals("The end of the range [-1, 1] shifted right by 1 should be 2.",
        //0, Range.shift(example, 1, false).getLowerBound(), .000000001d);
    }

    //shifting the range by 1 then verifying the upper bound of the range is correct (allowing zero crossing)
    @Test
    public void shiftZeroCrossingTestDontAllow3() {
        Range example = new Range(-5, -1);
        assertEquals("The end of the range [-5, -1] shifted right by 2 should be 1.",
        0, Range.shift(example, 2, false).getUpperBound(), .000000001d);
    }

    @Test
    public void shiftZeroCrossingTestDontAllow4() {
        Range example = new Range(-5, -1);
        assertEquals("The beginning of the range [-5, -1] shifted right by 2 should be -3.",
        -3, Range.shift(example, 2, false).getLowerBound(), .000000001d);
    }
//shifting the range by 1 then verifying the upper bound of the range is correct (allowing zero crossing)
    @Test
    public void shiftZeroCrossingTestAllow5() {
          Range example = new Range(-2, -1);
        assertEquals("The end of the range [-2, -1] shifted right by 1 should be 0.",
        0, Range.shift(example, 1, true).getUpperBound(), .000000001d);
    }

    //shifting the range by 1 then verifying the upper bound of the range is correct (allowing zero crossing)
    @Test
    public void shiftZeroCrossingTestAllow6() {
        Range example = new Range(-2, -1);
        assertEquals("The beginning of the range [-2, -1] shifted right by 1 should be -1.",
        -1, Range.shift(example, 1, true).getLowerBound(), .000000001d);
    }
    
  //testing a range that does intersect and verifying that it returns true
    @Test
    public void testIntersects2() {
        Range exRange = new Range(-0.5, 0.5);
        assertTrue(exampleRange.intersects(exRange));
    }
    
    @Test
    public void testIntersects3() {
        Range exRange = new Range(-0.5, 0.5);
        Range largeRange = new Range(-3, 3);
        assertTrue(largeRange.intersects(exRange));
    }
    
    @Test
    public void testIntersectsNewBoundaryValues() {
        assertFalse(exampleRange.intersects(1, 1));
    }
    
    @Test
    public void testIntersectsNewBoundaryValues2() {
        assertFalse(exampleRange.intersects(-1, -1));
    }
    
    @Test
    public void testContainsBoundaryValues() {
        assertTrue(exampleRange.contains(-1.0));
    }
    
    @Test
    public void testContainsBoundaryValues2() {
        assertTrue(exampleRange.contains(1.0));
    }
    
    
    @Test 
    public void isNanRangeMutation()
    {
    	Range new_range = new Range(0, 1);
    	
    	assertFalse(new_range.isNaNRange());
    	
    }
    
    @Test 
    public void isNanRangeMutation2()
    {
    	Range new_range = new Range(2, Double.NaN);
    	
    	assertFalse(new_range.isNaNRange());
    	
    }
    
    @Test 
    public void isNanRangeMutation3()
    {
    	Range new_range = new Range(Double.NaN, Double.NaN);
    	
    	assertTrue(new_range.isNaNRange());
    	
    }
    
    @Test 
    public void isNanRangeMutation4()
    {
    	Range new_range = new Range(Double.NaN, 2);
    	
    	assertFalse(new_range.isNaNRange());
    	
    }
    
    @Test
    public void expandToinludeMutation()
    {
    	Range new_range = new Range(0, 0);
    	Range expanded_range = Range.expandToInclude(new_range, 0);
    	assertEquals(0, expanded_range.getLowerBound(), 0.00001d);
    	assertEquals(0, expanded_range.getUpperBound(), 0.00001d);
    	assertEquals(new_range, expanded_range);
    }
    
    @Test
    public void maxTest()
    {
    	Range new_range_1 = new Range(0, Float.NaN);
    	Range new_range_2 = new Range(0, 1);
    	Range combined_range = Range.combineIgnoringNaN(new_range_1, new_range_2);
    	assertEquals(1, combined_range.getUpperBound(), 0.00001d);
    }
    
    @Test
    public void maxTest2()
    {
    	Range new_range_1 = new Range(0, 1);
    	Range new_range_2 = new Range(0, Float.NaN);
    	Range combined_range = Range.combineIgnoringNaN(new_range_1, new_range_2);
    	assertEquals(1, combined_range.getUpperBound(), 0.00001d);
    }
    
    @Test
    public void maxTest3()
    {
    	Range new_range_1 = new Range(0, 1);
    	Range new_range_2 = new Range(0, 3);
    	Range combined_range = Range.combineIgnoringNaN(new_range_1, new_range_2);
    	assertEquals(3, combined_range.getUpperBound(), 0.00001d);
    }
    
    @Test
    public void maxTest4()
    {
    	Range new_range_1 = new Range(0, Float.NaN);
    	Range new_range_2 = new Range(0, Float.NaN);
    	Range combined_range = Range.combineIgnoringNaN(new_range_1, new_range_2);
    	assertEquals(Float.NaN, combined_range.getUpperBound(), 0.00001d);
    }
    
    @Test
    public void minTest1()
    {
    	Range new_range_1 = new Range(Float.NaN, 0);
    	Range new_range_2 = new Range(1, 2);
    	Range combined_range = Range.combineIgnoringNaN(new_range_1, new_range_2);
    	assertEquals(1, combined_range.getLowerBound(), 0.00001d);
    }
    
    @Test
    public void minTest2()
    {
    	Range new_range_1 = new Range(1, 2);
    	Range new_range_2 = new Range(Float.NaN, 0);
    	Range combined_range = Range.combineIgnoringNaN(new_range_1, new_range_2);
    	assertEquals(1, combined_range.getLowerBound(), 0.00001d);
    }
    
    @Test
    public void minTest3()
    {
    	Range new_range_1 = new Range(1, 2);
    	Range new_range_2 = new Range(3, 4);
    	Range combined_range = Range.combineIgnoringNaN(new_range_1, new_range_2);
    	assertEquals(1, combined_range.getLowerBound(), 0.00001d);
    }
    
    @Test
    public void minTest4()
    {
    	Range new_range_1 = new Range(Float.NaN, 0);
    	Range new_range_2 = new Range(Float.NaN, 0);
    	Range combined_range = Range.combineIgnoringNaN(new_range_1, new_range_2);
    	assertEquals(Float.NaN, combined_range.getLowerBound(), 0.00001d);
    }
   
    @After
    public void tearDown() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }
}
