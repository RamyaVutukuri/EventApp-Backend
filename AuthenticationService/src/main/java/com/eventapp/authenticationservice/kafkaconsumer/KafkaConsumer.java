package com.eventapp.authenticationservice.kafkaconsumer;
import com.eventapp.authenticationservice.model.UserInfo;
import com.eventapp.authenticationservice.service.AuthService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

@Configuration
public class KafkaConsumer {
    @Autowired
    Gson gson;
    @Autowired
    AuthService authService;
    @KafkaListener(topics = "topic",groupId = "group_id")
    public void consume(String user) {
        System.out.println("received message = " + user);
        UserInfo userdata=gson.fromJson(user, UserInfo.class);
        UserInfo result = authService.saveUserDetails(userdata);
        System.out.println("stored data in user copy" + userdata.toString());
    }
}
