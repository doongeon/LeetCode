function solution(numbers, target) {
    var answer = 0;
    
    let q = [numbers[0], -numbers[0]];
    
    for(let i = 1; i < numbers.length; i++) {
        let newQ = [];
        while(q.length != 0) {
            let cur = q.pop();
            newQ.push(cur + numbers[i]);
            newQ.push(cur - numbers[i]);
        }
        q = newQ;
    }
    
    answer = q.reduce((count, e) => {
        if(e == target) count++;
        return count;
    }, 0);
    return answer;
}