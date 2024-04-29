package study.security.simpletwitter.services.roles;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import study.security.simpletwitter.entities.Role;
import study.security.simpletwitter.repositories.RolesRepository;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RolesRepository rolesRepository;

    @Override
    public Role findRoleByName(String roleName) {
        return rolesRepository.findByName(roleName);
    }
}
