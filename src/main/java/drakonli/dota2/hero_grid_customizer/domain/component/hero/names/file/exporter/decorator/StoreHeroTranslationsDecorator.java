package drakonli.dota2.hero_grid_customizer.domain.component.hero.names.file.exporter.decorator;

import drakonli.dota2.hero_grid_customizer.domain.component.hero.names.file.storage.HeroNamesGridCustomizationByFileStorage;
import drakonli.dota2.hero_grid_customizer.domain.component.hero.names.file.storage.StorageException;
import drakonli.dota2.hero_grid_customizer.domain.model.HeroNamesGridCustomization;
import drakonli.dota2.hero_grid_customizer.domain.services.IHeroGridConfigToFileExporter;
import drakonli.dota2.hero_grid_customizer.domain.services.export.ExportException;
import drakonli.jcomponents.file.exception.InvalidFileFormatException;

import java.io.File;
import java.io.IOException;

public class StoreHeroTranslationsDecorator extends HeroGridConfigToFileExporterAbstractDecorator
{
    private HeroNamesGridCustomizationByFileStorage heroNamesGridCustomizationByFileStorage;

    public StoreHeroTranslationsDecorator(
            IHeroGridConfigToFileExporter heroGridConfigToFileExporter,
            HeroNamesGridCustomizationByFileStorage heroNamesGridCustomizationByFileStorage
    )
    {
        super(heroGridConfigToFileExporter);

        this.heroNamesGridCustomizationByFileStorage = heroNamesGridCustomizationByFileStorage;
    }

    @Override
    public void export(File file, HeroNamesGridCustomization heroNamesGridCustomization)
            throws InvalidFileFormatException, IOException, ExportException
    {
        super.export(file, heroNamesGridCustomization);

        try {
            this.heroNamesGridCustomizationByFileStorage.store(heroNamesGridCustomization);
        } catch (StorageException e) {
            throw new ExportException(e);
        }
    }
}
