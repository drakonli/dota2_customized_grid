package drakonli.dota2.hero_grid_customizer.domain.services.dota2_translations_file.impl;

import drakonli.dota2.hero_grid_customizer.domain.models.HeroGridCustomization;
import drakonli.dota2.hero_grid_customizer.domain.services.dota2_translations_file.IHeroNameCustomizationByLineExtractor;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class Dota2HeroTranslationsLineByGridCustomizationEditorFactoryTest
{
    @Mock
    IHeroNameCustomizationByLineExtractor extractorMock;

    @Mock
    HeroGridCustomization heroNameCustomizationMock;

    @Mock
    HeroGridCustomization anotherHeroNameCustomizationMock;

    @Test
    public void create()
    {
        Dota2HeroTranslationsLineByGridCustomizationEditor expectedEditor =
                new Dota2HeroTranslationsLineByGridCustomizationEditor(
                        this.heroNameCustomizationMock,
                        this.extractorMock
                );

        Dota2HeroTranslationsLineByGridCustomizationEditor actualEditor
                = (new Dota2HeroTranslationsLineByGridCustomizationEditorFactory(this.extractorMock))
                .create(this.heroNameCustomizationMock);

        Assert.assertTrue(EqualsBuilder.reflectionEquals(expectedEditor, actualEditor));

        actualEditor = (new Dota2HeroTranslationsLineByGridCustomizationEditorFactory(this.extractorMock))
                .create(this.anotherHeroNameCustomizationMock);

        Assert.assertFalse(EqualsBuilder.reflectionEquals(expectedEditor, actualEditor));
    }
}