package com.deliguoo.ddsapp;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.deliguoo.ddsapp.common.Constants;
import com.deliguoo.ddsapp.vo.content.Note;
import com.deliguoo.ddsapp.vo.content.Post;
import com.deliguoo.ddsapp.vo.content.StaticSentence;
import com.deliguoo.ddsapp.vo.user.User;

import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
//  We create a `@SpringBootTest`, starting an actual server on a `RANDOM_PORT`
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SimpleRouterTest {

	// Spring Boot will create a `WebTestClient` for you,
	// already configure and ready to issue requests against "localhost:RANDOM_PORT"
	@Autowired
	private WebTestClient webTestClient;

	@Test
	public void testSaveUser() {
		User testUser = new User("Deliguo1", "得力过1", "Hangzhou");
		webTestClient.post().uri("/save").contentType(MediaType.APPLICATION_JSON_UTF8)
				.body(Mono.just(testUser), User.class).exchange();
		User testUser1 = new User("Deliguo2", "得力过2", "Yuhang");
		webTestClient.post().uri("/save").contentType(MediaType.APPLICATION_JSON_UTF8)
				.body(Mono.just(testUser1), User.class).exchange();
	}
	@Test
	public void testGetAllUsers() {
		List<User> userList = webTestClient.get().uri("/getAllUsers")
				.accept(MediaType.APPLICATION_JSON_UTF8).exchange().expectStatus().isOk()
						.expectBodyList(User.class).returnResult().getResponseBody();
		assertNotNull(userList);
		userList.forEach(user -> {System.out.println(user.toString());});
	}

	@Test
	public void testCheckAnswer() {
		Note question = new Note("Hangzhou");
		StaticSentence answer = webTestClient.post().uri("/checkAnswer")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.body(Mono.just(question), Note.class).exchange()
				.expectBody(StaticSentence.class).returnResult().getResponseBody();
		System.out.println(answer.getW());
		Note question1 = new Note("Hangzhou");
		StaticSentence answer1 = webTestClient.post().uri("/checkAnswer")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.body(Mono.just(question1), Note.class).exchange()
				.expectBody(StaticSentence.class).returnResult().getResponseBody();
		System.out.println(answer1.getW());
	}
	@Test
	public void testHello() {
		webTestClient
			// Create a GET request to test an endpoint
			.get().uri("/hello")
			.accept(MediaType.TEXT_PLAIN)
			.exchange()
			// and use the dedicated DSL to test assertions against the response
			.expectStatus().isOk()
			.expectBody(String.class).isEqualTo("Hello, Spring!");
	}

	@Test
	public void testPost() {
		Note post = new Note();
		post.setD(new Date());
		post.setU("Deliguo1");
		post.setQ("This is another question");
		webTestClient.post().uri(Constants.POST_QUESTION)
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.body(Mono.just(post), Note.class).exchange();
		Post ps = webTestClient.get().uri("/fetchPost?q=This is another question&u=Deliguo1")
				.accept(MediaType.APPLICATION_JSON_UTF8).exchange()
				.expectBody(Post.class).returnResult().getResponseBody();
		System.out.println(ps.getQ());
		System.out.println(ps.getU());
		System.out.println(ps.getD());
//		User u2 = new User();
//		u2.setWechatId("Deliguo2");
//		ps.setU(u2);
//		ps.setQ("This is a udpated question");
//		webTestClient.post().uri(Constants.POST_QUESTION)
//		.contentType(MediaType.APPLICATION_JSON_UTF8)
//		.body(Mono.just(ps), Post.class).exchange();
//		Post ps2 = webTestClient.get().uri("/fetchPost?q=This is a udpated question")
//				.accept(MediaType.APPLICATION_JSON_UTF8).exchange()
//				.expectBody(Post.class).returnResult().getResponseBody();
//		System.out.println(ps2.getQ());
//		System.out.println(ps2.getU());
//		System.out.println(ps2.getD());
	}
		
}