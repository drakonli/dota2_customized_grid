package drakonli.dota2.hero_grid_customizer.domain.component.hero.names.file.exporter.decorator;

import drakonli.dota2.hero_grid_customizer.domain.component.hero.names.file.storage.HeroGridCustomizationByFileStorage;
import drakonli.dota2.hero_grid_customizer.domain.component.hero.names.file.storage.StorageException;
import drakonli.dota2.hero_grid_customizer.domain.model.HeroGridCustomization;
import drakonli.dota2.hero_grid_customizer.domain.services.IHeroGridConfigToFileExporter;
import drakonli.dota2.hero_grid_customizer.domain.services.export.ExportException;
import drakonli.jcomponents.file.exception.InvalidFileFormatException;

import java.io.File;
import java.io.IOException;

public class StoreHeroTranslationsDecorator extends HeroGridConfigToFileExporterAbstractDecorator
{
    private HeroGridCustomizationByFileStorage heroGridCustomizationByFileStorage;

    public StoreHeroTranslationsDecorator(
            IHeroGridConfigToFileExporter heroGridConfigToFileExporter,
            HeroGridCustomizationByFileStorage heroGridCustomizationByFileStorage
    )
    {
        super(heroGridConfigToFileExporter);

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
