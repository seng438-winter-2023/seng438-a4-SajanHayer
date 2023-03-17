package org.jfree.data;

import static org.junit.Assert.*;


import org.jmock.*;
import org.junit.*;



public class DataUtilitiesTest {
	
	//START TEST CASES FOR METHOD 1 - calculateColumnTotal
	//testing that the correct value is returned using positive numbers
	
	//Testing valid values within a table
	@Test
	public void test1ColumnTotal() {
	//creating a real testValue and testing different values
		 double[] rows = {7.5,2.5,3.5,4.5};
		 DefaultKeyedValues2D testValue = new DefaultKeyedValues2D();
	        testValue.addValue(7.5, "R0", "C0");
	        testValue.addValue(2.5, "R0", "C1");
	        testValue.addValue(3.5, "R1", "C0");
	        testValue.addValue(4.5, "R1", "C1");
	        assertEquals(11.0, DataUtilities.calculateColumnTotal(testValue, 0), 0.000000001d);
 	}   
	
	@Test
	//testing a table with a null value
	public void test2ColumnTotal() {
	//creating a real testValue and testing different values
		 double[] rows = {7.5,2.5,3.5,4.5};
		 DefaultKeyedValues2D testValue = new DefaultKeyedValues2D();
	        testValue.addValue(7.5, "R0", "C0");
	        testValue.addValue(2.5, "R0", "C1");
	        testValue.addValue(3.5, "R1", "C0");
	        testValue.addValue(null, "R1", "C1");
	        assertEquals(2.5, DataUtilities.calculateColumnTotal(testValue, 1), 0.000000001d);
 	}   
	
	//Testing valid table, with both rows as valid
	@Test
	public void test3ColumnTotal() {
	//Creating a real testValue
		 double[] rows = {7.5,2.5,3.5,4.5};
		 DefaultKeyedValues2D testValue = new DefaultKeyedValues2D();
	        testValue.addValue(7.5, "R0", "C0");
	        testValue.addValue(2.5, "R0", "C1");
	        testValue.addValue(3.5, "R1", "C0");
	        testValue.addValue(4.5, "R1", "C1");
	        assertEquals(11.0, DataUtilities.calculateColumnTotal(testValue, 0,  new int[] {0, 1}), 0.000000001d);    
 	}   

	//testing with valid table, with no valid rows
	@Test
	public void test4ColumnTotal() {
	//Creating a real testValue
		 double[] rows = {7.5,2.5,3.5,4.5};
		 DefaultKeyedValues2D testValue = new DefaultKeyedValues2D();
	        testValue.addValue(7.5, "R0", "C0");
	        testValue.addValue(2.5, "R0", "C1");
	        testValue.addValue(3.5, "R1", "C0");
	        testValue.addValue(4.5, "R1", "C1");
	        assertEquals(0.0, DataUtilities.calculateColumnTotal(testValue, 1,  new int[] {3}), 0.000000001d);        
 	}   
	
	//testing with a null value in the table
	@Test
	public void test5ColumnTotal() {
	//Creating a real testValue
		 double[] rows = {7.5,2.5,3.5,4.5};
		 DefaultKeyedValues2D testValue = new DefaultKeyedValues2D();
	        testValue.addValue(7.5, "R0", "C0");
	        testValue.addValue(2.5, "R0", "C1");
	        testValue.addValue(3.5, "R1", "C0");
	        testValue.addValue(null, "R1", "C1");
	        assertEquals(2.5, DataUtilities.calculateColumnTotal(testValue, 1, new int[] {0, 1}), 0.000000001d);     
 	}
	
	@Test
	public void tes6calculateColumnTotal() {
		try {
			DefaultKeyedValues2D testValue = null;
			// creating a null double 1D array
			DataUtilities.calculateColumnTotal(testValue, 0, new int[] {0});
			// passing the null object to the createNumberArray2D function
			fail("This method should throw an exception!");
			// creating a failure message for if createNumberArray2D does not throw an
			// exception
		} catch (Exception e) {
			assertEquals("The exception thrown type is IllegalArgumentException", IllegalArgumentException.class,
					e.getClass());
			// catching the exception, asserting that an IllegalArgumentException was thrown
		}
	}
	


 	//Start of Test Cases for METHOD 2

	//New Tests for calculateRowTotal
	
