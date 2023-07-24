package com.interview.airobotics;

import com.interview.airobotics.data.User;
import com.interview.airobotics.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class AiroboticsApplication {
	@Autowired
    private UserRepository userRepository;
	@PostConstruct
	public void initialUsers(){
		User user = new User(100,"Airobotics","pass123","basi1518@gmail.com");
    	userRepository.save(user);

	}

	public static void main(String[] args) {
		SpringApplication.run(AiroboticsApplication.class, args);
	}

}
