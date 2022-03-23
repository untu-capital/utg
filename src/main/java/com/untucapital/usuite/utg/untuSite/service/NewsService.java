package com.untucapital.usuite.utg.untuSite.service;

import com.untucapital.usuite.utg.untuSite.model.News;
import com.untucapital.usuite.utg.untuSite.repository.NewsRepository;
import com.untucapital.usuite.utg.untuSite.response.GlobalImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class NewsService {
    @Autowired
    private final GlobalImageService globalImageService;
    @Autowired
    private final NewsRepository newsRepository;

    public NewsService(GlobalImageService globalImageService, NewsRepository newsRepository) {
        this.globalImageService = globalImageService;
        this.newsRepository = newsRepository;
    }
    //Get All News
    public List<News> getAllNews(){
        return newsRepository.findAll();
    }
    //Get News by Id
    public List<News> getNews(String id){
        return newsRepository.findNewsById(id);
    }

    //Update News
    @Transactional
    public void updateNews(String id, String title, String details, String imageUpLoad){
        News news = newsRepository.getById(id);
    }
    //Delete News
    public void deleteNews(String id){
        newsRepository.deleteById(id);
    }

    public String saveNews(MultipartFile file, String title, String details, Date date) {
        News news = new News();
        String imageUrl = globalImageService.saveFile(file);

        news.setTitle(title);
        news.setDetails(details);
        news.setDate(date);
        news.setImageUrl(imageUrl);

        newsRepository.save(news);
        return imageUrl;
    }
}
