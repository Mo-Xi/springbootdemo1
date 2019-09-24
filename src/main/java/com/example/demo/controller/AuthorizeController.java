package com.example.demo.controller;

import com.example.demo.dto.AccessTokenDto;
import com.example.demo.dto.GitHubUser;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;
import com.example.demo.provider.GitHubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class AuthorizeController {

    @Autowired
    private GitHubProvider gitHubProvider;

    @Value("${github.client.id}")
    private String clientId;

    @Value("${github.client.secret}")
    private String clientSecret;

    @Value("${github.redirect.uri}")
    private String redirectUri;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletRequest request,
                           HttpServletResponse response) {
        AccessTokenDto accessTokenDto = new AccessTokenDto();
        accessTokenDto.setCode(code);
        accessTokenDto.setRedirect_uri(redirectUri);
        accessTokenDto.setState(state);
        accessTokenDto.setClient_id(clientId);
        accessTokenDto.setClient_secret(clientSecret);
        //access_token=c6dcaf35fd3b7003b0ae979e332380ee026bce9e&scope=user&token_type=bearer
        String accessToken = gitHubProvider.getAccessToken(accessTokenDto);
        GitHubUser user = gitHubProvider.getUser(accessToken);
//        System.out.println(user);
        if (user != null){
            User user1 = new User();
            user1.setName(user.getName());
            String token = UUID.randomUUID().toString();
            user1.setToken(token);
            user1.setAccountId(String.valueOf(user.getId()));
            user1.setGmtCreate(System.currentTimeMillis());
            user1.setGmtModify(user1.getGmtCreate());
            user1.setAvatarUrl(user.getAvatar_url());
            userMapper.insert(user1);
            //登陆成功，写Cookie和Session
            response.addCookie(new Cookie("token",token));


            return "redirect:index";
        }else {
            return "redirect:index";
        }

    }

}
