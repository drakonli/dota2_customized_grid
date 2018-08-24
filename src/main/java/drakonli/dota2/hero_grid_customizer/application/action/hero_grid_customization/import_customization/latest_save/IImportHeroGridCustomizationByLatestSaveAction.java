package drakonli.dota2.hero_grid_customizer.application.action.hero_grid_customization.import_customization.latest_save;

import drakonli.dota2.hero_grid_customizer.application.action.ApplicationActionException;
import drakonli.dota2.hero_grid_customizer.application.models.HeroNameCustomizationVM;
import drakonli.dota2.hero_grid_customizer.domain.services.hero_grid_customization.storage.StorageException;

import java.util.List;

public interface IImportHeroGridCustomizationByLatestSaveAction
{
    public void importCustomization(List<HeroNameCustomizationVM> heroNameCustomizationVMListToImportInto)
            throws StorageException, LastVersionOfHeroGridCustomizationIsEmptyException, ApplicationActionException;
}
