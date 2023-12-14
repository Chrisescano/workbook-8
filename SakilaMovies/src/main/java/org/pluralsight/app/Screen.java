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
        System.out.println("Here is a list of actors with name: " + actorFN + " " + actorLN);
        for (Actor actor : actors)
            System.out.println(actor);

        //searches films actor in by actor id
        System.out.println("Type in the actor ID to display movies: ");
        int actorID = scanner.nextInt();
        scanner.nextLine(); //clear buffer

        //searches actor based on actor id
        Actor actorToQuery = null;
        for (Actor actor : actors)
            if (actorID == actor.getActorID()) actorToQuery = actor;

        //query films of actor
        ArrayList<Film> films;
        if (actorToQuery != null) {
            films = dataManager.getFilmsByActor(actorToQuery);
        } else {
            System.out.println("Sorry, That is not a valid actor");
            return;
        }

        //display films actor was in
        for (Film film : films)
            System.out.println(film);
    }
}
