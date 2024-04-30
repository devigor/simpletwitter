package study.security.simpletwitter.services.roles;

import study.security.simpletwitter.entities.Role.Role;

public interface RoleService {
    Role findRoleByName(String roleName);
}
