package com.albertflex.blacksky.mapper;

import com.albertflex.blacksky.domain.Artifact;
import com.albertflex.blacksky.domain.ArtifactType;
import java.util.List;

import com.albertflex.blacksky.query.ArtifactQuery;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ArtifactMapper {

    List<Artifact> fetchByType(Long typeId);
    List<Artifact> fetchByUser(Long userId);
    Artifact fetch(Long artifactId);
    ArtifactType fetchType(Long typeId);

    List<Artifact> query(ArtifactQuery query);
    List<Artifact> fetchNews(Integer size);
    List<ArtifactType> fetchNewsType();

    List<ArtifactType> fetchTypes(String name);
    List<Artifact> fetchArtifacts(String name);
    
    int newType(ArtifactType type);
    int newArtifact(Artifact artifact);
    
    int update(Artifact artifact);
    int delete(Long artifactId);
    int deleteType(Long typeId);
}
