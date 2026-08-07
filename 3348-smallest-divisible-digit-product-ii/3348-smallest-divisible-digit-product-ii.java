import java.util.Arrays;

class Solution {
    private static final int[] PRIMES = {2, 3, 5, 7};

    public String smallestNumber(String num, long t) {
        // 1. Check if t contains any prime factors other than 2, 3, 5, or 7
        long temp = t;
        for (int p : PRIMES) {
            while (temp % p == 0) {
                temp /= p;
            }
        }
        if (temp > 1) {
            return "-1";
        }

        int n = num.length();
        int[] targetCnt = getFactors(t);

        // Track factor counts up to each prefix index
        int[][] prefixCnt = new int[n + 1][10];
        boolean hasZero = false;
        int firstZero = -1;

        for (int i = 0; i < n; i++) {
            char ch = num.charAt(i);
            System.arraycopy(prefixCnt[i], 0, prefixCnt[i + 1], 0, 10);
            
            if (ch == '0') {
                if (!hasZero) {
                    hasZero = true;
                    firstZero = i;
                }
            } else {
                int d = ch - '0';
                int[] f = getFactors(d);
                for (int p : PRIMES) {
                    prefixCnt[i + 1][p] += f[p];
                }
            }
        }

        // If the number is already zero-free, check if it matches the product rule
        if (!hasZero) {
            boolean allSatisfied = true;
            for (int p : PRIMES) {
                if (prefixCnt[n][p] < targetCnt[p]) {
                    allSatisfied = false;
                    break;
                }
            }
            if (allSatisfied) {
                return num;
            }
        }

        // Search from right to left for the longest valid prefix match
        int limit = hasZero ? firstZero : n - 1;

        for (int i = limit; i >= 0; i--) {
            int currD = num.charAt(i) - '0';
            
            // Try to increment the digit at position i
            for (int d = currD + 1; d <= 9; d++) {
                int[] req = new int[10];
                int[] fD = getFactors(d);
                
                for (int p : PRIMES) {
                    req[p] = Math.max(0, targetCnt[p] - prefixCnt[i][p] - fD[p]);
                }

                String minSuf = getMinSuffix(req);
                int remLen = n - 1 - i;

                if (minSuf.length() <= remLen) {
                    // Valid suffix found. Construct the result string.
                    StringBuilder sb = new StringBuilder();
                    sb.append(num, 0, i);
                    sb.append(d);
                    
                    int padLen = remLen - minSuf.length();
                    for (int k = 0; k < padLen; k++) {
                        sb.append('1');
                    }
                    sb.append(minSuf);
                    return sb.toString();
                }
            }
        }

        // 2. If no number of the same length works, we must increase the length
        String minSuf = getMinSuffix(targetCnt);
        int newLen = Math.max(n + 1, minSuf.length());
        
        StringBuilder sb = new StringBuilder();
        int padLen = newLen - minSuf.length();
        for (int k = 0; k < padLen; k++) {
            sb.append('1');
        }
        sb.append(minSuf);
        return sb.toString();
    }

    // Helper to calculate needed counts of prime factors 2, 3, 5, 7
    private int[] getFactors(long v) {
        int[] cnt = new int[10];
        if (v <= 0) return cnt;
        for (int p : PRIMES) {
            while (v % p == 0) {
                cnt[p]++;
                v /= p;
            }
        }
        return cnt;
    }

    // Helper to find the lexicographically smallest string of digits needed to satisfy a requirement
    private String getMinSuffix(int[] reqCnt) {
        int c2 = reqCnt[2];
        int c3 = reqCnt[3];
        int c5 = reqCnt[5];
        int c7 = reqCnt[7];
        int c9 = c3 / 2;
        c3 %= 2;

        int c8 = c2 / 3;
        c2 %= 3;

        int c6 = 0;
        if (c3 == 1 && c2 >= 1) {
            c6 = 1;
            c3 = 0;
            c2 -= 1;
        }

        int c4 = c2 / 2;
        c2 %= 2;
        StringBuilder sb = new StringBuilder();
        for (int k = 0; k < c2; k++) sb.append('2');
        for (int k = 0; k < c3; k++) sb.append('3');
        for (int k = 0; k < c4; k++) sb.append('4');
        for (int k = 0; k < c5; k++) sb.append('5');
        for (int k = 0; k < c6; k++) sb.append('6');
        for (int k = 0; k < c7; k++) sb.append('7');
        for (int k = 0; k < c8; k++) sb.append('8');
        for (int k = 0; k < c9; k++) sb.append('9');

        return sb.toString();
    }
}
