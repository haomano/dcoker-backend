package com.deliguoo.ddsapp.base.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.stereotype.Component;

@Component
public class MongoHandler {

	@Autowired
	private ReactiveMongoOperations mongoOperations;
	public ReactiveMongoOperations getMongoOperations() {
		return mongoOperations;
	}
}
