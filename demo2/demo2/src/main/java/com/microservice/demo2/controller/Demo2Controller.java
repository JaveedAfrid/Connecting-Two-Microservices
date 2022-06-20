package com.microservice.demo2.controller;

import com.microservice.demo2.model.StudentDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
// Child End-point
@RequestMapping("demo2")
public class Demo2Controller {

    @Autowired
    private RestTemplate restTemplate;

    final String url = "http://localhost:8080/StudentDetails/";


//    @GetMapping
//    public List<Object> getStudentDetails(){
//        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
//        headers.set("Content-Type", "application/json");
//        HttpEntity request = new HttpEntity(headers);
//        ResponseEntity<Object[]> exchange = restTemplate.exchange("http://localhost:8080/StudentDetails", HttpMethod.GET, request, Object[].class);
//        return Arrays.asList(exchange.getBody());
//    }

    @GetMapping
    public List<Object> getStudentDetails(
            // Providing RequestHeader (Accept-Language)
            @RequestHeader(value = "Accept-Language") String acceptLangHeader,
                        // Providing RequestParam (Name)
                        @RequestParam(value = "name",required = false) String name,
                                    // Providing RequestParam (Email)
                                    @RequestParam(value = "email",required = false) String email){

        List<Object> forObject = null;

        // Checking if the Name and Email is null
        if (name!=null && email!=null){
            // Getting record only of particular Name and Email
            forObject = restTemplate.getForObject(url + "?name=" + name + "&email=" + email, List.class);
        }
        // Checking if the Name is null
        else if (name!=null) {
            // Getting record of particular Name
            forObject = restTemplate.getForObject(url + "?name=" + name, List.class);
        }
        // Checking if the Email is null
        else if (email!=null) {
            // Getting record of particular Email
            forObject = restTemplate.getForObject(url + "?email=" + email, List.class);
        }
        else {
            // If both Name and Email is empty getting all records
            forObject = restTemplate.getForObject(url, List.class);
        }
//      restTemplate.exchange(url, HttpMethod.GET, null, Object[].class);
        // Returning the object
        return forObject;
    }



    @PostMapping
    public ResponseEntity<?> studentRegisterFromDemo2(
            // Providing RequestHeader (Accept)
            @RequestHeader(value = "Accept") String acceptHeader,
                        // Providing RequestHeader (Authorization)
                        @RequestHeader (value = "Authorization") String authorizationHeader,
                                    // Providing RequestBody (StudentDetails)
                                    @RequestBody StudentDetails details) throws Exception {

        // Sending Post Request with data (details)
        ResponseEntity<StudentDetails> entity = restTemplate.postForEntity("http://localhost:8080/StudentDetails",details, StudentDetails.class);
        return new ResponseEntity<>(entity.getBody(), HttpStatus.CREATED);
    }

    @DeleteMapping("/{email}")
    public void StudentDeleteFromDemo2(@PathVariable String email){
        restTemplate.delete(url + email);
    }

}