	//testing with a valid table
	@Test
	public void  test1calculateRowTotal() {
		double[] rows = {7.5,2.5,3.5,4.5};
		 DefaultKeyedValues2D testValue = new DefaultKeyedValues2D();
	        testValue.addValue(7.5, "R0", "C0");
	        testValue.addValue(2.5, "R0", "C1");
	        testValue.addValue(3.5, "R1", "C0");
	        testValue.addValue(4.5, "R1", "C1");
	        assertEquals(10.0, DataUtilities.calculateRowTotal(testValue, 0), 0.000000001d);
	}
	
	
	//testing with a null value in the table
	@Test
	public void  test2calculateRowTotal() {
		double[] rows = {7.5,2.5,3.5,4.5};
		 DefaultKeyedValues2D testValue = new DefaultKeyedValues2D();
	        testValue.addValue(7.5, "R0", "C0");
	        testValue.addValue(2.5, "R0", "C1");
	        testValue.addValue(3.5, "R1", "C0");
	        testValue.addValue(null, "R1", "C1");
	        assertEquals(3.5, DataUtilities.calculateRowTotal(testValue, 1), 0.000000001d);
	}
	
	//testing with a valid table, with both columns as valid
	@Test
	public void  test3calculateRowTotal() {
		double[] rows = {7.5,2.5,3.5,4.5};
		 DefaultKeyedValues2D testValue = new DefaultKeyedValues2D();
	        testValue.addValue(7.5, "R0", "C0");
	        testValue.addValue(2.5, "R0", "C1");
	        testValue.addValue(3.5, "R1", "C0");
	        testValue.addValue(4.5, "R1", "C1");
	        assertEquals(10.0, DataUtilities.calculateRowTotal(testValue, 0,  new int[] {0, 1}), 0.000000001d);
	}
	
	//testing a valid table with invalid column number
	@Test
	public void  test4calculateRowTotal() {
		double[] rows = {7.5,2.5,3.5,4.5};
		 DefaultKeyedValues2D testValue = new DefaultKeyedValues2D();
	        testValue.addValue(7.5, "R0", "C0");
	        testValue.addValue(2.5, "R0", "C1");
	        testValue.addValue(3.5, "R1", "C0");
	        testValue.addValue(4.5, "R1", "C1");
	        assertEquals(0.0, DataUtilities.calculateRowTotal(testValue, 1,  new int[] {3}), 0.000000001d);
	}
	
	//testing with a null value in the table
	@Test
	public void  test5calculateRowTotal() {
		double[] rows = {7.5,2.5,3.5,4.5};
		 DefaultKeyedValues2D testValue = new DefaultKeyedValues2D();
	        testValue.addValue(7.5, "R0", "C0");
	        testValue.addValue(2.5, "R0", "C1");
	        testValue.addValue(3.5, "R1", "C0");
	        testValue.addValue(null, "R1", "C1");
	        assertEquals(3.5, DataUtilities.calculateRowTotal(testValue, 1, new int[] {0, 1}), 0.000000001d);
	}
	
	//new tests added
	//testing for the v < validCols.length within the function to pass the mutation, we have no coloumns so they 
	//should be equal
		@Test
		public void  test6calculateRowTotal() {
			double[] rows = {7.5,2.5,3.5,4.5};
			 DefaultKeyedValues2D testValue = new DefaultKeyedValues2D();
		        assertEquals(0.0, DataUtilities.calculateRowTotal(testValue, 0), 0.000000001d);
		}
	
		//testing col < colCount within the function by having one coloumn  so 
		@Test
		public void  test7calculateRowTotal() {
			double[] rows = {7.5,2.5,3.5,4.5};
			 DefaultKeyedValues2D testValue = new DefaultKeyedValues2D();
			 testValue.addValue(7.5, "R0", "C0");
		     assertEquals(7.5, DataUtilities.calculateRowTotal(testValue, 0, new int[] {0}), 0.000000001d);
		}
		
		@Test
		public void test8calculateRowTotal() {
			try {
				DefaultKeyedValues2D testValue = null;
				// creating a null double 1D array
				DataUtilities.calculateRowTotal(testValue, 0, new int[] {0});
				// passing the null object to the createNumberArray2D function
				fail("This method should throw an exception!");
				// creating a failure message for if createNumberArray2D does not throw an
				// exception
			} catch (Exception e) {
				assertEquals("The exception thrown type is IllegalArgumentException", IllegalArgumentException.class,
						e.getClass());
				// catching the exception, asserting that an IllegalArgumentException was thrown
			}
		}
	

	

	//End of Test Cases for METHOD 2

	// START TEST CASES FOR METHOD 3 - createNumberArray
	 
