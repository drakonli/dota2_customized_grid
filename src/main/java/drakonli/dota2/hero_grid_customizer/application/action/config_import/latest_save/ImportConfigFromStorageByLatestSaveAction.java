package drakonli.dota2.hero_grid_customizer.application.action.config_import.latest_save;

import drakonli.dota2.hero_grid_customizer.application.view_model.models.HeroTranslationViewModel;
import drakonli.dota2.hero_grid_customizer.application.view_model.services.HeroGridCustomizationToViewModelMapper;
import drakonli.dota2.hero_grid_customizer.domain.component.hero.names.file.storage.HeroGridCustomizationByFileStorage;
import drakonli.dota2.hero_grid_customizer.domain.component.hero.names.file.storage.StorageException;
import drakonli.dota2.hero_grid_customizer.domain.model.HeroGridCustomization;

import java.util.List;

public class ImportConfigFromStorageByLatestSaveAction implements IImportConfigByLatestSaveAction
{
    private final HeroGridCustomizationByFileStorage storage;
    private final HeroGridCustomizationToViewModelMapper heroGridCustomizationToViewModelMapper;

    public ImportConfigFromStorageByLatestSaveAction(
            HeroGridCustomizationByFileStorage storage,
            HeroGridCustomizationToViewModelMapper heroGridCustomizationToViewModelMapper
    )
    {
        this.storage = storage;
        this.heroGridCustomizationToViewModelMapper = heroGridCustomizationToViewModelMapper;
    }

    @Override
    public void importConfig(List<HeroTranslationViewModel> heroTranslationViewModelsToImportInto)
            throws StorageException, LastVersionOfHeroGridCustomizationIsEmptyException
    {
        HeroGridCustomization heroGridCustomization = this.storage.getLatest();

        if (heroGridCustomization.isEmpty()) {
            throw new LastVersionOfHeroGridCustomizationIsEmptyException();
        }

        this.heroGridCustomizationToViewModelMapper
                .map(heroGridCustomization, heroTranslationViewModelsToImportInto);
    }
}
