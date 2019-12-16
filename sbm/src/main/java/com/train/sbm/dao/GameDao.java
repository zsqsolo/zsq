package com.train.sbm.dao;

import com.train.sbm.entity.Game;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface GameDao {

    @Insert("insert into game(name,type,role_id,role_name)values(#{name},#{type},#{role_id},#{role_name})")
    int insert(Game game);

    @Delete("delete from game where id=#{id}")
    int delete(Integer id);

    @Update("update game set name=#{name},type=#{name},role_id=#{role_id},role_name=#{role_name} where id=#{id}")
    int update(Game game);

    @Select("select * from game where id=#{id}")
    Game getOne(Integer id);

    @Select("select * from game where 1=1")
    List<Game> getList(Game game);

}
