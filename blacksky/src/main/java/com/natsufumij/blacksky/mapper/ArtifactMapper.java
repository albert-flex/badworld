package com.natsufumij.blacksky.mapper;

import com.natsufumij.blacksky.domain.Artifact;
import com.natsufumij.blacksky.domain.ArtifactType;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ArtifactMapper {

    List<Artifact> fetchByType(Long typeId);
    List<Artifact> fetchByUser(Long userId);
    Artifact fetch(Long artifactId);
    ArtifactType fetchType(Long typeId);
    
    List<ArtifactType> fetchTypes(String name);
    List<Artifact> fetchArtifacts(String name);
    
    int newType(ArtifactType type);
    int newArtifact(Artifact artifact);
    
    int update(Artifact artifact);
    int delete(Long artifactId);
    int deleteType(Long typeId);
}
