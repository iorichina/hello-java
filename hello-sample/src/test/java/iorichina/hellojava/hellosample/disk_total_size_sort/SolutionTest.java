package iorichina.hellojava.hellosample.disk_total_size_sort;

import org.junit.Before;
import org.junit.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.Assert.assertArrayEquals;

/**
 * 单元测试类：SolutionTest
 */
public class SolutionTest {
    private Solution solution;

    @Before
    public void setUp() {
        solution = new Solution();
    }

    @Test
    public void testGetDiskCapacity_standardCase() {
        String[] disks = {"1T", "2G", "3M"};
        String[] expected = {"3M", "2G", "1T"};
        assertArrayEquals(expected, solution.getDiskCapacity(disks));
    }

    @Test
    public void testGetDiskCapacity_mixedUnits() {
        String[] disks = {"1T500G", "2G300M", "4M"};
        String[] expected = {"4M", "2G300M", "1T500G"};
        assertArrayEquals(expected, solution.getDiskCapacity(disks));
    }

    @Test
    public void testGetDiskCapacity_duplicateSizes() {
        String[] disks = {"1G", "1024M", "1T"};
        String[] expected = {"1G", "1024M", "1T"};
        assertArrayEquals(expected, solution.getDiskCapacity(disks));
    }

    @Test
    public void testGetDiskCapacity_emptyInput() {
        String[] disks = {};
        String[] expected = {};
        assertArrayEquals(expected, solution.getDiskCapacity(disks));
    }

    @Test
    public void testGetDiskCapacity_singleEntry() {
        String[] disks = {"500M"};
        String[] expected = {"500M"};
        assertArrayEquals(expected, solution.getDiskCapacity(disks));
    }
}