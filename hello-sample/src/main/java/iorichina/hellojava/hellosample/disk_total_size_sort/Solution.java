package iorichina.hellojava.hellosample.disk_total_size_sort;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

///一、题目描述
//磁盘的容量单位常用的有M，G，T这三个等级，它们之间的换算关系为1T = 1024G，1G = 1024M，现在给定n块磁盘的容量，
//
//请对它们按从小到大的顺序进行稳定排序，例如给定5块盘的容量，1T，20M，3G，10G6T，3M12G9M排序后的结果为20M，3G，3M12G9M，1T，10G6T。
//
//注意单位可以重复出现，上述3M12G9M表示的容量即为3M+12G+9M，和12M12G相等。
//
//二、输入描述
//输入第一行包含一个整数n(2 <= n <= 100)，表示磁盘的个数，接下的n行，每行一个字符串(长度大于2，小于30)，表示磁盘的容量，由一个或多个格式为mv的子串组成，其中m表示容量大小，v表示容量单位，例如20M，1T，30G，10G6T，3M12G9M。
//
//磁盘容量m的范围为1到1024的正整数，容量单位v的范围只包含题目中提到的M
public class Solution {
    public String[] getDiskCapacity(String[] disks) {
        //每个字符串转换为xxxM，存储在List中
        List<Object[]> list = new ArrayList<>();
        for (int i = 0; i < disks.length; i++) {
            list.add(convert(disks[i]));
        }
        //对List进行排序
        list.sort((o1, o2) -> ((Integer) o1[1]).compareTo((Integer) o2[1]));
        //转成数组输出
        return (String[]) list.stream()
                .map(o -> o[0].toString())
                .toArray(String[]::new);
    }

    //假设输入的字符串格式正确
    private Object[] convert(String disk) {
        Map<Character, Integer> map = new HashMap<>() {{
            put('m', 1);
            put('M', 1);
            put('g', 1024);
            put('G', 1024);
            put('t', 1024 * 1024);
            put('T', 1024 * 1024);
        }};
        int left = 0, sum = 0;
        for (int i = 0; i < disk.length(); i++) {
            if (map.containsKey(disk.charAt(i))) {
                int digit = Integer.parseInt(disk.substring(left, i));
                sum += digit * map.get(disk.charAt(i));
                left = i + 1;
            }
        }
        return new Object[]{disk, sum};
    }
}
