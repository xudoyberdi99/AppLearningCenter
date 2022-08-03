package com.company.service.serviceImpl;

import com.company.entity.Day;
import com.company.payload.ApiResponse;
import com.company.payload.DayDto;
import com.company.repository.DayRepository;
import com.company.service.DayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DayServiceImpl implements DayService {
    @Autowired
    DayRepository dayRepository;
    @Override
    public List<Day> getAllDay() {
        return dayRepository.findAll();
    }

    @Override
    public Day getByDayId(Integer id) {
        Optional<Day> optionalDay = dayRepository.findById(id);
        return optionalDay.orElse(new Day());
    }

    @Override
    public ApiResponse addDay(DayDto dayDto) {
        boolean existsByName = dayRepository.existsByName(dayDto.getName());
        if (existsByName){
            return new ApiResponse("already exist name day",false);
        }
         Day day=new Day();
        day.setName(dayDto.getName());

        dayRepository.save(day);
        return new ApiResponse("add day succes",true);
    }

    @Override
    public ApiResponse editDay(Integer id, DayDto dayDto) {
        boolean exists = dayRepository.existsByNameAndIdNot(dayDto.getName(), id);
        if (exists){
            return new ApiResponse("already exist name day",false);
        }
        Optional<Day> optionalDay = dayRepository.findById(id);
        if (!optionalDay.isPresent()){
            return new ApiResponse("not found  name day",false);
        }
        Day day = optionalDay.get();
        day.setName(dayDto.getName());

        dayRepository.save(day);
        return new ApiResponse("edit day succes",true);
    }

    @Override
    public ApiResponse deleteDay(Integer id) {
        try{
            dayRepository.deleteById(id);
            return new ApiResponse("delete student",true);
        }catch (Exception e){
            return new ApiResponse("Error",false);
        }
    }
}
