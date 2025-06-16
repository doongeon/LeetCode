class Solution {
    public int solution(int[] numbers, int target) {
        return dfs(0, 0, numbers, target);
    }
    
    private int dfs(int idx, int sum, int[] numbers, int target) {
        int count = 0;
        
        if(idx == numbers.length) {
            if(sum == target)
                return 1;
            else 
                return 0;
        }
        
        count += dfs(idx + 1, sum + numbers[idx], numbers, target);
        count += dfs(idx + 1, sum - numbers[idx], numbers, target);
        
        return count;
    }
}