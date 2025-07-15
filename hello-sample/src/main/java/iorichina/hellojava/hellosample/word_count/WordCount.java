package iorichina.hellojava.hellosample.word_count;
public class WordCount {
    public static void main(String[] args) {
        String sentence = "Hello world! Hello everyone. Hello world!";
        String word = "hello";
        int count = countOccurrences(sentence, word);
        System.out.printf("The word '%s' appears %d times in the sentence.%n", word, count);
    }
    public static int countOccurrences(String sentence, String word) {
        if (sentence == null || word == null || word.length() == 0) {
            return 0;
        }

        // 统一转小写
        sentence = sentence.toLowerCase();
        word = word.toLowerCase();
        int lenSentence = sentence.length();
        int lenWord = word.length();
        if (lenWord > lenSentence) {
            return 0;
        }

        int count = 0;
        // 滑动窗口，步长1（处理重叠）
        for (int i = 0; i <= lenSentence - lenWord; i++) {
            if (sentence.substring(i, i + lenWord).equals(word)) {
                count++;
            }
        }
        return count;
    }
}