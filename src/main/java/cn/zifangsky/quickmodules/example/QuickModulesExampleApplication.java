package cn.zifangsky.quickmodules.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@ServletComponentScan
@EnableAsync
@MapperScan("cn.zifangsky.quickmodules.example.mapper")
public class QuickModulesExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuickModulesExampleApplication.class, args);
	}

}
