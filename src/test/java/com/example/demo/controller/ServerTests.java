package com.example.demo.controller;

import com.example.demo.DemoApplication;
import com.example.demo.common.constant.ServerStatus;
import com.example.demo.common.exception.UpdateException;
import com.example.demo.entity.ServerEntity;
import com.example.demo.service.SeverService;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class ServerTests {

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @Before
    public void before() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    //----------------------------------------create method test----------------------------------------
    /**
     * 成功创建server
     * create server success
     */
    @Test
    public void createServerEntitySucess() {
        String jsonParam = "{\"ip\":\"127.0.0.3\",\"status\":\"BOOTING\"}";
        try {
            MockHttpServletResponse response = createServer(jsonParam);
            Assert.assertEquals("return code is error !", response.getStatus(), HttpStatus.CREATED.value());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    /**
     * ip参数错误方式创建
     * create server with ip error
     */
    @Test
    public void createServerEntityErrorWithIp() {
        String jsonParam = "{\"ip\":\"888.888.88.888\",\"status\":\"BOOTIN1G\"}";
        try {
            MockHttpServletResponse response = createServer(jsonParam);
            Assert.assertEquals("return code is error !", response.getStatus(), HttpStatus.BAD_REQUEST.value());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * status参数错误方式创建
     * create server with status error
     */
    @Test
    public void createServerEntityErrorWithStatus() {
        String jsonParam = "{\"ip\":\"127.0.0.1\",\"status\":\"ERRORCODE\"}";
        try {
            MockHttpServletResponse response = createServer(jsonParam);
            Assert.assertEquals("return code is error !", response.getStatus(), HttpStatus.BAD_REQUEST.value());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private MockHttpServletResponse createServer(String jsonParam) throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                post("/server/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonParam))
                .andReturn();
        return mvcResult.getResponse();
    }

    //----------------------------------------read method test------------------------------------------
    /**
     * find server by correct id
     */
    @Test
    public void findServerEntityByCorrectId() {
        int id = 1;
        try {
            MockHttpServletResponse response = findServerEntityById(id);
            Assert.assertEquals("return code is error !", response.getStatus(), HttpStatus.OK.value());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * find server by wrong id
     */
    @Test
    public void findServerEntityByWrongId() {
        int id = 100;
        try {
            MockHttpServletResponse response = findServerEntityById(id);
            Assert.assertEquals("return code is error !", response.getStatus(), HttpStatus.NOT_FOUND.value());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private MockHttpServletResponse findServerEntityById(int id) throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                get("/server/"+id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        return mvcResult.getResponse();
    }


    //----------------------------------------updateFullEntity full method test-----------------------------------

    /**
     * update by correct Value
     */
    @Test
    public void updateFullEntityByCorrectValue() {
        String jsonParam = "{\"ip\":\"127.0.0.8\",\"status\":\"BOOTING\"}";
        int id = 0;
        try {
            MockHttpServletResponse response = updateFullEntity(jsonParam,id);
            Assert.assertEquals("return code error",response.getStatus(),HttpStatus.OK.value());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * update by wrong value
     */
    @Test
    public void updateFullEntityByWrongValue() {
        String jsonParam = "{\"ip\":\"888.888.888.888\",\"status\":\"ERRORCODE\"}";
        int id = 1;
        try {
            MockHttpServletResponse response = updateFullEntity(jsonParam,id);
            Assert.assertEquals("return code error",response.getStatus(),HttpStatus.BAD_REQUEST.value());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private MockHttpServletResponse updateFullEntity(String jsonParam, int id) throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                put("/server/"+id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonParam))
                .andReturn();
        return mvcResult.getResponse();
    }

    //----------------------------------------updateFullEntity partial method test--------------------------------

    /**
     * update partial by correct Value
     */
    @Test
    public void updatePartialEntityByCorrectValue() {
        String jsonParam = "{\"ip\":\"192.168.0.1\" }";
        try {
            MockHttpServletResponse response = updatePartialEntity(jsonParam);
            Assert.assertEquals("return code error",response.getStatus(),HttpStatus.OK.value());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * update partial by wrong Value
     */
    @Test
    public void updatePartialEntityByWrongValue() {
        String jsonParam = "{\"ip\":\"999.999.999.999\",\"status\":\"ERRORCODE\"}";
        try {
            MockHttpServletResponse response = updatePartialEntity(jsonParam);
            Assert.assertEquals("return code error",response.getStatus(),HttpStatus.BAD_REQUEST.value());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private MockHttpServletResponse updatePartialEntity(String jsonParam) throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                post("/server/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonParam))
                .andReturn();
        return mvcResult.getResponse();
    }

    //----------------------------------------delete partial method test--------------------------------
    /**
     * del server by correct id
     */
    @Test
    public void delServerByCorrectIdTest() {
        int id = 1;
        try {
            MockHttpServletResponse response = getMockHttpServletResponse(id);
            Assert.assertEquals("retur", response.getStatus(), HttpStatus.OK.value());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * del server by wrong id
     */
    @Test
    public void delServerByWrongIdTest() {
        int id = 100;
        try {
            MockHttpServletResponse response = getMockHttpServletResponse(id);
            Assert.assertEquals("retur", response.getStatus(), HttpStatus.NOT_FOUND.value());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private MockHttpServletResponse getMockHttpServletResponse(int id) throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                delete("/server/"+id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        return mvcResult.getResponse();
    }


    @Autowired
    private SeverService service;

    @Test
    public void optimisticLocktest() throws UpdateException, InterruptedException {

        //count为1 代表 只有一个更新操作成功
        AtomicInteger count = new AtomicInteger(0);

        CountDownLatch latch = new CountDownLatch(1);

        new Thread(() -> {
            ServerEntity firstParam = new ServerEntity();
            firstParam.setIp("1.1.1.1");
            firstParam.setStatus(ServerStatus.STARTED);
            firstParam.setId(1);
            firstParam.setVersion(0);
            try {
                latch.await();
                service.updateFullEntity(firstParam);
            } catch (UpdateException e) {
                count.incrementAndGet();
            } catch (InterruptedException e) {
            }
        }).start();

       new Thread(() -> {
           ServerEntity secondParam = new ServerEntity();
           secondParam.setIp("2.2.2.2");
           secondParam.setStatus(ServerStatus.BOOTING);
           secondParam.setId(1);
           secondParam.setVersion(0);
           try {
               latch.await();
               service.updateFullEntity(secondParam);
           } catch (UpdateException e) {
               count.incrementAndGet();
           } catch (InterruptedException e) {
           }
       }).start();

        latch.countDown();

        Thread.sleep(3000);

        Assert.assertEquals(1,count.get());

    }
}