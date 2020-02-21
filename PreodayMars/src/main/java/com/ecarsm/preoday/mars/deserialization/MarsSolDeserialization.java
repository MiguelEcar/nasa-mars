package com.ecarsm.preoday.mars.deserialization;

import com.ecarsm.preoday.mars.entity.MarsSensor;
import com.ecarsm.preoday.mars.entity.MarsSol;
import com.ecarsm.preoday.mars.entity.MyDate;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ecar. S. M.
 */
public class MarsSolDeserialization {

    /**
     * Json Deserialization
     * It receives the json from Nasa api and returns MarsSol entities list
     */
    public List<MarsSol> deserialize(JsonNode json) {

        List<MarsSol> sols = new ArrayList<>();

        List<String> ids = new ArrayList<>();

        ((ArrayNode) json.get("sol_keys")).elements().forEachRemaining(s -> {
            ids.add(s.asText());
        });

        for (String id : ids) {

            MarsSol sol = new MarsSol();
            sol.setSol(Integer.parseInt(id));

            JsonNode o = json.get(id);

            sol.setDateFirst(new MyDate((String) o.get("First_UTC").asText()));
            sol.setDateLast(new MyDate((String) o.get("Last_UTC").asText()));

            JsonNode at = o.get("AT");

            Double av = (Double) at.get("av").asDouble();
            Double mn = (Double) at.get("mn").asDouble();
            Double mx = (Double) at.get("mx").asDouble();

            sol.setSensor(new MarsSensor(av, mn, mx));
            sols.add(sol);
        }
        return sols;
    }

}
