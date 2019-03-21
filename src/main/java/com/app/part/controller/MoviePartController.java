package com.app.part.controller;

import com.app.common.entity.PageModel;
import com.app.common.exception.MessageException;
import com.app.common.util.BeanUtils;
import com.app.common.util.LoginUtil;
import com.app.common.util.Util;
import com.app.movie.entity.Movie;
import com.app.part.service.MoviePartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.common.controller.BaseController;
import com.app.part.entity.MoviePart;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Scope("prototype")
@RequestMapping("/moviePart")
public class MoviePartController extends BaseController<MoviePart>{

    @Autowired
    private MoviePartService moviePartService;

    /**
     * 跳转到影院浏览页面
     * @return
     */
    @RequestMapping("/partListPanel")
    public ModelAndView partListPanel(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("/part/partListPanel");
        Map<String, String> param = new HashMap<>();
        if(null!=request){
            String jsonFromRequest = super.getJsonFromRequest(request);
            param = Util.jsonToMap(jsonFromRequest);
        }
        param.put("pageSize", "6");// 每页6个
        PageModel<MoviePart> page = moviePartService.findByPage(param);
        modelAndView.addObject("partName", param.get("partName"));
        modelAndView.addObject("page", page);
        return modelAndView;
    }


    /**
     * 跳转影院新增页面
     */
    @RequestMapping("/partAdd")
    public ModelAndView partAdd(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("/part/partAdd");
        return modelAndView;
    }

    /**
     * 添加或更新影院数据
     * @param request
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public ModelAndView uplaod(HttpServletRequest request, @RequestParam("file") MultipartFile file) {
        try {
            MoviePart moviePart = new MoviePart();
            BeanUtils.populate(request, moviePart);//前台传过来的参数映射成实体用于更新或新增至数据库
            // 设置文件名(模块名+时间)
            String fileName = "part" + Util.getCurrentTime() + "." + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1);
            String imageUrl = "/image/part/" + fileName;
            // 设置文件路径
            String destFileName = request.getSession().getServletContext().getRealPath("") + imageUrl;
            // 第一次运行的时候，创建文件路径
            File destFile = new File(destFileName);
            // 上传文件
            moviePartService.uploadFile(file, destFile);
            moviePart.setImage(imageUrl);
            // 添加或更新至数据库
            if(moviePart.getId() != null) {
                moviePartService.update(moviePart);
            } else {
                moviePart.setCreatedBy(LoginUtil.getUserName());
                moviePartService.insert(moviePart);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new MessageException("上传失败," + e.getMessage());
        }
        return this.partListPanel(null);
    }


    /**
     * 删除影院
     * @return
     */
    @RequestMapping("/deleteById")
    public ModelAndView deleteById(HttpServletRequest request) {
        String jsonStr = getJsonFromRequest(request);
        List<MoviePart> moviePartList = Util.jsonArrToList(jsonStr, MoviePart.class);
        moviePartService.deleteAll(moviePartList);
        return this.partListPanel(null);
    }


}
