package drakonli.dota2.hero_grid_customizer.application.action.config_import.latest_save;

import drakonli.dota2.hero_grid_customizer.application.view_model.models.HeroTranslationViewModel;
import drakonli.dota2.hero_grid_customizer.application.view_model.services.HeroNamesGridCustomizationToViewModelMapper;
import drakonli.dota2.hero_grid_customizer.domain.component.hero.names.file.storage.HeroNamesGridCustomizationByFileStorage;
import drakonli.dota2.hero_grid_customizer.domain.component.hero.names.file.storage.StorageException;
import drakonli.dota2.hero_grid_customizer.domain.model.HeroNamesGridCustomization;

import java.util.List;

public class ImportConfigFromStorageByLatestSaveAction implements IImportConfigByLatestSaveAction
{
    private final HeroNamesGridCustomizationByFileStorage storage;
    private final HeroNamesGridCustomizationToViewModelMapper heroNamesGridCustomizationToViewModelMapper;

    public ImportConfigFromStorageByLatestSaveAction(
            HeroNamesGridCustomizationByFileStorage storage,
            HeroNamesGridCustomizationToViewModelMapper heroNamesGridCustomizationToViewModelMapper
    )
    {
        this.storage = storage;
        this.heroNamesGridCustomizationToViewModelMapper = heroNamesGridCustomizationToViewModelMapper;
    }

    @Override
    public void importConfig(List<HeroTranslationViewModel> heroTranslationViewModelsToImportInto)
            throws StorageException, LastVersionOfHeroNamesGridCustomizationIsEmptyException
    {
        HeroNamesGridCustomization heroNamesGridCustomization = this.storage.getLatest();

        if (heroNamesGridCustomization.isEmpty()) {
            throw new LastVersionOfHeroNamesGridCustomizationIsEmptyException();
        }

        this.heroNamesGridCustomizationToViewModelMapper
                .map(heroNamesGridCustomization, heroTranslationViewModelsToImportInto);
    }
}
