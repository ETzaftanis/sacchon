package dto;

import lombok.Data;

@Data
public class UserDTO {

    private String username;
    private String password;
    private String role;
    private String name;
    private String email;
    private String address;

}
