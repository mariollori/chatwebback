package proyect;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig  implements WebSocketMessageBrokerConfigurer{

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		/* ruta para conectarse desde angular a este servidor
		 * configuracion del cors
		 * metodo que dice que stomp usara por debajo la libreria
		 * sockjs para realizar las acciones*/
		registry.addEndpoint("/chat-websocket")
		.setAllowedOrigins("http://localhost:4200")
		.withSockJS();
	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		/*prefijo de ruta para los eventos 
		 * prefijo para ruta de destinacion*/
		registry.enableSimpleBroker("/chat/");
		registry.setApplicationDestinationPrefixes("/app");
	}
	
	

}
