package com.jgr.api.clients.models.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class ClientDTO {
    @NotEmpty(message = "Name is required")
    @Size(min = 4, max = 20)
    private String name;

    @NotEmpty(message = "Surname is required")
    private String surnames;

    @NotEmpty(message = "Name is required")
    @Email(message = "Format email is invalid")
    private String email;
}
