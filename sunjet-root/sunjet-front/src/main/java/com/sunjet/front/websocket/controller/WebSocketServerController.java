package com.sunjet.front.websocket.controller;


import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.sunjet.common.dao.SjUserRepository;
import com.sunjet.common.entity.SjUser;
import com.sunjet.front.common.utils.ApplicationContextUtil;
import com.sunjet.front.websocket.vo.MsgVO;

import lombok.extern.slf4j.Slf4j;

/**
 * <p> websocket处理类Controller - 群聊 </p>
 *
 * @author : zhengqing
 * @description :
 * @date : 2019/12/4 9:20
 */
@Slf4j
@Component
@ServerEndpoint("/groupChat/{sid}/{userId}")
public class WebSocketServerController {
	

    /**
     * 房间号 -> 组成员信息
     */
    private static ConcurrentHashMap<String, Map<String, Session>> groupMemberInfoMap = new ConcurrentHashMap<>();
    /**
     * 房间号 -> 在线人数
     */
    private static ConcurrentHashMap<String, Set<Map<String, String>>> onlineUserMap = new ConcurrentHashMap<>();

    /**
     * 收到消息调用的方法，群成员发送消息
     *
     * @param sid:房间号
     * @param userId：用户id
     * @param message：发送消息
     */
    @OnMessage
    public void onMessage(@PathParam("sid") String sid, @PathParam("userId") String userId, String message) {
    	Gson gson = new Gson();
    	MsgVO msg = gson.fromJson(message, MsgVO.class);
    	String toId = msg.getToId();
		List<Session> sessionList = groupMemberInfoMap.get(sid).entrySet().stream()
				.filter(k -> StringUtils.isBlank(toId) ? true : k.getKey().equals(toId) || k.getKey().equals(userId))
				.map(map -> map.getValue()).collect(Collectors.toList());
//    	List<Session> sessionList = groupMemberInfoMap.get(sid).values().stream().collect(Collectors.toList());
        Set<Map<String, String>> onlineUserList = onlineUserMap.get(sid);
        log.info("onMessage : onlineUserList.size is : {} , sessionList.size is: {} ", onlineUserList.size(), sessionList.size());
        // 先一个群组内的成员发送消息
        sessionList.forEach(item -> {
//        	item.getu
            try {
                // json字符串转对象
            	
            	msg.setUsers(onlineUserList);
                msg.setCount(onlineUserList.size());
                // json对象转字符串
                String text = gson.toJson(msg);
                item.getBasicRemote().sendText(text);
                log.info(msg.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * 建立连接调用的方法，群成员加入
     *
     * @param session
     * @param sid
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("sid") String sid, @PathParam("userId") String userId) {
        Map<String, Session> sessionList = groupMemberInfoMap.computeIfAbsent(sid, k -> new HashMap<>());
        Set<Map<String, String>> onlineUserList = onlineUserMap.computeIfAbsent(sid, k -> new HashSet<>());
        SjUser sjUser = ApplicationContextUtil.getApplicationContext().getBean(SjUserRepository.class).findByAccount(userId);
        Map<String, String> map = new HashMap<>();
        map.put("userId", userId);
        map.put("avatar", sjUser.getAvatar());
        onlineUserList.add(map);
        sessionList.put(userId, session);

        // 发送上线通知
        sendInfo(sid, userId, sjUser.getAvatar(), onlineUserList.size(), "上线了~");

        log.info("Connection connected");
        log.info("sid: {}, sessionList size: {}", sid, sessionList.size());
    }


    public void sendInfo(String sid, String userId, String avatar, Integer onlineSum, String info) {
        // 获取该连接用户信息
    	
//    	SjUser securityContextHolder = ApplicationContextUtil.getApplicationContext().getBean(SjUserRepository.class).findByAccount(userId);
    	
//        UserDetailsImpl userInfo = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//    	System.out.println(userId + "---------------------------------");
//    	SjUser sjUser = sjUserRepository.findByAccount(userId);
        // 发送通知
        MsgVO msg = new MsgVO();
        msg.setCount(onlineSum);
        msg.setUserId(userId);
        msg.setAvatar(avatar);
        msg.setMsg(userId + info);//
        // json对象转字符串
        String text = new Gson().toJson(msg);
        onMessage(sid, userId, text);
    }

    /**
     * 关闭连接调用的方法，群成员退出
     *
     * @param session
     * @param sid
     */
    @OnClose
    public void onClose(Session session, @PathParam("sid") String sid, @PathParam("userId") String userId) {
        Map<String, Session> sessionList = groupMemberInfoMap.get(sid);
        sessionList.entrySet().removeIf(e -> e.getValue().equals(session));
//        sessionList.remove(session);
        
        Set<Map<String, String>> onlineUserList = onlineUserMap.get(sid);
        Set<Map<String, String>> newonlineUserList = new HashSet<Map<String, String>>();
        for(Map<String, String> map : onlineUserList){
        	if(!map.containsValue(userId)){
        		newonlineUserList.add(map);
        	}
        }
        onlineUserMap.put(sid, newonlineUserList);
        //onlineUserList.stream().filter(it -> it.entrySet().removeIf(e -> e.getValue().equals(userId))).collect(Collectors.toList());
        // 发送离线通知
        sendInfo(sid, userId, "", onlineUserList.size(), "下线了~");

        log.info("Connection closed");
        log.info("sid: {}, sessionList size: {}", sid, sessionList.size());
    }

    /**
     * 传输消息错误调用的方法
     *
     * @param error
     */
    @OnError
    public void OnError(Throwable error) {
        log.info("Connection error");
    }
}
