package com.natsufumij.blacksky.mapper;

import com.natsufumij.blacksky.domain.Artifact;
import com.natsufumij.blacksky.domain.ArtifactType;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author Administrator
 */
@SpringBootTest
public class ArtifactMapperTest {
    
    @Autowired
    private ArtifactMapper artifactMapper;
    
    @Test
    public void testFetch(){
        
        //Test FetchByType
        List<Artifact> list=artifactMapper.fetchByType(5L);
        Assertions.assertEquals(2, list.size());
        Assertions.assertEquals("樱花变奏曲", list.get(0).getName());
        Assertions.assertEquals("谋杀者Z", list.get(1).getName());
        
        list = artifactMapper.fetchByType(100L);
        Assertions.assertEquals(0, list.size());
        
        
        //Test FetchByUser
        List<Artifact> list2=artifactMapper.fetchByUser(100L);
        Assertions.assertEquals(5, list2.size());
        
        list2 = artifactMapper.fetchByUser(200L);
        Assertions.assertEquals(0, list2.size());
        
        
        //Test Fetch
        Artifact excepted = artifactMapper.fetch(4L);
        Assertions.assertNotNull(excepted);
        Assertions.assertEquals("呜咽", excepted.getName());
        
        excepted = artifactMapper.fetch(300L);
        Assertions.assertNull(excepted);
        
        
        //Test FetchArtifacts
        List<Artifact> artifacts=artifactMapper.fetchArtifacts("世界");
        Assertions.assertEquals(2, artifacts.size());
        Assertions.assertEquals("世界史", artifacts.get(0).getName());
        Assertions.assertEquals("世界寻宝游戏", artifacts.get(1).getName());
        
        artifacts = artifactMapper.fetchArtifacts("阿尔伯特游戏");
        Assertions.assertEquals(0, artifacts.size());
        
        
        //Test FetchTypes
        List<ArtifactType> types=artifactMapper.fetchTypes("M");
        Assertions.assertEquals(2, types.size());
        
        types=artifactMapper.fetchTypes("Manga");
        Assertions.assertEquals(1, types.size());
        
        types=artifactMapper.fetchTypes("Mangaa");
        Assertions.assertEquals(0, types.size());        
    }
    
    @Test
    public void testNew(){
        
        //Test NewType
        List<ArtifactType> exceptedType = artifactMapper.fetchTypes("Blog");
        Assertions.assertEquals(0,exceptedType.size());        
        
        ArtifactType type=new ArtifactType();
        type.setName("Blog");
        type.setUserId(100L);
        artifactMapper.newType(type);
        Assertions.assertNotNull(type.getId());
        
        exceptedType = artifactMapper.fetchTypes("Blog");
        Assertions.assertEquals(1,exceptedType.size());

        
        //Test NewArtifact
        Artifact a=new Artifact();
        a.setContent("CCCC");
        a.setName("World");
        a.setTitle("The World");
        a.setVersion("2022年第一版");
        a.setUserId(102L);
        a.setTypeId(1L);
        artifactMapper.newArtifact(a);
        Assertions.assertNotNull(a.getId());
        
        Artifact exceptedArtifact = artifactMapper.fetch(a.getId());
        Assertions.assertNotNull(exceptedArtifact);
        Assertions.assertEquals("World", exceptedArtifact.getName());
        
        artifactMapper.delete(exceptedArtifact.getId());
        artifactMapper.deleteType(exceptedType.get(0).getId());
        
        exceptedType = artifactMapper.fetchTypes("Blog");
        Assertions.assertEquals(0,exceptedType.size());
        exceptedArtifact = artifactMapper.fetch(a.getId());
        Assertions.assertNull(exceptedArtifact);
    }
    
    @Test
    public void testUpdate(){
        //Test Update
        Artifact exceptedArtifact = artifactMapper.fetch(3L);
        Date updateDate=exceptedArtifact.getUpdateTime();

        Artifact artifact=new Artifact();
        artifact.setId(3L);
        artifact.setName("清单");
        artifactMapper.update(artifact);
        
        artifact.setId(300L);
        int result=artifactMapper.update(artifact);
        Assertions.assertEquals(0, result);

        exceptedArtifact = artifactMapper.fetch(3L);
        Assertions.assertNotNull(exceptedArtifact.getId());
        Assertions.assertEquals("清单", exceptedArtifact.getName());
        Assertions.assertNotEquals(updateDate, exceptedArtifact.getUpdateTime());
    }
}
