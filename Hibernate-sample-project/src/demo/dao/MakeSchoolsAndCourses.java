/**
 * 
 */
package demo.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import demo.entity.Address;
import demo.entity.Citizenships;
import demo.entity.Course;
import demo.entity.Name;
import demo.entity.Person;
import demo.entity.PersonAddress;
import demo.entity.PersonSchools;
import demo.entity.SSN;
import demo.entity.School;
import demo.entity.SchoolAddress;
import demo.entity.SchoolCourses;

/**
 * 1. session is getting generated from the passed session factory.
 * 2.Transaction is getting received to take care of multiple query executions
 * together.
 * 
 * @author Arockia
 *
 */
public class MakeSchoolsAndCourses {

	public void makeCourses(SessionFactory sessionFactory) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		
		//All courses are submitted together in a single transaction.
		//The Save / SaveOrUpdate methods Persists the object's data into database
		
		Course bsc = new Course();
		bsc.setCourseName("Bachelor of Science");
		bsc.setDuration(3);
		session.save(bsc);

		Course csc = new Course();
		csc.setCourseName("Computer Science Engineering");
		csc.setDuration(4);
		session.save(csc);

		Course ba = new Course();
		ba.setCourseName("Bachelor of");
		ba.setDuration(3);
		long baID = (long) session.save(ba);

		Course nsc = new Course();
		nsc.setCourseName("Nuclear Science");
		nsc.setDuration(5);
		session.save(nsc);

		transaction.commit();

		// The get method takes the object from database for the passed ID with the given entity.
		// Please note that after the get method  baCourse is in transient state, hence merge just changes the data in that transient not in DB.
		// When commit happens the transient data is getting saved.
		
		transaction = session.beginTransaction();
		Course baCourse = session.get(Course.class, baID);
		baCourse.setCourseName("Bachelor of Arts");
		session.merge(baCourse);
		transaction.commit();

