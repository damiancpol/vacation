package wit;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.SessionFactoryBuilder;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;


public class Hibernateutil {

	
	
	
	public static SessionFactory session() {
		
		StandardServiceRegistry build = new StandardServiceRegistryBuilder().configure().build();
	MetadataSources metadataSources = new MetadataSources(build);
		Metadata build2 = metadataSources.getMetadataBuilder().build();
	SessionFactory build3 = build2.getSessionFactoryBuilder().build();
		return build3;
	}
}