	//testing that using valid double values returns Number[] array
	 @Test
	 public void test1CreateNumberArray() {
	     double[] data = {1.5, 2.5, 3.5, 4.5, 5.5};
	     java.lang.Number[] result = DataUtilities.createNumberArray(data);
	     
	     assertTrue(result instanceof java.lang.Number[]);        
	 }
	 
	 //Testing that the method returns a Number[] array returns correct values using positive numbers
	 @Test
	 public void test2CreateNumberArray() {
	     double[] data = {1.5, 2.5, 3.5, 4.5, 5.5};
	     java.lang.Number[] result = DataUtilities.createNumberArray(data);
	     
	     Assert.assertArrayEquals(new Number[]{1.5, 2.5, 3.5, 4.5, 5.5}, result);
	 }
	 
	//Testing that the method returns a Number[] array returns correct values using negative numbers
	@Test
	public void test3CreateNumberArray() {
		double[] data = {-1.0, -1.0, -1.0};
		java.lang.Number[] result = DataUtilities.createNumberArray(data);
		     
		Assert.assertArrayEquals(new Number[]{-1.0, -1.0, -1.0}, result);
	}
	
	//Testing that the method returns a Number[] array returns correct values using zero's
	@Test
	public void test4CreateNumberArray() {
		double[] data = {0.0, 0.0, 0.0};
		java.lang.Number[] result = DataUtilities.createNumberArray(data);
			     
		Assert.assertArrayEquals(new Number[]{0.0, 0.0, 0.0}, result);
	}
	
	@Test
	public void createNumberArrayNull() {
		try {
			double[] array = null;
			// creating a null double 1D array
			DataUtilities.createNumberArray(array);
			// passing the null object to the createNumberArray2D function
			fail("This method should throw an exception!");
			// creating a failure message for if createNumberArray2D does not throw an
			// exception
		} catch (Exception e) {
			assertEquals("The exception thrown type is IllegalArgumentException", IllegalArgumentException.class,
					e.getClass());
			// catching the exception, asserting that an IllegalArgumentException was thrown
		}
	}
	
//	//testing that correct exception is thrown
//	@Test (expected = InvalidParameterException.class)
//	public void test5CreateNumberArray() {
//		double[] data = null;
//	    DataUtilities.createNumberArray(data);
//	}
//	 
	//END of test cases for method 3

	//START of test cases for method 4
	//Testing that the method returns a Number[][] array 
	@Test
	public void test1CreateNumberArray2D(){
		double[][] test =  {{1.0,1.0}, {1.0,1.0}};
		Number[][] result = DataUtilities.createNumberArray2D(test);
		assertTrue(result instanceof Number[][]);
	}
	
	//Testing that the method returns a Number[][] array returns correct values using positive numbers
	@Test
	public void test2CreateNumberArray2D(){
		double[][] test =  {{1.0,1.0}, {1.0,1.0}};
		Number[][] result = DataUtilities.createNumberArray2D(test);
		Assert.assertArrayEquals(new Number[][]{{1.0,1.0}, {1.0,1.0}}, result);
	}
	
	//Testing that the method returns a Number[][] array returns correct values using negative numbers
	@Test
	public void test3CreateNumberArray2D(){
		double[][] test =  {{-1.0,-1.0}, {-1.0, -1.0}};
		Number[][] result = DataUtilities.createNumberArray2D(test);
		Assert.assertArrayEquals(new Number[][]{{-1.0, -1.0}, {-1.0, -1.0}}, result);
	}
		
	//Testing that the method returns a Number[][] array returns correct values using zero's
	@Test
	public void test4CreateNumberArray2D(){
		double[][] test =  {{0.0,0.0}, {0.0, 0.0}};
		Number[][] result = DataUtilities.createNumberArray2D(test);
		Assert.assertArrayEquals(new Number[][]{{0.0,0.0}, {0.0, 0.0}}, result);
	}
	
	@Test
	public void createNumberArrayNull2D() {
		try {
			double[][] array = null;
			// creating a null double 1D array
			DataUtilities.createNumberArray2D(array);
			// passing the null object to the createNumberArray2D function
			fail("This method should throw an exception!");
			// creating a failure message for if createNumberArray2D does not throw an
			// exception
		} catch (Exception e) {
			assertEquals("The exception thrown type is IllegalArgumentException", IllegalArgumentException.class,
					e.getClass());
			// catching the exception, asserting that an IllegalArgumentException was thrown
		}
	}

	
//	//Test for number array using  NULL 
//	@Test (expected = InvalidParameterException.class)
//	public void test5CreateNumberArray2D() {
//		double[][] test = null;
//		Number[][] result = DataUtilities.createNumberArray2D(test);
//		assertTrue(result instanceof Number[][]);
//		
//	}
	
