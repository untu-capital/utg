package com.untucapital.usuite.utg.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.untucapital.usuite.utg.service.MusoniService;
import com.untucapital.usuite.utg.utils.MusoniUtils;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.security.auth.login.AccountNotFoundException;
import java.text.ParseException;

@Configuration
@EnableScheduling
public class ScheduledTask {

//    private final MusoniService musoniService;
//
//    // Run at 5 AM every day
//    @Scheduled(cron = "0 0 5 * * ?")
//    public void runAtFiveAM() throws ParseException, JsonProcessingException, AccountNotFoundException {
//
//        Long startTime = MusoniUtils.getUnixTimeMinus24Hours();
//        musoniService.getLoansByTimestamp(startTime);
//    }
}
