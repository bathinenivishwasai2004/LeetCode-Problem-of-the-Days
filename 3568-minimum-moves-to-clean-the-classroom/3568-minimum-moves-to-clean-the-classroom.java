import java.util.*;

class Solution {
    public int minMoves(String[] classroom, int energy) {

        int m = classroom.length;
        int n = classroom[0].length();

        int startR = 0;
        int startC = 0;

        int litterCount = 0;

        int[][] litterId = new int[m][n];

        for (int[] row : litterId) {
            Arrays.fill(row, -1);
        }

        // Find start and assign IDs to litter
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                char ch = classroom[i].charAt(j);

                if (ch == 'S') {
                    startR = i;
                    startC = j;
                }

                if (ch == 'L') {
                    litterId[i][j] = litterCount;
                    litterCount++;
                }
            }
        }

        // No litter to collect
        if (litterCount == 0) {
            return 0;
        }

        int targetMask = (1 << litterCount) - 1;

        // State: row, column, mask, energy
        Queue<int[]> queue = new LinkedList<>();

        queue.offer(new int[] {
            startR,
            startC,
            0,
            energy
        });

        boolean[][][][] visited =
            new boolean[m][n][1 << litterCount][energy + 1];

        visited[startR][startC][0][energy] = true;

        int moves = 0;

        int[] dr = {1, -1, 0, 0};
        int[] dc = {0, 0, 1, -1};

        while (!queue.isEmpty()) {

            int size = queue.size();

            while (size-- > 0) {

                int[] state = queue.poll();

                int r = state[0];
                int c = state[1];
                int mask = state[2];
                int currEnergy = state[3];

                for (int d = 0; d < 4; d++) {

                    int nr = r + dr[d];
                    int nc = c + dc[d];

                    // Outside grid
                    if (nr < 0 || nr >= m ||
                        nc < 0 || nc >= n) {
                        continue;
                    }

                    // Obstacle
                    if (classroom[nr].charAt(nc) == 'X') {
                        continue;
                    }

                    // No energy = cannot move
                    if (currEnergy == 0) {
                        continue;
                    }

                    int newEnergy = currEnergy - 1;
                    int newMask = mask;

                    char nextCell = classroom[nr].charAt(nc);

                    // Collect litter
                    if (nextCell == 'L') {
                        int id = litterId[nr][nc];
                        newMask |= (1 << id);
                    }

                    // Reset energy
                    if (nextCell == 'R') {
                        newEnergy = energy;
                    }

                    // All litter collected
                    if (newMask == targetMask) {
                        return moves + 1;
                    }

                    if (!visited[nr][nc][newMask][newEnergy]) {

                        visited[nr][nc][newMask][newEnergy] = true;

                        queue.offer(new int[] {
                            nr,
                            nc,
                            newMask,
                            newEnergy
                        });
                    }
                }
            }

            moves++;
        }

        return -1;
    }
}