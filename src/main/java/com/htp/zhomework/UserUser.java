package com.htp.zhomework;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class UserUser {
    private Long id;
    private String name;
    private String surname;
    private List<UserUser> friends;

}

