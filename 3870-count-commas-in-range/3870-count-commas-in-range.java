class Solution {
    public int countCommas(int n) {
        int count = 0;

        for (int i = 1; i <= n; i++) {
            int x = i;

            while (x >= 1000) {
                count++;
                x = x / 1000;
            }
        }

        return count;
    }
}