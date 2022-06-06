package co.edu.uco.quotes.data.dao;

import java.util.List;

import co.edu.uco.quotes.dto.ClientDTO;

public interface ClientDAO {
	void create(ClientDTO client);
	void update(ClientDTO client);
	void delete(int id);
	List<ClientDTO> find(ClientDTO client);
}
