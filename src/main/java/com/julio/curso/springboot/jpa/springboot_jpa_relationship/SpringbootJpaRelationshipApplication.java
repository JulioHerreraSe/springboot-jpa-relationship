package com.julio.curso.springboot.jpa.springboot_jpa_relationship;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import com.julio.curso.springboot.jpa.springboot_jpa_relationship.entities.Address;
import com.julio.curso.springboot.jpa.springboot_jpa_relationship.entities.Client;
import com.julio.curso.springboot.jpa.springboot_jpa_relationship.entities.ClientDetails;
import com.julio.curso.springboot.jpa.springboot_jpa_relationship.entities.Course;
import com.julio.curso.springboot.jpa.springboot_jpa_relationship.entities.Invoice;
import com.julio.curso.springboot.jpa.springboot_jpa_relationship.entities.Student;
import com.julio.curso.springboot.jpa.springboot_jpa_relationship.repositories.ClientDetailsRepository;
import com.julio.curso.springboot.jpa.springboot_jpa_relationship.repositories.ClientRepository;
import com.julio.curso.springboot.jpa.springboot_jpa_relationship.repositories.CourseRepository;
import com.julio.curso.springboot.jpa.springboot_jpa_relationship.repositories.InvoiceRepository;
import com.julio.curso.springboot.jpa.springboot_jpa_relationship.repositories.StudentRepository;

@SpringBootApplication
public class SpringbootJpaRelationshipApplication implements CommandLineRunner{

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private InvoiceRepository invoiceRepository;

