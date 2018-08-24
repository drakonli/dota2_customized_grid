package drakonli.dota2.hero_grid_customizer.domain.services.hero_grid_customization.customization_exporter.decorator;

import drakonli.dota2.hero_grid_customizer.domain.models.HeroGridCustomization;
import drakonli.dota2.hero_grid_customizer.domain.services.hero_grid_customization.customization_exporter.IHeroGridCustomizationToFileExporter;
import drakonli.dota2.hero_grid_customizer.domain.services.hero_grid_customization.customization_exporter.exception.ExportException;
import drakonli.dota2.hero_grid_customizer.domain.services.hero_grid_customization.storage.IHeroGridCustomizationStorage;
import drakonli.dota2.hero_grid_customizer.domain.services.hero_grid_customization.storage.StorageException;
import drakonli.jcomponents.file.exception.InvalidFileFormatException;

import java.io.File;
import java.io.IOException;

public class StoreHeroGridCustomizationDecorator extends HeroGridCustomizationToFileExporterAbstractDecorator
{
    private IHeroGridCustomizationStorage heroGridCustomizationStorage;

    public StoreHeroGridCustomizationDecorator(
            IHeroGridCustomizationToFileExporter heroGridCustomizationToFileExporter,
            IHeroGridCustomizationStorage heroGridCustomizationStorage
    )
    {
        super(heroGridCustomizationToFileExporter);

        this.heroGridCustomizationStorage = heroGridCustomizationStorage;
    }

    @Override
    public void export(File file, HeroGridCustomization heroGridCustomization)
            throws InvalidFileFormatException, IOException, ExportException
    {
        super.export(file, heroGridCustomization);

        try {
            this.heroGridCustomizationStorage.store(heroGridCustomization);
        } catch (StorageException e) {
            throw new ExportException(e);
        }
    }
}
