package com.deliguoo.ddsapp.base.handlers;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.deliguoo.ddsapp.repository.content.StaticSentenceRepository;
import com.deliguoo.ddsapp.vo.content.Note;
import com.deliguoo.ddsapp.vo.content.StaticSentence;

import reactor.core.publisher.Mono;

@Component
public class StaticAnswerHandler {

	public Mono<ServerResponse> getStaticAnswer(ServerRequest request) {
		Mono<Note> questionMono = request.bodyToMono(Note.class);
		StaticSentence answer = StaticSentenceRepository.staticAnswersList.get(
				questionMono.hashCode() % StaticSentenceRepository.sizeOfList);
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8)
				.syncBody(answer);
	}
}
