package study.security.simpletwitter.services.roles;

import org.springframework.stereotype.Service;
import study.security.simpletwitter.entities.Role;
import study.security.simpletwitter.repositories.RolesRepository;

@Service
public class RoleServiceImpl implements RoleService {
    private final RolesRepository rolesRepository;

    public RoleServiceImpl(RolesRepository rolesRepository) {
        this.rolesRepository = rolesRepository;
    }

    @Override
    public Role findRoleByName(String roleName) {
        return rolesRepository.findByName(roleName);
    }
}
