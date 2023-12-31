package edu.greenriver.sdev.jokesapi.services;

import edu.greenriver.sdev.jokesapi.db.IJokesRepository;
import edu.greenriver.sdev.jokesapi.model.Joke;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class JokeService {

    private IJokesRepository repo;

    public JokeService(IJokesRepository repo){
        this.repo = repo;
    }
    public List<Joke> getAllJokes() {
        return repo.findAll();
    }

    public Joke getJokeById(int id){
        Optional<Joke> found = repo.findById(id);

        return found.orElse(null);
    }

    public boolean jokeExistsById(int id){
        return getJokeById(id) != null;
    }
    public Joke random(){
        Random random = new Random();
        List<Joke> jokes = getAllJokes();
        return jokes.get(random.nextInt(jokes.size()));
    }

    public Joke addJoke(Joke joke){

        //insert the record
        //jokes.add(joke);
        joke = repo.save(joke);

        //return joke with the new id
        return joke;
    }

    public Joke updateJoke(Joke updatedJoke) {
        Joke savedJoke = getJokeById(updatedJoke.getId());
        savedJoke.setJokeText(updatedJoke.getJokeText());

        //save changes to joke to db
        savedJoke = repo.save(savedJoke);
        return savedJoke;
    }

    public void deleteJoke(int id){
        repo.deleteById(id);
    }




}//end of class
