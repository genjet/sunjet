package com.sunjet.front.websocket.vo;

import lombok.Data;

@Data
public class ChatUser {
	private String userId;
	private String avatar;
	private int checkMsg = 0;
	private int unCheckMsg = 0;
}
