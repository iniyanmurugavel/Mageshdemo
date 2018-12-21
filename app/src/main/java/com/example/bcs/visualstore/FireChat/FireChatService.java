package com.example.bcs.visualstore.FireChat;

import retrofit2.http.Body;
import retrofit2.http.POST;
import io.reactivex.Observable;

public interface FireChatService {


    @POST("/users.json")
    Observable<FireChatResult>   admin_login(@Body FireChatModel fireChatModel);
}
