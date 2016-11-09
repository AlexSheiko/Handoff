package com.example.handoff.signin;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface HandoffService {
    @GET("users")
    Call<List<User>> users();
}
