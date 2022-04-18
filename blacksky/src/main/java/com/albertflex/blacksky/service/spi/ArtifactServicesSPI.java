/*

 */
package com.albertflex.blacksky.service.spi;

import com.albertflex.blacksky.domain.Artifact;
import com.albertflex.blacksky.domain.ArtifactType;
import com.albertflex.blacksky.mapper.ArtifactMapper;
import com.albertflex.blacksky.query.ArtifactQuery;
import com.albertflex.blacksky.service.ArtifactServices;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArtifactServicesSPI implements ArtifactServices {

    private final ArtifactMapper artifactMapper;

    private static final Artifact EMPTY_ARTIFACT = new Artifact();
    private static final ArtifactType EMPTY_ARTIFACT_TYPE = new ArtifactType();

    @Autowired
    public ArtifactServicesSPI(ArtifactMapper artifactMapper) {
        this.artifactMapper = artifactMapper;
    }

    @Override
    public List<Artifact> fetchByType(Long typeId) {
        return artifactMapper.fetchByType(typeId);
    }

    @Override
    public List<Artifact> fetchByUser(Long userId) {
        return artifactMapper.fetchByUser(userId);
    }

    @Override
    public Artifact fetch(Long artifactId) {
        Artifact result = artifactMapper.fetch(artifactId);
        if (result == null) {
            return EMPTY_ARTIFACT;
        } else {
            return result;
        }
    }

    @Override
    public List<ArtifactType> fetchTypes(String name) {
        return artifactMapper.fetchTypes(name);
    }

    @Override
    public List<Artifact> fetchArtifacts(String name) {
        return artifactMapper.fetchArtifacts(name);
    }

    @Override
    public ArtifactType newType(ArtifactType type) {
        artifactMapper.newType(type);
        return type;
    }

    @Override
    public Artifact newArtifact(Artifact artifact) {
        artifactMapper.newArtifact(artifact);
        return artifact;
    }

    @Override
    public Boolean update(Artifact artifact) {
        return artifactMapper.update(artifact) == 1;
    }

    @Override
    public Boolean delete(Long artifactId) {
        return artifactMapper.delete(artifactId) == 1;
    }

    @Override
    public Boolean deleteType(Long typeId) {
        return artifactMapper.deleteType(typeId) == 1;
    }

    @Override
    public ArtifactType fetchType(Long typeId) {
        ArtifactType type = artifactMapper.fetchType(typeId);
        if (type == null) {
            return EMPTY_ARTIFACT_TYPE;
        } else {
            return type;
        }
    }

    @Override
    public List<Artifact> fetchNews() {
        return artifactMapper.fetchNews();
    }

    @Override
    public List<Artifact> query(ArtifactQuery query) {
        return artifactMapper.query(query);
    }

    @Override
    public List<ArtifactType> fetchNewsType() {
        return artifactMapper.fetchNewsType();
    }
}
