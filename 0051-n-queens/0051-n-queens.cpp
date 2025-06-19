class Solution {
public:
    vector<vector<string>> solveNQueens(int n) {
        vector<vector<vector<int>>> possibles;
        vector<bool> vc;
        for(int i = 0; i < n; i++) vc.push_back(false);
        vector<vector<int>> l;
        dfs(n, 0, 0, vc, l, possibles);

        vector<vector<string>> result;

        for(vector<vector<int>> possible : possibles) {
            vector<string> temp;
            for(vector<int> p : possible) {
                string str = "";

                for(int i = 0; i < n; i++) {
                    if(i == p[1]) str += "Q";
                    else str += ".";
                }

                temp.push_back(str);
            }

            result.push_back(temp);
        }

        
        return result;
    }

    void dfs(const int& N, const int count, const int row, vector<bool>& vc, vector<vector<int>>& l, vector<vector<vector<int>>>& result) {
        if(count == N) {
            bool isValid = true;

            for(int i = 0; i < N; i++) {
                if(!isValid) break;
                for(int j = i + 1; j < N; j++) {
                    vector<int> p1 = l[i];
                    vector<int> p2 = l[j];

                    int r1 = p1[0];
                    int r2 = p2[0];
                    int c1 = p1[1];
                    int c2 = p2[1];

                    if(abs(r1 - r2) == abs(c1 - c2)) {
                        isValid = false;
                        break;
                    }
                }
            }

            if(isValid) {
                vector<vector<int>> temp;
                for(vector<int> p : l) temp.push_back(p);
                result.push_back(temp);
            }

            return;
        }

        for(int c = 0; c < N; c++) {
            if(vc[c]) continue;
            vc[c] = true;
            l.push_back({row, c});
            dfs(N, count + 1, row + 1, vc, l, result);
            l.pop_back();
            vc[c] = false;
        }
    }
};