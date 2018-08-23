package drakonli.dota2.hero_grid_customizer.application.action.config_import.latest_save;

import drakonli.dota2.hero_grid_customizer.application.view_model.models.HeroTranslationViewModel;
import drakonli.dota2.hero_grid_customizer.application.view_model.services.HeroGridCustomizationToViewModelsMapper;
import drakonli.dota2.hero_grid_customizer.domain.component.hero.names.file.storage.HeroGridCustomizationByFileStorage;
import drakonli.dota2.hero_grid_customizer.domain.component.hero.names.file.storage.StorageException;
import drakonli.dota2.hero_grid_customizer.domain.model.HeroGridCustomization;

import java.util.List;

public class ImportConfigFromStorageByLatestSaveAction implements IImportConfigByLatestSaveAction
{
    private final HeroGridCustomizationByFileStorage storage;
    private final HeroGridCustomizationToViewModelsMapper heroGridCustomizationToViewModelsMapper;

    public ImportConfigFromStorageByLatestSaveAction(
            HeroGridCustomizationByFileStorage storage,
            HeroGridCustomizationToViewModelsMapper heroGridCustomizationToViewModelsMapper
    )
    {
        this.storage = storage;
        this.heroGridCustomizationToViewModelsMapper = heroGridCustomizationToViewModelsMapper;
    }

    @Override
    public void importConfig(List<HeroTranslationViewModel> heroTranslationViewModelsToImportInto)
            throws StorageException, LastVersionOfHeroGridCustomizationIsEmptyException
    {
        HeroGridCustomization heroGridCustomization = this.storage.getLatest();

        if (heroGridCustomization.isEmpty()) {
            throw new LastVersionOfHeroGridCustomizationIsEmptyException();
        }

        this.heroGridCustomizationToViewModelsMapper
                .map(heroGridCustomization, heroTranslationViewModelsToImportInto);
    }
}
