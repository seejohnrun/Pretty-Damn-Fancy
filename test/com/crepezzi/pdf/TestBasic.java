package com.crepezzi.pdf;

import java.io.IOException;

import junit.framework.TestCase;

public class TestBasic extends TestCase {

	private static final String PASSWORD = PdfCrackerTest.PASSWORD;
	
	public void testBasic1() throws IOException {
		
		//Try to open directly with the right password and 
		//make sure we can open the file
		
		assertTrue(PdfCrackerTest.cracker.attempt(PASSWORD));
		
	}

	public void testBasic2() throws IOException {
		
		//Try to open directly with the wrong password and 
		//make sure we can't open the file
		
		assertFalse(PdfCrackerTest.cracker.attempt("wrong"));
		
	}
		
}
