package com.javiermarsicano.algorithms.hackerrank;

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

class Result {

    /*
     *
     * A company is performing an analysis on the computers at its main office. The computers are spaced along a single row.
     * For each group of contiguous computers of a certain length, that is, for each segment, determine the minimum amount
     * of disk space available on each computer. Return the maximum of these values as your answer.
     *
     * Example
     * n=4, the number of computers
     * space = [8,2,4,6]
     * x=2, the segment length
     *
     * The free disk space of computers in each of the segments is [8,2] [2,4] [4,6]. The minima of the three segments are
     * [2,2,4]. The maximum of these is 4.
     *
     *  Complete the 'segment' function below.
     *
     * The function is expected to return an INTEGER: The maximum of the minimum values of available space found while
     * analyzing the computers is segments of length x
     *
     * The function accepts following parameters:
     *  1. INTEGER x: the segment length to analyze
     *  2. INTEGER_ARRAY space: space on each of the computers
     */

    public static int segment(int x, List<Integer> space) {
        int max = 1;

        for (int index = x - 1; index < space.size(); index++) {
            int segmentMin = space.get(index);

            for (int offset = index; offset > index - x; offset--) {
                if (space.get(offset) < segmentMin) segmentMin = space.get(offset);
            }

            if (max < segmentMin) max = segmentMin;
        }

        return max;

    }

}

public class Space {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int x = Integer.parseInt(bufferedReader.readLine().trim());

        int spaceCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> space = IntStream.range(0, spaceCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine().replaceAll("\\s+$", "");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(toList());

        int result = Result.segment(x, space);

        System.out.println(result);

        bufferedReader.close();
    }
}

