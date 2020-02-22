package com.ecarsm.preoday.mars.resources;

import com.ecarsm.preoday.mars.entity.MarsSol;
import com.ecarsm.preoday.mars.entity.MarsSolDTO;
import com.ecarsm.preoday.mars.exception.MyException;
import com.ecarsm.preoday.mars.service.MarsSolService;
import java.util.List;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Ecar. S. M.
 */
@RestController
@RequestMapping("mars")
@Getter
public class MarsSolResource {

    @Autowired
    private MarsSolService service;

    @GetMapping("all")
    public ResponseEntity<List<MarsSolDTO>> all() throws MyException {

        List<MarsSolDTO> resp = this.service.all();

        return resp != null ? ResponseEntity.ok(resp) : ResponseEntity.noContent().build();
    }

    @GetMapping()
    public ResponseEntity<MarsSol> bySol(@RequestParam(name = "sol") Integer sol) throws MyException {
        try {
            MarsSol resp = this.service.bySol(sol);
            return ResponseEntity.ok(resp);
        } catch (Exception ex) {
            throw new MyException("msg.sol.not.found");
        }

    }
}
