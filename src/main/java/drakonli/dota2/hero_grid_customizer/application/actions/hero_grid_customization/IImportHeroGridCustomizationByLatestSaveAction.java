package drakonli.dota2.hero_grid_customizer.application.actions.hero_grid_customization;

import drakonli.dota2.hero_grid_customizer.application.actions.ApplicationActionException;
import drakonli.dota2.hero_grid_customizer.application.actions.hero_grid_customization.impl.import_customization.latest_save.LastVersionOfHeroGridCustomizationIsEmptyException;
import drakonli.dota2.hero_grid_customizer.application.models.HeroNameCustomizationVM;
import drakonli.dota2.hero_grid_customizer.domain.services.hero_grid_customization.StorageException;

import java.util.List;

public interface IImportHeroGridCustomizationByLatestSaveAction
{
    public void importCustomization(List<HeroNameCustomizationVM> heroNameCustomizationVMListToImportInto)
            throws StorageException, LastVersionOfHeroGridCustomizationIsEmptyException, ApplicationActionException;
}
