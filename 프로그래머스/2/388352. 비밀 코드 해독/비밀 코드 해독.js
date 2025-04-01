function solution(n, q, ans) {
    var answer = 0;
    let candidates = [];
    let stack = [];
    
    for(let i = 1; i <= n - 4; i++) {
        stack.push([i])
    }
    
    while(stack.length > 0) {
        let cur = stack.pop();
        if(cur.length === 5) {
            candidates.push(cur);
            continue;
        }
        
        let base = cur[cur.length - 1];
        if(base + 1 > n) continue;
        
        for(let i = base + 1; i <= n; i++) {
            stack.push([...cur, i]);
        }
    }
    
    q.forEach((query, i) => {
        let newCandidates = [];
        candidates.forEach((candidate) => {
            let hit = 0;
            query.forEach(n => {
                if(candidate.findIndex(c => c === n) > -1) hit++;
            })
            if(hit === ans[i]) newCandidates.push(candidate);
        })
        candidates = newCandidates
    })
    
    answer = candidates.length
    
    return answer;
}