/**
 * 
 */
package demo;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import demo.dao.MakeSchoolsAndCourses;

/**
 * @author Arockia
 *
 */
public class HRBookEntry {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

		MakeSchoolsAndCourses makeSchoolsAndCourses = new MakeSchoolsAndCourses();
		makeSchoolsAndCourses.makeCourses(sessionFactory);
		makeSchoolsAndCourses.buildSchools(sessionFactory);
		makeSchoolsAndCourses.makeAdmission(sessionFactory);
		sessionFactory.close();
	}

}
