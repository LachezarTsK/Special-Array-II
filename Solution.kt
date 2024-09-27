
class Solution {

    fun isArraySpecial(input: IntArray, queries: Array<IntArray>): BooleanArray {
        val backwardLengthSpecialSubarrays = createBackwardLengthSpecialSubarrays(input)
        return createSpecialSubarraysPerQuery(backwardLengthSpecialSubarrays, queries)
    }

    private fun createBackwardLengthSpecialSubarrays(input: IntArray): IntArray {
        val backwardLengthSpecialSubarrays = IntArray(input.size)
        var start = 0

        for (end in 1..<input.size) {
            backwardLengthSpecialSubarrays[end - 1] = end - start
            if (!isPairWithDifferentParity(input[end], input[end - 1])) {
                start = end
            }
        }
        backwardLengthSpecialSubarrays[input.size - 1] = input.size - start

        return backwardLengthSpecialSubarrays
    }

    private fun isPairWithDifferentParity(first: Int, second: Int): Boolean {
        return (first and 1) != (second and 1)
    }

    private fun createSpecialSubarraysPerQuery(backwardLengthSpecialSubarrays: IntArray, queries: Array<IntArray>): BooleanArray {
        val specialSubarraysPerQuery = BooleanArray(queries.size)
        for (i in queries.indices) {
            specialSubarraysPerQuery[i] = isInRangeOfSpecialSubarray(backwardLengthSpecialSubarrays, queries[i][0], queries[i][1])
        }
        return specialSubarraysPerQuery
    }

    private fun isInRangeOfSpecialSubarray(backwardLengthSpecialSubarrays: IntArray, start: Int, end: Int): Boolean {
        return end - start + 1 <= backwardLengthSpecialSubarrays[end]
    }
}
