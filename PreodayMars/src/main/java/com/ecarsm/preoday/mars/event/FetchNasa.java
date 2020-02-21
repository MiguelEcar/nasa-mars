package com.ecarsm.preoday.mars.event;

import com.ecarsm.preoday.mars.service.MarsSolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 *
 * @author Ecar. S. M.
 */
@Component
public class FetchNasa {

    @Autowired
    private MarsSolService service;

    /**
     * This method consult Nasa api per hour starting from deploy time.
     */
    @Scheduled(fixedRate = 3600000)
    public void fetchNasa() {
        this.service.fetch();
    }
}
