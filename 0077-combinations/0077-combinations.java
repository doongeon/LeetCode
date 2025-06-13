class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> ans = new ArrayList<>();

        dfs(1, new ArrayList<>(), n, k, ans);

        return ans;
    }

    private void dfs(int node, List<Integer> comb, int n, int k, List<List<Integer>> ans) {
        if(comb.size() == k) {
            ans.add(new ArrayList<Integer>(comb));
            return;
        }

        for(int i = node; i <= n; i++) {
            comb.add(i);
            dfs(i + 1, comb, n, k, ans);
            comb.remove(comb.size() - 1);
        }
    }
}