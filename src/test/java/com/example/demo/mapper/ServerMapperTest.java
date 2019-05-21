package com.example.demo.mapper;

import com.example.demo.DemoApplicationTests;
import com.example.demo.common.constant.ServerStatus;
import com.example.demo.entity.ServerEntity;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Administrator on 2019/5/20/020.
 */
public class ServerMapperTest extends DemoApplicationTests{

    @Autowired
    private ServerMapper serverMapper;

    @Test
    public void updateById() throws Exception {
        ServerEntity param = new ServerEntity();
        param.setIp("127.0.0.1");
        param.setStatus(ServerStatus.BOOTING);
        param.setId(0);

        int count = serverMapper.updateById(param);
        Assert.assertEquals(1,count);
    }

    @Test
    public void save() throws Exception {
        ServerEntity param = new ServerEntity();
        param.setIp("196.196.0.1");
        param.setStatus(ServerStatus.BOOTING);

        int count = serverMapper.insert(param);
        Assert.assertEquals(1,count);
    }

    @Test
    public void deleteById() throws Exception {
        int count = serverMapper.deleteById(0);
        Assert.assertEquals(1,count);

        ServerEntity serverEntity = serverMapper.selectById(0);
        Assert.assertNull(serverEntity);
    }

    @Test
    public void findById() throws Exception {
        ServerEntity param = new ServerEntity();
        param.setIp("127.0.0.1");
        param.setStatus(ServerStatus.BOOTING);
        serverMapper.insert(param);


        ServerEntity serverEntity = serverMapper.selectById(param.getId());
        Assert.assertEquals(param.getId(),serverEntity.getId());
    }



}