/*

 */
package com.natsufumij.blacksky.service;

import com.natsufumij.blacksky.domain.Artifact;
import com.natsufumij.blacksky.domain.ArtifactType;
import java.util.List;

/**
 *
 * @author Administrator
 */
public interface ArtifactServices {

    List<Artifact> fetchByType(Long typeId);
    List<Artifact> fetchByUser(Long userId);
    Artifact fetch(Long artifactId);
    ArtifactType fetchType(Long typeId);
    
    List<ArtifactType> fetchTypes(String name);
    List<Artifact> fetchArtifacts(String name);
    
    ArtifactType newType(ArtifactType type);
    Artifact newArtifact(Artifact artifact);
    
    Boolean update(Artifact artifact);
    Boolean delete(Long artifactId);
    Boolean deleteType(Long typeId);    
}
