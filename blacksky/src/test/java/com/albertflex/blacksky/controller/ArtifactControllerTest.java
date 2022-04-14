/*

 */
package com.albertflex.blacksky.controller;

import com.albertflex.blacksky.domain.Artifact;
import com.albertflex.blacksky.domain.ArtifactType;

import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Administrator
 */
public class ArtifactControllerTest extends AbstractControllerTest {

    @Test
    public void testFetch() throws Exception {
        //Test Fetch Artifact
        Artifact excepted;

        excepted = getResult("/artifact/1", Artifact.class);
        Assertions.assertNotNull(excepted.getId());

        excepted = getResult("/artifact/130", Artifact.class);
        Assertions.assertNotNull(excepted);

        //Test Fetch Artifact List
        List<Artifact> exceptList = getListResult("/artifact/by_type/5", Artifact.class);
        Assertions.assertEquals(2, exceptList.size());

        exceptList = getListResult("/artifact/by_user/100", Artifact.class);
        Assertions.assertEquals(5, exceptList.size());

        //Test Fetch ArtifactType
        ArtifactType type = getResult("/artifact/type/5", ArtifactType.class);
        Assertions.assertNotNull(type.getId());
        Assertions.assertEquals("Manga", type.getName());

        type = getResult("/artifact/type/500", ArtifactType.class);
        Assertions.assertNull(type.getId());

        //Test Fetch Artifact By Name
        List<Artifact> artifacts = getListResult("/artifact/artifacts/世界史", Artifact.class);
        Assertions.assertEquals(1, artifacts.size());
        artifacts = getListResult("/artifact/artifacts/杀死比尔", Artifact.class);
        Assertions.assertEquals(0, artifacts.size());

        //Test Fetch Artifact Type
        List<ArtifactType> types = getListResult("/artifact/types/M", ArtifactType.class);
        Assertions.assertEquals(2, types.size());
        types = getListResult("/artifact/types/MXX", ArtifactType.class);
        Assertions.assertEquals(0, types.size());
    }

    @Test
    public void testNew() throws Exception {
        //Test NewType
        List<ArtifactType> exceptedType = getListResult("/artifact/types/Blog", ArtifactType.class);
        Assertions.assertEquals(0, exceptedType.size());

        ArtifactType type = new ArtifactType();
        type.setName("Blog");
        type.setUserId(100L);
        type = postResult("/artifact/type", type, ArtifactType.class);
        Assertions.assertNotNull(type.getId());

        exceptedType = getListResult("/artifact/types/Blog", ArtifactType.class);
        Assertions.assertEquals(1, exceptedType.size());

        //Test NewArtifact
        Artifact a = new Artifact();
        a.setContent("CCCC");
        a.setName("World");
        a.setTitle("The World");
        a.setVersion("2022年第一版");
        a.setUserId(102L);
        a.setTypeId(1L);
        a = postResult("/artifact", a, Artifact.class);
        Assertions.assertNotNull(a.getId());

        Artifact exceptedArtifact = getResult("/artifact/" + a.getId(), Artifact.class);
        Assertions.assertNotNull(exceptedArtifact);
        Assertions.assertEquals("World", exceptedArtifact.getName());

        deleteResult("/artifact/" + exceptedArtifact.getId(), Boolean.class);
        deleteResult("/artifact/type/" + exceptedType.get(0).getId(), Boolean.class);

        exceptedType = getListResult("/artifact/types/Blog", ArtifactType.class);
        Assertions.assertEquals(0, exceptedType.size());
        exceptedArtifact = getResult("/artifact/" + a.getId(), Artifact.class);
        Assertions.assertNull(exceptedArtifact.getId());
    }
    
    @Test
    public void testUpdate() throws Exception{
        //Test Update
        Artifact exceptedArtifact = getResult("/artifact/3",Artifact.class);
        Date updateDate = exceptedArtifact.getUpdateTime();

        Artifact artifact = new Artifact();
        artifact.setId(3L);
        artifact.setName("清单");
        Assertions.assertTrue(putResult("/artifact", artifact, Boolean.class));

        artifact.setId(300L);
        Assertions.assertFalse(putResult("/artifact", artifact, Boolean.class));

        exceptedArtifact = getResult("/artifact/3",Artifact.class);
        Assertions.assertNotNull(exceptedArtifact.getId());
        Assertions.assertEquals("清单", exceptedArtifact.getName());
        Assertions.assertNotEquals(updateDate, exceptedArtifact.getUpdateTime());
    }
}
