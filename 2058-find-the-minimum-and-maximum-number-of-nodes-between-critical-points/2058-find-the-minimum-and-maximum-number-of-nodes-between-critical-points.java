class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {

        int minDistance = Integer.MAX_VALUE;
        int maxDistance = -1;

        int first = -1;
        int prevCritical = -1;

        int position = 1;

        ListNode prev = head;
        ListNode curr = head.next;

        while (curr != null && curr.next != null) {

            ListNode next = curr.next;

            // Check if current node is a critical point
            if ((curr.val > prev.val && curr.val > next.val) ||
                (curr.val < prev.val && curr.val < next.val)) {

                // First critical point
                if (first == -1) {
                    first = position;
                }

                // If this is not the first critical point
                if (prevCritical != -1) {
                    minDistance = Math.min(
                        minDistance,
                        position - prevCritical
                    );

                    maxDistance = position - first;
                }

                prevCritical = position;
            }

            prev = curr;
            curr = next;
            position++;
        }

        // Fewer than two critical points
        if (prevCritical == first) {
            return new int[]{-1, -1};
        }

        return new int[]{minDistance, maxDistance};
    }
}