	@Autowired
	private ClientDetailsRepository clientDetailsRepository;

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private CourseRepository courseRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootJpaRelationshipApplication.class, args);
	}

	public void run(String... args) throws Exception {
		manyToManyBidireccionalRemove();
	}

	@Transactional
	public void manyToManyBidireccionalRemove() {

		Student student1 = new Student("Luis", "Suarez");
		Student student2 = new Student("Pepe", "Porta"); 

		Course course1 = new Course("Curso java master", "Andres");
		Course course2 = new Course("Curso springboot", "Andres");

		student1.addCourse(course1);
		student1.addCourse(course2);
		student2.addCourse(course2);

		studentRepository.saveAll(Set.of(student1, student2));

		System.out.println(student1);
		System.out.println(student2);

		Optional<Student> studentOptionalDb = studentRepository.findOneWithCourses(3L);
		if (studentOptionalDb.isPresent()) {
			Student studentDb = studentOptionalDb.get();
			Optional<Course> courseOptionalDb = courseRepository.findOneWithStudents(3L);

			if (courseOptionalDb.isPresent()) {
				Course courseDb = courseOptionalDb.get();
				studentDb.RemoveCourse(courseDb);

				studentRepository.save(studentDb);
				System.out.println(studentDb);
			}
		}
	}

	@Transactional
	public void manyToManyBidireccional() {

		Student student1 = new Student("Luis", "Suarez");
		Student student2 = new Student("Pepe", "Porta"); 

		Course course1 = new Course("Curso java master", "Andres");
		Course course2 = new Course("Curso springboot", "Andres");

		student1.addCourse(course1);
		student1.addCourse(course2);
		student2.addCourse(course2);

		studentRepository.saveAll(Set.of(student1, student2));

		System.out.println(student1);
		System.out.println(student2);
	}

	@Transactional
	public void manyToManyRemove() {

		Student student1 = new Student("Luis", "Suarez");
		Student student2 = new Student("Pepe", "Porta"); 

		Course course1 = new Course("Curso java master", "Andres");
		Course course2 = new Course("Curso springboot", "Andres");

		student1.setCourses(Set.of(course1, course2));
		student2.setCourses(Set.of(course2));

		studentRepository.saveAll(Set.of(student1, student2));

		System.out.println(student1);
		System.out.println(student2);

		Optional<Student> studentOptionalDb = studentRepository.findOneWithCourses(3L);
		if (studentOptionalDb.isPresent()) {
			Student studentDb = studentOptionalDb.get();
			Optional<Course> courseOptionalDb = courseRepository.findById(3L);

			if (courseOptionalDb.isPresent()) {
				Course courseDb = courseOptionalDb.get();
				studentDb.getCourses().remove(courseDb);

				studentRepository.save(studentDb);
				System.out.println(studentDb);
			}
		}
	}

	@Transactional
	public void manyToManyRemoveFind() {

		Optional<Student> studentOptional1 = studentRepository.findById(1L);
		Optional<Student> studentOptional2 = studentRepository.findById(2L);

		Student student1 = studentOptional1.get();
		Student student2 = studentOptional2.get();

		Course course1 = courseRepository.findById(1L).get();
		Course course2 = courseRepository.findById(2L).get();

		student1.setCourses(Set.of(course1, course2));
		student2.setCourses(Set.of(course2));

		studentRepository.saveAll(Set.of(student1, student2));

		System.out.println(student1);
		System.out.println(student2);

		Optional<Student> studentOptionalDb = studentRepository.findOneWithCourses(1L);
		if (studentOptionalDb.isPresent()) {
			Student studentDb = studentOptionalDb.get();
			Optional<Course> courseOptionalDb = courseRepository.findById(2L);

			if (courseOptionalDb.isPresent()) {
				Course courseDb = courseOptionalDb.get();
				studentDb.getCourses().remove(courseDb);

				studentRepository.save(studentDb);
				System.out.println(studentDb);
			}
		}

	}

	@Transactional
	public void manyToManyFind() {

		Optional<Student> studentOptional1 = studentRepository.findById(1L);
		Optional<Student> studentOptional2 = studentRepository.findById(2L);

		Student student1 = studentOptional1.get();
		Student student2 = studentOptional2.get();

		Course course1 = courseRepository.findById(1L).get();
		Course course2 = courseRepository.findById(2L).get();

		student1.setCourses(Set.of(course1, course2));
		student2.setCourses(Set.of(course2));

		studentRepository.saveAll(Set.of(student1, student2));

		System.out.println(student1);
		System.out.println(student2);
	}

	@Transactional
	public void manyToMany() {

		Student student1 = new Student("Luis", "Suarez");
		Student student2 = new Student("Pepe", "Porta"); 

		Course course1 = new Course("Curso java master", "Andres");
		Course course2 = new Course("Curso springboot", "Andres");

		student1.setCourses(Set.of(course1, course2));
		student2.setCourses(Set.of(course2));

		studentRepository.saveAll(Set.of(student1, student2));

		System.out.println(student1);
		System.out.println(student2);
	}

	@Transactional
	public void oneToOneBidireccionalFindById() {

		Optional<Client> clientOptional = clientRepository.findOne(2L);

		clientOptional.ifPresent(client -> {
			ClientDetails clientDetails = new ClientDetails(true, 5000);
		
		client.setClientDetails(clientDetails);
		clientDetails.setClient(client);
		
		clientRepository.save(client);

		System.out.println(client);
		});
	}

	@Transactional
	public void oneToOneBidireccional() {

		Client client = new Client("Pepe", "Gomez");
		ClientDetails clientDetails = new ClientDetails(true, 5000);
		
		client.setClientDetails(clientDetails);
		clientDetails.setClient(client);

		clientRepository.save(client);

		System.out.println(client);
	}

	@Transactional
	public void oneToOneFindById() {
		ClientDetails clientDetails = new ClientDetails(true, 5000);
		clientDetailsRepository.save(clientDetails);

		Optional<Client> clientOptional = clientRepository.findOne(2L);

		clientOptional.ifPresent(client -> {
			client.setClientDetails(clientDetails);
			clientRepository.save(client);
			System.out.println(client);
		});
	}

	@Transactional
	public void oneToOne() {
		ClientDetails clientDetails = new ClientDetails(true, 5000);
		clientDetailsRepository.save(clientDetails);

		Client client = new Client("Pepe", "Gomez");
		client.setClientDetails(clientDetails);
		clientRepository.save(client);

		System.out.println(client);
	}

	@Transactional
	public void removeInvoiceBidireccionalFindById(){
		Optional<Client> optionalClient = clientRepository.findOne(1L);

		optionalClient.ifPresent(client -> {

			Invoice invoice1 = new Invoice("compras de la casa", 5000L);
			Invoice invoice2 = new Invoice("compras de la oficina", 8000L);

			client.addInvoice(invoice1).addInvoice(invoice2);

			clientRepository.save(client);

			System.out.println(client);
		});

		Optional<Client> optionalClientBd = clientRepository.findOne(1L);

		optionalClientBd.ifPresent(client -> {
			Optional<Invoice> invoiceOptional = invoiceRepository.findById(2L);
			invoiceOptional.ifPresent(invoice -> {
				client.getInvoices().remove(invoice);
				invoice.setClient(null);

				clientRepository.save(client);
				System.out.println(client);
			});
		});

	}

	@Transactional
	public void oneToManyInvoiceBidireccionalFindById(){
		Optional<Client> optionalClient = clientRepository.findOne(1L);

		optionalClient.ifPresent(client -> {

			Invoice invoice1 = new Invoice("compras de la casa", 5000L);
			Invoice invoice2 = new Invoice("compras de la oficina", 8000L);

			client.addInvoice(invoice1).addInvoice(invoice2);

			clientRepository.save(client);

			System.out.println(client);
		});

	}

	@Transactional
	public void oneToManyInvoiceBidireccional(){
		Client client = new Client("Fran", "Mora");

		Invoice invoice1 = new Invoice("compras de la casa", 5000L);
		Invoice invoice2 = new Invoice("compras de la oficina", 8000L);

		client.addInvoice(invoice1).addInvoice(invoice2);

		clientRepository.save(client);

		System.out.println(client);
	}

	@Transactional
	public void removeAddressFindById(){
		Optional<Client> optionalClient = clientRepository.findById(2L);
		optionalClient.ifPresent(client -> {
			Address address1 = new Address("El verjel", 1234);
			Address address2 = new Address("Vasco de Gama", 9876);

			Set<Address> addresses = new HashSet<>();
			addresses.add(address1);
			addresses.add(address2);
			client.setAddresses(addresses);

			clientRepository.save(client);

			System.out.println(client);

			Optional<Client> optionalClient2 = clientRepository.findById(2L);

			optionalClient2.ifPresent(c -> {
				c.getAddresses().remove(address2);
				System.out.println(c);
				clientRepository.save(c);
				System.out.println(c);
			});
		});
	}

	@Transactional
	public void removeAddress(){
		Client client = new Client("Fran", "Mora");

		Address address1 = new Address("El verjel", 1234);
		Address address2 = new Address("Vasco de Gama", 9876);

		client.getAddresses().add(address1);
		client.getAddresses().add(address2);

		clientRepository.save(client);

		Optional<Client> optionalClient = clientRepository.findById(3L);

		optionalClient.ifPresent(c -> {
			c.getAddresses().remove(address1);
			clientRepository.save(c);
			System.out.println(c);
		});
	}

	@Transactional
	public void oneToManyFindById(){
		Optional<Client> optionalClient = clientRepository.findById(2L);
		optionalClient.ifPresent(client -> {
			Address address1 = new Address("El verjel", 1234);
			Address address2 = new Address("Vasco de Gama", 9876);

			Set<Address> addresses = new HashSet<>();
			addresses.add(address1);
			addresses.add(address2);

			client.setAddresses(addresses);

			clientRepository.save(client);

			System.out.println(client);
		});
	}

	@Transactional
	public void oneToMany(){
		Client client = new Client("Fran", "Mora");

		Address address1 = new Address("El verjel", 1234);
		Address address2 = new Address("Vasco de Gama", 9876);

		client.getAddresses().add(address1);
		client.getAddresses().add(address2);

		clientRepository.save(client);
	}

	@Transactional
	public void manyToOne() {

		Client client = new Client("Jhon", "Doe");
		clientRepository.save(client);

		Invoice invoice = new Invoice("compras de oficina", 2000L);
		invoice.setClient(client);
		Invoice invoiceDB = invoiceRepository.save(invoice);
		System.out.println(invoiceDB);
	}

	@Transactional
	public void manyToOneFindByIdClient() {

		Optional<Client> optionalClient = clientRepository.findById(1L);

		if (optionalClient.isPresent()) {
			Client client = optionalClient.orElseThrow();
			Invoice invoice = new Invoice("compras de oficina", 2000L);
			invoice.setClient(client);
			Invoice invoiceDB = invoiceRepository.save(invoice);
			System.out.println(invoiceDB);
		}

	}

}
