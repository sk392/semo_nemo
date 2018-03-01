package com.semonemo.latte.toyouproject.service;

import com.semonemo.latte.toyouproject.dto.UserDto;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by latte on 2018. 1. 17..
 */

public interface NetworkService {
    /*
          키워드 기반 검색 정보

          keyword   검색어 정보
          offset    검색 결과 시작 위치
          cnt       검색 결과 요청 개수
      */
    @POST("/auth/login")
    Call<UserDto> getUserInfo(
            @Query("id") long id,
            @Query("name") String name,
            @Query("access_token") String accessToken,
            @Query("refresh_token") String refreshToken);


}

