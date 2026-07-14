class Solution {

    int MOD = 1_000_000_007;
    int[][] gcd = new int[201][201];
    Integer[][][] memo;

    public int subsequencePairCount(int[] nums) {

        int n = nums.length;

        memo = new Integer[n + 1][201][201];

        // Precompute gcd
        for (int i = 0; i <= 200; i++) {
            for (int j = 0; j <= 200; j++) {
                if (i == 0)
                    gcd[i][j] = j;
                else if (j == 0)
                    gcd[i][j] = i;
                else
                    gcd[i][j] = findGcd(i, j);
            }
        }

        return dfs(0, 0, 0, nums);
    }

    private int dfs(int idx, int g1, int g2, int[] nums) {

        if (idx == nums.length) {
            return (g1 != 0 && g1 == g2) ? 1 : 0;
        }

        if (memo[idx][g1][g2] != null)
            return memo[idx][g1][g2];

        long ans = 0;

        // Skip
        ans += dfs(idx + 1, g1, g2, nums);

        // Put in seq1
        ans += dfs(idx + 1, gcd[g1][nums[idx]], g2, nums);

        // Put in seq2
        ans += dfs(idx + 1, g1, gcd[g2][nums[idx]], nums);

        memo[idx][g1][g2] = (int) (ans % MOD);

        return memo[idx][g1][g2];
    }

    private int findGcd(int a, int b) {
        while (b != 0) {
            int t = a % b;
            a = b;
            b = t;
        }
        return a;
    }
}