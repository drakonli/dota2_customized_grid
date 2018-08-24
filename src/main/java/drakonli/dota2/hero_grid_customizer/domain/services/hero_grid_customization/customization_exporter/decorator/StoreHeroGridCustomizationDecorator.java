package drakonli.dota2.hero_grid_customizer.domain.services.hero_grid_customization.customization_exporter.decorator;

import drakonli.dota2.hero_grid_customizer.domain.model.HeroGridCustomization;
import drakonli.dota2.hero_grid_customizer.domain.services.hero_grid_customization.customization_exporter.IHeroGridCustomizationToFileExporter;
import drakonli.dota2.hero_grid_customizer.domain.services.hero_grid_customization.customization_exporter.exception.ExportException;
import drakonli.dota2.hero_grid_customizer.domain.services.hero_grid_customization.storage.HeroGridCustomizationByFileStorage;
import drakonli.dota2.hero_grid_customizer.domain.services.hero_grid_customization.storage.StorageException;
import drakonli.jcomponents.file.exception.InvalidFileFormatException;

import java.io.File;
import java.io.IOException;

public class StoreHeroGridCustomizationDecorator extends HeroGridCustomizationToFileExporterAbstractDecorator
{
    private HeroGridCustomizationByFileStorage heroGridCustomizationByFileStorage;

    public StoreHeroGridCustomizationDecorator(
            IHeroGridCustomizationToFileExporter heroGridCustomizationToFileExporter,
            HeroGridCustomizationByFileStorage heroGridCustomizationByFileStorage
    )
    {
        super(heroGridCustomizationToFileExporter);

        this.heroGridCustomizationByFileStorage = heroGridCustomizationByFileStorage;
    }

    @Override
    public void export(File file, HeroGridCustomization heroGridCustomization)
            throws InvalidFileFormatException, IOException, ExportException
    {
        super.export(file, heroGridCustomization);

        try {
            this.heroGridCustomizationByFileStorage.store(heroGridCustomization);
        } catch (StorageException e) {
            throw new ExportException(e);
        }
    }
}
