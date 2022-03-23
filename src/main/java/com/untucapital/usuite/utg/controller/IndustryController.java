package com.untucapital.usuite.utg.controller;

import com.untucapital.usuite.utg.model.Industry;
import com.untucapital.usuite.utg.service.AbstractService;
import com.untucapital.usuite.utg.service.IndustryService;
import com.untucapital.usuite.utg.untuSite.response.GlobalImageResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(path = "industries")
public class IndustryController extends AbstractController<Industry> {

    private static final Logger log = LoggerFactory.getLogger(IndustryController.class);

    private final IndustryService industryService;

    public IndustryController(IndustryService industryService) {
        this.industryService = industryService;
    }
    //Add Industry and Related industry image
    @PostMapping("/save")
    ResponseEntity<GlobalImageResponse> addIndustry(@RequestParam(name = "file", required = false) MultipartFile file,
                                                    @RequestParam("name") String name,
                                                    @RequestParam("code") int code
                                                    ){
        String fileName = industryService.addIndustry(file,name,code);
        GlobalImageResponse globalImageResponse = new GlobalImageResponse(fileName);
        return ResponseEntity.ok().body(globalImageResponse);
    }
    @Override
    protected AbstractService<Industry> getService() {
        return industryService;
    }
}
