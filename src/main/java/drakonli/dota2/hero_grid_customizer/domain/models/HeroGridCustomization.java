package drakonli.dota2.hero_grid_customizer.domain.models;

import drakonli.dota2.hero_grid_customizer.domain.services.dota2_translations_file.impl.HeroNameCustomizationSameAsHeroUIDPredicate;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Entity
public class HeroGridCustomization implements Serializable
{
    @Id
    private String name;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "hero_name_customization_id")
    private List<HeroNameCustomization> heroNameCustomizations = new ArrayList<>();

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date")
    private Date createDate = new Date();

    public HeroGridCustomization()
    {
    }

    public HeroGridCustomization(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public Date getCreateDate()
    {
        return createDate;
    }

    public List<HeroNameCustomization> getHeroNameCustomizations()
    {
        return heroNameCustomizations;
    }

    public Optional<HeroNameCustomization> findHeroNameCustomizationByHeroNameUUID(String heroNameUUID)
    {
        return this.heroNameCustomizations
                .stream()
                .filter(
                        new HeroNameCustomizationSameAsHeroUIDPredicate(heroNameUUID)
                )
                .findFirst();
    }
}
