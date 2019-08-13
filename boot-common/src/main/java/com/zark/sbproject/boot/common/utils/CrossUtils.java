package com.zark.sbproject.boot.common.utils;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Lists;

/**
 * 笛卡尔积工具类.
 * @author zark
 */
public class CrossUtils<T> {

    public List<List<T>> cross(List<List<T>> crossArgs) {
        // 计算出笛卡尔积行数
        int rows = crossArgs.size() > 0 ? 1 : 0;
        for (List<T> data : crossArgs) {
            rows *= data.size();
        }
        // 笛卡尔积索引记录
        int[] record = new int[crossArgs.size()];
        List<List<T>> results = new ArrayList<List<T>>();
        // 产生笛卡尔积
        for (int i = 0; i < rows; i++) {
            List<T> row = new ArrayList<T>();
            // 生成笛卡尔积的每组数据
            for (int index = 0; index < record.length; index++) {
                row.add(crossArgs.get(index).get(record[index]));
            }
            results.add(row);
            crossRecord(crossArgs, record, crossArgs.size() - 1);
        }
        return results;
    }

    /**
     * 产生笛卡尔积当前行索引记录.
     *
     * @param sourceArgs 要产生笛卡尔积的源数据
     * @param record     每行笛卡尔积的索引组合
     * @param level      索引组合的当前计算层级
     */
    private void crossRecord(List<List<T>> sourceArgs, int[] record, int level) {
        record[level] = record[level] + 1;
        if (record[level] >= sourceArgs.get(level).size() && level > 0) {
            record[level] = 0;
            crossRecord(sourceArgs, record, level - 1);
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> crossArgs = new ArrayList<List<Integer>>();

        crossArgs.add(Lists.newArrayList(1, 2));
        crossArgs.add(Lists.newArrayList(3));
        crossArgs.add(Lists.newArrayList(4, 5));

        List<List<Integer>> result = new CrossUtils<Integer>().cross(crossArgs);
        System.out.println(result);
    }
}