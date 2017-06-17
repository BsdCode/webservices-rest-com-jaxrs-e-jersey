package br.com.alura.loja;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.xstream.XStream;

import br.com.alura.loja.modelo.Carrinho;
import br.com.alura.loja.modelo.Produto;

public class ClienteTest {
	
	private HttpServer server;
	private Client client;
	private WebTarget target;
	
	@Before
	public void startServer(){
		server = Servidor.inicializaServidor();
		ClientConfig config = new ClientConfig();
		config.register(new LoggingFilter());
		
		client = ClientBuilder.newClient(config);
		target = client.target("http://localhost:8080/");
		
	}
	
	@Test
	public void testaConexaoComServidor(){
		
		Carrinho carrinho = target.path("carrinhos/1").request().get(Carrinho.class);
		
		Assert.assertEquals("Rua Vergueiro 3185, 8 andar",carrinho.getRua());

	}
	
	@Test
	public void testaCriacaoDeCarrinho(){
		
       
		Carrinho carrinho = new Carrinho();
    	carrinho.adiciona(new Produto(314L, "Tablet", 999, 1));
    	carrinho.setRua("Rua Vergueiro");
    	carrinho.setCidade("Sao Paulo");
    	    	
    	 Entity<Carrinho> entity = Entity.entity(carrinho, MediaType.APPLICATION_XML);

         Response response = target.path("/carrinhos").request().post(entity);

         Assert.assertEquals(201,response.getStatus());
         String location = response.getHeaderString("Location");
         Carrinho carrinhoCarregado = client.target("http://"+location).request().get(Carrinho.class);
         
         Assert.assertEquals("Tablet", carrinhoCarregado.getProdutos().get(0).getNome());
         
	}
	
	@After
	public void killServer(){
		server.stop();
	
	}

}
