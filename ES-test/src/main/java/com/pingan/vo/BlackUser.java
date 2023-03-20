package com.pingan.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BlackUser {

    @Id
    private Integer id;
    private String name;
    private String phone;
    private String discription;

    private Integer startNum;
    private Integer pageSize;
}
