package dev.hotel;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import dev.hotel.entite.Client;
import dev.hotel.service.ClientService;
import dev.hotel.web.client.ClientController;

@WebMvcTest(ClientController.class)
public class ClientControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ClientService clientService;
	
/**	@MockBean
	ClientRepository clientRepository;

	@Test
	public void testListClient() throws Exception {
		//
		Client c1 = new Client();
		c1.setNom("Smith");
		c1.setPrenoms("Dave");

		Client c2 = new Client();
		c2.setNom("Jones");
		c2.setPrenoms("Pete");

		when(clientRepository.findAll(PageRequest.of(10, 20))).thenReturn(new PageImpl<>(Arrays.asList(c1, c2)));

		// GET /clients
		mockMvc.perform(MockMvcRequestBuilders.get("/clients?start=10&size=20"))
				.andExpect(MockMvcResultMatchers.jsonPath("[0].nom").value("Smith"))
				.andExpect(MockMvcResultMatchers.jsonPath("[0].prenoms").value("Dave"))
				.andExpect(MockMvcResultMatchers.jsonPath("[1].nom").value("Jones"));
	}
*/

	@Test
	void testListClient() throws Exception {

		Client client = new Client("Smith", "Dave");
		Client client2 = new Client("Jones", "Pete");

		Mockito.when(clientService.listerClients(0, 2))
				.thenReturn(Arrays.asList(client, client2));

		mockMvc.perform(MockMvcRequestBuilders.get("/clients?start=0&size=2")).andExpect(status().isOk())
				.andExpect(jsonPath("[0].nom").value("Smith")).andExpect(jsonPath("[0].prenoms").value("Dave"))
				.andExpect(jsonPath("[1].nom").value("Jones")).andExpect(jsonPath("[1].prenoms").value("Pete"));

	}

	
	@Test
	void testGetClientUUID() throws Exception {
		
		Client client = new Client("Smith", "Dave");
		UUID id = UUID.randomUUID();
		client.setUuid(id);

		Mockito.when(clientService.recupererClient(id)).thenReturn(Optional.of(client));

		mockMvc.perform(MockMvcRequestBuilders.get("/clients/{uuid}", id))
				.andExpect(status().isOk())
				.andExpect(
						jsonPath("$.nom").value("Smith")
				).andExpect(
						jsonPath("$.prenoms").value("Dave"));
	}

	
	


}
