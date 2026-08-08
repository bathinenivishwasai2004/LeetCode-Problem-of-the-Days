class Solution {
    public int[] validSequence(String word1, String word2) {

        int n = word1.length();
        int m = word2.length();

        /*
         * suf[i] = how many characters of word2 are still
         * unmatched after greedily matching word2 from the
         * end using word1[i...n-1].
         */
        int[] suf = new int[n + 1];

        suf[n] = m;

        int j = m - 1;

        for (int i = n - 1; i >= 0; i--) {

            if (j >= 0 && word1.charAt(i) == word2.charAt(j)) {
                j--;
            }

            suf[i] = j + 1;
        }

        int[] ans = new int[m];

        int j2 = 0;
        boolean changed = false;

        for (int i = 0; i < n && j2 < m; i++) {

            // Case 1: Exact match
            if (word1.charAt(i) == word2.charAt(j2)) {

                ans[j2] = i;
                j2++;
            }

            // Case 2: Use our one allowed mismatch
            else if (!changed && suf[i + 1] <= j2 + 1) {

                ans[j2] = i;
                j2++;
                changed = true;
            }
        }

        if (j2 != m) {
            return new int[0];
        }

        return ans;
    }
}