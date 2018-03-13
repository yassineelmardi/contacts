package org.sid;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.sid.dao.ContactRepository;
import org.sid.entities.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ContactsApplication implements CommandLineRunner {

	@Autowired
	private ContactRepository contactRepository;

	public static void main(String[] args) {
		SpringApplication.run(ContactsApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		DateFormat dn = new SimpleDateFormat("dd/MM/yyyy");
		contactRepository
				.save(new Contact("el Mardi", "Yassine", dn.parse("12/10/1985"), "yassine@gmail.com", 6325415, "/tmp"));
		contactRepository
				.save(new Contact("Khalid", "Khalid", dn.parse("12/10/1998"), "yassine@gmail.com", 111111, "/tmp"));

		contactRepository
				.save(new Contact("Kamal", "kamal", dn.parse("12/10/1990"), "yassine@gmail.com", 222222, "/tmp"));

		contactRepository
				.save(new Contact("Mohcine", "Mohcine", dn.parse("12/10/1970"), "yassine@gmail.com", 33333, "/tmp"));
		
		contactRepository.findAll().forEach(c->{
			System.out.println(c.getPrenom());
		});

	}
}
