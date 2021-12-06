package com.elvislee.jsontest.restTemplate;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
public class RestTemplateController {

    @GetMapping("/getForObject")
    public String getForObject() {
        RestTemplate restTemplate = new RestTemplate();

        Student student = restTemplate.getForObject(
                "https://mocki.io/v1/b2914138-c300-4418-97bb-bc50632418c6",
                Student.class
        );

        System.out.println(student.getId());
        System.out.println(student.getName());
        System.out.println(student.getContactPhone());

        return "getForObject success";
    }

    @GetMapping("/getForEntity")
    public String getForEntity() {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Student> responseEntity = restTemplate.getForEntity(
                "https://mocki.io/v1/b2914138-c300-4418-97bb-bc50632418c6",
                Student.class
        );

        System.out.println("Status Code: " + responseEntity.getStatusCode());

        Student student = responseEntity.getBody();
        System.out.println(student.getId());
        System.out.println(student.getName());
        System.out.println(student.getContactPhone());

        return "getForObject success";
    }

    // GET and send query parameters
    @GetMapping("/getForObjectWithParams")
    public String getForObjectWithParams() {
        RestTemplate restTemplate = new RestTemplate();

        Map<String, Object> queryParamMap = new HashMap<>();
        queryParamMap.put("graduate", true);

        Student student = restTemplate.getForObject(
                "https://mocki.io/v1/b2914138-c300-4418-97bb-bc50632418c6",
                Student.class,
                queryParamMap
        );

        return "Get for object with query parameters success";
    }


    // POST and send request body, return object
    @GetMapping("/postForObject")
    public String postForObejct() {
        RestTemplate restTemplate = new RestTemplate();

        Student studentRequestBody = new Student();
        studentRequestBody.setName("Jenny");

        Student result = restTemplate.postForObject(
                "https://mocki.io/v1/b2914138-c300-4418-97bb-bc50632418c6",
                studentRequestBody,
                Student.class
        );

        return "Post for object success";
    }

    // POST and send request body, return entity
    @GetMapping("/postForEntityt")
    public String postForEntity() {
        RestTemplate restTemplate = new RestTemplate();

        Student studentRequestBody = new Student();
        studentRequestBody.setName("Jenny");

        ResponseEntity<Student> result = restTemplate.postForEntity(
                "https://mocki.io/v1/b2914138-c300-4418-97bb-bc50632418c6",
                studentRequestBody,
                Student.class
        );

        return "Post for entity success";
    }

    // Use exchange
    @GetMapping("/exchange")
    public String exchange() {
        RestTemplate restTemplate = new RestTemplate();

        // a. GET request
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.set("header1", "123");

        HttpEntity requestEntity = new HttpEntity(requestHeaders);

        Map<String, Object> queryParamMap = new HashMap<>();
        queryParamMap.put("graduate", true);

        ResponseEntity<Student> getStudentEntity = restTemplate.exchange(
                "https://mocki.io/v1/b2914138-c300-4418-97bb-bc50632418c6",
                HttpMethod.GET,
                requestEntity,
                Student.class,
                queryParamMap
        );

        // b. POST request
        HttpHeaders requestHeaders2 = new HttpHeaders();
        requestHeaders2.set("header2", "345");
        requestHeaders2.setContentType(MediaType.APPLICATION_JSON);

        Student studentRequestBody = new Student();
        studentRequestBody.setName("John");
        HttpEntity requestEntity2 = new HttpEntity(studentRequestBody, requestHeaders2);

        ResponseEntity<Student> postStudentEntity = restTemplate.exchange(
                "https://mocki.io/v1/b2914138-c300-4418-97bb-bc50632418c6",
                HttpMethod.POST,
                requestEntity2,
                Student.class
        );

        return "exchange success";
    }
}
