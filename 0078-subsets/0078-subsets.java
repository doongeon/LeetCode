class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        dfs(0, nums, new ArrayList<>(), ans);
        return ans;
    }

    private void dfs(int idx, int[] nums, List<Integer> subset, List<List<Integer>> ans) {
        ans.add(new ArrayList<>(subset));

        for (int i = idx; i < nums.length; i++) {
            subset.add(nums[i]);
            dfs(i + 1, nums, subset, ans);
            subset.remove(subset.size() - 1);
        }
    }
}
