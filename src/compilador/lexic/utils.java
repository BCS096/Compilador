/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilador.lexic;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author emanu
 */
public class utils {
    private HashMap<String, ArrayList<String>> dictionaries;
    private ArrayList<String> currentDictionary;
    public utils(){
        dictionaries = new HashMap<>();
        for (File file : getAllDictionaryFiles("src/dics/")) {
            try {
                List<String> allWords = Files.readAllLines(Paths.get(file.getAbsolutePath()));

                dictionaries.put(file.getName().replace(".dic", ""),
                        allWords.stream().map(word -> word.trim())
                                .collect(Collectors.toCollection(ArrayList::new)));
            } catch (IOException ex) {
                Logger.getLogger(utils.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        currentDictionary = dictionaries.get(getDictionaryNames().iterator().next());
    }
    public String Levenshtein(String input){
        String originalText = input;
        String[] words = originalText.split("\\s");
        List<String> dictionary = currentDictionary;

        ArrayList<CleanWordPair> result = new ArrayList<>();

        long initialTime = System.currentTimeMillis();
        for (String word : words) {
            String cleanWord = word.toLowerCase().trim().replaceAll("[¡!.,;:–\"'-\\[\\]\\(\\)?¿—/?]", "");

            int minDistance = Integer.MAX_VALUE;
            String wordToReplace = "";
            for (String dictionaryWord : dictionary) {
                int distance = levenshteinDistance(dictionaryWord.toLowerCase(), cleanWord);
                if (distance < minDistance) {
                    minDistance = distance;
                    wordToReplace = dictionaryWord.toLowerCase();
                    if(distance == 0) {
                        result.add(new CleanWordPair(cleanWord, cleanWord));
                        break;
                    }
                }
            }

            if (minDistance != 0 && !"".equals(cleanWord)) {
                result.add(new CleanWordPair(cleanWord, wordToReplace));
            }
        }
        long finalTime = System.currentTimeMillis();
        System.out.println("Process (revise / correction) took: " + (finalTime - initialTime) + " ms.");
        
        return result.get(0).getNewWord();
    }
    
    private File[] getAllDictionaryFiles(String path) {
        File directoryPath = new File(path);
        return directoryPath.listFiles((File file) -> file.isFile() && file.getName().endsWith(".dic"));
    }
    
    public ArrayList<String> getDictionaryNames() {
        return dictionaries.keySet().stream().collect(Collectors.toCollection(ArrayList::new));
    }
    
    public static int levenshteinDistance(String str1, String str2) {
        int[][] matrix = new int[str1.length() + 1][str2.length() + 1];

        for (int i = 0; i <= str1.length(); i++) {
            for (int j = 0; j <= str2.length(); j++) {
                if (i == 0) {
                    matrix[i][j] = j;
                } else if (j == 0) {
                    matrix[i][j] = i;
                } else {
                    matrix[i][j] = min(matrix[i - 1][j - 1]
                            + (str1.charAt(i - 1) == str2.charAt(j - 1) ? 0 : 1),
                            matrix[i - 1][j] + 1,
                            matrix[i][j - 1] + 1);
                }
            }
        }

        return matrix[str1.length()][str2.length()];
    }

    private static int min(int... ints) {
        return Arrays.stream(ints).min().getAsInt();
    }
}

class CleanWordPair {

    private final String cleanWord;
    private final String newWord;

    public CleanWordPair(String cleanWord, String newWord) {
        this.cleanWord = cleanWord;
        this.newWord = newWord;
    }

    public String getCleanWord() {
        return this.cleanWord;
    }

    public String getNewWord() {
        return this.newWord;
    }
}
