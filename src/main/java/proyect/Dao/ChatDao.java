package proyect.Dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import proyect.models.Mensaje;

public interface ChatDao extends MongoRepository<Mensaje,String > {
	
	
	public List<Mensaje> findFirst10ByOrderByFechaDesc();

}
