package com.javiermarsicano.algorithms.amazon;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

/*
Amazon is building a way to help customers search reviews quicker by providing real-time suggestions to search terms when
the customer starts typing. When given a minimum of two characters into the search field the system will suggest at most
three keywords from the review word repository. As the customer continues to type in the reviews search bar the relevant
keyword suggestions will update automatically.
Write an algorithm that will output a maximum of three keyword suggestions after each character is typed by the customer
in the search field.
If there are more than three acceptable keywords, return the keywords that are first in alphabetical order.

Only return keyword suggestions after the customer has entered two characters.

Keyword suggestions must start with the characters already typed
Both the repository and the customerQuery should be compared in a case-insensitive way.
Example
Input:
repository = [ "mobile", "mouse", "moneypot", "monitor", "mousepad" ]
customerQuery = "mouse"
Output:
["mobile", "moneypot", "monitor"]
["mouse", "mousepad"]
["mouse", "mousepad"]
["mouse", "mousepad"]

Explanation:
The chain of words that will generate in the search box will be
mo, mou, mous, mouse
and each line from output shows the suggestion of "mo", "mou", "mous", "mouse", respectively in each line.
For the keyword suggestions made by the system that are generated for 'mo', the matches that will be generated are:
["mobile", "mouse", "moneypot", "monitor", "mousepad"]
Alphabetically, they will be reordered to [ "mobile", "moneypot", "monitor", "mouse", "mousepad" ].
Thus the keyword suggestions made by the system are [ "mobile", "moneypot", "monitor"].
 */

class Result {

    /*
     * Complete the 'searchSuggestions' function below.
     *
     * The function is expected to return a 2D_STRING_ARRAY.
     * The function accepts following parameters:
     *  1. STRING_ARRAY repository
     *  2. STRING customerQuery
     */

    public static List<List<String>> searchSuggestions(List<String> repository, String customerQuery) {
        // Write your code here
        return new ArrayList<>();
    }

}

public class CustomerReviews {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int repositoryCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<String> repository = IntStream.range(0, repositoryCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
                .collect(toList());

        String customerQuery = bufferedReader.readLine();

        List<List<String>> result = Result.searchSuggestions(repository, customerQuery);

        result.stream()
                .map(
                        r -> r.stream()
                                .collect(joining(" "))
                )
                .map(r -> r + "\n")
                .collect(toList())
                .forEach(e -> {
                    try {
                        bufferedWriter.write(e);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                });

        bufferedReader.close();
        bufferedWriter.close();
    }
}
