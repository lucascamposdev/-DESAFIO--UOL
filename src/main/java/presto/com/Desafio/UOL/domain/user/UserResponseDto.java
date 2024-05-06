package presto.com.Desafio.UOL.domain.user;

import lombok.Getter;

@Getter
public class UserResponseDto {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String codename;
    private String usergroup;

    public UserResponseDto(UserEntity user){
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.codename = user.getCodename();
        this.usergroup = user.getUsergroup().getGroup();
    }
}
