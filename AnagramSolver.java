// Paul Lam
// Section AG, Neel jog
// A6 AnagramSolver
// AnagramSolver takes in a phrase and creates a combination of anagram from
// a given dictionary.

import java.util.*;

public class AnagramSolver {
   
   private HashMap<String, LetterInventory> dictionaryMap;
   private List<String> dictionary;
   
   // Purpose: Creates a dictionary from the given list that will be
   // use to make an anagram.
   // Pre: Given list is not empty and does not contain duplicates.
   // Post:Takes in the given list and creates a dictionary used to create 
   // combinations of anagrams.
   // Parameters: List = the given list of words for the dictionary.
   public AnagramSolver(List<String> list) {
      dictionary = list;
      dictionaryMap = new HashMap<String, LetterInventory>();
      for (String str : dictionary) {
         dictionaryMap.put(str, new LetterInventory(str));
      }
   }
   
   // Purpose: Takes in the given phrase and number of anagrams per combination
   // and prints out all possible combinations. If max 0 is selected, all possible
   // combinations will be printed (regardless of number of anagrams per combo).
   // Pre: The max amount of anagrams per combinations must not be less than 0.
   // If conditions are violated, throws an IllegalArgumentException.
   // Post: Uses the given phrase and number of anagrams per combination to print out
   // all possible combinations.If max is 0, all possible combinations will be printed
   // regardless of how many anagrams are in the combo.
   // Parameters: s = the given phrase used to find anagram combinations.
   // max: the max number of anagrams per combination.
   public void print(String s, int max) {
      if (max < 0) {
         throw new IllegalArgumentException();
      }
      LetterInventory givenPhrase = new LetterInventory(s);
      Stack<String> stk = new Stack<>();
      List<String> prunedStr = prune (givenPhrase, dictionary);
      printHelper(givenPhrase, max, stk, prunedStr);      
   }
    
   // Purpose: Takes in the given max and given phrase to find all possible
   // anagram combinations
   // Pre: The given max must not be less than 0.
   // Post: Takes in the given values and finds all of the possible
   // anagram combinations based off of the max number of anagrams per combo
   // and given phrase.
   // Parameters: given = The given phrase by the clinet.
   // max = The max amount of anagrams per combination.
   // s = the stack used to store the anagram comibations
   // dictionary = the given list/dictionary used to create anagrams
   // combinations out of.
   private void printHelper(LetterInventory given, int max, 
                                  Stack<String> s, List<String> dictionary) {
      if (given.isEmpty()) {
         System.out.println(s);
      } else if (s.size() < max || max == 0) {
         for (String str : dictionary) {
         s.push(str); 
         LetterInventory temp = given.subtract(dictionaryMap.get(str));
         if (temp != null) {
            printHelper(temp, max, s, dictionary);
         }
         s.pop();
         }
      }      
   }
   
   // Purpose: Prunes the given dictionary to help insure unnecessary words
   // are not explored. 
   // Pre: Given list is not empty and does not contain duplicates.
   // Post: returns the pruned dictionary to help reduce unnecessary explored
   // words. 
   // Parameters: phrase: The given phrase by the client.
   // tempDictionary: The dictionary used to create anagram combionations.
   private List<String> prune(LetterInventory phrase, List<String> tempDictionary) {
      List<String> updateDictionary = new ArrayList<String>();
      for (String word : dictionary) {
         if (phrase.size() >= word.length()) {
            updateDictionary.add(word);
         }
      }
      return updateDictionary;
   }   
}