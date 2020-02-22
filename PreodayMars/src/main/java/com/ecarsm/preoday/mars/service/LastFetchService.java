package com.ecarsm.preoday.mars.service;

import com.ecarsm.preoday.mars.entity.LastFetch;
import com.ecarsm.preoday.mars.exception.MyException;
import com.ecarsm.preoday.mars.repository.LastFetchRep;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ecar. S. M.
 */
@Service
public class LastFetchService {

    @Autowired
    private LastFetchRep repository;

    public LastFetch last() throws MyException {
        try {
            List<LastFetch> list = this.repository.findAll(Sort.by(Sort.Direction.DESC, "id"));
            return list.get(0);
        } catch (Exception ex) {
            throw new MyException("msg.not.fetch");
        }
    }

    public void updateLast() {
        List<LastFetch> list = this.repository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        if (list.isEmpty()) {
            this.repository.save(new LastFetch());
        } else {
            LastFetch last = list.get(0);
            last.updateDate();
            this.repository.save(last);
        }
    }
}
