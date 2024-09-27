
public class Solution {

    public boolean[] isArraySpecial(int[] input, int[][] queries) {
        int[] backwardLengthSpecialSubarrays = createBackwardLengthSpecialSubarrays(input);
        return createSpecialSubarraysPerQuery(backwardLengthSpecialSubarrays, queries);
    }

    private int[] createBackwardLengthSpecialSubarrays(int[] input) {
        int[] backwardLengthSpecialSubarrays = new int[input.length];
        int start = 0;

        for (int end = 1; end < input.length; ++end) {
            backwardLengthSpecialSubarrays[end - 1] = end - start;
            if (!isPairWithDifferentParity(input[end], input[end - 1])) {
                start = end;
            }
        }
        backwardLengthSpecialSubarrays[input.length - 1] = input.length - start;

        return backwardLengthSpecialSubarrays;
    }

    private boolean isPairWithDifferentParity(int first, int second) {
        return (first & 1) != (second & 1);
    }

    private boolean[] createSpecialSubarraysPerQuery(int[] backwardLengthSpecialSubarrays, int[][] queries) {
        boolean[] specialSubarraysPerQuery = new boolean[queries.length];
        for (int i = 0; i < queries.length; ++i) {
            specialSubarraysPerQuery[i] = isInRangeOfSpecialSubarray(backwardLengthSpecialSubarrays, queries[i][0], queries[i][1]);
        }
        return specialSubarraysPerQuery;
    }

    private boolean isInRangeOfSpecialSubarray(int[] backwardLengthSpecialSubarrays, int start, int end) {
        return end - start + 1 <= backwardLengthSpecialSubarrays[end];
    }
}
