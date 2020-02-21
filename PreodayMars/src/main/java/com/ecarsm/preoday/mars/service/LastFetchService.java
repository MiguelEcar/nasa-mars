package com.ecarsm.preoday.mars.service;

import com.ecarsm.preoday.mars.entity.LastFetch;
import com.ecarsm.preoday.mars.exception.MyException;
import com.ecarsm.preoday.mars.repository.LastFetchRep;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
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

    @Autowired
    private MessageSource messages;

    public LastFetch last() throws MyException {
        try {
            List<LastFetch> list = this.repository.findAll(Sort.by(Sort.Direction.DESC, "id"));
            return list.get(0);
        } catch (Exception ex) {
            throw new MyException(this.messages.getMessage("msg.not.fetch", null, LocaleContextHolder.getLocale()));
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
