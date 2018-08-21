package drakonli.dota2.hero_grid_customizer.domain.component.hero.names.file.exporter.decorator;

import drakonli.dota2.hero_grid_customizer.domain.component.hero.names.file.storage.HeroNamesByFileStorage;
import drakonli.dota2.hero_grid_customizer.domain.model.HeroTranslation;
import drakonli.dota2.hero_grid_customizer.domain.services.IHeroGridConfigToFileExporter;
import drakonli.jcomponents.file.exception.InvalidFileFormatException;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class StoreHeroTranslationsDecorator extends HeroGridConfigToFileExporterAbstractDecorator
{
    private HeroNamesByFileStorage heroNamesByFileStorage;

    public StoreHeroTranslationsDecorator(
            IHeroGridConfigToFileExporter heroGridConfigToFileExporter,
            HeroNamesByFileStorage heroNamesByFileStorage
    )
    {
        super(heroGridConfigToFileExporter);

        this.heroNamesByFileStorage = heroNamesByFileStorage;
    }

    @Override
    public void export(File file, List<HeroTranslation> heroTranslationsToExport)
            throws InvalidFileFormatException, IOException
    {
        super.export(file, heroTranslationsToExport);

        this.heroNamesByFileStorage.store(heroTranslationsToExport);
    }
}
