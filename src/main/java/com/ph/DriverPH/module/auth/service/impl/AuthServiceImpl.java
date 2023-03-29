package com.ph.DriverPH.module.auth.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ph.DriverPH.config.es.ElasticSearchIndexes;
import com.ph.DriverPH.exception.ServiceException;
import com.ph.DriverPH.module.auth.entity.AuthUser;
import com.ph.DriverPH.module.auth.repository.IAuthRepository;
import com.ph.DriverPH.module.auth.request.AuthRequest;
import com.ph.DriverPH.module.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.xcontent.XContentType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.Optional;

/**
 * @author Eejay Taa
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final IAuthRepository iAuthRepository;
    private final PasswordEncoder passwordEncoder;
    private final RestHighLevelClient restHighLevelClient;

    @Override
    @SneakyThrows
    public void register(AuthRequest request) {
        //Check if user already exists in the database
        Optional<AuthUser> user = this.getUser(request.getUsername());
        if(user.isPresent()){
            throw new ServiceException("Username already exists.");
        }
        //Encrypt user password
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        log.info("=====AuthUserServiceImpl=====register:{}=====", request);

        AuthUser authUser = AuthUser.of(request.getUsername(), request.getPassword(), request.getEmail());
        authUser.setCreatedDate(LocalDateTime.now());
        iAuthRepository.save(authUser);

        //TODO: Add user to elastic search
        IndexRequest indexRequest = new IndexRequest(ElasticSearchIndexes.AUTH_USER_INDEX);
        indexRequest.id(authUser.getId().toString());

        String json = JSONObject.toJSONString(authUser);

        indexRequest.source(json, XContentType.JSON);

        IndexResponse indexResponse = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);

        log.info("AuthUserServiceImpl _____ register _____ Elastic Search : {}", indexResponse.status());

    }

    @Override
    public Optional<AuthUser> getUser(String username) {
        return iAuthRepository.findAuthUserByUsername(username);
    }
}
