package swingy.model.save;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateDB
{
	SessionFactory sessionFactory;

	public HibernateDB()
	{
		StandardServiceRegistry	standardRegistry;

		standardRegistry = new StandardServiceRegistryBuilder()
							.configure()
							.build();

		MetadataSources	sources;

		sources = new MetadataSources( standardRegistry );

		sources.addAnnotatedClass(SaveData.class);

		Metadata metadata = sources.buildMetadata();
		this.sessionFactory = metadata.buildSessionFactory();
	}

	public void saveToDB(List<SaveData>	saves)
	{
		try (Session session = this.sessionFactory.openSession()) {
			Transaction transaction = session.beginTransaction();

			for (SaveData hero : saves)
				session.merge(hero);

			transaction.commit();
		}
		catch (Exception e)
		{
			System.err.println("Error while saving : " + e.getMessage());
		}
	}

	public List<SaveData>	loadFromDB()
	{
		List<SaveData> saves;
		saves = new ArrayList<>();
		try (Session session = this.sessionFactory.openSession())
		{
			Transaction transaction = session.beginTransaction();
			saves = session.createQuery("FROM SaveData", SaveData.class).getResultList();
			transaction.commit();
		}
		catch (Exception e)
		{
		}
		return (saves);
	}

	public void eraseFromDB(SaveData hero)
	{
		try (Session session = this.sessionFactory.openSession()) {
			Transaction transaction = session.beginTransaction();

			session.remove(hero);

			transaction.commit();
		}
		catch (Exception e)
		{
			System.err.println("Error while deleting save : " + e.getMessage());
		}
	}
}
