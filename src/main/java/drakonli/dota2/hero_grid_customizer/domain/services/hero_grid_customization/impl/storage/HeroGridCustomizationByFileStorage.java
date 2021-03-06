package drakonli.dota2.hero_grid_customizer.domain.services.hero_grid_customization.impl.storage;

import drakonli.dota2.hero_grid_customizer.domain.models.HeroGridCustomization;
import drakonli.dota2.hero_grid_customizer.domain.services.hero_grid_customization.IHeroGridCustomizationStorage;
import drakonli.dota2.hero_grid_customizer.domain.services.hero_grid_customization.StorageException;
import drakonli.jcomponents.file.IByNameFileFactory;

import java.io.*;
import java.util.Optional;

public class HeroGridCustomizationByFileStorage implements IHeroGridCustomizationStorage
{
    private final String             heroGridCustomizationSaveFileName;
    private final IByNameFileFactory byNameFileFactory;

    public HeroGridCustomizationByFileStorage(
            String heroGridCustomizationSaveFileName,
            IByNameFileFactory byNameFileFactory
    )
    {
        this.heroGridCustomizationSaveFileName = heroGridCustomizationSaveFileName;
        this.byNameFileFactory = byNameFileFactory;
    }

    @Override
    public void store(HeroGridCustomization heroGridCustomization) throws StorageException
    {
        try {
            File heroSavesFile = this.byNameFileFactory.create(this.heroGridCustomizationSaveFileName);
            FileOutputStream fileOut = new FileOutputStream(heroSavesFile);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(heroGridCustomization);
            out.close();
            fileOut.close();
        } catch (Exception e) {
            throw new StorageException(e);
        }
    }

    @Override
    public Optional<HeroGridCustomization> getLatest() throws StorageException
    {
        File heroNamesSaveFile = this.byNameFileFactory.create(this.heroGridCustomizationSaveFileName);

        if (!heroNamesSaveFile.isFile()) {
            return Optional.empty();
        }

        try {
            FileInputStream fileIn = new FileInputStream(heroNamesSaveFile);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            HeroGridCustomization heroGridCustomization = (HeroGridCustomization) in.readObject();
            in.close();
            fileIn.close();

            return Optional.of(heroGridCustomization);
        } catch (Exception e) {
            throw new StorageException(e);
        }
    }
}
