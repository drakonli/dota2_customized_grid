package drakonli.dota2.hero_grid_customizer.domain.services.hero_grid_customization.storage;

import drakonli.dota2.hero_grid_customizer.domain.models.HeroGridCustomization;

import java.io.*;

public class HeroGridCustomizationByFileStorage implements IHeroGridCustomizationStorage
{
    private final String heroGridCustomizationSaveFileName;

    public HeroGridCustomizationByFileStorage(String heroGridCustomizationSaveFileName)
    {
        this.heroGridCustomizationSaveFileName = heroGridCustomizationSaveFileName;
    }

    @Override
    public void store(HeroGridCustomization heroGridCustomization) throws StorageException
    {
        try {
            File heroSavesFile = new File(this.heroGridCustomizationSaveFileName);
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
    public HeroGridCustomization getLatest() throws StorageException
    {
        File heroNamesSaveFile = new File(this.heroGridCustomizationSaveFileName);

        if (!heroNamesSaveFile.isFile()) {
            throw new StorageException(new FileNotFoundException());
        }

        try {
            FileInputStream fileIn = new FileInputStream(this.heroGridCustomizationSaveFileName);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            HeroGridCustomization heroGridCustomization = (HeroGridCustomization) in.readObject();
            in.close();
            fileIn.close();

            return heroGridCustomization;
        } catch (Exception e) {
            throw new StorageException(e);
        }
    }
}
