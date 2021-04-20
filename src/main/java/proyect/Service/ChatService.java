package proyect.Service;

import java.util.List;

import proyect.models.Mensaje;

public interface ChatService {
	public List<Mensaje> findLastMessajes();
	public Mensaje save(Mensaje men);

}
