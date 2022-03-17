package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.model.News;
import com.untucapital.usuite.utg.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class NewsService {

    @Autowired
    private final NewsRepository newsRepository;

    public NewsService(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }
    //Save News
    public void saveNews(News news){
        newsRepository.save(news);
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
        if(title !=null && title.length()>0){
            news.setTitle(title);
        }
        if ( imageUpLoad !=null && imageUpLoad.length()>0){
            news.setImageUpLoad(imageUpLoad);
        }
        if( details !=null && details.length()>0){
            news.setDetails(details);
        }
    }
    //Delete News
    public void deleteNews(String id){
        newsRepository.deleteById(id);
    }

}
