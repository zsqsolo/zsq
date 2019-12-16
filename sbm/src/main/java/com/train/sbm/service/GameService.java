package com.train.sbm.service;

import com.train.sbm.entity.Game;

import java.util.List;

public interface GameService {

    int insert(Game game);

    int delete(Integer id);

    int update(Game game);

    Game getOne(int id);

    List<Game> getList(Game game);


}
