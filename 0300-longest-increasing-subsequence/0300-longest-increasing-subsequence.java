class Solution {
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];

        dp[nums.length - 1] = 1;

        for(int i = nums.length - 2; i >= 0; i--) {
            int len = 0;
            for(int j = i + 1; j < nums.length; j++) {
                if(nums[i] >= nums[j]) continue;
                len = Math.max(dp[j], len);
            }
            dp[i] = len + 1;
        }

        int maxLen = dp[nums.length - 1];
        for(int i = 0; i < nums.length; i++) {
            maxLen = Math.max(maxLen, dp[i]);
        }

        return maxLen;
    }
}