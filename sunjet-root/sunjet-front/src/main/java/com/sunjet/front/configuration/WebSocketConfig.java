package com.sunjet.front.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import com.sunjet.front.common.services.security.vo.ActiveUserStore;

/**
 * <p> WebSocket 配置 </p>
 *
 */
@Configuration
public class WebSocketConfig {

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
    
    @Bean
    public ActiveUserStore activeUserStore(){
        return new ActiveUserStore();
    }
    
  

}
