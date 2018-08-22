package drakonli.dota2.hero_grid_customizer.application.action.config_import.dota_translation_file;

import drakonli.dota2.hero_grid_customizer.application.view_model.models.HeroTranslationViewModel;
import drakonli.dota2.hero_grid_customizer.application.view_model.services.HeroTranslationViewModelsToDomainModelMapper;
import drakonli.dota2.hero_grid_customizer.application.view_model.services.HeroTranslationsToViewModelMapper;
import drakonli.dota2.hero_grid_customizer.domain.model.HeroNameCustomization;
import drakonli.dota2.hero_grid_customizer.domain.services.IHeroGridConfigFromFileImporter;
import drakonli.jcomponents.file.exception.InvalidFileFormatException;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class BasicImportConfigFromFileAction implements IImportConfigFromFileAction
{
    private final IHeroGridConfigFromFileImporter importer;
    private final HeroTranslationViewModelsToDomainModelMapper heroTranslationViewModelsToDomainModelMapper;
    private final HeroTranslationsToViewModelMapper heroTranslationsToViewModelMapper;

    public BasicImportConfigFromFileAction(
            IHeroGridConfigFromFileImporter importer,
            HeroTranslationViewModelsToDomainModelMapper heroTranslationViewModelsToDomainModelMapper,
            HeroTranslationsToViewModelMapper heroTranslationsToViewModelMapper
    )
    {
        this.importer = importer;
        this.heroTranslationViewModelsToDomainModelMapper = heroTranslationViewModelsToDomainModelMapper;
        this.heroTranslationsToViewModelMapper = heroTranslationsToViewModelMapper;
    }

    @Override
    public void importConfig(File file, List<HeroTranslationViewModel> heroTranslationViewModelsToImportInto) throws
            InvalidFileFormatException, IOException
    {
        List<HeroNameCustomization> heroNameCustomizations = this.heroTranslationViewModelsToDomainModelMapper
                .mapToNewEntityList(heroTranslationViewModelsToImportInto);

        this.importer.importHeroNamesByFile(file, heroNameCustomizations);

        this.heroTranslationsToViewModelMapper.map(heroNameCustomizations, heroTranslationViewModelsToImportInto);
    }
}
