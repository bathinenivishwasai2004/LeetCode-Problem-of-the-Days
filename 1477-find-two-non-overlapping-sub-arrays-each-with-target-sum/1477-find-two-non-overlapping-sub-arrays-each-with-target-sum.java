class Solution {
    public int minSumOfLengths(int[] arr, int target) {

        int n = arr.length;
        int INF = Integer.MAX_VALUE;

    
        int[] best = new int[n];

        Arrays.fill(best, INF);

        HashMap<Long, Integer> map = new HashMap<>();

        long prefixSum = 0;
        int ans = INF;

        map.put(0L, -1);

        for (int i = 0; i < n; i++) {

            prefixSum += arr[i];

            if (i > 0) {
                best[i] = best[i - 1];
            }

            long required = prefixSum - target;

            if (map.containsKey(required)) {

                int start = map.get(required) + 1;
                int length = i - map.get(required);

                if (start > 0 && best[start - 1] != INF) {
                    ans = Math.min(ans, length + best[start - 1]);
                }

                best[i] = Math.min(best[i], length);
            }

            map.put(prefixSum, i);
        }

        return ans == INF ? -1 : ans;
    }
}