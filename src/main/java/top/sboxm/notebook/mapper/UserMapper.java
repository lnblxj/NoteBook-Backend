package top.sboxm.notebook.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.sboxm.notebook.pojo.po.User;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}