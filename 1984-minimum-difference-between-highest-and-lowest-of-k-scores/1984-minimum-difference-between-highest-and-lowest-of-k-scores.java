class Solution {
    public int minimumDifference(int[] nums, int k) {
        int diff = 0;
        int mindiff = Integer.MAX_VALUE;
        int left = 0;
        Arrays.sort(nums);
        for(int i=k-1;i< nums.length;i++)
        {
            diff = nums[i] - nums[left];
            mindiff = Math.min(mindiff,diff);
            left++;
        }
        return mindiff;
    }
}