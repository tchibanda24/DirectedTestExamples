// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <iostream>
#include <random>
#include <string>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::random_device;
using std::stoi;
using std::uniform_int_distribution;
using std::vector;

void DirectedCombinations(int, int, int, vector<int>*, vector<vector<int>>*);

// @include
vector<vector<int>> Combinations(int n, int k) {
    vector<vector<int>> result;
    vector<int> partial_combination;
    DirectedCombinations(n, k, 1, &partial_combination, &result);
    return result;
}

void DirectedCombinations(int n, int k, int offset,
    vector<int>* partial_combination,
    vector<vector<int>>* result) {
        if (partial_combination->size() == k) {
            result->emplace_back(*partial_combination);
            return;
        }

        // Generate remaining combinations over {offset, ..., n - 1} of size
        // num_remaining.
        const int num_remaining = k - partial_combination->size();
        for (int i = offset; i <= n && num_remaining <= n - i + 1; ++i) {
            partial_combination->emplace_back(i);
            DirectedCombinations(n, k, i + 1, partial_combination, result);
            partial_combination->pop_back();
        }
    }
    // @exclude

    void SmallTest() {
        auto result = Combinations(4, 2);
        vector<vector<int>> golden_result = {{1, 2}, {1, 3}, {1, 4},
        {2, 3}, {2, 4}, {3, 4}};
        assert(result.size() == golden_result.size() &&
        equal(result.begin(), result.end(), golden_result.begin()));
    }

    //Test a users solution against an expected result
    // kill the program on any failed result
    void unitTest(int n, int k, vector<vector<int>> expectedComb){
        vector<vector<int>> got = Combinations(n, k);
        if(!equal(got.begin(), got.end(), expectedComb.begin())){
            cout << "Wrong result for n = " << n << " and k = " << k << endl;
            cout << "Expected: ";
            for (const vector<int>& vec : expectedComb) {
                for (int i : vec) { cout << i << " "; }
                cout << endl;
            }
            cout << "Got: ";
            for (const vector<int>& vec : got) {
                for (int i : vec) { cout << i << " "; }
                cout << endl;
            }
            exit(-1);
        }
    }

    //generated some constant results to check against
    const vector<vector<int>> res9_2 = {{1, 2}, {1, 3}, {1, 4}, {1, 5}, {1, 6}, {1, 7}, {1, 8}, {1, 9},
    {2, 3}, {2, 4}, {2, 5}, {2, 6}, {2, 7}, {2, 8}, {2, 9}, {3, 4}, {3, 5}, {3, 6}, {3, 7}, {3, 8},
    {3, 9}, {4, 5}, {4, 6}, {4, 7}, {4, 8}, {4, 9}, {5, 6}, {5, 7}, {5, 8}, {5, 9}, {6, 7}, {6, 8},
    {6, 9}, {7, 8}, {7, 9}, {8, 9}};

    const vector<vector<int>> res6_3 = {{1, 2, 3}, {1, 2, 4}, {1, 2, 5}, {1, 2, 6}, {1, 3, 4}, {1, 3, 5},
    {1, 3, 6}, {1, 4, 5}, {1, 4, 6}, {1, 5, 6}, {2, 3, 4}, {2, 3, 5}, {2, 3, 6}, {2, 4, 5}, {2, 4, 6},
    {2, 5, 6}, {3, 4, 5}, {3, 4, 6}, {3, 5, 6}, {4, 5, 6}};

    const vector<vector<int>> res13_1 = {{1}, {2}, {3}, {4}, {5}, {6}, {7}, {8}, {9}, {10}, {11}, {12}, {13}};

    const vector<vector<int>> res5_5 = {{1, 2, 3, 4, 5}};

    const vector<vector<int>> res21_0 = {{}};

    //generated a pair of "wrong" results just for testing the wrong result message
    const vector<vector<int>> wrong11_10 = {{1, 2}, {1, 3},
    {1, 4}, {2, 3}, {2, 4}, {3, 4}};

    const vector<vector<int>> wrong3_7 = {{1, 2}, {1, 3},
    {1, 4}, {2, 3}, {2, 4}, {3, 4}};

    void directedTests() {
        unitTest(9, 2, res9_2);
        unitTest(6, 3, res6_3);
        unitTest(13, 1, res13_1);
        unitTest(5, 5, res5_5);
        unitTest(21, 0, res21_0);
        unitTest(11, 10, wrong11_10);
        unitTest(3, 7, wrong3_7);
    }

    int main(int argc, char** argv) {
        SmallTest();
        directedTests(); // directedTests run
        default_random_engine gen((random_device())());
        int n, k;
        if (argc == 3) {
            n = stoi(argv[1]), k = stoi(argv[2]);
        } else {
            uniform_int_distribution<int> n_dis(1, 10);
            n = n_dis(gen);
            uniform_int_distribution<int> k_dis(0, n);
            k = k_dis(gen);
        }
        auto result = Combinations(n, k);
        cout << "n = " << n << ", k = " << k << endl;
        for (const vector<int>& vec : result) {
            for (int a : vec) {
                cout << a << " ";
            }
            cout << endl;
        }
        return 0;
    }
