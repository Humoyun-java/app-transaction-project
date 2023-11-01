package humoyun.olimjonov.appclickhumoyun.entity;

import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
public enum Role {
    ADMIN(Set.of(Permission.values())),
    USER(Set.of(Permission.CREATE_CARD,
            Permission.READ_USER,
            Permission.DELETE_CARD,
            Permission.DELETE_USER,
            Permission.UPDATE_USER,
            Permission.UPDATE_CARD));


    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<SimpleGrantedAuthority> getAuthorities() {
        return getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }
}
