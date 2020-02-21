package com.ecarsm.preoday.mars.resources;

import com.ecarsm.preoday.mars.entity.LocalUser;
import com.ecarsm.preoday.mars.exception.MyException;
import com.ecarsm.preoday.mars.repository.LocalUserRep;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Ecar. S. M.
 */
@RestController
@RequestMapping("user")
@Getter
public class LocalUserResource {

    @Autowired
    private LocalUserRep repository;

    @PostMapping(consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ResponseEntity<LocalUser> create(LocalUser user) throws MyException {
        
        user = this.repository.save(user);
        user.setPassword(null);
        return ResponseEntity.ok(user);
    }
}
