package drakonli.dota2.hero_grid_customizer.domain.component.hero.names.file.storage;

import drakonli.dota2.hero_grid_customizer.domain.model.HeroGridCustomization;

import java.io.*;

public class HeroGridCustomizationByFileStorage
{
    private static final String HERO_NAMES_SAVE_FILENAME = "hero_names_save.txt";

    public void store(HeroGridCustomization heroGridCustomization) throws StorageException
    {
        try {
            File heroSavesFile = new File(HERO_NAMES_SAVE_FILENAME);
            FileOutputStream fileOut = new FileOutputStream(heroSavesFile);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(heroGridCustomization);
            out.close();
            fileOut.close();
        } catch (Exception e)
        {
            throw new StorageException(e);
        }
    }

    public HeroGridCustomization getLatest() throws StorageException
    {
        File heroNamesSaveFile = new File(HERO_NAMES_SAVE_FILENAME);

        if (!heroNamesSaveFile.isFile()) {
            throw new StorageException(new FileNotFoundException());
        }

        try {
            FileInputStream fileIn = new FileInputStream(HERO_NAMES_SAVE_FILENAME);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            HeroGridCustomization heroGridCustomization = (HeroGridCustomization) in.readObject();
            in.close();
            fileIn.close();

            return heroGridCustomization;
        } catch (Exception e)
        {
            throw new StorageException(e);
        }
    }
}
