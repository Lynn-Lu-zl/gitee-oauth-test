package com.xxxx.springboot.Privider;

import com.alibaba.fastjson.JSON;
import com.xxxx.springboot.AccessToken.PrividerToken;
import com.xxxx.springboot.model.User;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AccessPrivider {
    public String getToken(PrividerToken prividerToken) {
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(prividerToken));
        Request request = new Request.Builder()
            .url("https://gitee.com/oauth/token?grant_type=authorization_code&code=" + prividerToken.getCode() + "&client_id=" + prividerToken.getClient_id() + "&redirect_uri=" + prividerToken.getRedirect_uri() + "&client_secret=" + prividerToken.getClient_Secret())
            .post(body)
            .build();
        try (Response response = client.newCall(request).execute()) {

            String string = response.body().string();

            String str1 = string.split(":")[1];
            String str2 = str1.split("\"")[1];

            System.out.println("token:    " + str2);
            //{"access_token":"e196d513f319cc05f7239780f3f7dbd8","token_type":"bearer","expires_in":86400,"refresh_token":"d2c6f6498ef01852b771804877fe8299f8b916ace8a179a83f7c3ea3da0e7fa6","scope":"user_info","created_at":1661228757}
            System.out.println(string);
            return str2;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取用户信息
     * @param Token
     * @return
     */
    public User getUser(String Token) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
            //实际为Gitee API文档，https://gitee.com/api/v5/swagger#/getV5User
            .url("https://gitee.com/api/v5/user?access_token=" + Token)
            .build();

        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            User user = JSON.parseObject(string, User.class);

            return user;
        } catch (IOException e) {

        }
        return null;
    }


}
