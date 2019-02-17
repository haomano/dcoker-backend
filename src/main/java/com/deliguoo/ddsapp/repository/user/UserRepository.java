package com.deliguoo.ddsapp.repository.user;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.deliguoo.ddsapp.vo.user.User;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveMongoRepository<User, String> {

    public Mono<User> findByWechatId(String wechatId);
    public Flux<User> findByNickName(String nickName);
    public Flux<User> findByLocation(String location);
}