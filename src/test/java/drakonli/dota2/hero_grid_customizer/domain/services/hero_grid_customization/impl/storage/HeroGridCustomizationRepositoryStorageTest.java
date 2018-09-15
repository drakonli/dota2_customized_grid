package drakonli.dota2.hero_grid_customizer.domain.services.hero_grid_customization.impl.storage;

import drakonli.dota2.hero_grid_customizer.domain.models.HeroGridCustomization;
import drakonli.dota2.hero_grid_customizer.domain.repository.IHeroGridCustomizationRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class HeroGridCustomizationRepositoryStorageTest
{
    @Mock
    IHeroGridCustomizationRepository repositoryMock;
    @Mock
    HeroGridCustomization            heroGridCustomizationMock;

    protected HeroGridCustomizationRepositoryStorage testedStorage;

    @Before
    public void setUp() throws Exception
    {
        this.testedStorage = new HeroGridCustomizationRepositoryStorage(this.repositoryMock);
    }

    @Test
    public void store()
    {
        String name = "someName";

        List<HeroGridCustomization> expectedList = new ArrayList<>();
        expectedList.add(new HeroGridCustomization("someGridName"));

        when(this.heroGridCustomizationMock.getName()).thenReturn(name);
        when(this.repositoryMock.findByName(name)).thenReturn(expectedList);

        this.testedStorage.store(this.heroGridCustomizationMock);

        verify(this.repositoryMock).deleteList(expectedList);
        verify(this.repositoryMock).save(this.heroGridCustomizationMock);
    }

    @Test
    public void storeWithoutPreviousList()
    {
        String name = "someName";

        List<HeroGridCustomization> expectedList = new ArrayList<>();

        when(this.heroGridCustomizationMock.getName()).thenReturn(name);
        when(this.repositoryMock.findByName(name)).thenReturn(expectedList);

        this.testedStorage.store(this.heroGridCustomizationMock);

        verify(this.repositoryMock, never()).deleteList(any());
        verify(this.repositoryMock).save(this.heroGridCustomizationMock);
    }

    @Test
    public void getLatest()
    {
        this.testedStorage.getLatest();

        verify(this.repositoryMock).findLatest();
    }
}