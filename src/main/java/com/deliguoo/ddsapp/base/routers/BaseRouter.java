package com.deliguoo.ddsapp.base.routers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
//import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
//import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.deliguoo.ddsapp.base.handlers.BaseHandler;
import com.deliguoo.ddsapp.base.handlers.PostHandler;
import com.deliguoo.ddsapp.base.handlers.StaticAnswerHandler;
import com.deliguoo.ddsapp.base.handlers.UserHandler;
import com.deliguoo.ddsapp.common.Constants;

import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class BaseRouter {

	@Bean
	public RouterFunction<ServerResponse> test(BaseHandler baseHandler) {

		return route(GET("/hello").and(accept(MediaType.TEXT_PLAIN)), baseHandler::hello);
	}
	@Bean
	public RouterFunction<ServerResponse> user(UserHandler userHandler) {
		return route(POST("/save").and(accept(MediaType.APPLICATION_JSON_UTF8)), userHandler::saveUser)
				.andRoute(GET("/getAllUsers").and(accept(MediaType.APPLICATION_JSON_UTF8)), userHandler::getAllUsers);
	}
	@Bean
	public RouterFunction<ServerResponse> staticAnswer(StaticAnswerHandler staticAnswerHandler) {
		return route(POST("/checkAnswer").and(accept(MediaType.APPLICATION_JSON_UTF8)), staticAnswerHandler::getStaticAnswer);
	}
	@Bean
	public RouterFunction<ServerResponse> post(PostHandler postHandler) {
		return route(POST(Constants.POST_QUESTION).and(accept(MediaType.APPLICATION_JSON_UTF8)), postHandler::postQuestion)
				.andRoute(GET("/fetchPost").and(accept(MediaType.APPLICATION_JSON_UTF8)), postHandler::fetchPost);
	}
}