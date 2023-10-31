package com.example.JPARestAPI.DTO;

import com.example.JPARestAPI.Entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseUserDTO {
    private Long seq;
    private String username;

    public ResponseUserDTO(User user){
        seq = user.getSeq();
        username = user.getUsername();
    }
}
