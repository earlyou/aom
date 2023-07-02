package com.earlyou.aom;

import java.io.File;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StorageTests {

	@Test
	void contextLoads() {
		File diskPartition = new File("C:");
		
        long totalCapacity = diskPartition.getTotalSpace();

        long usablePatitionSpace = diskPartition.getUsableSpace(); 
        long freePartitionSpace = diskPartition.getFreeSpace(); 

        System.out.println("**** Sizes in Mega Bytes ****\n");
 
        System.out.println("Total C partition size : " + String.format("%.2f", totalCapacity/(1024.0*1024.0)) + " MB");
        System.out.println("Usable Space : " + String.format("%.2f", usablePatitionSpace/(1024.0*1024.0)) + " MB");
        System.out.println("Free Space : " + String.format("%.2f", freePartitionSpace/(1024.0*1024.0)) + " MB");
 
        System.out.println("\n**** Sizes in Giga Bytes ****\n");
 
        System.out.println("Total C partition size : " + String.format("%.2f", totalCapacity/(1024.0*1024.0*1024.0)) + " GB");
        System.out.println("Usable Space : " + String.format("%.2f", usablePatitionSpace/(1024.0*1024.0*1024.0)) + " GB");
        System.out.println("Free Space : " + String.format("%.2f", freePartitionSpace/(1024.0*1024.0*1024.0)) + " GB");
	}

}
