
using System;

public class Solution
{
    public bool[] IsArraySpecial(int[] input, int[][] queries)
    {
        int[] backwardLengthSpecialSubarrays = CreateBackwardLengthSpecialSubarrays(input);
        return CreateSpecialSubarraysPerQuery(backwardLengthSpecialSubarrays, queries);
    }

    private int[] CreateBackwardLengthSpecialSubarrays(int[] input)
    {
        int[] backwardLengthSpecialSubarrays = new int[input.Length];
        int start = 0;

        for (int end = 1; end < input.Length; ++end)
        {
            backwardLengthSpecialSubarrays[end - 1] = end - start;
            if (!IsPairWithDifferentParity(input[end], input[end - 1]))
            {
                start = end;
            }
        }
        backwardLengthSpecialSubarrays[input.Length - 1] = input.Length - start;

        return backwardLengthSpecialSubarrays;
    }

    private bool IsPairWithDifferentParity(int first, int second)
    {
        return (first & 1) != (second & 1);
    }

    private bool[] CreateSpecialSubarraysPerQuery(int[] backwardLengthSpecialSubarrays, int[][] queries)
    {
        bool[] specialSubarraysPerQuery = new bool[queries.Length];
        for (int i = 0; i < queries.Length; ++i)
        {
            specialSubarraysPerQuery[i] = IsInRangeOfSpecialSubarray(backwardLengthSpecialSubarrays, queries[i][0], queries[i][1]);
        }
        return specialSubarraysPerQuery;
    }

    private bool IsInRangeOfSpecialSubarray(int[] backwardLengthSpecialSubarrays, int start, int end)
    {
        return end - start + 1 <= backwardLengthSpecialSubarrays[end];
    }
}
