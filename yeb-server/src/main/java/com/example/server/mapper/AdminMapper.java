package com.example.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.server.pojo.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hanbing
 * @since 2022-07-27
 */
@Mapper
public interface AdminMapper extends BaseMapper<Admin> {
    Admin getAdminByUserName(String username);

    List<Admin> getAllAdmin(@Param("id") Integer id, @Param("keywords") String keywords);
}
