package com.ph.DriverPH.module.user.service.impl;

import com.alibaba.fastjson.JSON;
import com.ph.DriverPH.config.es.ElasticSearchIndexes;
import com.ph.DriverPH.exception.ServiceException;
import com.ph.DriverPH.module.user.entity.User;
import com.ph.DriverPH.module.user.mapstruct.IUserConverter;
import com.ph.DriverPH.module.user.repository.IUserRepository;
import com.ph.DriverPH.module.user.request.UserSearchRequest;
import com.ph.DriverPH.module.user.response.UserDetailResponse;
import com.ph.DriverPH.module.user.service.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {
    private final IUserRepository userRepository;
    private final RestHighLevelClient restHighLevelClient;
    @Override
    public UserDetailResponse findUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        user.orElseThrow(() -> new ServiceException("User not found."));
        return IUserConverter.INSTANCE.convert(user.get());
    }

    @Override
    @SneakyThrows
    public Page<UserDetailResponse> findAllUsers(UserSearchRequest request) {
        //Search data to Elasticsearch
        SearchRequest searchRequest = new SearchRequest(ElasticSearchIndexes.AUTH_USER_INDEX);

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        //Conditions on the specific column using Query Builder
        BoolQueryBuilder bqb = QueryBuilders.boolQuery();
        bqb.must(QueryBuilders.termQuery("username", request.getUsername()));

        searchSourceBuilder.query(bqb);

        searchSourceBuilder.size(request.getSize());
        searchSourceBuilder.from((request.getPage() - 1) * request.getSize());

        //Set query conditions to the Elasticsearch Search Request API
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

        //Get the response from Elasticsearch Search Request API
        SearchHit[] hits = searchResponse.getHits().getHits();

        //Create a response object
        List<UserDetailResponse> response = new ArrayList<>();

        for(SearchHit hit : hits){
            String json = hit.getSourceAsString();
            UserDetailResponse detailResponse = JSON.parseObject(json, UserDetailResponse.class);
            response.add(detailResponse);
        }

        return new PageImpl<>(response, PageRequest.of(request.getPage() - 1, request.getSize()), searchResponse.getHits().getTotalHits().value);
    }


}
