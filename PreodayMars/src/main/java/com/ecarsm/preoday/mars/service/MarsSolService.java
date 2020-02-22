package com.ecarsm.preoday.mars.service;

import com.ecarsm.preoday.mars.deserialization.MarsSolDeserialization;
import com.ecarsm.preoday.mars.entity.MarsSol;
import com.ecarsm.preoday.mars.entity.MarsSolDTO;
import com.ecarsm.preoday.mars.repository.MarsSolRep;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Ecar. S. M.
 */
@Service
public class MarsSolService {

    @Value("${nasa.api.url}")
    private String url;

    @Value("${nasa.api.key}")
    private String key;

    @Value("${nasa.api.params}")
    private String params;

    @Autowired
    private MarsSolRep repository;

    @Autowired
    private LastFetchService lastFetchService;

    /**
     * Fetch data from Nasa api
     */
    public void fetch() {
        try {
            String response = new RestTemplate().getForObject(this.url + this.key + this.params, String.class);

            this.lastFetchService.updateLast();

            JsonNode jsonNode = new ObjectMapper().readTree(response);

            MarsSolDeserialization deserialization = new MarsSolDeserialization();

            saveSols(deserialization.deserialize(jsonNode));

        } catch (JsonProcessingException ex) {
            Logger.getLogger(MarsSolService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MarsSolService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns the list of Sols saved on database
     *
     * @return
     */
    public List<MarsSolDTO> all() {
        List<MarsSolDTO> list = new ArrayList<>();
        this.repository.findAll(Sort.by(Sort.Direction.DESC, "sol")).forEach(s -> {
            list.add(new MarsSolDTO(s.getSol(), s.getSensor().getAverage(), s.getDateLast()));
        });
        return list;
    }

    /**
     * Returns the Sol details
     *
     * @param id
     * @return Detailed Sol
     */
    public MarsSol bySol(Integer id) {
        return this.repository.getOne(id);
    }

    /**
     * Saves the Mars Sols after deserialization
     * 
     */
    private void saveSols(List<MarsSol> sols) {
        this.repository.saveAll(sols);
    }
}
