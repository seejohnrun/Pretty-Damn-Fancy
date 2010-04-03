/*
    John Crepezzi (c) 2009
    <john@crepezzi.com>
*/
package com.crepezzi.pdf;

import com.itextpdf.text.exceptions.BadPasswordException;
import com.itextpdf.text.pdf.PdfReader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;

/**
 *
 * @author based
 */
class PdfCracker {

    private String filename;

    // define some basic alphabets for the user
    public static final String ALPHA_LOWER = "abcdefghijklmnopqrstuvwxyz";
    public static final String ALPHA_UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String ALPHA_CASE_INSENSITIVE = ALPHA_LOWER;
    public static final String ALPHA_CASE_SENSITIVE = ALPHA_LOWER + ALPHA_UPPER;
    public static final String NUMERIC = "0123456789";
    public static final String ALPHA_NUM_CASE_INSENSITIVE = ALPHA_CASE_INSENSITIVE + NUMERIC;
    public static final String ALPHA_NUM_CASE_SENSITIVE = ALPHA_CASE_SENSITIVE + NUMERIC;

    PdfCracker(String filename) {
        this.filename = filename;
    }

    /**
     * Attempt a given password against the PDF file located at this.filename
     * @param password The password to attempt
     * @return The password for the PDF, null otherwise
     * @throws IOException
     */
    boolean attempt(String password) throws IOException {

        try {
            //attempt to open the reader stream
            PdfReader reader = new PdfReader(this.filename, password.getBytes());
            reader.close();
            return true;
        } catch (BadPasswordException e) {
            return false;
        }

    }

    /**
     * Attempt to crack a password using one of a set of wordlist files
     * @param wordlistFiles The list of full paths to word lists (newline separated words)
     * @return The password of the PDF, null otherwise
     * @throws IOException @TODO partial failure
     */
    String crackViaWordlistFiles(Collection<String> wordlistFiles) throws IOException {

        for (String file : wordlistFiles) {

            //try the crack
            String password = this.crackViaWordlistFile(file);

            //if successful, print the password and exit
            if (password != null) return password;

        }

        return null;

    }

    /**
     * Attempt to crack a PDF file given a specific wordlist file's full path
     * @param wordlistFile The full path to the wordlist file (newline separated words)
     * @return The password of the PDF, null otherwise
     * @throws IOException
     */
    String crackViaWordlistFile(String wordlistFile) throws IOException {

        //go through every password int the file til we find it
        BufferedReader words = new BufferedReader(new FileReader(wordlistFile));
        String password;

        while ((password = words.readLine()) != null) {

            //is password found?
            if (attempt(password)) return password;

        }

        return null;

    }

    /**
     * Attempt to crack a PDF password given a collection of passwords to try
     * @param words The passwords to try
     * @return The password of the PDF, null otherwise
     * @throws IOException
     */
    String crackViaWordlist(Collection<String> words) throws IOException {

        for (String word : words) {
            if (attempt(word)) return word;
        }

        return null;

    }

    /**
     * Attempt to crack PDF via brute force of a given alphabet, creating strings of length @length
     * Try passwords of all lengths
     * @param alphabet The alphabet to use
     * @return The password of the PDF if found, null otherwise
     * @throws IOException
     */
    String crackViaBruteForce(String alphabet) throws IOException {
    	
    	return this.crackViaBruteForce(alphabet, 1, alphabet.length());

    }
    
    /**
     * Attempt to crack PDF via brute force of a given alphabet, creating strings of length @length
     * @param alphabet The alphabet to use
     * @param length The length of the passwords to attempt
     * @return The password of the PDF if found, null otherwise
     * @throws IOException
     */
    String crackViaBruteForce(String alphabet, int length) throws IOException {

        //need some type of string
        if (alphabet.length() < 1) return null;

        //get some basic information used for iterating
        char beginning_char = alphabet.charAt(0);
        char ending_char = alphabet.charAt(alphabet.length() - 1);
        long keyspace = (long) Math.pow(alphabet.length(), length);

        //make an array and initialize it
        char[] comp = new char[length];
        for (int i = 0; i < length; i++) comp[i] = beginning_char;

        for (long j = 0; j < keyspace; j++) {

            //is this password correct?
            if (this.attempt(new String(comp))) return new String(comp);

            for (int k = 0; k < length; k++) {

                if (comp[k] == ending_char) continue;

                comp[k] = alphabet.charAt(alphabet.indexOf(comp[k]) + 1);
                if (k > 0) for (int z = 0; z < k; z++) comp[z] = beginning_char;

                break;
                
            }

        }

        return null;

    }

    /**
     * Attempt to crack a PDF password via brute force, of all combinations of an alphabet
     * of length between upper length and lower length (inclusive)
     * @param alphabet The alphabet to use for the crack
     * @param lower_length The lower length of passwords to try
     * @param upper_length The upper length of passwords to try
     * @return The password of the PDF, null otherwise
     * @throws IOException
     */
    String crackViaBruteForce(String alphabet, int lower_length, int upper_length) throws IOException {

        if (lower_length > upper_length) throw new IllegalArgumentException("lower length must be less than upper length");
        if (lower_length < 1 || upper_length > Integer.MAX_VALUE) throw new IllegalArgumentException("lower and upper length values must be positive integers");

        for (int i = lower_length; i <= upper_length; i++) {

            String password = this.crackViaBruteForce(alphabet, i);
            if (password != null) return password;

        }

        return null;

    }

}
