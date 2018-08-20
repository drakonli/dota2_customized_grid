package drakonli.dota2.hero_grid_customizer.domain.component.hero.names.file.exporter.decorator;

import drakonli.dota2.hero_grid_customizer.domain.model.HeroTranslation;
import drakonli.dota2.hero_grid_customizer.domain.services.IHeroGridConfigToFileExporter;
import drakonli.jcomponents.file.exception.InvalidFileFormatException;

import java.io.File;
import java.io.IOException;
import java.util.List;

abstract class HeroGridConfigToFileExporterAbstractDecorator implements IHeroGridConfigToFileExporter
{
    private IHeroGridConfigToFileExporter heroGridConfigToFileExporter;

    public HeroGridConfigToFileExporterAbstractDecorator(
            IHeroGridConfigToFileExporter heroGridConfigToFileExporter
    )
    {
        this.heroGridConfigToFileExporter = heroGridConfigToFileExporter;
    }

    @Override
    public void export(File file, List<HeroTranslation> heroTranslations)
            throws InvalidFileFormatException, IOException
    {
        this.heroGridConfigToFileExporter.export(file, heroTranslations);
    }
}