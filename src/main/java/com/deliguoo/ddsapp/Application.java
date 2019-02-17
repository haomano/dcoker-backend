package com.deliguoo.ddsapp;

//import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//import com.deliguoo.ddsapp.client.GreetingWebClient;

@SpringBootApplication
//public class Application implements CommandLineRunner{
public class Application{

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

//		GreetingWebClient gwc = new GreetingWebClient();
//		System.out.println(gwc.getResult());
	}
//	@Override
//	public void run(String... args) throws Exception {
//	}
}