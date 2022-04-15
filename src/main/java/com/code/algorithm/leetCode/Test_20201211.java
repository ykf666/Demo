package com.code.algorithm.leetCode;

import java.util.Arrays;
import java.util.TreeMap;

/**
 * Created by yankefei on 2020/12/11.
 */
public class Test_20201211 {

    public static void main(String[] args) {
        System.out.println(numberTargets("byx", "yxbbdyex"));
        int[][] arr = {{3, 1}, {0, 2}, {1, 2}, {2, 3}};
        System.out.println(Arrays.toString(findRoute(arr)));
    }

    /**
     * 给定一个待印刷内容记作字符串 target 和一块活动雕版记作字符串 block
     * 可划掉活动雕版 block 上不需要的字符，保留剩下的字符，剩下字符保持原相对顺序；
     * 请问最后活动雕版印刷一次最多可以显示出多少个target。
     * 示例1：
     * 输入： target="ab", block="dabab"
     * 输出：2
     * 解释：划掉不需要的字符"d",剩余"abab"，可印刷出两个target
     * <p>
     * 示例2：
     * 输入：target="bxcy", block="bxc"
     * 输出："0"
     * 解释：无法通过活动雕版block印刷出target
     * <p>
     * 示例3：
     * 输入：target="bcd", block="bxcbdcd"
     * 输出：1
     * 解释：最多可以印刷出一个target
     * <p>
     * 提示：
     * 1 <= target.length <= 100
     * 1 <= block.length <= 10^5
     * target和block仅包含小写字符
     *
     * @param target
     * @param block
     * @return
     */
    private static int numberTargets(String target, String block) {
        char[] targetArray = target.toCharArray();
        int targetLen = target.length();
        int index = 0;
        int count = 0;
        for (char c : block.toCharArray()) {
            if (targetArray[index] == c) {
                index++;
            }
            if (index == targetLen) {
                count++;
                index = 0;
            }
        }
        return count;
    }

    /**
     * 游乐场的新客礼包中赠送了 N 张游乐项目之间的车票（车票编号为 0 ~ N-1），
     * tickets[i] 以 [出发站,到达站] 格式记录了编号 i 车票的出发站与到达站编号。
     * 请规划出一条路线能够使用所赠所有车票，并按照使用车票的先后顺序返回车票编号列表。
     * 若有多种可行路线，请返回车票编号顺序字典序最小的方案。
     * <p>
     * 注意：
     * 每张车票都必须使用且仅可使用一次；
     * 题目保证所有车票存在可行的路线。
     * <p>
     * 示例1：
     * 输入: tickets = [[3,1],[0,2],[1,2],[2,3]]
     * 输出: [1,3,0,2]
     * 解释: 可行的路线 0->2->3->1->2，使用的车票编号依次为 [1,3,0,2]
     * <p>
     * 示例2：
     * 输入: tickets = [[0,1],[1,2],[2,3],[3,0]]
     * 输出: [0,1,2,3]
     * 解释: 有多种可行的路线，其中路线 0->1->2->3->0 的使用的车票编号顺序字典序最小，依次为 [0,1,2,3]
     * <p>
     * 示例3：
     * 输入: tickets = [[4,6],[4,6],[3,2],[6,3],[2,4],[3,4],[6,1],[1,0]]
     * 输出: [2,4,0,3,5,1,6,7]
     * 解释: 有多种可行的路线，其中路线 3->2->4->6->3->4->6->1->0 的使用车票编号的顺序字典序最小，依次为 [2,4,0,3,5,1,6,7]
     * <p>
     * <p>
     * 提示：
     * 1 <= tickets.length <= 10^4
     * 1 <= tickets[i] <= 10^5
     *
     * @param tickets
     * @return
     */
    private static int[] findRoute(int[][] tickets) {
        int[] result = new int[tickets.length];
        TreeMap treeMap = new TreeMap();
        for (int[] arr: tickets){
            int start = arr[0];
            int end = arr[1];
            treeMap.put(start, end);
        }

        return result;
    }

}
