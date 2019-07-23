package com.mod.user.entity.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class LoginUserDTO implements Serializable {
    @NotBlank
    private String userName;
    @NotBlank
    private String password;
}
