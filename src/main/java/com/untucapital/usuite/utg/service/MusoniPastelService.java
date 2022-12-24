package com.untucapital.usuite.utg.service;
import com.untucapital.usuite.utg.model.MusoniClient;
import com.untucapital.usuite.utg.model.MusoniPastel;
import com.untucapital.usuite.utg.repository.MusoniPastelRepository;
import com.untucapital.usuite.utg.repository.MusoniRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;


@Service
@Getter
@Setter
@AllArgsConstructor
public class MusoniPastelService {

    private static final Logger log = LoggerFactory.getLogger(RoleService.class);

    @Bean
    public HttpHeaders httpHeader(){
        HttpHeaders headers = new HttpHeaders();

        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setBasicAuth("Admin","");
        headers.set("Content-Type", "application/json");

        return headers;
    }

    @Autowired
    MusoniPastelRepository musoniPastelRepository;

    public void save(MusoniPastel musoniPastel){
        musoniPastelRepository.save(musoniPastel);
    }

    public MusoniPastel getMusoniPastelById(String transactionId){
        return musoniPastelRepository.findByTransactionId(transactionId);
    }

    public List<MusoniPastel> getMusoniPastelBySynced(String synced){
        return musoniPastelRepository.findBySynced(synced);
    }

}
