# dota2_customized_grid

A little program I wrote myself to make it easy to customize hero grid in dota2 version >7.00

More here https://www.youtube.com/watch?v=U5hgxPfecv4

Concepts and frameworks:
* DDD (Layered Architecture)
* MVVM (my own implementation)
* IoC (spring)
* JavaFX/FXML (I loved FXML in that it separates the work of a frontend and backend developer)
* Maven (also separated infrastructure layer into a separate package)

Every layer is divided in it's own package:

UI - layer that consists of FXML files and View files. View files has 2 concerns: 1. bind View component changes
to changes in ViewModels (if a table is empty, so should some ViewModel be empty); 2. receive and route user actions to
Application layer.

Application - this holds the application state. Consists of ViewHandlers (which are called in View layer), ViewModels
and "helper" classes. Only cares about routing different user actions to Domain layer components.

Domain - consists of services and models that are directly involved in main concerns of the application. Domain layer
concerns itself with actually doing the job that is required: loading data, saving data, backing-up, etc. Domain layer
only works with domain model, not the ViewModel.

Infrastructure (does not have a package, because it's the underlying layer for everything): Spring IoC, Maven, JavaFX.

Also, about my vision of MVVM:

I see ViewModels as a "glue" between UI/Application and Domain Layer. Application and UI layer concerns itself with
changing ViewModels and invoking appropriate Domain services when user actions are received, but never is ViewModel
passed to Domain services. Only Application and UI are aware of ViewModels.
