/**
 * 
 */
package demo;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import demo.dao.ReportsManager;

/**
 * @author Arockia
 *
 */
public class HRBookDisplay {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

		ReportsManager reportsManager = new ReportsManager();
		System.out.println("All Approved Courses");
		System.out.println("----------------------------------");
		reportsManager.approvedCourses(sessionFactory);
		System.out.println("----------------------------------");
		System.out.println("All Approved Schools");
		System.out.println("----------------------------------");
		reportsManager.approvedSchools(sessionFactory);
		System.out.println("----------------------------------");
		System.out.println("All Approved Courses in Each School");
		System.out.println("----------------------------------");
		reportsManager.approvedCoursesInSchool(sessionFactory);
		System.out.println("----------------------------------");
		System.out.println("All Persons");
		System.out.println("----------------------------------");
		reportsManager.allPersons(sessionFactory);
		System.out.println("----------------------------------");
		System.out.println("Person Details");
		System.out.println("----------------------------------");
		reportsManager.personDetails(sessionFactory, "Narendra ");
		System.out.println("----------------------------------");
		System.out.println("All Person Details");
		System.out.println("----------------------------------");
		reportsManager.allPersonDetails(sessionFactory);
		System.out.println("----------------------------------");
		sessionFactory.close();

	}

}
