package com.ecarsm.preoday.mars.resources;

import com.ecarsm.preoday.mars.entity.LastFetch;
import com.ecarsm.preoday.mars.exception.MyException;
import com.ecarsm.preoday.mars.service.LastFetchService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Ecar. S. M.
 */
@RestController
@RequestMapping("last")
@Getter
public class LastFetchResource {

    @Autowired
    private LastFetchService service;

    @GetMapping
    public ResponseEntity<LastFetch> last() throws MyException {
        return ResponseEntity.ok(this.service.last());
    }
}
