package com.miempresa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.miempresa.interfaces.IUsuario;
import com.miempresa.modelo.Usuario;

@SpringBootTest
class SprinJpaApplicationTests {
	
	@Autowired
	 private IUsuario repo;
	 
	 public BCryptPasswordEncoder passEncoder() {
	  BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();
	  return bcpe;
	 }
	 
	 @Autowired
	 private BCryptPasswordEncoder encoder;

	 @Test
	 void crearUsuario() {
	  Usuario user = new Usuario();
	  user.setId(1);
	  user.setNombre("usuario1");
	  user.setClave(encoder.encode("1234"));
	  user.setRol("ROLE_ADMIN");
	  repo.save(user);
	  
	 }
	 @Test
	 void crearUsuario2() {
	     Usuario user = new Usuario();
	     user.setId(2); // Asignar un ID diferente
	     user.setNombre("usuario2");
	     user.setClave(encoder.encode("1234"));
	     user.setRol("ROLE_USER");
	     repo.save(user);
	 }


}
