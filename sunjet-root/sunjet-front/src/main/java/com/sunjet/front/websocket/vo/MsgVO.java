package com.sunjet.front.websocket.vo;
import java.util.HashSet;
import java.util.Set;

import lombok.Data;

/**
 *  <p>  websocket消息内容 </p>
 *
 * @author：  zhengqing <br/>
 * @date：  2019/12/4$ 10:27$ <br/>
 * @version：  <br/>
 */
@Data
//@ApiModel(description = "websocket消息内容")
public class MsgVO {

//    @ApiModelProperty(value = "用户id")
    private String userId;

//    @ApiModelProperty(value = "用户名")
    private String username;

//    @ApiModelProperty(value = "用户头像")
    private String avatar;

//    @ApiModelProperty(value = "消息")
    private String msg;

//    @ApiModelProperty(value = "在线人数")
    private int count;
    
    private Set<ChatUser> users = new HashSet<ChatUser>();
    
    private String toId;
    
    private String groupKey;
    
    private boolean isCheck = false;
    

}
