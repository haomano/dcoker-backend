package com.deliguoo.ddsapp.base.handlers;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.deliguoo.ddsapp.common.Constants;
import com.deliguoo.ddsapp.repository.content.PostRepository;
import com.deliguoo.ddsapp.repository.user.UserRepository;
import com.deliguoo.ddsapp.vo.content.Note;
import com.deliguoo.ddsapp.vo.content.Post;
import com.deliguoo.ddsapp.vo.user.User;

import reactor.core.publisher.Mono;

@Component
public class PostHandler {
	private static final Logger logger = LoggerFactory.getLogger(UserHandler.class);
	@Autowired
	private PostRepository postRepository;
	@Autowired
	private UserRepository userRepository;
	public Mono<ServerResponse> postQuestion(ServerRequest request) {
		Mono<Post> postMono = request.bodyToMono(Note.class).flatMap(note -> {
			// TODO change to fetch user from cache or session
			return userRepository.findByWechatId(note.getU()).flatMap(user -> {
				Post p = new Post();
				p.setD(note.getD());
				p.setQ(note.getQ());
				User u = new User();
				logger.info(user.toString());
				u.setId(user.getId());
				u.setWechatId(user.getWechatId());
				u.setNickName(user.getNickName());
				u.setLocation(user.getLocation());
				p.setU(u);
				return Mono.just(p);
			});
		});
		return ServerResponse.ok().build(postRepository.saveAll(postMono).then());
	}

	public Mono<ServerResponse> fetchPost(ServerRequest request) {
		Optional<String> question = request.queryParam(Constants.P_QUESTION);
		Optional<String> user = request.queryParam(Constants.P_USER);
		if (user.isPresent()) {
			Mono<User> mUser = userRepository.findByWechatId(user.get());
			return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8)
					.body(postRepository.findByQAndU(question.get(), mUser), Post.class);
		} else {
			return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8)
					.body(postRepository.findByQ(question.get()), Post.class);
		}
	}
}
