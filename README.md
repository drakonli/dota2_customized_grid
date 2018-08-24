# dota2_hero_grid_customizer

A little program I wrote myself to make it easy to customize hero grid in dota2 version >7.00

Check out https://www.youtube.com/watch?v=U5hgxPfecv4 to learn more

Main task of this program is to Customize Dota2 Heroes Grid.

Main models:
1. HeroGridCustomization or just GridCustomization - contains HeroNameCustomizations.
2. HeroNameCustomization - contains all the information needed to customize a node of hero grid

Concepts and frameworks:
* DDD (Layered Architecture)
* MVVM (my own implementation)
* IoC (spring)
* JavaFX/FXML (I loved FXML in that it separates the work of a frontend and a backend developer)
* Maven

Every layer is separated to it's own package:

UI - layer that consists of FXML files and View files. View files has 2 concerns: 1. bind View component changes
to changes in ViewModels (if a table is empty, so should some ViewModel be empty); 2. receive and route user actions to
Application layer.

Application - this holds the application state. Consists of Actions (MVC controller-action would be the analogue),
which are executed from View layer, ViewModels and "helper" classes. Only cares about routing different user actions
to Domain layer components and binding Domain Models to ViewModels.

Domain - consists of services and models that are directly involved in main tasks of the application. This layer
concerns itself with actually doing the job that is required: loading data, saving data, backing-up, etc. Domain layer
only works with domain model, not the ViewModel.

Infrastructure: Spring IoC, Maven, JavaFX and my custom "jcomponents" package for general utilities.

Also, about my vision of MVVM:

I see ViewModels as a "glue" between UI/Application and Domain Layer. Application and UI layer concerns itself with
changing ViewModels, Application concerns itself with invoking appropriate Domain services when user actions are
received, but never is ViewModel passed to Domain services. Only Application and UI are aware of ViewModels.
