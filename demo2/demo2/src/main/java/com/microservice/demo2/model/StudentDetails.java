package com.microservice.demo2.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentDetails {

    private int id;
    private String name;
    private String std;
    private String gender;
    private String email;

}
