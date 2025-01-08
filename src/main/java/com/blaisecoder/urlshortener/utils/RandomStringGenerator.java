package com.blaisecoder.urlshortener.utils;


import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class RandomStringGenerator {
    private static final String LETTERS="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGITS = "0123456789";
    private static final String ALPHANUMERIC = LETTERS + DIGITS;
    public String generateRandomAlphanumericString(int length) {
        //ensure custom id is at least 6 character length
        if (length < 6) {
            length=6;
        }

        Random random = new Random();
        StringBuilder result = new StringBuilder(length);

        // Ensure at least one letter and one digit
        result.append(LETTERS.charAt(random.nextInt(LETTERS.length())));
        result.append(DIGITS.charAt(random.nextInt(DIGITS.length())));

        // Fill the remaining characters with random alphanumeric
        for (int i = 2; i < length; i++) {
            result.append(ALPHANUMERIC.charAt(random.nextInt(ALPHANUMERIC.length())));
        }

        // Shuffle the result to randomize the positions of the letter and digit
        return shuffleString(result.toString());
    }
    // Helper method to shuffle the characters in a string
    private static String shuffleString(String input) {
//        characters in the input
        char[] characters = input.toCharArray();
        Random random = new Random();
        for (int i = characters.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            // Swap characters
            char temp = characters[i];
            characters[i] = characters[j];
            characters[j] = temp;
        }
        return new String(characters);
    }
    public   String generateShortUrl(){
     String characters="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
  Random random=new Random();
  StringBuilder shortUrl=new StringBuilder(8);
  for (int i=0;i<8;i++){
      shortUrl.append(characters.charAt(random.nextInt(characters.length())));

  }
  return shortUrl.toString();

 }


}
