function solution(n, computers) {
    var answer = 0;
    let netId = 0;
    let networks = Array(n).fill(-1);
    
    let stack = [];
    
    for(let i = 0; i < n; i++) {
        if(networks[i] != -1) continue;
        
        stack.push(i);
        networks[i] = ++netId;
        while(stack.length > 0) {
            let cur = stack.pop();
            computers[cur].forEach((linked, c) => {
                if(linked && networks[c] == -1) {
                    stack.push(c);
                    networks[c] = netId
                }
            })
        }
    }
    
    answer = netId
    
    return answer;
}