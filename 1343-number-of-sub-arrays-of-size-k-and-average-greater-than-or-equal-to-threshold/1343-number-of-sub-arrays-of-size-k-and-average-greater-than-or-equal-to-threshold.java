class Solution {
    public int numOfSubarrays(int[] arr, int k, int threshold) {
        int sum = 0;
        for(int i = 0;i < k;i++)
        {
            sum += arr[i];
        }
        double avg = (double) sum / k;
        int count = 0;
        if(avg >= threshold)
        {
            count++;
        }
        for(int i = k;i < arr.length;i++){
            sum -= arr[i - k];
            sum += arr[i];
            avg = (double) sum / k;
            if(avg >= threshold)
            {
                count++;
            }
        }
        return count;
    }
}