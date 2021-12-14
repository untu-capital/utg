package com.untucapital.usuite.utg.controller;

import com.untucapital.usuite.utg.model.Industry;
import com.untucapital.usuite.utg.service.AbstractService;
import com.untucapital.usuite.utg.service.IndustryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "industries")
public class IndustryController extends AbstractController<Industry> {

    private static final Logger log = LoggerFactory.getLogger(IndustryController.class);

    private final IndustryService industryService;

    public IndustryController(IndustryService industryService) {
        this.industryService = industryService;
    }

    @Override
    protected AbstractService<Industry> getService() {
        return industryService;
    }
}
