package com.felipesalles.appmatematica.services;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.felipesalles.appmatematica.domain.User;
import com.felipesalles.appmatematica.domain.dto.UserDTO;
import com.felipesalles.appmatematica.domain.dto.UserNewDTO;
import com.felipesalles.appmatematica.repositories.UserRepository;
import com.felipesalles.appmatematica.services.exceptions.BusinessException;
import com.felipesalles.appmatematica.services.exceptions.ObjectNotFoundException;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class UserServiceTest {
	
	
	UserService service;
	
	@MockBean
	UserRepository repo;
	
	
	
	String NOME = "Felipe";
	String EMAIL = "felipe@gmail.com";
	String USERNAME = "felipe";
	String SENHA = "123abc";
	Integer PONTUACAO = 0;
	User user, savedUser;
	UserNewDTO userDto;
	
	@BeforeEach
	public void setUp() {
		user = new User(null, NOME, EMAIL, USERNAME, SENHA, PONTUACAO);
		savedUser = new User(1, NOME, EMAIL, USERNAME, SENHA, PONTUACAO);
		userDto = new UserNewDTO(NOME, EMAIL, USERNAME, SENHA);
		
		this.service = new UserService(repo);
	}
	
	@Test
	@DisplayName("Deve retornar um user específico")
	public void findOneTest() {
		Mockito.when(repo.findById(Mockito.anyInt())).thenReturn(Optional.of(savedUser));
		
		Optional<User> op = service.findOne(savedUser.getId());
		
		assertThat(op.isPresent()).isTrue();
		assertThat(op.get().getNome()).isEqualTo(savedUser.getNome());
		assertThat(op.get().getEmail()).isEqualTo(savedUser.getEmail());
		assertThat(op.get().getUsername()).isEqualTo(savedUser.getUsername());
		assertThat(op.get().getSenha()).isEqualTo(savedUser.getSenha());
	}
	
	@Test
	@DisplayName("Deve lançar uma not found exception")
	public void userNotFoundTest() {
		Throwable exception = Assertions.catchThrowable(() ->service.findOne(savedUser.getId())) ;
		
		assertThat(exception)
					.isInstanceOf(ObjectNotFoundException.class)
					.hasMessage("Objeto não encontrado");
	}
	
	@Test
	@DisplayName("Deve retornar todos os users cadastrados")
	public void findAllTest() {
		List<User> users = new ArrayList<>();
		
		users.add(savedUser);
		users.add(new User(2, "Paulo", "paulo@gmail.com", "paulin", "123abc", 0));
		
		Mockito.when(repo.findAll()).thenReturn(users);
		
		List<UserDTO> usersDto = service.findAll();
		
		assertThat(usersDto).isNotEmpty();
		assertThat(usersDto.size()).isEqualTo(users.stream().map(x -> new UserDTO(x)).collect(Collectors.toList()).size());
		
	}
	
	@Test
	@DisplayName("Deve salvar um livro com sucesso")
	public void saveUserTest() {
		
		Mockito.when(repo.save(user)).thenReturn(savedUser);
		//execucao
		user = service.insert(userDto);
		//verificacao
		assertThat(user.getId()).isNotNull();
		assertThat(user.getNome()).isEqualTo(NOME);
		assertThat(user.getEmail()).isEqualTo(EMAIL);
		assertThat(user.getUsername()).isEqualTo(USERNAME);
	}
	
	@Test
	@DisplayName("Deve lançar erro de regra de negócio ao tentar cadastrar user com username existente")
	public void notDuplicatedUsernameTest() {
		Mockito.when(repo.findByUsername(user.getUsername())).thenReturn(savedUser);
		
		Throwable exception = Assertions.catchThrowable(() ->service.insert(userDto) ) ;
		
		assertThat(exception)
					.isInstanceOf(BusinessException.class)
					.hasMessage("Username já cadastrado");
	}
	
	@Test
	@DisplayName("Deve lançar erro de regra de negócio ao tentar cadastrar user com email existente")
	public void notDuplicatedEmailTest() {
		Mockito.when(repo.findByEmail(user.getEmail())).thenReturn(savedUser);
		
		Throwable exception = Assertions.catchThrowable(() ->service.insert(userDto) ) ;
		
		assertThat(exception)
					.isInstanceOf(BusinessException.class)
					.hasMessage("Email já cadastrado");
	}
	
	
	
}
