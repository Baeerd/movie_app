package com.app.movie.controller;

import com.app.common.entity.PageModel;
import com.app.common.exception.MessageException;
import com.app.common.util.BeanUtils;
import com.app.common.util.Util;
import com.app.movie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

import com.app.common.controller.BaseController;
import com.app.movie.entity.Movie;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;
import java.util.Map;

@RestController
@Scope("prototype")
@RequestMapping("/movie")
public class MovieController extends BaseController<Movie>{

    @Autowired
    private MovieService movieService;

    /**
     * 添加或更新电影数据
     * @param request
     * @param file
     * @param model
     * @return
     */
    @PostMapping("/upload")
    public String uplaod(HttpServletRequest request, @RequestParam("file") MultipartFile file, Model model) {
        try {
            Movie movie = new Movie();
            BeanUtils.populate(request, movie);//前台传过来的参数映射成实体用于更新或新增至数据库
            // 设置文件名(模块名+时间)
            String fileName = "movie" + Util.getCurrentTime() + "." + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1);;
            String imageUrl = "/image/movie/" + fileName;
            // 设置文件路径
            String destFileName = request.getSession().getServletContext().getRealPath("") + imageUrl;
            // 第一次运行的时候，创建文件路径
            File destFile = new File(destFileName);
            // 上传文件
            movieService.uploadFile(file, destFile);
            movie.setImage(imageUrl);
            // 添加或更新至数据库
            if(movie.getId() != null) {
                movieService.update(movie);
            } else {
                movieService.insert(movie);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new MessageException("上传失败," + e.getMessage());
        }
        return "goodsList";
    }

    /**
     * 跳转到电影浏览页面
     * @return
     */
    @RequestMapping("/movieListPanel")
    public ModelAndView movieListPanel(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("/movie/movieListPanel");
        String jsonFromRequest = super.getJsonFromRequest(request);
        Map<String, String> param = Util.jsonToMap(jsonFromRequest);
        param.put("pageSize", "6");// 每页6个
        PageModel<Movie> page = movieService.findByPage(param);
        modelAndView.addObject("page", page);
        return modelAndView;
    }

}
