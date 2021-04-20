package proyect.controllers;

import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import proyect.Service.ChatServImp;
import proyect.models.Mensaje;

@Controller
public class ChatController {
	
	@Autowired
	private ChatServImp chas;
	@Autowired
	private SimpMessagingTemplate simp;
	
	private String[] colores= {"red","green","blue","magenta","purple","orange","yellow"};
	
	@MessageMapping("/mensaje")
	/*aca si se agrega el prefijo*/
	@SendTo("/chat/mensaje")
	public Mensaje  recibirMensaje(Mensaje mensaje) {
       
		
		mensaje.setFecha(new Date().getTime());
		switch (mensaje.getTipo()) {
		case "USERNAME":
			mensaje.setColor(colores[new Random().nextInt(colores.length)]);
			mensaje.setTexto("nuevo usuario");
			break;
		case "MENSAJE":
			chas.save(mensaje);
			break;
		case "DESCONECTADO":
			mensaje.setColor("red");
			
			break;
		default:
			System.out.println("F");
			break;
		}
		
		
		return mensaje;
	}
	
	@MessageMapping("/escribiendo")
	/*aca si se agrega el prefijo*/
	@SendTo("/chat/escribiendo")
	public String  verescrib(String username) {
		return username.concat("esta escribiendo....");
	}
	
	
	
	@MessageMapping("/historial")
	/*Ya que necesitamos enviar una variable por la ruta utilizaremos 
	 * otra clase de websocket, ya que sendto no nos permite
	 * personalizar la ruta*/
	
	public void historial(String ClienteId){
		/*y asi recibimos la id por la ruta y esta envia el resultado*/
		
		simp.convertAndSend("/chat/historial/" + ClienteId ,chas.findLastMessajes());
		
	}
	

}