	//END of test cases for METHOD 4

	//START of test cases for METHOD 5
	//Test cumulative percentage, using mocking
	
	
	//testing with a valid Key table
	@Test
	public void  getCumulativePercentagesTest() {
		double[] rows = {7.5,2.5,3.5,4.5};
		 DefaultKeyedValues testValue = new DefaultKeyedValues();
	        testValue.addValue("Key1", 5);
	        testValue.addValue("Key2", 9);
	        testValue.addValue("Key3", 2);
	        KeyedValues result = DataUtilities.getCumulativePercentages(testValue);
	        assertEquals(0.3125, result.getValue(0).doubleValue(), .000000001d);
	}
	
	//testing with a null value within the table
	@Test
	public void  getCumulativePercentagesTest2() {
		double[] rows = {7.5,2.5,3.5,4.5};
		 DefaultKeyedValues testValue = new DefaultKeyedValues();
	        testValue.addValue("Key1", null);
	        testValue.addValue("Key2", 9);
	        testValue.addValue("Key3", 2);
	        KeyedValues result = DataUtilities.getCumulativePercentages(testValue);
	        boolean isTrue = Double.isNaN(result.getValue(0).doubleValue());
	        assertFalse(isTrue);
	}
	
	@Test
	public void createCumulativePercentage() {
		try {
			DefaultKeyedValues testValue = null;
			// creating a null double 1D array
			DataUtilities.getCumulativePercentages(testValue);
			// passing the null object to the createNumberArray2D function
			fail("This method should throw an exception!");
			// creating a failure message for if createNumberArray2D does not throw an
			// exception
		} catch (Exception e) {
			assertEquals("The exception thrown type is IllegalArgumentException", IllegalArgumentException.class,
					e.getClass());
			// catching the exception, asserting that an IllegalArgumentException was thrown
		}
	}
	
//	@Test
//	public void getCumulativePercentagesTest() {
//		
//		//before
//		Mockery mockingContextKey = new Mockery();
//		KeyedValues valuesNewKey = mockingContextKey.mock(KeyedValues.class);
//		mockingContextKey.checking(new Expectations() {{
//			//creating a KeyValue object, with two arrays
//			String [] keyArray = {"Key1","Key2","Key3"};
//			double[] values = {5,9,2};
//			//loop through and create object using arrays from before
//			for(int i= 0; i < values.length;i++) {
//				allowing(valuesNewKey).getValue(i);
//				will (returnValue(Double.valueOf(values[i])));
//				allowing(valuesNewKey).getKey(i);
//				will(returnValue(keyArray[i]));
//			}
//			//set item count amount, and Key list values 
//			allowing(valuesNewKey).getItemCount();
//			will(returnValue(3));
//			allowing(valuesNewKey).getKeys();
//			will(returnEnumeration(Arrays.asList(keyArray)));
//		}
//		});
//		//testing using example given in the javadocs, comparing 
//		//key value to the percentage that should be returned
//		KeyedValues result = DataUtilities.getCumulativePercentages(valuesNewKey);
//		assertEquals(0.3125, result.getValue(0).doubleValue(), .000000001d);
//	}
	
//	//Test cumulative percentage, using 0 as one of the values
//	@Test
//	public void getCumulativePercentagesTest2() {
//		//before
//		Mockery mockingContextKey = new Mockery();
//		KeyedValues valuesNewKey = mockingContextKey.mock(KeyedValues.class);
//		mockingContextKey.checking(new Expectations() {{
//			//creating a KeyValue object, with two arrays
//			String [] keyArray = {"Key1","Key2","Key3"};
//			double[] values = {0,9,2};
//			//loop through and create object using arrays from before
//			for(int i= 0; i < values.length;i++) {
//				allowing(valuesNewKey).getValue(i);
//				will (returnValue(Double.valueOf(values[i])));
//				allowing(valuesNewKey).getKey(i);
//				will(returnValue(keyArray[i]));
//			}
//			//set item count amount, and Key list values 
//			allowing(valuesNewKey).getItemCount();
//			will(returnValue(3));
//			allowing(valuesNewKey).getKeys();
//			will(returnEnumeration(Arrays.asList(keyArray)));
//		}
//		});
//		KeyedValues result = DataUtilities.getCumulativePercentages(valuesNewKey);
//		assertEquals(0, result.getValue(0).doubleValue(), .000000001d);
//		
//	}
	
//	//testing percentage using NULL object 
//	@Test (expected = InvalidParameterException.class)
//    	public void getCumulativePercentagesTestFail() {
//		KeyedValues valuesKey = null;
//		KeyedValues result = DataUtilities.getCumulativePercentages(valuesKey);
//	}
//	
	//END of test cases for METHOD 5
	
