package com.ecarsm.preoday.mars.service;

import com.ecarsm.preoday.mars.entity.MarsSensor;
import com.ecarsm.preoday.mars.entity.MarsSol;
import com.ecarsm.preoday.mars.entity.MarsSolDTO;
import com.ecarsm.preoday.mars.entity.MyDate;
import com.ecarsm.preoday.mars.exception.MyException;
import com.ecarsm.preoday.mars.repository.MarsSolRep;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
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

    @Autowired
    private MessageSource messages;

    //<editor-fold defaultstate="collapsed" desc="INTALL CERT [NOT WORKING]">
    public void install() {
        try {
            InputStream certIn = MarsSolService.class.getResourceAsStream("/trust/mars-demo.cer");

            char[] passphrase = "changeit".toCharArray();

            final char sep = File.separatorChar;
            File dir = new File(System.getProperty("java.home") + sep + "lib" + sep + "security");
            File file = new File(dir, "cacerts");
            InputStream localCertIn = new FileInputStream(file);

            KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
            keystore.load(localCertIn, passphrase);

            if (!keystore.containsAlias("mars-demo")) {

                BufferedInputStream bis = new BufferedInputStream(certIn);
                CertificateFactory cf = CertificateFactory.getInstance("X.509");

                while (bis.available() > 0) {
                    Certificate cert = cf.generateCertificate(bis);
                    keystore.setCertificateEntry("mars-demo", cert);
                }

                certIn.close();

                OutputStream out = new FileOutputStream(file);
                keystore.store(out, passphrase);
                out.close();
            }
            localCertIn.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(MarsSolService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (KeyStoreException | NoSuchAlgorithmException | CertificateException | IOException ex) {
            Logger.getLogger(MarsSolService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //</editor-fold>

    @Scheduled(fixedRate = 3600000)
    public List<MarsSol> fetch() {
        try {
            String response = new RestTemplate().getForObject(this.url + this.key + this.params, String.class);

            this.lastFetchService.updateLast();

            JsonNode jsonNode = new ObjectMapper().readTree(response);

            return entities(jsonNode);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(MarsSolService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MarsSolService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private List<MarsSol> entities(JsonNode json) {

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
            this.repository.save(sol);

        }
        return sols;
    }

    public List<MarsSolDTO> all() {
        List<MarsSolDTO> list = new ArrayList<>();
        this.repository.findAll(Sort.by(Sort.Direction.DESC, "sol")).forEach(s -> {
            list.add(new MarsSolDTO(s.getSol(), s.getSensor().getAverage(), s.getDateLast()));
        });
        return list;
    }

    public MarsSol bySol(Integer sol) throws MyException {
        try {
            return this.repository.getOne(sol);
        } catch (Exception ex) {
            Logger.getLogger(MarsSolService.class.getName()).log(Level.SEVERE, null, ex);
            throw new MyException(this.messages.getMessage("msg.sol.not.found", null, LocaleContextHolder.getLocale()));
        }
    }

}
