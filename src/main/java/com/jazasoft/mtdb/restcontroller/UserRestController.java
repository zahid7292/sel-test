package com.jazasoft.mtdb.restcontroller;

import com.jazasoft.mtdb.IApiUrls;
import com.jazasoft.mtdb.IConfigKeys;
import com.jazasoft.mtdb.model.User;
import com.jazasoft.mtdb.service.UserService;
import com.jazasoft.mtdb.specification.CustomRsqlVisitor;
import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Created by mdzahidraza on 26/06/17.
 */
@RestController
@RequestMapping(IApiUrls.ROOT_URL_USERS)
public class UserRestController {
    private final Logger logger = LoggerFactory.getLogger(UserRestController.class);

    @Autowired
    UserService userService;  //Service which will do all data retrieval/manipulation work

    @GetMapping
    public ResponseEntity<?> findAll(@RequestParam(value = "search",defaultValue = "") String search, Pageable pageable, HttpServletRequest request) {
        String tenant = (String) request.getAttribute(IConfigKeys.REQ_ATTRIBUTE_KEY_TENANT);
//        if (tenant != null) {
//            search = "tenant.tenantId=="+tenant;
//        }
        Page<User> page;
        if (search.trim().isEmpty()) {
            page = userService.findAll(pageable, tenant);
        } else  {
            Node rootNode = new RSQLParser().parse(search);
            Specification<User> spec = rootNode.accept(new CustomRsqlVisitor<User>());
            page = userService.findAll(spec, pageable, tenant);
        }
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @GetMapping(IApiUrls.URL_USERS_USER)
    public ResponseEntity<?> findOne(@PathVariable("userId") long id) {
        logger.debug("getUser(): id = {}",id);
        User user = userService.findOne(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping
    @SuppressWarnings("unchecked")
    public ResponseEntity<?> create(@Valid @RequestBody User user, HttpServletRequest request) {
//        Assert.notNull(request.getAttribute(IConfigKeys.REQ_ATTRIBUTE_KEY_AUTHORITIES), "No authority found.");
//        List<String> roleList =  (List<String>) request.getAttribute(IConfigKeys.REQ_ATTRIBUTE_KEY_AUTHORITIES);
//        Assert.isTrue(roleList.size() == 1, "Should have only one authority of master");
//        String role = roleList.get(0).substring(5);
//        if (!role.equalsIgnoreCase("master")) {
//            return new ResponseEntity<>("Only Master has authority to create new User" ,HttpStatus.FORBIDDEN);
//        }

        logger.debug("createUser()");
        user = userService.save(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
//
//    @PatchMapping(IApiUrls.URL_USERS_USER)
//    public ResponseEntity<?> update(@PathVariable("userId") long id,@Validated @RequestBody User user) {
//        logger.debug("updateUser(): id = {}",id);
//        if (!userService.exists(id)) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        user.setId(id);
//        user = userService.update(user);
//        return new ResponseEntity<>(user, HttpStatus.OK);
//    }
//
//    @DeleteMapping(IApiUrls.URL_USERS_USER)
//    public ResponseEntity<Void> delete(@PathVariable("userId") long id) {
//        logger.debug("deleteUser(): id = {}",id);
//        if (!userService.exists(id)) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        userService.delete(id);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }

}
