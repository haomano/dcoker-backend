package com.deliguoo.ddsapp.base.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.deliguoo.ddsapp.common.Constants;
import com.deliguoo.ddsapp.repository.user.UserRepository;
import com.deliguoo.ddsapp.vo.user.User;

import reactor.core.publisher.Mono;

@Component
public class UserHandler {
	private static final Logger logger = LoggerFactory.getLogger(UserHandler.class);
	@Autowired
	private UserRepository repository;
	@Autowired
	private MongoHandler mongo;
	private FindAndModifyOptions optionNew = new FindAndModifyOptions().returnNew(true);
	public Mono<User> find(String objId, String wechatId, String nickname) {
		Mono<User> user = null;
		if (!StringUtils.isEmpty(objId)) {
			user = repository.findById(Mono.just(objId));
		}
		if (!StringUtils.isEmpty(wechatId)) {
			user = repository.findByWechatId(wechatId);
		}
		if (!StringUtils.isEmpty(nickname)) {
			user = repository.findByNickName(nickname).elementAt(0);
		}
		return user;
	}
	public Mono<User> findAndModify(User user) {
		Query query = new Query(Criteria.where(Constants.WECHATID).is(user.getWechatId()));
		Update update = new Update().set(Constants.NICKNAME, user.getNickName());
		return mongo.getMongoOperations().findAndModify(query, update, optionNew, User.class);
	}
	public Mono<ServerResponse> saveUser(ServerRequest request) {
		Mono<User> userMono = request.bodyToMono(User.class);
//		userMono.flatMap(user -> logger.debug(user.toString()););
		// TODO migrate to saveUser() in repository
		return ServerResponse.ok().build(repository.saveAll(userMono).then());
//		return ServerResponse.ok().body(request.bodyToMono(User.class), User.class);
	}
//		repository.save(new User("haomano", "得力过", "Hangzhou"));
//		repository.save(new User("juju", "橘子妹", "Hangzhou"));

	public Mono<ServerResponse> getAllUsers(ServerRequest request) {
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8)
				.body(repository.findAll(), User.class);
	}

}
