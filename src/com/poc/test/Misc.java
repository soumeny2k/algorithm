package com.poc.test;

import java.util.*;

public class Misc {
    public static void main(String args[]) {

        String num1 = "123", num2 = "459";
        //System.out.println(phoneNumberToText());
        findStockPrice();
    }

    static void findStockPrice() {
        int[] stock = {2, 1, 2, 0, 1};
        Map<Integer, Integer> stockPriceDay = new TreeMap<>();

        for (int i = 0; i < stock.length; i++) {
            stockPriceDay.put(stock[i], i + 1);
        }

        int k = 0;
        int profit = 0;
        int minPrice = 0;
        int minDay = 0;
        for (Map.Entry<Integer, Integer> entry : stockPriceDay.entrySet()) {
            if (minPrice == 0 && entry.getValue() < stock.length) {
                minPrice = entry.getKey();
                minDay = entry.getValue();
            } else if (minPrice > 0 && minDay < entry.getValue() && minPrice < entry.getKey()) {
                profit = entry.getKey() - minPrice;
            }
            k++;
        }
        System.out.println(profit);
    }

    static void employeeFreeTime(List<List<Interval>> avails) {
        List<int[]> events = new ArrayList();
        List<int[]> res = new ArrayList();

        for (List<Interval> employee : avails)
            for (Interval iv : employee) {
                events.add(new int[]{iv.start, iv.end});
            }

        Collections.sort(events, (a, b) -> a[0] - b[0]);

        int end = events.get(0)[1];
        for (int[] interval : events) {
            if (interval[0] > end) {
                res.add(new int[]{end, interval[0]});
            }

            end = Math.max(end, interval[1]);
        }

        int[][] ans = new int[res.size()][2];
        for (int i = 0; i < ans.length; ++i) {
            ans[i] = res.get(i);
        }
        System.out.println(Arrays.asList(ans));
    }

    static void reorderLogs() {
        String[] logs = {"a1 9 2 3 1", "g1 act car", "zo4 4 7", "ab1 off key dog", "a8 act zoo"};
        Arrays.sort(logs, (log1, log2) -> {
            String[] split1 = log1.split(" ", 2);
            String[] split2 = log2.split(" ", 2);
            boolean isDigit1 = Character.isDigit(split1[1].charAt(0));
            boolean isDigit2 = Character.isDigit(split2[1].charAt(0));
            if (!isDigit1 && !isDigit2) {
                int cmp = split1[1].compareTo(split2[1]);
                if (cmp != 0) return cmp;
                return split1[0].compareTo(split2[0]);
            }
            return isDigit1 ? (isDigit2 ? 0 : 1) : -1;
        });
        System.out.println(Arrays.asList(logs));
    }

    static void backtrack(int[] nums, int idx, List<Integer> subset, List<List<Integer>> subsets) {
        subsets.add(new ArrayList<>(subset));
        for (int i = idx; i < nums.length; i++) {
            subset.add(nums[i]);
            backtrack(nums, i + 1, subset, subsets);
            subset.remove(subset.size() - 1);
        }
    }

    static void randomizeArray() {
        int a[] = {1, 2, 3, 4, 5, 6};

        Random rgen = new Random();
        int temp;
        for(int i = 0; i < a.length; i++) {
            int j = rgen.nextInt(i+1);
            temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }
        System.out.println(Arrays.asList(a));
    }

    static int findMedian() {
        int a[] = {1, 12, 15, 26, 38};
        int b[] = {2, 13, 17, 30, 45};
        int n = a.length;

        int i = 0, j = 0;
        int m1 = -1, m2 = -1;
        int count;

        for (count = 0; count <= n; count++) {

            if (i == n) {
                m1 = m2;
                m2 = b[0];
                break;
            }

            if (j == n) {
                m1 = m2;
                m2 = a[0];
                break;
            }

            if (a[i] < b[j]) {
                m1 = m2;
                m2 = a[i];
                i++;
            } else {
                m1 = m2;
                m2 = b[j];
                j++;
            }
        }
        return (m1 + m2) / 2;
    }

