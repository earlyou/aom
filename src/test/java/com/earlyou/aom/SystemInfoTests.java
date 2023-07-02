package com.earlyou.aom;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SystemInfoTests {

	@Test
	void contextLoads() {
		try {
			OperatingSystemMXBean osBean = ManagementFactory.getOperatingSystemMXBean();

			for (int i = 0; i < 100; i++) {
				System.out.println("***********************************************************");
				String arch = osBean.getArch();
				String avpro = Integer.toString(osBean.getAvailableProcessors());
				String os = osBean.getName();
				String loadavg = String.format("%.2f", osBean.getSystemLoadAverage());
				String version = osBean.getVersion();

				System.out.println("architecture : " + arch);
				System.out.println("number of processors : " + avpro);
				System.out.println("OS : " + os);
				System.out.println("getSystemLoadAverage : " + loadavg);
				System.out.println("OS version : " + version);

				Thread.sleep(1000);
			}

		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

}
