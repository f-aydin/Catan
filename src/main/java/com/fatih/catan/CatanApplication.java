package com.fatih.catan;

import com.fatih.catan.domain.Board;
import com.fatih.catan.domain.Player;
import com.fatih.catan.repository.PlayerRepository;
import com.fatih.catan.repository.TileRepository;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@SpringBootApplication
public class CatanApplication {

	public static void main(String[] args) {
		SpringApplication.run(CatanApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**");
			}
		};
	}

	@Bean
	@Transactional
	public CommandLineRunner demo(PlayerRepository playerRepository, TileRepository tileRepository){
		return (args) -> {
			Player player1 = new Player(1, 4, 4, 4, 4, 4, true);
			Player player2 = new Player(2, 4, 4, 4, 4, 4, false);
			Player player3 = new Player(3, 4, 4, 4, 4, 4, false);
			Player player4 = new Player(4, 4, 4, 4, 4, 4, false);
			playerRepository.saveAll(List.of(player1, player2, player3, player4));

			Board board = new Board();
			tileRepository.saveAll(List.of(board.getTiles()));
		};
	}

}
