package drakonli.dota2.hero_grid_customizer.application.action.config_import.latest_save;

import drakonli.dota2.hero_grid_customizer.application.view_model.models.HeroTranslationViewModel;
import drakonli.dota2.hero_grid_customizer.application.view_model.services.HeroTranslationsToViewModelMapper;
import drakonli.dota2.hero_grid_customizer.domain.component.hero.names.file.storage.HeroNamesByFileStorage;
import drakonli.dota2.hero_grid_customizer.domain.component.hero.names.file.storage.StorageException;
import drakonli.dota2.hero_grid_customizer.domain.model.HeroNameCustomization;

import java.util.List;

public class ImportConfigFromStorageByLatestSaveAction implements IImportConfigByLatestSaveAction
{
    private final HeroNamesByFileStorage storage;
    private final HeroTranslationsToViewModelMapper heroTranslationsToViewModelMapper;

    public ImportConfigFromStorageByLatestSaveAction(
            HeroNamesByFileStorage storage,
            HeroTranslationsToViewModelMapper heroTranslationsToViewModelMapper
    )
    {
        this.storage = storage;
        this.heroTranslationsToViewModelMapper = heroTranslationsToViewModelMapper;
    }

    @Override
    public void importConfig(List<HeroTranslationViewModel> heroTranslationViewModelsToImportInto)
            throws StorageException, LastVersionOfHeroNamesIsEmptyException
    {
        List<HeroNameCustomization> heroNameCustomizations = this.storage.getLatest();

        if (heroNameCustomizations.isEmpty()) {
            throw new LastVersionOfHeroNamesIsEmptyException();
        }

        heroTranslationViewModelsToImportInto.clear();

        this.heroTranslationsToViewModelMapper.map(heroNameCustomizations, heroTranslationViewModelsToImportInto);
    }
}
