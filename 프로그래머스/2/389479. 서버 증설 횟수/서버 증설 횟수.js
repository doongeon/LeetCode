function solution(players, m, k) {
    var answer = 0;
    let capacity = m;
    let servers = [];
    players.forEach((p, i) => {
        if(servers.length > 0) {
            while(i === servers[0]) {
                servers.shift();
                capacity -= m;
            }
        }
        
        while(p >= capacity) {
            servers.push(i + k);
            capacity += m;
            answer++;
        }
    })
    return answer;
}