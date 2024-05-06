package presto.com.Desafio.UOL.domain.user;

import lombok.Getter;

@Getter
public enum UserGroups {

    VINGADORES("Vingadores"),
    LIGADAJUSTICA("Liga da Justiça");

    private String group;

    UserGroups(String group) {
        this.group = group;
    }
}
