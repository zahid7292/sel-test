package com.jazasoft.mtdb.repository;

import com.jazasoft.mtdb.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by mdzahidraza on 22/07/17.
 */
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    User findOneByUsername(String username);

    User findOneByEmail(String email);

    Page<User> findAllByTenantTenantId(String tenantId, Pageable pageable);

    Page<User> findAllByTenantTenantId(String tenantId, Specification<User> spec, Pageable pageable);
}
