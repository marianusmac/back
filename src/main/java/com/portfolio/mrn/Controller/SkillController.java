package com.portfolio.mrn.Controller;

import com.portfolio.mrn.Entity.Person;
import com.portfolio.mrn.Entity.Skill;
import com.portfolio.mrn.Service.PersonService;
import com.portfolio.mrn.Service.SkillService;
import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/skill")
public class SkillController {

    @Autowired
    SkillService skillService;

    @Autowired
    PersonService personService;

    
    @GetMapping("/getskills")
    public ResponseEntity<List<Skill>> getAllSkill() {
        List<Skill> skillList = skillService.getAllSkill();
        return new ResponseEntity<>(skillList, HttpStatus.OK);
    }
    
    @GetMapping("/getone/{id}")
    public ResponseEntity<Skill> getSkillById(@PathVariable(value = "id") Long id) {
        Skill skill = skillService.getSkillByID(id);
        return new ResponseEntity<>(skill, HttpStatus.OK);
    }
    
    @GetMapping("/person/{person_id}")
    public ResponseEntity<List<Skill>> getAllSkillByPersonId(@PathVariable(value = "person_id") Long personId) {
        List<Skill> skillList = new ArrayList<>();
        if (personService.getPersonByID(personId) != null) {
            skillList = skillService.getSkillByPersonId(personId);
            return new ResponseEntity<>(skillList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(skillList, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/person/{person_id}")
    public ResponseEntity<Skill> createSkill(@PathVariable(value = "person_id") Long personId, 
            @RequestBody Skill skillRequest) {
        Person per = personService.findById(personId);
        skillRequest.setPerson(per);
        Skill newSkill = skillService.saveSkill(skillRequest);
        return new ResponseEntity<>(newSkill, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteSkill(@PathVariable("id") Long id) {
        skillService.deleteSkillById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    //Usar: Hernan nuevo
   @PutMapping("/editar/{id}")
    public ResponseEntity<Skill> updateSkill(@PathVariable("id") Long id, @RequestBody Skill skillRequest) {
        Skill skill =skillService.getSkillByID(id);
        // .orElseThrow(() -> new ResourceNotFoundException("EducationId " + id + "not found"));
        skill.setPerson(skillRequest.getPerson());
        skillRequest.setId(skill.getId());
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setSkipNullEnabled(true).setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.map(skillRequest, skill);

        return new ResponseEntity<>(skillService.saveSkill(skill), HttpStatus.OK);
    }
    
    //De hernan,(copia del de person) solo cambie person por skill. 
    //Trae un skill sin relacion
    @PutMapping("/change/{id}")
    public Skill cambiarSkill(@PathVariable("id") Long id, @RequestBody Skill skillTochange) {

        Skill s = skillService.findSkill(id);
        System.out.println(s);
        skillTochange.setId(s.getId());
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setSkipNullEnabled(true).setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.map(skillTochange, s);
        return skillService.saveSkill(s);
    }
    
}