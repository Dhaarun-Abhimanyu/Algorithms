import java.util.Arrays;

class obst {

  static int sum(int[] freq, int i, int j) {

    int s = 0;
    for (int k = i; k <= j; k++)
      s += freq[k];
    return s;
  }

  static int optCost(int[] freq, int i, int j, int[][] memo) {

    // no elements in this subarray
    if (j < i)
      return 0;

    // one element in this subarray
    if (j == i)
      return freq[i];

    if (memo[i][j] != -1)
      return memo[i][j];

    // Get sum of freq[i], freq[i+1], ... freq[j]
    int fsum = sum(freq, i, j);

    // Initialize minimum value
    int min = Integer.MAX_VALUE;

    // One by one consider all elements as root and calc cost
    for (int r = i; r <= j; r++) {
      int cost = optCost(freq, i, r - 1, memo)
          + optCost(freq, r + 1, j, memo);
      if (cost < min)
        min = cost;
    }

    // Return minimum value
    return memo[i][j] = min + fsum;
  }

  static int optimalSearchTree(int[] keys, int[] freq) {

    int n = keys.length;
    int[][] memo = new int[n][n];
    for (int[] row : memo)
      Arrays.fill(row, -1);
    return optCost(freq, 0, n - 1, memo);
  }

  public static void main(String[] args) {

    int[] keys = { 10, 12, 20 };
    int[] freq = { 34, 8, 50 };
    System.out.println(optimalSearchTree(keys, freq));
  }
}
