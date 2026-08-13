class Solution {
    public int totalFruit(int[] fruits) {
        int left = 0;
        int maxlength = Integer.MIN_VALUE;
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int i = 0; i <fruits.length;i++)
        {
            map.put(fruits[i],map.getOrDefault(fruits[i],0)+1);
            while(map.size() > 2)
            {
                map.put(fruits[left] , map.get(fruits[left]) -1);
                if(map.get(fruits[left])== 0)
                {
                    map.remove(fruits[left]);
                }
                left++;
            }
            maxlength = Math.max(maxlength,i - left + 1);
        }
        return maxlength;
    }
}