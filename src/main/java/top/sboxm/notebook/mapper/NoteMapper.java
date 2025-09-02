package top.sboxm.notebook.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.sboxm.notebook.pojo.po.Notes;

@Mapper
public interface NoteMapper extends BaseMapper<Notes> {
}