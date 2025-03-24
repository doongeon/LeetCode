function solution(info, n, m) {
    var answer = 0;
    let rec = new Set();
    rec.add(JSON.stringify([0, 0]));
    info.forEach((i) => {
        newRec = new Set();
        rec.forEach((r) => {
            r = JSON.parse(r);
            if(r[0] + i[0] < n) {
                newRec.add(JSON.stringify([r[0] + i[0], r[1]]));
            }
            if(r[1] + i[1] < m) {
                newRec.add(JSON.stringify([r[0], r[1] + i[1]]));
            }
        })
        rec = newRec;
    })
    
    if(rec.size === 0) answer = -1;
    else {
        answer = Array.from(rec).reduce((min, r) => {
            r = JSON.parse(r);
            if(r[0] < min) min = r[0];
            return min
        }, 120)
    }
    
    return answer;
}