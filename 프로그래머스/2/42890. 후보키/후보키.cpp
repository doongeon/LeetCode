#include <string>
#include <vector>
#include <queue>
#include <set>

using namespace std;

int solution(vector<vector<string>> relation) {
    int answer = 0;
    int C = relation[0].size();
    vector<set<int>> candidates;
    queue<set<int>> q;
    for(int c = 0; c < C; c++) {
        set<int> temp;
        temp.insert(c);
        q.push(temp);
    }
    
    set<set<int>> v;
    
    while(q.size() != 0) {
        set<int> cur = q.front();
        q.pop();
        
        bool isMinSet = true;
        
        for(set<int> candidate : candidates) {
            bool isAllIncluded = true;
            
            for(int c : candidate) {
                if(!cur.count(c)) {
                    isAllIncluded = false;
                    break;
                }
            }
            
            if(isAllIncluded) {
                isMinSet = false;
                break;
            }
        }
        
        if(!isMinSet) continue;
        
        bool isUnique = true;
        set<string> keySet;
        for(vector<string> row : relation) {
            string str = "";
            for(int c : cur) {
                str += row[c];
                str += "/";
            }
            
            if(keySet.count(str)) {
                isUnique = false;
                break;
            }
            keySet.insert(str);
        }
        
        if(isUnique) {
            candidates.push_back(cur);
            continue;
        }
        
        for(int c = 0; c < C; c++) {
            if(cur.count(c)) continue;
            
            set<int> nextSet;
            for(int e : cur) nextSet.insert(e);
            nextSet.insert(c);
            if(v.count(nextSet)) continue;
            
            v.insert(nextSet);
            q.push(nextSet);
        }
    }
    
    return candidates.size();
}