package com.untucapital.usuite.utg.controller;

import com.untucapital.usuite.utg.model.News;
import com.untucapital.usuite.utg.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public void saveNews(@RequestBody News news){
        newsService.saveNews(news);
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
