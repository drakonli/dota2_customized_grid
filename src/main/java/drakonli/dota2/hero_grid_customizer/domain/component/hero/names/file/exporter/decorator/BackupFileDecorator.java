package drakonli.dota2.hero_grid_customizer.domain.component.hero.names.file.exporter.decorator;

import drakonli.dota2.hero_grid_customizer.domain.model.HeroTranslation;
import drakonli.dota2.hero_grid_customizer.domain.services.IHeroGridConfigToFileExporter;
import drakonli.jcomponents.file.backuper.FileBackuper;
import drakonli.jcomponents.file.exception.InvalidFileFormatException;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class BackupFileDecorator extends HeroGridConfigToFileExporterAbstractDecorator
{
    private FileBackuper backuper;

    public BackupFileDecorator(
            IHeroGridConfigToFileExporter heroGridConfigToFileExporter,
            FileBackuper backuper
    )
    {
        super(heroGridConfigToFileExporter);

        this.backuper = backuper;
    }

    @Override
    public void export(
            File file, List<HeroTranslation> heroTranslations
    ) throws InvalidFileFormatException, IOException
    {
        super.export(file, heroTranslations);

        this.backuper.backup(file);
    }
}
