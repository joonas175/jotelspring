package fi.tamk.tiko.joonass.jotel

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class JotelApplication {

	static void main(String[] args) {
		SpringApplication.run(JotelApplication, args)
	}

}

//Tee MIDDLEWARE!!
//Aseta cookie jos ei ole, joka on käyttäjälle uniikki