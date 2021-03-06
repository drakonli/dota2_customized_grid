package drakonli.dota2.hero_grid_customizer.domain.services.hero_grid_customization.impl.customization_exporter.decorator;

import drakonli.dota2.hero_grid_customizer.domain.models.HeroGridCustomization;
import drakonli.dota2.hero_grid_customizer.domain.services.hero_grid_customization.IHeroGridCustomizationStorage;
import drakonli.dota2.hero_grid_customizer.domain.services.hero_grid_customization.IHeroGridCustomizationToFileExporter;
import drakonli.dota2.hero_grid_customizer.domain.services.hero_grid_customization.StorageException;
import drakonli.dota2.hero_grid_customizer.domain.services.hero_grid_customization.impl.customization_exporter.exception.ExportException;
import drakonli.jcomponents.file.exception.InvalidFileFormatException;

import java.io.File;
import java.io.IOException;

public class StoreHeroGridCustomizationDecorator extends HeroGridCustomizationToFileExporterAbstractDecorator
{
    private final IHeroGridCustomizationStorage heroGridCustomizationStorage;

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
