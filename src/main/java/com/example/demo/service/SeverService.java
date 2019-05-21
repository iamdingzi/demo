package com.example.demo.service;

import com.example.demo.common.exception.NotFoundException;
import com.example.demo.common.exception.UpdateException;
import com.example.demo.entity.ServerDto;
import com.example.demo.entity.ServerEntity;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Validated
public interface SeverService {

    ServerEntity saveServer(@Valid ServerEntity serverEntity);

    ServerEntity updateFullEntity(@Valid ServerEntity serverEntity) throws UpdateException;

    void delServerById(int id) throws NotFoundException;

    ServerEntity findServerById(int Id) throws NotFoundException;

    ServerEntity updatePartialEntity(@Valid ServerDto serverEntity) throws UpdateException, NotFoundException;
}
