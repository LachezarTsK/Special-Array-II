
package main

import "fmt"

func isArraySpecial(input []int, queries [][]int) []bool {
    backwardLengthSpecialSubarrays := createBackwardLengthSpecialSubarrays(input)
    return createSpecialSubarraysPerQuery(backwardLengthSpecialSubarrays, queries)
}

func createBackwardLengthSpecialSubarrays(input []int) []int {
    backwardLengthSpecialSubarrays := make([]int, len(input))
    start := 0

    for end := 1; end < len(input); end++ {
        backwardLengthSpecialSubarrays[end - 1] = end - start
        if !isPairWithDifferentParity(input[end], input[end - 1]) {
            start = end
        }
    }
    backwardLengthSpecialSubarrays[len(input) - 1] = len(input) - start

    return backwardLengthSpecialSubarrays
}

func isPairWithDifferentParity(first int, second int) bool {
    return (first & 1) != (second & 1)
}

func createSpecialSubarraysPerQuery(backwardLengthSpecialSubarrays []int, queries [][]int) []bool {
    specialSubarraysPerQuery := make([]bool, len(queries))
    for i := range queries {
        specialSubarraysPerQuery[i] = isInRangeOfSpecialSubarray(backwardLengthSpecialSubarrays, queries[i][0], queries[i][1])
    }
    return specialSubarraysPerQuery
}

func isInRangeOfSpecialSubarray(backwardLengthSpecialSubarrays []int, start int, end int) bool {
    return end - start + 1 <= backwardLengthSpecialSubarrays[end]
}
