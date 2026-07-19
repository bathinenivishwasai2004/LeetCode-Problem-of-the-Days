class Solution {
    public String smallestSubsequence(String s) {
        int[] freq = new int[26];

        for (char ch : s.toCharArray()) {
            freq[ch - 'a']++;
        }

        Stack<Character> stack = new Stack<>();
        HashSet<Character> set = new HashSet<>();

        for (char ch : s.toCharArray()) {
            freq[ch - 'a']--;

            if (set.contains(ch))
                continue;

            while (!stack.isEmpty()
                    && ch < stack.peek()
                    && freq[stack.peek() - 'a'] > 0) {

                set.remove(stack.pop());
            }

            stack.push(ch);
            set.add(ch);
        }

        StringBuilder ans = new StringBuilder();

        while (!stack.isEmpty()) {
            ans.append(stack.pop());
        }

        return ans.reverse().toString();
    }
}