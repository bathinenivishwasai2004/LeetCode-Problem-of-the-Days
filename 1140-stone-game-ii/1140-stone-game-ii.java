class Solution {
    private int[][] memo;
    private int[] suffix;
    private int n;

    public int stoneGameII(int[] piles) {
        n = piles.length;

        suffix = new int[n + 1];
        for (int i = n - 1; i >= 0; i--) {
            suffix[i] = suffix[i + 1] + piles[i];
        }

        memo = new int[n][n + 1];

        return dfs(0, 1);
    }

    private int dfs(int i, int m) {
        if (i >= n)
            return 0;

        if (2 * m >= n - i)
            return suffix[i];

        if (memo[i][m] != 0)
            return memo[i][m];

        int best = 0;

        for (int x = 1; x <= 2 * m; x++) {
            best = Math.max(best,
                    suffix[i] - dfs(i + x, Math.max(m, x)));
        }

        memo[i][m] = best;
        return best;
    }
}