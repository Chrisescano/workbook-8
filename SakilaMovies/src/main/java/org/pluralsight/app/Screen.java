package org.pluralsight.app;

import org.pluralsight.data.DataManager;
import org.pluralsight.data.SakilaDatabase;
import org.pluralsight.model.Actor;
import org.pluralsight.model.Film;

import java.util.*;

public class Screen {
    Scanner scanner;

    public Screen() {
        scanner = new Scanner(System.in);
    }

    public void run() {
        System.out.print("Type in the first name of an actor: ");
        String actorFN = scanner.nextLine();
        System.out.print("Type in the last name of an actor: ");
        String actorLN = scanner.nextLine();

        //display list of actors
        SakilaDatabase sakilaDatabase = new SakilaDatabase();
        DataManager dataManager = new DataManager(sakilaDatabase.dataSource);
        ArrayList<Actor> actors = dataManager.getActorByLastName(actorFN, actorLN);

        //displaying all actors with userInput last name
        System.out.println("Here is a list of actors with name " + actorFN + " " + actorLN);
        for (Actor actor : actors)
            System.out.println(actor);

        //fix code to query for fn and ln

        //searches actor by first name
        Actor actorToQuery = null;
        for (Actor actor : actors)
            if (actor.getFirstName().equalsIgnoreCase(actorFN)) actorToQuery = actor;

        if (actorToQuery != null) {
            ArrayList<Film> films = dataManager.getFilmsByActor(actorToQuery);
            for (Film film : films)
                System.out.println(film);
        } else {
            System.out.println("Sorry, that is not a valid actor");
        }
    }
}
