class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int sum = 0;
        int minlength = Integer.MAX_VALUE;
        int left = 0;
        for(int i = 0; i < nums.length;i++)
        {
            sum += nums[i];
            while(sum >= target)
            {
                minlength = Math.min(minlength,i - left + 1);
                sum -= nums[left];
                left++;
            }
        }
        if (minlength == Integer.MAX_VALUE) {
            return 0;
        }
        return minlength;
    }
}