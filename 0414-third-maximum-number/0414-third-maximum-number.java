class Solution {
    public int thirdMax(int[] nums) {
        Long s = null;
        Long ss = null;
        Long ts = null;
        for (int num : nums) {
            if ((s != null && num == s) ||(ss != null && num == ss) ||(ts != null && num == ts)){
            continue;
            }
            if (s == null || num > s) {
                ts = ss; 
                ss = s; 
                s = (long) num;
            }
            else if (ss == null || num > ss) {
                ts = ss;
                ss = (long) num;

            }
            else if (ts == null || num > ts) {
                ts = (long) num;
            }
        }
        return ts == null ? s.intValue() : ts.intValue();
    }
}