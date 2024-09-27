
/**
 * @param {number[]} input
 * @param {number[][]} queries
 * @return {boolean[]}
 */
var isArraySpecial = function (input, queries) {
    const backwardLengthSpecialSubarrays = createBackwardLengthSpecialSubarrays(input);
    return createSpecialSubarraysPerQuery(backwardLengthSpecialSubarrays, queries);
};

/**
 * @param {number} first
 * @param {number} second
 * @return {boolean}
 */
function isPairWithDifferentParity(first, second) {
    return (first & 1) !== (second & 1);
}

/**
 * @param {number[]} input
 * @return {number[]}
 */
function createBackwardLengthSpecialSubarrays(input) {
    const backwardLengthSpecialSubarrays = new Array(input.length);
    let start = 0;

    for (let end = 1; end < input.length; ++end) {
        backwardLengthSpecialSubarrays[end - 1] = end - start;
        if (!isPairWithDifferentParity(input[end], input[end - 1])) {
            start = end;
        }
    }
    backwardLengthSpecialSubarrays[input.length - 1] = input.length - start;

    return backwardLengthSpecialSubarrays;
}

/**
 * @param {number[]} backwardLengthSpecialSubarrays
 * @param {number[][]} queries
 * @return {boolean[]}
 */
function createSpecialSubarraysPerQuery(backwardLengthSpecialSubarrays, queries) {
    const specialSubarraysPerQuery = new Array(queries.length);
    for (let i = 0; i < queries.length; ++i) {
        specialSubarraysPerQuery[i] = isInRangeOfSpecialSubarray(backwardLengthSpecialSubarrays, queries[i][0], queries[i][1]);
    }
    return specialSubarraysPerQuery;
}

/**
 * @param {number[]} backwardLengthSpecialSubarrays
 * @param {number} start
 * @param {number} end
 * @return {boolean}
 */
function  isInRangeOfSpecialSubarray(backwardLengthSpecialSubarrays, start, end) {
    return end - start + 1 <= backwardLengthSpecialSubarrays[end];
}
