package drakonli.dota2.hero_grid_customizer.domain.services.hero_grid_customization.impl.storage;

import drakonli.dota2.hero_grid_customizer.domain.models.HeroGridCustomization;
import drakonli.dota2.hero_grid_customizer.domain.models.HeroNameCustomization;
import drakonli.dota2.hero_grid_customizer.domain.services.hero_grid_customization.StorageException;
import drakonli.jcomponents.file.IByNameFileFactory;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.*;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class HeroGridCustomizationByFileStorageTest
{
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Mock
    File fileMock;

    String fileName = "someStorageFile.txt";

    @Mock
    IByNameFileFactory byNameFileFactoryMock;

    protected HeroGridCustomizationByFileStorage testedStorage;

    @Before
    public void setUp()
    {
        this.testedStorage = new HeroGridCustomizationByFileStorage(this.fileName, this.byNameFileFactoryMock);
    }

    @Test
    public void store() throws Exception
    {
        File expectedFile = this.folder.newFile(this.fileName);

        assertTrue(expectedFile.exists());

        when(this.byNameFileFactoryMock.create(this.fileName)).thenReturn(expectedFile);

        HeroGridCustomization expectedHeroGridCustomization = new HeroGridCustomization("someName");
        List<HeroNameCustomization> heroNameCustomizations = expectedHeroGridCustomization.getHeroNameCustomizations();
        heroNameCustomizations.add(new HeroNameCustomization("someUid", "someName"));
        heroNameCustomizations.add(new HeroNameCustomization("someUid1", "someName1"));
        heroNameCustomizations.add(new HeroNameCustomization("someUid2", "someName2"));

        this.testedStorage.store(expectedHeroGridCustomization);

        assertTrue(expectedFile.isFile());

        HeroGridCustomization actualHeroGridCustomization = this.unserializeFile(expectedFile);

        assertTrue(EqualsBuilder.reflectionEquals(
                expectedHeroGridCustomization.getHeroNameCustomizations(),
                actualHeroGridCustomization.getHeroNameCustomizations()
        ));

        assertTrue(EqualsBuilder.reflectionEquals(
                expectedHeroGridCustomization,
                actualHeroGridCustomization,
                new String[]{"heroNameCustomizations"}
        ));

        assertTrue(expectedFile.delete());
    }

    @Test(expected = StorageException.class)
    public void storeWithException() throws StorageException
    {
        this.testedStorage.store(new HeroGridCustomization("someName"));
    }

    @Test
    public void testGetLatest() throws Exception
    {
        File expectedFile = this.folder.newFile(this.fileName);

        HeroGridCustomization expectedHeroGridCustomization = new HeroGridCustomization("someName");
        List<HeroNameCustomization> heroNameCustomizations = expectedHeroGridCustomization.getHeroNameCustomizations();
        heroNameCustomizations.add(new HeroNameCustomization("someUid", "someName"));
        heroNameCustomizations.add(new HeroNameCustomization("someUid1", "someName1"));
        heroNameCustomizations.add(new HeroNameCustomization("someUid2", "someName2"));

        when(this.byNameFileFactoryMock.create(this.fileName)).thenReturn(expectedFile);

        this.serializeAndSaveHeroGridCustomization(expectedHeroGridCustomization, expectedFile);

        Optional<HeroGridCustomization> actualHeroGridCustomization = this.testedStorage.getLatest();

        assertTrue(actualHeroGridCustomization.isPresent());

        assertTrue(EqualsBuilder.reflectionEquals(
                expectedHeroGridCustomization.getHeroNameCustomizations(),
                actualHeroGridCustomization.get().getHeroNameCustomizations()
        ));

        assertTrue(EqualsBuilder.reflectionEquals(
                expectedHeroGridCustomization,
                actualHeroGridCustomization.get(),
                new String[]{"heroNameCustomizations"}
        ));

        assertTrue(expectedFile.delete());
    }

    @Test
    public void testGetLatestEmptyResult() throws StorageException
    {
        when(this.fileMock.isFile()).thenReturn(false);
        when(this.byNameFileFactoryMock.create(this.fileName)).thenReturn(this.fileMock);

        Optional<HeroGridCustomization> heroGridCustomization = this.testedStorage.getLatest();

        assertFalse(heroGridCustomization.isPresent());
    }

//    @Test
//    public void testGetLatestException() throws IOException
//    {
//        HeroGridCustomizationByFileStorage testedStorage =
//                new HeroGridCustomizationByFileStorage("directoryName", this.byNameFileFactoryMock);
//
//        File expectedFile = this.folder.newFolder("directoryName");
//
//        when(this.byNameFileFactoryMock.create("directoryName")).thenReturn(expectedFile);
//
//        try {
//            testedStorage.getLatest();
//
//            fail("Expected" + StorageException.class);
//        } catch (StorageException e) {
//            assertEquals("Storage error", e.getMessage());
//        }
//    }

    protected void serializeAndSaveHeroGridCustomization(HeroGridCustomization heroGridCustomization, File file)
            throws Exception
    {
        FileOutputStream fileOut = new FileOutputStream(file);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(heroGridCustomization);
        out.close();
        fileOut.close();
    }

    protected HeroGridCustomization unserializeFile(File file) throws Exception
    {
        FileInputStream fileIn = new FileInputStream(file);
        ObjectInputStream in = new ObjectInputStream(fileIn);
        HeroGridCustomization heroGridCustomization = (HeroGridCustomization) in.readObject();
        in.close();
        fileIn.close();

        return heroGridCustomization;
    }
}