class Solution {
    public List<List<Integer>> permute(int[] nums) {
        return dfs(new ArrayList<>(), new boolean[nums.length], nums, new ArrayList<>());
    }

    private List<List<Integer>> dfs(List<Integer> perm, boolean[] v, int[] nums, List<List<Integer>> ans) {
        if(perm.size() == nums.length) {
            ans.add(new ArrayList<Integer>(perm));
            return ans;
        }

        for(int i = 0; i < nums.length; i++) {
            if(v[i]) continue;

            v[i] = true;
            perm.add(nums[i]);
            dfs(perm, v, nums, ans);
            perm.remove(perm.size() - 1);
            v[i] = false;
        }

        return ans;
    }
}