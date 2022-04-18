/*

 */
package com.albertflex.blacksky.controller;

import com.albertflex.blacksky.domain.Artifact;
import com.albertflex.blacksky.domain.ArtifactType;
import com.albertflex.blacksky.query.ArtifactQuery;
import com.albertflex.blacksky.query.PageData;
import com.albertflex.blacksky.query.PageQuery;
import com.albertflex.blacksky.service.ArtifactServices;

import java.util.Collections;
import java.util.List;

import com.github.pagehelper.Page;
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
    public PageData<Artifact> fetchByType(@PathVariable("typeId") Long typeId, PageQuery page) {
        page.start();
        return PageData.of(artifactServices.fetchByType(typeId));
    }

    @GetMapping("/by_user/{userId}")
    public PageData<Artifact> fetchByUser(@PathVariable("userId") Long userId, PageQuery page) {
        page.start();
        return PageData.of(artifactServices.fetchByUser(userId));
    }

    @GetMapping("/{artifactId}")
    public Artifact fetch(@PathVariable("artifactId") Long artifactId){
        return artifactServices.fetch(artifactId);
    }

    @GetMapping("/fetch/newest")
    public PageData<Artifact> fetchNews(PageQuery page) {
        page.start();
        return PageData.of(artifactServices.fetchNews());
    }

    @GetMapping("/fetch/query")
    public PageData<Artifact> query(ArtifactQuery query){
        query.start();
        if(query==null)return PageData.empty();

        return PageData.of(artifactServices.query(query));
    }

    @GetMapping("/fetch/newstype")
    public List<ArtifactType> fetchNewsType(PageQuery page) {
        return artifactServices.fetchNewsType();
    }

    @GetMapping("/type/{typeId}")
    public ArtifactType fetchType(@PathVariable("typeId") Long typeId) {
        return artifactServices.fetchType(typeId);
    }
    
    @GetMapping("/types/{name}")
    public List<ArtifactType> fetchTypes(@PathVariable("name") String name,PageQuery page) {
        return artifactServices.fetchTypes(name);
    }
    
    @GetMapping("/artifacts/{name}")
    public PageData<Artifact> fetchArtifacts(@PathVariable("name") String name,PageQuery page) {
        page.start();
        return PageData.of(artifactServices.fetchArtifacts(name));
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
