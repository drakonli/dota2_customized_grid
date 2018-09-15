package drakonli.dota2.hero_grid_customizer.domain.repositories;

import drakonli.dota2.hero_grid_customizer.domain.models.HeroGridCustomization;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class HeroGridCustomizationHibernateRepository implements IHeroGridCustomizationRepository
{
    private SessionFactory sessionFactory;

    public HeroGridCustomizationHibernateRepository(SessionFactory sessionFactory)
    {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<HeroGridCustomization> findByName(String name)
    {
        Session session = this.sessionFactory.getCurrentSession();
        String query = "from HeroGridCustomization hgc where hgc.name = :name";

        return session.createQuery(query, HeroGridCustomization.class)
                      .setParameter("name", name)
                      .list();
    }

    @Override
    public void deleteList(List<HeroGridCustomization> customizations)
    {
        Session session = this.sessionFactory.getCurrentSession();

        for (HeroGridCustomization customization : customizations) {
            session.delete(customization);
        }
    }

    @Override
    public void save(HeroGridCustomization customization)
    {
        this.sessionFactory.getCurrentSession().save(customization);
    }

    @Override
    public Optional<HeroGridCustomization> findLatest()
    {
        Session session = this.sessionFactory.getCurrentSession();
        String query = "from HeroGridCustomization hgc ORDER by hgc.createDate DESC";

        return session.createQuery(query, HeroGridCustomization.class).list().stream().findFirst();
    }
}
