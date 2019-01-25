package com.jazasoft.mtdb.service;


import com.jazasoft.mtdb.model.AbstractUser;
import com.jazasoft.mtdb.model.Tenant;
import com.jazasoft.mtdb.model.User;
import com.jazasoft.mtdb.repository.TenantRepository;
import com.jazasoft.mtdb.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by mdzahidraza on 22/07/17.
 */
@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS )
@Profile("default")
@Transactional(value = "masterTransactionManager", readOnly = true)
public class UserService implements IUserService {
    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final TenantRepository tenantRepository;

    public UserService(UserRepository userRepository, TenantRepository tenantRepository) {
        this.userRepository = userRepository;
        this.tenantRepository = tenantRepository;
    }

    public User findOne(Long id) {
        return userRepository.findOne(id);
    }

    public Page<User> findAll(Pageable pageable, String tenantId) {
        if (tenantId != null) {
            return userRepository.findAllByTenantTenantId(tenantId, pageable);
        }
        return userRepository.findAll(pageable);
    }

    public Page<User> findAll(Specification<User> spec, Pageable pageable, String tenantId) {
        if (tenantId != null) {
            return userRepository.findAllByTenantTenantId(tenantId, spec, pageable);
        }
        return userRepository.findAll(spec, pageable);
    }

    @Transactional
    public User save(User user) {
        user.setAuthorities(user.getRoles());
        user.setPassword(user.getMobile());
        user.setAccountExpired(false);
        user.setAccountLocked(false);
        user.setCredentialExpired(false);
        user.setEnabled(true);

        if (user.getTntId() != null) {
            Tenant tenant = tenantRepository.findOne(user.getTntId());
            user.setTenant(tenant);
        }
        return userRepository.save(user);
    }


    public long count() {
        return userRepository.count();
    }

    @Override
    public AbstractUser findOneByUsername(String username) {
        return userRepository.findOneByUsername(username);
    }

    @Override
    public AbstractUser findOneByEmail(String email) {
        return userRepository.findOneByEmail(email);
    }
}
