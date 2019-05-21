package com.example.demo.controller;

import com.example.demo.common.BeanMappingUtil;
import com.example.demo.common.exception.NotFoundException;
import com.example.demo.common.exception.UpdateException;
import com.example.demo.entity.Result;
import com.example.demo.entity.ServerDto;
import com.example.demo.entity.ServerEntity;
import com.example.demo.service.SeverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/server")
public class ServerController {

    @Autowired
    private SeverService service;

    @PostMapping(value = "/")
    @ResponseStatus(HttpStatus.CREATED)
    public Result createServer(@RequestBody ServerDto dto) {
        ServerEntity serverEntity = BeanMappingUtil.map(dto, ServerEntity.class);
        ServerEntity server = service.saveServer(serverEntity);
        return Result.ok(server);
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result findServerById(@PathVariable int id) throws NotFoundException {
        ServerEntity server = service.findServerById(id);
        return Result.ok(server);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result updateFullOfServer(@PathVariable int id, @RequestBody ServerEntity entity) throws UpdateException {
        entity.setId(id);
        ServerEntity updateServer = service.updateFullEntity(entity);
        return Result.ok(updateServer);
    }


    @PostMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result updatePartialOfServer(@PathVariable int id, @RequestBody ServerDto dto) throws UpdateException, NotFoundException {
        dto.setId(id);
        ServerEntity updateServer = service.updatePartialEntity(dto);
        return Result.ok(updateServer);
    }


    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delServerById(@PathVariable int id) throws NotFoundException {
        service.delServerById(id);
        return Result.ok();
    }
}
