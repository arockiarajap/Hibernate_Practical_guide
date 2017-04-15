/**
 * 
 */
package demo.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import demo.entity.Address;
import demo.entity.Course;
import demo.entity.Name;
import demo.entity.School;
import demo.entity.SchoolAddress;
import demo.entity.SchoolCourses;

/**
 * This whole class deals with retrieving data from database with the options
 * such as Criteria, Join using Criteria, JPA Named Query, HQL query.
 * 
 * @author Arockia
 *
 */
public class ReportsManager {

	@SuppressWarnings({ "deprecation", "unchecked" })
	public void approvedCourses(SessionFactory sessionFactory) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();

		// Retrieve all the rows from Course table

		Criteria courseCriteria = session.createCriteria(Course.class);
		List<Course> courses = courseCriteria.list();

		// Java 8 For Each method on collection

		courses.forEach(System.out::println);

		transaction.commit();
		session.clear();
		session.close();
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	public void approvedSchools(SessionFactory sessionFactory) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();

		// Retrieve all the rows from School table
		Criteria schoolsCriteria = session.createCriteria(School.class);
		List<School> schools = schoolsCriteria.list();
		schools.forEach(System.out::println);

		// The address is getting accessed for each school.
		schools.forEach((school) -> {
			Criteria schoolAddressCriteria = session.createCriteria(SchoolAddress.class, "schooladdress");
			schoolAddressCriteria.add(Restrictions.eq("schooladdress.school.schoolId", school.getSchoolId()));
			List<SchoolAddress> schoolsAddress = schoolAddressCriteria.list();
			System.out.println("Address for :" + school.getSchoolName());
			schoolsAddress.forEach((schooladr) -> {
				System.out.println(schooladr.getAddress());
			});
		});

		transaction.commit();
		session.clear();
		session.close();
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	public void approvedCoursesInSchool(SessionFactory sessionFactory) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();

		// Retrieve all the rows from School table by keeping distinct option.
		Criteria schoolsCriteria = session.createCriteria(School.class);
		schoolsCriteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

		// The Courses is getting accessed for each school.
		List<School> list = schoolsCriteria.list();
		list.forEach(school -> {
			Criteria schoolsCoursesCriteria = session.createCriteria(SchoolCourses.class, "allSchoolCourses");
			schoolsCoursesCriteria.add(Restrictions.eq("allSchoolCourses.school.schoolId", school.getSchoolId()));
			List<SchoolCourses> coursesOnSchool = schoolsCoursesCriteria.list();
			System.out.println(school.getSchoolName());
			coursesOnSchool.forEach(courses -> {
				System.out.println(courses.getCourse().getCourseName());
			});

		});

		transaction.commit();
		session.clear();
		session.close();
	}

	@SuppressWarnings({ "unchecked" })
	public void allPersons(SessionFactory sessionFactory) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();

		// Named query is used here to get the names of persons.
		Query<Name> allPersonsQuery = session.createNamedQuery("allpersons");
		List<Name> allPersons = allPersonsQuery.getResultList();
		allPersons.forEach(System.out::println);
		transaction.commit();
		session.clear();
		session.close();
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	public void personDetails(SessionFactory sessionFactory, String name) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();

		// Hibernate Query Language is used here to get the custom results.
		Query<Object> hqlQuery = session.createQuery(
				"SELECT pe.name,ssn.ssnNumber,citizen.country,peraddr.address,schoocou.school,schoocou.course FROM Person pe JOIN SSN ssn on pe.ssn.ssnId=ssn.ssnId JOIN Citizenships citizen on pe.personId=citizen.person.personId "
						+ "JOIN PersonAddress peraddr on peraddr.person.personId=pe.personId JOIN PersonSchools perscho on perscho.person.personId=pe.personId JOIN SchoolCourses "
						+ "schoocou ON perscho.schoolCourses.schoolCourseId=schoocou.schoolCourseId WHERE pe.name.firstName = :firstname");
		hqlQuery.setString("firstname", name);
		List<Object> list = hqlQuery.list();
		for (Object obj : list) {
			Object[] row = (Object[]) obj;
			System.out.println((Name) row[0] + "\t" + (String) row[1] + "\t" + (String) row[2] + "\t" + (Address) row[3]
					+ "\t" + (School) row[4] + "\t" + (Course) row[5]);
		}
		transaction.commit();
		session.clear();
		session.close();
	}

	@SuppressWarnings("unchecked")
	public void allPersonDetails(SessionFactory sessionFactory) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		
		// Again Named query is used here to get the names of persons.
		Query<Name> allPersonsQuery = session.createNamedQuery("allpersons");
		List<Name> allPersons = allPersonsQuery.getResultList();
		allPersons.forEach((name) -> {
			System.out.println(name + " details :");
			this.personDetails(sessionFactory, name.getFirstName());
			System.out.println("-------------------------------------------------------------------------------------");
		});
		
		transaction.commit();
		session.clear();
		session.close();
	}
}