	//START of test cases for Equal
	// TEST The equal() Method in DataUtilities
    // Null First Array -> expected to return false because array2 is not null
    @Test
    public void testEqual1() {
        double[][] array1 = null;
        double[][] array2 = {{1.3, 5.0, 3.2}, {1.3, 5.0, 3.2}};
        
        boolean result = DataUtilities.equal(array1, array2);
        assertFalse(result);
    }
    
    // Null Second Array -> expected to return false
    @Test
    public void testEqual2() {
        double[][] array2 = null;
        double[][] array1 = {{1.3, 5.0, 3.2}, {1.3, 5.0, 3.2}};
        
        boolean result = DataUtilities.equal(array1, array2);
        assertFalse(result);
    }
    
    // Both arrays null -> expected to return True because array 2 is null
    @Test
    public void testEqual3() {
        double[][] array1 = null;
        double[][] array2 = null;
        
        boolean result = DataUtilities.equal(array1, array2);
        assertTrue(result);
    }
    
    // Both arrays identical -> expected to return True (both arrays equal)
    @Test
    public void testEqual4() {
        double[][] array1 = {{1.3, 5.0, 3.2}, {1.3, 5.0, 3.2}};
        double[][] array2 = {{1.3, 5.0, 3.2}, {1.3, 5.0, 3.2}};
        
        boolean result = DataUtilities.equal(array1, array2);
        assertTrue(result);
    }
    
 // Arrays of different length -> expected to return False
    @Test
    public void testEqual5() {
        double[][] array1 = {{1.3, 5.0, 3.2}};
        double[][] array2 = {{1.3, 5.0, 3.2}, {1.3, 5.0, 3.2}};
        
        boolean result = DataUtilities.equal(array1, array2);
        assertFalse(result);
    }
    
    // Expected to return false -> arrays are same length (2 elements each
    // but the values of sub arrays are different)
    @Test
    public void testEqual6() {
        double[][] array1 = {{1.3, 5.0, 3.2}, {1.3, 5.0, 3.2}};
        double[][] array2 = {{1.3, 5.0, 3.2}, {1.2, 7.0, 3.5}};
        
        boolean result = DataUtilities.equal(array1, array2);
        assertFalse(result);
    }
    
    // END TESTS FOR equal() METHOD
    
    
    // Start Tests FOR clone() METHOD
    // Expect a valid clone of the sourceArray
    @Test
    public void testClone1() {
        double[][] sourceArray = {{1.5, 2.5, 3.5}, {4.5, 5.5, 6.5}};
        
        double[][] resultClone = DataUtilities.clone(sourceArray);
        
        assertArrayEquals(sourceArray, resultClone);
    }
        
    // Sub Arrays in sourceArray are different lengths
    @Test
    public void testClone2() {
        double[][] sourceArray2 = {{1.5, 2.5}, {4.5, 5.5, 6.5}};
        
        double[][] resultClone2 = DataUtilities.clone(sourceArray2);
        
        assertArrayEquals(sourceArray2, resultClone2);
    }
    
 // First sub array is null -> expect a clone with first sub array as null
    // and the second sub array with appropriate values
    @Test
    public void testClone3() {
        double[][] sourceArray3 = {null, {4.5, 5.5, 6.5}};
        
        double[][] resultClone3 = DataUtilities.clone(sourceArray3);
        
        assertArrayEquals(sourceArray3, resultClone3);
    }
    
    
    @Test
	public void testCloneNull() {
		try {
			double[][] sourceArray = null;
			// creating a null double 1D array
			DataUtilities.clone(sourceArray);
			// passing the null object to the createNumberArray2D function
			fail("This method should throw an exception!");
			// creating a failure message for if createNumberArray2D does not throw an
			// exception
		} catch (Exception e) {
			assertEquals("The exception thrown type is IllegalArgumentException", IllegalArgumentException.class,
					e.getClass());
			// catching the exception, asserting that an IllegalArgumentException was thrown
		}
	}
    
    
    // END TESTS FOR clone() METHOD    
}
