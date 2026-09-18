import java.util.*;

class Solution {
    public List<String> maxNumOfSubstrings(String s) {

        int n = s.length();

        int[] first = new int[26];
        int[] last = new int[26];

        Arrays.fill(first, n);
        Arrays.fill(last, -1);

        // Find first and last occurrence
        for (int i = 0; i < n; i++) {
            int ch = s.charAt(i) - 'a';

            first[ch] = Math.min(first[ch], i);
            last[ch] = i;
        }

        List<int[]> intervals = new ArrayList<>();

        // Try every first occurrence
        for (int i = 0; i < n; i++) {

            int ch = s.charAt(i) - 'a';

            // Not the first occurrence
            if (first[ch] != i) {
                continue;
            }

            int l = i;
            int r = last[ch];

            boolean valid = true;

            // Expand interval
            for (int j = l; j <= r; j++) {

                int current = s.charAt(j) - 'a';

                // This character occurred before l
                if (first[current] < l) {
                    valid = false;
                    break;
                }

                // Need to include all occurrences
                r = Math.max(r, last[current]);
            }

            if (valid) {
                intervals.add(new int[]{l, r});
            }
        }

        // Sort by ending position
        intervals.sort((a, b) -> Integer.compare(a[1], b[1]));

        List<String> answer = new ArrayList<>();

        int previousEnd = -1;

        // Greedily select non-overlapping intervals
        for (int[] interval : intervals) {

            int l = interval[0];
            int r = interval[1];

            if (l > previousEnd) {

                answer.add(s.substring(l, r + 1));

                previousEnd = r;
            }
        }

        return answer;
    }
}