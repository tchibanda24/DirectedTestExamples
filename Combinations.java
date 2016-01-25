//package com.epi;

/*
@slug
combinations-1
@summary
There are a number of testing applications in which it is required
to compute all subsets of a given size for a specified set.
@problem
Write a program which
computes all size $k$ subsets of $\{1,2,\dots,n\}$, where $k$ and $n$ are program inputs.
For example, if $k=2$ and $n=5$, then the result is the following:
$\{ \{1,2\}, \{1,3\}, \{1,4\}, \{1,5\}, \{2,3\}, \{2,4\}, \{2,5\}, \{3,4\}, \{3,5\}, \{4,5\} \}$
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Combinations {
    // @include
    public static List<List<Integer>> combinations(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> partialCombination = new ArrayList<>();
        directedCombinations(n, k, 1, partialCombination, result);
        return result;
    }

    private static void directedCombinations(int n, int k, int offset,
    List<Integer> partialCombination,
    List<List<Integer>> result) {
        if (partialCombination.size() == k) {
            result.add(new ArrayList<>(partialCombination));
            return;
        }

        // Generate remaining combinations over {offset, ..., n - 1} of size
        // numRemaining.
        final int numRemaining = k - partialCombination.size();
        for (int i = offset; i <= n && numRemaining <= n - i + 1; ++i) {
            partialCombination.add(i);
            directedCombinations(n, k, i + 1, partialCombination, result);
            partialCombination.remove(partialCombination.size() - 1);
        }
    }
    // @exclude

    private static void smallTest() {
        List<List<Integer>> result = combinations(4, 2);
        List<List<Integer>> goldenResult = Arrays.asList(
        Arrays.asList(1, 2), Arrays.asList(1, 3), Arrays.asList(1, 4),
        Arrays.asList(2, 3), Arrays.asList(2, 4), Arrays.asList(3, 4));
        assert(result.equals(goldenResult));
    }

    //Test a users solution against an expected result
    // kill the program on any failed result
    private static void unitTest(int n, int k, List<List<Integer>> expectedComb) {
		List<List<Integer>> got = combinations(n, k);
		if(!got.equals(expectedComb)) {
            System.err.println("Wrong result for n = " + n + " and k = " + k);
            System.err.println("Expected " + expectedComb);
            System.err.println("Got " + got);
            System.exit(-1);
        }
    }

    //generated some constant results to check agains
    private static final List<List<Integer>> res9_2 = Arrays.asList(Arrays.asList(1, 2), Arrays.asList(1, 3),
    Arrays.asList(1, 4), Arrays.asList(1, 5), Arrays.asList(1, 6), Arrays.asList(1, 7), Arrays.asList(1, 8),
    Arrays.asList(1, 9), Arrays.asList(2, 3), Arrays.asList(2, 4), Arrays.asList(2, 5), Arrays.asList(2, 6),
    Arrays.asList(2, 7), Arrays.asList(2, 8), Arrays.asList(2, 9), Arrays.asList(3, 4), Arrays.asList(3, 5),
    Arrays.asList(3, 6), Arrays.asList(3, 7), Arrays.asList(3, 8), Arrays.asList(3, 9), Arrays.asList(4, 5),
    Arrays.asList(4, 6), Arrays.asList(4, 7), Arrays.asList(4, 8), Arrays.asList(4, 9), Arrays.asList(5, 6),
    Arrays.asList(5, 7), Arrays.asList(5, 8), Arrays.asList(5, 9), Arrays.asList(6, 7), Arrays.asList(6, 8),
    Arrays.asList(6, 9), Arrays.asList(7, 8), Arrays.asList(7, 9), Arrays.asList(8, 9));

    private static final List<List<Integer>> res6_3 = Arrays.asList(Arrays.asList(1, 2, 3), Arrays.asList(1, 2, 4),
    Arrays.asList(1, 2, 5), Arrays.asList(1, 2, 6), Arrays.asList(1, 3, 4), Arrays.asList(1, 3, 5),
    Arrays.asList(1, 3, 6), Arrays.asList(1, 4, 5), Arrays.asList(1, 4, 6), Arrays.asList(1, 5, 6),
    Arrays.asList(2, 3, 4), Arrays.asList(2, 3, 5), Arrays.asList(2, 3, 6), Arrays.asList(2, 4, 5),
    Arrays.asList(2, 4, 6), Arrays.asList(2, 5, 6), Arrays.asList(3, 4, 5), Arrays.asList(3, 4, 6),
    Arrays.asList(3, 5, 6), Arrays.asList(4, 5, 6));

    private static final List<List<Integer>> res13_1 = Arrays.asList(Arrays.asList(1), Arrays.asList(2),
    Arrays.asList(3), Arrays.asList(4), Arrays.asList(5), Arrays.asList(6), Arrays.asList(7), Arrays.asList(8),
    Arrays.asList(9), Arrays.asList(10), Arrays.asList(11), Arrays.asList(12), Arrays.asList(13));

    private static final List<List<Integer>> res5_5 = Arrays.asList(Arrays.asList(1, 2, 3, 4, 5));

    private static final List<List<Integer>> res21_0 = Arrays.asList(Arrays.asList());

    //generated a pair of "wrong" results just for testing the wrong result message
    private static final List<List<Integer>> wrong11_10 = Arrays.asList(Arrays.asList(1, 2), Arrays.asList(1, 3),
    Arrays.asList(1, 4), Arrays.asList(2, 3), Arrays.asList(2, 4), Arrays.asList(3, 4));

    private static final List<List<Integer>> wrong3_7 = Arrays.asList(Arrays.asList(1, 2), Arrays.asList(1, 3),
    Arrays.asList(1, 4), Arrays.asList(2, 3), Arrays.asList(2, 4), Arrays.asList(3, 4));

    private static void directedTests() {
        unitTest(9, 2, res9_2);
        unitTest(6, 3, res6_3);
        unitTest(13, 1, res13_1);
        unitTest(5, 5, res5_5);
        unitTest(21, 0, res21_0);
        unitTest(11, 10, wrong11_10);
        unitTest(3, 7, wrong3_7);
    }

    public static void main(String[] args) {
        smallTest();
        directedTests(); // directedTests run
        Random r = new Random();
        int n, k;
        if (args.length == 2) {
            n = Integer.parseInt(args[0]);
            k = Integer.parseInt(args[1]);
        } else {
            n = r.nextInt(10) + 1;
            k = r.nextInt(n + 1);
        }
        List<List<Integer>> res = combinations(n, k);
        System.out.println("n = " + n + ", k = " + k);
        System.out.println(res);
    }
}
