package drakonli.dota2.hero_grid_customizer.domain.services;

import drakonli.dota2.hero_grid_customizer.domain.model.HeroTranslation;
import drakonli.jcomponents.file.exception.InvalidFileFormatException;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface IHeroGridConfigToFileExporter
{
    public void export(File file, List<HeroTranslation> heroTranslations)
            throws InvalidFileFormatException, IOException;
}
