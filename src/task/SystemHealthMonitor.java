package task;

import java.io.File;
import java.lang.management.ManagementFactory;
import com.sun.management.OperatingSystemMXBean;

public class SystemHealthMonitor {
	// Thresholds
    private static final double CPU_USAGE_THRESHOLD = 0.8; // 80% CPU usage
    private static final double MEMORY_USAGE_THRESHOLD = 0.8; // 80% Memory usage
    private static final double DISK_SPACE_THRESHOLD = 0.9; // 90% Disk usage

    public static void main(String[] args) {
        checkCPUUsage();
        checkMemoryUsage();
        checkDiskSpace();
    }

    // Check CPU Usage
    public static void checkCPUUsage() {
        OperatingSystemMXBean osBean = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);
        double cpuLoad = osBean.getSystemCpuLoad(); // Get CPU load as a fraction (0.0 to 1.0)
        System.out.printf("Current CPU usage: %.2f%%%n", cpuLoad * 100);
        if (cpuLoad > CPU_USAGE_THRESHOLD) {
            System.out.println("Warning: CPU usage exceeds threshold!");
        }
    }

    // Check Memory Usage
    public static void checkMemoryUsage() {
        OperatingSystemMXBean osBean = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);
        long totalMemory = osBean.getTotalPhysicalMemorySize();
        long freeMemory = osBean.getFreePhysicalMemorySize();
        double memoryUsage = (double) (totalMemory - freeMemory) / totalMemory;
        System.out.printf("Current Memory usage: %.2f%%%n", memoryUsage * 100);
        if (memoryUsage > MEMORY_USAGE_THRESHOLD) {
            System.out.println("Warning: Memory usage exceeds threshold!");
        }
    }

    // Check Disk Space
    public static void checkDiskSpace() {
        File root = new File("/");
        long totalSpace = root.getTotalSpace();
        long freeSpace = root.getFreeSpace();
        double diskUsage = (double) (totalSpace - freeSpace) / totalSpace;
        System.out.printf("Current Disk usage: %.2f%%%n", diskUsage * 100);
        if (diskUsage > DISK_SPACE_THRESHOLD) {
            System.out.println("Warning: Disk usage exceeds threshold!");
        }
    }
}
