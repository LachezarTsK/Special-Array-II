
#include <span>
#include <vector>
using namespace std;

/*
 The code will run faster with ios::sync_with_stdio(0).
 However, this should not be used in production code and interactive problems.
 In this particular problem, it is ok to apply ios::sync_with_stdio(0).

 Many of the top-ranked C++ solutions for time on leetcode apply this trick,
 so, for a fairer assessment of the time percentile of my code
 you could outcomment the lambda expression below for a faster run.
*/

/*
 const static auto speedup = [] {
	ios::sync_with_stdio(0);
	return nullptr;
 }();
*/

class Solution {

public:
    vector<bool> isArraySpecial(const vector<int>& input, const vector<vector<int>>& queries) const {
        vector<int> backwardLengthSpecialSubarrays = createBackwardLengthSpecialSubarrays(input);
        return createSpecialSubarraysPerQuery(backwardLengthSpecialSubarrays, queries);
    }

private:
    vector<int> createBackwardLengthSpecialSubarrays(span<const int> input) const {
        vector<int> backwardLengthSpecialSubarrays(input.size());
        size_t start = 0;

        for (size_t end = 1; end < input.size(); ++end) {
            backwardLengthSpecialSubarrays[end - 1] = end - start;
            if (!isPairWithDifferentParity(input[end], input[end - 1])) {
                start = end;
            }
        }
        backwardLengthSpecialSubarrays[input.size() - 1] = input.size() - start;

        return backwardLengthSpecialSubarrays;
    }

    bool isPairWithDifferentParity(int first, int second) const {
        return (first & 1) != (second & 1);
    }

    vector<bool> createSpecialSubarraysPerQuery(span<const int> backwardLengthSpecialSubarrays, span<const vector<int>> queries) const {
        vector<bool> specialSubarraysPerQuery(queries.size());
        for (size_t i = 0; i < queries.size(); ++i) {
            specialSubarraysPerQuery[i] = isInRangeOfSpecialSubarray(backwardLengthSpecialSubarrays, queries[i][0], queries[i][1]);
        }
        return specialSubarraysPerQuery;
}

bool isInRangeOfSpecialSubarray(span<const int> backwardLengthSpecialSubarrays, int start, int end) const {
        return end - start + 1 <= backwardLengthSpecialSubarrays[end];
    }
};
