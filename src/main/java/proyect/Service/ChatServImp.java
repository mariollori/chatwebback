package proyect.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proyect.Dao.ChatDao;
import proyect.models.Mensaje;

@Service
public class ChatServImp implements ChatService{

	
	@Autowired
	private ChatDao chatd;
	@Override
	public List<Mensaje> findLastMessajes() {
		// TODO Auto-generated method stub
		return chatd.findFirst10ByOrderByFechaDesc();
	}

	@Override
	public Mensaje save(Mensaje men) {
		// TODO Auto-generated method stub
		return chatd.save(men);
	}

}
