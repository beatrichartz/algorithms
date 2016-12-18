package beatrichartz.algorithms.analysis.examples;

import java.util.*;
import java.util.stream.Collectors;

public class QuadraticThreeSum {
    public static Set<Set<Integer>> solve(Set<Integer> inputSet) {
        List<Integer> input = inputSet.stream().collect(Collectors.toList());
        input.sort((a, b) -> Integer.compare(a, b));
        Set<Set<Integer>> summingToThree = new HashSet<>();
        if (input.size() < 3) return summingToThree;

        for (int i = 0; i < input.size(); i++) {
            Integer a = input.get(i);
            int start = i+1;
            int end = input.size() - 1;
            while (start < end) {
                Integer b = input.get(start);
                Integer c = input.get(end);

                if (a + b + c == 0) {
                    summingToThree.add(setOf(a, b, c));
                    end--;
                } else if (a + b + c > 0) {
                    end--;
                } else {
                    start++;
                }
            }


        }
        return summingToThree;
    }

    private static Set<Integer> setOf(Integer a, Integer b, Integer c) {
        Set<Integer> set = new HashSet<>();
        set.add(a);
        set.add(b);
        set.add(c);

        return set;
    }
}
