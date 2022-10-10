package com.untucapital.usuite.utg.service;
import com.untucapital.usuite.utg.model.ClientFileUpload;
import com.untucapital.usuite.utg.model.MusoniClient;
import com.untucapital.usuite.utg.repository.ClientFileUploadRepository;
import com.untucapital.usuite.utg.repository.MusoniRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;


@Service
@Getter
@Setter
@AllArgsConstructor
public class MusoniService {

    @Bean
    public HttpHeaders httpHeaders(){
        HttpHeaders headers = new HttpHeaders();

        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setBasicAuth("kelvinr","Randa@321");
        headers.set("X-Fineract-Platform-TenantId","untucapital");
        headers.set("x-api-key","4WEowWNz169UbYC052Lgsagd8U32t7As2lPYzEZl");
        headers.set("Content-Type", "application/json");

        return headers;
    }

    @Autowired
    MusoniRepository musoniRepository;

    public void save(MusoniClient musoniClient){
        musoniRepository.save(musoniClient);
    }

    public MusoniClient getMusoniClientById(String clientId){
        return musoniRepository.findMusoniClientById(clientId);
    }

    public List<MusoniClient> getMusoniClientsByStatus(String status){
        return musoniRepository.findMusoniClientsByStatus(status);
    }

}
