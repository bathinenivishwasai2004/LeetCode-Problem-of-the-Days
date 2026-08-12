class Solution {
    public double findMaxAverage(int[] nums, int k) {
        int sum = 0;
        double maxavg = Integer.MIN_VALUE;
        for(int i = 0;i < k;i++){
            sum += nums[i];
        }
        double avg = (double) sum / k;
        maxavg = avg;
        for(int i = k ; i < nums.length;i++)
        {
            sum += nums[i];
            sum -= nums[i-k];
            avg = (double) sum / k;
            maxavg = Math.max(maxavg,avg);
        }
        return maxavg;
    }
}