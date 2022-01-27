package com.untucapital.usuite.utg.controller;

import com.untucapital.usuite.utg.model.Cities;

import com.untucapital.usuite.utg.service.AbstractService;
import com.untucapital.usuite.utg.service.CitiesService;
import com.untucapital.usuite.utg.service.IndustryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "cities")
public class CitiesController extends AbstractController<Cities> {

    private static final Logger log = LoggerFactory.getLogger(CitiesController.class);

    private final CitiesService citiesService;

    public CitiesController(CitiesService citiesService) {
        this.citiesService = citiesService;
    }

    @Override
    protected AbstractService<Cities> getService() {
        return citiesService;
    }
}