    static int shortestDistance() {
        int grid[][] = {{1,0,2,0,1}, {0,0,0,0,0}, {0,0,1,0,0}};
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return -1;
        }
        int m = grid.length;
        int n = grid[0].length;
        int houses = 0;
        int[][] reach = new int[m][n];
        int[][] dis = new int[m][n];
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 1) {
                    ++houses;
                    bfs(grid, dis, reach, i, j, m, n);
                }
            }
        }
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 0 && reach[i][j] == houses) {
                    min = Math.min(dis[i][j], min);
                }
            }
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }

    private static int[] DX = new int[]{0, 1, 0, -1};
    private static int[] DY = new int[]{1, 0, -1, 0};

    static private void bfs(int[][] grid, int[][] dis, int[][] reach, int r, int c, int m, int n) {
        int steps = 0;
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[m][n];
        q.offer(new int[]{r, c});
        while (!q.isEmpty()) {
            int size = q.size();
            for (int j = 0; j < size; ++j) {
                int[] p = q.poll();
                dis[p[0]][p[1]] += steps;
                ++reach[p[0]][p[1]];
                for (int i = 0; i < 4; ++i) {
                    int x = p[0] + DX[i];
                    int y = p[1] + DY[i];
                    if (inbound(x, y, m, n) && !visited[x][y] && grid[x][y] == 0) {
                        q.offer(new int[]{x, y});
                        visited[x][y] = true;
                    }
                }
            }
            ++steps;
        }
    }

    static private boolean inbound(int x, int y, int m, int n) {
        return 0 <= x && x < m && 0 <= y && y < n;
    }

    static void combinationSum() {
        int candidates[] = {10,1,2,7,6,1,5};
        int target = 8;

        List<List<Integer>> ret = new ArrayList<>();
        int[] cand;
        Arrays.sort(candidates);
        cand = candidates;
        dfs(0, target, 0, new ArrayList<>(), ret, cand);
        System.out.println(ret);
    }

    static void dfs(int cursum, int target, int idx, List<Integer> path, List<List<Integer>> ret, int[] cand){
        if (cursum == target) ret.add(new ArrayList<>(path));
        int cur;
        for (int pos=idx; pos<cand.length; pos++){
            cur = cand[pos];
            if (cursum+cur>target) break;
            if (pos > idx && cur == cand[pos-1]) continue;
            path.add(cur);
            dfs(cursum+cur, target, pos+1, path, ret, cand);
            path.remove(path.size()-1);
        }
    }

    static void findLongestPath() {
        String s = "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext";

        Map<Integer, Integer> lens = new HashMap<>();
        String[] paths = s.split("\n");
        int max = 0;
        for(String path : paths) {

            int i = 0;
            while (i < path.length() && path.charAt(i) == '\t') {
                i++;
            }

            int depth = i;
            int len = path.length() - depth;

            if (lens.containsKey(depth - 1))
            {
                // if there is an entry with (depth - 1), then
                // that one must be its direct parent as long
                // as the input is valid.
                len += 1 + lens.get(depth - 1);
            }
            lens.put(depth, len);
            if (path.contains("."))
            {
                max = Math.max(max, len);
            }
        }
    }

    static class Interval {
        int start;
        int end;

        Interval (int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    static Interval[] intervalIntersection(Interval[] A, Interval[] B) {
        List<Interval> ans = new ArrayList();
        int i = 0, j = 0;

        while (i < A.length && j < B.length) {
            // Let's check if A[i] intersects B[j].
            // lo - the startpoint of the intersection
            // hi - the endpoint of the intersection
            int lo = Math.max(A[i].start, B[j].start);
            int hi = Math.min(A[i].end, B[j].end);
            if (lo <= hi)
                ans.add(new Interval(lo, hi));

            // Remove the interval with the smallest endpoint
            if (A[i].end < B[j].end)
                i++;
            else
                j++;
        }

        return ans.toArray(new Interval[ans.size()]);
    }

    static int rearrange() {
        String haystack = "mississippi", needle = "issip";
        int index = -1;
        for (int i = 0; i < haystack.length(); i++) {
            if (haystack.charAt(i) == needle.charAt(0)) {
                index = i;
                break;
            }
        }

        int j = index;
        for (int i = 1; i < needle.length(); i++) {
            j += 1;
            if (j < haystack.length() && needle.charAt(i) == haystack.charAt(j)) {
                continue;
            }
            index = -1;
            break;
        }

        return index;
    }

    static int sqrt(int x) {
        if (x > Integer.MAX_VALUE) {
            return 0;
        }
        if (x == Integer.MAX_VALUE) {
            x = Integer.MAX_VALUE - 1;
        }

        if (x == 0) return 0;
        int l = 1, r = x, ans = 0;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (mid*mid == x) return mid; // already converged
            if ((long) mid*mid < x)
            {
                l = mid + 1;
                ans = mid;
            }
            else   // If mid*mid is greater than x
                r = mid-1;
        }
        return ans;
    }

    static double myPow(double x, int n) {
        double result = pow(x, n);
        if (n < 0) {
            return 1 / result;
        }
        return result;
    }

    static double pow(double x, int y) {
        if (y == 0)
            return 1;

        double temp = pow(x, y / 2);
        if (y % 2 == 0) {
            return temp * temp;
        } else {
            return x * temp * temp;
        }
    }


    static String multiply(String num1, String num2)
    {
        int len1 = num1.length();
        int len2 = num2.length();
        int result[] = new int[len1 + len2];

        int i_n1 = 0;

        for (int i = len1 - 1; i >= 0; i--) {
            int n1 = num1.charAt(i) - '0';
            int i_n2 = 0;
            int carry = 0;

            for (int j = len2 - 1; j >= 0; j--) {
                int n2 = num2.charAt(j) - '0';

                int sum = (n1 * n2) + result[i_n1 + i_n2] + carry;
                carry = sum / 10;

                result[i_n1 + i_n2] = sum % 10;
                i_n2++;
            }

            if (carry > 0) {
                result[i_n1 + i_n2] += carry;
            }
            i_n1++;
        }

        int i = result.length - 1;
        while (i > 0 && result[i] == 0) {
            i--;
        }

        String s = "";
        while (i >=0) {
            s += result[i--];
        }
        return s;
    }

    static String add(String num1, String num2)
    {
        int len1 = num1.length();
        int len2 = num2.length();
        int result[] = new int[len1 + len2];

        int i_n1 = 0;
        int carry = 0;

        for (int i = len1 - 1; i >= 0; i--) {
            int n1 = num1.charAt(i) - '0';
            int n2 = num2.charAt(i) - '0';

            int sum = (n1 + n2) + result[i_n1] + carry;
            carry = sum / 10;

            result[i_n1] = sum % 10;

            i_n1++;
        }

        if (carry > 0) {
            result[i_n1] += carry;
        }

        int i = result.length - 1;
        while (i > 0 && result[i] == 0) {
            i--;
        }

        String s = "";
        while (i >= 0) {
            s += result[i--];
        }
        return s;
    }

    static boolean isPalindrome(String s) {
        int n = s.length();
        if (n == 0)
            return true;
        return isPalRec(s, 0, n - 1);
    }

    static boolean isPalRec(String s, int start, int end) {

        if (start == end)
            return true;

        if (s.charAt(start) != s.charAt(end))
            return false;

        if (start < end + 1)
            return isPalRec(s, start + 1, end - 1);

        return true;
    }

    static List<String> phoneNumberToText() {
        String digits = "9278";
        int n = digits.length();
        String table[] = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

        List<String> result = new ArrayList<>();
        Queue<String> q = new LinkedList<>();
        q.add("");

        while (!q.isEmpty()) {
            String s = q.remove();

            if (s.length() == n)
                result.add(s);
            else {
                int digit = digits.charAt(s.length());
                String val = table[digit - '0'];
                for (int i = 0; i < val.length(); i++) {
                    q.add(s + val.charAt(i));
                }
            }
        }
        return result;
    }

}
