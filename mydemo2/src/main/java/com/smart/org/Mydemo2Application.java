package com.smart.org;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan // 开启通用注解扫描
@ServletComponentScan // 扫描使用注解方式的servlet
//@MapperScan("com.smart.org.dao")
public class Mydemo2Application extends SpringBootServletInitializer {

	private static Logger logger = Logger.getLogger(Mydemo2Application.class);

	/* 这个方法在打包在war包运行时是必需的 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Mydemo2Application.class);
	}


	public static void main(String[] args) {
		logger.debug("============ Application Starting ===========");
		SpringApplication.run(Mydemo2Application.class, args);
	}
}
