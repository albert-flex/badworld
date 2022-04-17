/*

 */
package com.albertflex.blacksky.controller;

import com.albertflex.blacksky.domain.Artifact;
import com.albertflex.blacksky.domain.ArtifactType;
import com.albertflex.blacksky.query.ArtifactQuery;
import com.albertflex.blacksky.service.ArtifactServices;

import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Administrator
 */
@RestController
@RequestMapping("/artifact")
public class ArtifactController {
    
    private final ArtifactServices artifactServices;

    @Autowired
    public ArtifactController(ArtifactServices artifactServices) {
        this.artifactServices = artifactServices;
    }
    
    @GetMapping("/by_type/{typeId}")
    public List<Artifact> fetchByType(@PathVariable("typeId") Long typeId) {
        return artifactServices.fetchByType(typeId);
    }

    @GetMapping("/by_user/{userId}")
    public List<Artifact> fetchByUser(@PathVariable("userId") Long userId) {
        return artifactServices.fetchByUser(userId);
    }

    @GetMapping("/{artifactId}")
    public Artifact fetch(@PathVariable("artifactId") Long artifactId) {
        return artifactServices.fetch(artifactId);
    }

    @GetMapping("/fetch/news")
    public List<Artifact> fetchNews(){
        return artifactServices.fetchNews();
    }

    @GetMapping("/query")
    public List<Artifact> query(ArtifactQuery query){
        if(query==null)return Collections.emptyList();

        return artifactServices.query(query);
    }

    @GetMapping("/fetch/newstype")
    public List<ArtifactType> fetchNewsType(){
        return artifactServices.fetchNewsType();
    }

    @GetMapping("/type/{typeId}")
    public ArtifactType fetchType(@PathVariable("typeId") Long typeId) {
        return artifactServices.fetchType(typeId);
    }
    
    @GetMapping("/types/{name}")
    public List<ArtifactType> fetchTypes(@PathVariable("name") String name) {
        return artifactServices.fetchTypes(name);
    }
    
    @GetMapping("/artifacts/{name}")
    public List<Artifact> fetchArtifacts(@PathVariable("name") String name) {
        return artifactServices.fetchArtifacts(name);
    }

    @PostMapping("/type")
    public ArtifactType newType(@RequestBody ArtifactType type) {
        artifactServices.newType(type);
        return type;
    }

    @PostMapping
    public Artifact newArtifact(@RequestBody Artifact artifact) {
        artifactServices.newArtifact(artifact);
        return artifact;
    }

    @PutMapping
    public Boolean update(@RequestBody Artifact artifact) {
        return artifactServices.update(artifact);
    }

    @DeleteMapping("/{artifactId}")
    public Boolean delete(@PathVariable("artifactId") Long artifactId) {
        return artifactServices.delete(artifactId);
    }

    @DeleteMapping("/type/{typeId}")
    public Boolean deleteType(@PathVariable("typeId") Long typeId) {
        return artifactServices.deleteType(typeId);
    }
}
