package cn.thinkjoy.sample;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.thinkjoy.sample.benchmark.Benchmark;
import cn.thinkjoy.sample.benchmark.BenchmarkUtils;
import cn.thinkjoy.sample.service.UserService;

public class Dubbo {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring.xml");
        context.start();
		
		
        final UserService service = (UserService) context.getBean("userService");
        
        StringBuffer sb = new StringBuffer();
		
		for(int i=0; i<1024; i++) {
			sb.append(i);
		}
		
		final String s = sb.toString();
        
		BenchmarkUtils.testAndPrint(100, 2000, new Benchmark() {

			@Override
			public Object doTask() throws Exception {
				
				service.getUser(s);
				
				return null;
			}

		});
	}

}
