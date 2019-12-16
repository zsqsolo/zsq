package com.train.sbm.service.impl;

import com.train.sbm.dao.GameDao;
import com.train.sbm.entity.Game;
import com.train.sbm.service.GameService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class GameServiceImpl implements GameService {
    @Resource
    private GameDao gameDao;

    @Override
    public int insert(Game game) {
        return gameDao.insert(game);
    }

    @Override
    public int delete(Integer id) {
        return gameDao.delete(id);
    }

    @Override
    public int update(Game game) {
        return gameDao.update(game);
    }

    @Override
    public Game getOne(int id) {
        return gameDao.getOne(id);
    }

    @Override
    public List<Game> getList(Game game) {
        return gameDao.getList(game);
    }
}
