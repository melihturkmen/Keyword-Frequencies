//Ahmet Melih Türkmen 27.12.2017
//This code is a basic keyword frequency calculator. Takes the words that we are looking for as a set and take a
//java file as a input from user to search these words.

import java.util.Scanner;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Map;

public class AhmetMelih_Turkmen {

	public static void main(String[] args) throws FileNotFoundException {

		// Taking input Java file name from user

		System.out.println("Enter the name of the input Java file:");
		Scanner input = new Scanner(System.in);
		String filename = input.nextLine();

		// Words that we are looking for

		String[] keywordString = { "abstract", "finally", "public", "boolean", "float", "return", "break", "for",
				"short", "byte", "goto", "static", "case", "if", "super", "catch", "implements", "switch", "char",
				"import", "synchronized", "class", "instanceof", "this", "const", "int", "throw", "continue",
				"interface", "throws", "default", "long", "transient", "do", "native", "try", "double", "new", "void",
				"else", "package", "volatile", "extends", "private", "while", "final", "protected", "true", "null" };

		Set<String> keywordSet = new HashSet<String>(Arrays.asList(keywordString));

		// Creating an ArrayList and add all words to that list

		ArrayList<String> words = new ArrayList<>();

		input = new Scanner(new File(filename));
		String text = "";
		while (input.hasNext()) {
			String line = input.nextLine();
			line = line.replaceAll("//.*|/\\*((.|\\n)(?!=*/))+\\*/", "");
			line = line.replaceAll("[^a-zA-Z ]", "");
			text = line + "";
			words.add(text);

		}

		// Creating TreeMap and add words with their occurences

		TreeMap<String, Integer> map = new TreeMap<String, Integer>();

		for (int i = 0; i < words.size(); i++) {
			String elements = words.get(i);
			String[] elementsArray = elements.toLowerCase().split("\\s+");
			for (int j = 0; j < elementsArray.length; j++) {
				elementsArray[j] = elementsArray[j].trim();

				if (keywordSet.contains(elementsArray[j])) {

					if (map.get(elementsArray[j]) == null) {
						map.put(elementsArray[j], 1);
					} else {
						int value = map.get(elementsArray[j]);
						map.put(elementsArray[j], value + 1);

					}
				}

			}

		}

		input.close();

		// Converting TreeMap to Sorted Set

		SortedSet<Map.Entry<String, Integer>> sorted = entriesSortedByValues(map);

		Object[] sortedArray = sorted.toArray();

		System.out.println("Keyword Frequencies in Descending Order");

		// Printing WordOccurences in Descending Order
		for (int a = 0; a < sortedArray.length; a++) {

			String sortedWords = String.valueOf(sortedArray[a]);
			String[] temp = sortedWords.split("=");
			int value = Integer.parseInt(temp[1]);

			String finalmap = String.format("%-10s : %10d", temp[0], value);
			System.out.println(finalmap);
		}
	}

	// I research and found that TreeMaps can not sorted by their values. So I
	// use these interface to convert TreeMap to SortedSet

	static <K, V extends Comparable<? super V>> SortedSet<Map.Entry<K, V>> entriesSortedByValues(Map<K, V> map) {
		SortedSet<Map.Entry<K, V>> sortedEntries = new TreeSet<Map.Entry<K, V>>(new Comparator<Map.Entry<K, V>>() {
			@Override
			public int compare(Map.Entry<K, V> e1, Map.Entry<K, V> e2) {
				int res = e2.getValue().compareTo(e1.getValue());
				return res != 0 ? res : 1;
			}
		});
		sortedEntries.addAll(map.entrySet());
		return sortedEntries;
	}

}
