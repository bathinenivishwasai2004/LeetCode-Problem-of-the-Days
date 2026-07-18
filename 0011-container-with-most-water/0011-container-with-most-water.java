class Solution {
    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int max = 0;
        while(left < right)
        {
            int temp = Math.min(height[right],height[left]) * (right - left);
            if(max < temp)
            {
                max = temp;
            }
            else if(height[right] >= height[left])
            {
                left++;
            }
            else
            {
                right--;
            }
        }
        return max;
    }
}