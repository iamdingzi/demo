package com.example.demo.service.impl;

import com.example.demo.common.BeanMappingUtil;
import com.example.demo.common.exception.NotFoundException;
import com.example.demo.common.exception.UpdateException;
import com.example.demo.entity.ServerDto;
import com.example.demo.entity.ServerEntity;
import com.example.demo.mapper.ServerMapper;
import com.example.demo.service.SeverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServerServiceImpl implements SeverService {


    @Autowired
    private ServerMapper serverMapper;

    @Override
    public ServerEntity saveServer(ServerEntity serverEntity) {
        serverMapper.insert(serverEntity);
        return serverEntity;
    }

    @Override
    public ServerEntity updateFullEntity(ServerEntity serverEntity) throws UpdateException {
        int count = serverMapper.updateById(serverEntity);
        if (count == 0) {
            throw new UpdateException("the server:"+serverEntity+" Update failed");
        }
        return serverEntity;
    }

    @Override
    public ServerEntity updatePartialEntity(ServerDto dto) throws UpdateException, NotFoundException {
        ServerEntity serverEntity = BeanMappingUtil.map(dto, ServerEntity.class);
        int count = serverMapper.updateById(serverEntity);
        if (count == 0) {
            throw new UpdateException("the server:"+serverEntity+" Update failed");
        }
        return findServerById(dto.getId());
    }

    @Override
    public void delServerById(int id) throws NotFoundException {
        if (serverMapper.deleteById(id) == 0) {
            throw new NotFoundException("the entity with Id "+id+" doesn't exist");
        }

    }

    @Override
    public ServerEntity findServerById(int id) throws NotFoundException {
        ServerEntity serverEntity = serverMapper.selectById(id);
        if (serverEntity == null) {
            throw new NotFoundException("the entity with Id "+id+" doesn't exist");
        }
        return serverEntity;
    }


}
