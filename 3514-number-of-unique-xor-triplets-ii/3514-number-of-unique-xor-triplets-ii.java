class Solution {
    public int uniqueXorTriplets(int[] nums) {
        int n = nums.length;

        HashSet<Integer> pairXor = new HashSet<>();

        // All distinct XORs of two numbers
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                pairXor.add(nums[i] ^ nums[j]);
            }
        }

        boolean[] seen = new boolean[2048];

        // XOR every pair value with every number
        for (int x : pairXor) {
            for (int num : nums) {
                seen[x ^ num] = true;
            }
        }

        int ans = 0;
        for (boolean b : seen) {
            if (b) ans++;
        }

        return ans;
    }
}