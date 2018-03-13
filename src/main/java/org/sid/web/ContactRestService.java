package org.sid.web;

import java.util.List;

import org.sid.dao.ContactRepository;
import org.sid.entities.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiParam;

@RestController
public class ContactRestService {
	@Autowired
	private ContactRepository contactRepository;

	@RequestMapping(value = "/contacts", method = RequestMethod.GET)
	public List<Contact> getAllContact() {
		return contactRepository.findAll();

	}

	@RequestMapping(value = "/chercher", method = RequestMethod.GET)
	public Page<Contact> chercher(@RequestParam(name = "mc", defaultValue = "") String mc,
			@RequestParam(name = "page", defaultValue = "0") int page,@RequestParam(name="size",defaultValue="5") int size) {

		return contactRepository.chercher("%"+mc+"%", new PageRequest(page, size));

	}

	@RequestMapping(value = "/contacts/{id}", method = RequestMethod.GET)
	public Contact getContact(@PathVariable Long id) {
		return contactRepository.findOne(id);

	}

	@RequestMapping(value = "/saveContact", method = RequestMethod.POST)
	public Contact saveContact(@RequestBody Contact c) {
		System.out.println("Save");
		return contactRepository.save(c);
	}

	@RequestMapping(value = "/saveContact{id}", method = RequestMethod.PUT)
	public Contact saveContact(@PathVariable Long id, @RequestBody Contact c) {
		c.setId(id);
		System.out.println("Update");
		return contactRepository.save(c);
	}

	@RequestMapping(value = "/deleteContact/{id}", method = RequestMethod.DELETE)
	public void deleteContact(@PathVariable Long id) {
		System.out.println("Delete" + id);
		contactRepository.delete(id);

	}

}
