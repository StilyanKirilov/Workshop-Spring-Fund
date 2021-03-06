package softuni.workshopjudge2.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.workshopjudge2.model.service.ExerciseServiceModel;
import softuni.workshopjudge2.model.entity.Exercise;
import softuni.workshopjudge2.repository.ExerciseRepository;
import softuni.workshopjudge2.service.ExerciseService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExerciseServiceImpl implements ExerciseService {

    private final ExerciseRepository exerciseRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ExerciseServiceImpl(ExerciseRepository exerciseRepository, ModelMapper modelMapper) {
        this.exerciseRepository = exerciseRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addExercise(ExerciseServiceModel exercise) {
        this.exerciseRepository.saveAndFlush(this.modelMapper.map(exercise, Exercise.class));
    }

    @Override
    public List<String> findAllExNames() {
        return this.exerciseRepository
                .findAll()
                .stream()
                .map(Exercise::getName)
                .collect(Collectors.toList());
    }

    @Override
    public ExerciseServiceModel findByName(String name) {
        return this.exerciseRepository.findByName(name)
                .map(exe -> this.modelMapper.map(exe, ExerciseServiceModel.class))
                .orElse(null);
    }


}
