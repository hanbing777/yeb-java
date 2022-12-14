package com.example.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.server.Utils.AdminUtils;
import com.example.server.mapper.MenuMapper;
import com.example.server.pojo.Admin;
import com.example.server.pojo.Menu;
import com.example.server.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hanbing
 * @since 2022-07-27
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 根据用户id查询菜单列表
     *
     * @return
     */
    @Override
    public List<Menu> getMenusByAdminId() {
        Integer adminId = AdminUtils.getCurrentAdmin().getId();
        ValueOperations valueOperations = redisTemplate.opsForValue();
        //从redis中获取数据
        List<Menu> menus = (List<Menu>) valueOperations.get("menu_" + adminId);
        //如果为空,从数据库中获取
        if (CollectionUtils.isEmpty(menus)){
            menus = menuMapper.getMenusByAdminId(adminId);
            valueOperations.set("menu_" + adminId,menus);
        }
        return menus;
    }

    @Override
    /**
     * 根据角色获取菜单列表
     * @return
     */
    public List<Menu> getMenusWithRole() {
        return menuMapper.getMenusWithRole();
    }

    /**
     * 查询所有菜单
     * @return
     */
    @Override
    public List<Menu> getAllMenus() {
        return menuMapper.getAllMenus();
    }
}
