package com.example.server.controller;


import com.example.server.pojo.Joblevel;
import com.example.server.pojo.RespBean;
import com.example.server.service.IJoblevelService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hanbing
 * @since 2022-07-27
 */
@RestController
@RequestMapping("/system/basic/joblevel")
public class JoblevelController {
    @Autowired
    public IJoblevelService joblevelService;

    @ApiOperation(value = "获取职位信息")
    @GetMapping("/")
    public List<Joblevel> getAllPostion() {
        return joblevelService.list();
    }

    @ApiOperation(value = "添加职位信息")
    @PostMapping("/addJoblevel")
    public RespBean addJoblevel(@RequestBody Joblevel Joblevel) {
        Joblevel.setCreateDate(LocalDateTime.now());
        if (joblevelService.save(Joblevel)) {
            return RespBean.success("添加成功");
        } else {
            return RespBean.error("添加失败");
        }
    }

    @ApiOperation(value = "修改职位信息")
    @PostMapping("/updateJoblevel")
    public RespBean updateJoblevel(@RequestBody Joblevel Joblevel) {
        if (joblevelService.updateById(Joblevel)) {
            return RespBean.success("修改成功");
        } else {
            return RespBean.error("修改失败");
        }
    }

    @ApiOperation(value = "删除职位信息")
    @DeleteMapping("deleteJoblevelById/{id}")
    public RespBean deleteJoblevelById(@PathVariable Integer id) {
        if (joblevelService.removeById(id)) {
            return RespBean.success("删除成功");
        } else {
            return RespBean.error("删除失败");
        }
    }

    @ApiOperation(value = "批量删除职位信息")
    @DeleteMapping("/deleteJoblevelByIds")
    public RespBean deleteJoblevelByIds(Integer[] ids) {
        if (joblevelService.removeByIds(Arrays.asList(ids))) {
            return RespBean.success("删除成功");
        } else {
            return RespBean.error("删除失败");
        }
    }

}
