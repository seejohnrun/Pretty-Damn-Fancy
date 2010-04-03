package com.crepezzi.pdf;

import junit.framework.Test;
import junit.framework.TestSuite;

public class PdfCrackerTest extends TestSuite {

	private static final String FILENAME = "data/samples/password_sample.pdf";
	protected static final String PASSWORD = "abc";
	
	protected static PdfCracker cracker = new PdfCracker(FILENAME);
	
	public static Test suite() { 
	          
		TestSuite suite = new TestSuite();
	    suite.addTestSuite(TestBasic.class);
		suite.addTestSuite(TestWordlists.class);
		suite.addTestSuite(TestWords.class);
		suite.addTestSuite(TestBruteForce.class);
		
		return suite; 
	     
	}
	
}
