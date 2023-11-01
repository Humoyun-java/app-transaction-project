package humoyun.olimjonov.appclickhumoyun.entity;

import lombok.Getter;

@Getter
public enum Permission {
    CREATE_USER("CREATE_USER"),
    READ_USER("READ_USER"),
    UPDATE_USER("UPDATE_USER"),
    DELETE_USER("DELETE_USER"),

    CREATE_CARD("CREATE_CARD"),
    READ_CARD("READ_CARD"),
    UPDATE_CARD("UPDATE_CARD"),
    DELETE_CARD("DELETE_CARD");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

}
