package com.felipesalles.appmatematica.resources;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.felipesalles.appmatematica.domain.User;
import com.felipesalles.appmatematica.domain.dto.UserDTO;
import com.felipesalles.appmatematica.domain.dto.UserNewDTO;
import com.felipesalles.appmatematica.services.UserService;
import com.felipesalles.appmatematica.services.exceptions.BusinessException;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest
@AutoConfigureMockMvc
public class UserResourcesTest {
	
	@Autowired
	MockMvc mvc;
	
	final String PATH_USERS = "/users";
	
	UserNewDTO userRequest = new UserNewDTO("Ana", "ana@gmail.com", "aninha", "123abc");;
	User userResponse= new User(1, "Ana", "ana@gmail.com", "aninha", "123abc", 0);
	
	@MockBean
	UserService userService;
	
	
		
	
	@Test
	@DisplayName("Deve inserir um user com sucesso")
	public void insertUserTest() throws Exception {
		
		
		String json = new ObjectMapper().writeValueAsString(userRequest);
		BDDMockito.given(userService.insert(Mockito.any(UserNewDTO.class))).willReturn(userResponse);
		
		MockHttpServletRequestBuilder request =  MockMvcRequestBuilders.post(PATH_USERS)
		                      										   .contentType(MediaType.APPLICATION_JSON)
		                      										   .accept(MediaType.APPLICATION_JSON)
		                      										   .content(json);
		
		mvc
		   .perform(request)
		   .andExpect(MockMvcResultMatchers.status().isCreated())
		   .andExpect(MockMvcResultMatchers.jsonPath("id").isNotEmpty())
		   .andExpect(MockMvcResultMatchers.jsonPath("id").value(userResponse.getId()))
		   .andExpect(MockMvcResultMatchers.jsonPath("nome").value(userResponse.getNome()))
		   .andExpect(MockMvcResultMatchers.jsonPath("email").value(userResponse.getEmail()))
		   .andExpect(MockMvcResultMatchers.jsonPath("username").value(userResponse.getUsername()));
	}
	
	@Test
	@DisplayName("Deve lançar exceção ao inserir user inválido")
	public void inserInvalidUserTest() throws Exception {
		String json = new ObjectMapper().writeValueAsString(new UserNewDTO());
		
		MockHttpServletRequestBuilder request =  MockMvcRequestBuilders.post(PATH_USERS)
																	   .contentType(MediaType.APPLICATION_JSON)
																	   .accept(MediaType.APPLICATION_JSON)
																	   .content(json);
		
		mvc
		   .perform(request)
		   .andExpect(MockMvcResultMatchers.status().isBadRequest())
		   .andExpect(MockMvcResultMatchers.jsonPath("errors", Matchers.hasSize(4)));
		
	}
	
	@Test
	@DisplayName("Deve lançar erro ao cadastrar user com email existente")
	public void insertUserWithDuplicatedEmail() throws Exception {
		
		String json = new ObjectMapper().writeValueAsString(userRequest);
		BDDMockito.given(userService.insert(Mockito.any(UserNewDTO.class))).willThrow(new BusinessException("Email ja cadastrado"));
		
		MockHttpServletRequestBuilder request =  MockMvcRequestBuilders.post(PATH_USERS)
																	   .contentType(MediaType.APPLICATION_JSON)
																	   .accept(MediaType.APPLICATION_JSON)
																	   .content(json);
		
		mvc.perform(request)
		   .andExpect(MockMvcResultMatchers.status().isBadRequest())
		   .andExpect(MockMvcResultMatchers.jsonPath("msg").value("Email ja cadastrado"));
		
	}
	
	@Test
	@DisplayName("Deve lançar erro ao cadastrar user com username existente")
	public void insertUserWithDuplicatedUsername() throws Exception {
		
		String json = new ObjectMapper().writeValueAsString(userRequest);
		BDDMockito.given(userService.insert(Mockito.any(UserNewDTO.class))).willThrow(new BusinessException("Username ja cadastrado"));
		
		MockHttpServletRequestBuilder request =  MockMvcRequestBuilders.post(PATH_USERS)
																	   .contentType(MediaType.APPLICATION_JSON)
																	   .accept(MediaType.APPLICATION_JSON)
																	   .content(json);
		
		mvc.perform(request)
		   .andExpect(MockMvcResultMatchers.status().isBadRequest())
		   .andExpect(MockMvcResultMatchers.jsonPath("msg").value("Username ja cadastrado"));
		
	}
	
	
}
