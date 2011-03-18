# PDF (Pretty Damn Fancy)

Java PDF Brute Force Cracker.  Works with wordlist or alphabet, to repeatedly attempt to open a PDF file.  This was hacked together to solve a day's problem, and maybe it'll be useful to someone else.

Introduced in [this blog post](http://blog.johncrepezzi.com/archives/118)

---

## Provides the following cracking methods:

    boolean attempt(String password);
    String crackViaWordlistFiles(Collection<String> wordlistFiles);
    String crackViaWordlistFile(String wordlistFile);
    String crackViaWordlist(Collection<String> words);
    String crackViaBruteForce(String alphabet);
    String crackViaBruteForce(String alphabet, int length);
    String crackViaBruteForce(String alphabet, int lower_length, int upper_length);

---

## Dependencies

* iText
* BouncyCastle

---

### Author

* John Crepezzi [john.crepezzi@gmail.com](mailto:john.crepezzi@gmail.com)

---

### License

Licensed under the MIT License (attached)
