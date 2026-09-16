class Solution {
    static final long MOD = 1_000_000_007L;

    public int numberOfSets(int n, int k) {

        long[][] dp = new long[k + 1][n];

        // 0 segments: one way
        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }

        for (int j = 1; j <= k; j++) {

            long prefix = 0;

            for (int i = 1; i < n; i++) {

                // dp[j-1][p] for p < i
                prefix = (prefix + dp[j - 1][i - 1]) % MOD;

                // Don't end a segment at i
                // OR end one at i
                dp[j][i] = (dp[j][i - 1] + prefix) % MOD;
            }
        }

        return (int) dp[k][n - 1];
    }
}