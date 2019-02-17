package com.deliguoo.ddsapp.repository.content;

//import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
//import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.deliguoo.ddsapp.vo.content.Post;
import com.deliguoo.ddsapp.vo.user.User;

import reactor.core.publisher.Mono;

//public interface PostRepository extends MongoRepository<Post, String>,
//QuerydslPredicateExecutor<Post>
public interface PostRepository extends ReactiveMongoRepository<Post, String>
{

    public Mono<Post> findByQ(String q);
    public Mono<Post> findByQAndU(String q, Mono<User> u);
}