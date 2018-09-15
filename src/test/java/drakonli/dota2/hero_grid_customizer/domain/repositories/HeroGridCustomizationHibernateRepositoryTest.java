package drakonli.dota2.hero_grid_customizer.domain.repositories;

import drakonli.dota2.hero_grid_customizer.domain.models.HeroGridCustomization;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class HeroGridCustomizationHibernateRepositoryTest
{
    @Mock
    SessionFactory sessionFactoryMock;
    @Mock
    Session        sessionMock;
    @Mock
    Query          query;

    protected HeroGridCustomizationHibernateRepository testedRepository;

    @Before
    public void setUp()
    {
        this.testedRepository = new HeroGridCustomizationHibernateRepository(this.sessionFactoryMock);

        when(this.sessionFactoryMock.getCurrentSession()).thenReturn(this.sessionMock);
    }

    @Test
    public void findByName()
    {
        String queryString = "from HeroGridCustomization hgc where hgc.name = :name";
        String name = "someName";

        List<HeroGridCustomization> expectedList = new ArrayList<>();
        expectedList.add(new HeroGridCustomization("someGridName"));

        when(this.sessionMock.createQuery(queryString, HeroGridCustomization.class)).thenReturn(this.query);
        when(this.query.setParameter("name", name)).thenReturn(this.query);
        when(this.query.list()).thenReturn(expectedList);

        List<HeroGridCustomization> actualList = this.testedRepository.findByName(name);

        assertTrue(EqualsBuilder.reflectionEquals(expectedList, actualList));
    }

    @Test
    public void deleteList()
    {
        HeroGridCustomization expectedHeroGridCustomization = new HeroGridCustomization("someGridName");
        List<HeroGridCustomization> expectedList = new ArrayList<>();
        expectedList.add(expectedHeroGridCustomization);

        this.testedRepository.deleteList(expectedList);

        verify(this.sessionMock).delete(expectedHeroGridCustomization);
    }

    @Test
    public void save()
    {
        HeroGridCustomization expectedHeroGridCustomization = new HeroGridCustomization("someGridName");

        this.testedRepository.save(expectedHeroGridCustomization);

        verify(this.sessionMock).save(expectedHeroGridCustomization);
    }

    @Test
    public void findLatest()
    {
        String queryString = "from HeroGridCustomization hgc ORDER by hgc.createDate DESC";

        HeroGridCustomization expectedHeroGridCustomization = new HeroGridCustomization("someGridName");
        List<HeroGridCustomization> expectedList = new ArrayList<>();
        expectedList.add(expectedHeroGridCustomization);

        when(this.sessionMock.createQuery(queryString, HeroGridCustomization.class)).thenReturn(this.query);
        when(this.query.list()).thenReturn(expectedList);

        Optional<HeroGridCustomization> optionalHeroGridCustomization = this.testedRepository.findLatest();

        assertTrue(optionalHeroGridCustomization.isPresent());
        assertTrue(EqualsBuilder.reflectionEquals(expectedHeroGridCustomization, optionalHeroGridCustomization.get()));
    }

    @Test
    public void findLatestEmptyResult()
    {
        String queryString = "from HeroGridCustomization hgc ORDER by hgc.createDate DESC";

        List<HeroGridCustomization> expectedList = new ArrayList<>();

        when(this.sessionMock.createQuery(queryString, HeroGridCustomization.class)).thenReturn(this.query);
        when(this.query.list()).thenReturn(expectedList);

        Optional<HeroGridCustomization> optionalHeroGridCustomization = this.testedRepository.findLatest();

        assertFalse(optionalHeroGridCustomization.isPresent());
    }
}