package com.untucapital.usuite.utg.untu_capital.controller;

import com.untucapital.usuite.utg.untu_capital.model.News;
import com.untucapital.usuite.utg.untu_capital.response.GlobalImageResponse;
import com.untucapital.usuite.utg.untu_capital.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping( path = "news")
public class NewsController {
    @Autowired
    private final NewsService newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }
    //Save
    @PostMapping("/save")
    public ResponseEntity<GlobalImageResponse> saveNews(
            @RequestParam(name = "file", required = false) MultipartFile file,
            @RequestParam("title") String title,
            @RequestParam("details") String details,
            @RequestParam("date") Date date
    ){
        String fileName = newsService.saveNews(file, title, details,date);
        GlobalImageResponse globalImageResponse = new GlobalImageResponse(fileName);
        return ResponseEntity.ok().body(globalImageResponse);
    }
    //Get by id
    @GetMapping("/get/{newsId}")
    public List<News> getNews(@PathVariable("newsId") String id){
        return  newsService.getNews(id);
    }
    //Get all
    @GetMapping("/get")
    public List<News> getAllNews(){
        return  newsService.getAllNews();
    }
    //Update
    @PutMapping("/update/{newsId}")
    public void updateNews(
            @PathVariable("newsId")         String id,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String details,
            @RequestParam(required = false) String imageUpLoad
    )
    {
        newsService.updateNews(id, title, details, imageUpLoad );
    }
    //Delete
    @DeleteMapping("/delete/{newsId}")
    public void deleteNews(@PathVariable("newsId") String id){
        newsService.deleteNews(id);
    }
}
