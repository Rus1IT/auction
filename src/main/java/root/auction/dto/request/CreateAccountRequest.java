package root.auction.dto.request;

import lombok.Data;

@Data
public class CreateAccountRequest {

    private String username;
    private String email;
    private String password;
}
