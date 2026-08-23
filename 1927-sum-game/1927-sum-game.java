class Solution {
    public boolean sumGame(String num) {
        int n = num.length();
        int half = n / 2;

        int leftSum = 0;
        int rightSum = 0;
        int leftQ = 0;
        int rightQ = 0;

        for (int i = 0; i < half; i++) {
            if (num.charAt(i) == '?') {
                leftQ++;
            } else {
                leftSum += num.charAt(i) - '0';
            }
        }

        for (int i = half; i < n; i++) {
            if (num.charAt(i) == '?') {
                rightQ++;
            } else {
                rightSum += num.charAt(i) - '0';
            }
        }

        int diff = leftSum - rightSum;
        int qDiff = rightQ - leftQ;

        // Odd number of ? difference -> Alice can force a win
        if (qDiff % 2 != 0) {
            return true;
        }

        // Bob can exactly balance the sums
        return diff != (qDiff / 2) * 9;
    }
}