		session.clear();
		session.close();
	}

	@SuppressWarnings("unchecked")
	public void buildSchools(SessionFactory sessionFactory) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		
		//To get the all available courses which was saved earlier, we are using named query which means please take a look at the entity Course.java.
		Query<Course> coursesNamedQuery = session.createNamedQuery("courseTableData");
		List<Course> allCourses = coursesNamedQuery.getResultList();
		
		//The Oxford object of School is saved with a single address and many courses.
		School oxford = new School();
		oxford.setSchoolName("Oxford University");
		session.save(oxford);
		Address oxfordAddress = new Address();
		oxfordAddress.setHouseNo("OX1");
		oxfordAddress.setStreet("Wellington Square");
		oxfordAddress.setCity(" ");
		oxfordAddress.setState("Oxford");
		oxfordAddress.setCountry("England,UK");
		oxfordAddress.setPincode("OX1 2JD");
		session.save(oxfordAddress);
		SchoolAddress oxfordAddressMapper = new SchoolAddress();
		oxfordAddressMapper.setSchool(oxford);
		oxfordAddressMapper.setAddress(oxfordAddress);
		session.save(oxfordAddressMapper);
		SchoolCourses oxfordCourses;
		for (Course course : allCourses) {
			oxfordCourses = new SchoolCourses();
			oxfordCourses.setSchool(oxford);
			oxfordCourses.setCourse(course);
			session.save(oxfordCourses);
		}

		//The iit object of School is saved with a two addresses and many courses.
		School iit = new School();
		iit.setSchoolName("Indian Institue of Technology");
		session.save(iit);
		Address iitAddress1 = new Address();
		iitAddress1.setHouseNo("");
		iitAddress1.setStreet("Beside Adyar Cancer Institute,Adyar");
		iitAddress1.setCity("Chennai");
		iitAddress1.setState("Tamilnadu");
		iitAddress1.setCountry("India");
		iitAddress1.setPincode("600 036");
		session.save(iitAddress1);
		SchoolAddress iitAddressMapper1 = new SchoolAddress();
		iitAddressMapper1.setSchool(iit);
		iitAddressMapper1.setAddress(iitAddress1);
		session.save(iitAddress1);
		Address iitAddress2 = new Address();
		iitAddress2.setHouseNo("");
		iitAddress2.setStreet("IIT Area");
		iitAddress2.setCity("Powai,Mumbai");
		iitAddress2.setState("Maharashtra ");
		iitAddress2.setCountry("India");
		iitAddress2.setPincode("400 076");
		session.save(iitAddress2);
		SchoolAddress iitAddressMapper2 = new SchoolAddress();
		iitAddressMapper2.setSchool(iit);
		iitAddressMapper2.setAddress(iitAddress2);
		session.save(iitAddressMapper2);
		SchoolCourses iitCourses;
		for (Course course : allCourses) {
			iitCourses = new SchoolCourses();
			iitCourses.setSchool(iit);
			iitCourses.setCourse(course);
			session.save(iitCourses);
		}

		transaction.commit();

		session.clear();
		session.close();
	}

	@SuppressWarnings("unchecked")
	public void makeAdmission(SessionFactory sessionFactory) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();

		//To get the all mapped courses against school which was saved earlier, we are using HQL query.
		
		Query<SchoolCourses> stephenSchoolCoures = session.createQuery("From SchoolCourses");
		List<SchoolCourses> allSchoolCourses = stephenSchoolCoures.getResultList();

		// The object of Person stephen is getting saved with two citizenship's(one to many),a SSN, single address,and then two mapped schools and courses.
		
		Person stephen = new Person();
		Name stephenName = new Name();
		stephenName.setFirstName("Stephanie");
		stephenName.setMiddleName("");
		stephenName.setLastName("Murphy");
		stephen.setName(stephenName);

		Citizenships stephenIndia = new Citizenships();
		stephenIndia.setCountry("India");
		stephenIndia.setPerson(stephen);
		session.save(stephenIndia);
		Citizenships stephenUSA = new Citizenships();
		stephenUSA.setCountry("USA");
		stephenUSA.setPerson(stephen);
		session.save(stephenUSA);
		List<Citizenships> stephenCitizenships = new ArrayList<>();
		stephenCitizenships.add(stephenUSA);
		stephenCitizenships.add(stephenIndia);
		stephen.setCitizenships(stephenCitizenships);

		SSN stephenSsn = new SSN();
		stephenSsn.setSsnNumber("SDGYX6543A");
		stephenSsn.setWhenProvided(new Date());
		stephen.setSsn(stephenSsn);

		session.save(stephen);

		Address stephenAddress = new Address();
		stephenAddress.setHouseNo("");
		stephenAddress.setStreet("Beside Adyar Cancer Institute,Adyar");
		stephenAddress.setCity("Chennai");
		stephenAddress.setState("Tamilnadu");
		stephenAddress.setCountry("India");
		stephenAddress.setPincode("600 036");
		session.save(stephenAddress);
		
		PersonAddress stephenAddressMapper=new PersonAddress();
		stephenAddressMapper.setAddress(stephenAddress);
		stephenAddressMapper.setPerson(stephen);
		session.save(stephenAddressMapper);

		PersonSchools stephenSchool1 = new PersonSchools();
		stephenSchool1.setPerson(stephen);
		stephenSchool1.setSchoolCourses(allSchoolCourses.get(0));
		session.save(stephenSchool1);
		PersonSchools stephenSchool2 = new PersonSchools();
		stephenSchool2.setPerson(stephen);
		stephenSchool2.setSchoolCourses(allSchoolCourses.get(1));
		session.save(stephenSchool2);

		// The object of Person modi is getting saved with two citizenship's(one to many),a SSN, single address,and then four mapped schools and courses.
		Person modi = new Person();
		Name modiName = new Name();
		modiName.setFirstName("Narendra ");
		modiName.setMiddleName("");
		modiName.setLastName("Modi");
		modi.setName(modiName);

		Citizenships modiIndia = new Citizenships();
		modiIndia.setCountry("India");
		modiIndia.setPerson(modi);
		session.save(modiIndia);
		Citizenships modiAustralia = new Citizenships();
		modiAustralia.setCountry("USA");
		modiAustralia.setPerson(stephen);
		session.save(modiAustralia);
		List<Citizenships> modiCitizenships = new ArrayList<>();
		modiCitizenships.add(modiAustralia);
		modiCitizenships.add(modiIndia);
		modi.setCitizenships(modiCitizenships);

		SSN modiSsn = new SSN();
		modiSsn.setSsnNumber("ADFT765HJJ9");
		modiSsn.setWhenProvided(new Date());
		modi.setSsn(modiSsn);

		session.save(modi);

		Address modiAddress = new Address();
		modiAddress.setHouseNo("24/17");
		modiAddress.setStreet("Near L D Engineering College");
		modiAddress.setCity("Ahmedabad");
		modiAddress.setState("Gujarat ");
		modiAddress.setCountry("India");
		modiAddress.setPincode("380 009");
		session.save(modiAddress);
		
		PersonAddress modiAddressMapper=new PersonAddress();
		modiAddressMapper.setAddress(modiAddress);
		modiAddressMapper.setPerson(modi);
		session.save(modiAddressMapper);

		PersonSchools modiSchool1 = new PersonSchools();
		modiSchool1.setPerson(modi);
		modiSchool1.setSchoolCourses(allSchoolCourses.get(0));
		session.save(modiSchool1);
		PersonSchools modiSchool2 = new PersonSchools();
		modiSchool2.setPerson(modi);
		modiSchool2.setSchoolCourses(allSchoolCourses.get(1));
		session.save(modiSchool2);
		PersonSchools modiSchool3 = new PersonSchools();
		modiSchool3.setPerson(modi);
		modiSchool3.setSchoolCourses(allSchoolCourses.get(2));
		session.save(modiSchool3);
		PersonSchools modiSchool4 = new PersonSchools();
		modiSchool4.setPerson(modi);
		modiSchool4.setSchoolCourses(allSchoolCourses.get(3));
		session.save(modiSchool4);
		
		
		transaction.commit();
		session.clear();
		session.close();
	}

}
