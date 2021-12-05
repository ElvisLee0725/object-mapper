package com.elvislee.jsontest.objectMapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ObjectMapperController {

    @GetMapping("/convert")
    public String convert() throws JsonProcessingException {
        User user = new User();
        user.setId(1);
        user.setName("Elvis Lee");
        user.setContactEmail("test@test.com");

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonResult = objectMapper.writeValueAsString(user);

        System.out.println(jsonResult);

        String temp = "{\"id\":3," +
                "\"name\":\"James Bond\"," +
                "\"contact_email\":\"contact@test.com\"}";

        User userResult = objectMapper.readValue(temp, User.class);
        System.out.println(userResult.getId());
        System.out.println(userResult.getName());
        System.out.println(userResult.getContactEmail());
        return "success";
    }

}
