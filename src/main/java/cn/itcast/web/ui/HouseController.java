package cn.itcast.web.ui;

import cn.itcast.model.House;
import cn.itcast.model.Users;
import cn.itcast.service.HouseService;
import cn.itcast.utils.FileUploadUtil;
import cn.itcast.utils.PageUtil;
import cn.itcast.utils.SearchHouseCondition;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

/**
 * @Author caoqian
 * @ClassName HouseController
 * @Date 2019/12/27 15:15
 * @Version 1.0
 */
@Controller
@RequestMapping("/page/")
public class HouseController {

    @Autowired
    private HouseService houseService;

    /**
     * 房屋信息发布
     *
     * @param session
     * @param house
     * @param pfile
     * @return
     */
    @RequestMapping("addHouse.do")
    //一个表单对象对应一个参数或者实体
    //一个文件域对象与一个CommonsMultipartFile对象对应
    public String addHouse(HttpSession session, House house,
                           @RequestParam(value = "pfile", required = false) CommonsMultipartFile pfile) throws IOException {
        Users user = (Users) session.getAttribute("user");
        if (user == null) {
            return "redirect:out.jsp";
        }

        //上传
        //获取文件的扩展名
        String fname = pfile.getOriginalFilename();
        System.out.println(fname);
        String fexpName = fname.substring(fname.lastIndexOf("."));
        //生成新的文件名
        String unique = System.currentTimeMillis() + "";
        String saveFileName = unique + fexpName;
        String savePath = "D:\\images\\" + saveFileName;
        File savefile = new File(savePath);
        pfile.transferTo(savefile); //上传

        //2.将数据添加到数据库
        //修改house实体，添加额外的属性值
        //设置编号
        house.setId(unique);
        //设置所属用户   重点理解
        house.setUserId(user.getId());
        //设置图片路径
        house.setPath(saveFileName);
        //添加未删除的参数
        house.setIsdel(0);
        //添加未审核的参数
        house.setIspass(0);

        //调用业务实现添加
        houseService.addHouse(house);
        return "redirect:showHouse.do";
    }

    /**
     * 分页查询房屋信息
     *
     * @return
     */
    @RequestMapping("showHouse.do")
    public String findByPage(HttpSession session, PageUtil pageUtil, Model model) {
        Users user = (Users) session.getAttribute("user");
        System.out.println(user);
        if (user == null) {
            return "redirect:out.jsp";
        }
        System.out.println(user.getId());
        if (pageUtil.getPage() == null) {
            pageUtil.setPage(1);
        }
        pageUtil.setRows(3);
        PageInfo<House> pageInfo = houseService.getAllHouseByPage(user.getId(), pageUtil);
        model.addAttribute("pageInfo", pageInfo);
        return "forward:guanli.jsp";
    }


    /**
     * 根据id查询房屋信息进行回显
     *
     * @return
     */
    @RequestMapping("findById.do")
    public String findById(String id, Model model) {
        //获取房屋id
        House house = houseService.findById(id);
        model.addAttribute("house", house);
        return "forward:updateHouse.jsp";
    }

    /**
     * 修改房屋信息
     *
     * @param house      更新之后的房屋信息
     * @param oldPicPath 修改之前的文件图片存放路径
     * @param pfile      文件上传对象
     * @return
     */
    @RequestMapping("updateHouseById.do")
    public String updateById(House house, String id, String oldPicPath, @RequestParam(value = "pfile", required = false) CommonsMultipartFile pfile) {
        //判断是否有文件上传
        try {
            if (!pfile.getOriginalFilename().equals("")) {
                //文件上传
                String upload = FileUploadUtil.upload(pfile);
                //修改文件服务器中存放的地址
                house.setPath(upload);
                //删除之前的旧图
                FileUploadUtil.deleteFile(oldPicPath);
            }
            //调用service进行信息修改
            houseService.updateHouse(house);
            return "redirect:showHouse.do";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:error.jsp";
    }


    /**
     * 根据id删除房屋信息,同时设置是否删除的参数
     *
     * @param id    房屋id
     * @param isDel 是否删除的逻辑参数
     * @return
     */
    @RequestMapping("DelHouseById.do")
    public String DelHouseById(String id, String isDel) {
        houseService.updateIsPass(id, isDel);
        return "redirect:showHouse.do";
    }


    /**
     * 分页展示出租房信息 （已完成审核+未删除）
     *
     * @param searchHouseCondition
     * @param pageUtil
     * @param model
     * @return
     */
    @RequestMapping("getAllHouseByPage.do")
    public String getAllHouseByPage(SearchHouseCondition searchHouseCondition, PageUtil pageUtil, Model model) {
        //设置页码参数
        if (pageUtil.getPage() == null) {
            pageUtil.setPage(1);
        }
        pageUtil.setRows(3);

        PageInfo<House> pageInfo = houseService.getUiAllHouseByPage(searchHouseCondition, pageUtil);
        model.addAttribute("pageInfo", pageInfo);
        //查询条件回显
        model.addAttribute("searchHouseCondition", searchHouseCondition);
        return "forward:list.jsp";
    }

    /**
     * 根据房屋id查询房屋详细信息
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("findUserHouseById.do")
    public String findUserHouseById(String id,Model model){
        House house=houseService.findUserHouseById(id);
        model.addAttribute("house",house);
        return "forward:details.jsp";
    }
}
