package ua.varus.testtask.dao;

import ua.varus.testtask.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDao extends JpaRepository<Role, Long> {
}
